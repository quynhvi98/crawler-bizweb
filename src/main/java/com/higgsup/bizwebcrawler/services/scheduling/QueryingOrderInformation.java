package com.higgsup.bizwebcrawler.services.scheduling;

import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import com.higgsup.bizwebcrawler.services.getandupdatedata.GettingOrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 3:12 PM 9/12/2017
 */
@Component
public class QueryingOrderInformation extends StartScheduling {
    @Autowired
    GettingOrderData getDataWebAndSetToDataBase;
    @Autowired
    HtmlData authenticationGetRequest;
    @Autowired
    CheckingAuthentication checkingAuthentication;
    private static final Logger logger = Logger.getLogger(QueryingOrderInformation.class.getName());

    public void startScheduling() {
        try {
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/orders", checkingAuthentication.getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), checkingAuthentication.getCookie());
            logger.info(checkErrorRequest + " QueryingOrderInformation");
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
