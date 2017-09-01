package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */

import com.higgsup.bizwebcrawler.controller.managedatabase.ProductCategoryDao;
import com.higgsup.bizwebcrawler.controller.managedatabase.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartSoftWare {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("pilot-module.xml");
        ProductDao productDao = (ProductDao) context.getBean("productDao");
        System.out.println(productDao.hasProductID("7824754"));
        ProductCategoryDao productCategoryDao = (ProductCategoryDao) context.getBean("productCategoryDao");
        System.out.println(productCategoryDao.getIDProductCategory("Sản phẩm khuyến mãi"));
        productDao.getIDProducer("NemCollege");
    }
}

