package com.higgsup.bizwebcrawler.services.scheduling;

import com.higgsup.bizwebcrawler.controller.managedatabase.ConnectDB;
import org.springframework.stereotype.Component;
import com.higgsup.bizwebcrawler.services.authentication.CheckingAuthentication;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/*
    By chicanem 10/08/2017
    */
@Component
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
