package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.repositories.ProductRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepoImpl implements ProductRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Product> getProduct() {
        return null;
    }

    @Override
    public List<String> getCurrentCompleteAndPaid(String id) {
        return null;
    }

    @Override
    public List<String> getPreviousCompleteAndPaid(String id) {
        return null;
    }
}
