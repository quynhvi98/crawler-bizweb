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
public class QueryingOrderInformation extends CheckingAuthentication {
    @Autowired
    GettingOrderData getDataWebAndSetToDataBase;
    @Autowired
    HtmlData authenticationGetRequest;

    private static final Logger logger = Logger.getLogger(QueryingOrderInformation.class.getName());

    public void startScheduling() {
        try {
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite + "/orders", getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            logger.info(checkErrorRequest + " QueryingOrderInformation");
        } catch (Error e) {
            String s = e.getLocalizedMessage();
            if (s.equals("Error cookie")) {
                try {
                    System.out.println("lỗi cookie");
                    doRequestTakeCookie();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
