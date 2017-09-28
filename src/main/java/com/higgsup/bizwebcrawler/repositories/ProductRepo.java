package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  9/28/2017
 */


import com.higgsup.bizwebcrawler.entites.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product,String>, ProductRepoCustom {
    @Query(value = "SELECT * FROM product WHERE product_id = :poId", nativeQuery = true)
    Product findById(@Param("poId") String poId);

}

