package codist.garmin.uploader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
//@EnableTransactionManagement
@PropertySource("classpath:garmin-uploader.properties")
@Import({RestClientConfig.class})
public class Config {

	
	

	

}
