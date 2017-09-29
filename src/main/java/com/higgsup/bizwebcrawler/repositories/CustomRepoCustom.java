package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/29/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface CustomRepoCustom {
    List<String> getListCustomerDddIdFormCustomerId(String customer_ID);

    List<CustomerAddress> getListAddressFormCustomerId(String customer_ID);

    boolean hasCustomerID(String customerID);

    boolean setDataFromCustomer(Customer customer);

    boolean setDataCustomerAddress(CustomerAddress objectCustomerAddress);

    Customer getDataCustomersFromCustomerID(String customer_ID);

    boolean updateDataCustomersFromObjectCustomer(Customer objectCustomers);
    boolean updateDataCustomerAddress(CustomerAddress objectCustomerAddress);

}
