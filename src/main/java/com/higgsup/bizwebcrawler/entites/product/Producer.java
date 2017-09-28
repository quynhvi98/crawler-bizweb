package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
/**
 * Created by viquynh
 */
@Entity
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "producer_id")
    private int producerID;
    @Column(name = "name")
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
