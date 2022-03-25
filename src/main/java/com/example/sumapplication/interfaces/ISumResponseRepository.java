package com.example.sumapplication.interfaces;

import com.example.sumapplication.models.SumResponseBody;

public interface ISumResponseRepository {

    void saveResponseSum(String endpoint, int result_sum);

    SumResponseBody getDataOfTableResponses(int id_response);
}
