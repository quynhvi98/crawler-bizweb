package com.higgsup.bizwebcrawler.object.objectcustomer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
    By chicanem 09/08/2017
    */
@Entity
@Table(name ="Customer")
public class ObjectCustomerAddress {
    @Id
    @Column(name = "customerAdd_id")
    private String customerAddID;
    private String addressUser;
    private String name;
    private String phone;
    private String company;
    private String zipeCode;
    private String nation;
    private String city;
    private String district;
    @Column (name = "customer_ID")
    private String customerID;

    public ObjectCustomerAddress(String customerAddID, String addressUser, String name, String phone, String company, String zipeCode, String nation, String city, String district, String customerID) {
        this.customerAddID = customerAddID;
        this.addressUser = addressUser;
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.zipeCode = zipeCode;
        this.nation = nation;
        this.city = city;
        this.district = district;
        this.customerID = customerID;
    }

    public ObjectCustomerAddress() {
    }

    public String getCustomerAddID() {
        return customerAddID;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

    public String getZipeCode() {
        return zipeCode;
    }

    public String getNation() {
        return nation;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerAddID(String customerAddID) {
        this.customerAddID = customerAddID;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setZipeCode(String zipeCode) {
        this.zipeCode = zipeCode;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectCustomerAddress) {
            if (((ObjectCustomerAddress) obj).customerAddID.equals(this.customerAddID) && ((ObjectCustomerAddress) obj).name.equals(this.name) && ((ObjectCustomerAddress) obj).name.equals(this.name) && ((ObjectCustomerAddress) obj).phone.equals(this.phone) && ((ObjectCustomerAddress) obj).company.equals(this.company) && ((ObjectCustomerAddress) obj).zipeCode.equals(this.zipeCode) && ((ObjectCustomerAddress) obj).customerID.equals(this.customerID) && ((ObjectCustomerAddress) obj).nation.equals(this.nation) && ((ObjectCustomerAddress) obj).city.equals(this.city) && ((ObjectCustomerAddress) obj).district.equals(this.district))
                return true;
        } else {
            return false;
        }
        return false;
    }
}
