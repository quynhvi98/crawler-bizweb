package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/29/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface CustomRepoCustom {
    boolean hasCustomerID(String customerID);
    Customer getDataCustomersFromCustomerID(String customer_ID);
}
