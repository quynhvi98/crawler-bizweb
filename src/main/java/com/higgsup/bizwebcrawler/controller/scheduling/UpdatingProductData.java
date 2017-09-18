package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:31 PM 9/12/2017
 */
abstract class UpdatingProductData extends StartScheduling implements Runnable {
    private static final Logger logger = Logger.getLogger("Updating Product Data");
    public void run() {
        try {
            com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingProductData checkDataWebAndUpdateDataBase = new com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingProductData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products", getCookie());
            boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataProductFromWebAndUpdateToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            logger.info(checkUpdateRequest + " UpdatingProductData");

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
