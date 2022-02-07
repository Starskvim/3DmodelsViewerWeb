package com.example.modelsviewerweb.repositories;

import com.example.modelsviewerweb.controllers.response.DBStatsResponse;
import com.example.modelsviewerweb.controllers.response.DBStatsResponseMapper;
import com.example.modelsviewerweb.dao.DBStatsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateDBStatsDao implements DBStatsDao {

    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DBStatsResponse getStats() {

        String SQL = "SELECT (SELECT COUNT(*)FROM model_db.print_model_web) AS totalModels," +
                "(SELECT COUNT(*)FROM model_db.print_model_tag) AS totalTag," +
                "(SELECT COUNT(*)FROM model_db.print_model_oth_web) AS totalOTH," +
                "(SELECT sum (print_model_web.model_size) / 1024 FROM model_db.print_model_web) AS totalSize;";

        DBStatsResponse dbStatsResponse = jdbcTemplate.queryForObject(SQL, new DBStatsResponseMapper());

        return dbStatsResponse;
    }
}
