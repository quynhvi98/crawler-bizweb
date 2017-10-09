package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.product.Producer;
import org.springframework.stereotype.Service;

@Service
public interface ProducerServices {
    boolean hasProducerByName(String name);
    Integer getIdProducerByName(String name);
    void save(Producer producer);
}
