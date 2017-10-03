package com.higgsup.bizwebcrawler.entites.order;

import javax.persistence.*;

/*
    By chicanem 11/08/2017
    */
@Entity
@Table(name = "product_order")//table product reference table order
public class OrderProduct {

    private int orderProductID;
    private Double quantity;

    private String productID;

    private String orderID;


    public OrderProduct(Double quantity, String productID, String orderID) {
        this.quantity = quantity;
        this.productID = productID;
        this.orderID = orderID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_product_id")
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
    @Column(name = "product_id")
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    @Column(name = "order_id")
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
