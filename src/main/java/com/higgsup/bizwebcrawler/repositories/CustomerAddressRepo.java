package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepo  extends PagingAndSortingRepository<CustomerAddress,String>,CustomerAddressRepoCustom { }
