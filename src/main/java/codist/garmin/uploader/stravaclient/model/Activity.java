package codist.garmin.uploader.stravaclient.model;

public class Activity {
	
	private Long id;
	
	/**
	 * indicates level of detail
	 */
	private Integer resourceState;
	
	/**
	 * provided at upload
	 */
	private String externalId;
	
	/**
	 * meta or summary representation of the athlete
	 */
	private Athlete athlete;
	
	private String name;
	
	private String description;
	/**
	 * distance in meters
	 */
	private Float distance;
	
	/**
	 * seconds
	 */
	private Integer movingTime;

	/**
	 * seconds
	 */
	private Integer elapsedTime; 
	
	/**
	 * Meters
	 */
	private Float totalElevationGain;

	/**
	 * 	activity type, ie. ride, run, swim, etc.
	 */
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getResourceState() {
		return resourceState;
	}

	public void setResourceState(Integer resourceState) {
		this.resourceState = resourceState;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Integer getMovingTime() {
		return movingTime;
	}

	public void setMovingTime(Integer movingTime) {
		this.movingTime = movingTime;
	}

	public Integer getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Float getTotalElevationGain() {
		return totalElevationGain;
	}

	public void setTotalElevationGain(Float totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", resourceState=" + resourceState + ", externalId=" + externalId + ", athlete=" + athlete
				+ ", name=" + name + ", description=" + description + ", distance=" + distance + ", movingTime=" + movingTime
				+ ", elapsedTime=" + elapsedTime + ", totalElevationGain=" + totalElevationGain + ", type=" + type + "]";
	}
	
	
	
	/*start_date:	time string
	start_date_local:	time string
	time_zone:	string
	start_latlng:	[latitude, longitude]
	end_latlng:	[latitude, longitude]
	location_city:	string
	location_state:	string
	location_country:	string
	achievement_count:	integer
	kudos_count:	integer
	comment_count:	integer
	athlete_count:	integer
	photo_count:	integer
	map:	object 
	detailed representation of the route
	trainer:	boolean
	commute:	boolean
	manual:	boolean
	private:	boolean
	flagged:	boolean
	workout_type:	integer 
	for runs only, 0 -> ‘default’, 1 -> ‘race’, 2 -> ‘long run’, 3 -> ‘intervals’
	gear_id:	string 
	corresponds to a bike or pair of shoes included in athlete details
	gear:	object 
	gear summary
	average_speed:	float 
	meters per second
	max_speed:	float 
	meters per second
	average_cadence:	float 
	RPM, if provided at upload
	average_temp:	integer 
	degrees Celsius, if provided at upload
	average_watts:	float rides only
	kilojoules:	float rides only 
	uses estimated power if necessary
	average_heartrate:	integer only if recorded with heartrate 
	average over moving portion
	max_heartrate:	integer only if recorded with heartrate
	calories:	float 
	kilocalories, uses kilojoules for rides and speed/pace for runs
	truncated:	integer 
	only present if activity is owned by authenticated athlete, returns 0 if not truncated by privacy zones
	has_kudoed:	boolean 
	if the authenticated athlete has kudoed this activity
	segment_efforts:	array of objects 
	array of summary representations of the segment efforts
	splits_metric:	array of metric split summaries 
	running activities only
	splits_standard:	array of standard split summaries 
	running activities only
	best_efforts:	array of best effort summaries 
	running activities only*/

}
