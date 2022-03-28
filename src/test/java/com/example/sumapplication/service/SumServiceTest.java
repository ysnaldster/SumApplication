package com.example.sumapplication.service;

import com.example.sumapplication.models.SumResult;
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
        SumResult sumResult = new SumResult(9);
        ResponseEntity result = sumService.operationSum(4, 5);
        Assertions.assertEquals(gson.toJson(sumResult), gson.toJson(result.getBody()));

    }
}
