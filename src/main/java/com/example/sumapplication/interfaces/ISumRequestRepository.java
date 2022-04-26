package com.example.sumapplication.interfaces;

import com.example.sumapplication.model.SumRequestBody;

import java.sql.SQLException;

public interface ISumRequestRepository {
    SumRequestBody saveRequestNumbers(SumRequestBody sumRequestBody) throws SQLException;

    SumRequestBody getDataOfTableRequests(int idRequest);

}
