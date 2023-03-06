package com.iyzico.challenge.entity.mapper;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.model.FlightModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T08:50:37+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Flight toFlight(FlightModel flightModel) {
        if ( flightModel == null ) {
            return null;
        }

        Flight flight = new Flight();

        flight.setName( flightModel.getName() );
        flight.setDescription( flightModel.getDescription() );
        flight.setPrice( flightModel.getPrice() );

        return flight;
    }

    @Override
    public FlightModel toFlightModel(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightModel flightModel = new FlightModel();

        flightModel.setId( flight.getId() );
        flightModel.setName( flight.getName() );
        flightModel.setDescription( flight.getDescription() );
        flightModel.setPrice( flight.getPrice() );

        return flightModel;
    }
}
