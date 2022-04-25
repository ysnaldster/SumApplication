package com.example.sumapplication.controller;


import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.example.sumapplication.service.SumService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sums", produces = "application/json")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class SumController {

    public SumController(RequestService requestService, ResponseService responseService, SumService sumService) {
        this.requestService = requestService;
        this.responseService = responseService;
        this.sumService = sumService;
    }

    private final RequestService requestService;
    private final ResponseService responseService;
    private final SumService sumService;

    @PostMapping(value = "/requestParam.postSum", produces = "application/json")
    public ResponseEntity postSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) {
        String endpoint = "postSumWithRequestParam";
        requestService.setNumbersWithParametersAndPositionsURL(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/pathVariable.postSum/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity postSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) {
        String endpoint = "postSumWithPathVariable";
        requestService.setNumbersWithParametersAndPositionsURL(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/requestBody.postSum", produces = "application/json")
    public ResponseEntity postSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) {
        String endpoint = "postSumWithRequestBody";
        requestService.setNumbersWithBodyRequest(endpoint, sumRequestBody);
        responseService.setResponseWithBodyRequest(sumRequestBody);
        return sumService.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
    }
}
