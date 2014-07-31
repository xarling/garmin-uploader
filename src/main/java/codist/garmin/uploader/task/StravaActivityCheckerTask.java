package codist.garmin.uploader.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import codist.garmin.uploader.activity.Activity;
import codist.garmin.uploader.activity.ActivityService;
import codist.garmin.uploader.stravaclient.StravaHttpClient;

/**
 * Task checks for new activities in Strava and adds them to the application
 * 
 * @author Xander Arling
 *
 */
@Component
public class StravaActivityCheckerTask {
	private static final Logger logger = LoggerFactory.getLogger(StravaActivityCheckerTask.class);
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private StravaHttpClient stravaHttpClient;
	
	/**
	 * 
	 */
	@Scheduled(fixedDelay=50000)
	public void uploadAvailableFitFiles() {
	    logger.info("Starting to upload files" );
	    stravaHttpClient.getActivities().forEach((activity) -> { storeActivity(activity); });
	}
	
	/**
	 * Uses the activity service to save the strava activity into the application
	 * @param stravaActivity
	 */
	private void storeActivity(final codist.garmin.uploader.stravaclient.model.Activity stravaActivity) {
		final Activity activity = new Activity(stravaActivity.getExternalId(), stravaActivity.getId(), stravaActivity.getName());
		activityService.save(activity);
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	public StravaHttpClient getStravaHttpClient() {
		return stravaHttpClient;
	}

	public void setStravaHttpClient(StravaHttpClient stravaHttpClient) {
		this.stravaHttpClient = stravaHttpClient;
	}

	

}
