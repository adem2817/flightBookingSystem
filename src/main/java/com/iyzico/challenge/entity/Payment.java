package com.iyzico.challenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal price;
    private String bankResponse;
}
