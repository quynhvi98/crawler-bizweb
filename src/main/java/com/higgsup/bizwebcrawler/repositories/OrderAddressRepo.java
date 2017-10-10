package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAddressRepo extends PagingAndSortingRepository<OrderAddress,Integer>,OrderAddressRepoCustom {
    @Query(value = "SELECT oa FROM OrderAddress  as oa")
    List<OrderAddress> getListDataOrderAddress();
}
