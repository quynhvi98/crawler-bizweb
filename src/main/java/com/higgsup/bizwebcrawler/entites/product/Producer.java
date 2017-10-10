package com.higgsup.bizwebcrawler.entites.product;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by viquynh
 */
@Entity
@Table(name = "producer")
public class Producer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "producer_id")
    private int producerID;

    private String name;
    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private Set<Product> Product;

    public Set<com.higgsup.bizwebcrawler.entites.product.Product> getProduct() {
        return Product;
    }

    public Producer() {
    }

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
