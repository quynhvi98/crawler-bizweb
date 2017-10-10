package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.repositories.ProductRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ProductRepoImpl implements ProductRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Product> getProduct() {
        Query query = em.createQuery("SELECT a FROM Product as a");
        return query.getResultList();
    }
}
