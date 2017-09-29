package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerServices {
    List<Customer> findAll();

}
