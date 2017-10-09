package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.CategoryProduct;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CategoryProductServices {
    Integer findIdByName(String product_cate_id,String product_id);
    List<String> getListProductCateIdFormProductIdInCategoryProduct(String id);
    void deleteCategoryProduct(String productCateId,String productId);
    void save(CategoryProduct categoryProduct);
}
