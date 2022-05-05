package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumRequestBody;

public interface ISumRequestRepository {
    int saveRequestNumbers(SumRequestBody sumRequestBody);

    SumRequestBody getRequest(int idRequest);

}
