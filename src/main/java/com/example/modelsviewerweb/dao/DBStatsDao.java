package com.example.modelsviewerweb.dao;

import com.example.modelsviewerweb.controllers.response.DBStatsResponse;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public interface DBStatsDao {

    void setDataSource(DataSource dataSource);

    DBStatsResponse getStats ();

}
