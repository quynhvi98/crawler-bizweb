package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.services.ProductServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.higgsup.bizwebcrawler.repositories")
public class BizwebCrawler {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler-context.xml");
        ProductServices productServices=applicationContext.getBean(ProductServices.class);
        Product product=productServices.findById("8179155");
        if(product!=null){
            System.out.println(product.toString());
        }
    }
}

