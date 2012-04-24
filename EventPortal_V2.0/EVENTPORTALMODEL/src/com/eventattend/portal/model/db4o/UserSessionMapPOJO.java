package com.eventattend.portal.model.db4o;

public class UserSessionMapPOJO {

	private String usmId;	
	private String usmUserId;
	private String usmSessionId;
	private String usmStatus;
	public UserSessionMapPOJO() {		
	}
	
	public UserSessionMapPOJO(String usmSessionId) {	
		this.usmSessionId = usmSessionId;
	}	
	public UserSessionMapPOJO(String usmUserId,String usmSessionId) {	
		this.usmUserId = usmUserId;
		this.usmSessionId = usmSessionId;
	}	

	public UserSessionMapPOJO(String usmUserId,String id1, String id2) {	
		this.usmUserId = usmUserId;		
	}	
	
	public String getUsmStatus() {
		return usmStatus;
	}

	public void setUsmStatus(String usmStatus) {
		this.usmStatus = usmStatus;
	}

	public String getUsmId() {
		return usmId;
	}

	public void setUsmId(String usmId) {
		this.usmId = usmId;
	}

	public String getUsmUserId() {
		return usmUserId;
	}

	public void setUsmUserId(String usmUserId) {
		this.usmUserId = usmUserId;
	}

	public String getUsmSessionId() {
		return usmSessionId;
	}

	public void setUsmSessionId(String usmSessionId) {
		this.usmSessionId = usmSessionId;
	}

	

}
