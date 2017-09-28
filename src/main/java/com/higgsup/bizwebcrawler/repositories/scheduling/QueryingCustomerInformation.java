package com.higgsup.bizwebcrawler.repositories.scheduling;
import com.higgsup.bizwebcrawler.repositories.authentication.RequestHeader;
import com.higgsup.bizwebcrawler.repositories.crawlerdatafrombizweb.getandupdatedata.GettingCustomerData;
import com.higgsup.bizwebcrawler.repositories.authentication.HtmlData;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:28 PM 9/12/2017
 */
abstract class QueryingCustomerInformation extends StartScheduling implements Runnable {
    private static final Logger logger = Logger.getLogger(QueryingCustomerInformation.class.getName());
    public void run() {
        try {
            GettingCustomerData getDataWebAndSetToDataBase = new GettingCustomerData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML(RequestHeader.urlWebsite+"/customers", getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataCustomerFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            logger.info(checkErrorRequest + " QueryingCustomerInformation");
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
