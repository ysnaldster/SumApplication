package com.example.sumapplication.controller;


import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.example.sumapplication.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sumApp", produces = "application/json")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class SumController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SumService sumService;

    @PostMapping(value = "/sumRequestParam", produces = "application/json")
    public ResponseEntity getSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) {
        requestService.setNumbersWithParametersAndPositionsURL(numberOne, numberTwo);
        responseService.setResponseWithParametersAndPositionsURL("getSumWithRequestParam", numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/sumPathVariable/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity getSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) {
        requestService.setNumbersWithParametersAndPositionsURL(numberOne, numberTwo);
        responseService.setResponseWithParametersAndPositionsURL("getSumWithPathVariable", numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/sumRequestBody", produces = "application/json")
    public ResponseEntity getSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) {
        requestService.setNumbersWithBodyRequest(sumRequestBody);
        responseService.setResponseWithBodyRequest(sumRequestBody);
        return sumService.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
    }

}
