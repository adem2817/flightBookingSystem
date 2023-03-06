package com.iyzico.challenge.service.unitTest;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.model.FlightModel;
import com.iyzico.challenge.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    @Rollback(value = false)
    public void add() {
        Flight flight = new Flight();
        flight.setName("name");
        flight.setDescription("desc");
        flight.setPrice(new BigDecimal(500));
        flightService.save(flight);
        assertTrue(flight.getId() > 0);
    }

    @Test
    //@Sql("/db/sql/insert-data.sql")
    public void b_list() {
        assertTrue(flightService.list().size() > 0);
    }

    @Test
    @Rollback(value = false)
    //@Sql("/db/sql/insert-data.sql")
    public void c_update() {
        Flight flight = flightService.getById(1L).get();
        FlightModel updateModel = new FlightModel();
        updateModel.setPrice(new BigDecimal(400));
        updateModel.setName("Giresun");
        flightService.update(flight, updateModel, new ArrayList<>());
        assertEquals(flight.getName(), updateModel.getName());
    }

    @Test
    //@Sql("/db/sql/insert-data.sql")
    public void d_delete() {
        Flight flight = flightService.getById(1L).get();
        flightService.delete(flight);
        Boolean f1 = flightService.getById(1L).isEmpty();
        assertEquals(f1, true);
    }
}
