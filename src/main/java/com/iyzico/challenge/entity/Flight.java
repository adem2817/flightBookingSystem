package com.iyzico.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "flight"
    )
    private List<Seat> seats = new ArrayList<>();
    @Column
    private BigDecimal price;

    public void addSeat(Seat seat)
    {
        this.seats.add(seat);
    }

    public void removeSeat(Seat seat)
    {
        this.seats.remove(seat);
    }
}
