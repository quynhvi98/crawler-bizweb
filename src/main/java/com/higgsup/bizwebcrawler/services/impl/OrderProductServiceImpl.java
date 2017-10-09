package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import com.higgsup.bizwebcrawler.repositories.OrderProductRepo;
import com.higgsup.bizwebcrawler.services.OrderProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductServices {
    @Autowired
    OrderProductRepo orderProductRepo;
    @Override
    public List<OrderProduct> getListDataOrderProduct(String id) {
        return orderProductRepo.getListDataOrderProduct(id);
    }

    @Override
    public Integer hasOrderProduct(String ProductID, String OrderID) {
        return orderProductRepo.hasOrderProduct(ProductID,OrderID);
    }

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepo.save(orderProduct);
    }
}
