package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderAddressServices {
    List<OrderAddress> getListDataOrderAddress();
    void save(OrderAddress orderAddress);
    void updateDataOrderAddress(OrderAddress orderAddress);
}
