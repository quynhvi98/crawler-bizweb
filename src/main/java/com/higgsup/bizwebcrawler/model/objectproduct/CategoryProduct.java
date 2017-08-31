package com.higgsup.bizwebcrawler.model.objectproduct;

import javax.persistence.*;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Category_Product")
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_ID")
    private int categoryID;
    @Column(name = "productCate_ID")
    private String productCateID;
    @Column(name = "product_ID")
    private  String productID;

    public CategoryProduct(String productCateID, String productID) {
        this.productCateID = productCateID;
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductCateID() {
        return productCateID;
    }

    public void setProductCateID(String productCateID) {
        this.productCateID = productCateID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public CategoryProduct() {
    }
}
