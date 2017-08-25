package com.higgsup.bizwebcrawler.object.objectorder;
/*
    By chicanem 11/08/2017
    */

public class ObjectOrderProduct {
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

    public ObjectOrderProduct(Double quantity, String product_ID, String order_ID) {
        this.quantity = quantity;
        this.product_ID = product_ID;
        this.order_ID = order_ID;
    }

    public ObjectOrderProduct() {
    }

    private Double quantity;
    private String product_ID;
    private String order_ID;
}
