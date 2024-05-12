package com.shovans.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * SpringJdbcConfig is a configuration class for setting up data source and JDBC
 * templates for MySQL databases.
 */
@Configuration
@ComponentScan("com.shovans.demo")
public class SpringJdbcConfig {
	/*
	 * @Bean 
	 * public DataSource mysqlDataSource() {
	 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 * return dataSource; 
	 * }
	 */
	/**
	 * Configures the primary MySQL data source.
	 *
	 * @return DataSource: The primary MySQL data source.
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.mydatasource")
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		return dataSource;
	}

	/**
	 * Alias for the primary MySQL data source.
	 *
	 * @return DataSource: The primary MySQL data source.
	 */
	@Bean("mysqlJdbcDatasource")
	public DataSource mysqlJdbcDatasource() {
		return mysqlDataSource();
	}

	/**
	 * Configures the named parameter JDBC template for the primary MySQL data
	 * source.
	 *
	 * @param datasource (DataSource): The primary MySQL data source.
	 * @return NamedParameterJdbcTemplate: The named parameter JDBC template.
	 */
	@Bean("mysqlJdbcTemplate")
	public NamedParameterJdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlJdbcDatasource") DataSource datasource) {
		return new NamedParameterJdbcTemplate(datasource);
	}

	/**
	 * Configures another MySQL data source.
	 *
	 * @return DataSource: Another MySQL data source.
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.myotherdatasource")
	public DataSource mysqlOtherDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		return dataSource;
	}

	/**
	 * Alias for another MySQL data source.
	 *
	 * @return DataSource: Another MySQL data source.
	 */
	@Bean("mysqlJdbcOtherDatasource")
	public DataSource mysqlJdbcOtherDatasource() {
		return mysqlOtherDataSource();
	}

	/**
	 * Configures the JDBC template for the other MySQL data source.
	 *
	 * @param datasource (DataSource): Another MySQL data source.
	 * @return JdbcTemplate: The JDBC template.
	 */
	@Bean("mysqlJdbcOtherTemplate")
	public JdbcTemplate myOtherSqlJdbcTemplate(@Qualifier("mysqlJdbcOtherDatasource") DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
}
