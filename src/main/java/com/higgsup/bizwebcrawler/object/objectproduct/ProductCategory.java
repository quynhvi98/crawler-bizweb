package com.higgsup.bizwebcrawler.object.objectproduct;

/**
 * Created by viquynh
 */
public class ProductCategory {
    private String productCate_ID;
    private String name;

    public ProductCategory(String productCate_ID, String name) {
        this.productCate_ID = productCate_ID;
        this.name = name;
    }

    public ProductCategory() {
    }

    public String getProductCate_ID() {
        return productCate_ID;
    }

    public void setProductCate_ID(String productCate_ID) {
        this.productCate_ID = productCate_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}