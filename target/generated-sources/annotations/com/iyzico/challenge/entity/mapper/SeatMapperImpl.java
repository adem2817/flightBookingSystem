package com.iyzico.challenge.entity.mapper;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SeatModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T08:50:37+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class SeatMapperImpl implements SeatMapper {

    @Override
    public Seat toSeat(SeatModel seatModel) {
        if ( seatModel == null ) {
            return null;
        }

        Seat seat = new Seat();

        seat.setSeatNumber( seatModel.getSeatNumber() );
        seat.setAvailable( seatModel.getAvailable() );
        seat.setLockUntil( seatModel.getLockUntil() );

        return seat;
    }

    @Override
    public SeatModel toSeatModel(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatModel seatModel = new SeatModel();

        seatModel.setFlight( seatFlightId( seat ) );
        seatModel.setId( seat.getId() );
        seatModel.setSeatNumber( seat.getSeatNumber() );
        seatModel.setAvailable( seat.getAvailable() );
        seatModel.setLockUntil( seat.getLockUntil() );

        return seatModel;
    }

    private Long seatFlightId(Seat seat) {
        if ( seat == null ) {
            return null;
        }
        Flight flight = seat.getFlight();
        if ( flight == null ) {
            return null;
        }
        Long id = flight.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
