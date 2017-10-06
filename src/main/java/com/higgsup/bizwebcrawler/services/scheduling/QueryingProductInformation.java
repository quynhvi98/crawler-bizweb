package com.higgsup.bizwebcrawler.services.scheduling;

import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;
import com.higgsup.bizwebcrawler.services.authentication.HtmlData;
import com.higgsup.bizwebcrawler.services.getandupdatedata.GettingProductData;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquy 2:24 PM 9/12/2017
 */
@Component
public class QueryingProductInformation extends CheckingAuthentication {
    @Autowired
    GettingProductData getDataWebAndSetToDataBase;
    @Autowired
    HtmlData authenticationGetRequest;
    private static final Logger logger = Logger.getLogger(QueryingProductInformation.class.getName());
    private static final String url = RequestHeader.urlWebsite+"/products";
    @Override
    public String getCookie() {
        return super.getCookie();
    }

    public void startScheduling() {
        try {
            authenticationGetRequest.connectURLAndTakeHTML(url, getCookie());
            boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataProductFromWeb(authenticationGetRequest.getHtmlData(), getCookie());
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
