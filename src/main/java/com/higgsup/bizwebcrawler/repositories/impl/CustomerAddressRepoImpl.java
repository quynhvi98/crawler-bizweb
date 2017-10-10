package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.repositories.CustomerAddressRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomerAddressRepoImpl implements CustomerAddressRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<String> getListCustomerDddIdFormCustomerId(String customer_ID) {
        Query query = em.createNativeQuery("SELECT customer_add_id FROM customer_address WHERE customer_id=:customerId");
        query.setParameter("customerId", customer_ID);
        return (List<String>) query.getResultList();
    }

    @Override
    public List<CustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        Query query = em.createQuery("SELECT cta FROM CustomerAddress as  cta WHERE cta.customer.id =:customerId ");
        query.setParameter("customerId", customer_ID);
        return query.getResultList();
    }
}
