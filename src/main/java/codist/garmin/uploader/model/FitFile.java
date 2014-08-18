package codist.garmin.uploader.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="FIT_FILE")
public class FitFile {
	
	private static final Logger logger = LoggerFactory.getLogger(FitFile.class);
	
	private static final Map<FitStatus, List<FitStatus>> allowedStateChanges = new HashMap<>();
	
	static {
		allowedStateChanges.put(FitStatus.FILE_ON_DISK, Arrays.asList(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA));
		allowedStateChanges.put(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA, Arrays.asList(FitStatus.CANNOT_UPLOAD_TO_STRAVA));
		allowedStateChanges.put(FitStatus.CANNOT_UPLOAD_TO_STRAVA, Arrays.asList(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA, FitStatus.CANNOT_UPLOAD_TO_STRAVA, FitStatus.UPLOADED_TO_STRAVA));
		allowedStateChanges.put(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA, Arrays.asList(FitStatus.UPLOADING_TO_STRAVA));
		allowedStateChanges.put(FitStatus.UPLOADING_TO_STRAVA, Arrays.asList(FitStatus.UPLOADED_TO_STRAVA, FitStatus.CANNOT_UPLOAD_TO_STRAVA));
	}

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="UPLOADED")
	private Boolean uploaded;
	
	
	@Column(name="TOTAL_TIME_IN_SECONDS")
	private Double totalTimeInSeconds;
	
	@Column(name="AVG_SPEED_IN_METERS_PER_SECOND")
	private Double avgSpeedInMetersPerSecond;
	
	@Column(name="TOTAL_DISTANCE_IN_METERS")
	private Double totalDistanceInMeters;
	
	@Column(name="AVG_HEART_BPM")
	private Short avgHeartBpm;
	
	@Column(name="TIME_CREATED")
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime timeCreated;
	
	@Column(name="START_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime startTime;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name="SPORT")
	private String sport;
	
	@ManyToOne
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private User user;
	
	@Column(name="STATUS")
	@javax.persistence.Enumerated(EnumType.STRING)
	private FitStatus status;
	
	@Column(name="STRAVA_ID")
	private Long stravaId;
	
	
	
	public FitFile() {
		
	}
	
	public void changeStatus(FitStatus newStatus) {
		if (statusChangeAllowed(newStatus)) {
			setStatus(newStatus);
		} else {
			logger.error("Not allowed to change status from {} to {}", getStatus(), newStatus);
			throw new FitStatusChangeNotAllowedException();
		}
	}
	
	public boolean statusChangeAllowed(FitFile newFitFile) {
		return statusChangeAllowed(newFitFile.getStatus());
	}
	
	public boolean statusChangeAllowed(FitStatus status) {
		final List<FitStatus> allowed = allowedStateChanges.get(this.getStatus());
		return allowed.contains(status);
	}
	
	public FitFile(final String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getUploaded() {
		return uploaded;
	}

	public void setUploaded(Boolean uploaded) {
		this.uploaded = uploaded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotalTimeInSeconds() {
		return totalTimeInSeconds;
	}

	public void setTotalTimeInSeconds(Double totalTimeInSeconds) {
		this.totalTimeInSeconds = totalTimeInSeconds;
	}

	public Double getAvgSpeedInMetersPerSecond() {
		return avgSpeedInMetersPerSecond;
	}

	public void setAvgSpeedInMetersPerSecond(Double avgSpeedInMetersPerSecond) {
		this.avgSpeedInMetersPerSecond = avgSpeedInMetersPerSecond;
	}

	public Double getTotalDistanceInMeters() {
		return totalDistanceInMeters;
	}

	public void setTotalDistanceInMeters(Double totalDistanceInMeters) {
		this.totalDistanceInMeters = totalDistanceInMeters;
	}

	public Short getAvgHeartBpm() {
		return avgHeartBpm;
	}

	public void setAvgHeartBpm(Short avgHeartBpm) {
		this.avgHeartBpm = avgHeartBpm;
	}

	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public FitStatus getStatus() {
		return status;
	}


	public void setStatus(FitStatus status) {
		this.status = status;
	}

	public Long getStravaId() {
		return stravaId;
	}

	public void setStravaId(Long stravaId) {
		this.stravaId = stravaId;
	}
	

}
