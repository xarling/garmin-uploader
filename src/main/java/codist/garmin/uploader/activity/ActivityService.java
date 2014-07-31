package codist.garmin.uploader.activity;

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
	
	@Autowired
	private ActivityRepository activityRepository;
	
	public Iterable<Activity> findAll() {
		return activityRepository.findAll();
	}
	
	public Activity save(final Activity activity) {
		return activityRepository.save(activity);
	}

}
