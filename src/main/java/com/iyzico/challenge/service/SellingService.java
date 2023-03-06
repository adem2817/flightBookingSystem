package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SellingModel;
import com.iyzico.challenge.exception.SeatAlreadyReserved;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@RequiredArgsConstructor
@Service
public class SellingService {

    private final FlightService flightService;
    private final SeatService seatService;
    private final PaymentServiceClients paymentServiceClients;

    @Transactional
    public void sellFlight(SellingModel sellingModel) throws Exception {
        Instant now = Instant.now();

        Optional<Seat> seatOpt = seatService.getById(sellingModel.getSeatId(), sellingModel.getFlightId());
        Optional<Flight> flightOptional = flightService.getById(sellingModel.getFlightId());
        if (flightOptional.isPresent() && seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            Flight flight = flightOptional.get();
            if ((seat.getLockUntil() != null) && now.isBefore(seat.getLockUntil())) {
                throw new SeatAlreadyReserved(seat.getId() + "");
            }

            int result = seatService.reserveSeat(now.plus(5, MINUTES), seat.getId(), sellingModel.getFlightId());

            if (result == 0)
                throw new SeatAlreadyReserved(seat.getId() + "");

            seat.setAvailable(false);
            seatService.save(seat);
            paymentServiceClients.call(flight.getPrice());
        }
    }
}
