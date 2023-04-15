package com.rajeeb.os.api.dto;

import com.rajeeb.os.api.common.PaymentEntity;
import com.rajeeb.os.api.entity.OrderEntity;
import com.rajeeb.os.api.common.UserCredential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private UserCredential user;
    private OrderEntity order;
    private PaymentEntity payment;
}
