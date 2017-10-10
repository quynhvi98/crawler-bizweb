package com.higgsup.bizwebcrawler.entites.product;

import com.higgsup.bizwebcrawler.entites.order.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private String productID;
    private String name;
    private Double price;
    private Integer stork;
    private Double weight;
    private String content;
    @Column(name = "IMG")
    private String img;
    private String description;
    @ManyToOne
    @JoinColumn(name="product_group_id")
    private ProductGroup productGroup;

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    @ManyToOne
    @JoinColumn(name="producer_id")
    private Producer producer;

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "category_product", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "product_cate_id",
                    nullable = false, updatable = false) })
    private Set<ProductCategory> categories = new HashSet<ProductCategory>(0);

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Order> orders = new HashSet<Order>(0);

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStork() {
        return stork;
    }

    public void setStork(Integer stork) {
        this.stork = stork;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stork=" + stork +
                ", weight=" + weight +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +


                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Product){
            if(((Product) obj).productID.equals(this.productID)&&
                    ((Product) obj).name.equals(this.name)&&
                    Objects.equals(((Product) obj).price, this.price) &&
                    Objects.equals(((Product) obj).stork, this.stork) &&
                    ((Product) obj).weight==this.weight&&
                    ((Product) obj).content.equals(this.content)&&
                    ((Product) obj).img.equals(this.img)&&
                    ((Product) obj).description.equals(this.description)){
                return true;
            }
        }
        return false;
    }
}
