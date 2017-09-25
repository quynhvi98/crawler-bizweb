package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.controller.common.FileTemplate;
import com.higgsup.bizwebcrawler.controller.scheduling.StartScheduling;
import com.higgsup.bizwebcrawler.model.product.Producer;
import com.higgsup.bizwebcrawler.model.product.Product;
import com.higgsup.bizwebcrawler.model.product.ProductGroup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BizwebCrawler {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler.xml");
        StartScheduling startScheduling = (StartScheduling) applicationContext.getBean("scheduling");
        startScheduling.startScheduling();

    }
}

