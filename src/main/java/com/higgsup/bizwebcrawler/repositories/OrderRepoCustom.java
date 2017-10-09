package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;

public interface OrderRepoCustom {
    boolean hasOrderId(String orderID);
}
