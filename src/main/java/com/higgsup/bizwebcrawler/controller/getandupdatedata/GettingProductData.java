package com.higgsup.bizwebcrawler.controller.getandupdatedata;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.authentication.RequestHeader;
import com.higgsup.bizwebcrawler.utils.CommonUtil;
import com.higgsup.bizwebcrawler.utils.DividePage;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.entites.product.Producer;
import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.entites.product.ProductGroup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
public class GettingProductData {
    private static final Logger logger = Logger.getLogger(GettingProductData.class.getName());
    private HtmlData authenticationGetRequest = new HtmlData();
    private static final String url = RequestHeader.urlWebsite + "/products?page=";
    private String htmlData;
    private Document getHTML;
    private String cookie;
    private Product product = new Product();
    private ProductGroup productGroup = new ProductGroup();
    private Producer producer = new Producer();
    public boolean getDataProductFromWeb(String htmlData, String cookie) throws IOException {
        this.cookie = cookie;
        this.htmlData = htmlData;
        startGettingProductData();
        return true;
    }

    private void startGettingProductData() {
        try {
            int pages = takeQuantityPage();
            for (int page = 1; page <= pages; page++) {
                authenticationGetRequest.connectURLAndTakeHTML(url + page, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                    throw new Error("Error cookie");
                }
                product = new Product();
                productGroup = new ProductGroup();
                producer = new Producer();
                getDataProduct();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private int takeQuantityPage() {
        DividePage dividePage = new DividePage();
        getHTML = Jsoup.parse(htmlData);
        dividePage.setDataCheckingFromWeb(getHTML);
        Elements getDataAllProducts = dividePage.getDataCheckingFromWeb();
        int allProducts = Integer.parseInt(CommonUtil.cutID(getDataAllProducts.text()));
        dividePage.setPage(allProducts);
        allProducts = dividePage.getPage();
        return allProducts;
    }

    private void getDataProduct() throws IOException {
        Elements getDataFromTrTags = getHTML.select("tbody tr");
        for (Element tags : getDataFromTrTags) {
            HashMap getDataCategoryProduct = new HashMap();//lấy danh mục mới, hot, sale
            Elements getDataFromAhrefTags = tags.select("td  a[href]");
            product.setProductID(CommonUtil.cutID(getDataFromAhrefTags.get(0).attr("href")));
            product.setName(getDataFromAhrefTags.get(0).text());
            Elements getIMGProduct = tags.select("td  img[src]");
            product.setImg("https:" + getIMGProduct.get(0).attr("src"));
            Elements getDataFromPTags = tags.select("td p");
            product.setStork(CommonUtil.cutQuantity(getDataFromPTags.get(0).text()));
            productGroup.setName(getDataFromPTags.get(1).text());
            producer.setName(getDataFromPTags.get(2).text());
            getContentProduct(getDataFromTrTags,getDataCategoryProduct);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getContentProduct(Elements getDataFromTrTags,HashMap getDataCategoryProduct) throws IOException {
        String titleURL;
        authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/products/" + product.getProductID(), cookie);
        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
        titleURL = getHTML.title();
        if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
            throw new Error("Error cookie");
        }
        Elements getDataFromDivRowTag = getHTML.select("div.row");
        if (getDataFromDivRowTag.size() > 0) {
            getDataFromTrTags = getDataFromDivRowTag.get(0).select("div.controls textarea[bind*=content]");

        } else {
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/products/" + product.getProductID(), cookie);
            getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
            titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                throw new Error("Error cookie");
            }
            getDataFromDivRowTag = getHTML.select("div.row");
            if (getDataFromDivRowTag.size() < 0) {
                throw new Error("False " + product.getProductID());
            }
        }
        if (getDataFromTrTags.size() > 0) {
            product.setContent(getDataFromTrTags.get(0).text());
        }
        getDataFromTrTags = getDataFromDivRowTag.get(2).select("a[href]");
        for (int j = 1; j < getDataFromTrTags.size(); j++) {
            if (getDataFromTrTags.get(j).text().toString().length() > 0) {
                getDataCategoryProduct.put(CommonUtil.cutID(getDataFromTrTags.get(j).attr("href")), getDataFromTrTags.get(j).text());
            }
        }
        Elements checkVersionProduct = getHTML.select("div.row a[bind-event-click*=changeOptionNamesModal.show(]");
        saveAndUpdateProductData(checkVersionProduct, getDataFromDivRowTag, getDataCategoryProduct);
    }
    public void saveAndUpdateProductData( Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct) {
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            product.setDescription("");
            product.setWeight(0.0);
            queryDataBase.setDataProductGroup(productGroup.getName());
            product.setProductGroupId(queryDataBase.getIDProductGroup(productGroup.getName()));
            queryDataBase.setDataProducer(producer.getName());
            product.setProducerId(queryDataBase.getIDProducer(producer.getName()));
            if (queryDataBase.hasProductID(product.getProductID())) {
                setProductToDB(checkVersionProduct,getDataFromDivRowTag,getDataCategoryProduct,queryDataBase);
            } else {
                updateProductToDB(checkVersionProduct,getDataFromDivRowTag,getDataCategoryProduct,queryDataBase);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    private void updateProductToDB(Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct,QueryDataBase queryDataBase){
        if (checkVersionProduct.size() >= 1) {
            product.setPrice(0.0);
        } else {
            Elements getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value]");
            String checkGetPrice = getDataFromTrTags.get(0).attr("value");
            if (checkGetPrice == null) {
                product.setPrice(0.0);
            } else {
                product.setPrice(Double.parseDouble(checkGetPrice));
            }
            queryDataBase.setDataProductGroup(productGroup.getName());
        }
        ArrayList<Product> dataProducerFromProductID = null;
        dataProducerFromProductID = queryDataBase.getDataProductFromProductID(product.getProductID());
        if (!(dataProducerFromProductID.get(0).getName().equals(product.getName()) && String.valueOf(dataProducerFromProductID.get(0).getPrice()).equals(String.valueOf(product.getPrice())) && String.valueOf(dataProducerFromProductID.get(0).getStork()).equals(product.getStork()) && dataProducerFromProductID.get(0).getContent().equals(product.getContent()) && dataProducerFromProductID.get(0).getImg().equals(product.getImg()) && String.valueOf(dataProducerFromProductID.get(0).getProductGroupId()).equals(String.valueOf(queryDataBase.getIDProductGroup(productGroup.getName()))) && String.valueOf(dataProducerFromProductID.get(0).getProducerId()).equals(String.valueOf(queryDataBase.getIDProducer(producer.getName()))))) {
            queryDataBase.updateProduct(product);
        }
        ArrayList<String> listProductCateID = null;
        listProductCateID = queryDataBase.getListProductCateIdFormProductIdInCategoryProduct(product.getProductID());
        Set set = getDataCategoryProduct.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i.next();
            queryDataBase.setDataProductCategory((String) mapEntry.getKey(), (String) mapEntry.getValue());
            int indexList = listProductCateID.indexOf((String) mapEntry.getKey());
            if (indexList >= 0) {
                listProductCateID.remove(indexList);
            } else {
                queryDataBase.setDataFromCategoryProductAndProduct((String) mapEntry.getKey(), product.getProductID());
            }
        }
        for (String productCate_ID : listProductCateID) {
            queryDataBase.remoDataCategoryProductFromCateIdAndProductId(productCate_ID, product.getProductID());
        }
        dataProducerFromProductID.clear();
    }
    private void setProductToDB(Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct,QueryDataBase queryDataBase ){
        if (checkVersionProduct.size() >= 1) {
            product.setPrice(0.0);
            queryDataBase.setDataProduct(product);
        } else {
            Elements getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value] ");
            String checkGetPrice = getDataFromTrTags.get(0).attr("value");

            if (checkGetPrice == null) {
                product.setPrice(0.0);
            } else {
                product.setPrice(Double.parseDouble(checkGetPrice));
            }
            queryDataBase.setDataProduct(product);
            Set set = getDataCategoryProduct.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) i.next();
                queryDataBase.setDataProductCategory((String) mapEntry.getKey(), (String) mapEntry.getValue());
                queryDataBase.setDataFromCategoryProductAndProduct((String) mapEntry.getKey(), product.getProductID());
            }
        }
    }
}
