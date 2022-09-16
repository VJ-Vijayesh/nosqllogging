package com.myservices.nosqllogservice;


import com.myservices.nosqllogservice.controller.NoSqlLoggerController;
import com.myservices.nosqllogservice.model.Logs;
import com.myservices.nosqllogservice.repository.CassandraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class NosqllogserviceApplicationUnitTests {

    @InjectMocks
    NoSqlLoggerController noSqlLoggerController;
    @Mock
    CassandraRepo cassandraRepo;


    @Test
    void ut_getLogsTest() throws Exception{
        List<Logs> logsList= new ArrayList<>();
        logsList.add(new Logs(100,"test info","test obj"));
        logsList.add(new Logs(101,"test info","test obj"));

        Mockito.when(cassandraRepo.findAll())
                .thenReturn(logsList);

        List<Logs> result = noSqlLoggerController.getlogs();
        Assertions.assertEquals(result,logsList);
    }

    @Test
    void ut_getLogTest() throws Exception{
        Logs log=new Logs(100,"test info","test obj");

        Mockito.when(cassandraRepo.findById(100))
                .thenReturn(Optional.of(log));

        Logs result = noSqlLoggerController.getlogs(100);
        Assertions.assertEquals(result,log);
    }
    @Test
    void ut_postLogsTest() throws Exception{
        List<Logs> logsList= new ArrayList<>();
        logsList.add(new Logs(100,"test info message","test obj"));
        logsList.add(new Logs(101,"test info log","test obj"));


        Mockito.when(cassandraRepo.saveAll(logsList))
                .thenReturn(logsList);

        ResponseEntity<String> result = noSqlLoggerController.writelog(logsList);
        Assertions.assertEquals(result.getBody(),"Logs inserted");
    }
}
