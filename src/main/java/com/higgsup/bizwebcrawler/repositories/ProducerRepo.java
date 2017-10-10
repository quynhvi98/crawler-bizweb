package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/5/2017
 */

import com.higgsup.bizwebcrawler.entites.product.Producer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepo extends PagingAndSortingRepository<Producer,Integer>,ProducerRepoCustom { }
