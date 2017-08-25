package com.higgsup.bizwebcrawler.object.objectorder;/*
    By chicanem 15/08/2017
    */

public class ObjectOrderAddress {
    private int Order_Address_ID;
    private String email;
    private String NameCustomer;
    private String Phone;
    private String Order_Address;
    private String ZipCode;
    private String nation;
    private String city;
    private String district;
    private String PaymentAddress;
    private String order_ID;

    public ObjectOrderAddress(int order_Address_ID, String email, String nameCustomer, String phone, String order_Address, String zipCode, String nation, String city, String district, String paymentAddress, String order_ID) {
        Order_Address_ID = order_Address_ID;
        this.email = email;
        NameCustomer = nameCustomer;
        Phone = phone;
        Order_Address = order_Address;
        ZipCode = zipCode;
        this.nation = nation;
        this.city = city;
        this.district = district;
        PaymentAddress = paymentAddress;
        this.order_ID = order_ID;
    }

    public int getOrder_Address_ID() {
        return Order_Address_ID;
    }

    public void setOrder_Address_ID(int order_Address_ID) {
        Order_Address_ID = order_Address_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameCustomer() {
        return NameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        NameCustomer = nameCustomer;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getOrder_Address() {
        return Order_Address;
    }

    public void setOrder_Address(String order_Address) {
        Order_Address = order_Address;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
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
        return PaymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        PaymentAddress = paymentAddress;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public ObjectOrderAddress() {
    }


}
