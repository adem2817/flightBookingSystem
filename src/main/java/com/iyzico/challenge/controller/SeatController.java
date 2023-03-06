package com.iyzico.challenge.controller;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SeatModel;
import com.iyzico.challenge.service.FlightService;
import com.iyzico.challenge.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;
    private final FlightService flightService;

    @GetMapping("/{flightId}")
    public ResponseEntity<?> list(@PathVariable Long flightId) {
        try {
            return ResponseEntity.ok(seatService.list(flightId));
        } catch (Exception e) {
            log.error("Error while listing seats, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while listing seats, " + e.getMessage());
        }
    }

    @GetMapping("/available/{flightId}")
    public ResponseEntity<?> availableList(@PathVariable Long flightId) {
        try {
            return ResponseEntity.ok(seatService.availableList(flightId));
        } catch (Exception e) {
            log.error("Error while listing seats, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while listing seats, " + e.getMessage());
        }
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<?> add(@PathVariable Long flightId, @RequestBody SeatModel seatModel) {
        Optional<Flight> flightOpt = flightService.getById(flightId);
        if (!flightOpt.isPresent()) {
            log.error("Given flight not found");
            return ResponseEntity.notFound().build();
        }
        try {
            seatService.add(flightOpt.get(), seatModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Seat is added successfully");
        } catch (Exception e) {
            log.error("Error while adding seat {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while adding seat, " + e.getMessage());
        }
    }

    @DeleteMapping("/{flightId}/{seatId}")
    public ResponseEntity<?> remove (@PathVariable Long flightId, @PathVariable Long seatId) {
        Optional<Seat> seatOpt = seatService.getById(seatId, flightId);
        Optional<Flight> flightOpt = flightService.getById(flightId);
        if (!seatOpt.isPresent())
            return ResponseEntity.notFound().build();
        else {
            seatService.remove(flightOpt.get(), seatOpt.get());
            return ResponseEntity.accepted().body("Seat is deleted successsfully");
        }
    }

    @PutMapping("/{flightId}/{seatId}")
    public ResponseEntity<?> update (@PathVariable Long flightId, @PathVariable Long seatId, @RequestBody SeatModel seatModel) {
        Optional<Seat> seatOpt = seatService.getById(seatId, flightId);
        Optional<Flight> flightOpt = flightService.getById(flightId);
        if (!seatOpt.isPresent() || !flightOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            seatService.update(seatOpt.get(), flightOpt.get(), seatModel);
        }
        return ResponseEntity.accepted().body("Seat is updated successsfully");
    }
}
