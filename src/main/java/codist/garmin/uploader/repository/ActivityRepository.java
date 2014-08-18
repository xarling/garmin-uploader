package codist.garmin.uploader.repository;

import org.springframework.data.repository.CrudRepository;

import codist.garmin.uploader.model.Activity;


/**
 * @author Xander Arling
 *
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {
	
	
	 /**
	  * Find activity by strava id, which is unique for strava
	 * @param stravaId
	 * @return
	 */
	public Activity findByStravaId(final Long stravaId);

}
