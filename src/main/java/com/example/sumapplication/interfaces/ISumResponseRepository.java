package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumResponseBody;

public interface ISumResponseRepository {

    void saveResponseSum(String endpoint, int resultSum);

    SumResponseBody getDataOfTableResponses(int idResponse);

}
