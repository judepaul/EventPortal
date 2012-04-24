package com.eventattend.portal.model.db4o;

public class UserEventMapPOJO {
	private String uemId;
	private String uemUserId;
	private String uemEventId;
	private String uemDate;

	public UserEventMapPOJO() {		
	}
	
	public UserEventMapPOJO(String uemEventId) {
		this.uemEventId = uemEventId;
	}
	
	public UserEventMapPOJO(String uemUserId,String uemEventId) {
		super();
		this.uemUserId = uemUserId;
		this.uemEventId = uemEventId;
	}	
	
	public UserEventMapPOJO(String uemUserId,String id1, String id2) {
		this.uemUserId = uemUserId;
	}
	

	public String getUemId() {
		return uemId;
	}

	public void setUemId(String uemId) {
		this.uemId = uemId;
	}

	public String getUemUserId() {
		return uemUserId;
	}

	public void setUemUserId(String uemUserId) {
		this.uemUserId = uemUserId;
	}

	public String getUemEventId() {
		return uemEventId;
	}

	public void setUemEventId(String uemEventId) {
		this.uemEventId = uemEventId;
	}

	public String getUemDate() {
		return uemDate;
	}

	public void setUemDate(String uemDate) {
		this.uemDate = uemDate;
	}
	
	
}
