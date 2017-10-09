package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServices {
    boolean hasOrderId(String orderID);

    List<Order> getListDataOrders();

    void save(Order order);
}
