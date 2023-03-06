package com.iyzico.challenge.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SeatAlreadyReserved extends Exception {

    public SeatAlreadyReserved(String str) {
        super(str + " numbered seat already reserved");
    }
}
