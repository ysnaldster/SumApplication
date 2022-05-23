package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public RequestService(SumRequestRepository sumRequestRepository, ResponseService responseService) {
        this.SUM_REQUEST_REPOSITORY = sumRequestRepository;
        this.RESPONSE_SERVICE = responseService;
    }

    private final SumRequestRepository SUM_REQUEST_REPOSITORY;
    private final ResponseService RESPONSE_SERVICE;

    public SumResponseBody saveRequest(String endpoint, int numberOne, int numberTwo) throws JsonProcessingException {
        SumRequestBody initRequest = new SumRequestBody(numberOne, numberTwo);
        int idRequest = SUM_REQUEST_REPOSITORY.saveRequestNumbers(initRequest);
        SumRequestBody request = new SumRequestBody(idRequest, numberOne, numberTwo);
        SUM_REQUEST_REPOSITORY.saveRequestAtRedis(request);
        return RESPONSE_SERVICE.saveResponse(endpoint, request);
    }

    public SumResponseBody saveRequestWithBodyRequest(String endpoint, SumRequestBody sumRequestBody) throws JsonProcessingException {
        int idRequest = SUM_REQUEST_REPOSITORY.saveRequestNumbers(sumRequestBody);
        SumRequestBody request = new SumRequestBody(idRequest, sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
        SUM_REQUEST_REPOSITORY.saveRequestAtRedis(request);
        return RESPONSE_SERVICE.saveResponse(endpoint, request);
    }

    public SumRequestBody getObjectForIdRequest(int idRequest) {
        return SUM_REQUEST_REPOSITORY.getRequest(idRequest);
    }
}
