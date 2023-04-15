package com.rajeeb.os.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    private String paymentStatus;
    private String transactionId;
    private Integer orderId;
    private double amount;

}
