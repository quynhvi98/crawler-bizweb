package com.higgsup.bizwebcrawler.controller.crawlerdata;

import com.higgsup.bizwebcrawler.controller.authentication.AuthenticationGetRequest;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
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

public class GetDataWebAndSetToDataBase {
    private static final Logger logger = Logger.getLogger(GetDataWebAndSetToDataBase.class.getName());
    private AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();

    public boolean getDataProductFromWebAndSetToDataBase(String get, String cookie) throws IOException {
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
                authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products?page=" + ii, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
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
                            return false;
                        }
                        Elements getDataFromDivRowTag = getHTML.select("div.row");
                        System.out.println(getDataFromDivRowTag.size());
                        getDataFromTrTags = getDataFromDivRowTag.get(0).select("div.controls textarea[bind*=content]");
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
                            System.out.println("ID Sản Phẩm :" + fullDataFromTags[0]);
                            System.out.println("Tên sản phẩm: " + fullDataFromTags[1]);
                            System.out.println("getIMGProduct sản phẩm :" + fullDataFromTags[2]);
                            System.out.println("Số Lượng: " + fullDataFromTags[3]);
                            System.out.println("Thể Loại: " + fullDataFromTags[4]);
                            System.out.println("Tác Giả " + fullDataFromTags[5]);
                            System.out.println("Mô Tả: " + fullDataFromTags[6]);
                            System.out.println("số 7: " + fullDataFromTags[7]);
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

    public boolean getDataCustomerFromWebSetToDataBase(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                return false;
            }
            Elements getDataAllCustomer = getHTML.select("div div[class*=t-status-text dataTables_info]");//lấy tất cả links
           if(getDataAllCustomer.text().equals("")){
               return true;
           }
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
                    if (queryDataBase.hasCustomerID(fullDataFromTags[0])) {
                        fullDataFromTags[1] = getDataFromAhrefTags.get(0).text();
                        fullDataFromTags[2] = getDataFromAhrefTags.get(1).text();
                        fullDataFromTags[3] = getDataFromAhrefTags.get(2).text();
                        if (getDataFromAhrefTags.size() > 3) {
                            fullDataFromTags[4] = commonUtil.cutID(getDataFromAhrefTags.get(3).attr("href"));
                        } else {
                            fullDataFromTags[4] = "null";
                        }

                        System.out.println("ID customer:" + fullDataFromTags[0]);
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
                                        String[] splitTakeValue = d.toString().split("\">");
                                        splitTakeValue = splitTakeValue[1].split("</option>");
                                        if (ListCustomerAddress.indexOf(splitTakeValue[0]) < 0) {
                                            ListCustomerAddress.add(splitTakeValue[0]);
                                        }

                                    }
                                }


                            }
                            ListCustomerAddress.add(" ");
                            //CustomerAddress objectCustomerAddress = new CustomerAddress(customerAdd_iD, ListCustomerAddress.get(4), ListCustomerAddress.get(0) + "," + ListCustomerAddress.get(1), ListCustomerAddress.get(3), ListCustomerAddress.get(2), ListCustomerAddress.get(5), fullDataFromTags[0], ListCustomerAddress.get(6), ListCustomerAddress.get(7), ListCustomerAddress.get(8));

                            //queryDataBase.setDataCustomerAddress(objectCustomerAddress);
                        }
                        TimeUnit.SECONDS.sleep(11);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Message error get data set database" + e);
        }
        return true;
    }

    public boolean getDataOrderFromWebSetToDataBase(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                return false;
            }
            Elements getDataAllCustomer = getHTML.select("div div[class*=t-status-text dataTables_info]");//lấy tất cả links
            if(getDataAllCustomer.text().equals("")){
                return true;
            }
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
                // start
                authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders?page=" + ii, cookie);
                getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
                Elements getDataFromTRTags = getHTML.select("tbody tr[id*=parent-quick-view-]");
                for (Element tags : getDataFromTRTags) {
                    String[] fullDataFromTags = new String[15];
                    //
                    Elements getDataFromAhrefTags = tags.select("td");
                    Elements getIdOrder = getDataFromAhrefTags.select("input[value]");
                    fullDataFromTags[0] = getIdOrder.get(0).attr("value");//ID Order
                    fullDataFromTags[1] = getDataFromAhrefTags.get(2).text();//Thời Gian Order
                    Elements getIdCa = getDataFromAhrefTags.select("td a[href]");
                    fullDataFromTags[2] = commonUtil.cutID(getIdCa.get(2).attr("href"));//ID Người Mua
                    fullDataFromTags[3] = getDataFromAhrefTags.get(4).text();//Thanh toán
                    fullDataFromTags[4] = getDataFromAhrefTags.get(5).text();//Giao hàng
                    fullDataFromTags[5] = getDataFromAhrefTags.get(6).text();//
                    fullDataFromTags[5] = commonUtil.takeMoneyInString(fullDataFromTags[5]);//Tổng tiền
                    //
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders/" + fullDataFromTags[0], cookie);
                    getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());//vào bên trong orders
                    Elements getListProductsOfOrder = getHTML.select("tbody");
                    Elements getAllTagTrInTagTbody0 = getListProductsOfOrder.get(0).select("tr");
                    String[] fullDataFromTagsAddress = new String[15];
                    Properties ListProductOfOrder = new Properties();
                    Set states;
                    String idProduct;
                    for (Element element : getAllTagTrInTagTbody0
                            ) {
                        Elements listElementsGetProductID = element.select("td");
                        Elements listElementsHref = listElementsGetProductID.get(1).select("a[href]");
                        if (listElementsHref.size() > 0) {
                            ListProductOfOrder.put(commonUtil.cutNumberToCharStop(listElementsHref.get(0).attr("href")), listElementsGetProductID.get(4).text());
                        }
                    }
                    Elements  getAllTagTrInTagTbody1 = getListProductsOfOrder.get(1).select("tr");
                    getAllTagTrInTagTbody1 = getAllTagTrInTagTbody1.get(1).select("td");
                    if (getAllTagTrInTagTbody1.size() > 1) {
                        Elements elements1 = getAllTagTrInTagTbody1.get(0).select("div");
                        if (elements1.size() > 1 && elements1.get(0).text().equals("Vận chuyển")) {
                            fullDataFromTags[6] = commonUtil.takeMoneyInString(getAllTagTrInTagTbody1.get(1).text());//tiền vận chuyển
                        } else {
                            fullDataFromTags[6] = "0";
                        }
                    } else {
                        fullDataFromTags[6] = "0";
                    }
                    Elements getthanhtoan = getHTML.select("div[class*=panel panel-default panel-full] div[class*=panel-body] div.row");
                    if (getthanhtoan.get(getthanhtoan.size() - 1).select("p").text().equals("Giống địa chỉ giao hàng")) {
                        for (int i = 0; i < fullDataFromTagsAddress.length; i++) {
                            fullDataFromTagsAddress[i] = "";
                        }
                    } else {

                        fullDataFromTagsAddress[0] = getthanhtoan.get(getthanhtoan.size() - 3).select("p").text();//tên
                        fullDataFromTagsAddress[1] = getthanhtoan.get(getthanhtoan.size() - 2).select("p").text();
                        getthanhtoan = getthanhtoan.get(getthanhtoan.size() - 1).select("p");
                        ArrayList<String> lisNamePhoneDistrictCityNotionBillingaddress = new ArrayList<String>();
                        for (Element element : getthanhtoan
                                ) {
                            lisNamePhoneDistrictCityNotionBillingaddress.add(element.text());
                        }
                        for (int i = 0; i < lisNamePhoneDistrictCityNotionBillingaddress.size(); i++) {
                            switch (i) {
                                case 0:
                                    fullDataFromTagsAddress[2] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//quốc gia
                                    break;
                                case 1:
                                    fullDataFromTagsAddress[3] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//thành phố
                                    break;
                                case 2:
                                    fullDataFromTagsAddress[4] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//quận huyện
                                    break;
                            }
                        }
                    }
                    String namePayOrder = "";
                    getDataFromTRTags = getHTML.select("div[class*=next-card__section  hide-when-printing]#order-payment-callout");
                    getDataFromTRTags = getDataFromTRTags.select("h2[class]");
                    if (getDataFromTRTags.size() > 0) {
                        namePayOrder = getDataFromTRTags.get(0).text();
                        queryDataBase.setDataPaymenFromOrder(namePayOrder);
                    } else {
                        queryDataBase.setDataPaymenFromOrder(namePayOrder);
                    }


                    getDataFromTRTags = getHTML.select("div[class*=panel panel-default panel-full] div");
                    String emailDonHang = getDataFromTRTags.get(5).text();
                    System.out.println(emailDonHang);
                    getDataFromTRTags = getHTML.select("script.modal_source#modal-edit-shipping-address[define*={editShippingAddressModal]");
                    for (Element getTags : getDataFromTRTags
                            ) {
                        ArrayList<String> listSaveShippingAddress = new ArrayList<String>();
                        String[] cutscript = getTags.toString().split("type=\"text/html\">");
                        cutscript = cutscript[1].split("</script>");
                        getHTML = Jsoup.parse(cutscript[0]);
                        getDataFromAhrefTags = getHTML.select("div.modal-body div.row");
                        getIdCa = getDataFromAhrefTags.select("input[value]");
                        listSaveShippingAddress.add(getIdCa.get(0).attr("value"));
                        //
                        listSaveShippingAddress.add(getIdCa.get(1).attr("value"));
                        //
                        listSaveShippingAddress.add(getIdCa.get(2).attr("value"));
                        //
                        listSaveShippingAddress.add(getIdCa.get(3).attr("value"));
                        //
                        Elements getDataInGetDataFromTRTagsInputSLe = getDataFromAhrefTags.select("option[selected*=selected]");
                        for (Element d : getDataInGetDataFromTRTagsInputSLe
                                ) {
                            String[] splitTakeValue = d.toString().split("\">");
                            splitTakeValue = splitTakeValue[1].split("</option>");
                            if (listSaveShippingAddress.indexOf(splitTakeValue[0]) < 0) {
                                listSaveShippingAddress.add(splitTakeValue[0]);
                            }
                        }
                        System.out.println("địa chỉ thanh toán");
                        String billingAddress = "";
                        for (int i = 0; i < 5; i++) {
                            if(!billingAddress.equalsIgnoreCase("Giống địa chỉ giao hàng;")){
                                billingAddress += fullDataFromTagsAddress[i] + ";";

                            }else {
                                billingAddress="";
                                i=5;
                            }
                        }
                        System.out.println(" \nđịa chỉ giao hàng\n");
                        for (String a : listSaveShippingAddress
                                ) {
                            System.out.println(a);
                        }
                        //
                        //set database
                        Order objectOrder = new Order(fullDataFromTags[0], fullDataFromTags[1], fullDataFromTags[3], fullDataFromTags[4], Double.parseDouble(fullDataFromTags[5]), Double.parseDouble("123"), Double.parseDouble(fullDataFromTags[6]), fullDataFromTags[2], queryDataBase.getIDPaymentFromContent(namePayOrder));
                        queryDataBase.setDataFromOrder(objectOrder);
                        ArrayList<OrderProduct> objectOrderProducts = new ArrayList<OrderProduct>();
                        states = ListProductOfOrder.keySet();
                        Iterator itr = states.iterator();
                        while (itr.hasNext()) {
                            idProduct = (String) itr.next();
                            objectOrderProducts.add(new OrderProduct(Double.parseDouble(ListProductOfOrder.getProperty(idProduct)), idProduct, fullDataFromTags[0]));
                        }
                        for (OrderProduct objectOrderProduct : objectOrderProducts
                                ) {
                            queryDataBase.setDataFromOrderAndProduct(objectOrderProduct);
                        }
                        OrderAddress objectOrderAddress = new OrderAddress(0,emailDonHang, listSaveShippingAddress.get(0), listSaveShippingAddress.get(1), listSaveShippingAddress.get(2), listSaveShippingAddress.get(3), listSaveShippingAddress.get(4), listSaveShippingAddress.get(5), listSaveShippingAddress.get(6), billingAddress, fullDataFromTags[0]);
                        queryDataBase.setDataFromOrderAddress(objectOrderAddress);
                        TimeUnit.SECONDS.sleep(5);
                    }
                }
            }
            return true;
        } catch (Exception e) {

              logger.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }
}
