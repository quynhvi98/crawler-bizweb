package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(mappedBy = "productGroup", cascade = CascadeType.ALL)
    private Set<Product> product;
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
