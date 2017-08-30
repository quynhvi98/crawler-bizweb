package com.higgsup.bizwebcrawler.object.objectcustomer;

import org.hibernate.type.FloatType;
import org.springframework.data.annotation.Id;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
    By viquynh 09/08/2017
    */
@Entity
@Table(name ="Customer")

public class ObjectCustomers {
    @Id
    @Column(name = "customer_ID")
    private String customerID;
    private  String fullName;
    private String email;
    private Double totalBill;

    public ObjectCustomers(String customerID, String fullName, String email, Double totalBill) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.totalBill = totalBill;
    }

    public ObjectCustomers() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectCustomers) {
            if (((ObjectCustomers) obj).customerID.equals(this.customerID)&&((ObjectCustomers) obj).fullName.equals(this.fullName)&&((ObjectCustomers) obj).email.equals(this.email)&&((ObjectCustomers) obj).totalBill==this.totalBill)
                return true;
        } else {
            return false;
        }
        return false;
    }
}
