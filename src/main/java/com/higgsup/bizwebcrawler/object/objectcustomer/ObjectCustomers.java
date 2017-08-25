package com.higgsup.bizwebcrawler.object.objectcustomer;

/*
    By chicanem 09/08/2017
    */
public class ObjectCustomers {
    public ObjectCustomers(String customer_ID, String fullName, String email, double totalBill) {
        this.customer_ID = customer_ID;
        this.fullName = fullName;
        this.email = email;
        this.totalBill = totalBill;
    }

    public ObjectCustomers() {
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
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

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    private String customer_ID;
    private String fullName;
    private String email;
    private double totalBill;
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectCustomers) {
            if (((ObjectCustomers) obj).customer_ID.equals(this.customer_ID)&&((ObjectCustomers) obj).fullName.equals(this.fullName)&&((ObjectCustomers) obj).email.equals(this.email)&&((ObjectCustomers) obj).totalBill==this.totalBill)
                return true;
        } else {
            return false;
        }
        return false;
    }
}
