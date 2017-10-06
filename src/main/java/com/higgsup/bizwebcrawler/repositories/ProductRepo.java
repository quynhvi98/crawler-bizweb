package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/28/2017
 */


import com.higgsup.bizwebcrawler.entites.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product,String>, ProductRepoCustom {
    @Query(value = "SELECT product_id FROM product WHERE product_id = :product_id ORDER BY product_id limit 1", nativeQuery = true)
    Integer findById(@Param("product_id") String poId);

    @Query(value = "SELECT p FROM Product as p WHERE p.productID=:product_ID")
    Product getDataProductFromProductID(@Param("product_ID")String product_ID);
}

