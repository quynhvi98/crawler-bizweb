package com.higgsup.bizwebcrawler.methods;

import com.higgsup.bizwebcrawler.methods.authentication.AuthenticationGetRequest;
import com.higgsup.bizwebcrawler.methods.authentication.AuthenticationPostRequest;
import com.higgsup.bizwebcrawler.methods.crawlerdata.CheckDataWebAndUpdateDataBase;
import com.higgsup.bizwebcrawler.methods.crawlerdata.GetDataWebAndSetToDataBase;
import com.higgsup.bizwebcrawler.methods.managedatabase.ConnectDB;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    By chicanem 10/08/2017
    */
public class StartScheduling {
    private static final Logger logger = Logger.getLogger("StartScheduling");
    private String cookie;
    private AuthenticationPostRequest authenticationPostRequest = new AuthenticationPostRequest();
    public StartScheduling() {
        try {
            authenticationPostRequest.doRequest();
            cookie = authenticationPostRequest.getCookie();
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
                    AuthenticationGetRequest AuthenticationGetRequest = new AuthenticationGetRequest();
                    boolean checkErrorRequest = GetDataWebAndSetToDataBase.getDataProductFromWebAndSetToDataBase(AuthenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/products", cookie), cookie);
                    if (!(checkErrorRequest))
                        if(!cookie.equalsIgnoreCase("FalseAccount")){
                            cookie = authenticationPostRequest.doRequest();
                        }
                    System.out.println(checkErrorRequest + " product");
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
                    boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataCustomerFromWebSetToDataBase(authenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/customers", cookie), cookie);
                    if (!(checkErrorRequest))
                        if(!cookie.equalsIgnoreCase("FalseAccount")){
                            cookie = authenticationPostRequest.doRequest();
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
                    boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataProductFromWebAndUpdateToDataBase(authenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/products", cookie), cookie);
                    if (!(checkUpdateRequest))
                        if(!cookie.equalsIgnoreCase("FalseAccount")){
                            cookie = authenticationPostRequest.doRequest();
                        }
                    System.out.println(checkUpdateRequest + " update");
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
                    boolean checkUpdateRequest = checkDataWebAndUpdateDataBase.updateDataCustomerFromWebSetToDataBase(authenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/customers", cookie), cookie);
                    if (!(checkUpdateRequest))
                        if(!cookie.equalsIgnoreCase("FalseAccount")){
                            cookie = authenticationPostRequest.doRequest();
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
                    boolean checkErrorRequest = getDataWebAndSetToDataBase.getDataOrderFromWebSetToDataBase(authenticationGetRequest.connectURLAndReturnHTML("https://booktest2.bizwebvietnam.net/admin/orders", cookie), cookie);
                    if (!(checkErrorRequest))
                        if(!cookie.equalsIgnoreCase("FalseAccount")){
                            cookie = authenticationPostRequest.doRequest();
                        }
                    System.out.println(checkErrorRequest);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
        }
    }
}
