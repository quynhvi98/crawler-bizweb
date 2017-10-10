package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.repositories.CustomerAddressRepo;
import com.higgsup.bizwebcrawler.services.CustomerAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerAddressServiceImpl implements CustomerAddressServices{
    @Autowired
    CustomerAddressRepo customerAddressRepo;
    @Override
    public List<String> getListCustomerDddIdFormCustomerId(String customer_ID) {
        return customerAddressRepo.getListCustomerDddIdFormCustomerId(customer_ID);
    }

    @Override
    public List<CustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        return customerAddressRepo.getListAddressFormCustomerId(customer_ID);
    }

    @Override
    public void save(CustomerAddress customerAddress) {
        customerAddressRepo.save(customerAddress);
    }

    @Override
    public void delete(String id) {
        customerAddressRepo.delete(id);
    }
}
