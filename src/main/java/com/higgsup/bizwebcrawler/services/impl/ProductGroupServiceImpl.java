package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductGroup;
import com.higgsup.bizwebcrawler.repositories.ProductGroupRepo;
import com.higgsup.bizwebcrawler.services.ProductGroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupServiceImpl implements ProductGroupServices {
    @Autowired
    ProductGroupRepo ProductGroupRepo;
    @Override
    public Integer getIDProductGroup(String name) {
        return ProductGroupRepo.getIDProductGroup(name);
    }

    @Override
    public void save(ProductGroup productGroup) {
        ProductGroupRepo.save(productGroup);
    }
}
