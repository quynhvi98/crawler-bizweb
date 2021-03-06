package com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getandupdatedata;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.authentication.RequestHeader;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.common.DividePage;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
public class GettingOrderData {
    private static final Logger logger = Logger.getLogger(GettingOrderData.class.getName());
    private HtmlData authenticationGetRequest = new HtmlData();
    private Order order;
    private OrderAddress objectOrderAddress;
    private QueryDataBase queryDataBase = new QueryDataBase();
    private Set states;
    private OrderProduct orderProduct;
    private Document getHTML;
    private Properties ListProductOfOrder;
    private String cookie;
    private String html;
    private int pages;

    public boolean getDataOrderFromWebSetToDataBase(String html, String cookie) throws IOException {
        this.cookie = cookie;
        this.html = html;
        startScheduling();
        return true;
    }

    private void startScheduling() {
        try {
            getPageOrder();
            for (int page = 1; page <= pages; page++) {
                getDataOrder(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void getPageOrder() {
        DividePage dividePage = new DividePage();
        getHTML = Jsoup.parse(html);
        dividePage.setDataCheckingFromWeb(getHTML);
        Elements getDataAllOrders = dividePage.getDataCheckingFromWeb();
        int allOrder = Integer.parseInt(CommonUtil.cutID(getDataAllOrders.text()));
        dividePage.setPage(allOrder);
        this.pages = dividePage.getPage();
    }

    private void getDataOrder(int page) throws InterruptedException, ParseException {
        order = new Order();
        authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite+"/orders?page=" + page, cookie);
        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
        if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
            throw new Error("Error cookie");
        }
        Elements getDataFromTRTags = getHTML.select("tbody tr[id*=parent-quick-view-]");
        for (Element tags : getDataFromTRTags) {
            Elements getDataFromAhrefTags = tags.select("td");
            Elements getIdOrder = getDataFromAhrefTags.select("input[value]");
            order.setOrderID(getIdOrder.get(0).attr("value"));
            order.setDate(CommonUtil.fomatDateSQL(getDataFromAhrefTags.get(2).text()));//Thời Gian Order
            Elements getIdCa = getDataFromAhrefTags.select("td a[href]");
            order.setCustomerID(CommonUtil.cutID(getIdCa.get(2).attr("href")));
            order.setStatusPaymen(getDataFromAhrefTags.get(4).text());
            order.setStatusDelivery(getDataFromAhrefTags.get(5).text());
            String totalBill = CommonUtil.takeMoneyInString(getDataFromAhrefTags.get(6).text());//Tổng tiền
            order.setTotalBill(Double.parseDouble(totalBill));
            order.setTotalWeight(0.0);
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite+"/orders/" + order.getOrderID(), cookie);
            getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());//vào bên trong orders
            if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                throw new Error("Error cookie");
            }
            Elements getListProductsOfOrder = getHTML.select("tbody");
            Elements getAllTagTrInTagTbody0 = getListProductsOfOrder.get(0).select("tr");
            ListProductOfOrder = new Properties();
            for (Element element : getAllTagTrInTagTbody0
                    ) {
                Elements listElementsGetProductID = element.select("td");
                Elements listElementsHref = listElementsGetProductID.get(1).select("a[href]");
                if (listElementsHref.size() > 0) {
                    ListProductOfOrder.put(CommonUtil.cutNumberToCharStop(listElementsHref.get(0).attr("href")), listElementsGetProductID.get(4).text());
                }
            }
            Elements getAllTagTrInTagTbody1 = getListProductsOfOrder.get(1).select("tr");
            getAllTagTrInTagTbody1 = getAllTagTrInTagTbody1.get(1).select("td");
            String feeDelivery = "";
            if (getAllTagTrInTagTbody1.size() > 1) {
                Elements elements1 = getAllTagTrInTagTbody1.get(0).select("div");
                if (elements1.size() > 1 && elements1.get(0).text().equals("Vận chuyển")) {
                    feeDelivery = CommonUtil.takeMoneyInString(getAllTagTrInTagTbody1.get(1).text());//tiền vận chuyển
                } else {
                    feeDelivery = "0";
                }
            } else {
                feeDelivery = "0";
            }
            order.setFeeDelivery(Double.parseDouble(feeDelivery));
            String[] fullDataFromTagsAddress = new String[6];
            Elements getthanhtoan = getHTML.select("div[class*=panel panel-default panel-full] div[class*=panel-body] div.row");
            if (getthanhtoan.get(getthanhtoan.size() - 1).select("p").text().equals("Giống địa chỉ giao hàng")) {
                for (int i = 0; i < fullDataFromTagsAddress.length; i++) {
                    fullDataFromTagsAddress[i] = "";
                }
            } else {
                fullDataFromTagsAddress[0] = getthanhtoan.get(getthanhtoan.size() - 3).select("p").text();//tên
                fullDataFromTagsAddress[1] = getthanhtoan.get(getthanhtoan.size() - 2).select("p").text();
                getthanhtoan = getthanhtoan.get(getthanhtoan.size() - 6).select("p");
                ArrayList<String> lisNamePhoneDistrictCityNotionBillingaddress = new ArrayList<String>();
                for (Element element : getthanhtoan
                        ) {
                    lisNamePhoneDistrictCityNotionBillingaddress.add(element.text());
                }
                for (int i = 0; i < lisNamePhoneDistrictCityNotionBillingaddress.size(); i++) {
                    switch (i) {
                        case 0:
                            fullDataFromTagsAddress[2] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//thành phố
                            break;
                        case 1:
                            fullDataFromTagsAddress[3] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//quận huyện
                            break;
                        case 2:
                            fullDataFromTagsAddress[4] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);//
                            break;
                        case 3:
                            fullDataFromTagsAddress[5] = lisNamePhoneDistrictCityNotionBillingaddress.get(i);// quốc gia
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
            getDataFromTRTags = getHTML.select("script.modal_source#modal-edit-shipping-address[define*={editShippingAddressModal]");
            getShippingAddress(emailDonHang, getDataFromTRTags, getDataFromAhrefTags, fullDataFromTagsAddress, namePayOrder);
            saveAndUpdateOrderData();
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private void getShippingAddress(String emailDonHang, Elements getDataFromTRTags, Elements getDataFromAhrefTags, String[] fullDataFromTagsAddress, String namePayOrder) {
        DividePage dividePage = new DividePage();
        for (Element getTags : getDataFromTRTags
                ) {
            ArrayList<String> listSaveShippingAddress = new ArrayList<String>();
            String[] cutscript = getTags.toString().split("type=\"text/html\">");
            cutscript = cutscript[1].split("</script>");
            getHTML = Jsoup.parse(cutscript[0]);
            getDataFromAhrefTags = getHTML.select("div.modal-body div.row");
            Elements getIdCa = getDataFromAhrefTags.select("input[value]");
            listSaveShippingAddress.add(getIdCa.get(0).attr("value"));
            listSaveShippingAddress.add(getIdCa.get(1).attr("value"));
            listSaveShippingAddress.add(getIdCa.get(2).attr("value"));
            listSaveShippingAddress.add(getIdCa.get(3).attr("value"));
            Elements getDataInGetDataFromTRTagsInputSLe = getDataFromAhrefTags.select("option[selected*=selected]");
            listSaveShippingAddress = dividePage.getDataFromTRTagsInputSLe(getDataInGetDataFromTRTagsInputSLe, listSaveShippingAddress);
            String billingAddress = "";
            for (int i = 0; i < 6; i++) {
                if (!billingAddress.equalsIgnoreCase("Giống địa chỉ giao hàng;")) {
                    billingAddress += fullDataFromTagsAddress[i] + ";";
                } else {
                    billingAddress = "";
                    i = 5;
                }
            }
            order.setPaymentID(queryDataBase.getIDPaymentFromContent(namePayOrder));
            objectOrderAddress = new OrderAddress(emailDonHang, listSaveShippingAddress.get(0), listSaveShippingAddress.get(1), listSaveShippingAddress.get(2), listSaveShippingAddress.get(3), listSaveShippingAddress.get(4), listSaveShippingAddress.get(5), listSaveShippingAddress.get(6), billingAddress, order.getOrderID());
            states = ListProductOfOrder.keySet();
            Iterator itr = states.iterator();
            while (itr.hasNext()) {
                String idProduct = (String) itr.next();
                orderProduct = new OrderProduct(Double.parseDouble(ListProductOfOrder.getProperty(idProduct)), idProduct, order.getOrderID());
            }
        }
    }

    private void saveAndUpdateOrderData() {
        if (queryDataBase.hasOrderID(order.getOrderID())) {
            queryDataBase.setDataFromOrder(order);
            queryDataBase.setDataFromOrderAndProduct(orderProduct);
            queryDataBase.setDataFromOrderAddress(objectOrderAddress);
        } else {
            ArrayList<Order> listDataOrders = queryDataBase.getListDataOrders();
            ArrayList<OrderAddress> listOrderAddress = queryDataBase.getListDataOrderAddress();
            int indexorder = listDataOrders.indexOf(order);
            if (listDataOrders.get(indexorder).getDate().equals(order.getDate()) && listDataOrders.get(indexorder).getStatusPaymen().equals(order.getStatusPaymen()) && listDataOrders.get(indexorder).getStatusDelivery().equals(order.getStatusDelivery()) && listDataOrders.get(indexorder).getTotalBill().equals(order.getTotalBill()) && listDataOrders.get(indexorder).getTotalWeight().equals(order.getTotalWeight()) && listDataOrders.get(indexorder).getFeeDelivery().equals(order.getFeeDelivery()) && listDataOrders.get(indexorder).getCustomerID().equals(order.getCustomerID()) && listDataOrders.get(indexorder).getPaymentID() == order.getPaymentID()) {
            } else {
                queryDataBase.updateDataFromOrder(order);
            }
            queryDataBase.setDataFromOrderAndProduct(orderProduct);
            ArrayList<OrderProduct> listOrderProduct = queryDataBase.getListDataOrderProduct(order.getOrderID());
            if (listOrderProduct.indexOf(orderProduct) < 0) {
                boolean checkquery = queryDataBase.updateDataFromOrderAndProduct(orderProduct);
                if (checkquery == false) {
                    queryDataBase.setDataFromOrderAndProduct(orderProduct);
                }
            }
            int index = listOrderAddress.indexOf(objectOrderAddress);
            if (listOrderAddress.get(index).getEmail().equals(objectOrderAddress.getEmail()) && listOrderAddress.get(index).getNameCustomer().equals(objectOrderAddress.getNameCustomer()) && listOrderAddress.get(index).getPhone().equals(objectOrderAddress.getPhone()) && listOrderAddress.get(index).getOrderAddress().equals(objectOrderAddress.getOrderAddress()) && listOrderAddress.get(index).getZipCode().equals(objectOrderAddress.getZipCode()) && listOrderAddress.get(index).getNation().equals(objectOrderAddress.getNation()) && listOrderAddress.get(index).getCity().equals(objectOrderAddress.getCity()) && listOrderAddress.get(index).getDistrict().equals(objectOrderAddress.getDistrict()) && listOrderAddress.get(index).getPaymentAddress().equals(objectOrderAddress.getPaymentAddress()) && listOrderAddress.get(index).getOrderID().equals(objectOrderAddress.getOrderID())) {
            } else {
                queryDataBase.updateDataFromOrderAddress(objectOrderAddress);
            }
            if (index < 0) {
                queryDataBase.setDataFromOrderAddress(objectOrderAddress);
            }
        }
    }
}
