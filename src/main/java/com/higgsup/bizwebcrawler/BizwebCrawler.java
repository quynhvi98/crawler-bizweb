package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.controller.scheduling.StartScheduling;
import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.repositories.CustomerRepo;
import com.higgsup.bizwebcrawler.repositories.ProductRepo;
import com.higgsup.bizwebcrawler.repositories.ProductRepoCustom;
import com.higgsup.bizwebcrawler.repositories.impl.ProductRepoImpl;
import com.higgsup.bizwebcrawler.services.CustomerServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Iterator;
import java.util.List;

@Configuration
@EnableJpaRepositories("com.higgsup.bizwebcrawler.repositories")
public class BizwebCrawler {
   public final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler-context.xml");

    public static void main(String[] args) {
    /*   CustomerServices customerServices = applicationContext.getBean(CustomerServices.class);
        List<Customer> customers = customerServices.findAll();
        System.out.println(customers.get(0).getId());
        ProductRepo productRepo = applicationContext.getBean(ProductRepo.class);
        */
    //

        CustomerRepo customerRepo=applicationContext.getBean(CustomerRepo.class);
        StartScheduling startScheduling=new StartScheduling();
        startScheduling.startScheduling();
    }
}

