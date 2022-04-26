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

    public void setNumbersWithParametersAndPositionsURL(String endpoint, int numberOne, int numberTwo) {
        SumRequestBody sumRequestBody = new SumRequestBody();
        sumRequestBody.setNumberOne(numberOne);
        sumRequestBody.setNumberTwo(numberTwo);
        SumRequestBody sumRequestBody = sumRequestRepository.saveRequestNumbers(SumRequestBody);
        responseService.setResponseWithParametersAndPositionsURL(endpoint, sumRequestBody);
    }

    public void setNumbersWithBodyRequest(String endpoint, SumRequestBody sumRequestBody) {
        SumRequestBody request = sumRequestRepository.saveRequestNumbers(endpoint, sumRequestBody.getNumberOne(),
                sumRequestBody.getNumberTwo());
        responseService.setResponseWithParametersAndPositionsURL(endpoint, request);
    }

    public SumRequestBody getObjectForIdRequest(int idRequest) {
        return sumRequestRepository.getDataOfTableRequests(idRequest);
    }

}
