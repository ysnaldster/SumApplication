package com.example.sumapplication.repository.mapper;

import com.example.sumapplication.model.SumResponseBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SumResponseMapper implements RowMapper<SumResponseBody> {

    @Override
    public SumResponseBody mapRow(ResultSet rs, int rowNum) throws SQLException {
        String idResponse = "id_response";
        String idRequestFk = "id_request_fk";
        String endpoint = "endpoint";
        String resultSum = "result_sum";
        SumResponseBody sumResponseBody = new SumResponseBody();

        sumResponseBody.setId_response(rs.getInt(idResponse));
        sumResponseBody.setIdRequestFk(rs.getInt(idRequestFk));
        sumResponseBody.setEndpoint(rs.getString(endpoint));
        sumResponseBody.setResultSum(rs.getInt(resultSum));

        return sumResponseBody;
    }
}
