package com.higgsup.bizwebcrawler.entites.customer;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by viquy 9:44 AM 9/7/2017
 */
@Entity
@Table(name ="customer")
public class Customer extends Person{
    @Id
    @Column(name = "customer_id")
    @Override
    public String getId() {
        return super.getId();
    }
    @Column(name = "full_name", nullable = false)
    @Override
    public String getFullName() {
        return super.getFullName();
    }
    @Override
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return super.getEmail();
    }
    @Column(name="total_bill", nullable = false)
    private Double totalBill;
    public Customer(String id, String firstName, String phoneNumber, String email, String passWord, String address, Double totalBill) {
        super(id,firstName, phoneNumber, email, passWord, address);
        this.totalBill = totalBill;
    }
    public Customer(String id, String firstName, String email, Double totalBill) {
        setId(id);
        setFullName(firstName);
        setEmail(email);
        this.totalBill = totalBill;
    }
    public Customer() {
    }
    public Double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            if (((Customer) obj).getId().equals(this.getId()) &&((Customer) obj).getFullName().equals(this.getFullName())&& ((Customer) obj).getFullName().equals(this.getFullName()) && ((Customer) obj).getEmail().equals(this.getEmail()) && ((Customer) obj).totalBill.equals(this.totalBill))
                return true;
        } else {
            return false;
        }
        return false;
    }




}
