package codist.garmin.uploader.fit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codist.garmin.uploader.filereader.FitFileReader;

@Service
public class FitService {
	
	private static final Logger logger = LoggerFactory.getLogger(FitService.class);
	
	@Autowired
	private FitFileReader fitFileReader;
	
	@Autowired
	private FitFileParser parser;
	
	
	/**
	 * @return
	 */
	public List<FitFile> getAllFilesInDirectory() {
		final List<File> fitFiles = fitFileReader.getFilesInDirectory();
		return fitFiles.stream().map(fitFile -> parseFitFile(fitFile)).collect(Collectors.toList());
	}
	
	/**
	 * Parse filefile and handle exceptions in case of emergency
	 * @param file
	 * @return
	 */
	private FitFile parseFitFile(final File file) {
		try {
			return parser.parseFile(file);
		} catch (IOException e) {
			logger.error("Something is wrong parsing file {}", file.getName(), e);
			return new FitFile(file.getName());
		}
	}

}
