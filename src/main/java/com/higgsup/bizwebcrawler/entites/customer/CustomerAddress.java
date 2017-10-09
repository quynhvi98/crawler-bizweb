package com.higgsup.bizwebcrawler.entites.customer;
import javax.persistence.*;
/*
    By viquynh 09/08/2017
 */
@Entity
@Table(name ="customer_address")
public class CustomerAddress extends Person{
    @Id
    @Column(name = "customer_add_id")
    @Override
    public String getId() {
        return super.getId();
    }
    @Column(name = "address_user")
    private String addressUser;
    @Column(name = "name")
    @Override
    public String getFullName() {
        return super.getFullName();
    }
    @Column(name="phone")
    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }
    private String company;
    @Column(name = "zipe_code")
    private String zipeCode;
    @Column(name = "zipe_code")
    public String getZipeCode() {
        return zipeCode;
    }
    @Column(name = "address_user")
    public String getAddressUser() {
        return addressUser;
    }

    private String nation;
    private String city;
    private String district;

    private Customer customer;
    public CustomerAddress() {

    }

    @ManyToOne
    @JoinColumn(name="customer_id",insertable=false, updatable=false ,referencedColumnName = "customer_id", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setZipeCode(String zipeCode) {
        this.zipeCode = zipeCode;
    }
    public CustomerAddress(String id, String addressUser, String firstName, String phoneNumber, String company, String zipeCode, Customer customer, String nation, String city, String district) {
        setId(id);
        this.addressUser = addressUser;
        setFullName(firstName);
        setPhoneNumber(phoneNumber);
        this.company = company;
        this.zipeCode = zipeCode;
        this.customer = customer;
        this.nation = nation;
        this.city = city;
        this.district = district;


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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerAddress) {
            if (((CustomerAddress) obj).getId().equals(this.getId()) &&((CustomerAddress) obj).addressUser.equals(this.addressUser)&&((CustomerAddress) obj).customer.equals(this.customer)&& ((CustomerAddress) obj).getFullName().equals(this.getFullName()) && ((CustomerAddress) obj).getPhoneNumber().equals(this.getPhoneNumber()) && ((CustomerAddress) obj).company.equals(this.company) && ((CustomerAddress) obj).zipeCode.equals(this.zipeCode) && ((CustomerAddress) obj).nation.equals(this.nation) && ((CustomerAddress) obj).city.equals(this.city) && ((CustomerAddress) obj).district.equals(this.district))
                return true;
        } else {
            return false;
        }
        return false;
    }
}
