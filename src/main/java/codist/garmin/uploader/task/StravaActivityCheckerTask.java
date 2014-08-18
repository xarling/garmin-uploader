package codist.garmin.uploader.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import codist.garmin.uploader.model.Activity;
import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.model.FitStatus;
import codist.garmin.uploader.model.User;
import codist.garmin.uploader.service.ActivityService;
import codist.garmin.uploader.service.FitService;
import codist.garmin.uploader.service.UserService;
import codist.garmin.uploader.stravaclient.StravaHttpClient;
import codist.garmin.uploader.stravaclient.model.UploadResponse;

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
	private FitService fitService;

	@Autowired
	private UserService userService;

	@Autowired
	private StravaHttpClient stravaHttpClient;

	@Scheduled(fixedDelay = 50000)
	public void uploadAvailableFitFiles() {

		Iterable<FitFile> files = fitService.getAllForUpload();

		logger.info("Running upload task for {} files");

		for (FitFile fitFile : files) {
			if (fitFile.getUser() != null) {
				logger.info("Starting to upload file for user {}", fitFile.getUser());
				fitFile.changeStatus(FitStatus.UPLOADING_TO_STRAVA);

				try {

					final UploadResponse response = stravaHttpClient.uploadActivity(fitFile);

					logger.info("Status: {}", response);

					fitFile.setStravaId(response.getId());

					fitService.save(fitFile);
				} catch (HttpServerErrorException | HttpClientErrorException e) {
					logger.error(e.getResponseBodyAsString());

					fitFile.changeStatus(FitStatus.CANNOT_UPLOAD_TO_STRAVA);
					fitService.save(fitFile);

					logger.error("Something went wrong", e);
				}
			} else {
				logger.error("User is null on a marked for download fitFile {}", fitFile);
			}
		}

	}

	@Scheduled(fixedDelay = 50000, initialDelay = 10000)
	public void checkIfFileIsUploaded() {
		for (FitFile file : fitService.getUploadingToStrava()) {
			final UploadResponse response = stravaHttpClient.checkUpload(file);

			if (response != null) {
				switch (response.getStatus()) {
				case "Your activity is ready.":
					file.changeStatus(FitStatus.UPLOADED_TO_STRAVA);
					fitService.save(file);
					break;
				case "Your activity is still being processed.":
					logger.info("Activity is still beeing processed {}", file);
					break;
				case "There was an error processing your activity.":
					logger.info("There was an error processing {}", file);
					break;
				default:
					logger.error("Don't know what to do with this activity {}", file);
					break;
				}
			} else {
				logger.error("Don't know what to do with this activity {}", file);
			}
		}
	}

	// @Scheduled(fixedDelay = 50000)
	public void getFiles() {
		logger.info("Starting to upload files");
		Iterable<User> users = userService.getAll();

		for (User user : users) {
			if (user.getAccessToken() != null) {
				try {
					stravaHttpClient.getActivities(user).forEach((activity) -> {
						logger.info("Activity: {}", activity);
					});
				} catch (HttpClientErrorException e) {
					logger.error("Error", e);
				}
			}
		}

	}

	/**
	 * Uses the activity service to save the strava activity into the
	 * application
	 * 
	 * @param stravaActivity
	 */
	private void storeActivity(final codist.garmin.uploader.stravaclient.model.Activity stravaActivity) {
		final Activity activity = new Activity(stravaActivity.getExternalId(), stravaActivity.getId(), stravaActivity.getName(),
				stravaActivity.getAthlete().getId());
		activityService.saveNotExistingActivity(activity);
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
