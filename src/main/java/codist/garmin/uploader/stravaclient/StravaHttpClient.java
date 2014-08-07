package codist.garmin.uploader.stravaclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import codist.garmin.uploader.stravaclient.model.Activity;

@Service
public class StravaHttpClient {
	private static final Logger logger = LoggerFactory.getLogger(StravaHttpClient.class);
	
	private String athleteInfo = "https://www.strava.com/api/v3/athlete";
	private String activityInfo = "https://www.strava.com/api/v3/athlete/activities";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${strava.rest.accessToken}")
	private String restToken;
	
	public String getToken() {
		ResponseEntity<String> response = restTemplate.exchange(athleteInfo, HttpMethod.GET, createAuthenticationEntity(), String.class);
		return response.getBody();
	}
	
	public List<Activity> getActivities() {
		try {
			final ResponseEntity<Activity[]> response = restTemplate.exchange(activityInfo, HttpMethod.GET, createAuthenticationEntity(), Activity[].class);
			return Arrays.asList(response.getBody());
		} catch (HttpServerErrorException e) {
			logger.warn("Strava send the error code", e);
			return new ArrayList<>();
		}
		
	}
	
	private HttpHeaders createAuthenticationHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + restToken);
		return headers;
	}
	
	private HttpEntity<byte[]> createAuthenticationEntity() {
		return new HttpEntity<byte[]>(createAuthenticationHeaders());
	}

}
