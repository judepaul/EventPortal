package com.eventattend.portal.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class EventDTO {
	
	private String eventId = null;         
	private String eventName = null; 	
	private String eventStartDate = null; 	
	private String eventEndDate = null; 	
	private String eventStartTime = null; 	
	private String eventEndTime = null; 	
	private String eventDescription = null; 	
	private String eventLocation = null; 	
	private String eventCountry= null;
	private String eventCity= null;
	private String eventState= null;
	private String eventNoOfDays = null; 	
	private String eventOrganizedBy = null; 	
	private String eventWebsite = null;
	private String sessionTime = null;
	private String eventAttendeeCount = null;
	private String eventShortDesc = null; 
	private String eventTimeZone = null; 
	private String selectedEvent = null;
	private List liveEventList = null;
	private List futureEventList = null;
	private List pastEventList = null;
	
	private List eventAttendeeProfileList = null;
	private String sessionId = null;
	private String sessionName = null;
	private String sessionStartTime = null;
	private String sessionEndTime = null;
	private String sessionKeyNote = null;
	private String sessionEventId = null;
	private String sessionVenueId = null;
	private String sessionVenueName = null;
	private String sessionStartDate = null;
	private String sessionEndDate = null;
	private List sessionList =null;
	private String userId = null;
	private String joinEventDate = null;
	private String[] eventsTag = null;
	private String sessionCommentValue = null;
	private Date eventStartDateWithTime = null;
	private Date eventEndDateWithTime = null;
	
	private boolean joinedEvent ;
	
	private boolean liveSession ;
	private boolean  userInLiveSession;
	private String eventTag = null;
	
	private String sessionTag = null;
	
	private int sessionLikeCount = 0;
	private String sessionCommentId = null;
	private String sessionCommentSessId = null;
	private String userTimeZone = null;
	private Map eventMap = null;
	MapDTO mapDTO = null;
	
	private List eventList = null;
	
	public String getEventShortDesc() {
		return eventShortDesc;
	}
	public void setEventShortDesc(String eventShortDesc) {
		this.eventShortDesc = eventShortDesc;
	}
	public MapDTO getMapDTO() {
		return mapDTO;
	}
	public void setMapDTO(MapDTO mapDTO) {
		this.mapDTO = mapDTO;
	}
	public String[] getEventsTag() {
		return eventsTag;
	}
	public void setEventsTag(String[] eventsTag) {
		this.eventsTag = eventsTag;
	}
	public String getSessionTag() {
		return sessionTag;
	}
	public void setSessionTag(String sessionTag) {
		this.sessionTag = sessionTag;
	}
	public String getEventTag() {
		return eventTag;
	}
	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}
	public boolean isUserInLiveSession() {
		return userInLiveSession;
	}
	public void setUserInLiveSession(boolean userInLiveSession) {
		this.userInLiveSession = userInLiveSession;
	}
	public boolean isLiveSession() {
		return liveSession;
	}
	public void setLiveSession(boolean liveSession) {
		this.liveSession = liveSession;
	}
	public boolean isJoinedEvent() {
		return joinedEvent;
	}
	public void setJoinedEvent(boolean joinedEvent) {
		this.joinedEvent = joinedEvent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJoinEventDate() {
		return joinEventDate;
	}
	public void setJoinEventDate(String joinEventDate) {
		this.joinEventDate = joinEventDate;
	}
	public String getEventAttendeeCount() {
		return eventAttendeeCount;
	}
	public void setEventAttendeeCount(String eventAttendeeCount) {
		this.eventAttendeeCount = eventAttendeeCount;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(String eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public String getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(String eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getEventNoOfDays() {
		return eventNoOfDays;
	}
	public void setEventNoOfDays(String eventNoOfDays) {
		this.eventNoOfDays = eventNoOfDays;
	}
	public String getEventOrganizedBy() {
		return eventOrganizedBy;
	}
	public void setEventOrganizedBy(String eventOrganizedBy) {
		this.eventOrganizedBy = eventOrganizedBy;
	}
	public String getEventWebsite() {
		return eventWebsite;
	}
	public void setEventWebsite(String eventWebsite) {
		this.eventWebsite = eventWebsite;
	}
	public List getLiveEventList() {
		return liveEventList;
	}
	public void setLiveEventList(List liveEventList) {
		this.liveEventList = liveEventList;
	}
	public List getFutureEventList() {
		return futureEventList;
	}
	public void setFutureEventList(List futureEventList) {
		this.futureEventList = futureEventList;
	}
	public List getPastEventList() {
		return pastEventList;
	}
	public void setPastEventList(List pastEventList) {
		this.pastEventList = pastEventList;
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
	public String getSessionVenueName() {
		return sessionVenueName;
	}
	public void setSessionVenueName(String sessionVenueName) {
		this.sessionVenueName = sessionVenueName;
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
	public List getSessionList() {
		return sessionList;
	}
	public void setSessionList(List sessionList) {
		this.sessionList = sessionList;
	}
	public String getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}
	public List getEventAttendeeProfileList() {
		return eventAttendeeProfileList;
	}
	public void setEventAttendeeProfileList(List eventAttendeeProfileList) {
		this.eventAttendeeProfileList = eventAttendeeProfileList;
	}
	public String getSessionCommentValue() {
		return sessionCommentValue;
	}
	public void setSessionCommentValue(String sessionCommentValue) {
		this.sessionCommentValue = sessionCommentValue;
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
	public String getUserTimeZone() {
		return userTimeZone;
	}
	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}
	public String getEventCountry() {
		return eventCountry;
	}
	public void setEventCountry(String eventCountry) {
		this.eventCountry = eventCountry;
	}
	public String getEventCity() {
		return eventCity;
	}
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public List getEventList() {
		return eventList;
	}
	public void setEventList(List eventList) {
		this.eventList = eventList;
	}
	public Map getEventMap() {
		return eventMap;
	}
	public void setEventMap(Map eventMap) {
		this.eventMap = eventMap;
	}
	public String getEventTimeZone() {
		return eventTimeZone;
	}
	public void setEventTimeZone(String eventTimeZone) {
		this.eventTimeZone = eventTimeZone;
	}
	public String getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(String selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	public Date getEventStartDateWithTime() {
		return eventStartDateWithTime;
	}
	public void setEventStartDateWithTime(Date eventStartDateWithTime) {
		this.eventStartDateWithTime = eventStartDateWithTime;
	}
	public Date getEventEndDateWithTime() {
		return eventEndDateWithTime;
	}
	public void setEventEndDateWithTime(Date eventEndDateWithTime) {
		this.eventEndDateWithTime = eventEndDateWithTime;
	}

	
}
