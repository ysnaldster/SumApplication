package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumResponseRepository;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.mapper.SumResponseMapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class SumResponseRepository implements ISumResponseRepository {

    public static final String RESPONSE_KEY = "RESPONSE";

    private final HashOperations<String, String, Integer> hashOperations;
    private final HashOperations<String, String, String> hashOperationsString;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SumResponseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.hashOperationsString = redisTemplate.opsForHash();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void saveResponseSum(SumResponseBody sumResponseBody) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("idRequestFk", sumResponseBody.getIdRequestFk()).addValue("endpoint", sumResponseBody.getEndpoint()).addValue("resultSum", sumResponseBody.getResultSum());
        String sql = "INSERT INTO RESPONSES (id_request_fk, endpoint, result_sum) VALUES (:idRequestFk, :endpoint, :resultSum)";
        namedParameterJdbcTemplate.update(sql, parameters, keyHolder, new String[]{"id_response"});
        var id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        hashOperations.put(RESPONSE_KEY + "." + id, "id_response", sumResponseBody.getIdResponse());
        hashOperations.put(RESPONSE_KEY + "." + id, "id_request_fk", sumResponseBody.getIdRequestFk());
        hashOperationsString.put(RESPONSE_KEY + "." + id, "endpoint", sumResponseBody.getEndpoint());
        hashOperations.put(RESPONSE_KEY + "." + id, "result_sum", sumResponseBody.getResultSum());
    }

    @Override
    public SumResponseBody getDataOfTableResponses(int idResponse) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idResponse", idResponse);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM RESPONSES WHERE ID_RESPONSE = :idResponse", namedParameters, new SumResponseMapper());
    }

}
