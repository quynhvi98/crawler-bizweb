package com.higgsup.bizwebcrawler.model.objectproduct;

import javax.persistence.*;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "Producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "producer_ID")
    private int producerID;
    private String name;


    public Producer() {
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Producer(String name) {
        this.name = name;
    }
}
