package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;

import java.util.List;

public interface ProductRepoCustom {
    List<Product> getProduct();
    List<String> getCurrentCompleteAndPaid(String id);
    List<String> getPreviousCompleteAndPaid(String id);
}
