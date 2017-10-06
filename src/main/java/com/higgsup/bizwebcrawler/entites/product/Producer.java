package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "producer")
public class Producer{

    private int producerID;

    private String name;
    public Producer() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "producer_id")
    public int getProducerID() {
        return producerID;
    }
    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }
    @Column(name = "name")
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
