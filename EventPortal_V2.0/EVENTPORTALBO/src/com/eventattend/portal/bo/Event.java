package com.eventattend.portal.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.exceptions.EventPortalException;

public class Event extends BusinessObject {
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
	private String selectedEvent = null;
	private String hiddenAction = null;
	private String profileBio = null;
	private String speakerName = null;		
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
	private String scId = null;
	private String scSessionId = null;
	private String scUserId = null;
	private String scComment = null;	
	private boolean joinedEvent ;
	private Profile profile = null;	
	private String sessionCommentValue = null;	
	private boolean result ;
	private String eventTag = null;
	private String eventTimeZone = null;
	private String sessionTag = null;
	private String[] eventsTag = null;
	private String twitterCheck = null;
	private String facebookCheck = null;
	private String linkedinCheck = null;
		
	private int sessionCommentLikeCount = 0;
	private int sessionLikeCount = 0;
	private int eventLikeCount = 0;

	private int sessionCommentLikeCountSum = 0;
	private int sessionLikeCountSum = 0;
	private int eventLikeCountSum = 0;
	private String userTimeZone = null;
	
	private String sessionCommentId = null;
	private String sessionCommentSessId = null;
	private String sessionCommentTime = null;
	
	
	
	public String getUserTimeZone() {
		return userTimeZone;
	}
	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}
	public String getSessionTag() {
		return sessionTag;
	}
	public void setSessionTag(String sessionTag) {
		this.sessionTag = sessionTag;
	}
	
	public String[] getEventsTag() {
		return eventsTag;
	}
	public void setEventsTag(String[] eventsTag) {
		this.eventsTag = eventsTag;
	}
	public String getEventTag() {
		return eventTag;
	}
	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
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
	public String getHiddenAction() {
		return hiddenAction;
	}
	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
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
	public String getProfileBio() {
		return profileBio;
	}
	public void setProfileBio(String profileBio) {
		this.profileBio = profileBio;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}	
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	public String getScSessionId() {
		return scSessionId;
	}
	public void setScSessionId(String scSessionId) {
		this.scSessionId = scSessionId;
	}
	public String getScUserId() {
		return scUserId;
	}
	public void setScUserId(String scUserId) {
		this.scUserId = scUserId;
	}
	public String getScComment() {
		return scComment;
	}
	public void setScComment(String scComment) {
		this.scComment = scComment;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getSessionCommentValue() {
		return sessionCommentValue;
	}
	public void setSessionCommentValue(String sessionCommentValue) {
		this.sessionCommentValue = sessionCommentValue;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
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
	
	public int getSessionCommentLikeCount() {
		return sessionCommentLikeCount;
	}
	public void setSessionCommentLikeCount(int sessionCommentLikeCount) {
		this.sessionCommentLikeCount = sessionCommentLikeCount;
	}
	public int getEventLikeCount() {
		return eventLikeCount;
	}
	public void setEventLikeCount(int eventLikeCount) {
		this.eventLikeCount = eventLikeCount;
	}
	public int getSessionCommentLikeCountSum() {
		return sessionCommentLikeCountSum;
	}
	public void setSessionCommentLikeCountSum(int sessionCommentLikeCountSum) {
		this.sessionCommentLikeCountSum = sessionCommentLikeCountSum;
	}
	public int getSessionLikeCountSum() {
		return sessionLikeCountSum;
	}
	public void setSessionLikeCountSum(int sessionLikeCountSum) {
		this.sessionLikeCountSum = sessionLikeCountSum;
	}
	public int getEventLikeCountSum() {
		return eventLikeCountSum;
	}
	public void setEventLikeCountSum(int eventLikeCountSum) {
		this.eventLikeCountSum = eventLikeCountSum;
	}
	public String getTwitterCheck() {
		return twitterCheck;
	}
	public void setTwitterCheck(String twitterCheck) {
		this.twitterCheck = twitterCheck;
	}
	public String getFacebookCheck() {
		return facebookCheck;
	}
	public void setFacebookCheck(String facebookCheck) {
		this.facebookCheck = facebookCheck;
	}
	public String getSessionCommentTime() {
		return sessionCommentTime;
	}
	public void setSessionCommentTime(String sessionCommentTime) {
		this.sessionCommentTime = sessionCommentTime;
	}
	public String getLinkedinCheck() {
		return linkedinCheck;
	}
	public void setLinkedinCheck(String linkedinCheck) {
		this.linkedinCheck = linkedinCheck;
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
	/**
	 * @method eventDetails 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection eventDetails(Event event) throws EventPortalException {
		event.setHiddenAction("EVENTINFO");
		Collection eventDetailList = event.read();
		return eventDetailList;
	}    
	/**
	 * @method listEvents 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection listEvents(Event event) throws EventPortalException {
		event.setHiddenAction("EVENTS");
		Collection eventList = event.read();
		return eventList;
	}  
	
	/**
	 * @method eventAttendees 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection eventAttendees(Event event) throws EventPortalException {
		event.setHiddenAction("EVENTATTENDEE");
		Collection eventAttendeeList = event.read();
		return eventAttendeeList;
	}  
	/**
	 * @method agendaSessionList 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection agendaSessionList(Event event) throws EventPortalException {
		event.setHiddenAction("AgendaSessionList");
		Collection userList = event.read();
		return userList;
	} 
	
	/**
	 * @method sessionSpeakerInfo 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection sessionSummary(Event event) throws EventPortalException {
		event.setHiddenAction("sessionSummary");
		Collection userList = event.read();
		return userList;
	} 
	/**
	 * @method sessionAttendees 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection sessionAttendees(Event event) throws EventPortalException {
		event.setHiddenAction("sessionAttendees");
		Collection eventAttendeeList = event.read();
		return eventAttendeeList;
	}
	
	/**
	 * @method joinEvent
	 * @param event
	 * @return boolean
	 */
	public boolean joinEvent(Event event)throws EventPortalException {
		event.setHiddenAction("joinEvent");
		boolean resultStatus = event.save();
		return resultStatus;
	}
		
	/**
	 * @method unjoinEvent
	 * @param event
	 * @return boolean
	 */
	public boolean unjoinEvent(Event event)throws EventPortalException {
		event.setHiddenAction("unjoinEvent");
		boolean resultStatus = event.save();
		return resultStatus;
	}
	/**
	 * @method 	attendSession
	 * @param event
	 * @return boolean
	 * @throws EventPortalException
	 */
	public boolean attendSession(Event event)throws EventPortalException {
		event.setHiddenAction("attendSession");
		boolean resultStatus = event.save();
		return resultStatus;
	}
	/**
	 * @method leaveSession
	 * @param event
	 * @return boolean
	 * @throws EventPortalException
	 */
	public boolean leaveSession(Event event)throws EventPortalException {
		event.setHiddenAction("leaveSession");
		boolean resultStatus = event.save();
		return resultStatus;
	}

	
	/**
	 * @method liveSessionAttendees 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection liveSessionAttendees(Event event) throws EventPortalException {
		event.setHiddenAction("liveSessionAttendees");
		Collection liveSessionAttendeesList = event.read();
		return liveSessionAttendeesList;
	}
	
	
	/**
	 * @method firstEventSearchTag-To get the tag for first event
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection eventSearchTag(Event event) throws EventPortalException {
		event.setHiddenAction("eventSearchTag");
		Collection eventSearchTagList = event.read();
		return eventSearchTagList;
	}
	
	
	
	/**
	 * @method updateSessionCommentLikeCount 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection updateSessionCommentLikeCount(Event event) throws EventPortalException {
		event.setHiddenAction("updateSessionCommentLikeCount");
		Collection userList = event.read();
		return userList;
	} 
	
	
	/**
	 * @method updateSessionLikeCount 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection updateSessionLikeCount(Event event) throws EventPortalException {
		event.setHiddenAction("updateSessionLikeCount");
		Collection userList = event.read();
		return userList;
	} 

	/**
	 * @method updateEventLikeCount 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection updateEventLikeCount(Event event) throws EventPortalException {
		event.setHiddenAction("updateEventLikeCount");
		Collection userList = event.read();
		return userList;
	} 

		
	/**
	 * @method getSessionCommentLike 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getSessionCommentLike(Event event) throws EventPortalException {
		event.setHiddenAction("getSessionCommentLike");
		Collection userList = event.read();
		return userList;
	} 
	
	/**
	 * @method getSessionLike 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getSessionLike(Event event) throws EventPortalException {
		event.setHiddenAction("getSessionLike");
		Collection userList = event.read();
		return userList;
	} 
	
	
	/**
	 * @method getEventLike 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getEventLike(Event event) throws EventPortalException {
		event.setHiddenAction("getEventLike");
		Collection userList = event.read();
		return userList;
	} 
	
	
	/**
	 * @method getTotalEventLike 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getTotalEventLike(Event event) throws EventPortalException {
		event.setHiddenAction("getTotalEventLike");
		Collection userList = event.read();
		return userList;
	}
	
	public Collection getProfileDetailsById(Event event) throws EventPortalException {
		event.setHiddenAction("getProfileDetailsById");
		Collection profileInfoList = event.read();
		return profileInfoList;
	}
	
	/**
	 * @method getEvents 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getEvents(Event event) throws EventPortalException {
		event.setHiddenAction("getEvents");
		Collection eventList = event.read();
		return eventList;
	}
	
	
	/**
	 * @method getEvents 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection eventUserList(Event event) throws EventPortalException {
		
		event.setHiddenAction("EVENTUSERLIST");
		
		return event.read();
	}  
	
	
	/**
	   * @method sessionInformation 
	   * @param event
	   * @return
	   * @throws EventPortalException
	   */
	  @SuppressWarnings("unchecked")
	  public Collection sessionInformation(Event event) throws EventPortalException {
	    event.setHiddenAction("sessionInformation");
	    Collection  sessionInfoList = event.read();
	    return sessionInfoList;
	  } 
	  
	  @SuppressWarnings("unchecked")
	  public boolean  addEvent(Event event) throws EventPortalException {
	    event.setHiddenAction("ADDEVENT");
	    boolean  result = event.save();
	    return result;
	  } 
	  
	  @SuppressWarnings("unchecked")
	  public boolean  deleteEvent(Event event) throws EventPortalException {
	    event.setHiddenAction("DELETEEVENT");
	    boolean  result = event.delete();
	    return result;
	  } 
}
