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
    @ManyToMany
    private ProductCategory productCategory;
    @ManyToMany
    @Column(name = "product_id")
    private  Product product;




}
