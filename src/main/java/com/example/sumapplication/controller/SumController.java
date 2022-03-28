package com.example.sumapplication.controller;


import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.example.sumapplication.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sums", produces = "application/json")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class SumController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SumService sumService;

    @PostMapping(value = "/requestParam.postSum", produces = "application/json")
    public ResponseEntity postSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) {
        String endpoint = "postSumWithRequestParam";
        requestService.setNumbersWithParametersAndPositionsURL(numberOne, numberTwo);
        responseService.setResponseWithParametersAndPositionsURL(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/pathVariable.postSum/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity postSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) {
        String endpoint = "postSumWithPathVariable";
        requestService.setNumbersWithParametersAndPositionsURL(numberOne, numberTwo);
        responseService.setResponseWithParametersAndPositionsURL(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/requestBody.postSum", produces = "application/json")
    public ResponseEntity postSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) {
        requestService.setNumbersWithBodyRequest(sumRequestBody);
        responseService.setResponseWithBodyRequest(sumRequestBody);
        return sumService.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
    }

}
