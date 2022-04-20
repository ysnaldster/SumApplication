package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumRequestBody;

public interface ISumRequestRepository {

    void saveRequestNumbers(SumRequestBody sumRequestBody);

    SumRequestBody getDataOfTableRequests(int idRequest);

}
