package com.example.sumapplication.service;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class SumServiceTest {

    @Autowired
    SumService sumService;

    @Test
    public void testSum_GivenFourAndFive_ShouldReturnNine() {
        Gson gson = new Gson();
        //Check Expected Result
        ResponseEntity result = sumService.operationSum(4, 5);
        Assertions.assertEquals("{\"sumResult\":9}", gson.toJson(result.getBody()));

    }
}
