package com.higgsup.bizwebcrawler.repositories;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public  interface PaymentRepo extends PagingAndSortingRepository<Payment,Integer> {
    @Query(value = "SELECT payment.paymentID FROM Payment AS payment WHERE payment.content=:content")
    Integer getIDPaymentFromContent(@Param("content") String content);
}
