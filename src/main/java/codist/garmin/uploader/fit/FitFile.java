package codist.garmin.uploader.fit;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import codist.garmin.uploader.user.User;

@Entity
@Table(name="FIT_FILE")
public class FitFile {
	
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
	private LocalDate timeCreated;
	
	@Column(name="START_TIME")
	private LocalDate startTime;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name="SPORT")
	private String sport;
	
	@Transient
	//@OneToMany(mappedBy="FitFile")
	private User user;
	
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

	public LocalDate getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDate timeCreated) {
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


	public LocalDate getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	

}
