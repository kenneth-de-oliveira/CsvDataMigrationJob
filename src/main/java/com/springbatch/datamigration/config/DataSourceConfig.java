package com.springbatch.datamigration.config;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

	private final Environment env;

	@Primary
	@Bean
	public DataSource springDataSource() {
		return DataSourceBuilder.create()
				.url(env.getProperty("spring.datasource.url"))
				.username(env.getProperty("spring.datasource.username"))
				.password(env.getProperty("spring.datasource.password"))
				.build();
	}

	@Bean
	public DataSource appDataSource() {
		return DataSourceBuilder.create()
				.url(env.getProperty("app.datasource.url"))
				.username(env.getProperty("app.datasource.username"))
				.password(env.getProperty("app.datasource.password"))
				.build();
	}

}