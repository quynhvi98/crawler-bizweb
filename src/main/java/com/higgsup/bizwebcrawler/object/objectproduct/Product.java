package com.higgsup.bizwebcrawler.object.objectproduct;

/**
 * Created by viquynh
 */
public class Product {
    private String product_ID;
    private String name;
    private float price;
    private int stork;
    private float weight_;
    private String content;
    private String IMG;
    private String description_;
    private int productGroup_iD;
    private int producer_ID;

    public Product(String product_ID, String name, float price, int stork, float weight_, String content, String IMG, String description_, int productGroup_iD, int producer_ID) {
        this.product_ID = product_ID;
        this.name = name;
        this.price = price;
        this.stork = stork;
        this.weight_ = weight_;
        this.content = content;
        this.IMG = IMG;
        this.description_ = description_;
        this.productGroup_iD = productGroup_iD;
        this.producer_ID = producer_ID;
    }

    public Product() {
    }

    public String getProduct_ID() {

        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
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

    public float getWeight_() {
        return weight_;
    }

    public void setWeight_(float weight_) {
        this.weight_ = weight_;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public int getProductGroup_iD() {
        return productGroup_iD;
    }

    public void setProductGroup_iD(int productGroup_iD) {
        this.productGroup_iD = productGroup_iD;
    }

    public int getProducer_ID() {
        return producer_ID;
    }

    public void setProducer_ID(int producer_ID) {
        this.producer_ID = producer_ID;
    }
}
