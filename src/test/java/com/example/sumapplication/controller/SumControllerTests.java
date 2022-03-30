package com.example.sumapplication.controller;

import com.example.sumapplication.models.SumResult;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    RequestService requestService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ResponseService responseService;

    final String host = "http://localhost:%s%s";
    final String statusCodeBadRequest = "400 BAD_REQUEST";
    final String statusCode200 = "200 OK";
    final Gson gson = new Gson();

    //Test Bad Requests ->

    @Test
    public void testGetStatusCodeRequestParams_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        int numberOne = 500;
        String numberTwo = "a";
        String requestUrlRequestParams = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        var responseRequestParams = restTemplate.postForEntity
                (String.format(host, port, requestUrlRequestParams), null, String.class);
        assertThat(responseRequestParams.getStatusCode().toString()).isEqualTo(statusCodeBadRequest);
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());
    }

    @Test
    public void testGetStatusCodePathVariable_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        int numberOne = 100;
        String numberTwo = "H";
        String requestUrlPathVariable = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        var responsePathVariable = restTemplate.postForEntity
                (String.format(host, port, requestUrlPathVariable), null, String.class);
        assertThat(responsePathVariable.getStatusCode().toString()).isEqualTo(statusCodeBadRequest);
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());
    }

    @Test
    public void testGetStatusCodeRequestBody_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        int numberOne = 1000;
        String numberTwo = "K";
        String requestUrlRequestBody = "/sums/requestBody.postSum";
        String jsonExpected = String.format("{\"numberOne\":%s,\"numberTwo\":%s}", numberOne, numberTwo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonExpected, headers);
        var responseRequestBody = restTemplate.postForEntity
                (String.format(host, port, requestUrlRequestBody), requestEntity, String.class);
        assertThat(responseRequestBody.getStatusCode().toString()).isEqualTo(statusCodeBadRequest);
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());

    }

    @Test
    public void testGetResultSumRequestParam_GivenTeenAndFifteen_ShouldReturnTwentyFive() throws Exception {
        int numberOne = 10;
        int numberTwo = 15;
        SumResult sumResult = new SumResult(numberOne + numberTwo);
        String requestUrlRequestParams = String.format("/sums/requestParam.postSum?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        var responseRequestParams = restTemplate.postForEntity
                (String.format(host, port, requestUrlRequestParams), null, String.class);
        assertThat(responseRequestParams.getStatusCode().toString()).isEqualTo(statusCode200);
        assertThat(responseRequestParams.getBody()).contains(gson.toJson(sumResult));
    }

    @Test
    public void testGetResultSumPathVariable_GivenFiveAndTeen_ShouldReturnFifteen() throws Exception {
        int numberOne = 5;
        int numberTwo = 10;
        SumResult sumResult = new SumResult(numberOne + numberTwo);
        String requestUrlPathVariable = String.format("/sums/pathVariable.postSum/%s/%s", numberOne, numberTwo);
        var responsePathVariable = restTemplate.postForEntity
                (String.format(host, port, requestUrlPathVariable), null, String.class);
        assertThat(responsePathVariable.getStatusCode().toString()).isEqualTo(statusCode200);
        assertThat(responsePathVariable.getBody()).contains(gson.toJson(sumResult));
    }

    @Test
    public void testGetResultSumRequestBody_GivenElevenAndNine_ShouldReturnTwenty() throws Exception {
        int numberOne = 11;
        int numberTwo = 9;
        SumResult sumResult = new SumResult(numberOne + numberTwo);
        String requestUrlRequestParams = "/sums/requestBody.postSum";
        String jsonExpected = String.format("{\"numberOne\":%s,\"numberTwo\":%s}", numberOne, numberTwo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity(jsonExpected, headers);
        var responseRequestBody = restTemplate.postForEntity
                (String.format(host, port, requestUrlRequestParams), requestEntity, String.class);
        assertThat(responseRequestBody.getStatusCode().toString()).isEqualTo(statusCode200);
        assertThat(responseRequestBody.getBody()).contains(gson.toJson(sumResult));
    }

}
