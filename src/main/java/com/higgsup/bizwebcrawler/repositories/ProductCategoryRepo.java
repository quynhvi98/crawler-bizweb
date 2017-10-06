package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductCategoryRepo extends PagingAndSortingRepository<ProductCategory,String> {

   @Query(value = "SELECT pc FROM ProductCategory as pc WHERE pc.productCateID=:productCateID ")
   ProductCategory findCategoryById(@Param("productCateID")String product_cate_id);

}
