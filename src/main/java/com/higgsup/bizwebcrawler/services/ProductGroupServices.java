package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductGroup;
import org.springframework.stereotype.Service;

@Service
public interface ProductGroupServices {
    Integer getIDProductGroup(String name);
    void save(ProductGroup productGroup);
}
