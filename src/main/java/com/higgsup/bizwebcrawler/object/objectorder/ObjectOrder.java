package com.higgsup.bizwebcrawler.object.objectorder;/*
    By chicanem 11/08/2017
    */

import java.text.SimpleDateFormat;

public class ObjectOrder {
    SimpleDateFormat FormatTimeTheoSQL = new SimpleDateFormat("yyyy-MM-dd");
    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus_Paymen() {
        return status_Paymen;
    }

    public void setStatus_Paymen(String status_Paymen) {
        this.status_Paymen = status_Paymen;
    }

    public String getStatus_Delivery() {
        return status_Delivery;
    }

    public void setStatus_Delivery(String status_Delivery) {
        this.status_Delivery = status_Delivery;
    }

    public Double getTotal_Bill() {
        return total_Bill;
    }

    public void setTotal_Bill(Double total_Bill) {
        this.total_Bill = total_Bill;
    }

    public Double getTotal_Weight() {
        return total_Weight;
    }

    public void setTotal_Weight(Double total_Weight) {
        this.total_Weight = total_Weight;
    }

    public Double getFee_Delivery() {
        return fee_Delivery;
    }

    public void setFee_Delivery(Double fee_Delivery) {
        this.fee_Delivery = fee_Delivery;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getPayment_ID() {
        return payment_ID;
    }

    public void setPayment_ID(int payment_ID) {
        this.payment_ID = payment_ID;
    }



    public ObjectOrder(String order_ID, String date, String status_Paymen, String status_Delivery, Double total_Bill, Double total_Weight, Double fee_Delivery, String customer_ID, int payment_ID) {
        this.order_ID = order_ID;
        this.date=date;
        this.status_Paymen = status_Paymen;
        this.status_Delivery = status_Delivery;
        this.total_Bill = total_Bill;
        this.total_Weight = total_Weight;
        this.fee_Delivery = fee_Delivery;
        this.customer_ID = customer_ID;
        this.payment_ID = payment_ID;

    }

    public ObjectOrder() {
    }

    private String order_ID;
    private String date;
    private String status_Paymen;
    private String status_Delivery;
    private Double total_Bill;
    private Double total_Weight;
    private Double fee_Delivery;
    private String customer_ID;
    private int payment_ID;


}
