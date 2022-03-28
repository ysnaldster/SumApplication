package com.example.sumapplication.controller;

import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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
}
