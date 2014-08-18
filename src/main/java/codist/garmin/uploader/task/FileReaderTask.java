package codist.garmin.uploader.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import codist.garmin.uploader.fit.FitFileParser;
import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.service.FitService;

/**
 * Tasks checks the disk for new activities in the fit directory 
 *  
 * @author Xander Arling
 *
 */
@Component
public class FileReaderTask {
	private static final Logger logger = LoggerFactory.getLogger(FileReaderTask.class);

	
	@Autowired
	private FitFileParser fileParser;
	
	@Autowired
	private FitService fitService;
	
	@Scheduled(fixedDelay=1000 * 60)
	public void getAvailableFitFiles() {
	    logger.debug("Starting to pickup files" );
	    final List<FitFile> files = fitService.getAllFilesInDirectory();

	    files.forEach((fitFile) -> storeFitFile(fitFile));
	}

	
	/**
	 * @param fitFile
	 */
	private void storeFitFile(final FitFile fitFile) {
		fitService.saveNotExisting(fitFile);
	}
	
}
