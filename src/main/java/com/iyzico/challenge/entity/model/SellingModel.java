package com.iyzico.challenge.entity.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellingModel {

    @NotNull
    private Long flightId;
    @NotNull
    private Long seatId;
}
