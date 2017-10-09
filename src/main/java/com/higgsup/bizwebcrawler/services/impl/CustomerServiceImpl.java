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
    public List<Customer> findAll() {
        return customerRepo.findById();
    }

    @Override
    public List<String> getListCustomerDddIdFormCustomerId(String customer_ID) {
        return customerRepo.getListCustomerDddIdFormCustomerId(customer_ID);
    }

    @Override
    public List<CustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        return customerRepo.getListAddressFormCustomerId(customer_ID);
    }

    @Override
    public boolean hasCustomerID(String customerID) {
        return customerRepo.hasCustomerID(customerID);
    }

    @Override
    public boolean setDataFromCustomer(Customer customer) {
        return customerRepo.setDataFromCustomer(customer);
    }

    @Override
    public void setDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        customerRepo.setDataCustomerAddress(objectCustomerAddress);
    }

    @Override
    public Customer getDataCustomersFromCustomerID(String customer_ID) {
        return customerRepo.getDataCustomersFromCustomerID(customer_ID);
    }

    @Override
    public void updateDataCustomersFromObjectCustomer(Customer objectCustomers) {
        customerRepo.updateDataCustomersFromObjectCustomer(objectCustomers);
    }

    @Override
    public void updateDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        customerRepo.updateDataCustomerAddress(objectCustomerAddress);
    }

    @Override
    public void deleteDataCustomerAddress(String ID) {
        customerRepo.deleteDataCustomerAddress(ID);
    }


}
