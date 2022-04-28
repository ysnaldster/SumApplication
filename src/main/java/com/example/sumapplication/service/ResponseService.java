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

    public void setResponse(String endpoint, SumRequestBody sumRequestBody) {
        SumResponseBody responseBody = new SumResponseBody();
        responseBody.setIdRequestFk(sumRequestBody.getIdRequest());
        responseBody.setEndpoint(endpoint);
        responseBody.setResultSum(sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
        sumResponseRepository.saveResponseSum(responseBody);
    }

    public SumResponseBody getResponse(int idResponse) {
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }

}
