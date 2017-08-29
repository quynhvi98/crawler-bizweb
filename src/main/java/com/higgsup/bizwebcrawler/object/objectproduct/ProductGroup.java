package com.higgsup.bizwebcrawler.object.objectproduct;

import javax.persistence.*;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Product_Group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productGroup_iD;
    private String name;

    public ProductGroup(int productGroup_iD, String name) {
        this.productGroup_iD = productGroup_iD;
        this.name = name;
    }

    public ProductGroup() {
    }

    public int getProductGroup_iD() {

        return productGroup_iD;
    }

    public void setProductGroup_iD(int productGroup_iD) {
        this.productGroup_iD = productGroup_iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
