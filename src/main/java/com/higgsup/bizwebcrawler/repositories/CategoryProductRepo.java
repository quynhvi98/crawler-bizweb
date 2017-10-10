package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.CategoryProduct;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepo extends PagingAndSortingRepository<CategoryProduct,Integer>,CategoryProductRepoCustom { }
