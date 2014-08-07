package codist.garmin.uploader.fit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codist.garmin.uploader.fit.filereader.FitFileReader;

@Service
public class FitService {
	
	private static final Logger logger = LoggerFactory.getLogger(FitService.class);
	
	@Autowired
	private FitFileReader fitFileReader;
	
	@Autowired
	private FitFileParser parser;
	
	@Autowired
	private FitFileRepository fitFileRepository;
	
	
	public Iterable<FitFile> getAll() {
		return fitFileRepository.findAll();
	}
	
	/**
	 * @return
	 */
	public List<FitFile> getAllFilesInDirectory() {
		final List<File> fitFiles = fitFileReader.getFilesInDirectory();
		return fitFiles.stream().map(fitFile -> parseFitFile(fitFile)).collect(Collectors.toList());
	}
	
	@Transactional
	public FitFile save(final FitFile fitFile) {
		return fitFileRepository.save(fitFile);
	}
	
	@Transactional
	public FitFile saveNotExisting(final FitFile fitFile) {
		
		if (exists(fitFile)) {
			logger.info("File with name {} already exists in the database", fitFile.getName());
			return fitFile;
		} else {
			logger.info("Save file with name {}", fitFile.getName());
			return fitFileRepository.save(fitFile);
		}
		
	}
	
	/**
	 * Find if fitfile already exists
	 * @param fitfile
	 * @return
	 */
	private boolean exists(final FitFile fitFile) {
		final FitFile existing = fitFileRepository.findByName(fitFile.getName());
		return existing != null;
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
