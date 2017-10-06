package com.higgsup.bizwebcrawler.services.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;

import java.io.IOException;
import java.util.logging.Logger;

/*
    By chicanem 10/08/2017
    */
@Component
public class StartScheduling {

    private static final Logger logger = Logger.getLogger(StartScheduling.class.getName());
    @Autowired
    CheckingAuthentication checkingAuthentication;
    @Autowired
    QueryingProductInformation queryingProductInformation;
    @Autowired
    QueryingCustomerInformation queryingCustomerInformation;
    @Autowired
    QueryingOrderInformation queryingOrderInformation;
    public StartScheduling() {

    }

    public void startScheduling() {
        try {
            checkingAuthentication.doRequestTakeCookie();
            queryingProductInformation.startScheduling();
            queryingCustomerInformation.startScheduling();
            queryingOrderInformation.startScheduling();


        } catch (Error e) {
            String s = e.getLocalizedMessage();
            if (s.equals("FalseAccount")) {
                System.out.println("sai tk mk");
            }
            if (s.equals("Not Connect Internet")) {
                System.out.println("mất mạng");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
