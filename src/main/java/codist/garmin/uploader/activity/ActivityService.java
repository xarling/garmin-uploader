package codist.garmin.uploader.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xander Arling
 *
 */
@Service
@Transactional(readOnly=true)
public class ActivityService {
	private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);
	
	@Autowired
	private ActivityRepository activityRepository;
	
	public Iterable<Activity> findAll() {
		return activityRepository.findAll();
	}
	
	/**
	 * Save an activity
	 * @param activity
	 * @return
	 */
	@Transactional(readOnly=false)
	public Activity save(final Activity activity) {
		logger.info("Save activity {}", activity);
		
		return activityRepository.save(activity);
	}
	
	/**
	 * Only save if activity doesn't exist
	 * @return
	 */
	@Transactional(readOnly=false)
	public Activity saveNotExistingActivity(final Activity activity) {
		logger.info("save activity that doesn't exists");
		if (!exists(activity)) {
			return save(activity);
		} else {
			logger.info("activity with strava id <{}> exists already", activity.getStravaId());
		}
		
		return activity;
	}
	
	/**
	 * Find if activity already exists
	 * @param activity
	 * @return
	 */
	private boolean exists(final Activity activity) {
		final Activity existing = activityRepository.findByStravaId(activity.getStravaId());
		return existing != null;
	}
	
	public void setActivityRepository(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

}
