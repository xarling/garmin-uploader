package codist.garmin.uploader.fit.filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Simple implementation of a file reader
 * 
 * @author Xander Arling
 *
 */
@Service
public class FitFileReader implements FileReader {

	private static final Logger logger = LoggerFactory.getLogger(FitFileReader.class);

	@Value("${garmin.fit.folder}")
	private String garminFitFolder;

	@Value("${garmin.fit.extension}")
	private String garminFitExtension;

	/*
	 * (non-Javadoc)
	 * 
	 * @see codist.garmin.uploader.filereader.FileReader#getFilesInDirectory()
	 */
	@Override
	public List<File> getFilesInDirectory() {
		logger.info("Get all files in the directory {} with the extension {}", garminFitFolder, garminFitExtension);
		final List<File> resultList = new ArrayList<>();

		File[] result = new File(garminFitFolder).listFiles((File file) -> file.getName().endsWith(garminFitExtension));

		if (result != null) {
			resultList.addAll(Arrays.asList(result));
			logger.info("{} files found", result.length);
		} else {
			logger.warn("No files found, is {} the correct location?", garminFitFolder);

		}

		return resultList;
	}

}
