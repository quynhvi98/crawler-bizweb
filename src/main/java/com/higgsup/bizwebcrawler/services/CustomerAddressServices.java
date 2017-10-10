package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAddressServices {
    List<String> getListCustomerDddIdFormCustomerId(String customer_ID);
    List<CustomerAddress> getListAddressFormCustomerId(String customer_ID);
    void save(CustomerAddress customerAddress);
    void delete(String id);
}
