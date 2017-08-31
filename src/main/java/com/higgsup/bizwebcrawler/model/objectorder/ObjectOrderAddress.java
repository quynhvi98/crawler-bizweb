package com.higgsup.bizwebcrawler.model.objectorder;/*
    By chicanem 15/08/2017
    */

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "Order_Address")
public class ObjectOrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Order_Address_ID")
    private int orderAddressID;
    private String email;
    @Column(name = "NameCustomer")
    private String nameCustomer;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Order_Address")
    private String orderAddress;
    @Column(name = "ZipCode")
    private String zipCode;
    private String nation;
    private String city;
    private String district;
    @Column(name = "PaymentAddress")
    private String paymentAddress;
    @Column(name = "order_ID")
    private String orderID;

    public int getOrderAddressID() {
        return orderAddressID;
    }

    public void setOrderAddressID(int orderAddressID) {
        this.orderAddressID = orderAddressID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public ObjectOrderAddress(int orderAddressID, String email, String nameCustomer, String phone, String orderAddress, String zipCode, String nation, String city, String district, String paymentAddress, String orderID) {

        this.orderAddressID = orderAddressID;
        this.email = email;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.orderAddress = orderAddress;
        this.zipCode = zipCode;
        this.nation = nation;
        this.city = city;
        this.district = district;
        this.paymentAddress = paymentAddress;
        this.orderID = orderID;
    }

    public ObjectOrderAddress() {
    }


}
