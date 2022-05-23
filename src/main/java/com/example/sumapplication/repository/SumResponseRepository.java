package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumResponseRepository;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.repository.mapper.SumResponseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
public class SumResponseRepository implements ISumResponseRepository {

    private final String RESPONSE_KEY = "RESPONSE";

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RedisTemplate<String, String> REDIS_TEMPLATE;

    private final NamedParameterJdbcTemplate NAMED_PARAMETER_JDBC_TEMPLATE;

    public SumResponseRepository(RedisTemplate<String, String> redisTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.REDIS_TEMPLATE = redisTemplate;
        this.NAMED_PARAMETER_JDBC_TEMPLATE = namedParameterJdbcTemplate;
    }

    @Override
    public int saveResponseSum(SumResponseBody sumResponseBody) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("idRequestFk", sumResponseBody.getIdRequestFk()).addValue("endpoint", sumResponseBody.getEndpoint()).addValue("resultSum", sumResponseBody.getResultSum());
        String sql = "INSERT INTO RESPONSES (id_request_fk, endpoint, result_sum) VALUES (:idRequestFk, :endpoint, :resultSum)";
        NAMED_PARAMETER_JDBC_TEMPLATE.update(sql, parameters, keyHolder, new String[]{"id_response"});
        var id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return (int) id;
    }

    @Override
    public void saveResponseAtRedis(SumResponseBody sumResponseBody) throws JsonProcessingException {
        String sumResponseBodyJson = OBJECT_MAPPER.writeValueAsString(sumResponseBody);
        REDIS_TEMPLATE.opsForValue().set(RESPONSE_KEY + "." + sumResponseBody.getIdRequestFk(), sumResponseBodyJson, 1, TimeUnit.MINUTES);
    }

    @Override
    public SumResponseBody findResponseAtRedis(int idResponse) throws JsonProcessingException {
        var responseResult = REDIS_TEMPLATE.opsForValue().get(RESPONSE_KEY + "." + idResponse);
        return OBJECT_MAPPER.readValue(responseResult, SumResponseBody.class);
    }

    @Override
    public SumResponseBody getDataOfTableResponses(int idResponse) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idResponse", idResponse);
        return NAMED_PARAMETER_JDBC_TEMPLATE.queryForObject("SELECT * FROM RESPONSES WHERE ID_RESPONSE = :idResponse", namedParameters, new SumResponseMapper());
    }
}
