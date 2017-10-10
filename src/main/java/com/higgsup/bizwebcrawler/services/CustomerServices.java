package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerServices {
    boolean hasCustomerID(String customerID);
    Customer getDataCustomersFromCustomerID(String customer_ID);
    void save(Customer customer);
}
