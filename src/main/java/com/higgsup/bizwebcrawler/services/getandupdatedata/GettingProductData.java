package com.higgsup.bizwebcrawler.services.getandupdatedata;

import com.higgsup.bizwebcrawler.entites.product.*;
import com.higgsup.bizwebcrawler.repositories.*;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import com.higgsup.bizwebcrawler.utils.CommonUtil;
import com.higgsup.bizwebcrawler.utils.DividePage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by viquynh
 */
@Component
public class GettingProductData {
    private static final Logger logger = LoggerFactory.getLogger(GettingProductData.class);
    private HtmlData authenticationGetRequest = new HtmlData();
    private static final String url = RequestHeader.urlWebsite + "/products?page=";
    private String htmlData;
    private Document getHTML;
    private String cookie;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductGroupRepo productGroupRepo;
    @Autowired
    ProducerRepo producerRepo;
    @Autowired
    ProductCategoryRepo productCategoryRepo;
    @Autowired
    CategoryProductRepo categoryProductRepo;
    private Product product;
    private ProductGroup productGroup;
    private Producer producer;
    private final int TIME_SLEEP =8;
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
            product = new Product();
            productGroup = new ProductGroup();
            producer = new Producer();
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
                TimeUnit.SECONDS.sleep(TIME_SLEEP);
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
            product.setDescription("");
            product.setWeight(0.0);
            if(productGroupRepo.getIDProductGroup(productGroup.getName()) ==null){
                productGroupRepo.save(productGroup);
            }
            product.setProductGroupId(productGroupRepo.getIDProductGroup(productGroup.getName()));

            if(!producerRepo.hasProducerByName(producer.getName())){
                producerRepo.save(producer);
            }
            product.setProducerId(producerRepo.getIdProducerByName(producer.getName()));
            if (productRepo.findById(product.getProductID())==null) {
                setProductToDB(checkVersionProduct,getDataFromDivRowTag,getDataCategoryProduct);
            } else {
               updateProductToDB(checkVersionProduct,getDataFromDivRowTag,getDataCategoryProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveAndUpdateProductData error ", e);
        }
    }
    private void updateProductToDB(Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct){
        if (checkVersionProduct.size() >= 1) {
            product.setPrice(0.0);
        } else {
            String checkGetPrice=null;
            if(getDataFromDivRowTag.size()>=7){
                Elements getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value] ");
                checkGetPrice = getDataFromTrTags.get(0).attr("value");
            }
            if (checkGetPrice == null) {
                product.setPrice(0.0);
            } else {
                product.setPrice(Double.parseDouble(checkGetPrice));
            }
        }
       Product dataProducerFromProductID = productRepo.getDataProductFromProductID(product.getProductID());
        if (!(dataProducerFromProductID.getName().equals(product.getName()) && String.valueOf(dataProducerFromProductID.getPrice()).equals(String.valueOf(product.getPrice())) && dataProducerFromProductID.getStork()==product.getStork() && dataProducerFromProductID.getContent().equals(product.getContent()) && dataProducerFromProductID.getImg().equals(product.getImg()) && String.valueOf(dataProducerFromProductID.getProductGroupId()).equals(String.valueOf(productGroupRepo.getIDProductGroup(productGroup.getName()))) && String.valueOf(dataProducerFromProductID.getProducerId()).equals(String.valueOf(producerRepo.getIdProducerByName(producer.getName()))))) {
            productRepo.save(product);
        }
        List<String> listProductCateID = categoryProductRepo.getListProductCateIdFormProductIdInCategoryProduct(product.getProductID());
        Set set = getDataCategoryProduct.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) i.next();
            ProductCategory productCategory=new ProductCategory(String.valueOf(mapEntry.getKey()),String.valueOf( mapEntry.getValue()));
           productCategoryRepo.save(productCategory);
           int indexList = listProductCateID.indexOf(mapEntry.getKey());
             if (indexList >= 0) {
                listProductCateID.remove(indexList);
            } else {
              categoryProductRepo.save(new CategoryProduct((String) mapEntry.getKey(),product.getProductID()));
            }
        }
         for (String productCateID : listProductCateID) {
            categoryProductRepo.deleteCategoryProduct(productCateID,product.getProductID());
        }

    }
    private void setProductToDB(Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct){
        if (checkVersionProduct.size() >= 1) {
            product.setPrice(0.0);
            productRepo.save(product);
        } else {
            String checkGetPrice=null;
            if(getDataFromDivRowTag.size()>=7){
                Elements getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value] ");
                checkGetPrice = getDataFromTrTags.get(0).attr("value");
            }
            if (checkGetPrice == null) {
                product.setPrice(0.0);
            } else {
                product.setPrice(Double.parseDouble(checkGetPrice));
            }
            productRepo.save(product);
            Set set = getDataCategoryProduct.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) i.next();
                ProductCategory productCategory=new ProductCategory((String) mapEntry.getKey(),(String) mapEntry.getValue());
                if(productCategoryRepo.findCategoryById((String) mapEntry.getKey())==null){
                    productCategoryRepo.save(productCategory);
                }
                if(categoryProductRepo.findIdByName((String) mapEntry.getKey(),product.getProductID())==-1){
                categoryProductRepo.save(new CategoryProduct((String) mapEntry.getKey(),product.getProductID()));
                }

            }
        }
    }
}
