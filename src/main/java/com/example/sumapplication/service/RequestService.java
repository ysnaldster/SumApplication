package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public RequestService(SumRequestRepository sumRequestRepository, ResponseService responseService) {
        this.sumRequestRepository = sumRequestRepository;
        this.responseService = responseService;
    }

    private final SumRequestRepository sumRequestRepository;
    private final ResponseService responseService;

    public SumResponseBody saveRequest(String endpoint, int numberOne, int numberTwo) throws JsonProcessingException {
        SumRequestBody initRequest = new SumRequestBody(numberOne, numberTwo);
        int idRequest = sumRequestRepository.saveRequestNumbers(initRequest);
        SumRequestBody request = new SumRequestBody(idRequest, numberOne, numberTwo);
        sumRequestRepository.saveRequestAtRedis(request);
        return responseService.saveResponse(endpoint, request);
    }

    public SumResponseBody saveRequestWithBodyRequest(String endpoint, SumRequestBody sumRequestBody) throws JsonProcessingException {
        int idRequest = sumRequestRepository.saveRequestNumbers(sumRequestBody);
        SumRequestBody request = new SumRequestBody(idRequest, sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
        sumRequestRepository.saveRequestAtRedis(request);
        return responseService.saveResponse(endpoint, request);
    }

    public SumRequestBody getObjectForIdRequest(int idRequest) {
        return sumRequestRepository.getRequest(idRequest);
    }

}
