package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.SumRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public RequestService(SumRequestRepository sumRequestRepository, ResponseService responseService) {
        this.sumRequestRepository = sumRequestRepository;
        this.responseService = responseService;
    }

    private final SumRequestRepository sumRequestRepository;
    private final ResponseService responseService;

    public void setRequest(String endpoint, int numberOne, int numberTwo) {
        SumRequestBody initRequest = new SumRequestBody(numberOne, numberTwo);
        int idRequest = sumRequestRepository.saveRequestNumbers(initRequest);
        SumRequestBody request = new SumRequestBody(idRequest, numberOne, numberTwo);
        responseService.saveResponse(endpoint, request);
    }

    public void setNumbersWithBodyRequest(String endpoint, SumRequestBody sumRequestBody) {
        int idRequest = sumRequestRepository.saveRequestNumbers(sumRequestBody);
        SumRequestBody request = new SumRequestBody(idRequest, sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
        responseService.saveResponse(endpoint, request);
    }

    public SumRequestBody getObjectForIdRequest(int idRequest) {
        return sumRequestRepository.getRequest(idRequest);
    }

}
