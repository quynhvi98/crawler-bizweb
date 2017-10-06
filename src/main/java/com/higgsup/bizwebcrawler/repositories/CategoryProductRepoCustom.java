package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/6/2017
 */

import java.util.List;

public interface CategoryProductRepoCustom {
    Integer findIdByName(String product_cate_id,String product_id);
    List<String> getListProductCateIdFormProductIdInCategoryProduct(String id);
    void deleteCategoryProduct(String productCateId,String productId);
}
