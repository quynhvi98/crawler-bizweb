package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.repositories.OrderAddressRepo;
import com.higgsup.bizwebcrawler.services.OrderAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAddressServiceImpl implements OrderAddressServices {
    @Autowired
    OrderAddressRepo orderAddressRepo;
    @Override
    public List<OrderAddress> getListDataOrderAddress() {
        return orderAddressRepo.getListDataOrderAddress();
    }

    @Override
    public void save(OrderAddress orderAddress) {
        orderAddressRepo.save(orderAddress);
    }

    @Override
    public void updateDataOrderAddress(OrderAddress orderAddress) {
        orderAddressRepo.updateDataOrderAddress(orderAddress);
    }
}
