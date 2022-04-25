package com.example.sumapplication.repository;

import com.example.sumapplication.interfaces.ISumRequestRepository;
import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.repository.mapper.SumRequestMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class SumRequestRepository implements ISumRequestRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbcTemplate;

    public SumRequestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SumRequestBody saveRequestNumbers(String endpoint, int numberOne, int numberTwo) throws SQLException {
        SumRequestBody sumRequestBody = new SumRequestBody();
        sumRequestBody.setNumberOne(numberOne);
        sumRequestBody.setNumberTwo(numberTwo);
        //jdbcTemplate.update("insert into requests (number_one, number_two) values(?,?)", sumRequestBody.getNumberOne(), sumRequestBody.getNumberTwo());

        KeyHolder keyHolder = new GeneratedKeyHolder();

       /* jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into requests (number_one, number_two) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, sumRequestBody.getNumberOne());
            ps.setInt(2, sumRequestBody.getNumberTwo());
            ps.getGeneratedKeys();
            //ps.setString(1, String.valueOf(sumRequestBody.getNumberOne()));
            return ps;
        }, keyHolder);
        var id = keyHolder.getKey().longValue();*/
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
    public SumRequestBody getDataOfTableRequests(int idRequest) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("idRequest", idRequest);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM REQUESTS WHERE ID_REQUEST = :idRequest", namedParameters, new SumRequestMapper());
    }

}
