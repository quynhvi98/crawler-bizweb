package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.ProductGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupRepo extends PagingAndSortingRepository<ProductGroup, Integer>, ProductGroupRepoCustom {
    @Query(value = "SELECT product_group_id FROM product_group WHERE name=:name", nativeQuery = true)
    Integer getIDProductGroup(@Param("name")String name);




}
