package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;

public interface OrderRepoCustom {
    void setDataPaymenFromOrder(String content);
    boolean hasOrderId(String orderID);
    void setDataFromOrder(Order dataFromOrder);
    void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct);
    void setDataFromOrderAddress(OrderAddress dataFromOrderAddress);
    void updateDataFromOrder(Order dataFromOrder);
    boolean updateDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct);
    void updateDataFromOrderAddress(OrderAddress dataFromOrderAddress);
}
