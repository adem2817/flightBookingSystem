package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.mapper.FlightMapper;
import com.iyzico.challenge.entity.model.FlightModel;
import com.iyzico.challenge.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public Optional<Flight> getById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> getByIdIn(List<Long> ids) {
        return flightRepository.findByIdIn(ids);
    }

    public Flight getByName(String name) {
        return flightRepository.findByName(name);
    }

    public Flight getByDesc(String desc) {
        return flightRepository.findByDescription(desc);
    }

    public List<Flight> getByPrice(BigDecimal price) {
        return flightRepository.findFlightsByPrice(price);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

    public void add(FlightModel flightModel) {
        Flight flight = flightMapper.toFlight(flightModel);
        List<Seat> seats = new ArrayList<>();
        flightModel.getSeats().forEach(s -> {
            seats.add(new Seat(s));
        });
        this.save(flight);
    }

    public List<FlightModel> list(){
        List<Flight> flights = this.getAll();
        List<FlightModel> flightModels = new ArrayList<>();
        flights.forEach(f -> {
            List<Long> seats = new ArrayList<>();
            f.getSeats().forEach(s-> {
                seats.add(s.getId());
            });
            FlightModel fm = flightMapper.toFlightModel(f);
            fm.setSeats(seats);
            flightModels.add(fm);
        });
        return flightModels;
    }

    public void update(Flight flight, FlightModel flightModel, List<Seat> seats) {

        flight.setName(flightModel.getName());
        flight.setDescription(flight.getDescription());
        flight.setPrice(flightModel.getPrice());
        flight.getSeats().clear();
        flight.getSeats().addAll(seats);

        this.save(flight);
    }
}
