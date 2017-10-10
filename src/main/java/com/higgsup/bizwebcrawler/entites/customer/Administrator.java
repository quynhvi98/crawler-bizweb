package com.higgsup.bizwebcrawler.entites.customer;

import javax.persistence.*;

/**
 * Created by viquy 9:55 AM 9/7/2017
 */
@Entity
@Table(name ="administrator")
public class Administrator extends Person {
    @Id
    @Override
    @Column(name ="id", nullable = false)
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
    @Column(name="phone")
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

    public Administrator(String id,String name,String password,String phone,String email,String adminLink, String infoNote) {
       setId(id);
       setFullName(name);
       setPassWord(password);
       setPhoneNumber(phone);
       setEmail(email);
         this.adminLink = adminLink;
        this.infoNote = infoNote;
    }
}
