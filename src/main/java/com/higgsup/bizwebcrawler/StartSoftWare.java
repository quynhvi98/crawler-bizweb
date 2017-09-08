package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.controller.StartScheduling;
import com.higgsup.bizwebcrawler.controller.managedatabase.ProductCategoryDao;
import com.higgsup.bizwebcrawler.controller.managedatabase.ProductDao;
import com.higgsup.bizwebcrawler.model.product.Producer;
import com.higgsup.bizwebcrawler.model.product.Product;
import com.higgsup.bizwebcrawler.model.product.ProductGroup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartSoftWare {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bizwebcrawler.xml");
        Producer producer = (Producer) applicationContext.getBean("producer");
        producer.setName("haha");
        ProductGroup productGroup = (ProductGroup) applicationContext.getBean("productGroup");
        productGroup.setName("haha");
        Product Product= (com.higgsup.bizwebcrawler.model.product.Product) applicationContext.getBean("product");
        Product Product1= (com.higgsup.bizwebcrawler.model.product.Product) applicationContext.getBean("product");
        System.out.println(Product.getProducer().getName());
        System.out.println(Product1.getProducer().getName());
    }
}

