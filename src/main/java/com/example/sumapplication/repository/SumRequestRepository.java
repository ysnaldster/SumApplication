package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumRequestRepository;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.mapper.SumRequestMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Repository
@Transactional
public class SumRequestRepository implements ISumRequestRepository {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final NamedParameterJdbcTemplate NAMED_PARAMETER_JDBC_TEMPLATE;
    private final RedisTemplate<String, String> REDIS_TEMPLATE;

    public SumRequestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RedisTemplate<String, String> redisTemplate) {
        this.REDIS_TEMPLATE = redisTemplate;
        this.NAMED_PARAMETER_JDBC_TEMPLATE = namedParameterJdbcTemplate;
    }

    @Override
    public int saveRequestNumbers(SumRequestBody sumRequestBody) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("numberOne", sumRequestBody.getNumberOne()).addValue("numberTwo", sumRequestBody.getNumberTwo());
        String sql = "INSERT INTO requests(number_one, number_two)" + "VALUES(:numberOne, :numberTwo)";
        NAMED_PARAMETER_JDBC_TEMPLATE.update(sql, parameters, keyHolder, new String[]{"id_request"});
        var id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return (int) id;
    }

    @Override
    public void saveRequestAtRedis(SumRequestBody sumRequestBody) throws JsonProcessingException {
        String requestKey = "REQUEST";
        String sumRequestBodyJson = OBJECT_MAPPER.writeValueAsString(sumRequestBody);
        REDIS_TEMPLATE.opsForValue().set(requestKey + "." + sumRequestBody.getIdRequest(), sumRequestBodyJson, 1, TimeUnit.MINUTES);
    }

    @Override
    public SumRequestBody getRequest(int idRequest) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idRequest", idRequest);
        return NAMED_PARAMETER_JDBC_TEMPLATE.queryForObject("SELECT * FROM REQUESTS WHERE ID_REQUEST = :idRequest", namedParameters, new SumRequestMapper());
    }
}
