package com.eventattend.portal.dto;

import java.util.List;

public class AttendeeDTO {
	
	private EventDTO eventDTO = null;	
	private SessionDTO sessionDTO = null;
	
	private List attendeeList = null;
	
	private String hiddenValue = null;
	private String attendeeCount = null;
	
	private int eventLikeCount = 0;
	private int eventLikeCountSum = 0;
	private int sessionLikeCount = 0;
	private int sessionCommentLikeCount = 0;
	private String sessionCommentId = null;
	private String sessionCommentSessId = null;
	private String eventId = null;
	private String hiddenLikeId = null;
	private String currentUserProfileId = null;
	
	
	public String getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	public String getAttendeeCount() {
		return attendeeCount;
	}
	
	public void setAttendeeCount(String attendeeCount) {
		this.attendeeCount = attendeeCount;
	}
	public List getAttendeeList() {
		return attendeeList;
	}
	public void setAttendeeList(List attendeeList) {
		this.attendeeList = attendeeList;
	}
	public EventDTO getEventDTO() {
		return eventDTO;
	}
	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}
	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}
	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}

	public int getSessionLikeCount() {
		return sessionLikeCount;
	}

	public void setSessionLikeCount(int sessionLikeCount) {
		this.sessionLikeCount = sessionLikeCount;
	}

	public String getSessionCommentId() {
		return sessionCommentId;
	}

	public void setSessionCommentId(String sessionCommentId) {
		this.sessionCommentId = sessionCommentId;
	}

	public String getSessionCommentSessId() {
		return sessionCommentSessId;
	}

	public void setSessionCommentSessId(String sessionCommentSessId) {
		this.sessionCommentSessId = sessionCommentSessId;
	}

	public String getHiddenLikeId() {
		return hiddenLikeId;
	}

	public void setHiddenLikeId(String hiddenLikeId) {
		this.hiddenLikeId = hiddenLikeId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getEventLikeCount() {
		return eventLikeCount;
	}

	public void setEventLikeCount(int eventLikeCount) {
		this.eventLikeCount = eventLikeCount;
	}

	public int getEventLikeCountSum() {
		return eventLikeCountSum;
	}

	public void setEventLikeCountSum(int eventLikeCountSum) {
		this.eventLikeCountSum = eventLikeCountSum;
	}

	public int getSessionCommentLikeCount() {
		return sessionCommentLikeCount;
	}

	public void setSessionCommentLikeCount(int sessionCommentLikeCount) {
		this.sessionCommentLikeCount = sessionCommentLikeCount;
	}

	public String getCurrentUserProfileId() {
		return currentUserProfileId;
	}

	public void setCurrentUserProfileId(String currentUserProfileId) {
		this.currentUserProfileId = currentUserProfileId;
	}


	
}
