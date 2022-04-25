package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumResponseRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseService(SumResponseRepository sumResponseRepository) {
        this.sumResponseRepository = sumResponseRepository;
    }

    private final SumResponseRepository sumResponseRepository;

    public void setResponseWithParametersAndPositionsURL(String endpoint, SumRequestBody sumRequestBody) {
        sumResponseRepository.saveResponseSum(endpoint, sumRequestBody);
    }

    public void setResponseWithBodyRequest(SumRequestBody sumRequestBody) {
        String endpoint = "postSumWithRequestBody";
        SumResponseBody sumResponseBody = new SumResponseBody();
        sumResponseBody.setEndpoint(endpoint);
        sumResponseBody.setResultSum(sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
    }

    public SumResponseBody getObjectForIdResponse(int idResponse) {
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }

}
