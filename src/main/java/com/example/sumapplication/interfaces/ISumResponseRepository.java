package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;

public interface ISumResponseRepository {

    void saveResponseSum(String endpoint , SumRequestBody sumRequestBody);

    SumResponseBody getDataOfTableResponses(int idResponse);

}
