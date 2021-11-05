package loggerDuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import loggerDuration.core.IProcessLoggerService;

@SpringBootApplication
@ComponentScan(basePackages = { "loggerDuration.repository", "loggerDuration.core" })
@EnableJpaRepositories
@Transactional
public class LoggerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LoggerApplication.class);

	@Autowired
	private IProcessLoggerService processLogger;

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Initialize the service..");
		processLogger.initialize();
	}

}
