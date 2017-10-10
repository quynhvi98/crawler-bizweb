package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.higgsup.bizwebcrawler.repositories")
public class BizwebCrawler {
    private final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler-context.xml");

    public static void main(String[] args) {
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}

