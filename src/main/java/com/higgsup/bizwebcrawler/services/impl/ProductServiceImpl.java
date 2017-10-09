package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.repositories.ProductRepo;
import com.higgsup.bizwebcrawler.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductServices {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Integer findById(String poId) {
        return productRepo.findById(poId);
    }

    @Override
    public Product getDataProductFromProductID(String product_ID) {
        return productRepo.getDataProductFromProductID(product_ID);
    }

    @Override
    public List<Product> getProduct() {
        return productRepo.getProduct();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }
}
