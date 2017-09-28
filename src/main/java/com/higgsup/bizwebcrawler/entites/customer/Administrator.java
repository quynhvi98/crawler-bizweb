package com.higgsup.bizwebcrawler.entites.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by viquy 9:55 AM 9/7/2017
 */
@Entity
@Table(name ="administrator")
public class Administrator extends Person {
    @Id
    @Override
    public String getId() {
        return super.getId();
    }
    @Column(name ="name", nullable = false)
    @Override
    public String getFullName() {
        return super.getFullName();
    }
    @Column(name="pass_word", nullable = false)
    @Override
    public String getPassWord() {
        return super.getPassWord();
    }
    @Column(name="phone", nullable = false)
    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }
    @Column(name="email", nullable = false)
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    @Column(name="link")
    private String adminLink;
    @Column(name="info_note")
    private String infoNote;
    public Administrator() {
    }
    public Administrator(String id, String firstName, String phoneNumber, String email, String passWord, String address, String adminLink, String infoNote) {
        super(id, firstName, phoneNumber, email, passWord, address);
        this.adminLink = adminLink;
        this.infoNote = infoNote;
    }

    public String getAdminLink() {
        return adminLink;
    }
    public void setAdminLink(String adminLink) {
        this.adminLink = adminLink;
    }
    public String getInfoNote() {
        return infoNote;
    }
    public void setInfoNote(String infoNote) {
        this.infoNote = infoNote;
    }
}
