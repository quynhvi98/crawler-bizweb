package com.higgsup.bizwebcrawler.model.objectproduct;

import javax.persistence.*;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Product_Group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productGroup_iD")
    private int productGroupID;
    private String name;

    public int getProductGroupID() {
        return productGroupID;
    }

    public ProductGroup() {
    }

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
