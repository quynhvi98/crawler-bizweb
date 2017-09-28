package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.repositories.scheduling.StartScheduling;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BizwebCrawler {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler-context.xml");
        StartScheduling startScheduling = (StartScheduling) applicationContext.getBean("scheduling");
        startScheduling.startScheduling();

    }
}

