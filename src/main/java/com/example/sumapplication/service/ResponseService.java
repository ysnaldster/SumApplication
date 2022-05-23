package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.SumResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public ResponseService(SumResponseRepository sumResponseRepository, SumService sumService) {
        this.SUM_RESPONSE_REPOSITORY = sumResponseRepository;
        this.SUM_SERVICE = sumService;
    }

    private final SumResponseRepository SUM_RESPONSE_REPOSITORY;

    private final SumService SUM_SERVICE;

    public SumResponseBody saveResponse(String endpoint, SumRequestBody sumRequestBody) throws JsonProcessingException {
        int resultSum = SUM_SERVICE.operationSum(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
        SumResponseBody initResponse = new SumResponseBody(sumRequestBody.getIdRequest(), endpoint, resultSum);
        int idResponse = SUM_RESPONSE_REPOSITORY.saveResponseSum(initResponse);
        SumResponseBody response = new SumResponseBody(idResponse, initResponse.getIdRequestFk(), initResponse.getEndpoint(), initResponse.getResultSum());
        SUM_RESPONSE_REPOSITORY.saveResponseAtRedis(response);
        return response;
    }

    public SumResponseBody findResponse(int idResponse) throws JsonProcessingException {
        SumResponseBody resultResponse;
        // Search at database Redis.
        //First case: Data doesn't exist for database Redis.
        if (SUM_RESPONSE_REPOSITORY.findResponseAtRedis(idResponse) == null) {
            // Nested case: Search at PostgresSQL database.
            resultResponse = SUM_RESPONSE_REPOSITORY.getDataOfTableResponses(idResponse);
            // Execute casa result response isn't null.
            if (resultResponse != null) {
                // Insert result response object at database Redis with TTL of one minute.
                SUM_RESPONSE_REPOSITORY.saveResponseAtRedis(resultResponse);
            }
        } else {
            //Second case: Data exist at database Redis.
            resultResponse = SUM_RESPONSE_REPOSITORY.findResponseAtRedis(idResponse);
        }
        return resultResponse;
    }

    public SumResponseBody getResponse(int idResponse) {
        return SUM_RESPONSE_REPOSITORY.getDataOfTableResponses(idResponse);
    }
}
