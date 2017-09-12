package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;
import com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.getdata.GettingOrderData;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 3:12 PM 9/12/2017
 */
abstract class QueryingOrderInformation extends StartScheduling implements Runnable {
    private static final Logger logger = Logger.getLogger("Querying Order Information");
    public void run() {
        try {
            GettingOrderData getDataWebAndSetToDataBase = new GettingOrderData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders", getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            if (!(checkErrorRequest))
                if (!getCookie().equalsIgnoreCase("FalseAccount")) {
                    doRequestTakeCookie();
                }
            System.out.println(checkErrorRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
