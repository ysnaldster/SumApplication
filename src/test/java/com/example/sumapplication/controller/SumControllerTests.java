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

    @Test
    public void testGetDataRequestAndResponseRequestParam_GivenNumberOneIs300AndNumberTwoIs400_ShouldReturnJsonDataBase() throws Exception {
        Gson gson = new Gson();
        String requestUrlRequestParams = String.format("/sumApp/sumRequestParam?numberOne=%s&numberTwo=%s", 800, 1000);
        restTemplate.postForLocation(String.format("http://localhost:%s%s", port, requestUrlRequestParams), null);

        SumRequestBody sumRequestBody = new SumRequestBody(2, 800, 1000);
        SumResponseBody sumResponseBody = new SumResponseBody(2, 2, "getSumWithRequestParam",
                800 + 1000);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(2)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(2)));
    }

    @Test
    public void testGetDataRequestAndResponsePathVariable_GivenNumberOneIs300AndNumberTwoIs400_ShouldReturnJsonDataBase() throws Exception {
        Gson gson = new Gson();
        String requestUrlPathVariable = String.format("/sumApp/sumPathVariable/%s/%s", 300, 400);
        restTemplate.postForLocation(String.format("http://localhost:%s%s", port, requestUrlPathVariable), null);

        SumRequestBody sumRequestBody = new SumRequestBody(1, 300, 400);
        SumResponseBody sumResponseBody = new SumResponseBody(1, 1, "getSumWithPathVariable",
                300 + 400);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(1)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(1)));
    }

  /*  @Test
    public void testGetDataRequestAndResponseRequestBody_GivenNumberOneIs300AndNumberTwoIs400_ShouldReturnJsonDataBase() throws Exception {
        Gson gson = new Gson();
        SumRequestBody sumRequestBody = new SumRequestBody(1, 300, 400);
        String requestUrlRequestParams = "/sumApp/sumRequestBody";
        restTemplate.postForLocation(String.format("http://localhost:%s%s", port, requestUrlRequestParams), sumRequestBody);

        SumResponseBody sumResponseBody = new SumResponseBody(1, 1, "getSumWithRequestBody",
                300 + 400);

        assertEquals(gson.toJson(sumRequestBody), gson.toJson(requestService.getObjectForIdRequest(1)));
        assertEquals(gson.toJson(sumResponseBody), gson.toJson(responseService.getObjectForIdResponse(1)));
    }*/

    //TEST FOR CODE BAD REQUESTS

    @Test
    public void testGetStatusCodeRequestParams_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        String requestUrlRequestParams = String.format("/sumApp/sumRequestParam?numberOne=%s&numberTwo=%s", 300, "a");
        var responseRequestParams = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlRequestParams), null, String.class);
        assertThat(responseRequestParams.getStatusCode().toString()).isEqualTo("400 BAD_REQUEST");
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());
    }

    @Test
    public void testGetStatusCodePathVariable_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        String requestUrlPathVariable = String.format("/sumApp/sumPathVariable/%s/%s", 300, "a");
        var responsePathVariable = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlPathVariable), null, String.class);
        assertThat(responsePathVariable.getStatusCode().toString()).isEqualTo("400 BAD_REQUEST");
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());
    }

    @Test
    public void testGetStatusCodeRequestBody_GivenNumberOneIsIntegerAndNumberTwoIsString_Should400BadRequest() throws Exception {
        String requestUrlRequestParams = "/sumApp/sumRequestBody";
        String jsonExpected = String.format("{\"numberOne\":%s,\"numberTwo\":%s}", 10, "a");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonExpected, headers);
        var responseRequestBody = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlRequestParams), requestEntity, String.class);
        assertThat(responseRequestBody.getStatusCode().toString()).isEqualTo("400 BAD_REQUEST");
        assertEquals(HttpStatus.BAD_REQUEST, ResponseEntity.badRequest().build().getStatusCode());

    }

    //Request Param
    @Test
    public void testGetResultSumRequestParam_GivenTeenAndFifteen_ShouldReturnTwentyFive() throws Exception {
        String requestUrlRequestParams = String.format("/sumApp/sumRequestParam?numberOne=%s&numberTwo=%s", 10, 15);
        var responseRequestParams = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlRequestParams), null, String.class);
        assertThat(responseRequestParams.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(responseRequestParams.getBody()).contains("{\"sumResult\":25}");
    }

    //Path Params, PathVariable
    @Test
    public void testGetResultSumPathVariable_GivenTeenAndFifteen_ShouldReturnTwentyFive() throws Exception {
        String requestUrlPathVariable = String.format("/sumApp/sumPathVariable/%s/%s", 10, 15);
        var responsePathVariable = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlPathVariable), null, String.class);
        assertThat(responsePathVariable.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(responsePathVariable.getBody()).contains(("{\"sumResult\":25}"));
    }

    //Request Body
    // Request Entity Object --> Solicitud del Objeto a Publicar.
    @Test
    public void testGetResultSumRequestBody_GivenTeenAndFifteen_ShouldReturnTwentyFive() throws Exception {
        String requestUrlRequestParams = "/sumApp/sumRequestBody";
        String jsonExpected = String.format("{\"numberOne\":%s,\"numberTwo\":%s}", 10, 15);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity(jsonExpected, headers);
        var responseRequestBody = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, requestUrlRequestParams), requestEntity, String.class);
        assertThat(responseRequestBody.getStatusCode().toString()).isEqualTo("200 OK");
        assertThat(responseRequestBody.getBody()).contains("{\"sumResult\":25}");
    }

}
