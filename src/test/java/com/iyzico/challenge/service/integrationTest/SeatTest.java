package com.iyzico.challenge.service.integrationTest;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.entity.model.SeatModel;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeatTest extends BaseTest{

    private final String basePath = "/seat";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Sql("/db/sql/insert-data.sql")
    @Test
    public void add() throws Exception {
        String uri = basePath + "/" + 1;
        SeatModel seatModel = new SeatModel();
        seatModel.setSeatNumber(1);
        //seatModel.setLockUntil(Instant.now());
        //seat.setFlight();
        String inputJson = super.mapToJson(seatModel);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Seat is added successfully");
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void b_getList() throws Exception {
        String uri = basePath + "/" + 1;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        SeatModel[] seatList = super.mapFromJson(content, SeatModel[].class);
        assertTrue(seatList.length > 0);
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void c_getAvailableSeatList() throws Exception {
        String uri = basePath + "/available/" + 1;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        SeatModel[] seatList = super.mapFromJson(content, SeatModel[].class);
        assertTrue(seatList.length > 0);
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void d_update() throws Exception {
        String uri = basePath + "/" + 1 + "/" + 1;
        //String uri = basePath + "/" + 1 + "/" + 28;
        SeatModel seatModel = new SeatModel();
        seatModel.setSeatNumber(63);
        //seatModel.setLockUntil(Instant.now());
        //seat.setFlight();
        String inputJson = super.mapToJson(seatModel);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Seat is updated successsfully");
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void e_delete() throws Exception {
        String uri = basePath + "/" + 1 + "/" + 1;
        //String uri = basePath + "/" + 1 + "/" + 28;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Seat is deleted successsfully");
    }
}
