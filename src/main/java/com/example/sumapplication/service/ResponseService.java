package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseService(SumResponseRepository sumResponseRepository) {
        this.sumResponseRepository = sumResponseRepository;
    }

    private final SumResponseRepository sumResponseRepository;

    public void saveResponse(String endpoint, SumRequestBody sumRequestBody) throws JsonProcessingException {
        SumResponseBody responseBody = new SumResponseBody(sumRequestBody.getIdRequest(), endpoint, sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
        int idResponse = sumResponseRepository.saveResponseSum(responseBody);
        sumResponseRepository.saveResponseAtRedis(new SumResponseBody(idResponse, responseBody.getIdRequestFk(), responseBody.getEndpoint(), responseBody.getResultSum()));
    }

    public SumResponseBody getResponse(int idResponse) {
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }

}
