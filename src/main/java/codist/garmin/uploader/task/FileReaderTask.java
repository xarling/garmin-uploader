package codist.garmin.uploader.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import codist.garmin.uploader.filereader.FileReader;
import codist.garmin.uploader.stravaclient.StravaHttpClient;

@Component
public class FileReaderTask {
	private static final Logger logger = LoggerFactory.getLogger(FileReaderTask.class);

	@Autowired
	private FileReader fileReader;
	
	@Autowired
	private StravaHttpClient stravaHttpClient;
	
	//@Scheduled(fixedDelay=5000)
	public void getAvailableFitFiles() {
	    logger.debug("Starting to pickup files" );
	    fileReader.getFilesInDirectory();
	}
	
	
	@Scheduled(fixedDelay=50000)
	public void uploadAvailableFitFiles() {
	    logger.debug("Starting to upload files" );
	    
	    stravaHttpClient.getActivities().forEach((x) -> { logger.info("Activity {}", x); });
	    
	}

}
