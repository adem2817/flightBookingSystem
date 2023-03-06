package com.iyzico.challenge.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightModel {

    private Long id;
    private String name;
    private String description;
    private List<Long> seats = new ArrayList<>();
    private BigDecimal price;

}
