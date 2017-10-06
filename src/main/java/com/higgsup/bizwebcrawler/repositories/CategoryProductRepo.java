package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.CategoryProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryProductRepo extends PagingAndSortingRepository<CategoryProduct,Integer>,CategoryProductRepoCustom {
    @Query(value = "SELECT cp.categoryID FROM CategoryProduct as cp WHERE cp.product_id=:id")
    List<String> getListProductCateIdFormProductIdInCategoryProduct(@Param("id")String id);

}
