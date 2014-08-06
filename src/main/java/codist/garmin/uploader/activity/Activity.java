package codist.garmin.uploader.activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Xander Arling
 *
 */
@Entity
@Table(name="ACTIVITY")
public class Activity {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name="external_id")
	private String externalId;
	
	@NotNull
	@Column(name="strava_id")
	private Long stravaId;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	public Activity() {
		
	}
	
	/**
	 * Should be using builder pattern here. 
	 * 
	 * @param id
	 * @param externalId
	 * @param stravaId
	 * @param name
	 */
	public Activity(final String externalId, final Long stravaId, final String name) {
		this.setName(name);
		this.externalId = externalId;
		this.stravaId = stravaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Long getStravaId() {
		return stravaId;
	}

	public void setStravaId(Long stravaId) {
		this.stravaId = stravaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", externalId=" + externalId + ", stravaId=" + stravaId + ", name=" + name + "]";
	}
	
	
	
	
}
