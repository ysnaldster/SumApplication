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
        if (sumResponseRepository.findResponseAtRedis(idResponse) == null) { //No esta en Redis
            resultResponse = sumResponseRepository.getDataOfTableResponses(idResponse); // Busca objecto response en la base de datos PostgreSQL
            if (resultResponse != null) { // Si se encuentra en la base de datos, almacena en redis
                sumResponseRepository.saveResponseAtRedis(resultResponse);
            }
        } else {
            resultResponse = sumResponseRepository.findResponseAtRedis(idResponse);
        }
        return resultResponse;
    }

    public SumResponseBody getResponse(int idResponse) {
        return sumResponseRepository.getDataOfTableResponses(idResponse);
    }

}
