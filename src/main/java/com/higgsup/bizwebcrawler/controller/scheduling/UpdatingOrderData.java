package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * By chicanem   16:55 - 13/09/2017
 */
public class UpdatingOrderData  extends StartScheduling implements Runnable{
    private static final Logger logger = Logger.getLogger("Updating Product Data");
    public void run() {
        try {
            com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingOrderData getDataWebAndSetToDataBase = new com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingOrderData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders", getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.checkDataOrderWebAndUpdateDataBase(authenticationGetRequest.getHtmlData(), getCookie());
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
