package com.iyzico.challenge.entity.mapper;

import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SeatModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "id", ignore = true)
    Seat toSeat(SeatModel seatModel);

    @Mapping(target = "flight", source = "flight.id")
    SeatModel toSeatModel(Seat seat);
}
