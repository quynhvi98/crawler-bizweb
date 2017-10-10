package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo  extends PagingAndSortingRepository<Customer,String>,CustomRepoCustom{ }