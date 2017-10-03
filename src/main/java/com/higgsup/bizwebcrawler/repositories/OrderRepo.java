package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order,String>,OrderRepoCustom {
    @Query(value = "SELECT paymen.paymentID FROM Paymen AS paymen WHERE paymen.content=:content")
    int getIDPaymentFromContent(@Param("content") String content);
    @Query(value = "SELECT o FROM Order as o")
    List<Order> getListDataOrders();
    @Query(value = "SELECT oa FROM OrderAddress  as oa")
    List<OrderAddress> getListDataOrderAddress();
    @Query(value = "SELECT  op FROM  OrderProduct as op WHERE op.orderID=:orderID")
    List<OrderProduct> getListDataOrderProduct(@Param("orderID")String id);
}
