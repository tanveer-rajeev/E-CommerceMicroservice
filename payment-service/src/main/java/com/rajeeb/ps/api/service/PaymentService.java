package com.rajeeb.ps.api.service;

import com.rajeeb.ps.api.entity.PaymentEntity;
import com.rajeeb.ps.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void doPayment(PaymentEntity paymentEntity) {

        paymentEntity.setPaymentStatus(paymentProcessing());
        paymentEntity.setTransactionId(UUID.randomUUID().toString());
        paymentRepository.save(paymentEntity);
    }

    public String paymentProcessing() {
        return new Random().nextBoolean() ? "Success" : "Failure";
    }

    public PaymentEntity getPaymentHistoryByOrderId(Integer orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
