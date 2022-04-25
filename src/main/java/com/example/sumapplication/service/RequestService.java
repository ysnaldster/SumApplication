package com.example.sumapplication.service;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.SumRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RequestService {

    @Autowired
    private SumRequestRepository sumRequestRepository;

    @Autowired
    ResponseService responseService;

    public void setNumbersWithParametersAndPositionsURL(String endpoint, int numberOne, int numberTwo) throws SQLException {
        SumRequestBody sumRequestBody = sumRequestRepository.saveRequestNumbers(endpoint, numberOne, numberTwo);
        responseService.setResponseWithParametersAndPositionsURL(endpoint, sumRequestBody);
    }

    public void setNumbersWithBodyRequest(String endpoint, SumRequestBody sumRequestBody) throws SQLException {
        SumRequestBody request = sumRequestRepository.saveRequestNumbers(endpoint, sumRequestBody.getNumberOne(),
                sumRequestBody.getNumberTwo());
        responseService.setResponseWithParametersAndPositionsURL(endpoint, request);
    }

    public SumRequestBody getObjectForIdRequest(int idRequest) {
        return sumRequestRepository.getDataOfTableRequests(idRequest);
    }

}
