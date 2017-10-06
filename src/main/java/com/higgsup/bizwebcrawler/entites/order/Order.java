package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 11/08/2017
  */

import javax.persistence.*;

@Entity
@Table(name = "order_product")//order
public class Order {
    private String orderID;
    private String date;
    private String statusPayment;
    private String statusDelivery;
    private Double totalBill;
    private Double totalWeight;
    private Double feeDelivery;
    private String customerID;
    private int paymentID;

    public Order(String orderID, String date, String statusPayment, String statusDelivery, Double totalBill, Double totalWeight, Double feeDelivery, String customerID, int paymentID) {
        this.orderID = orderID;
        this.date = date;
        this.statusPayment = statusPayment;
        this.statusDelivery = statusDelivery;
        this.totalBill = totalBill;
        this.totalWeight = totalWeight;
        this.feeDelivery = feeDelivery;
        this.customerID = customerID;
        this.paymentID = paymentID;
    }

    @Id
    @Column(name = "order_id")
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "status_payment", nullable = false)
    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
    }

    @Column(name = "status_delivery", nullable = false)
    public String getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(String statusDelivery) {
        this.statusDelivery = statusDelivery;
    }

    @Column(name = "total_bill", nullable = false)
    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    @Column(name = "total_weight")
    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Column(name = "fee_delivery")
    public Double getFeeDelivery() {
        return feeDelivery;
    }

    public void setFeeDelivery(Double feeDelivery) {
        this.feeDelivery = feeDelivery;
    }

    @Column(name = "customer_id", nullable = true)
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Column(name = "payment_id", nullable = false)
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
        return orderID.hashCode() + date.hashCode() + statusPayment.hashCode() - statusDelivery.hashCode() - totalBill.hashCode() - totalWeight.hashCode() + feeDelivery.hashCode() + customerID.hashCode() + paymentID;

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
                ", statusPaymen='" + statusPayment + '\'' +
                ", statusDelivery='" + statusDelivery + '\'' +
                ", totalBill=" + totalBill +
                ", totalWeight=" + totalWeight +
                ", feeDelivery=" + feeDelivery +
                ", customerID='" + customerID + '\'' +
                ", paymentID=" + paymentID +
                '}';
    }
}
