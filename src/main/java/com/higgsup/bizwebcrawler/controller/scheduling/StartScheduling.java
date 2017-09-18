package com.higgsup.bizwebcrawler.controller.scheduling;

import com.higgsup.bizwebcrawler.controller.authentication.CheckingAuthentication;
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
public class StartScheduling extends CheckingAuthentication {
    private static final Logger logger = Logger.getLogger(StartScheduling.class.getName());

    @Override
    public String getCookie() {
        return super.getCookie();
    }

    public StartScheduling() {

    }

    public void startScheduling() {//bắt đầu chương trình
        final ScheduledExecutorService reLoadTime = Executors.newSingleThreadScheduledExecutor();
        try {
            ConnectDB con = new ConnectDB();
            con.startConnect();
            doRequestTakeCookie();
            Runnable queryProduct = new QueryingProductInformation() {
                @Override
                public void run() {
                    System.out.printf("queryProduct");
                    super.run();
                }
            };
            Runnable queryInfoCustomer = new QueryingCustomerInformation() {
                @Override
                public void run() {
                    System.out.printf("queryInfoCustomer");

                    super.run();
                }
            };
            Runnable queryInfoOrder = new QueryingOrderInformation() {
                @Override
                public void run() {
                    System.out.printf("queryInfoOrder");

                    super.run();
                }
            };
            Runnable updateDataProduct = new UpdatingProductData() {
                @Override
                public void run() {
                    System.out.printf("updateDataProduct");
                    super.run();
                }
            };
            Runnable updateDatCustomer = new UpdatingCustomerData() {
                @Override
                public void run() {
                    System.out.printf("updateDatCustomer");

                    super.run();
                }
            };
            Runnable updatingOrderData = new UpdatingOrderData() {
                @Override
                public void run() {
                    System.out.printf("updatingOrderData");

                    super.run();
                }
            };
            reLoadTime.scheduleWithFixedDelay(queryProduct, 10, 100, TimeUnit.SECONDS);
          //  reLoadTime.scheduleWithFixedDelay(queryInfoCustomer, 0, 100, TimeUnit.SECONDS);
          //  reLoadTime.scheduleWithFixedDelay(queryInfoOrder, 0, 100, TimeUnit.SECONDS);
           // reLoadTime.scheduleWithFixedDelay(updateDataProduct, 0, 120, TimeUnit.MINUTES);
           // reLoadTime.scheduleWithFixedDelay(updateDatCustomer, 0, 120, TimeUnit.MINUTES);
           // reLoadTime.scheduleWithFixedDelay(updatingOrderData, 0, 120, TimeUnit.MINUTES);

        } catch (Error e) {
            String s = e.getLocalizedMessage();
            if (s.equals("FalseAccount")) {
                System.out.println("sai tk mk");
            }
            if (s.equals("Not Connect Internet")) {
                System.out.println("mất mạng");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
