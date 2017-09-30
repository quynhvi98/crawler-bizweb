package com.higgsup.bizwebcrawler.entites.product;
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
    private String productCategory;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Column(name = "product_id")
    private  String product_id;

    public CategoryProduct() {
    }
}
