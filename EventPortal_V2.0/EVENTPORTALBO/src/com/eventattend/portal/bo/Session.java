package com.eventattend.portal.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eventattend.portal.exceptions.EventPortalException;

public class Session extends BusinessObject {

	private String sessionId = null;
	private String selectedSession = null;
	private String userId = null;
	
	private String speakerUserId = null;
	
	private String eventId = null;
	
	private String sessionName = null;

	 private String sessionStartTime = null;
	 private String sessionEndTime = null;
	 private String sessionKeyNote = null;
	 private String sessionEventId = null;
	 private String sessionEventName = null;
	 private String sessionVenueId = null;
	 private String sessionVenueName = null;
	 private String sessionStartDate = null;
	 private String sessionEndDate = null;
	 private String sessionTag = null;
	 private String sessionLikeCount = null;
	private String scComment = null;
	private String scUserId = null;
	private String scId = null;
	private String scSessionId = null;
	private String scSessionCommentTime = null;
	private String userTimeZone = null;
	private List sessionList = null;
	private Profile profile = null;
	private String sessionCommentTime = null;
	private int sessionCommentLikeCount = 0;
	private String twitterCheck = null;
	private String facebookCheck = null;
	private String linkedinCheck = null;
	private boolean userInLiveSession = false;

	public String getSpeakerUserId() {
		return speakerUserId;
	}

	public void setSpeakerUserId(String speakerUserId) {
		this.speakerUserId = speakerUserId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
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

	public String getSessionLikeCount() {
		return sessionLikeCount;
	}

	public void setSessionLikeCount(String sessionLikeCount) {
		this.sessionLikeCount = sessionLikeCount;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List getSessionList() {
		return sessionList;
	}

	public void setSessionList(List sessionList) {
		this.sessionList = sessionList;
	}

	
	public String getScComment() {
		return scComment;
	}

	public void setScComment(String scComment) {
		this.scComment = scComment;
	}

	public String getScUserId() {
		return scUserId;
	}

	public void setScUserId(String scUserId) {
		this.scUserId = scUserId;
	}

	public String getScSessionId() {
		return scSessionId;
	}

	public void setScSessionId(String scSessionId) {
		this.scSessionId = scSessionId;
	}

	public String getScSessionCommentTime() {
		return scSessionCommentTime;
	}

	public void setScSessionCommentTime(String scSessionCommentTime) {
		this.scSessionCommentTime = scSessionCommentTime;
	}
	
	

	public String getUserTimeZone() {
		return userTimeZone;
	}

	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}
	
	

	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getSessionCommentLikeCount() {
		return sessionCommentLikeCount;
	}

	public void setSessionCommentLikeCount(int sessionCommentLikeCount) {
		this.sessionCommentLikeCount = sessionCommentLikeCount;
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

	public String getLinkedinCheck() {
		return linkedinCheck;
	}

	public void setLinkedinCheck(String linkedinCheck) {
		this.linkedinCheck = linkedinCheck;
	}

	

	public String getSessionCommentTime() {
		return sessionCommentTime;
	}

	public void setSessionCommentTime(String sessionCommentTime) {
		this.sessionCommentTime = sessionCommentTime;
	}

	public boolean isUserInLiveSession() {
		return userInLiveSession;
	}

	public void setUserInLiveSession(boolean userInLiveSession) {
		this.userInLiveSession = userInLiveSession;
	}

	
	public String getSessionEventName() {
		return sessionEventName;
	}

	public void setSessionEventName(String sessionEventName) {
		this.sessionEventName = sessionEventName;
	}

	
	
	public String getSessionVenueName() {
		return sessionVenueName;
	}

	public void setSessionVenueName(String sessionVenueName) {
		this.sessionVenueName = sessionVenueName;
	}

	public String getSelectedSession() {
		return selectedSession;
	}

	public void setSelectedSession(String selectedSession) {
		this.selectedSession = selectedSession;
	}

	public boolean resetLiveSessionInfo(Session session) throws EventPortalException {
		
		session.setHiddenAction("resetLiveSessionInfo");
		
		return session.update();
	}

	public boolean isUserInLiveSession(Session session) throws EventPortalException {
		
		session.setHiddenAction("USERINLIVESESSION");
		
		return session.isExist(session);
	}
	
	/**
	 * @method updateSessionComment 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public boolean updateSessionComment(Session session) throws EventPortalException {
		session.setHiddenAction("UPDATESESSIONCOMMENT");
		boolean result = session.update();
		return result;
	} 
	
	/**
	 * @method getSessionByEventId - to get list of sessions for event where the speakers are not assigned
	 * @param session
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getSessionByEventId(Session session) throws EventPortalException {
		
		session.setHiddenAction("getSessionByEventId");
		
		return session.read();
	}
	
	/**
	 * @method getSessionByEventId - to get list of sessions for event 
	 * @param session
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection sessionListForEvent(Session session) throws EventPortalException {
		
		session.setHiddenAction("SESSIONLISTFOREVENT");
		
		return session.read();
	}  

	/**
	 * @method sessionAttendees 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection sessionAttendee(Session session) throws EventPortalException {
		
		session.setHiddenAction("SESSIONATTENDEE");
		
		return session.read();
	}

	public Collection speakerIdMap(Session session) throws EventPortalException {
		
		session.setHiddenAction("SPEAKERIDMAP");
		
		return session.read();
	}

	public Collection speakerForInfo(Session session) throws EventPortalException {
		
		session.setHiddenAction("SPEAKERFORINFO");
		
		return session.read();
	}

	public Collection speakerInformation(Session session) throws EventPortalException {
		
		session.setHiddenAction("SPEAKERINFORMATION");
		
		return session.read();
	}
	
	public Collection sessionUserList(Session session) throws EventPortalException {
		
		session.setHiddenAction("SESSIONUSERLIST");
		
		return session.read();
	}

	public Collection  liveSessionUserList(Session session) throws EventPortalException {
		
		session.setHiddenAction("LIVESESSIONUSERLIST");
		
		return session.read();
	}
	

	public Collection sessionCommentsList(Session session) throws EventPortalException {
		session.setHiddenAction("SESSIONCOMMENTS");
		Collection eventAttendeeList = session.read();
		return eventAttendeeList;
	}
	

	
	
	/**
	 * @method sessionSearchTag-To get a session Tag for a session
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection sessionSearchTag(Session session) throws EventPortalException {
		session.setHiddenAction("SESSIONSEARCHTAG");
		Collection sessionSearchTagList = session.read();
		return sessionSearchTagList;
	}
	
	/**
	 * @method liveSessionAttendees 
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection userInLiveSession(Session session) throws EventPortalException {
		session.setHiddenAction("USERINLIVESESSION");
		Collection userInLiveSessionList = session.read();
		return userInLiveSessionList;
	}

	public Collection  speakerIdList(Session session) throws EventPortalException {
		
		session.setHiddenAction("SPEAKERIDLIST");
		
		return session.read();
	}
	public boolean saveSessionSpeakerMap(Session session) throws EventPortalException {
		
		session.setHiddenAction("SAVESESSIONSPEAKERMAP");
		
		return session.save();
	}
	
	public Collection sessionListForSpeaker(Session session) throws EventPortalException {
		
		session.setHiddenAction("SESSIONLISTFORSPEAKER");
		
		return session.read();
	}

	public boolean saveSessionIdForSpeaker(Session session) throws EventPortalException {
		
		session.setHiddenAction("SAVESESSIONIDFORSPEAKER");
		
		return session.save();
	}
	
	public boolean removeSessionIdForSpeaker(Session session) throws EventPortalException {
		
		session.setHiddenAction("REMOVESESSIONIDFORSPEAKER");
		
		return session.delete();
	}
	  @SuppressWarnings("unchecked")
	  public Collection sessions(Session session) throws EventPortalException {
		  session.setHiddenAction("SESSIONS");
	    Collection  sessionList = session.read();
	    return sessionList;
	  } 
	  @SuppressWarnings("unchecked")
	  public Collection session(Session session) throws EventPortalException {
		  session.setHiddenAction("SESSION");
	    Collection  sessionList = session.read();
	    return sessionList;
	  } 
	  public boolean deleteSession(Session session) throws EventPortalException {
			
			session.setHiddenAction("DELETESESSION");
			
			return session.delete();
		}
	  public boolean addSession(Session session) throws EventPortalException {
			
			session.setHiddenAction("ADDSESSION");
			
			return session.save();
		}
}

