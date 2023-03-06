package com.iyzico.challenge.controller;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.FlightModel;
import com.iyzico.challenge.service.FlightService;
import com.iyzico.challenge.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<?> add (@RequestBody FlightModel flightModel) {

        if (flightService.getByName(flightModel.getName()) != null) {
            log.error("Flight already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight already exist");
        }
        try {
            flightService.add(flightModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Flight is added successfully");
        } catch (Exception e) {
            log.error("Error while adding flight, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while adding flight, " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list () {
        try {
            return ResponseEntity.ok(flightService.list());
        } catch (Exception e) {
            log.error("Error while listing flights, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while listing flights, " + e.getMessage());
        }
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<?> remove (@PathVariable String flightId) {
        Optional<Flight> flight = flightService.getById(Long.parseLong(flightId));
        if (!flight.isPresent()) {
            log.error("Given flight not found");
            return ResponseEntity.notFound().build();
        }
        flightService.delete(flight.get());
        return ResponseEntity.accepted().body("Flight is deleted successsfully");
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<?> update (@PathVariable Long flightId, @RequestBody FlightModel flightModel) {
        Optional<Flight> flightOpt = flightService.getById(flightId);
        if (!flightOpt.isPresent()) {
            log.error("Given flight not found");
            return ResponseEntity.notFound().build();
        }
        try {
            List<Seat> seats = seatService.getByIdIn(flightModel.getSeats(), flightId);
            flightService.update(flightOpt.get(), flightModel, seats);
            return ResponseEntity.accepted().body("Flight is updated successfully");
        } catch (Exception e) {
            log.error("Error while updating flight, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while updating flight, " + e.getMessage());
        }
    }

}
