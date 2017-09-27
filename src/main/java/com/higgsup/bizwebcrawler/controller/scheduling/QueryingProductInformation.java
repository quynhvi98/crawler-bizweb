package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.authentication.RequestHeader;
import com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getandupdatedata.GettingProductData;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:24 PM 9/12/2017
 */
abstract class QueryingProductInformation extends StartScheduling implements Runnable {

    private static final Logger logger = Logger.getLogger(QueryingProductInformation.class.getName());
    private static final String url = RequestHeader.urlWebsite+"/products";
    @Override
    public String getCookie() {
        return super.getCookie();
    }

    public void run() {
        try {
            GettingProductData GetDataWebAndSetToDataBase = new GettingProductData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML(url, getCookie());

            boolean checkErrorRequest = GetDataWebAndSetToDataBase.getDataProductFromWeb(authenticationGetRequest.getHtmlData(), getCookie());
            logger.info(checkErrorRequest + " Product");
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
