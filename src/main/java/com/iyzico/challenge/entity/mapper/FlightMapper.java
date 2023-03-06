package com.iyzico.challenge.entity.mapper;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.model.FlightModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface FlightMapper {


    @Mapping (target = "seats",  ignore = true)
    @Mapping (target = "id", ignore = true)
    Flight toFlight(FlightModel flightModel);

    @Mapping (target = "seats", ignore = true)
    FlightModel toFlightModel(Flight flight);

}