package codist.garmin.uploader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		logger.info("Starting application garmin-updater");

		try {
			new AnnotationConfigApplicationContext("codist.garmin.uploader");
		} catch (Exception e) {
			logger.error("Something terrible happened. Application is shutting down", e);
		}
	}

}
