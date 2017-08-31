package com.higgsup.bizwebcrawler.model.objectproduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_ID")
    private String productID;
    private String name;
    private float price;
    private int stork;
    @Column(name = "weight_")
    private float weight;
    private String content;
    @Column(name = "IMG")
    private String img;
    @Column(name = "description_")
    private String description;
    @Column(name = "productGroup_iD")
    private int productGroupID;
    @Column(name = "producer_ID")
    private int producerID;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStork() {
        return stork;
    }

    public void setStork(int stork) {
        this.stork = stork;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
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

    public int getProductGroupID() {
        return productGroupID;
    }

    public void setProductGroupID(int productGroupID) {
        this.productGroupID = productGroupID;
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }

    public Product(String productID, String name, float price, int stork, float weight, String content, String img, String description, int productGroupID, int producerID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight = weight;
        this.content = content;
        this.img = img;
        this.description = description;
        this.productGroupID = productGroupID;
        this.producerID = producerID;
    }

    public Product() {
    }

}
