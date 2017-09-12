package com.higgsup.bizwebcrawler.controller;
import com.higgsup.bizwebcrawler.controller.authentication.AuthenticationGetRequest;
import com.higgsup.bizwebcrawler.controller.authentication.AuthenticationPostRequest;
import com.higgsup.bizwebcrawler.controller.crawlerdata.CheckDataWebAndUpdateDataBase;
import com.higgsup.bizwebcrawler.controller.crawlerdata.GetDataWebAndSetToDataBase;
import com.higgsup.bizwebcrawler.controller.managedatabase.ConnectDB;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
    By chicanem 10/08/2017
    */
public class StartScheduling extends AuthenticationPostRequest {
    private static final Logger logger = Logger.getLogger("StartScheduling");
    @Override
    public String getCookie() {
        return super.getCookie();
    }
    @Override
    public String doRequestTakeCookie() throws IOException {
        return super.doRequestTakeCookie();
    }
    public StartScheduling() {
        try {
            doRequestTakeCookie();
        } catch (IOException e) {
            logger.info("Error fo request" + e);
        }
    }

    public void startScheduling() {//bắt đầu chương trình
        final ScheduledExecutorService reLoadTime = Executors.newSingleThreadScheduledExecutor();
        try {
            ConnectDB con = new ConnectDB();
            con.startConnect();
            Runnable queryProduct = new QueryInfoProduct();
            Runnable queryInfoCustomer = new QueryInfoCustomer();
            Runnable queryInfoOrder = new QueryInfoOrder();
            Runnable updateDataProduct = new UpdateDataProduct();
            Runnable updateDatCustomer = new UpdateDatCustomer();
            reLoadTime.scheduleWithFixedDelay(queryProduct, 0, 100, TimeUnit.SECONDS);
            reLoadTime.scheduleWithFixedDelay(queryInfoCustomer, 0, 100, TimeUnit.SECONDS);
            reLoadTime.scheduleWithFixedDelay(queryInfoOrder, 0, 100, TimeUnit.SECONDS);
            reLoadTime.scheduleWithFixedDelay(updateDataProduct, 0, 120, TimeUnit.MINUTES);
            reLoadTime.scheduleWithFixedDelay(updateDatCustomer, 0, 120, TimeUnit.MINUTES);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    private class QueryInfoData implements Runnable{

        public void run(){
            try{
            }catch (Exception e){
                logger.info("Thread do not run!");
            }
        }
    }
    private class QueryInfoProduct implements Runnable {
        public void run() {
                try {
                    GetDataWebAndSetToDataBase GetDataWebAndSetToDataBase = new GetDataWebAndSetToDataBase();
                    AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products", getCookie());
                    boolean checkErrorRequest = GetDataWebAndSetToDataBase.getDataProductFromWebAndSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
                    if (!(checkErrorRequest))
                        if(!getCookie().equalsIgnoreCase("FalseAccount")){
                            doRequestTakeCookie();
                        }
                    logger.info(checkErrorRequest + " product");
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
        }
    }

    private class QueryInfoCustomer implements Runnable {
        public void run() {
                try {
                    GetDataWebAndSetToDataBase getDataWebAndSetToDataBase = new GetDataWebAndSetToDataBase();
                    AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers", getCookie());
                    boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataCustomerFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
                    if (!(checkErrorRequest))
                        if(!getCookie().equalsIgnoreCase("FalseAccount")){
                             doRequestTakeCookie();
                        }
                    System.out.println(checkErrorRequest);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
        }
    }

    private class UpdateDataProduct implements Runnable {
        public void run() {

                try {
                    CheckDataWebAndUpdateDataBase checkDataWebAndUpdateDataBase = new CheckDataWebAndUpdateDataBase();
                    AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/products", getCookie());
                    boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataProductFromWebAndUpdateToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
                    if (!(checkUpdateRequest))
                        if(!getCookie().equalsIgnoreCase("FalseAccount")){
                             doRequestTakeCookie();
                        }
                    logger.info(checkUpdateRequest + " update");
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }

        }
    }

    private class UpdateDatCustomer implements Runnable {
        public void run() {

                try {
                    CheckDataWebAndUpdateDataBase checkDataWebAndUpdateDataBase = new CheckDataWebAndUpdateDataBase();
                    AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/customers", getCookie());
                    boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataCustomerFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
                    if (!(checkUpdateRequest))
                        if(!getCookie().equalsIgnoreCase("FalseAccount")){
                           doRequestTakeCookie();
                        }
                    System.out.println(checkUpdateRequest);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
        }
    }

    private class QueryInfoOrder implements Runnable {
        public void run() {

                try {
                    GetDataWebAndSetToDataBase getDataWebAndSetToDataBase = new GetDataWebAndSetToDataBase();
                    AuthenticationGetRequest authenticationGetRequest = new AuthenticationGetRequest();
                    authenticationGetRequest.connectURLAndTakeHTML("https://bookweb1.bizwebvietnam.net/admin/orders", getCookie());
                    boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.getHtmlData(), getCookie());
                    if (!(checkErrorRequest))
                        if(!getCookie().equalsIgnoreCase("FalseAccount")){
                             doRequestTakeCookie();
                        }
                    System.out.println(checkErrorRequest);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
        }
    }
}
