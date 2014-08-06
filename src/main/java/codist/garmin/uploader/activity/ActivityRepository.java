package codist.garmin.uploader.activity;

import org.springframework.data.repository.CrudRepository;


/**
 * @author Xander Arling
 *
 */
interface ActivityRepository extends CrudRepository<Activity, Long> {
	
	
	 /**
	  * Find activity by strava id, which is unique for strava
	 * @param stravaId
	 * @return
	 */
	public Activity findByStravaId(final Long stravaId);

}
