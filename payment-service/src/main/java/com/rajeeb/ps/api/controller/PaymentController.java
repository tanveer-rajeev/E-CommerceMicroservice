package com.rajeeb.ps.api.controller;

import com.rajeeb.ps.api.entity.PaymentEntity;
import com.rajeeb.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public PaymentEntity doPayment(@RequestBody PaymentEntity paymentEntity){
        paymentService.doPayment(paymentEntity);
        return paymentEntity;
    }

    @GetMapping("/{orderId}")
    public PaymentEntity getPaymentHistoryByOrderId(@PathVariable Integer orderId){
        return paymentService.getPaymentHistoryByOrderId(orderId);
    }
}
