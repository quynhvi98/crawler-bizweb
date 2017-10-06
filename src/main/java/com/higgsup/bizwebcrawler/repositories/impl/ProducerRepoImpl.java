package com.higgsup.bizwebcrawler.repositories.impl;/*
  By Chi Can Em  10/6/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Producer;
import com.higgsup.bizwebcrawler.repositories.ProducerRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ProducerRepoImpl implements ProducerRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public boolean hasProducerByName(String name) {
        Query query=em.createQuery("select p.producerID from  Producer as p where p.name=:name");
        query.setParameter("name", name);
        return query.getResultList().size()>0;
    }

    @Override
    public Integer getIdProducerByName(String name) {
        Query query=em.createQuery("select p from  Producer as p where p.name=:name");
        query.setParameter("name", name);
        List<Producer> producers=query.getResultList();
        for (Producer producer:producers
             ) {
            return producer.getProducerID();
        }
        return 0;
    }
}
