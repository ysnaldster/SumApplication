package com.example.sumapplication.service;

import com.example.sumapplication.models.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    public ResponseEntity operationSum(int numberOne, int numberTwo) {
        ResultModel result = new ResultModel();
        result.setResultModel(numberOne + numberTwo);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
