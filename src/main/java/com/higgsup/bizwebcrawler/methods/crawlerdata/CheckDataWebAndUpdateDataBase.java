package com.higgsup.bizwebcrawler.methods.crawlerdata;

import com.higgsup.bizwebcrawler.methods.authentication.AuthenticationGetRequest;
import com.higgsup.bizwebcrawler.methods.common.CommonUtil;
import com.higgsup.bizwebcrawler.methods.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.object.objectcustomer.ObjectCustomerAddress;
import com.higgsup.bizwebcrawler.object.objectcustomer.ObjectCustomers;
import com.higgsup.bizwebcrawler.object.objectproduct.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDataWebAndUpdateDataBase {
    private static final Logger logger = Logger.getLogger("CheckDataWebAndUpdateDataBase");
    private AuthenticationGetRequest AuthenticationGetRequest = new AuthenticationGetRequest();

    public boolean updateDataProductFromWebAndUpdateToDataBase(String get, String cookie) {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                return false;
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
                getHTML = Jsoup.parse(AuthenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/products?page=" + ii, cookie));
                Elements getDataFromTrTags = getHTML.select("tbody tr");
                for (Element tags : getDataFromTrTags) {
                    String[] fullDataFromTags = new String[15];
                    HashMap getDataCategoryProduct = new HashMap();//lấy danh mục mới, hot, sale
                    Elements getDataFromAhrefTags = tags.select("td  a[href]");
                    fullDataFromTags[0] = commonUtil.cutID(getDataFromAhrefTags.get(0).attr("href"));

                    if (!(queryDataBase.hasProductID(fullDataFromTags[0]))) {
                        ArrayList<Product> dataProducerFromProductID = null;
                        dataProducerFromProductID = queryDataBase.getDataProductFromProductID(fullDataFromTags[0]);
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
                        getHTML = Jsoup.parse(AuthenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/products/" + fullDataFromTags[0], cookie));
                        titleURL = getHTML.title();
                        if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                            return false;
                        }
                        Elements getDataFromDivRowTag = getHTML.select("div.row");
                        getDataFromTrTags = getDataFromDivRowTag.get(0).select("div.controls textarea");
                        fullDataFromTags[6] = getDataFromTrTags.get(0).text();
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
                            System.out.println("ID Sản Phẩm :" + fullDataFromTags[0]);
                            System.out.println("Tên sản phẩm: " + fullDataFromTags[1]);
                            System.out.println("getIMGProduct sản phẩm :" + fullDataFromTags[2]);
                            System.out.println("Số Lượng: " + fullDataFromTags[3]);
                            System.out.println("Thể Loại: " + fullDataFromTags[4]);
                            System.out.println("Tác Giả " + fullDataFromTags[5]);
                            System.out.println("Mô Tả: " + fullDataFromTags[6]);
                            System.out.println("số 7: " + fullDataFromTags[7]);

                        } else {
                            getDataFromTrTags = getDataFromDivRowTag.get(7).select("div.controls input[value]");
                            fullDataFromTags[7] = getDataFromTrTags.get(0).attr("value");
                            if (fullDataFromTags[7] == null) {
                                fullDataFromTags[7] = "0";
                            }
                        }
                        if (!dataProducerFromProductID.get(0).getName().equals(fullDataFromTags[1]) && String.valueOf(dataProducerFromProductID.get(0).getPrice()).equals(String.valueOf(Double.parseDouble(fullDataFromTags[7]))) && String.valueOf(dataProducerFromProductID.get(0).getStork()).equals(fullDataFromTags[3]) && dataProducerFromProductID.get(0).getContent().equals(fullDataFromTags[6]) && dataProducerFromProductID.get(0).getIMG().equals(fullDataFromTags[2]) && String.valueOf(dataProducerFromProductID.get(0).getProductGroup_iD()).equals(String.valueOf(queryDataBase.getIDProductGroup(fullDataFromTags[4]))) && String.valueOf(dataProducerFromProductID.get(0).getProducer_ID()).equals(String.valueOf(queryDataBase.getIDProducer(fullDataFromTags[5])))) {
                            queryDataBase.updateProduct(fullDataFromTags[0], fullDataFromTags[1], Double.parseDouble(fullDataFromTags[7]), Integer.parseInt(fullDataFromTags[3]), 0, fullDataFromTags[6], fullDataFromTags[2], "", queryDataBase.getIDProductGroup(fullDataFromTags[4]), queryDataBase.getIDProducer(fullDataFromTags[5]));
                        }
                        ArrayList<String> listProductCateID = null;
                        listProductCateID = queryDataBase.getListProductCateIdFormProductIdInCategoryProduct(fullDataFromTags[0]);
                        Set set = getDataCategoryProduct.entrySet();
                        Iterator i = set.iterator();
                        while (i.hasNext()) {
                            Map.Entry mapEntry = (Map.Entry) i.next();
                            queryDataBase.setDataProductCategory((String) mapEntry.getKey(), (String) mapEntry.getValue());

                            int indexList = listProductCateID.indexOf((String) mapEntry.getKey());
                            if (indexList >= 0) {
                                listProductCateID.remove(indexList);

                            } else {
                                queryDataBase.setDataFromCategoryProductAndProduct((String) mapEntry.getKey(), fullDataFromTags[0]);

                            }
                        }
                        for (String productCate_ID : listProductCateID
                                ) {
                            System.out.println("xoa");
                            queryDataBase.remoDataFromCategoryProductAndProduct(productCate_ID, fullDataFromTags[0]);
                        }
                        dataProducerFromProductID.clear();
                        TimeUnit.SECONDS.sleep(159);
                    } else {
                        System.out.println("chưa có");
                    }

                }
            }
        } catch (Exception e) {

            logger.log(Level.ALL, e.getMessage());
            return false;
        }

        return true;
    }

    public boolean updateDataCustomerFromWebSetToDataBase(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                return false;
            }
            Elements getDataAllCustomer = getHTML.select("div div[class*=t-status-text dataTables_info]");//lấy tất cả links
            int allCustomers = Integer.parseInt(commonUtil.cutID(getDataAllCustomer.text()));
            if (allCustomers > 50) {
                if (allCustomers % 50 == 0) {
                    System.out.println(allCustomers / 50);
                    allCustomers = allCustomers / 50;
                } else {
                    allCustomers = allCustomers / 50 + 1;
                }
            } else {
                allCustomers = 1;
            }
            for (int ii = 1; ii <= allCustomers; ii++) {
                getHTML = Jsoup.parse(AuthenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/customers?page=" + ii, cookie));
                Elements getDataFromTRTags = getHTML.select("tbody tr");
                for (Element tags : getDataFromTRTags) {
                    String[] fullDataFromTags = new String[15];
                    Elements getDataFromAhrefTags = tags.select("td  a[href]");
                    fullDataFromTags[0] = commonUtil.cutID(getDataFromAhrefTags.get(0).attr("id"));//id
                    if (!queryDataBase.hasCustomerID(fullDataFromTags[0])) {
                        System.out.println(fullDataFromTags[0]);
                        fullDataFromTags[1] = getDataFromAhrefTags.get(0).text();
                        fullDataFromTags[2] = getDataFromAhrefTags.get(1).text();
                        fullDataFromTags[3] = getDataFromAhrefTags.get(2).text();
                        if (getDataFromAhrefTags.size() > 3) {
                            fullDataFromTags[4] = commonUtil.cutID(getDataFromAhrefTags.get(3).attr("href"));
                        } else {
                            fullDataFromTags[4] = "null";
                        }

                        System.out.println("ID customer:" + fullDataFromTags[0]);
                        ArrayList<String> listCustomerDddIdFormCustomerId = queryDataBase.getListCustomerDddIdFormCustomerId(fullDataFromTags[0]);
                        ArrayList<ObjectCustomerAddress> listAddressFormCustomerId = queryDataBase.getListAddressFormCustomerId(fullDataFromTags[0]);
                        System.out.println("Tên khách hàng:" + fullDataFromTags[1]);
                        System.out.println("Email:" + fullDataFromTags[3]);
                        System.out.println("Đơn hàng gần nhất: " + fullDataFromTags[4]);
                        Elements getDataFromTDTags = tags.select("td");
                        fullDataFromTags[5] = getDataFromTDTags.get(3).text();
                        fullDataFromTags[6] = getDataFromTDTags.get(5).text();
                        System.out.println("Tổng đơn hàng: " + fullDataFromTags[5]);
                        String Str = "";
                        Pattern p = Pattern.compile("[\\d]+.[\\d]+,[\\d]+");
                        Matcher m = p.matcher(fullDataFromTags[6]);
                        String potFullDataFromTags6 = "";
                        while (m.find()) {
                            potFullDataFromTags6 += m.group();
                        }
                        fullDataFromTags[6] = potFullDataFromTags6.replaceAll(",", "");
                        if (fullDataFromTags[6].length() <= 0) {
                            fullDataFromTags[6] = "0";
                        }
                        System.out.println("Tổng chi tiêu: " + fullDataFromTags[6]);
                        queryDataBase.setDataFromCustomer(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[3], fullDataFromTags[6]);
                        ObjectCustomers objectCustomers = new ObjectCustomers(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[3], Double.parseDouble(fullDataFromTags[6]));
                        ObjectCustomers dataCustomersFromCustomerID = queryDataBase.getDataCustomersFromCustomerID(fullDataFromTags[0]);
                        if (!objectCustomers.equals(dataCustomersFromCustomerID)) {
                            queryDataBase.updateDataCustomersFromObjectCustomer(objectCustomers);
                        }
                        getHTML = Jsoup.parse(AuthenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/customers/" + fullDataFromTags[0], cookie));
                        getDataFromTRTags = getHTML.select("div script.modal_source#modal-add-layouts[define*={editAddressModal]");
                        for (Element getTags : getDataFromTRTags
                                ) {
                            String[] CutScriptTakeHtml = getTags.toString().split("type=\"text/html\">");
                            p = Pattern.compile("[\\d]+");
                            m = p.matcher(CutScriptTakeHtml[0]);
                            String customerAdd_iD = "";
                            while (m.find()) {
                                customerAdd_iD += m.group();
                            }
                            CutScriptTakeHtml = CutScriptTakeHtml[1].split("</script>");
                            getHTML = Jsoup.parse(CutScriptTakeHtml[0]);
                            Elements getDataInGetDataFromTRTags = getHTML.select("div.row");
                            ArrayList<String> ListCustomerAddress = new ArrayList<String>();
                            for (Element gettag : getDataInGetDataFromTRTags
                                    ) {
                                Elements getDataInGetDataFromTRTagsInput = gettag.select("input[value]");
                                for (int i = 0; i < getDataInGetDataFromTRTagsInput.size(); i++) {
                                    if (!getDataInGetDataFromTRTagsInput.get(i).attr("value").equals("true") || getDataInGetDataFromTRTagsInput.get(i).attr("value").equals("false")) {
                                        String get1 = getDataInGetDataFromTRTagsInput.get(i).attr("value");
                                        if (get1.equals("")) {
                                            ListCustomerAddress.add("null");
                                        } else {
                                            ListCustomerAddress.add(getDataInGetDataFromTRTagsInput.get(i).attr("value"));
                                        }
                                    }
                                }
                                if (ListCustomerAddress.size() == 6 || ListCustomerAddress.size() == 8) {
                                    Elements getDataInGetDataFromTRTagsInputSLe = gettag.select("option[selected*=selected]");
                                    for (Element d : getDataInGetDataFromTRTagsInputSLe
                                            ) {
                                        String[] slit = d.toString().split("\">");
                                        slit = slit[1].split("</option>");
                                        if (ListCustomerAddress.indexOf(slit[0]) < 0) {
                                            ListCustomerAddress.add(slit[0]);
                                        }

                                    }
                                }
                            }
                            //update CustomerAddress
                            ObjectCustomerAddress objectCustomerAddress = new ObjectCustomerAddress(customerAdd_iD, ListCustomerAddress.get(4), ListCustomerAddress.get(0) + "," + ListCustomerAddress.get(1), ListCustomerAddress.get(3), ListCustomerAddress.get(2), ListCustomerAddress.get(5), fullDataFromTags[0], ListCustomerAddress.get(6), ListCustomerAddress.get(7), ListCustomerAddress.get(8));
                            int CheckIndix = listCustomerDddIdFormCustomerId.indexOf(objectCustomerAddress.getCustomerAdd_iD());
                            if (CheckIndix >= 0) {
                                if (listAddressFormCustomerId.get(CheckIndix).equals(objectCustomerAddress)) {
                                    listCustomerDddIdFormCustomerId.remove(CheckIndix);
                                    listAddressFormCustomerId.remove(CheckIndix);
                                } else {
                                    queryDataBase.updateDataCustomerAddress(objectCustomerAddress);
                                    listCustomerDddIdFormCustomerId.remove(CheckIndix);
                                    listAddressFormCustomerId.remove(CheckIndix);
                                }
                            } else {
                                queryDataBase.setDataCustomerAddress(objectCustomerAddress);
                            }
                        }
                        for (int i = 0; i < listCustomerDddIdFormCustomerId.size(); i++) {
                            queryDataBase.deleteDataCustomerAddress(listCustomerDddIdFormCustomerId.get(i));
                        }
                        //end update CustomerAddress
                        TimeUnit.SECONDS.sleep(398);
                    }

                }

            }
        } catch (Exception e) {
            logger.info("Message error get data and set database" + e);
        }
        return true;
    }
}
