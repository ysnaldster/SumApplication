package com.example.sumapplication.service;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.models.SumResponseBody;
import com.example.sumapplication.repository.SumResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Autowired
    private SumResponseRepository sumResponseRepository;

    public void setResponseWithParametersAndPositionsURL(String endpoint, int numberOne, int numberTwo){
        sumResponseRepository.saveResponseSum(endpoint, numberOne + numberTwo);
    }

    public void setResponseWithBodyRequest(SumRequestBody sumRequestBody) {
        String endpoint = "postSumWithRequestBody";
        SumResponseBody sumResponseBody = new SumResponseBody();
        sumResponseBody.setEndpoint(endpoint);
        sumResponseBody.setResultSum(sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
        sumResponseRepository.saveResponseSum(sumResponseBody.getEndpoint(), sumResponseBody.getResultSum());
    }

    public SumResponseBody getObjectForIdResponse(int idResponse){
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }
}
