package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerServices {
    List<Customer> findAll();
    List<String> getListCustomerDddIdFormCustomerId(String customer_ID);

    List<CustomerAddress> getListAddressFormCustomerId(String customer_ID);

    boolean hasCustomerID(String customerID);

    boolean setDataFromCustomer(Customer customer);

    void setDataCustomerAddress(CustomerAddress objectCustomerAddress);

    Customer getDataCustomersFromCustomerID(String customer_ID);

    void updateDataCustomersFromObjectCustomer(Customer objectCustomers);

    void updateDataCustomerAddress(CustomerAddress objectCustomerAddress);

    void deleteDataCustomerAddress(String ID);
}
