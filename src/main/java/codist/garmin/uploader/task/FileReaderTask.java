package codist.garmin.uploader.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import codist.garmin.uploader.filereader.FileReader;

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
	private FileReader fileReader;
	
	
	
	//@Scheduled(fixedDelay=5000)
	public void getAvailableFitFiles() {
	    logger.debug("Starting to pickup files" );
	    fileReader.getFilesInDirectory();
	}



	public FileReader getFileReader() {
		return fileReader;
	}



	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}
	
	
	
}
