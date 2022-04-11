package com.example.sumapplication.controller;

import com.example.sumapplication.containers.ConfigurationContainer;
import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.models.SumResponseBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumControllerSaveDataTests extends ConfigurationContainer {

    @LocalServerPort
    private int port;

    @Autowired
    RequestService requestService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ResponseService responseService;

    final String host = "http://localhost:%s%s";
    final ObjectMapper objectMapper = new ObjectMapper();

    final int wantedId = 1;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @AfterEach
    public void tearDown() {

        jdbcTemplate.execute("DELETE FROM RESPONSES");
        jdbcTemplate.execute("ALTER SEQUENCE RESPONSES_ID_RESPONSE_SEQ RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE RESPONSES_ID_REQUEST_FK_SEQ RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE REQUESTS_ID_REQUEST_SEQ RESTART WITH 1");
        jdbcTemplate.execute("DELETE FROM REQUESTS");
    }

    @Test
    public void testGetDataRequestAndResponsePathVariable_Given300and400_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 300;
        int numberTwo = 400;
        String endpoint = "postSumWithPathVariable";
        String requestUrlPathVariable = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlPathVariable), null);

        SumRequestBody sumRequestBody = new SumRequestBody(wantedId, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(wantedId, wantedId, endpoint, numberOne + numberTwo);

        assertEquals(objectMapper.writeValueAsString(sumRequestBody),
                objectMapper.writeValueAsString(requestService.getObjectForIdRequest(wantedId)));
        assertEquals(objectMapper.writeValueAsString(sumResponseBody),
                objectMapper.writeValueAsString(responseService.getObjectForIdResponse(wantedId)));

    }


    @Test
    public void testGetDataRequestAndResponseRequestBody_Given500and900_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 500;
        int numberTwo = 900;
        String endpoint = "postSumWithRequestBody";
        SumRequestBody sumRequestBody = new SumRequestBody(wantedId, numberOne, numberTwo);
        String requestUrlRequestBody = "/sums/requestBody.postSum";
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestBody), sumRequestBody);

        SumResponseBody sumResponseBody = new SumResponseBody(wantedId, wantedId, endpoint, numberOne + numberTwo);

        assertEquals(objectMapper.writeValueAsString(sumRequestBody),
                objectMapper.writeValueAsString(requestService.getObjectForIdRequest(wantedId)));
        assertEquals(objectMapper.writeValueAsString(sumResponseBody),
                objectMapper.writeValueAsString(responseService.getObjectForIdResponse(wantedId)));
    }

    @Test
    public void testGetDataRequestAndResponseRequestParam_Given800and1000_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 800;
        int numberTwo = 1000;
        String endpoint = "postSumWithRequestParam";
        String requestUrlRequestParams = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestParams), null);

        SumRequestBody sumRequestBody = new SumRequestBody(wantedId, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(wantedId, wantedId, endpoint, numberOne + numberTwo);

        assertEquals(objectMapper.writeValueAsString(sumRequestBody),
                objectMapper.writeValueAsString(requestService.getObjectForIdRequest(wantedId)));
        assertEquals(objectMapper.writeValueAsString(sumResponseBody),
                objectMapper.writeValueAsString(responseService.getObjectForIdResponse(wantedId)));
    }

}
