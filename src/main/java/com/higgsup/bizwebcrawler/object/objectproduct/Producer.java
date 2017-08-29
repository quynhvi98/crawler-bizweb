package com.higgsup.bizwebcrawler.object.objectproduct;

/**
 * Created by viquynh
 */

public class Producer {
    private int producer_ID;

    private String name;

    public Producer() {
    }

    public Producer(int producer_ID, String name) {
        this.producer_ID = producer_ID;
        this.name = name;
    }

    public int getProducer_ID() {
        return producer_ID;
    }

    public void setProducer_ID(int producer_ID) {
        this.producer_ID = producer_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
