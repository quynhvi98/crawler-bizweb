package com.higgsup.bizwebcrawler.model.order;
/*
    By chicanem 11/08/2017
  */
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "order_product")//order
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderID;
    private String date;
    @Column(name = "status_paymen")
    private String statusPaymen;
    @Column(name = "status_delivery")
    private String statusDelivery;
    @Column(name = "total_bill")
    private Double totalBill;
    @Column(name = "total_weight")
    private Double totalWeight;
    @Column(name = "fee_delivery")
    private Double feeDelivery;
    @Column(name = "customer_id")
    private String customerID;
    @Column(name = "payment_id")
    private int paymentID;
    public Order(String orderID, String date, String statusPaymen, String statusDelivery, Double totalBill, Double totalWeight, Double feeDelivery, String customerID, int paymentID) {
        this.orderID = orderID;
        this.date = date;
        this.statusPaymen = statusPaymen;
        this.statusDelivery = statusDelivery;
        this.totalBill = totalBill;
        this.totalWeight = totalWeight;
        this.feeDelivery = feeDelivery;
        this.customerID = customerID;
        this.paymentID = paymentID;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatusPaymen() {
        return statusPaymen;
    }
    public void setStatusPaymen(String statusPaymen) {
        this.statusPaymen = statusPaymen;
    }
    public String getStatusDelivery() {
        return statusDelivery;
    }
    public void setStatusDelivery(String statusDelivery) {
        this.statusDelivery = statusDelivery;
    }
    public Double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }
    public Double getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
    public Double getFeeDelivery() {
        return feeDelivery;
    }
    public void setFeeDelivery(Double feeDelivery) {
        this.feeDelivery = feeDelivery;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public Order() { }

}
