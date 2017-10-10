package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductCategory;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryServices {
    ProductCategory findCategoryById(String product_cate_id);
    void save(ProductCategory productCategory);
}
