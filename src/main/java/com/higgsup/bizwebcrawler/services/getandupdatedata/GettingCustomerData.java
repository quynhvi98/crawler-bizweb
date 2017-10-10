package com.higgsup.bizwebcrawler.services.getandupdatedata;

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.services.CustomerAddressServices;
import com.higgsup.bizwebcrawler.services.CustomerServices;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import com.higgsup.bizwebcrawler.utils.CommonUtil;
import com.higgsup.bizwebcrawler.utils.DividePage;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by viquynh
 */
@Component
public class GettingCustomerData {
    private static final Logger logger = Logger.getLogger(GettingCustomerData.class.getName());
    @Autowired
    private HtmlData authenticationGetRequest;
    @Autowired
    private  DividePage dividePage;
    private String cookie;
    private String html;
    private int page;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    CustomerAddressServices customerAddressServices;
    private List<String> listCustomerAddIdFromCustomerId;
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
            e.printStackTrace();
        }
    }

    private void getPageCustomer() {

        Document getHTML = Jsoup.parse(html);
        dividePage.setDataCheckingFromWeb(getHTML);
        Elements getDataAllCustomer = dividePage.getDataCheckingFromWeb();
        int allPages = Integer.parseInt(CommonUtil.cutID(getDataAllCustomer.text()));
        dividePage.setPage(allPages);
        allPages = dividePage.getPage();
        this.page = allPages;
    }

    private void getDataCustomer(int ii) throws InterruptedException {
        Document getHTML;
        authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/customers?page=" + ii, cookie);
        getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
        if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
            throw new Error("Error cookie");
        }
        Elements getDataFromTRTags = getHTML.select("tbody tr");
        for (Element tags : getDataFromTRTags) {
            Customer customer = new Customer();
            Elements getDataFromAhrefTags = tags.select("td  a[href]");
            customer.setId(CommonUtil.cutID(getDataFromAhrefTags.get(0).attr("id")));
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
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/customers/" + customer.getId(), cookie);
            getHTML = Jsoup.parse(authenticationGetRequest.getHtmlData());
            if (getHTML.title().equals("Đăng nhập quản trị hệ thống")) {
                throw new Error("Error cookie");
            }
            Elements getTagsHasAddress = getHTML.select("div script.modal_source#modal-add-layouts[define*={editAddressModal]");
            listCustomerAddIdFromCustomerId = customerAddressServices.getListCustomerDddIdFormCustomerId(customer.getId());//

            for (Element getTagsAddress : getTagsHasAddress
                    ) {
                getDataCustomerAddress(getTagsAddress, customer);
            }
            TimeUnit.SECONDS.sleep(2);
            for (int i = 0; i < listCustomerAddIdFromCustomerId.size(); i++) {
                customerAddressServices.delete(listCustomerAddIdFromCustomerId.get(i));
            }
        }
    }

    private void getDataCustomerAddress(Element getTagsAddress, Customer customer) {
        Document getHTML;
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
        ArrayList<String> listCustomerAddress = new ArrayList<>();
        for (Element gettag : getDataInGetDataFromTRTags) {
            Elements getDataInGetDataFromTRTagsInput = gettag.select("input[value]");
            for (int i = 0; i < getDataInGetDataFromTRTagsInput.size(); i++) {
                String dataAddress = getDataInGetDataFromTRTagsInput.get(i).attr("value");
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
        CustomerAddress customerAddress = new CustomerAddress(customerAddID, listCustomerAddress.get(4), listCustomerAddress.get(0) + "," + listCustomerAddress.get(1), listCustomerAddress.get(3), listCustomerAddress.get(2), listCustomerAddress.get(5), customer, listCustomerAddress.get(6), listCustomerAddress.get(7), listCustomerAddress.get(8));
        saveAndUpdateCustomerData(customer, customerAddress);
    }

    private void saveAndUpdateCustomerData(Customer customer, CustomerAddress customerAddress) {
        List<CustomerAddress> listAddressFormCustomerId = customerAddressServices.getListAddressFormCustomerId(customer.getId());//
        if (customerServices.hasCustomerID(customer.getId())) {
            customerServices.save(customer);
            customerAddressServices.save(customerAddress);
        } else {
            Customer dataCustomersFromCustomerID = customerServices.getDataCustomersFromCustomerID(customer.getId());
            if (!customer.equals(dataCustomersFromCustomerID)) {
                customerServices.save(customer);
            }
            int checkIndex = listCustomerAddIdFromCustomerId.indexOf(customerAddress.getId());
            if (checkIndex >= 0) {
                if (listAddressFormCustomerId.get(checkIndex).equals(customerAddress)) {
                    listCustomerAddIdFromCustomerId.remove(checkIndex);
                    listAddressFormCustomerId.remove(checkIndex);
                } else {
                    customerAddressServices.save(customerAddress);
                    listCustomerAddIdFromCustomerId.remove(checkIndex);
                    listAddressFormCustomerId.remove(checkIndex);
                }
            } else {
                customerAddressServices.save(customerAddress);
            }

        }
    }
}
