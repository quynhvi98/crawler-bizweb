package com.higgsup.bizwebcrawler.services.scheduling;
import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import com.higgsup.bizwebcrawler.services.getandupdatedata.GettingCustomerData;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:28 PM 9/12/2017
 */
@Component
public class QueryingCustomerInformation extends CheckingAuthentication {
    @Autowired
    GettingCustomerData getDataWebAndSetToDataBase;
    @Autowired
    HtmlData authenticationGetRequest;
    private static final Logger logger = Logger.getLogger(QueryingCustomerInformation.class.getName());
    public void startScheduling() {
        try {

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
