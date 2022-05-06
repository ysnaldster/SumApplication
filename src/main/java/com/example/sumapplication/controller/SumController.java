package com.example.sumapplication.controller;


import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.model.SumResult;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.SumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sums", produces = "application/json")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class SumController {

    @Value("${endpointName.requestParam}")
    private String endpointNameRequestParam;

    @Value("${endpointName.pathVariable}")
    private String endpointNamePathVariable;

    @Value("${endpointName.requestBody}")
    private String endpointNameRequestBody;

    public SumController(RequestService requestService, SumService sumService) {
        this.requestService = requestService;
        this.sumService = sumService;
    }

    private final RequestService requestService;

    private final SumService sumService;

    @PostMapping(value = "/requestParam.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNameRequestParam;
        requestService.setRequest(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/pathVariable.postSum/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNamePathVariable;
        requestService.setRequest(endpoint, numberOne, numberTwo);
        return sumService.operationSum(numberOne, numberTwo);
    }

    @PostMapping(value = "/requestBody.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) throws JsonProcessingException {
        String endpoint = endpointNameRequestBody;
        requestService.setNumbersWithBodyRequest(endpoint, sumRequestBody);
        return sumService.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
    }

    @PostMapping(value = "/requestParam.getSumResponseBody", produces = "application/json")
    public ResponseEntity<SumResponseBody> findSumResponseBody(@RequestParam int idResponse) throws JsonProcessingException {
        
    }

}
