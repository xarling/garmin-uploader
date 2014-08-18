package codist.garmin.uploader.stravaclient.model;

public class UploadResponse {
	
	private Long id;
	private String externalId;
	private String error;
	private String status;
	
	private String activityId;

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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		return "UploadResponse [id=" + id + ", externalId=" + externalId + ", error=" + error + ", status=" + status + ", activityId="
				+ activityId + "]";
	}

	
	
	
}
