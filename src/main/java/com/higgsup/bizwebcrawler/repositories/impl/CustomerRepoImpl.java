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

    @Override
    public boolean hasCustomerID(String customerID) {
        Query query = em.createQuery("SELECT cust.id FROM Customer as cust WHERE cust.id=:customerID ");
        query.setParameter("customerID", customerID);
        return query.getResultList().size() <= 0;
    }

    @Transactional
    @Override
    public boolean setDataFromCustomer(Customer customer) {
        Query query = em.createNativeQuery("INSERT customer (customer_id, full_name, email, total_bill) VALUES (:customer_id,:full_name,:email,:total_bill)");
        query.setParameter("customer_id", customer.getId());
        query.setParameter("full_name", customer.getFullName());
        query.setParameter("email", customer.getEmail());
        query.setParameter("total_bill", customer.getTotalBill());
        return query.executeUpdate() > 0;
    }

    @Transactional
    @Override
    public void setDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        Query query = em.createQuery("select  custadd.id FROM  CustomerAddress as custadd where custadd.customer.id=:customerID");
        query.setParameter("customerID", objectCustomerAddress.getCustomer().getId());
        if (query.getMaxResults() > 0) {
                em.merge(objectCustomerAddress);
        }
    }

    @Override
    public Customer getDataCustomersFromCustomerID(String customer_ID) {
        Query query = em.createQuery("SELECT cust FROM Customer as cust WHERE cust.id=:customerID ");
        query.setParameter("customerID", customer_ID);
        return (Customer) query.getResultList().get(0);
    }

    @Transactional
    @Override
    public void updateDataCustomersFromObjectCustomer(Customer objectCustomers) {
        Query query = em.createQuery("SELECT customer FROM Customer as customer WHERE customer.id=:customerId");
        query.setParameter("customerId", objectCustomers.getId());
        if (query.getMaxResults() > 0) {
        em.merge(objectCustomers);
        }
    }

    @Transactional
    @Override
    public void updateDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        Query query = em.createQuery("SELECT customerAddress FROM CustomerAddress as customerAddress " +
                "WHERE customerAddress.id=:customerAddressId AND customerAddress.customer.id=:customerID");
        query.setParameter("customerAddressId", objectCustomerAddress.getId());
        query.setParameter("customerID", objectCustomerAddress.getCustomer().getId());
        if (query.getMaxResults() > 0) {
          em.merge(objectCustomerAddress);
        }
    }

    @Transactional
    @Override
    public void deleteDataCustomerAddress(String ID) {
        Query query = em.createQuery("DELETE from CustomerAddress WHERE id=:ID");
        query.setParameter("ID", ID);
        query.executeUpdate();
    }
}
