package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.HtmlData;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:33 PM 9/12/2017
 */
abstract class UpdatingCustomerData extends StartScheduling implements Runnable {
    private static final Logger logger = Logger.getLogger("Updating Customer Data");
    public void run() {
        try {
            com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingCustomerData checkDataWebAndUpdateDataBase = new com.higgsup.bizwebcrawler.controller.crawlerdatafrombizweb.updatedata.UpdatingCustomerData();
            HtmlData authenticationGetRequest = new HtmlData();
            authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers", getCookie());
            boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataCustomerFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
            if (!(checkUpdateRequest))
                if (!getCookie().equalsIgnoreCase("FalseAccount")) {
                    doRequestTakeCookie();
                }
            System.out.println(checkUpdateRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
