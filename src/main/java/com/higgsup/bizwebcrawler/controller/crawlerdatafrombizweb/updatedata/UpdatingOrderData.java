package com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * By chicanem   16:58 - 13/09/2017
 */
public class UpdatingOrderData {
    private static final Logger logger = Logger.getLogger(UpdatingOrderData.class.getName());
    private HtmlData authenticationGetRequest = new HtmlData();

    public boolean checkDataOrderWebAndUpdateDataBase(String get, String cookie) throws IOException {
        CommonUtil commonUtil = new CommonUtil();
        try {
            QueryDataBase queryDataBase = new QueryDataBase();
            Document getHTML = Jsoup.parse(get);
            String titleURL = getHTML.title();
            if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
                System.out.println("lỗi đăng nhập hệ th");
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
            ArrayList<Order> listDataOrders=queryDataBase.getListDataOrders();
            ArrayList<OrderAddress> listOrderAddress=queryDataBase.getListDataOrderAddress();
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
                    Elements getAllTagTrInTagTbody1 = getListProductsOfOrder.get(1).select("tr");
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
                        logger.info("địa chỉ thanh toán");
                        String billingAddress = "";
                        for (int i = 0; i < 5; i++) {
                            if (!billingAddress.equalsIgnoreCase("Giống địa chỉ giao hàng;")) {
                                billingAddress += fullDataFromTagsAddress[i] + ";";

                            } else {
                                billingAddress = "";
                                i = 5;
                            }
                        }
                        logger.info(" \nđịa chỉ giao hàng\n");
                        for (String a : listSaveShippingAddress
                                ) {
                            System.out.println(a);
                        }
                        //
                        //set database
                        Order objectOrder = new Order(fullDataFromTags[0], commonUtil.fomatDateSQL(fullDataFromTags[1]), fullDataFromTags[3], fullDataFromTags[4], Double.parseDouble(fullDataFromTags[5]), Double.parseDouble("123"), Double.parseDouble(fullDataFromTags[6]), fullDataFromTags[2], queryDataBase.getIDPaymentFromContent(namePayOrder));
                        int indexorder=listDataOrders.indexOf(objectOrder);
                        if(listDataOrders.get(indexorder).getDate().equals(objectOrder.getDate())&&listDataOrders.get(indexorder).getStatusPaymen().equals(objectOrder.getStatusPaymen())&&listDataOrders.get(indexorder).getStatusDelivery().equals(objectOrder.getStatusDelivery())&&listDataOrders.get(indexorder).getTotalBill().equals(objectOrder.getTotalBill())&&listDataOrders.get(indexorder).getTotalWeight().equals(objectOrder.getTotalWeight())&&listDataOrders.get(indexorder).getFeeDelivery().equals(objectOrder.getFeeDelivery())&&listDataOrders.get(indexorder).getCustomerID().equals(objectOrder.getCustomerID())&&listDataOrders.get(indexorder).getPaymentID()==objectOrder.getPaymentID()){
                        }else {
                            queryDataBase.updateDataFromOrder(objectOrder);
                            System.out.println("okii");
                        }



                       ArrayList<OrderProduct> objectOrderProducts = new ArrayList<OrderProduct>();
                        states = ListProductOfOrder.keySet();
                        Iterator itr = states.iterator();
                        while (itr.hasNext()) {
                            idProduct = (String) itr.next();
                            objectOrderProducts.add(new OrderProduct(Double.parseDouble(ListProductOfOrder.getProperty(idProduct)), idProduct, fullDataFromTags[0]));
                        }
                        ArrayList<OrderProduct> listOrderProduct =queryDataBase.getListDataOrderProduct(fullDataFromTags[0]);
                        for (OrderProduct objectOrderProduct : objectOrderProducts
                                ) {
                            int indexObject=listOrderProduct.indexOf(objectOrderProduct);
                         boolean checkquery=   queryDataBase.updateDataFromOrderAndProduct(objectOrderProduct);
                         if(checkquery==false){
                             queryDataBase.setDataFromOrderAndProduct(objectOrderProduct);
                         }

                        }

                        //

                        OrderAddress objectOrderAddress = new OrderAddress(0, emailDonHang, listSaveShippingAddress.get(0), listSaveShippingAddress.get(1), listSaveShippingAddress.get(2), listSaveShippingAddress.get(3), listSaveShippingAddress.get(4), listSaveShippingAddress.get(5), listSaveShippingAddress.get(6), billingAddress, fullDataFromTags[0]);
                        int index=listOrderAddress.indexOf(objectOrderAddress);
                        System.out.println("vvv "+index+"size "+listOrderAddress.size());
                        if(listOrderAddress.get(index).getEmail().equals(objectOrderAddress.getEmail())&&listOrderAddress.get(index).getNameCustomer().equals(objectOrderAddress.getNameCustomer())&&listOrderAddress.get(index).getPhone().equals(objectOrderAddress.getPhone())&&listOrderAddress.get(index).getOrderAddress().equals(objectOrderAddress.getOrderAddress())&&listOrderAddress.get(index).getZipCode().equals(objectOrderAddress.getZipCode())&&listOrderAddress.get(index).getNation().equals(objectOrderAddress.getNation())&&listOrderAddress.get(index).getCity().equals(objectOrderAddress.getCity())&&listOrderAddress.get(index).getDistrict().equals(objectOrderAddress.getDistrict())&&listOrderAddress.get(index).getPaymentAddress().equals(objectOrderAddress.getPaymentAddress())&&listOrderAddress.get(index).getOrderID().equals(objectOrderAddress.getOrderID())){

                        }else {
                            queryDataBase.updateDataFromOrderAddress(objectOrderAddress);
                        }
                        if(index<0){
                            queryDataBase.setDataFromOrderAddress(objectOrderAddress);
                        }


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
