package codist.garmin.uploader.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codist.garmin.uploader.fit.FitFileParser;
import codist.garmin.uploader.fit.filereader.FitFileReader;
import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.model.FitStatus;
import codist.garmin.uploader.model.FitStatusChangeNotAllowedException;
import codist.garmin.uploader.repository.FitFileRepository;
import codist.garmin.uploader.util.SortBuilder;

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
		return fitFileRepository.findAll(SortBuilder.sortBy(Direction.DESC, "startTime"));
	}
	
	public Iterable<FitFile> getAllForUpload() {
		return getByStatus(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA);
	}
	
	public Iterable<FitFile> getUploadingToStrava() {
		return getByStatus(FitStatus.UPLOADING_TO_STRAVA);
	}
	
	public Iterable<FitFile> getByStatus(final FitStatus status) {
		return fitFileRepository.findByStatus(status);
	}

	public FitFile getById(final Long id) {
		return fitFileRepository.findOne(id);
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
		checkStateChange(fitFile);
		return fitFileRepository.save(fitFile);
	}

	private void checkStateChange(final FitFile fitFile) {
		if (fitFile.getId() != null) {
			final FitStatus newStatus = fitFile.getStatus();
			final FitFile fitFileFromDb = getById(fitFile.getId());
	
			if (fitFileFromDb != null && !fitFile.getStatus().equals(newStatus)) {
				if (!fitFileFromDb.statusChangeAllowed(fitFile)) {
					logger.error("Not allowed to change status from {} to {}", fitFileFromDb.getStatus(), newStatus);
					throw new FitStatusChangeNotAllowedException("Not allowed to change status");
				}
			}
		}
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
	 * 
	 * @param fitfile
	 * @return
	 */
	private boolean exists(final FitFile fitFile) {
		final FitFile existing = fitFileRepository.findByName(fitFile.getName());
		return existing != null;
	}

	/**
	 * Parse filefile and handle exceptions in case of emergency
	 * 
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
