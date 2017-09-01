package com.higgsup.bizwebcrawler.model.order;

import javax.persistence.*;

/*
    By chicanem 11/08/2017
    */
@Entity
@Table(name = "product_order")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_product_id")
    private int orderProductID;
    private Double quantity;
    @Column(name = "product_id")
    private String productID;
    @Column(name = "order_id")
    private String orderID;

    public OrderProduct(Double quantity, String productID, String orderID) {
        this.quantity = quantity;
        this.productID = productID;
        this.orderID = orderID;
    }

    public int getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(int orderProductID) {
        this.orderProductID = orderProductID;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public OrderProduct() {
    }
}
