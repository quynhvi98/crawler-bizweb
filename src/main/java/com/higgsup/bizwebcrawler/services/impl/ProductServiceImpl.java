package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.repositories.ProductRepo;
import com.higgsup.bizwebcrawler.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductServices {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product findById(String poId) {
        return new Product();
    }
}
