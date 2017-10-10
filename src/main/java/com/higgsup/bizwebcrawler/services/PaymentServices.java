package com.higgsup.bizwebcrawler.services;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentServices {
    Integer getIDPaymentFromContent(String content);
    void save(Payment payment);
}
