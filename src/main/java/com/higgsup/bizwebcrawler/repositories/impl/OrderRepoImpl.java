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
    @Transactional
    @Override
    public void setDataPaymentFromOrder(String content) {
        Query query = em.createQuery("SELECT  p.content  from Payment as p where p.content=:content");
        query.setParameter("content", content);
        if (query.getResultList().size() <=0) {
            query = em.createNativeQuery("INSERT payment (content) VALUES (:content)");
            query.setParameter("content", content);
            query.executeUpdate();
        }
    }

    @Override
    public boolean hasOrderId(String orderID) {
        Query query = em.createNativeQuery("SELECT order_id FROM order_product WHERE order_id=:orderID");
        query.setParameter("orderID", orderID);
        return query.getResultList().size() <= 0;
    }
    @Transactional
    @Override
    public void setDataFromOrder(Order dataFromOrder) {
        em.merge(dataFromOrder);
    }
    @Transactional
    @Override
    public void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        Query query = em.createQuery("SELECT o.productID FROM OrderProduct as o WHERE o.productID =:productID AND o.orderID=:orderID");
        query.setParameter("productID", dataFromOrderAndProduct.getProductID());
        query.setParameter("orderID", dataFromOrderAndProduct.getOrderID());
        if (query.getResultList().size() <= 0) {
          em.merge(dataFromOrderAndProduct);
        }

    }
    @Transactional
    @Override
    public void setDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
        Query query = em.createQuery(" SELECT oad.orderID FROM OrderAddress as oad WHERE oad.orderID=:orderID");
        query.setParameter("orderID", dataFromOrderAddress.getOrderID());
        if (query.getResultList().size() <= 0) {
            em.merge(dataFromOrderAddress);
        }

    }
    @Transactional
    @Override
    public void updateDataFromOrder(Order dataFromOrder) {
        Query query=em.createQuery("SELECT o FROM Order as o WHERE o.orderID=:orderID");
        query.setParameter("orderID", dataFromOrder.getOrderID());
        List<Order> orderList=query.getResultList();
        if (orderList.size() >0) {
            dataFromOrder.setOrderID(orderList.get(0).getOrderID());
            em.merge(dataFromOrder);
        }
    }
    @Transactional
    @Override
    public boolean updateDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        Query query=em.createQuery("SELECT op FROM OrderProduct as op WHERE op.productID =:ProductID AND op.orderID=:OrderID");
        query.setParameter("ProductID", dataFromOrderAndProduct.getProductID());
        query.setParameter("OrderID", dataFromOrderAndProduct.getOrderID());
        List<OrderProduct> orderProductList =query.getResultList();
        if(orderProductList.size()>0){
            dataFromOrderAndProduct.setOrderProductID(orderProductList.get(0).getOrderProductID());
            System.out.println(dataFromOrderAndProduct.getProductID());
            em.merge(dataFromOrderAndProduct);
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    @Override
    public void updateDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
        Query query=em.createQuery("SELECT oad FROM OrderAddress as oad WHERE oad.orderID=:orderID");
        query.setParameter("orderID",dataFromOrderAddress.getOrderID());
        List<OrderAddress> orderAddressList=query.getResultList();
        if(orderAddressList.size()>0){
            dataFromOrderAddress.setOrderAddressID(orderAddressList.get(0).getOrderAddressID());
            em.merge(dataFromOrderAddress);
        }
    }
}
