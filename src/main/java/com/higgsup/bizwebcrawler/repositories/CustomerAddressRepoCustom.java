package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;

import java.util.List;

public interface CustomerAddressRepoCustom {
    List<String> getListCustomerDddIdFormCustomerId(String customer_ID);
    List<CustomerAddress> getListAddressFormCustomerId(String customer_ID);
}
