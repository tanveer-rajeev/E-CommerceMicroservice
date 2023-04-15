package com.rajeeb.os.api.dto;

import com.rajeeb.os.api.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private OrderEntity order;
    private String TransactionId;
    private String deliveryMessage;
    private double amount;
}
