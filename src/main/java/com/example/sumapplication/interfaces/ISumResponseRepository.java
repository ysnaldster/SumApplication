package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumResponseBody;

public interface ISumResponseRepository {

    void saveResponseSum(SumResponseBody sumResponseBody);

    SumResponseBody getDataOfTableResponses(int idResponse);

}
