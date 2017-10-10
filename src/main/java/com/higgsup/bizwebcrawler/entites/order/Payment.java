package com.higgsup.bizwebcrawler.entites.order;
/*
    By chicanem 29/08/2017
   */
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int paymentID;
    private String content;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private Set<Order> order;

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

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
    public Payment(String content) {
        this.content = content;
    }

    public Payment() {
    }
}
