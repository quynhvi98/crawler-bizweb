package com.higgsup.bizwebcrawler.model.product;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

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
    private int stork;
    private Double weight;
    private String content;
    @Column(name = "IMG")
    private String img;
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_group_id")
    @Autowired
    private ProductGroup productGroup;
    @Column(name = "product_group_id")
    private int productGroupId;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    @Autowired
    private Producer producer;
    @Column(name = "producer_id")
    private int producerId;

    public Product(String productID, String name, Double price, int stork, Double weight, String content, String img, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight = weight;
        this.content = content;
        this.img = img;
        this.description = description;

    }

    public String getProductID() {
        return productID;
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
                ", productGroup=" + productGroup +
                ", productGroupId=" + productGroupId +
                ", producer=" + producer +
                ", producerId=" + producerId +
                '}';
    }

    public Product(String productID, String name, Double price, int stork, Double weight, String content, String img, String description, int productGroupId, int producerId) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight = weight;
        this.content = content;
        this.img = img;
        this.description = description;
        this.productGroupId = productGroupId;
        this.producerId = producerId;
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

    public int getStork() {
        return stork;
    }

    public void setStork(int stork) {
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

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }


    public Product() {
    }
}
