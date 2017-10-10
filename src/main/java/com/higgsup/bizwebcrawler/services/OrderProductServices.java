package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderProductServices {
    List<OrderProduct> getListDataOrderProduct(String id);
    Integer hasOrderProduct(String ProductID,String OrderID);
    void save(OrderProduct orderProduct);


}
