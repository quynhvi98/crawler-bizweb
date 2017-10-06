package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by viquynh
 */
/*Product reference category: hot, new*/
@Entity
@Table(name = "category_product")
public class CategoryProduct  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int categoryID;
    @Column(name = "product_cate_id")
    private String productCategory;
    @Column(name = "product_id")
    private  String product_id;
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

    public CategoryProduct(int categoryID,String productCategory, String product_id) {
        this.categoryID=categoryID;
        this.productCategory = productCategory;
        this.product_id = product_id;
    }


    public CategoryProduct(String productCategory, String product_id) {
        this.productCategory = productCategory;
        this.product_id = product_id;
    }

    public CategoryProduct() {
    }
}
