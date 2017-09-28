package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 11/08/2017
  */

import javax.persistence.*;

@Entity
@Table(name = "order_product")//order
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderID;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "status_paymen", nullable = false)
    private String statusPaymen;
    @Column(name = "status_delivery", nullable = false)
    private String statusDelivery;
    @Column(name = "total_bill", nullable = false)
    private Double totalBill;
    @Column(name = "total_weight")
    private Double totalWeight;
    @Column(name = "fee_delivery")
    private Double feeDelivery;
    @Column(name = "customer_id", nullable = false)
    private String customerID;
    @Column(name = "payment_id", nullable = false)
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

    public Order() {
    }

    @Override
    public int hashCode() {
        return  orderID.hashCode()+date.hashCode()+statusPaymen.hashCode()-statusDelivery.hashCode()-totalBill.hashCode()-totalWeight.hashCode()+feeDelivery.hashCode()+customerID.hashCode()+paymentID;

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            if (this.orderID.equals(((Order) o).orderID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", statusPaymen='" + statusPaymen + '\'' +
                ", statusDelivery='" + statusDelivery + '\'' +
                ", totalBill=" + totalBill +
                ", totalWeight=" + totalWeight +
                ", feeDelivery=" + feeDelivery +
                ", customerID='" + customerID + '\'' +
                ", paymentID=" + paymentID +
                '}';
    }
}
