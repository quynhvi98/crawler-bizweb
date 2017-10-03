package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 15/08/2017
  */
import javax.persistence.*;
@Entity
@Table(name = "order_address")
public class OrderAddress {

    private int orderAddressID;
    private String email;

    private String nameCustomer;

    private String phone;

    private String orderAddress;

    private String zipCode;
    private String nation;
    private String city;
    private String district;

    private String paymentAddress;

    private String orderID;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_address_id")
    public int getOrderAddressID() {
        return orderAddressID;
    }
    public void setOrderAddressID(int orderAddressID) {
        this.orderAddressID = orderAddressID;
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "namecustomer", nullable = false)
    public String getNameCustomer() {
        return nameCustomer;
    }
    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "order_address_content", nullable = false)
    public String getOrderAddress() {
        return orderAddress;
    }
    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }
    @Column(name = "zipcode")
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    @Column(name = "nation")
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    @Column(name = "city")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    @Column(name = "payment_address")
    public String getPaymentAddress() {
        return paymentAddress;
    }
    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }
    @Column(name = "order_id")
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public OrderAddress(int orderAddressID, String email, String nameCustomer, String phone, String orderAddress, String zipCode, String nation, String city, String district, String paymentAddress, String orderID) {
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
    public OrderAddress( String email, String nameCustomer, String phone, String orderAddress, String zipCode, String nation, String city, String district, String paymentAddress, String orderID) {
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

    @Override
    public String toString() {
        return "OrderAddress{" +
                "orderAddressID=" + orderAddressID +
                ", email='" + email + '\'' +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", phone='" + phone + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", nation='" + nation + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", paymentAddress='" + paymentAddress + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof OrderAddress){
            if(this.orderID.equals(((OrderAddress) o).orderID)){
                return true;
            }
        }
        return false;
    }

    public OrderAddress() { }
}
