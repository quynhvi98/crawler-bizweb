package com.higgsup.bizwebcrawler.model.product;
import javax.persistence.*;
/**
 * Created by viquynh
 */
/*Product reference category: hot, new*/
@Entity
@Table(name = "category_product")
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int categoryID;
    @Column(name = "product_cate_id")
    private String productCateID;
    @Column(name = "product_id")
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
