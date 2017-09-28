package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 29/08/2017
   */
import javax.persistence.*;
@Entity
@Table(name ="paymen")
public class Paymen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int paymentID;
    private String content;
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Paymen(String content) {
        this.content = content;
    }
}
