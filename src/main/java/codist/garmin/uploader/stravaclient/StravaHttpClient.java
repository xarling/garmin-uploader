package codist.garmin.uploader.stravaclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.model.User;
import codist.garmin.uploader.stravaclient.model.AccessToken;
import codist.garmin.uploader.stravaclient.model.Activity;
import codist.garmin.uploader.stravaclient.model.UploadResponse;

@Service
public class StravaHttpClient {
	private static final Logger logger = LoggerFactory.getLogger(StravaHttpClient.class);
	
	//private String athleteInfo = "https://www.strava.com/api/v3/athlete";
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${strava.rest.accessToken}")
	private String restToken;
	
	@Value("${strava.rest.clientId}")
	private String clientId;
	
	@Value("${garmin.fit.folder}")
	private String garminFitFolder;
	
	@Value("${strava.rest.clientSecret}")
	private String clientSecret;
	
	public UploadResponse checkUpload(final FitFile fitFile) {
		try {
			final ResponseEntity<UploadResponse> response = restTemplate.exchange("https://www.strava.com/api/v3/uploads/" + fitFile.getStravaId(), HttpMethod.GET, createAuthenticationEntity(fitFile.getUser().getAccessToken().trim()), UploadResponse.class);
			return response.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.warn("Strava send the error code", e);
			return null;
		}
	}
	
	public UploadResponse uploadActivity(final FitFile fitFile) {
		logger.info("Uploading file {} for user {}", fitFile, fitFile.getUser());
		
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("external_id", fitFile.getName());
		parts.add("data_type", "fit");
		Resource resource = new FileSystemResource(garminFitFolder + fitFile.getName()); 
		parts.add("file", resource);
		
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, createAuthenticationHeaders(fitFile.getUser().getAccessToken()));

		final ResponseEntity<UploadResponse> uploadResponseEntity = restTemplate.exchange("https://www.strava.com/api/v3/uploads?data_type=fit&external_id=" + fitFile.getName(), HttpMethod.POST, requestEntity, UploadResponse.class);
		return uploadResponseEntity.getBody();
	}
	
	public List<Activity> getActivities(final User user) {
		
		try {
			final ResponseEntity<Activity[]> response = restTemplate.exchange("https://www.strava.com/api/v3/athlete/activities", HttpMethod.GET, createAuthenticationEntity(user.getAccessToken().trim()), Activity[].class);
			return Arrays.asList(response.getBody());
		} catch (HttpServerErrorException e) {
			logger.warn("Strava send the error code", e);
			return new ArrayList<>();
		}
		
	}
	
	/**
	 * Get the access token for the user
	 * @param code
	 * @return
	 */
	public AccessToken getAccessToken(final String code) {
		final ResponseEntity<AccessToken> response = restTemplate.exchange(getAccessTokenUrl(code) , HttpMethod.POST, createAuthenticationEntity(null), AccessToken.class);
		return response.getBody();
	}
	
	private HttpHeaders createAuthenticationHeaders(final String restToken) {
		final HttpHeaders headers = new HttpHeaders();
		if (restToken != null) {
			headers.set("Authorization", "Bearer " + restToken);
		}
		return headers;
	}
	
	private String getAccessTokenUrl(final String code) {
		return new StringBuilder("https://www.strava.com/oauth/token?approval_prompt=force&client_id=").append(this.clientId).append("&client_secret=").append(this.clientSecret).append("&code=").append(code).toString();

	}
	
	private HttpEntity<byte[]> createAuthenticationEntity(final String restToken) {
		return new HttpEntity<byte[]>(createAuthenticationHeaders(restToken));
	}

}
