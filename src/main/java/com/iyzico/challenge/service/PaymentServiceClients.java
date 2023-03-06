package com.iyzico.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PaymentServiceClients {

    private IyzicoPaymentService iyzicoPaymentService;

    public PaymentServiceClients(IyzicoPaymentService iyzicoPaymentService) {
        this.iyzicoPaymentService = iyzicoPaymentService;
    }

    @Async
    public CompletableFuture<String> call(BigDecimal price) {
        CompletableFuture.runAsync(() -> {
            try {
                iyzicoPaymentService.pay(price);
            } catch(Exception e) {
                log.error("Error::" + e.getMessage());
            }
        }).join();
        return CompletableFuture.completedFuture("success");
    }
}
