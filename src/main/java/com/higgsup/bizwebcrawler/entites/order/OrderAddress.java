package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 15/08/2017
  */
import javax.persistence.*;
@Entity
@Table(name = "order_address")
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_address_id")
    private int orderAddressID;
    @Column(name = "email")
    private String email;
    @Column(name = "namecustomer", nullable = false)
    private String nameCustomer;
    @Column(name = "phone")
    private String phone;
    @Column(name = "order_address_content", nullable = false)
    private String orderAddress;
    @Column(name = "zipcode")
    private String zipCode;
    @Column(name = "nation")
    private String nation;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "payment_address")
    private String paymentAddress;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
    public OrderAddress(int orderAddressID, String email, String nameCustomer, String phone, String orderAddress, String zipCode, String nation, String city, String district, String paymentAddress, Order order) {
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
        this.order = order;
    }
    public OrderAddress( String email, String nameCustomer, String phone, String orderAddress, String zipCode, String nation, String city, String district, String paymentAddress,Order order) {
        this.email = email;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.orderAddress = orderAddress;
        this.zipCode = zipCode;
        this.nation = nation;
        this.city = city;
        this.district = district;
        this.paymentAddress = paymentAddress;
        this.order = order;
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
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof OrderAddress){
            if(this.order.getOrderID().equals(((OrderAddress) o).getOrder().getOrderID())){
                return true;
            }
        }
        return false;
    }

    public OrderAddress() { }
}
