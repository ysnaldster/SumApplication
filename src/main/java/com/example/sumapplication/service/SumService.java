package com.example.sumapplication.service;

import com.example.sumapplication.model.SumResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    public ResponseEntity<SumResult> operationSum(int numberOne, int numberTwo) {
        SumResult result = new SumResult();
        result.setSumResult(numberOne + numberTwo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
