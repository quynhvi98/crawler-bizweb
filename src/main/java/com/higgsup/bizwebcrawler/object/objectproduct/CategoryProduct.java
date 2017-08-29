package com.higgsup.bizwebcrawler.object.objectproduct;

import javax.persistence.*;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Category_Product")
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_ID;
    private String productCate_ID;
    private  String product_ID;

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }

    public String getProductCate_ID() {
        return productCate_ID;
    }

    public void setProductCate_ID(String productCate_ID) {
        this.productCate_ID = productCate_ID;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public CategoryProduct(String productCate_ID, String product_ID) {

        this.productCate_ID = productCate_ID;
        this.product_ID = product_ID;
    }

    public CategoryProduct() {
    }
}
