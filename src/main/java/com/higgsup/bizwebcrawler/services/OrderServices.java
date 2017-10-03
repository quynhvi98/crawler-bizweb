package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServices {
    void setDataPaymenFromOrder(String content);
    boolean hasOrderId(String orderID);
    void setDataFromOrder(Order dataFromOrder);
    void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct);
    void setDataFromOrderAddress(OrderAddress dataFromOrderAddress);
    void updateDataFromOrder(Order dataFromOrder);
    boolean updateDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct);
    void updateDataFromOrderAddress(OrderAddress dataFromOrderAddress);
    List<OrderProduct> getListDataOrderProduct(String id);
    List<OrderAddress> getListDataOrderAddress();
    List<Order> getListDataOrders();
    int getIDPaymentFromContent(String content);

}
