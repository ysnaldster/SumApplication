package com.example.sumapplication.repository.mapper;

import com.example.sumapplication.model.SumRequestBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SumRequestMapper implements RowMapper<SumRequestBody> {

    @Override
    public SumRequestBody mapRow(ResultSet rs, int rowNum) throws SQLException {
        SumRequestBody sumRequestBody = new SumRequestBody();

        sumRequestBody.setIdRequest(rs.getInt("id_request"));
        sumRequestBody.setNumberOne(rs.getInt("number_one"));
        sumRequestBody.setNumberTwo(rs.getInt("number_two"));

        return sumRequestBody;
    }
}
