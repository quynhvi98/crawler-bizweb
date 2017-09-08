package com.higgsup.bizwebcrawler.model.product;
import javax.persistence.*;
/**
 * Created by viquynh
 */
@Entity
@Table(name = "product_group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_group_id")
    private int productGroupID;
    private String name;
    public int getProductGroupID() {
        return productGroupID;
    }
    public ProductGroup() { }
    public void setProductGroupID(int productGroupID) {
        this.productGroupID = productGroupID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ProductGroup(String name) {
        this.name = name;
    }

}
