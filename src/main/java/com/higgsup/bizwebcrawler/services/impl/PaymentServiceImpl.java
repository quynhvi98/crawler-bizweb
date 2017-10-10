package com.higgsup.bizwebcrawler.services.impl;/*
  By Chi Can Em  09-10-2017
 */

import com.higgsup.bizwebcrawler.entites.order.Payment;
import com.higgsup.bizwebcrawler.repositories.PaymentRepo;
import com.higgsup.bizwebcrawler.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentServices {
    @Autowired
    PaymentRepo paymentRepo;
    @Override
    public Integer getIDPaymentFromContent(String content) {
        return paymentRepo.getIDPaymentFromContent(content);
    }

    @Override
    public void save(Payment payment) {
        paymentRepo.save(payment);
    }
}
