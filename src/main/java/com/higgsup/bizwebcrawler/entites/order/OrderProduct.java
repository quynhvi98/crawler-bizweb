package com.higgsup.bizwebcrawler.entites.order;

import com.higgsup.bizwebcrawler.entites.product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
    By chicanem 11/08/2017
    */
@Entity
@Table(name = "product_order")//table product reference table order
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public int getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(int orderProductID) {
        this.orderProductID = orderProductID;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderProduct) {
            if (this.quantity.equals(((OrderProduct) o).quantity) && productID.equals(((OrderProduct) o).productID) && orderID.equals(((OrderProduct) o).orderID)) {
                return true;
            }
        }
        return false;
    }

}
