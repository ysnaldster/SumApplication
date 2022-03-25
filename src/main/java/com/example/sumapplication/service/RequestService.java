package com.example.sumapplication.service;

import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.repository.SumRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private SumRequestRepository sumRequestRepository;

    public void setNumbersWithParametersAndPositionsURL(int numberOne, int numberTwo){
        sumRequestRepository.saveRequestNumbers(numberOne, numberTwo);
    }

    public void setNumbersWithBodyRequest(SumRequestBody sumRequestBody) {
        sumRequestRepository.saveRequestNumbers(sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());
    }

    public SumRequestBody getObjectForIdRequest(int id_request){
        return sumRequestRepository.getDataOfTableRequests(id_request);
    }

}
