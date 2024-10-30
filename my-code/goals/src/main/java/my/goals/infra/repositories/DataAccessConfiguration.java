package my.goals.infra.repositories;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.goals.application.dataaccess.IGoalsDataAccess;

@Configuration
public class DataAccessConfiguration {

    @Bean
    IGoalsDataAccess postgresGoalsDataAccess() {
	return new PostgresGoalsDataAccess();
    }

}
