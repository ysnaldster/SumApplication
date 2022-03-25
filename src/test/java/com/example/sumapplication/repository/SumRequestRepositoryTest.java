package com.example.sumapplication.repository;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.service.RequestService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumRequestRepositoryTest {

    @LocalServerPort
    private int port;

    @Autowired
    RequestService requestService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    SumRequestRepository sumRequestRepository;

    @Test
    public void testGetBodyRequestEndpointRequestParam_GivenZeroAndOneAndThree_ShouldReturnObjectJSON() throws Exception {

        String requestUrlRequestParams = "/sumApp/sumRequestParam?numberOne=300&numberTwo=400";
        restTemplate.postForLocation(String.format("http://localhost:%s%s", port, requestUrlRequestParams),null);

        int numberOne = 300;
        int numberTwo = 400;

        SumRequestBody sumRequestBody = new SumRequestBody();
        sumRequestBody.setId_request(1);
        sumRequestBody.setNumberOne(numberOne);
        sumRequestBody.setNumberTwo(numberTwo);

        Gson gson = new Gson();
        String JSON = gson.toJson(sumRequestBody);
        String JSON2 = gson.toJson(requestService.getObjectForIdRequest(1));
        Assertions.assertEquals(JSON, JSON2);

    }

}
