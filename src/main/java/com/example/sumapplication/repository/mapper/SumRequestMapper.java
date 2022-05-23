package com.example.sumapplication.repository.mapper;

import com.example.sumapplication.model.SumRequestBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SumRequestMapper implements RowMapper<SumRequestBody> {

    @Override
    public SumRequestBody mapRow(ResultSet rs, int rowNum) throws SQLException {
        String idRequest = "id_request";
        String numberOne = "number_one";
        String numberTwo = "number_two";
        SumRequestBody sumRequestBody = new SumRequestBody();

        sumRequestBody.setIdRequest(rs.getInt(idRequest));
        sumRequestBody.setNumberOne(rs.getInt(numberOne));
        sumRequestBody.setNumberTwo(rs.getInt(numberTwo));

        return sumRequestBody;
    }
}
