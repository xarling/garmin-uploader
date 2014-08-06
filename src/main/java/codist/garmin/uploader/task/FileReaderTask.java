package codist.garmin.uploader.task;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import codist.garmin.uploader.filereader.FileReader;
import codist.garmin.uploader.fit.FitFile;
import codist.garmin.uploader.fit.FitFileParser;

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
	
	@Autowired
	private FitFileParser fileParser;
	
	
	
	//@Scheduled(fixedDelay=5000)
	public void getAvailableFitFiles() {
	    logger.debug("Starting to pickup files" );
	    final List<File> files = fileReader.getFilesInDirectory();
	    
	    
	    final List<String> fitFileNames = files.stream().map(file -> file.getName()).collect(Collectors.toList());
	    
	}



	public FileReader getFileReader() {
		return fileReader;
	}



	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}
	
	
	
}
