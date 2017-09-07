package com.higgsup.bizwebcrawler.controller.crawlerdata.checkdatafromwebandupdatedatabase;

import com.higgsup.bizwebcrawler.controller.authentication.AuthenticationGetRequest;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.customer.Customer;
import com.higgsup.bizwebcrawler.model.customer.CustomerAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by viquynh
 */
public class UpdateDataCustomerFromWebSetToDataBase {
    private static final Logger logger = Logger.getLogger(UpdateDataCustomerFromWebSetToDataBase.class.getName());
    private com.higgsup.bizwebcrawler.controller.authentication.AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
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
                authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers?page=" + ii, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
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

                        logger.info("ID customer:" + fullDataFromTags[0]);
                        ArrayList<String> listCustomerDddIdFormCustomerId = queryDataBase.getListCustomerDddIdFormCustomerId(fullDataFromTags[0]);
                        ArrayList<CustomerAddress> listAddressFormCustomerId = queryDataBase.getListAddressFormCustomerId(fullDataFromTags[0]);
                        logger.info("Tên khách hàng:" + fullDataFromTags[1]);
                        logger.info("Email:" + fullDataFromTags[3]);
                        logger.info("Đơn hàng gần nhất: " + fullDataFromTags[4]);
                        Elements getDataFromTDTags = tags.select("td");
                        fullDataFromTags[5] = getDataFromTDTags.get(3).text();
                        fullDataFromTags[6] = getDataFromTDTags.get(5).text();
                        logger.info("Tổng đơn hàng: " + fullDataFromTags[5]);
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
                        logger.info("Tổng chi tiêu: " + fullDataFromTags[6]);
                        queryDataBase.setDataFromCustomer(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[3], fullDataFromTags[6]);
                        //Customer objectCustomers = new Customer(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[3], Double.parseDouble(fullDataFromTags[6]));
                        Customer dataCustomersFromCustomerID = queryDataBase.getDataCustomersFromCustomerID(fullDataFromTags[0]);
                        /*if (!objectCustomers.equals(dataCustomersFromCustomerID)) {
                            queryDataBase.updateDataCustomersFromObjectCustomer(objectCustomers);
                        }*/
                        authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers/" + fullDataFromTags[0], cookie);
                        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
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
                            //CustomerAddress objectCustomerAddress = new CustomerAddress(customerAdd_iD, ListCustomerAddress.get(4), ListCustomerAddress.get(0) + "," + ListCustomerAddress.get(1), ListCustomerAddress.get(3), ListCustomerAddress.get(2), ListCustomerAddress.get(5), fullDataFromTags[0], ListCustomerAddress.get(6), ListCustomerAddress.get(7), ListCustomerAddress.get(8));
                            //int CheckIndix = listCustomerDddIdFormCustomerId.indexOf(objectCustomerAddress.getId());
                           /* if (CheckIndix >= 0) {
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
                            }*/
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
