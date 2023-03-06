package com.iyzico.challenge.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentModel {

    private Long id;
    private BigDecimal price;
    private String bankResponse;
}
