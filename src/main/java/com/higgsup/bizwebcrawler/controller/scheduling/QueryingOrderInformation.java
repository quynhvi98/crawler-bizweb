package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getdata.GettingOrderData;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 3:12 PM 9/12/2017
 */
abstract class QueryingOrderInformation extends StartScheduling implements Runnable {
    private static final Logger logger = Logger.getLogger(QueryingOrderInformation.class.getName());
    public void run() {
        try {
            GettingOrderData getDataWebAndSetToDataBase = new GettingOrderData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders", getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
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
