package com.higgsup.bizwebcrawler.services.scheduling;

import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import com.higgsup.bizwebcrawler.services.getandupdatedata.GettingProductData;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:24 PM 9/12/2017
 */
@Component
public class QueryingProductInformation {
    @Autowired
    GettingProductData getDataWebAndSetToDataBase;
    @Autowired
    HtmlData authenticationGetRequest;
    @Autowired
    CheckingAuthentication checkingAuthentication;
    private static final Logger logger = Logger.getLogger(QueryingProductInformation.class.getName());
    private static final String url = RequestHeader.urlWebsite+"/products";

    public void startScheduling() {
        try {
            authenticationGetRequest.connectURLAndTakeHTML(url, checkingAuthentication.getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataProductFromWeb(authenticationGetRequest.getHtmlData(),checkingAuthentication.getCookie());
            logger.info(checkErrorRequest + " Product");
        } catch (Error e) {
            String s = e.getLocalizedMessage();
            if (s.equals("Error cookie")) {
                try {
                    System.out.println("lỗi cookie");
                    checkingAuthentication.doRequestTakeCookie();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
