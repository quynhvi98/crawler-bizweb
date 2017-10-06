package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  10/6/2017
 */

import com.higgsup.bizwebcrawler.entites.customer.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepo extends PagingAndSortingRepository<Administrator,String> {
    @Query(value = "SELECT * FROM administrator WHERE id=:id", nativeQuery = true)
    Administrator getAdministrator(@Param("id") String id);

}
