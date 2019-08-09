package com.javawiz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Bean(name = "dsPrimary")
	@ConfigurationProperties(prefix = "datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dsSecondary")
	@Primary
	@ConfigurationProperties(prefix = "datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "jdbcPrimary")
	@Autowired
	public JdbcTemplate slaveJdbcTemplate(@Qualifier("dsPrimary")DataSource primary) {
		return new JdbcTemplate(primary);
	}
	
	@Bean(name = "jdbcSecondary")
	@Autowired
	public JdbcTemplate masterJdbcTemplate(@Qualifier("dsSecondary")DataSource secondary) {
		return new JdbcTemplate(secondary);
	}
}