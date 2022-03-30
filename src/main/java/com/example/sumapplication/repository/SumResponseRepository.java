package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumResponseRepository;
import com.example.sumapplication.models.SumRequestBody;
import com.example.sumapplication.models.SumResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    public void saveResponseSum(String endpoint, int resultSum) {
        String sql = "INSERT INTO responses (endpoint, result_sum) VALUES (:endpoint, :resultSum)";
        SumResponseBody responseBody = new SumResponseBody();
        responseBody.setEndpoint(endpoint);
        responseBody.setResultSum(resultSum);
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(responseBody);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public SumResponseBody getDataOfTableResponses(int idResponse) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idResponse", idResponse);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM RESPONSES WHERE ID_RESPONSE = :idResponse", namedParameters, new BeanPropertyRowMapper<>(SumResponseBody.class));
    }

}
