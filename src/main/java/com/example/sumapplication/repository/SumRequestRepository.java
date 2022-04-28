package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumRequestRepository;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.mapper.SumRequestMapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
@Transactional
public class SumRequestRepository implements ISumRequestRepository {

    public static final String REQUEST_KEY = "REQUEST";

    private final HashOperations<String, String, Integer> hashOperations;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SumRequestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SumRequestBody saveRequestNumbers(SumRequestBody sumRequestBody) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("numberOne", sumRequestBody.getNumberOne())
                .addValue("numberTwo", sumRequestBody.getNumberTwo());
        String sql = "INSERT INTO requests(number_one, number_two)" + "VALUES(:numberOne, :numberTwo)";
        namedParameterJdbcTemplate.update(sql, parameters, keyHolder, new String[]{"id_request"});
        var id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        sumRequestBody.setIdRequest((int) id);
        hashOperations.put(REQUEST_KEY + "." + id, "id_request", sumRequestBody.getIdRequest());
        hashOperations.put(REQUEST_KEY + "." + id, "numberOne", sumRequestBody.getNumberOne());
        hashOperations.put(REQUEST_KEY + "." + id, "numberTwo", sumRequestBody.getNumberTwo());
        return sumRequestBody;
    }

    @Override
    public SumRequestBody getRequest(int idRequest) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idRequest", idRequest);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM REQUESTS WHERE ID_REQUEST = :idRequest", namedParameters, new SumRequestMapper());
    }

}
