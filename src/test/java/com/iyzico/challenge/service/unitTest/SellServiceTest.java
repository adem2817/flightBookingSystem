package com.iyzico.challenge.service.unitTest;

import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SellingModel;
import com.iyzico.challenge.service.SeatService;
import com.iyzico.challenge.service.SellingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class SellServiceTest {

    @Autowired
    private SellingService sellingService;

    @Autowired
    private SeatService seatService;

    private SellingModel sellingModel = new SellingModel(1L, 28L);

    @Sql("/db/sql/insert-data.sql")
    @Test
    public void sell() throws Exception {
        sellingService.sellFlight(sellingModel);
        Seat seat = seatService.getById(28L, 1L).get();
        assertEquals(seat.getAvailable(), false);
    }
}
