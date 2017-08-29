package com.higgsup.bizwebcrawler.object.objectproduct;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Product_Category")
public class ProductCategory {
    @Id
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