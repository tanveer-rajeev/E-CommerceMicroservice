package com.rajeeb.os.api.controller;
import com.rajeeb.os.api.service.OrderService;
import com.rajeeb.os.api.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public void saveOrder(@RequestBody TransactionRequest transactionRequest){
          orderService.saveOrder(transactionRequest);
    }
}
