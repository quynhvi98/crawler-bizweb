package com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getandupdatedata;
import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.controller.common.DividePage;
import com.higgsup.bizwebcrawler.controller.managedatabase.QueryDataBase;
import com.higgsup.bizwebcrawler.model.customer.Customer;
import com.higgsup.bizwebcrawler.model.customer.CustomerAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by viquynh
 */
public class GettingCustomerData {
    private static final Logger logger = Logger.getLogger(GettingCustomerData.class.getName());
    private HtmlData authenticationGetRequest = new HtmlData();
    private String cookie;
    private String html;
    private int page;
    public boolean getDataCustomerFromWebSetToDataBase(String html, String cookie) {
        this.html = html;
        this.cookie = cookie;
        startScheduling();
        return true;
    }
    private void startScheduling() {
        try {
            getPageCustomer();
            for (int i = 1; i <= page; i++) {
                getDataCustomer(i);
            }
        } catch (Exception e) {
            logger.severe("Message error get data set database: " + e);
        }
    }
    private void getPageCustomer() {
        CommonUtil commonUtil = new CommonUtil();
        DividePage dividePage = new DividePage();
        Document getHTML = Jsoup.parse(html);
        dividePage.setCheckDataFromWeb(getHTML);
        Elements getDataAllCustomer = dividePage.getCheckDataFromWeb();
        int allPages = Integer.parseInt(commonUtil.cutID(getDataAllCustomer.text()));
        dividePage.setPage(allPages);
        allPages = dividePage.getPage();
        this.page = allPages;
    }
    private void getDataCustomer(int ii) throws InterruptedException {
        Document getHTML;
        CommonUtil commonUtil = new CommonUtil();
        authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers?page=" + ii, cookie);
        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
        if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
            throw new Error("Error cookie");
        }
        Elements getDataFromTRTags = getHTML.select("tbody tr");
        for (Element tags : getDataFromTRTags) {
            Customer customer = new Customer();
            Elements getDataFromAhrefTags = tags.select("td  a[href]");
            customer.setId(commonUtil.cutID(getDataFromAhrefTags.get(0).attr("id")));
            customer.setFullName(getDataFromAhrefTags.get(0).text());
            customer.setEmail(getDataFromAhrefTags.get(2).text());
            Elements getDataFromTDTags = tags.select("td");
            Pattern pattern = Pattern.compile("[\\d]+.[\\d]+,[\\d]+");
            Matcher matcher = pattern.matcher(getDataFromTDTags.get(5).text());
            String stringTotalBill = "";
            while (matcher.find()) {
                stringTotalBill += matcher.group();
            }
            stringTotalBill = stringTotalBill.replaceAll(",", "");
            if (stringTotalBill.length() <= 0) {
                stringTotalBill = "0";
            }
            customer.setTotalBill(Double.parseDouble(stringTotalBill));
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers/" + customer.getId(), cookie);
            getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
            if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                throw new Error("Error cookie");
            }
            Elements getTagsHasAddress = getHTML.select("div script.modal_source#modal-add-layouts[define*={editAddressModal]");
            for (Element getTagsAddress : getTagsHasAddress
                    ) {
                getDataCustomerAddress(getTagsAddress, customer);
            }
            TimeUnit.SECONDS.sleep(11);
        }
    }
    private void getDataCustomerAddress(Element getTagsAddress, Customer customer) {
        Document getHTML;
        DividePage dividePage = new DividePage();
        String[] cutScriptTakeHtml = getTagsAddress.toString().split("type=\"text/html\">");
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(cutScriptTakeHtml[0]);
        String customerAddID = "";
        while (matcher.find()) {
            customerAddID += matcher.group();
        }
        cutScriptTakeHtml = cutScriptTakeHtml[1].split("</script>");
        getHTML = Jsoup.parse(cutScriptTakeHtml[0]);
        Elements getDataInGetDataFromTRTags = getHTML.select("div.row");
        ArrayList<String> listCustomerAddress = new ArrayList<String>();
        for (Element gettag : getDataInGetDataFromTRTags) {
            Elements getDataInGetDataFromTRTagsInput = gettag.select("input[value]");
            for (int i = 0; i < getDataInGetDataFromTRTagsInput.size(); i++) {
                String dataAddress=getDataInGetDataFromTRTagsInput.get(i).attr("value");
                if (!dataAddress.equals("true") || dataAddress.equals("false")) {
                    if (dataAddress.equals("")) {
                        listCustomerAddress.add("null");
                    } else {
                        listCustomerAddress.add(dataAddress);
                    }
                }
            }
            if (listCustomerAddress.size() == 6 || listCustomerAddress.size() == 8) {
                Elements getDataInGetDataFromTRTagsInputSLe = gettag.select("option[selected*=selected]");
                listCustomerAddress = dividePage.getDataFromTRTagsInputSLe(getDataInGetDataFromTRTagsInputSLe, listCustomerAddress);
            }
        }
        listCustomerAddress.add("");
        CustomerAddress customerAddress = new CustomerAddress(customerAddID, listCustomerAddress.get(4), listCustomerAddress.get(0) + "," + listCustomerAddress.get(1), listCustomerAddress.get(3), listCustomerAddress.get(2), listCustomerAddress.get(5), customer.getId(), listCustomerAddress.get(6), listCustomerAddress.get(7), listCustomerAddress.get(8));
        saveAndUpdateCustomerData(customer, customerAddress);
    }
    private void saveAndUpdateCustomerData(Customer customer, CustomerAddress customerAddress) {
        QueryDataBase queryDataBase = new QueryDataBase();
        ArrayList<String> listCustomerDddIdFormCustomerId = queryDataBase.getListCustomerDddIdFormCustomerId(customer.getId());
        ArrayList<CustomerAddress> listAddressFormCustomerId = queryDataBase.getListAddressFormCustomerId(customer.getId());
        if (queryDataBase.hasCustomerID(customer.getId())) {
            queryDataBase.setDataFromCustomer(customer);
            if (customerAddress != null) {
                queryDataBase.setDataCustomerAddress(customerAddress);
            }
        } else {
            Customer dataCustomersFromCustomerID = queryDataBase.getDataCustomersFromCustomerID(customer.getId());
            if (!customer.equals(dataCustomersFromCustomerID)) {
                queryDataBase.updateDataCustomersFromObjectCustomer(customer);
            }
            int checkIndex = listCustomerDddIdFormCustomerId.indexOf(customerAddress.getId());
            if (checkIndex >= 0) {
                if (listAddressFormCustomerId.get(checkIndex).equals(customerAddress)) {
                    listCustomerDddIdFormCustomerId.remove(checkIndex);
                    listAddressFormCustomerId.remove(checkIndex);
                } else {
                    queryDataBase.updateDataCustomerAddress(customerAddress);
                    listCustomerDddIdFormCustomerId.remove(checkIndex);
                    listAddressFormCustomerId.remove(checkIndex);
                }
            } else {
                queryDataBase.setDataCustomerAddress(customerAddress);
            }
            for (int i = 0; i < listCustomerDddIdFormCustomerId.size(); i++) {
                queryDataBase.deleteDataCustomerAddress(listCustomerDddIdFormCustomerId.get(i));
            }
        }
    }
}
