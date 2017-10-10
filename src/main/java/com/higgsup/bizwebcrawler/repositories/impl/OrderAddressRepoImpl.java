package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  10-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.repositories.OrderAddressRepoCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class OrderAddressRepoImpl implements OrderAddressRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public void updateDataOrderAddress(OrderAddress orderAddress) {
            Query query=em.createQuery("SELECT oad.orderAddressID FROM OrderAddress as oad WHERE oad.orderID=:orderID");
            query.setParameter("orderID",orderAddress.getOrderID());
        List<Integer> integerList=query.getResultList();
            if(integerList.size()>0){
                orderAddress.setOrderAddressID(integerList.get(0));
                em.merge(orderAddress);
            }
    }
}
