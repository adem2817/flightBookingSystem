package com.iyzico.challenge.service.integrationTest;

import com.iyzico.challenge.entity.model.SellingModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class SellTest extends BaseTest{

    private final String basePath = "/sell";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Sql("/db/sql/insert-data.sql")
    @Test
    public void sellFlight() throws Exception {
        String uri = basePath;
        SellingModel sellingModel = new SellingModel(1L, 28L);
        String inputJson = super.mapToJson(sellingModel);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Ticket is sold successsfully");
    }
}
