package com.eventattend.portal.dto;

import java.util.Date;
import java.util.List;

public class SessionDTO {

	private String sessionId = null;
	private String sessionName = null;
	private String sessionStartTime = null;
	private String sessionEndTime = null;
	private String sessionKeyNote = null;
	private String sessionEventId = null;
	private String sessionEventName = null;
	private String sessionVenueId = null;
	private String sessionSpeakerName = null;
	private String sessionTime = null;
	private String sessionSpeakerBio = null;
	private String sessionVenueName = null;
	private String scCommentId = null;
	private String scId = null;
	private String scSessionId = null;
	private String scUserId = null;
	private String scComment = null;
	private String sessionTag = null;
	private String sessionStartDate = null;
	private String sessionEndDate = null;
	private String sessionSpeakerSMPId = null;
	private String sessionSpeakerSMPTwitterId = null;
	private String sessionSpeakerSMPFacebookId = null;
	private String sessionSpeakerSMPLinkedInId = null;
	private String sessionSpeakerSMPTwitterImgUrl = null;
	private String sessionSpeakerSMPFacebookImgUrl = null;
	private String sessionSpeakerSMPLinkedInImgUrl = null;
	private String sessionSpeakerSMPTwitterProfileUrl = null;
	private String sessionSpeakerSMPFaceBookProfileUrl = null;
	private String sessionSpeakerSMPLinkedInProfileUrl = null;
	private String userTimeZone = null;
	private Date sessionStartDateWithTime = null;
	private Date sessionEndDateWithTime = null;

	
	private int sessionLikeCount = 0;
	private String sessionState = null; 
	
	private List sessionCommentsList = null;
	private String sessionCommentTime = null;
	private boolean result ;
	
	private String twitterCheck = null;
	private String facebookCheck = null;
	private String linkedinCheck = null;
	
	private ProfileDTO profileDTO = null;
	
	private String speakerKeyNotes = null;
	
	// New variables
	private String userId = null;
	private boolean userInLiveSession = false;
	
	private List sessionList = null;
	
	private String currentUserProfileId = null; 
	
	public List getSessionList() {
		return sessionList;
	}
	public void setSessionList(List sessionList) {
		this.sessionList = sessionList;
	}
	public boolean isUserInLiveSession() {
		return userInLiveSession;
	}
	public void setUserInLiveSession(boolean userInLiveSession) {
		this.userInLiveSession = userInLiveSession;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getSessionSpeakerName() {
		return sessionSpeakerName;
	}
	public void setSessionSpeakerName(String sessionSpeakerName) {
		this.sessionSpeakerName = sessionSpeakerName;
	}
	public String getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}	
	public String getSessionVenueName() {
		return sessionVenueName;
	}
	public void setSessionVenueName(String sessionVenueName) {
		this.sessionVenueName = sessionVenueName;
	}
	public String getSessionSpeakerBio() {
		return sessionSpeakerBio;
	}
	public void setSessionSpeakerBio(String sessionSpeakerBio) {
		this.sessionSpeakerBio = sessionSpeakerBio;
	}
	public String getScCommentId() {
		return scCommentId;
	}
	public void setScCommentId(String scCommentId) {
		this.scCommentId = scCommentId;
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
	public String getSessionSpeakerSMPId() {
		return sessionSpeakerSMPId;
	}
	public void setSessionSpeakerSMPId(String sessionSpeakerSMPId) {
		this.sessionSpeakerSMPId = sessionSpeakerSMPId;
	}
	public String getSessionSpeakerSMPTwitterId() {
		return sessionSpeakerSMPTwitterId;
	}
	public void setSessionSpeakerSMPTwitterId(String sessionSpeakerSMPTwitterId) {
		this.sessionSpeakerSMPTwitterId = sessionSpeakerSMPTwitterId;
	}
	public String getSessionSpeakerSMPFacebookId() {
		return sessionSpeakerSMPFacebookId;
	}
	public void setSessionSpeakerSMPFacebookId(String sessionSpeakerSMPFacebookId) {
		this.sessionSpeakerSMPFacebookId = sessionSpeakerSMPFacebookId;
	}
	public String getSessionSpeakerSMPLinkedInId() {
		return sessionSpeakerSMPLinkedInId;
	}
	public void setSessionSpeakerSMPLinkedInId(String sessionSpeakerSMPLinkedInId) {
		this.sessionSpeakerSMPLinkedInId = sessionSpeakerSMPLinkedInId;
	}
	public String getSessionSpeakerSMPTwitterImgUrl() {
		return sessionSpeakerSMPTwitterImgUrl;
	}
	public void setSessionSpeakerSMPTwitterImgUrl(
			String sessionSpeakerSMPTwitterImgUrl) {
		this.sessionSpeakerSMPTwitterImgUrl = sessionSpeakerSMPTwitterImgUrl;
	}
	public String getSessionSpeakerSMPFacebookImgUrl() {
		return sessionSpeakerSMPFacebookImgUrl;
	}
	public void setSessionSpeakerSMPFacebookImgUrl(
			String sessionSpeakerSMPFacebookImgUrl) {
		this.sessionSpeakerSMPFacebookImgUrl = sessionSpeakerSMPFacebookImgUrl;
	}
	public String getSessionSpeakerSMPLinkedInImgUrl() {
		return sessionSpeakerSMPLinkedInImgUrl;
	}
	public void setSessionSpeakerSMPLinkedInImgUrl(
			String sessionSpeakerSMPLinkedInImgUrl) {
		this.sessionSpeakerSMPLinkedInImgUrl = sessionSpeakerSMPLinkedInImgUrl;
	}
	public String getSessionSpeakerSMPTwitterProfileUrl() {
		return sessionSpeakerSMPTwitterProfileUrl;
	}
	public void setSessionSpeakerSMPTwitterProfileUrl(
			String sessionSpeakerSMPTwitterProfileUrl) {
		this.sessionSpeakerSMPTwitterProfileUrl = sessionSpeakerSMPTwitterProfileUrl;
	}
	public String getSessionSpeakerSMPFaceBookProfileUrl() {
		return sessionSpeakerSMPFaceBookProfileUrl;
	}
	public void setSessionSpeakerSMPFaceBookProfileUrl(
			String sessionSpeakerSMPFaceBookProfileUrl) {
		this.sessionSpeakerSMPFaceBookProfileUrl = sessionSpeakerSMPFaceBookProfileUrl;
	}
	public String getSessionSpeakerSMPLinkedInProfileUrl() {
		return sessionSpeakerSMPLinkedInProfileUrl;
	}
	public void setSessionSpeakerSMPLinkedInProfileUrl(
			String sessionSpeakerSMPLinkedInProfileUrl) {
		this.sessionSpeakerSMPLinkedInProfileUrl = sessionSpeakerSMPLinkedInProfileUrl;
	}
	public List getSessionCommentsList() {
		return sessionCommentsList;
	}
	public void setSessionCommentsList(List sessionCommentsList) {
		this.sessionCommentsList = sessionCommentsList;
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
	public String getSessionState() {
		return sessionState;
	}
	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
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
	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}
	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}
	public String getSpeakerKeyNotes() {
		return speakerKeyNotes;
	}
	public void setSpeakerKeyNotes(String speakerKeyNotes) {
		this.speakerKeyNotes = speakerKeyNotes;
	}
	public String getCurrentUserProfileId() {
		return currentUserProfileId;
	}
	public void setCurrentUserProfileId(String currentUserProfileId) {
		this.currentUserProfileId = currentUserProfileId;
	}
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
	public String getSessionEventName() {
		return sessionEventName;
	}
	public void setSessionEventName(String sessionEventName) {
		this.sessionEventName = sessionEventName;
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
	public Date getSessionStartDateWithTime() {
		return sessionStartDateWithTime;
	}
	public void setSessionStartDateWithTime(Date sessionStartDateWithTime) {
		this.sessionStartDateWithTime = sessionStartDateWithTime;
	}
	public Date getSessionEndDateWithTime() {
		return sessionEndDateWithTime;
	}
	public void setSessionEndDateWithTime(Date sessionEndDateWithTime) {
		this.sessionEndDateWithTime = sessionEndDateWithTime;
	}
	
}
