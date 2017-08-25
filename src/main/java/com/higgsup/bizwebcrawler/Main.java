package com.higgsup.bizwebcrawler;


/**
 * Created by viquynh.
 */
import com.higgsup.bizwebcrawler.methods.managedatabase.QueryDataProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("pilot-module.xml");
        QueryDataProduct queryDataProduct= (QueryDataProduct) context.getBean("InterfaceDataProduct");
        System.out.println(queryDataProduct.hasProductID("7824754"));

    }
}

