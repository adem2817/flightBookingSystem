package com.iyzico.challenge.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatModel {

    private Long id;
    private Integer seatNumber;
    private Boolean available = true;
    private Long flight;
    private Instant lockUntil;
}
