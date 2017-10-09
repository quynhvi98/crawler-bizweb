package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import com.higgsup.bizwebcrawler.repositories.OrderRepo;
import com.higgsup.bizwebcrawler.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderServices {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public boolean hasOrderId(String orderID) {
        return orderRepo.hasOrderId(orderID);
    }

    @Override
    public List<Order> getListDataOrders() {
        return orderRepo.getListDataOrders();
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }


}
