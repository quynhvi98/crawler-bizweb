package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.controller.StartScheduling;
import com.higgsup.bizwebcrawler.controller.managedatabase.ProductCategoryDao;
import com.higgsup.bizwebcrawler.controller.managedatabase.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartSoftWare {
    public static void main(String[] args) {
        StartScheduling startScheduling=new StartScheduling();
        startScheduling.startScheduling();
    }
}

