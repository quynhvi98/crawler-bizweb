package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "product_category")
public class ProductCategory  {
    @Id
    @Column(name = "product_cate_id")
    private String productCateID;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Product> products = new HashSet<Product>(0);
    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
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