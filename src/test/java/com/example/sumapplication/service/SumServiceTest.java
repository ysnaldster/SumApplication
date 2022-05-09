package com.example.sumapplication.service;

import com.example.sumapplication.model.SumResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SumService.class)
public class SumServiceTest {

    @Autowired
    private SumService sumService;

    final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testSum_GivenFourAndFive_ShouldReturnNine() throws JsonProcessingException {
        // Arrange
        int numberOne = 4;
        int numberTwo = 5;
        int resultExpected = 9;

        SumResult sumResult = new SumResult(resultExpected);
        // Act
        int initResult = sumService.operationSum(numberOne, numberTwo);
        ResponseEntity<SumResult> result = new ResponseEntity<>(new SumResult(initResult), HttpStatus.OK);
        // Assert
        Assertions.assertEquals(OBJECT_MAPPER.writeValueAsString(sumResult), OBJECT_MAPPER.writeValueAsString(result.getBody()));
    }
}
