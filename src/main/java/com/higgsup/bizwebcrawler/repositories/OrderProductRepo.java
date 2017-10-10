package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepo extends PagingAndSortingRepository<OrderProduct,Integer> {
    @Query(value = "SELECT  op FROM  OrderProduct as op WHERE op.orderID=:orderID")
    List<OrderProduct> getListDataOrderProduct(@Param("orderID")String id);
    @Query(value = "SELECT op.orderProductID FROM OrderProduct as op WHERE op.productID =:ProductID AND op.orderID=:OrderID")
    Integer hasOrderProduct(@Param("ProductID")String ProductID,@Param("OrderID")String OrderID);
}
