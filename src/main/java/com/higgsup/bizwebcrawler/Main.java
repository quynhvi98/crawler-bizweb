package com.higgsup.bizwebcrawler;

/**
 * Created by viquynh.
 */
import com.higgsup.bizwebcrawler.methods.managedatabase.ProductCategoryDao;
import com.higgsup.bizwebcrawler.methods.managedatabase.ProductDao;
import com.higgsup.bizwebcrawler.object.objectproduct.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("pilot-module.xml");
        ProductDao productDao = (ProductDao) context.getBean("productDao");
        System.out.println(productDao.hasProductID("7824754"));
        ProductCategoryDao  productCategoryDao= (ProductCategoryDao) context.getBean("productCategoryDao");
        System.out.println(productCategoryDao.getIDProductCategory("Sản phẩm khuyến mãi"));



    }
}

