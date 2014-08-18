package codist.garmin.uploader.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	/**
	 * EAGER because I almost always need the user. 
	 * @JsonManagedReference is used to break the cycle, for Jackson to be able to marshall
	 * 
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user", fetch=FetchType.EAGER)
	@JsonBackReference@JsonManagedReference
	private List<FitFile> fitFiles;
	
	@Column(name="strava_user_id")
	private String stravaUserId;
	
	@NotNull
	@Column(name="username")
	private String username;
	
	@Column(name="access_token")
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

	public List<FitFile> getFitFiles() {
		return fitFiles;
	}

	public void setFitFiles(List<FitFile> fitFiles) {
		this.fitFiles = fitFiles;
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
		return "User [id=" + id + ", fitFiles=" + fitFiles + ", stravaUserId=" + stravaUserId + ", username=" + username + ", accessToken="
				+ accessToken + "]";
	}
	
	

}
