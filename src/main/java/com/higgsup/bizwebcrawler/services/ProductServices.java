package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  9/28/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Product;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductServices {
    Integer findById(String poId);
    Product getDataProductFromProductID(String product_ID);

    List<Product> getProduct();

    void save(Product product);
}
