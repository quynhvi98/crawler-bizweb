package com.higgsup.bizwebcrawler.object.objectproduct;

import javax.persistence.Column;
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
    @Column(name = "productCate_ID")
    private String productCateID;
    private String name;



    public ProductCategory() {
    }

    public String getProductCateID() {
        return productCateID;
    }

    public void setProductCateID(String productCateID) {
        this.productCateID = productCateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory(String productCateID, String name) {

        this.productCateID = productCateID;
        this.name = name;
    }
}