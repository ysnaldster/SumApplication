package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseService(SumResponseRepository sumResponseRepository, SumService sumService) {
        this.sumResponseRepository = sumResponseRepository;
        this.sumService = sumService;
    }

    private final SumResponseRepository sumResponseRepository;

    private final SumService sumService;

    public SumResponseBody saveResponse(String endpoint, SumRequestBody sumRequestBody) throws JsonProcessingException {
        int resultSum = sumService.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
        SumResponseBody initResponse = new SumResponseBody(sumRequestBody.getIdRequest(), endpoint, resultSum);
        int idResponse = sumResponseRepository.saveResponseSum(initResponse);
        SumResponseBody response = new SumResponseBody(idResponse, initResponse.getIdRequestFk(), initResponse.getEndpoint(), initResponse.getResultSum());
        sumResponseRepository.saveResponseAtRedis(response);
        return response;
    }

    public SumResponseBody findResponse(int idResponse) throws JsonProcessingException {
        SumResponseBody resultResponse;
        // Search at database Redis.
            //First case: Data doesn't exist for database Redis.
        if (sumResponseRepository.findResponseAtRedis(idResponse) == null) {
                // Nested case: Search at PostgresSQL database.
            resultResponse = sumResponseRepository.getDataOfTableResponses(idResponse);
                    // Execute casa result response isn't null.
            if (resultResponse != null) {
                    // Insert result response object at database Redis with TTL of one minute.
                sumResponseRepository.saveResponseAtRedis(resultResponse);
            }
        } else {
            //Second case: Data exist at database Redis.
            resultResponse = sumResponseRepository.findResponseAtRedis(idResponse);
        }
        return resultResponse;
    }

    public SumResponseBody getResponse(int idResponse) {
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }
}
