package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.CategoryProduct;
import com.higgsup.bizwebcrawler.repositories.CategoryProductRepo;
import com.higgsup.bizwebcrawler.services.CategoryProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryProductServiceImpl implements CategoryProductServices {
    @Autowired
    CategoryProductRepo CategoryProductRepo;
    @Override
    public Integer findIdByName(String product_cate_id, String product_id) {
        return CategoryProductRepo.findIdByName(product_cate_id,product_id);
    }

    @Override
    public List<String> getListProductCateIdFormProductIdInCategoryProduct(String id) {
        return CategoryProductRepo.getListProductCateIdFormProductIdInCategoryProduct(id);
    }

    @Override
    public void deleteCategoryProduct(String productCateId, String productId) {
        CategoryProductRepo.deleteCategoryProduct(productCateId,productId);
    }

    @Override
    public void save(CategoryProduct categoryProduct) {
        CategoryProductRepo.save(categoryProduct);
    }
}
