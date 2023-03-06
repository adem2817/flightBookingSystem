package com.iyzico.challenge.service.integrationTest;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.model.FlightModel;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightTest extends BaseTest {

    private final String basePath = "/flight";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void add() throws Exception {
        String uri = basePath;
        Flight flight = new Flight();
        //long rand = (long)new Random().nextInt();
        //idNumber = rand;
        flight.setId(1L);
        flight.setName("Giresun");
        flight.setPrice(new BigDecimal(500));
        flight.setDescription("Giresun");
        String inputJson = super.mapToJson(flight);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Flight is added successfully");
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void b_getList() throws Exception {
        String uri = basePath;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        FlightModel[] flightList = super.mapFromJson(content, FlightModel[].class);
        assertTrue(flightList.length > 0);
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void c_update() throws Exception {
        String uri = basePath + "/" + 1;
        Flight flight = new Flight();
        flight.setName("Urfa");
        flight.setPrice(new BigDecimal(500));
        flight.setDescription("Urfa");
        String inputJson = super.mapToJson(flight);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Flight is updated successfully");
    }

    //@Sql("/db/sql/insert-data.sql")
    @Test
    public void d_delete() throws Exception {
        String uri = basePath + "/" + 1;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Flight is deleted successsfully");
    }
}
