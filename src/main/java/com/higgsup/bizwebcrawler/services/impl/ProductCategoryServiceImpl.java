package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductCategory;
import com.higgsup.bizwebcrawler.repositories.ProductCategoryRepo;
import com.higgsup.bizwebcrawler.services.ProductCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryServices {
    @Autowired
    ProductCategoryRepo productCategoryRepo;
    @Override
    public ProductCategory findCategoryById(String product_cate_id) {
        return productCategoryRepo.findCategoryById(product_cate_id);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepo.save(productCategory);
    }
}
