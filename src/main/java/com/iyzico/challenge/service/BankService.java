package com.iyzico.challenge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BankService {

    private BankPaymentRequest tempRequest;

    /**
     * Bank Latency Simulation (avg: 5 seconds)
     */
    public BankPaymentResponse pay(BankPaymentRequest request) {
        try {
            Thread.sleep(5000);
            return new BankPaymentResponse("200");
        } catch (InterruptedException e) {
            return null;
        }
    }
}
