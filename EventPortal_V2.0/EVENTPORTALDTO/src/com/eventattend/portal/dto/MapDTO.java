package com.eventattend.portal.dto;

public class MapDTO {

	private String latitude  = null;
	private String longitude = null;
	private String accuracy = null;
	private String status = null;
	private String gmapKey =  null;
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGmapKey() {
		return gmapKey;
	}
	public void setGmapKey(String gmapKey) {
		this.gmapKey = gmapKey;
	}
	
	
}
