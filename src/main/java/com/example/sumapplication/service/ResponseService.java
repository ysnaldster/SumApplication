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
        SumResponseBody sumResponseBody = new SumResponseBody();
        sumResponseBody.setEndpoint("getSumWithRequestBody");
        sumResponseBody.setResult_sum(sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
        sumResponseRepository.saveResponseSum(sumResponseBody.getEndpoint(), sumResponseBody.getResult_sum());
    }

    public SumResponseBody getObjectForIdResponse(int id_response){
        return sumResponseRepository.getDataOfTableResponses(id_response);
    }
}
