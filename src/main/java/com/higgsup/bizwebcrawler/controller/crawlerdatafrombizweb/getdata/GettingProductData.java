package com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getdata;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
public class GettingProductData {
    private static final Logger logger = Logger.getLogger(GettingProductData.class.getName());
    private HtmlData authenticationGetRequest = new HtmlData();

    public boolean getDataProductFromWebAndSetToDataBase(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                throw new Error("Error cookie");
            }
            Elements getDataAllProducts = getHTML.select("div div[class*=t-status-text dataTables_info]");
            int allProducts = Integer.parseInt(commonUtil.cutID(getDataAllProducts.text()));
            if (allProducts > 50) {
                if (allProducts % 50 == 0) {
                    System.out.println(allProducts / 50);
                    allProducts = allProducts / 50;
                } else {
                    allProducts = allProducts / 50 + 1;
                }
            } else {
                allProducts = 1;
            }
            for (int ii = 1; ii <= allProducts; ii++) {
                authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products?page=" + ii, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                    throw new Error("Error cookie");

                }
                Elements getDataFromTrTags = getHTML.select("tbody tr");
                for (Element tags : getDataFromTrTags) {
                    String[] fullDataFromTags = new String[15];
                    HashMap getDataCategoryProduct = new HashMap();//lấy danh mục mới, hot, sale
                    Elements getDataFromAhrefTags = tags.select("td  a[href]");
                    fullDataFromTags[0] = commonUtil.cutID(getDataFromAhrefTags.get(0).attr("href"));
                    if (queryDataBase.hasProductID(fullDataFromTags[0])) {
                        fullDataFromTags[1] = getDataFromAhrefTags.get(0).text();
                        Elements getIMGProduct = tags.select("td  img[src]");
                        fullDataFromTags[2] = "https:" + getIMGProduct.get(0).attr("src");
                        Elements getDataFromPTags = tags.select("td p");
                        fullDataFromTags[3] = commonUtil.cutQuantity(getDataFromPTags.get(0).text());
                        if (fullDataFromTags[3].equals("")) {
                            fullDataFromTags[3] = "0";
                        }
                        fullDataFromTags[4] = getDataFromPTags.get(1).text();
                        fullDataFromTags[5] = getDataFromPTags.get(2).text();
                        queryDataBase.setDataProducer(fullDataFromTags[5]);
                        authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products/" + fullDataFromTags[0], cookie);
                        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                        titleURL = getHTML.title();
                        if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                            throw new Error("Error cookie");
                        }
                        Elements getDataFromDivRowTag = getHTML.select("div.row");
                        if(getDataFromDivRowTag.size()>0){
                            getDataFromTrTags = getDataFromDivRowTag.get(0).select("div.controls textarea[bind*=content]");

                        }else {
                            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products/" + fullDataFromTags[0], cookie);
                            getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                            titleURL = getHTML.title();
                            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                                throw new Error("Error cookie");
                            }
                            getDataFromDivRowTag = getHTML.select("div.row");
                            if(getDataFromDivRowTag.size()<0){
                                throw new Error("False "+fullDataFromTags[0]);
                            }
                        }
                        if(getDataFromTrTags.size()>0){
                            fullDataFromTags[6] = getDataFromTrTags.get(0).text();
                        }
                        getDataFromTrTags = getDataFromDivRowTag.get(2).select("a[href]");
                        for (int i = 1; i < getDataFromTrTags.size(); i++) {
                            if (getDataFromTrTags.get(i).text().toString().length() > 0) {
                                getDataCategoryProduct.put(commonUtil.cutID(getDataFromTrTags.get(i).attr("href")), getDataFromTrTags.get(i).text());
                            }
                        }
                        Elements checkVersionProduct = getHTML.select("div.row a[bind-event-click*=changeOptionNamesModal.show(]");
                        System.out.println(checkVersionProduct.size());
                        if (checkVersionProduct.size() >= 1) {
                            fullDataFromTags[7] = "0.0";
                            logger.info("ID Sản Phẩm :" + fullDataFromTags[0]);
                            logger.info("Tên sản phẩm: " + fullDataFromTags[1]);
                            logger.info("getIMGProduct sản phẩm :" + fullDataFromTags[2]);
                            logger.info("Số Lượng: " + fullDataFromTags[3]);
                            logger.info("Thể Loại: " + fullDataFromTags[4]);
                            logger.info("Tác Giả " + fullDataFromTags[5]);
                            logger.info("Mô Tả: " + fullDataFromTags[6]);
                            logger.info("số 7: " + fullDataFromTags[7]);
                            queryDataBase.setDataProductGroup(fullDataFromTags[4]);
                            queryDataBase.setDataProduct(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[7], fullDataFromTags[3], fullDataFromTags[6], fullDataFromTags[2], queryDataBase.getIDProductGroup(fullDataFromTags[4]), queryDataBase.getIDProducer(fullDataFromTags[5]));
                        } else {
                            getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value] ");
                            fullDataFromTags[7] = getDataFromTrTags.get(0).attr("value");
                            if (fullDataFromTags[7] == null) {
                                fullDataFromTags[7] = "0";
                            }
                            queryDataBase.setDataProductGroup(fullDataFromTags[4]);
                            queryDataBase.setDataProduct(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[7], fullDataFromTags[3], fullDataFromTags[6], fullDataFromTags[2], queryDataBase.getIDProductGroup(fullDataFromTags[4]), queryDataBase.getIDProducer(fullDataFromTags[5]));
                            Set set = getDataCategoryProduct.entrySet();
                            Iterator i = set.iterator();
                            while (i.hasNext()) {
                                Map.Entry mapEntry = (Map.Entry) i.next();
                                queryDataBase.setDataProductCategory((String) mapEntry.getKey(), (String) mapEntry.getValue());
                                queryDataBase.setDataFromCategoryProductAndProduct((String) mapEntry.getKey(), fullDataFromTags[0]);
                            }
                        }
                        TimeUnit.SECONDS.sleep(10);
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
