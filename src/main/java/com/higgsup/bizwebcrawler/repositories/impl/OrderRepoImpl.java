package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  10/2/2017
 */

import com.higgsup.bizwebcrawler.entites.order.Order;
import com.higgsup.bizwebcrawler.entites.order.OrderAddress;
import com.higgsup.bizwebcrawler.entites.order.OrderProduct;
import com.higgsup.bizwebcrawler.repositories.OrderRepoCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class OrderRepoImpl implements OrderRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public boolean hasOrderId(String orderID) {
        Query query = em.createNativeQuery("SELECT order_id FROM order_product WHERE order_id=:orderID");
        query.setParameter("orderID", orderID);
        return query.getResultList().size() <= 0;
    }
}
