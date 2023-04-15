package com.rajeeb.os.api.service;

import ch.qos.logback.core.LayoutBase;
import com.rajeeb.os.api.common.PaymentEntity;
import com.rajeeb.os.api.dto.TransactionRequest;
import com.rajeeb.os.api.dto.TransactionResponse;
import com.rajeeb.os.api.entity.OrderEntity;
import com.rajeeb.os.api.common.UserCredential;
import com.rajeeb.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.identity-service.endpoints.endpoint.uri}")
    private String IDENTITY_URL;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_URL;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) {

        String username = transactionRequest.getUser().getUsername();

        UserCredential user = restTemplate.getForObject(IDENTITY_URL, UserCredential.class, username);
        OrderEntity order = transactionRequest.getOrder();
        order.setCustomerId(user.getId());
        PaymentEntity payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        PaymentEntity paymentResponse = restTemplate.postForObject(PAYMENT_URL, payment, PaymentEntity.class);
//        String message = payment.getPaymentStatus().equals("Success")?"Your payment is successful":"Your payment not done!! Try again";
        orderRepository.save(order);

        return new TransactionResponse(order, paymentResponse.getTransactionId(), "Your Payment has placed", paymentResponse.getAmount());
    }
}
