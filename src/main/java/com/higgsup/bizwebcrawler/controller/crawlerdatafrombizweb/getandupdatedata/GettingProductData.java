package com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getandupdatedata;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.common.DividePage;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.product.Producer;
import com.higgsup.bizwebcrawler.model.product.Product;
import com.higgsup.bizwebcrawler.model.product.ProductGroup;
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

    public boolean getDataProductFromWeb(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        DividePage dividePage = new DividePage();
        String titleURL;
        try {
            Document getHTML = Jsoup.parse(get);
            dividePage.setCheckDataFromWeb(getHTML);
            Elements getDataAllProducts = dividePage.getCheckDataFromWeb();
            int allProducts = Integer.parseInt(commonUtil.cutID(getDataAllProducts.text()));
            dividePage.setPage(allProducts);
            allProducts = dividePage.getPage();
            for (int ii = 1; ii <= allProducts; ii++) {
                authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products?page=" + ii, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                    throw new Error("Error cookie");
                }
                Elements getDataFromTrTags = getHTML.select("tbody tr");
                for (Element tags : getDataFromTrTags) {
                    Product product = new Product();
                    ProductGroup productGroup = new ProductGroup();
                    Producer producer = new Producer();
                    HashMap getDataCategoryProduct = new HashMap();//lấy danh mục mới, hot, sale
                    Elements getDataFromAhrefTags = tags.select("td  a[href]");
                    product.setProductID(commonUtil.cutID(getDataFromAhrefTags.get(0).attr("href")));
                    product.setName(getDataFromAhrefTags.get(0).text());
                    Elements getIMGProduct = tags.select("td  img[src]");
                    product.setImg("https:" + getIMGProduct.get(0).attr("src"));
                    Elements getDataFromPTags = tags.select("td p");
                    product.setStork(commonUtil.cutQuantity(getDataFromPTags.get(0).text()));
                    productGroup.setName(getDataFromPTags.get(1).text());
                    producer.setName(getDataFromPTags.get(2).text());
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products/" + product.getProductID(), cookie);
                    getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                    titleURL = getHTML.title();
                    if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                        throw new Error("Error cookie");
                    }
                    Elements getDataFromDivRowTag = getHTML.select("div.row");
                    if (getDataFromDivRowTag.size() > 0) {
                        getDataFromTrTags = getDataFromDivRowTag.get(0).select("div.controls textarea[bind*=content]");

                    } else {
                        authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products/" + product.getProductID(), cookie);
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
                    for (int i = 1; i < getDataFromTrTags.size(); i++) {
                        if (getDataFromTrTags.get(i).text().toString().length() > 0) {
                            getDataCategoryProduct.put(commonUtil.cutID(getDataFromTrTags.get(i).attr("href")), getDataFromTrTags.get(i).text());
                        }
                    }
                    Elements checkVersionProduct = getHTML.select("div.row a[bind-event-click*=changeOptionNamesModal.show(]");
                    saveAndUpdateProductData(product, productGroup, producer, checkVersionProduct, getDataFromDivRowTag, getDataCategoryProduct);
                    TimeUnit.SECONDS.sleep(10);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return true;
    }

    public void saveAndUpdateProductData(Product product, ProductGroup productGroup, Producer producer, Elements checkVersionProduct, Elements getDataFromDivRowTag, HashMap getDataCategoryProduct) {
        try {
            Elements getDataFromTrTags;
            QueryDataBase queryDataBase = new QueryDataBase();
            product.setDescription("");
            product.setWeight(0.0);
            queryDataBase.setDataProductGroup(productGroup.getName());
            product.setProductGroupId(queryDataBase.getIDProductGroup(productGroup.getName()));
            queryDataBase.setDataProducer(producer.getName());
            product.setProducerId(queryDataBase.getIDProducer(producer.getName()));
            if (queryDataBase.hasProductID(product.getProductID())) {
                if (checkVersionProduct.size() >= 1) {
                    product.setPrice(0.0);
                    queryDataBase.setDataProduct(product);
                } else {
                    getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value] ");
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
            } else {
                if (checkVersionProduct.size() >= 1) {
                    product.setPrice(0.0);

                } else {
                    getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value]");
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
                } else {
                    //no update
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
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
