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
@Transactional
public class OrderServiceImpl implements OrderServices {
    @Autowired
    OrderRepo orderRepo;
    @Override
    public void setDataPaymenFromOrder(String content) {
        orderRepo.setDataPaymenFromOrder(content);
    }

    @Override
    public boolean hasOrderId(String orderID) {
        return orderRepo.hasOrderId(orderID);
    }

    @Override
    public void setDataFromOrder(Order dataFromOrder) {
orderRepo.setDataFromOrder(dataFromOrder);
    }

    @Override
    public void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
    orderRepo.setDataFromOrderAndProduct(dataFromOrderAndProduct);
    }

    @Override
    public void setDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
    orderRepo.setDataFromOrderAddress(dataFromOrderAddress);
    }

    @Override
    public void updateDataFromOrder(Order dataFromOrder) {
    orderRepo.updateDataFromOrder(dataFromOrder);
    }

    @Override
    public boolean updateDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        return orderRepo.updateDataFromOrderAndProduct(dataFromOrderAndProduct);
    }

    @Override
    public void updateDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
    orderRepo.updateDataFromOrderAddress(dataFromOrderAddress);
    }

    @Override
    public List<OrderProduct> getListDataOrderProduct(String id) {
        return orderRepo.getListDataOrderProduct(id);
    }

    @Override
    public List<OrderAddress> getListDataOrderAddress() {
        return orderRepo.getListDataOrderAddress();
    }

    @Override
    public List<Order> getListDataOrders() {
        return orderRepo.getListDataOrders();
    }

    @Override
    public int getIDPaymentFromContent(String content) {
        return orderRepo.getIDPaymentFromContent(content);
    }
}
