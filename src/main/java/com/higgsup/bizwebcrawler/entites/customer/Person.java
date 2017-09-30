package com.higgsup.bizwebcrawler.entites.customer;

/**
 * Created by viquy 9:41 AM 9/7/2017
 */
public class Person {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String passWord;
    private String address;

    public Person() {
    }

    public Person(String id, String firstName, String phoneNumber, String email, String passWord, String address) {
        this.id = id;
        this.fullName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passWord = passWord;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
