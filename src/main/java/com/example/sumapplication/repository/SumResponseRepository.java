package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumResponseRepository;
import com.example.sumapplication.repository.mapper.SumResponseMapper;
import com.example.sumapplication.model.SumResponseBody;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveResponseSum(String endpoint, int resultSum) {
        String sql2 = "SELECT ID_REQUEST FROM REQUESTS ORDER BY ID_REQUEST DESC LIMIT 1";
        int idRequestFk = jdbcTemplate.queryForObject(sql2, Integer.class);
        String sql = "INSERT INTO RESPONSES (id_request_fk, endpoint, result_sum) VALUES (:idRequestFk, :endpoint, :resultSum)";
        SumResponseBody responseBody = new SumResponseBody();
        responseBody.setIdRequestFk(idRequestFk);
        responseBody.setEndpoint(endpoint);
        responseBody.setResultSum(resultSum);
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
