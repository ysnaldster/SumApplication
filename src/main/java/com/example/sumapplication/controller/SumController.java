/* IllegalArgumentException
 * Exception present when user inserts an ID nonexistent.
 * */
package com.example.sumapplication.controller;

import com.example.sumapplication.model.NotFoundException;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.model.SumResult;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${exception.error.notFound}")
    private String errorNotFound;

    @Value("${exception.errorCode.notFound}")
    private String errorCodeNotFound;

    @Value("${exception.message.notFound}")
    private String exceptionMessageNotFound;

    public SumController(RequestService requestService, ResponseService responseService) {
        this.REQUEST_SERVICE = requestService;
        this.RESPONSE_SERVICE = responseService;
    }

    private final RequestService REQUEST_SERVICE;

    private final ResponseService RESPONSE_SERVICE;

    private final Logger LOGGER = LogManager.getLogger(SumController.class);

    @PostMapping(value = "/requestParam.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNameRequestParam;
        SumResponseBody result = REQUEST_SERVICE.saveRequest(endpoint, numberOne, numberTwo);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @PostMapping(value = "/pathVariable.postSum/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNamePathVariable;
        SumResponseBody result = REQUEST_SERVICE.saveRequest(endpoint, numberOne, numberTwo);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @PostMapping(value = "/requestBody.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) throws JsonProcessingException {
        String endpoint = endpointNameRequestBody;
        SumResponseBody result = REQUEST_SERVICE.saveRequestWithBodyRequest(endpoint, sumRequestBody);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @GetMapping(value = "/requestParam.getSumResponseBody", produces = "application/json")
    public ResponseEntity<?> findSumResponseBody(@RequestParam int idResponse) throws JsonProcessingException {
        try {
            return new ResponseEntity<>(RESPONSE_SERVICE.findResponse(idResponse), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
            String detailNotFoundException = String.format("Registry with " + "{idResponse: " + "%s" + "}" + " doesn't exist", idResponse);
            NotFoundException notFoundException = new NotFoundException(errorNotFound, errorCodeNotFound, exceptionMessageNotFound, detailNotFoundException);
            return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
        }
    }
}
