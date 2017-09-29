package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.repositories.CustomerRepo;
import com.higgsup.bizwebcrawler.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServicelmpl implements CustomerServices {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public List<Customer> findAll() {
        return customerRepo.findById();
    }




}
