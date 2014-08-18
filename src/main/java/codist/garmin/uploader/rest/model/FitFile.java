package codist.garmin.uploader.rest.model;

import java.time.LocalDateTime;

public class FitFile {
	
	private Long id;
	
	private String name;
	
	private Boolean uploaded;
	
	
	private Double totalTimeInSeconds;
	
	private Double avgSpeedInMetersPerSecond;
	
	private Double totalDistanceInMeters;
	
	private Short avgHeartBpm;
	
	private LocalDateTime timeCreated;
	
	private LocalDateTime startTime;
	
	private String productId;
	
	private String serialNumber;
	
	private String sport;
	
	private User user;
	
	private FitStatus status;
	
	private Long stravaId;
	
	
	
	public FitFile() {
		
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
