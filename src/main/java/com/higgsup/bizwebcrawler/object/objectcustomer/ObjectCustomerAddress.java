package com.higgsup.bizwebcrawler.object.objectcustomer;

/*
    By chicanem 09/08/2017
    */public class ObjectCustomerAddress {
    private String customerAdd_iD;
    private String addressUser;
    private String name;
    private String phone;
    private String company;
    private String zipeCode;
    private String customer_ID;
    private String nation;
    private String city;
    private String district;

    public String getCustomerAdd_iD() {
        return customerAdd_iD;
    }

    public void setCustomerAdd_iD(String customerAdd_iD) {
        this.customerAdd_iD = customerAdd_iD;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getZipeCode() {
        return zipeCode;
    }

    public void setZipeCode(String zipeCode) {
        this.zipeCode = zipeCode;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
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

    public ObjectCustomerAddress(String customerAdd_iD, String addressUser, String name, String phone, String company, String zipeCode, String customer_ID, String nation, String city, String district) {
        this.customerAdd_iD = customerAdd_iD;
        this.addressUser = addressUser;
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.zipeCode = zipeCode;
        this.customer_ID = customer_ID;
        this.nation = nation;
        this.city = city;
        this.district = district;
    }

    public ObjectCustomerAddress() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectCustomerAddress) {
            if (((ObjectCustomerAddress) obj).customerAdd_iD.equals(this.customerAdd_iD) && ((ObjectCustomerAddress) obj).addressUser.equals(this.addressUser) && ((ObjectCustomerAddress) obj).name.equals(this.name) && ((ObjectCustomerAddress) obj).phone.equals(this.phone) && ((ObjectCustomerAddress) obj).company.equals(this.company) && ((ObjectCustomerAddress) obj).zipeCode.equals(this.zipeCode) && ((ObjectCustomerAddress) obj).customer_ID.equals(this.customer_ID) && ((ObjectCustomerAddress) obj).nation.equals(this.nation) && ((ObjectCustomerAddress) obj).city.equals(this.city) && ((ObjectCustomerAddress) obj).district.equals(this.district))
                return true;
        } else {
            return false;
        }
        return false;
    }
}
