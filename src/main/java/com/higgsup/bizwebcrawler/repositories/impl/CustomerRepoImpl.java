package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  9/29/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.repositories.CustomRepoCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomerRepoImpl implements CustomRepoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean hasCustomerID(String customerID) {
        Query query = em.createQuery("SELECT cust.id FROM Customer as cust WHERE cust.id=:customerID ");
        query.setParameter("customerID", customerID);
        return query.getResultList().size() <= 0;
    }
    @Override
    public Customer getDataCustomersFromCustomerID(String customer_ID) {
        Query query = em.createQuery("SELECT cust FROM Customer as cust WHERE cust.id=:customerID ");
        query.setParameter("customerID", customer_ID);
        return (Customer) query.getResultList().get(0);
    }

}
