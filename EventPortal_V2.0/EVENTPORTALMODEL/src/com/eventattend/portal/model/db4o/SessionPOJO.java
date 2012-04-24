
package com.eventattend.portal.model.db4o;

public class SessionPOJO {

	private String sessionId;
	private String sessionName;
	private String sessionStartTime;
	private String sessionEndTime;
	private String sessionKeyNote;
	private String sessionEventId;
	private String sessionVenueId;
	private String sessionStartDate;
	private String sessionEndDate;
	private String sessionTag;
	private int sessionLikeCount = 0;
	
	public SessionPOJO() {		
		this.sessionId = sessionId;
	}
	
	public SessionPOJO(String sessionId,String id){		
		this.sessionId = sessionId;
	}
	
	public SessionPOJO(String sessionEventId) {
		super();
		this.sessionEventId = sessionEventId;
	}	
		
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	public String getSessionKeyNote() {
		return sessionKeyNote;
	}
	public void setSessionKeyNote(String sessionKeyNote) {
		this.sessionKeyNote = sessionKeyNote;
	}
	public String getSessionEventId() {
		return sessionEventId;
	}
	public void setSessionEventId(String sessionEventId) {
		this.sessionEventId = sessionEventId;
	}
	public String getSessionVenueId() {
		return sessionVenueId;
	}
	public void setSessionVenueId(String sessionVenueId) {
		this.sessionVenueId = sessionVenueId;
	}
	public String getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public String getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public String getSessionTag() {
		return sessionTag;
	}

	public void setSessionTag(String sessionTag) {
		this.sessionTag = sessionTag;
	}

	public int getSessionLikeCount() {
		return sessionLikeCount;
	}

	public void setSessionLikeCount(int sessionLikeCount) {
		this.sessionLikeCount = sessionLikeCount;
	}	
	
}

