package com.example.sumapplication.service;

import com.example.sumapplication.model.SumResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SumService.class)
public class SumServiceTest {

    @Autowired
    SumService sumService;

    @Test
    public void testSum_GivenFourAndFive_ShouldReturnNine() throws JsonProcessingException {
        // Arrange -> Se preparan los datos necesarios para la prueba.
        int numberOne = 4;
        int numberTwo = 5;
        int resultExpected = 9;
        final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        SumResult sumResult = new SumResult(resultExpected);
        // Act -> Se ejecuta que se desea probar.
        ResponseEntity result = sumService.operationSum(numberOne, numberTwo);
        // Assert -> Verificación del resultado con lo deseado.
        Assertions.assertEquals(OBJECT_MAPPER.writeValueAsString(sumResult),
                OBJECT_MAPPER.writeValueAsString(result.getBody()));
    }
}
