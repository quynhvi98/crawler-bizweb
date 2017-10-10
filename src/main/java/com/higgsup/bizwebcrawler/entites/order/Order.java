package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 11/08/2017
  */

import com.higgsup.bizwebcrawler.entites.customer.Customer;
import com.higgsup.bizwebcrawler.entites.product.Product;
import com.higgsup.bizwebcrawler.entites.product.ProductCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_product")//order
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderID;
    @Column(name = "date", nullable = false)

    private String date;
    @Column(name = "status_payment", nullable = false)

    private String statusPayment;
    @Column(name = "status_delivery", nullable = false)

    private String statusDelivery;
    @Column(name = "total_bill")

    private Double totalBill;
    @Column(name = "total_weight")

    private Double totalWeight;
    @Column(name = "fee_delivery")

    private Double feeDelivery;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="payment_id")
    private Payment payment;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_order", joinColumns = {
            @JoinColumn(name = "order_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "product_id",
                    nullable = false, updatable = false) })
    private Set<Product> products = new HashSet<Product>(0);
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order() {
    }

    @Override
    public int hashCode() {
        return orderID.hashCode() + date.hashCode() + statusPayment.hashCode() - statusDelivery.hashCode() - totalBill.hashCode() - totalWeight.hashCode() + feeDelivery.hashCode() + customer.getId().hashCode() + payment.getPaymentID();

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


                '}';
    }
}
