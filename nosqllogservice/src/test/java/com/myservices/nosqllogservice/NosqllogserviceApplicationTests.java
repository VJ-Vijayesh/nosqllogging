package com.myservices.nosqllogservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;

@SpringBootTest
@AutoConfigureMockMvc
class NosqllogserviceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getLogsTest() throws Exception{

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/nosqllogging/getlogs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        assert (result.getResponse().getStatus()== HttpServletResponse.SC_OK);
    }

    @Test
    void getLogTest() throws Exception{

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/nosqllogging/getlog/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        assert (result.getResponse().getStatus()== HttpServletResponse.SC_OK);
    }
    @Test
    void postLogsTest() throws Exception{
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/nosqllogging/writelog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":100,\"loginfo\":\"error\",\"logobject\":\"obj100\"}]")
        ).andReturn();
        //validation for less than 10 chars
        assert (result.getResponse().getStatus()==HttpServletResponse.SC_NOT_ACCEPTABLE);

        result = mockMvc.perform(
                MockMvcRequestBuilders.post("/nosqllogging/writelog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":100,\"loginfo\":\"error with more than 10 chars\",\"logobject\":\"obj100\"}]")
        ).andReturn();
        assert (result.getResponse().getStatus()==HttpServletResponse.SC_CREATED);
    }
}
