package com.example.sumapplication.controller;

import com.example.sumapplication.containers.ConfigurationContainer;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
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

    final String HOST = "http://localhost:%s%s";
    final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    final int WANTED_ID = 1;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM RESPONSES");
        jdbcTemplate.execute("ALTER SEQUENCE RESPONSES_ID_RESPONSE_SEQ RESTART WITH 1");
        jdbcTemplate.execute("DELETE FROM REQUESTS");
        jdbcTemplate.execute("ALTER SEQUENCE REQUESTS_ID_REQUEST_SEQ RESTART WITH 1");
    }

    @Test
    public void testGetDataRequestAndResponsePathVariable_Given300and400_ShouldReturnJsonFromDatabase() throws Exception {
        // Arrange
        int numberOne = 300;
        int numberTwo = 400;
        String endpoint = "postSumWithPathVariable";
        SumRequestBody sumRequestBody = new SumRequestBody(WANTED_ID, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(WANTED_ID, WANTED_ID, endpoint, numberOne + numberTwo);
        // Act
        String request = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(HOST, port, request), null);
        // Assert
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumRequestBody),
                OBJECT_MAPPER.writeValueAsString(requestService.getObjectForIdRequest(WANTED_ID)));
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumResponseBody),
                OBJECT_MAPPER.writeValueAsString(responseService.getObjectForIdResponse(WANTED_ID)));

    }

    @Test
    public void testGetDataRequestAndResponseRequestBody_Given500and900_ShouldReturnJsonFromDatabase() throws Exception {
        // Arrange
        int numberOne = 500;
        int numberTwo = 900;
        String endpoint = "postSumWithRequestBody";
        SumRequestBody sumRequestBody = new SumRequestBody(WANTED_ID, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(WANTED_ID, WANTED_ID, endpoint, numberOne + numberTwo);
        // Act
        String request = "/sums/requestBody.postSum";
        restTemplate.postForLocation(String.format(HOST, port, request), sumRequestBody);
        // Assert
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumRequestBody),
                OBJECT_MAPPER.writeValueAsString(requestService.getObjectForIdRequest(WANTED_ID)));
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumResponseBody),
                OBJECT_MAPPER.writeValueAsString(responseService.getObjectForIdResponse(WANTED_ID)));
    }

    @Test
    public void testGetDataRequestAndResponseRequestParam_Given800and1000_ShouldReturnJsonFromDatabase() throws Exception {
        // Arrange
        int numberOne = 800;
        int numberTwo = 1000;
        String endpoint = "postSumWithRequestParam";
        SumRequestBody sumRequestBody = new SumRequestBody(WANTED_ID, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(WANTED_ID, WANTED_ID, endpoint, numberOne + numberTwo);
        // Act
        String request = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(HOST, port, request), null);
        // Assert
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumRequestBody),
                OBJECT_MAPPER.writeValueAsString(requestService.getObjectForIdRequest(WANTED_ID)));
        assertEquals(OBJECT_MAPPER.writeValueAsString(sumResponseBody),
                OBJECT_MAPPER.writeValueAsString(responseService.getObjectForIdResponse(WANTED_ID)));
    }
}
