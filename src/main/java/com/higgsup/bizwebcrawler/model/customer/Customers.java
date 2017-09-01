package com.higgsup.bizwebcrawler.model.customer;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/*
    By viquynh 09/08/2017
 */
@Entity
@Table(name ="customer")
public class Customers {
    @Id
    @Column(name = "customer_id")
    private String customerID;
    @Column(name = "full_name")
    private  String fullName;
    private String email;
    @Column(name = "total_bill")
    private Double totalBill;

    public Customers(String customerID, String fullName, String email, Double totalBill) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.totalBill = totalBill;
    }

    public Customers() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customers) {
            if (((Customers) obj).customerID.equals(this.customerID)&&((Customers) obj).fullName.equals(this.fullName)&&((Customers) obj).email.equals(this.email)&&((Customers) obj).totalBill==this.totalBill)
                return true;
        } else {
            return false;
        }
        return false;
    }
}
