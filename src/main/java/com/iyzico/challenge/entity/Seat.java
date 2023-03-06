package com.iyzico.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    private Integer seatNumber;

    private Boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    private Instant lockUntil = Instant.now();

    public Seat(Long id) {
        this.id = id;
    }
}
