package com.higgsup.bizwebcrawler.entites.product;

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
    @Column(name = "product_group_id")
    private Integer productGroupId;
    @ManyToOne
    @JoinColumn(name="producer_id")
    private Producer producer;

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    private Set<ProductCategory> categories = new HashSet<ProductCategory>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "category_product", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "product_cate_id",
                    nullable = false, updatable = false) })

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }
    public Product(String productID, String name, Double price, Integer stork, Double weight, String content, String img, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight = weight;
        this.content = content;
        this.img = img;
        this.description = description;

    }

    public Product(String productID, String name, Double price, Integer stork, Double weight, String content, String img, String description, int productGroupId, Producer producer) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight = weight;
        this.content = content;
        this.img = img;
        this.description = description;
        this.productGroupId = productGroupId;
        this.producer = producer;
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

    public Integer getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Integer productGroupId) {
        this.productGroupId = productGroupId;
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
                ", productGroupId=" + productGroupId +

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
                    ((Product) obj).description.equals(this.description)&&
                    ((Product) obj).productGroupId==this.productGroupId){
                return true;
            }
        }
        return false;
    }
}
