package com.iyzico.challenge.repository;

import com.iyzico.challenge.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByIdAndFlight_Id(Long id, Long flightId);

    Seat findBySeatNumberAndFlight_Id(Integer seatNumber, Long flightId);

    List<Seat> findByFlight_Id(Long flightId);

    List<Seat> findByIdInAndFlight_Id(List<Long> ids, Long flightId);

    List<Seat> findByAvailableAndFlight_Id(boolean status, Long flightId);

    @Modifying
    @Query("UPDATE Seat s " +
            "SET s.lockUntil =:lockUntil " +
            "WHERE s.id =:seatId and s.flight.id =:flightId ")
    int reserveSeat(Instant lockUntil, Long seatId, Long flightId);
}
