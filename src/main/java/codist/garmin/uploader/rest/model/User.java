package codist.garmin.uploader.rest.model;

import java.util.List;

public class User {
	
	private Long id;
	
	private String stravaUserId;
	
	private String username;
	
	private String accessToken;
	
	public User() {
		
	}
	
	public User(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getStravaUserId() {
		return stravaUserId;
	}

	public void setStravaUserId(String stravaUserId) {
		this.stravaUserId = stravaUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", stravaUserId=" + stravaUserId + ", username=" + username + ", accessToken="
				+ accessToken + "]";
	}
	
	

}
