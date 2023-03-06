package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.mapper.SeatMapper;
import com.iyzico.challenge.entity.model.SeatModel;
import com.iyzico.challenge.repository.SeatRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final FlightService flightService;
    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, FlightService flightService, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
        this.seatMapper = seatMapper;
    }

    public List<Seat> getByAvailableSeatAndFlightId(Long flightId) {
        return seatRepository.findByAvailableAndFlight_Id(true, flightId);
    }

    public List<Seat> getAll(Long flightId) {
        return seatRepository.findByFlight_Id(flightId);
    }

    public Optional<Seat> getById(Long id, Long flightId) {
        return seatRepository.findByIdAndFlight_Id(id, flightId);
    }

    public List<Seat> getByIdIn(List<Long> ids, Long flightId) {
        return seatRepository.findByIdInAndFlight_Id(ids, flightId);
    }

    public Seat getBySeatNumber(Integer seatNumber, Long flightId) {
        return seatRepository.findBySeatNumberAndFlight_Id(seatNumber, flightId);
    }

    public void save(Seat seat) {
        seatRepository.save(seat);
    }

    public void delete(Seat seat) {
        seatRepository.delete(seat);
    }

    public int reserveSeat(Instant lockUntil, Long seatId, Long flightId) {
        return seatRepository.reserveSeat(lockUntil, seatId, flightId);
    }

    public List<SeatModel> list(Long flightId) {
        List<Seat> seats = this.getAll(flightId);
        List<SeatModel> seatModels = new ArrayList<>();
        seats.forEach(s-> {
            SeatModel seatModel = seatMapper.toSeatModel(s);
            seatModel.setFlight(s.getFlight().getId());
            seatModels.add(seatModel);
        });
        return seatModels;
    }

    public List<SeatModel> availableList(Long flightId) {
        List<Seat> seats =  this.getByAvailableSeatAndFlightId(flightId);
        List<SeatModel> seatModels = new ArrayList<>();
        seats.forEach(s-> {
            SeatModel seatModel = seatMapper.toSeatModel(s);
            seatModel.setFlight(s.getFlight().getId());
            seatModels.add(seatModel);
        });
        return seatModels;
    }

    public void add(Flight flight, SeatModel seatModel) {
        Seat seat = seatMapper.toSeat(seatModel);
        seat.setFlight(flight);
        flight.getSeats().add(seat);
        this.save(seat);
        flightService.save(flight);
    }

    public void remove(Flight flight, Seat seat) {
        flight.getSeats().remove(seat);
        flightService.save(flight);
        seat.setFlight(null);
        this.delete(seat);
    }

    public void update(Seat seat, Flight flight, SeatModel seatModel) {
        seat.setFlight(flight);
        seat.setSeatNumber(seatModel.getSeatNumber());
        seat.setAvailable(seatModel.getAvailable());
        seat.setLockUntil(Instant.now());
        this.save(seat);
    }
}
