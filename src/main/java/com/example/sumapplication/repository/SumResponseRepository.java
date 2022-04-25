package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumResponseRepository;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.mapper.SumResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class SumResponseRepository implements ISumResponseRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveResponseSum(String endpoint, SumRequestBody sumRequestBody) {
        SumResponseBody responseBody = new SumResponseBody();
        responseBody.setIdRequestFk(sumRequestBody.getIdRequest());
        responseBody.setEndpoint(endpoint);
        responseBody.setResultSum(sumRequestBody.getNumberOne() + sumRequestBody.getNumberTwo());
        String sql = "INSERT INTO RESPONSES (id_request_fk, endpoint, result_sum) VALUES (:idRequestFk, :endpoint, :resultSum)";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(responseBody);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public SumResponseBody getDataOfTableResponses(int idResponse) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idResponse", idResponse);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM RESPONSES WHERE ID_RESPONSE = :idResponse", namedParameters, new SumResponseMapper());
    }

}
