package com.higgsup.bizwebcrawler.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by viquy 10:12 AM 9/7/2017
 */

@Entity
@Table(name="managers")
public class Manager extends Person {
    @Id
    @Column(name="manager_id")
    @Override
    public String getId() {
        return super.getId();
    }
    @Column(name="first_name")
    @Override
    public String getFullName() {
        return super.getFullName();
    }
    @Column(name="pass_word")
    @Override
    public String getPassWord() {
        return super.getPassWord();
    }
    @Column(name="phone")
    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    @Column(name="link")
    private String managerLink;
    @Column(name="info_note")
    private String infoNote;
    @Column(name="id")
    private String adminID;//id Administrator
    public Manager(){}
    public Manager(String id, String firstName, String phoneNumber, String email, String passWord, String address, String managerLink, String infoNote, String adminID) {
        super(id, firstName, phoneNumber, email, passWord, address);
        this.managerLink = managerLink;
        this.infoNote = infoNote;
        this.adminID=adminID;
    }
}
