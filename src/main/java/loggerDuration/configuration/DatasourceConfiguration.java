package loggerDuration.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import loggerDuration.repository.EventDurationRepository;
import loggerDuration.repository.EventRepo;

@Configuration
@ComponentScan(basePackageClasses = { EventRepo.class, EventDurationRepository.class })
@PropertySource("classpath:datasource.properties")
public class DatasourceConfiguration {

	@Value("h2.driverClassName")
	private String driverClassName;

	@Value("h2.url")
	private String url;

	@Value("h2.username")
	private String username;

	@Value("h2.password")
	private String password;

	@Bean
	DataSource getDataSource() {

		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();

	}

}
