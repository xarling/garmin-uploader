package codist.garmin.uploader.config;

import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration
@PropertySource("classpath:garmin-uploader.properties")
@ComponentScan("codist.garmin.uploader")
@Import({RestClientConfig.class})
public class Config {

	
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		logger.info("Starting application garmin-updater");
		SpringApplication.run(Config.class, args);

	}

	

}
