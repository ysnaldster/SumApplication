package com.example.sumapplication.controller;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.models.SumResponseBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumControllerSaveDataTests {

    @LocalServerPort
    private int port;

    @Autowired
    RequestService requestService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ResponseService responseService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    final String host = "http://localhost:%s%s";
    final Gson gson = new Gson();
    final int startID = 1;
    final SqlParameterSource paramReset = new MapSqlParameterSource();

    @AfterEach
    public void tearDown() {
        namedParameterJdbcTemplate.update("DELETE FROM RESPONSES ", paramReset);
        namedParameterJdbcTemplate.update("ALTER TABLE RESPONSES ALTER COLUMN ID_RESPONSE RESTART WITH " + startID, paramReset);
        namedParameterJdbcTemplate.update("ALTER TABLE RESPONSES ALTER COLUMN ID_REQUEST_FK RESTART WITH " + startID, paramReset);
        namedParameterJdbcTemplate.update("DELETE FROM REQUESTS ", paramReset);
        namedParameterJdbcTemplate.update("ALTER TABLE REQUESTS ALTER COLUMN ID_REQUEST RESTART WITH " + startID, paramReset);
    }

    @Test
    public void testGetDataRequestAndResponsePathVariable_Given300and400_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 300;
        int numberTwo = 400;
        String endpoint = "postSumWithPathVariable";
        String requestUrlPathVariable = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlPathVariable), null);

        SumRequestBody sumRequestBody = new SumRequestBody(startID, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(startID, startID, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(startID)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(startID)));
    }


    @Test
    public void testGetDataRequestAndResponseRequestBody_Given500and900_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 500;
        int numberTwo = 900;
        String endpoint = "postSumWithRequestBody";
        SumRequestBody sumRequestBody = new SumRequestBody(startID, numberOne, numberTwo);
        String requestUrlRequestBody = "/sums/requestBody.postSum";
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestBody), sumRequestBody);

        SumResponseBody sumResponseBody = new SumResponseBody(startID, startID, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(startID)));
        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(startID)));

    }

    @Test
    public void testGetDataRequestAndResponseRequestParam_Given800and1000_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 800;
        int numberTwo = 1000;
        String endpoint = "postSumWithRequestParam";
        String requestUrlRequestParams = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestParams), null);

        SumRequestBody sumRequestBody = new SumRequestBody(startID, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(startID, startID, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(startID)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(startID)));
    }

}
