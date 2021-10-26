package com.dominic.todolist.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Bean
    public NamedParameterJdbcTemplate jdbc(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
