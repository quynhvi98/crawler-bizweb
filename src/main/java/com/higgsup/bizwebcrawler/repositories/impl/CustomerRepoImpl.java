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
        Query query = em.createQuery("SELECT cta FROM CustomerAddress as  cta WHERE cta.customerID =:customerId ");
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
        Query query = em.createQuery("select  custadd.id FROM  CustomerAddress as custadd where custadd.customerID=:customerID");
        query.setParameter("customerID", objectCustomerAddress.getCustomerID());
        if (query.getMaxResults() > 0) {
            query = em.createNativeQuery("INSERT customer_address(customer_add_id,address_user ,name ,phone ,company ,zipe_code ,customer_id ,nation ,city ,district)VALUES(:customer_add_id,:address_user,:name,:phone,:company,:zipe_code,:customer_id,:nation,:city,:district)");
            query.setParameter("customer_add_id", objectCustomerAddress.getId());
            query.setParameter("address_user", objectCustomerAddress.getAddressUser());
            query.setParameter("name", objectCustomerAddress.getFullName());
            query.setParameter("phone", objectCustomerAddress.getPhoneNumber());
            query.setParameter("company", objectCustomerAddress.getCompany());
            query.setParameter("zipe_code", objectCustomerAddress.getZipeCode());
            query.setParameter("customer_id", objectCustomerAddress.getCustomerID());
            query.setParameter("nation", objectCustomerAddress.getNation());
            query.setParameter("city", objectCustomerAddress.getCity());
            query.setParameter("district", objectCustomerAddress.getDistrict());
            query.executeUpdate();
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
            query = em.createQuery("UPDATE Customer as customer SET customer.fullName=:fullName,customer.email=:email," +
                    "customer.totalBill=:totalBill" +
                    " WHERE customer.id=:customerId");
            query.setParameter("fullName", objectCustomers.getFullName());
            query.setParameter("email", objectCustomers.getEmail());
            query.setParameter("totalBill", objectCustomers.getTotalBill());
            query.setParameter("customerId", objectCustomers.getId());
            query.executeUpdate();
        }
    }

    @Transactional
    @Override
    public void updateDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        Query query = em.createQuery("SELECT customerAddress FROM CustomerAddress as customerAddress " +
                "WHERE customerAddress.id=:customerAddressId AND customerAddress.customerID=:customerID");
        query.setParameter("customerAddressId", objectCustomerAddress.getId());
        query.setParameter("customerID", objectCustomerAddress.getCustomerID());
        if (query.getMaxResults() > 0) {
            query = em.createQuery("update  CustomerAddress as customerAddress set customerAddress.addressUser=:addressUser," +
                    "customerAddress.fullName=:name,customerAddress.phoneNumber=:phone,customerAddress.company=:company," +
                    "customerAddress.zipeCode=:zipeCode,customerAddress.nation=:nation,customerAddress.city=:city," +
                    "customerAddress.district=:district  " +
                    "where customerAddress.id=:id and  customerAddress.customerID=:customerId");
            query.setParameter("addressUser", objectCustomerAddress.getAddressUser());
            query.setParameter("name", objectCustomerAddress.getFullName());
            query.setParameter("phone", objectCustomerAddress.getPhoneNumber());
            query.setParameter("company", objectCustomerAddress.getCompany());
            query.setParameter("zipeCode", objectCustomerAddress.getZipeCode());
            query.setParameter("nation", objectCustomerAddress.getNation());
            query.setParameter("city", objectCustomerAddress.getCity());
            query.setParameter("district", objectCustomerAddress.getDistrict());
            query.setParameter("id", objectCustomerAddress.getId());
            query.setParameter("customerId", objectCustomerAddress.getCustomerID());
            query.executeUpdate();
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
