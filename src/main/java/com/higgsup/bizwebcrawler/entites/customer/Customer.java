package com.higgsup.bizwebcrawler.entites.customer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

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
    private Double totalBill;
    private Set<CustomerAddress> customerAddress;

    public Customer() { }

    public Customer(String id, String firstName, String email, Double totalBill) {
        setId(id);
        setFullName(firstName);
        setEmail(email);
        this.totalBill = totalBill;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public Set<CustomerAddress> getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(Set<CustomerAddress> customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Column(name="total_bill", nullable = false)
    public Double getTotalBill() {
        return totalBill;
    }
    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            if (((Customer) obj).getId().equals(this.getId()) &&((Customer) obj).getFullName().equals(this.getFullName())&& ((Customer) obj).getEmail().equals(this.getEmail())
                    && ((Customer) obj).getEmail().equals(this.getEmail()) && ((Customer) obj).totalBill.equals(this.totalBill))
                return true;
        } else {
            return false;
        }
        return false;
    }
}
