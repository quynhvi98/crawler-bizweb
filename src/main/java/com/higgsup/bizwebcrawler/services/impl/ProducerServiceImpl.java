package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.Producer;
import com.higgsup.bizwebcrawler.repositories.ProducerRepo;
import com.higgsup.bizwebcrawler.services.ProducerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerServices {
    @Autowired
    ProducerRepo producerRepo;
    @Override
    public boolean hasProducerByName(String name) {
        return producerRepo.hasProducerByName(name);
    }

    @Override
    public Integer getIdProducerByName(String name) {
        return producerRepo.getIdProducerByName(name);
    }

    @Override
    public void save(Producer producer) {
        producerRepo.save(producer);
    }
}
