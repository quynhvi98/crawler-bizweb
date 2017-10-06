package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  10/6/2017
 */

import com.higgsup.bizwebcrawler.repositories.CategoryProductRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CategoryProductRepoImpl implements CategoryProductRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Integer findIdByName(String product_cate_id, String product_id) {
        Query query=em.createNativeQuery("SELECT category_id FROM category_product WHERE product_cate_id =:product_cate_id AND product_id=:product_id order by category_id desc limit 1");
        query.setParameter("product_cate_id",product_cate_id);
        query.setParameter("product_id",product_id);
        List<Integer> listCategoryId=query.getResultList();
        for (Integer integer:listCategoryId
             ) {
            return integer;
        }
        return -1;
    }
}
