package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductServices {
    Product findById(String poId);
}
