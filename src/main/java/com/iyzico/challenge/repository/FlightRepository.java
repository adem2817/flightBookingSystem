package com.iyzico.challenge.repository;

import com.iyzico.challenge.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findByDescription(String desc);

    Flight findByName(String name);

    List<Flight> findFlightsByPrice(BigDecimal price);

    List<Flight> findByIdIn(List<Long> ids);
}
