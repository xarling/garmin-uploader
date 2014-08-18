package codist.garmin.uploader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.model.FitStatus;

public interface FitFileRepository extends JpaRepository<FitFile, Long> {

	
	
	/**
	 * Find fitfile by name (filename), which correlates to the externalId of strava
	 * 
	 * @param name
	 * @return
	 */
	public FitFile findByName(final String name);
	
	
	public Iterable<FitFile> findByStatus(final FitStatus fitStatus);
}
