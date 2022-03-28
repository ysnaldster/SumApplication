package com.example.sumapplication.controller;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.models.SumResponseBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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

    final String host = "http://localhost:%s%s";
    final Gson gson = new Gson();

    @Test
    public void testGetDataRequestAndResponsePathVariable_Given300and400_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 300;
        int numberTwo = 400;
        int idExpected = 1;
        String endpoint = "postSumWithPathVariable";
        String requestUrlPathVariable = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlPathVariable), null);

        SumRequestBody sumRequestBody = new SumRequestBody(idExpected, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(idExpected, idExpected, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(idExpected)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(idExpected)));
    }

    @Test
    public void testGetDataRequestAndResponseRequestBody_Given500and900_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 500;
        int numberTwo = 900;
        int idExpected = 2;
        String endpoint = "postSumWithRequestBody";
        SumRequestBody sumRequestBody = new SumRequestBody(idExpected, numberOne, numberTwo);
        String requestUrlRequestParams = "/sums/requestBody.postSum";
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestParams), sumRequestBody);

        SumResponseBody sumResponseBody = new SumResponseBody(idExpected, idExpected, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(idExpected)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(idExpected)));
    }

    @Test
    public void testGetDataRequestAndResponseRequestParam_Given800and1000_ShouldReturnJsonFromDatabase() throws Exception {
        int numberOne = 800;
        int numberTwo = 1000;
        int idExpected = 3;
        String endpoint = "postSumWithRequestParam";
        String requestUrlRequestParams = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        restTemplate.postForLocation(String.format(host, port, requestUrlRequestParams), null);

        SumRequestBody sumRequestBody = new SumRequestBody(idExpected, numberOne, numberTwo);
        SumResponseBody sumResponseBody = new SumResponseBody(idExpected, idExpected, endpoint,
                numberOne + numberTwo);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(idExpected)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(idExpected)));
    }
}
