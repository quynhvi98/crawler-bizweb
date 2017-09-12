package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getdata.GettingProductData;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:24 PM 9/12/2017
 */
abstract class QueryingProductInformation extends StartScheduling implements Runnable  {
    private static final Logger logger = Logger.getLogger("Querying Product Information");
    public void run() {
        try {
            GettingProductData GetDataWebAndSetToDataBase = new GettingProductData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products", getCookie());
            boolean checkErrorRequest = GetDataWebAndSetToDataBase.getDataProductFromWebAndSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            if (!(checkErrorRequest))
                if (!getCookie().equalsIgnoreCase("FalseAccount")) {
                    doRequestTakeCookie();
                }
            logger.info(checkErrorRequest + " product");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
