package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumRequestRepository;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.mapper.SumRequestMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class SumRequestRepository implements ISumRequestRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SumRequestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Cacheable(value= "SumRequestBody" , key= "#p0" )
    public SumRequestBody saveRequestNumbers(String endpoint, int numberOne, int numberTwo) {
        SumRequestBody sumRequestBody = new SumRequestBody();
        sumRequestBody.setNumberOne(numberOne);
        sumRequestBody.setNumberTwo(numberTwo);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("numberOne", numberOne)
                .addValue("numberTwo", numberTwo);
        namedParameterJdbcTemplate.update("INSERT INTO requests(number_one, number_two)"
                        + "VALUES(:numberOne, :numberTwo)",
                parameters, keyHolder, new String[]{"id_request"});
        var id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        sumRequestBody.setIdRequest((int) id);
        return sumRequestBody;
    }

    @Override
    @Cacheable(value= "SumRequestBody" , key= "#p0" )
    public SumRequestBody getDataOfTableRequests(int idRequest) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idRequest", idRequest);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM REQUESTS WHERE ID_REQUEST = :idRequest", namedParameters, new SumRequestMapper());
    }

}
