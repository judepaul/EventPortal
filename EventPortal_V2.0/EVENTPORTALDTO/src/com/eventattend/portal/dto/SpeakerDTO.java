package com.eventattend.portal.dto;

import java.util.List;

import com.eventattend.portal.dao.DataAccessObject;

public class SpeakerDTO extends DataAccessObject {
	
	private String speakerUserId = null;
	
	private String speakerName = null;
	
	private String speakerFor = null;
	
	private String sessionId = null;
	
	private String sessionName = null;
	
	private String eventName = null;
	
	private ProfileDTO profileDTO = null;
	
	private List sessionList = null;


	public String getSpeakerUserId() {
		return speakerUserId;
	}

	public void setSpeakerUserId(String speakerUserId) {
		this.speakerUserId = speakerUserId;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getSpeakerFor() {
		return speakerFor;
	}

	public void setSpeakerFor(String speakerFor) {
		this.speakerFor = speakerFor;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}

	public List getSessionList() {
		return sessionList;
	}

	public void setSessionList(List sessionList) {
		this.sessionList = sessionList;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	

	
}
