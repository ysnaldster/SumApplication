package com.example.sumapplication.interfaces;

import com.example.sumapplication.models.SumRequestBody;

public interface ISumRequestRepository {

    void saveRequestNumbers(int numberOne, int numberTwo);

    SumRequestBody getDataOfTableRequests(int idRequest);

}
