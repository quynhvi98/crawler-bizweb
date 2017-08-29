package com.higgsup.bizwebcrawler.object.objectorder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
    By chicanem 11/08/2017
    */
@Entity
@Table(name = "Order_Product")
public class ObjectOrderProduct {
    @Id
    private int order_product_ID;
    private Double quantity;
    private String product_ID;
    private String order_ID;

    public ObjectOrderProduct(int order_product_ID, Double quantity, String product_ID, String order_ID) {
        this.order_product_ID = order_product_ID;
        this.quantity = quantity;
        this.product_ID = product_ID;
        this.order_ID = order_ID;
    }

    public ObjectOrderProduct(Double quantity, String product_ID, String order_ID) {
        this.quantity = quantity;
        this.product_ID = product_ID;
        this.order_ID = order_ID;
    }

    public int getOrder_product_ID() {
        return order_product_ID;
    }

    public void setOrder_product_ID(int order_product_ID) {
        this.order_product_ID = order_product_ID;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public ObjectOrderProduct() {
    }
}
