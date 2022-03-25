package com.example.sumapplication.repository;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.service.RequestService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
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
    public void testGetBodyRequestEndpointRequestParam_GivenZeroAndOneAndThree_ShouldReturnObjectJSON()  throws Exception {
        //Mandar Data
      /*  int numberOne= 1500;
        int numberTwo = 100;
        String urlRequestParams = String.format("http://localhost/sumApp/sumRequestParam?numberOne=%s&numberTwo=%s", numberOne, numberTwo);
        var responseRequestParams = restTemplate.postForEntity
                (String.format("http://localhost:%s%s", port, urlRequestParams), null, String.class);
        //Obtener


        //Validar


        Gson gson = new Gson();
        String JSON = gson.toJson(sumRequestBody);
        String JSON2 = gson.toJson(requestService.getObjectForIdRequest(1));
        requestService.getObjectForIdRequest(1);
        Assertions.assertEquals(JSON, JSON2);*/
    }

}
