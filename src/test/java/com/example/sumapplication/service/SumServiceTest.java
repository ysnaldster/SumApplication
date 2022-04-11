package com.example.sumapplication.service;

import com.example.sumapplication.models.SumResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void testSum_GivenFourAndFive_ShouldReturnNine() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        SumResult sumResult = new SumResult(9);
        ResponseEntity result = sumService.operationSum(4, 5);
        Assertions.assertEquals(objectMapper.writeValueAsString(sumResult),
                objectMapper.writeValueAsString(result.getBody()));
    }
}
