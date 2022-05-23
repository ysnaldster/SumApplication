package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ISumRequestRepository {
    int saveRequestNumbers(SumRequestBody sumRequestBody);

    void saveRequestAtRedis(SumRequestBody sumRequestBody) throws JsonProcessingException;

    SumRequestBody getRequest(int idRequest);
}
