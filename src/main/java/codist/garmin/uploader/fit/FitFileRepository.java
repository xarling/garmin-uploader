package codist.garmin.uploader.fit;

import org.springframework.data.repository.CrudRepository;

interface FitFileRepository extends CrudRepository<FitFile, Long> {

	/**
	 * Find fitfile by name (filename), which correlates to the externalId of strava
	 * 
	 * @param name
	 * @return
	 */
	public FitFile findByName(final String name);
}
