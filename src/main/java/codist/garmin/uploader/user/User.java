package codist.garmin.uploader.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import codist.garmin.uploader.fit.FitFile;

@Entity
@Table(name="FIT_FILE")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private List<FitFile> fitFiles;
	
	@Column(name="strava_user_id")
	private String stravaUserId;
	
	@Column(name="username")
	private String username;

}
