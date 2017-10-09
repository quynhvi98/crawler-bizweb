package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.repositories.CustomerRepo;
import com.higgsup.bizwebcrawler.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServices {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public boolean hasCustomerID(String customerID) {
        return customerRepo.hasCustomerID(customerID);
    }

    @Override
    public Customer getDataCustomersFromCustomerID(String customer_ID) {
        return customerRepo.getDataCustomersFromCustomerID(customer_ID);
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }


}
