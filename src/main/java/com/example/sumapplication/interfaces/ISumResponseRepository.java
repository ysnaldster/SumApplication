package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ISumResponseRepository {

    int saveResponseSum(SumResponseBody sumResponseBody);

    void saveResponseAtRedis(SumResponseBody sumResponseBody) throws JsonProcessingException;

    SumResponseBody getDataOfTableResponses(int idResponse);

}
