package com.iyzico.challenge.service.unitTest;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SeatModel;
import com.iyzico.challenge.service.FlightService;
import com.iyzico.challenge.service.SeatService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @Autowired
    private FlightService flightService;

    @Test
    @Sql("/db/sql/insert-data.sql")
    public void add() {
        Flight flight = flightService.getById(1L).get();
        SeatModel seatModel = new SeatModel();
        seatModel.setLockUntil(Instant.now());
        seatService.add(flight, seatModel);
        assertTrue(flight.getSeats().size() > 0);
    }

    @Test
    public void b_list() {
        assertTrue(seatService.list(1L).size() > 0);
    }

    @Test
    public void c_update() {
        Flight flight = flightService.getById(1L).get();
        Seat seat = seatService.getById(1L, 1L).get();
        SeatModel seatModel = new SeatModel();
        seatModel.setSeatNumber(28);
        seatModel.setAvailable(false);
        seatModel.setLockUntil(Instant.now());
        seatService.update(seat, flight, seatModel);
        assertTrue(seat.getSeatNumber().equals(28));
    }

    @Test
    public void d_delete() {
        Seat seat = seatService.getById(1L, 1L).get();
        seatService.delete(seat);
        Boolean isDeleted = seatService.getById(1L, 1L).isEmpty();
        assertEquals(isDeleted, true);
    }
}
