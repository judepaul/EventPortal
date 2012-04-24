package com.eventattend.portal.web.bean;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.eventattend.portal.Factory.EventFactory;
import com.eventattend.portal.Factory.SessionFactory;
import com.eventattend.portal.Factory.SocialMediaFactory;
import com.eventattend.portal.controller.EventController;
import com.eventattend.portal.controller.SessionController;
import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.MapDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.SpeakerDTO;
import com.eventattend.portal.dto.TwitterDTO;
import com.eventattend.portal.socialmedia.util.SocialMediaKeys;

@SuppressWarnings("deprecation")
public class SessionBean extends ApplicationBean {
	private String sessionId = null;
	private String eventId = null;
	private EventDTO eventDTO = null;
	private String speakerName = null;
	private String sessionTime = null;
	private String sessionLocation = null;
	private String sessionKeynote = null;
	private String speakerBio = null;
	private String eventName = null;
	private String eventLocation = null;
	private String sessionCommentValue = null;
	private List sessionCommentsList = null;
	private String sessionSpeakerSMPId = null;
	private String sessionSpeakerPicture = null;
	private String sessionSpeakerEmail = null;
	private String sessionSpeakerSMPTwitterId = null;
	private String sessionSpeakerSMPFacebookId = null;
	private String sessionSpeakerSMPLinkedInId = null;
	private String sessionSpeakerSMPTwitterImgUrl = null;
	private String sessionSpeakerSMPFacebookImgUrl = null;
	private String sessionSpeakerSMPLinkedInImgUrl = null;
	private String sessionSpeakerSMPTwitterProfileUrl = null;
	private String sessionSpeakerSMPFaceBookProfileUrl = null;
	private String sessionSpeakerSMPLinkedInProfileUrl = null;
	private String sessionSpeakerFirstName = null;
	private String sessionSpeakerLastName = null;
	private boolean sessionSpeakerTwitterFollow = false;
	private boolean sessionSpeakerFacebookConnect = false;
	private boolean sessionSpeakerLinkedInConnect = false;
	private boolean liveSession = false;
	private boolean twitterCheck = false;
	private boolean facebookCheck = false;
	private boolean linkedinCheck = false;
	private boolean currentUser = false;

	private SessionDTO sessionDTO = null;
	private ProfileDTO profileDTO = null;

	private int likeCount = 0;
	private int sessionCommentLikeCount = 0;
	private String scCommentId = null;
	private String scSessionId = null;

	public boolean isLiveSession() {
		return liveSession;
	}

	public void setLiveSession(boolean liveSession) {
		this.liveSession = liveSession;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getSessionLocation() {
		return sessionLocation;
	}

	public void setSessionLocation(String sessionLocation) {
		this.sessionLocation = sessionLocation;
	}

	public String getSessionKeynote() {
		return sessionKeynote;
	}

	public void setSessionKeynote(String sessionKeynote) {
		this.sessionKeynote = sessionKeynote;
	}

	public String getSpeakerBio() {
		return speakerBio;
	}

	public void setSpeakerBio(String speakerBio) {
		this.speakerBio = speakerBio;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}

	public List getSessionCommentsList() {
		return sessionCommentsList;
	}

	public void setSessionCommentsList(List sessionCommentsList) {
		this.sessionCommentsList = sessionCommentsList;
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

	public void setSessionSpeakerSMPFacebookId(
			String sessionSpeakerSMPFacebookId) {
		this.sessionSpeakerSMPFacebookId = sessionSpeakerSMPFacebookId;
	}

	public String getSessionSpeakerSMPLinkedInId() {
		return sessionSpeakerSMPLinkedInId;
	}

	public void setSessionSpeakerSMPLinkedInId(
			String sessionSpeakerSMPLinkedInId) {
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

	public String getSessionCommentValue() {
		return sessionCommentValue;
	}

	public void setSessionCommentValue(String sessionCommentValue) {
		this.sessionCommentValue = sessionCommentValue;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getSessionCommentLikeCount() {
		return sessionCommentLikeCount;
	}

	public void setSessionCommentLikeCount(int sessionCommentLikeCount) {
		this.sessionCommentLikeCount = sessionCommentLikeCount;
	}

	public String getScCommentId() {
		return scCommentId;
	}

	public void setScCommentId(String scCommentId) {
		this.scCommentId = scCommentId;
	}

	public String getScSessionId() {
		return scSessionId;
	}

	public void setScSessionId(String scSessionId) {
		this.scSessionId = scSessionId;
	}

	public boolean getTwitterCheck() {
		return twitterCheck;
	}

	public String getSessionSpeakerPicture() {
		return sessionSpeakerPicture;
	}

	public void setSessionSpeakerPicture(String sessionSpeakerPicture) {
		this.sessionSpeakerPicture = sessionSpeakerPicture;
	}

	public String getSessionSpeakerEmail() {
		return sessionSpeakerEmail;
	}

	public void setSessionSpeakerEmail(String sessionSpeakerEmail) {
		this.sessionSpeakerEmail = sessionSpeakerEmail;
	}

	public void setTwitterCheck(boolean twitterCheck) {
		this.twitterCheck = twitterCheck;
	}

	public boolean getFacebookCheck() {
		return facebookCheck;
	}

	public void setFacebookCheck(boolean facebookCheck) {
		this.facebookCheck = facebookCheck;
	}

	public boolean getLinkedinCheck() {
		return linkedinCheck;
	}

	public void setLinkedinCheck(boolean linkedinCheck) {
		this.linkedinCheck = linkedinCheck;
	}

	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}

	public String getSessionSpeakerFirstName() {
		return sessionSpeakerFirstName;
	}

	public String getSessionSpeakerLastName() {
		return sessionSpeakerLastName;
	}

	public void setSessionSpeakerLastName(String sessionSpeakerLastName) {
		this.sessionSpeakerLastName = sessionSpeakerLastName;
	}

	public void setSessionSpeakerFirstName(String sessionSpeakerFirstName) {
		this.sessionSpeakerFirstName = sessionSpeakerFirstName;
	}

	public boolean isSessionSpeakerTwitterFollow() {
		return sessionSpeakerTwitterFollow;
	}

	public void setSessionSpeakerTwitterFollow(
			boolean sessionSpeakerTwitterFollow) {
		this.sessionSpeakerTwitterFollow = sessionSpeakerTwitterFollow;
	}

	public boolean isSessionSpeakerFacebookConnect() {
		return sessionSpeakerFacebookConnect;
	}

	public void setSessionSpeakerFacebookConnect(
			boolean sessionSpeakerFacebookConnect) {
		this.sessionSpeakerFacebookConnect = sessionSpeakerFacebookConnect;
	}

	public boolean isSessionSpeakerLinkedInConnect() {
		return sessionSpeakerLinkedInConnect;
	}

	public void setSessionSpeakerLinkedInConnect(
			boolean sessionSpeakerLinkedInConnect) {
		this.sessionSpeakerLinkedInConnect = sessionSpeakerLinkedInConnect;
	}

	public boolean isCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(boolean currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @method populateSessionPage - To get the Session details.
	 * @return String
	 */
	public String populateSessionPage() {
		
		FacesContext facesContext = null;
		HttpSession session = null;
		
		
		String retValue = "session";
		this.setLiveSession(liveSession);

		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);

		session.setAttribute("CURRENTSESSIONID", this.sessionId);
		
		populateSessionInfoPage();

		//getAttendeeBeanInstance().sessionAttendees(this.sessionId);
		sessionAttendee();
		String sessionLikeCount = getAttendeeBeanInstance().getSessionLike(
				this.sessionId);
		getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
		getApplicationBeanInstance().setLike("Session");
		setCurrentPage("SESSIONSUMMARY");
		
		EventDTO eventDTO = new EventDTO();
		
		eventDTO = getEventBeanInstance().eventDetails();
		
		this.eventId = eventDTO.getEventId();
		
		String country = eventDTO.getEventCountry();
		String location = eventDTO.getEventLocation();
		String placeName = eventDTO.getEventCity();
		System.out.println("<< >> " + placeName);

		//System.out.println("<< >> " + placeName);
		// String gmapKey =
		// "ABQIAAAAiqblAQNWNoua1VYnGtU1dBRedtL7gCpXlU8FuammbFvwIMVSlhRL4gzsM_7WZ6hbScCw1YuLXHNjwA";
		String gmapKey = SocialMediaKeys.GOOGLE_MAP_KEY;
		URL mapurl = getEventBeanInstance().geoMapURL(location, country, placeName);
		MapDTO mapDTO = getEventBeanInstance().getGeoLocation(mapurl);
		eventDTO.setMapDTO(mapDTO);
		getEventBeanInstance().setEventDetailDTO(eventDTO);
		
		
			
		return retValue;

	}
	
	public void sessionAttendee(){
		
		SessionDTO sessionDTO = null;
		ResultDTO resultDTO = null;
		AttendeeDTO attendeeDTO = null;
		String currentUserProfileId = null;
		SessionController sessionController = null;
		FacesContext facesContext = null;
		HttpSession session = null;		
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		currentUserProfileId = (String)session.getAttribute("USERPROFILEID");
	
		try{
			
			sessionDTO = new SessionDTO();
			attendeeDTO = new AttendeeDTO();
			
			sessionDTO.setSessionId(this.sessionId);
			
			if(currentUserProfileId!=null){
				
				sessionDTO.setCurrentUserProfileId(currentUserProfileId);
				
			}
			
			sessionController = SessionFactory.sessionAttendee();			
			
			resultDTO = sessionController.sessionAttendee(sessionDTO);
			
			if(resultDTO != null){
				attendeeDTO = resultDTO.getAttendeeDTO();
			}
			
			getAttendeeBeanInstance().populateAttendees(attendeeDTO);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	

	/**
	 * @method populateSessionPage - To get the Session details.
	 * @return String
	 */
	public String populateSessionDetailsPage() {

		FacesContext facesContext = null;
		HttpSession session = null;
		String retValue = "SESSIONDETAIL";

		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			session.setAttribute("CURRENTSESSIONID", this.sessionId);

			attendSession(this.eventId,this.sessionId);
			String currentSessionSpeakerId = populateSessionInfoPage();

			System.out.println("currentSessionSpeakerId >>"
					+ currentSessionSpeakerId);
			
			//getAttendeeBeanInstance().liveSessionAttendees(this.sessionId,
			//		currentSessionSpeakerId);
			liveSessionAttendee(currentSessionSpeakerId);

			sessionCommentsList(this.sessionId);
			String sessionLikeCount = getAttendeeBeanInstance().getSessionLike(
					this.sessionId);
			getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
			getApplicationBeanInstance().setLike("Session");
			setCurrentPage("SESSIONDETAIL");
			// populateSessionInfoPage();
			/*
			 * Tweets -Not in Live Session ActionEvent ae = null;
			 * 
			 * getSocialMediaBeanInstance().sessionTweets(ae);
			 * 
			 * getSocialMediaBeanInstance().setTwitterPollEnabled(true);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retValue;

	}
	
	public void liveSessionAttendee(String currentspeakerId){
		
		SessionDTO sessionDTO = null;
		ResultDTO resultDTO = null;
		AttendeeDTO attendeeDTO = null;
		String currentUserProfileId = null;
		FacesContext facesContext = null;
		SessionController sessionController = null;
		HttpSession session = null;		
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		currentUserProfileId = (String)session.getAttribute("USERPROFILEID");
	
		try{
			
			sessionDTO = new SessionDTO();
			attendeeDTO = new AttendeeDTO();
			
			sessionDTO.setSessionId(this.sessionId);
			
			if(currentspeakerId!=null){
			sessionDTO.setSessionSpeakerSMPId(currentspeakerId);
			}
		
			if(currentUserProfileId!=null){
				
				sessionDTO.setCurrentUserProfileId(currentUserProfileId);}			
			sessionController = SessionFactory.liveSessionAttendee();			
			
			resultDTO = sessionController.liveSessionAttendee(sessionDTO);
			
			if(resultDTO != null){
				attendeeDTO = resultDTO.getAttendeeDTO();
			}
			
			getAttendeeBeanInstance().populateAttendees(attendeeDTO);
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
		
	
	
	

	/**
	 * @method populateSessionInfoPage - To get Session Information Page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String populateSessionInfoPage() {
		@SuppressWarnings("unused")
		FacesContext facesContext = null;
		SessionController sessionController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		ProfileDTO profileDTO = null;
		List SessionList = null;
		String userTimeZone = null;
		HttpSession session = null;
		String currentUserProfileId = null;
		String currentSpeaker = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		currentUserProfileId = (String) session.getAttribute("USERPROFILEID");
		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		}

		try {
			facesContext = FacesContext.getCurrentInstance();
			eventDTO = new EventDTO();
			eventDTO.setSessionId(this.sessionId);
			eventDTO.setUserTimeZone(userTimeZone);
			sessionController = SessionFactory
					.populateSessionInfoPage(eventDTO);
			resultDTO = sessionController.populateSessionInfoPage(eventDTO);
			SessionList = resultDTO.getAgendaSessionList();
			int i = SessionList.size();
			if (SessionList != null) {
				if (SessionList.get(0) != null) {
					eventDTO = (EventDTO) SessionList.get(0);
					this.setEventDTO(eventDTO);

					getApplicationBeanInstance().setEventTooptip(
							eventTooltip(eventDTO));
					getApplicationBeanInstance().setSessionTooptip(
							sessionTooltip(eventDTO));

					if (eventDTO != null) {
						getApplicationBeanInstance().setCurrentSession(
								shrinkName(eventDTO.getSessionName()));
					}

				}
				if (i == 2 && SessionList.get(1) != null) {
					profileDTO = (ProfileDTO) SessionList.get(1);
					if (currentUserProfileId.equals(profileDTO.getProfileId())) {
						this.currentUser = true;
					}
					if (profileDTO != null) {
						getApplicationBeanInstance().setCurrentSpeaker(
								profileDTO.getFirstName() + " "
										+ profileDTO.getLastName());
						getApplicationBeanInstance().setCurrentSpeakerToolTip(
								speakerToolTip(profileDTO));

					}
					currentSpeaker = profileDTO.getProfileId();
					this.setSessionSpeakerSMPId(profileDTO.getProfileId());
					this.setSessionSpeakerFirstName(profileDTO.getFirstName());
					this.setSessionSpeakerLastName(profileDTO.getLastName());
					this.setSpeakerName(profileDTO.getFirstName() + " "
							+ profileDTO.getLastName());
					this.setSpeakerBio(profileDTO.getSpeakerKeyNotes());
					this.setSessionSpeakerEmail(profileDTO.getProfileEmail());
					this.setSessionSpeakerPicture(profileDTO
							.getProfImgFileName());
					this.setSessionSpeakerSMPLinkedInProfileUrl(profileDTO
							.getSocialMediaDTO().getLinkedInProfileUrl());
					this.setSessionSpeakerSMPLinkedInId(profileDTO
							.getSocialMediaDTO().getLinkedInId());
					if (profileDTO.getSocialMediaDTO().getLinkedInFriendsConnect() != null
							&& profileDTO.getSocialMediaDTO().getLinkedInFriendsConnect().equals("true")) {
						this.setSessionSpeakerLinkedInConnect(true);
					} else {
						this.setSessionSpeakerLinkedInConnect(false);
					}
					this.setSessionSpeakerSMPFaceBookProfileUrl(profileDTO
							.getSocialMediaDTO().getFaceBookProfileUrl());
					this.setSessionSpeakerSMPFacebookId(profileDTO
							.getSocialMediaDTO().getFaceBookId());
					if (profileDTO.getSocialMediaDTO().getFaceBookFriendsConnect() != null
							&& profileDTO.getSocialMediaDTO().getFaceBookFriendsConnect().equals("true")) {
						this.setSessionSpeakerFacebookConnect(true);
					} else {
						this.setSessionSpeakerFacebookConnect(false);
					}
					this.setSessionSpeakerSMPTwitterProfileUrl(profileDTO
							.getSocialMediaDTO().getTwitterProfileUrl());
					this.setSessionSpeakerSMPTwitterId(profileDTO
							.getSocialMediaDTO().getTwitterId());
					if (profileDTO.getSocialMediaDTO().getTwitterFollow() != null
							&& profileDTO.getSocialMediaDTO().getTwitterFollow().equals("true")) {
						this.setSessionSpeakerTwitterFollow(true);
					} else {
						this.setSessionSpeakerTwitterFollow(false);
					}

					if (profileDTO != null) {
						profileDTO
								.setProfileToolTip(speakerToolTip(profileDTO));

					}
					this.profileDTO = profileDTO;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("CURRENTSPEAKER", currentSpeaker);
		return currentSpeaker;
	}

	public String sessionTooltip(EventDTO eventDTO) {
		String sessiontip = null;
		String name = null;
		String keynote = null;
		String venue = null;
		String date = null;
		String time = null;

		if (eventDTO.getSessionName() != null) {
			name = eventDTO.getSessionName();
		}
		if (eventDTO.getSessionKeyNote() != null) {
			keynote = eventDTO.getSessionKeyNote();
			keynote = keynote.replace("—", "&mdash;");
			keynote = keynote.replace("–", "&ndash;");
			keynote = keynote.replace(String.valueOf('"'), "&quot;");
			keynote = keynote.replace("“", "&ldquo;");
			keynote = keynote.replace("”", "&rdquo;");
			keynote = keynote.replace("'", "&#39;");
			keynote = keynote.replace("‘", "&lsquo;");
			keynote = keynote.replace("’", "&rsquo;");
			System.out.println("keynote " + keynote);
		}
		if (eventDTO.getSessionVenueName() != null) {
			venue = eventDTO.getSessionVenueName();
			System.out.println("venue " + venue);
		}
		if (eventDTO.getSessionStartDate() != null) {
			date = eventDTO.getSessionStartDate();
			System.out.println("date " + date);
		}
		if (eventDTO.getSessionStartTime() != null
				&& eventDTO.getSessionEndTime() != null) {
			time = eventDTO.getSessionStartTime() + " - "
					+ eventDTO.getSessionEndTime();
			System.out.println("time " + time);
		}

		sessiontip = "<table width='311' border='0' cellpadding='0' cellspacing='0' class='profile-tooltip'>";

		if (name != null) {
			sessiontip = sessiontip
					+ "<tr><td width='152' colspan='2' class='ea-heading-class'>"
					+ name + "</td></tr>";
		}
		if (keynote != null) {
			sessiontip = sessiontip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>KeyNote: </strong> "
					+ keynote + "</td></tr>";
		}
		if (venue != null) {
			sessiontip = sessiontip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Venue: </strong>"
					+ venue + "</td></tr>";
		}
		if (sessiontip != null) {
			sessiontip = sessiontip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Date: </strong> "
					+ date + "</td></tr>";
		}
		if (time != null) {
			sessiontip = sessiontip
					+ "  <tr><td colspan='2' class='ea-content-class'><strong>Timings: </strong>"
					+ time + "</td></tr>";
		}
		sessiontip = sessiontip + "</table>";
		System.out.println("SessTip");
		System.out.println(sessiontip);
		return sessiontip;
	}

	public String eventTooltip(EventDTO eventDTO) {
		String tip = null;
		String name = null;
		String desc = null;
		String city = null;
		String state = null;
		String country = null;
		String date = null;
		String time = null;
		String website = null;
		String orgBy = null;
		String days = null;
		if (eventDTO.getEventName() != null) {
			name = eventDTO.getEventName();
		}
		if (eventDTO.getEventDescription() != null) {
			desc = eventDTO.getEventDescription();
		}
		if (eventDTO.getEventCity() != null) {
			city = eventDTO.getEventCity();
		}
		if (eventDTO.getEventState() != null) {
			state = eventDTO.getEventState();
		}
		if (eventDTO.getEventCountry() != null) {
			country = eventDTO.getEventCountry();
		}
		if (eventDTO.getEventStartDate() != null
				&& eventDTO.getEventEndDate() != null) {
			date = eventDTO.getEventStartDate() + " to "
					+ eventDTO.getEventEndDate();
		}

		if (eventDTO.getEventStartTime() != null
				&& eventDTO.getEventEndTime() != null) {
			time = eventDTO.getEventStartTime() + " to "
					+ eventDTO.getEventEndTime();
		}
		if (eventDTO.getEventNoOfDays() != null) {
			days = eventDTO.getEventNoOfDays();
		}
		if (eventDTO.getEventOrganizedBy() != null) {
			orgBy = eventDTO.getEventOrganizedBy();
		}
		if (eventDTO.getEventWebsite() != null) {
			website = eventDTO.getEventWebsite();
		}

		tip = "<table width='311' border='0' cellpadding='0' cellspacing='0' class='profile-tooltip'>";

		if (name != null) {
			tip = tip
					+ "<tr><td width='152' colspan='2' class='ea-heading-class'>"
					+ name + "</td></tr>";
		}
		if (website != null) {
			tip = tip + "<tr><td class='ea-content-class' colspan='2'>"
					+ website + "</td></tr>";
		}
		if (desc != null) {
			tip = tip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Description: </strong> "
					+ desc + "</td></tr>";
		}
		tip = tip + "<tr><td colspan='2' class='ea-content-class'>";
		if (city != null) {
			tip = tip + "<strong>Location: </strong>" + city;
		}
		if (state != null) {
			tip = tip + ", " + state;
		}
		if (country != null) {
			tip = tip + ", " + country;
		}
		tip = tip + "</td></tr>";
		if (date != null) {
			tip = tip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Date: </strong> "
					+ date + "</td></tr>";
		}
		if (time != null) {
			tip = tip
					+ "  <tr><td colspan='2' class='ea-content-class'><strong>Timings: </strong>"
					+ time + "</td></tr>";
		}
		if (days != null) {
			tip = tip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>No.of.Days: </strong>"
					+ days + "</td></tr>";
		}
		if (orgBy != null) {
			tip = tip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Organized By: </strong>"
					+ orgBy + "</td></tr>";
		}
		tip = tip + "</table>";
		System.out.println("EventTip");
		System.out.println(tip);
		return tip;
	}

	/**
	 * method attendSession-To attend a session
	 * 
	 * @return String
	 */
	public void attendSession(String eventId,String sessionId) {
		ResultDTO resultDTO = null;
		@SuppressWarnings("unused")
		String retValue = "failure";
		SessionController sessionController = null;
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		String userId = (String) session.getAttribute("USERID");

		if (sessionId != null) {
			EventDTO eventDTO = new EventDTO();
			eventDTO.setUserId(userId);
			if(eventId!=null && sessionId!=null){
			eventDTO.setSessionId(sessionId);
			eventDTO.setEventId(eventId);
			try {
				sessionController = SessionFactory.attendSession(eventDTO);
				resultDTO = sessionController.attendSession(eventDTO);
				this.setEventDTO(resultDTO.getEventDTO());
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}

		retValue = "session";
	}

	public String leaveLiveSession() {

		leaveSession(this.sessionId);
		//populateSessionPage();
		getEventBeanInstance().populateAgendaPage();

		return "agenda";
	}

	public String leaveLiveSessionForLogOut() {

		leaveSession(this.sessionId);

		getLogoutBeanInstance().logOut();

		return "logout";
	}

	public String leaveLiveSessionForProfile() {

		leaveSession(this.sessionId);

		getUserBeanInstance().userProfilePopulate();

		return "profile";
	}

	public String leaveLiveSessionForEvents() {

		leaveSession(this.sessionId);

		getEventBeanInstance().populateEventsPage();

		return "event";
	}

	public void leaveLiveSessionListener(ActionEvent ae) {
		leaveSession(this.sessionId);
		System.out.println("In leaveLiveSessionListener Method");

	}

	/**
	 * method leaveSession-To leave from a session
	 * 
	 * @return String
	 */
	public void leaveSession(String sessionId) {
		ResultDTO resultDTO = null;
		@SuppressWarnings("unused")
		String retValue = "failure";
		SessionController sessionController = null;
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		String userId = (String) session.getAttribute("USERID");

		if (sessionId != null) {
			eventDTO = new EventDTO();
			eventDTO.setUserId(userId);
			eventDTO.setSessionId(sessionId);
			try {
				sessionController = SessionFactory.leaveSession(eventDTO);
				resultDTO = sessionController.leaveSession(eventDTO);
				this.setEventDTO(resultDTO.getEventDTO());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		retValue = "session";
	}

	public void setCurrentPage(String currentPage) {

		FacesContext facesContext = null;
		Application application = null;
		ValueBinding binding = null;

		ApplicationBean applicationBean = null;

		facesContext = FacesContext.getCurrentInstance();
		application = facesContext.getApplication();

		binding = application.createValueBinding("#{applicationBean}");
		applicationBean = (ApplicationBean) binding.getValue(facesContext);

		applicationBean.setCurrentPage(currentPage);

	}

	/**
	 * method sessionCommentsList - To list down session comments
	 * 
	 */
	@SuppressWarnings( { "unused", "unchecked" })
	public void sessionCommentsList(String sessionId) {
		ResultDTO resultDTO = null;
		HttpSession session = null;
		List<SessionDTO> sessionCommentList = null;
		SessionController sesssionController = null;
		sessionCommentList = new ArrayList<SessionDTO>();
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		
		SessionDTO sessionDTO = new SessionDTO();
		
		String userId = (String) session.getAttribute("USERID");
		sessionDTO.setSessionId(sessionId);
		sessionDTO.setUserId(userId);
		String userTimeZone = null;

		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
			sessionDTO.setUserTimeZone(userTimeZone);
		}

		try {
			sesssionController = SessionFactory.sessionCommentsList();
			resultDTO = sesssionController.sessionCommentsList(sessionDTO);
			sessionCommentList = resultDTO.getSessionCommentsList();
			this.setSessionCommentsList((List) sessionCommentList.get(0));
			List TwitterFacebookCheck = (List) sessionCommentList.get(1);
			if (TwitterFacebookCheck != null) {
				Iterator it = TwitterFacebookCheck.iterator();
				while (it.hasNext()) {
					sessionDTO = (SessionDTO) it.next();					
					if (sessionDTO.getFacebookCheck() != null
							&& sessionDTO.getFacebookCheck().equals("Y")) {
						this.setFacebookCheck(true);
					} else {
						this.setFacebookCheck(false);
					}
					if (sessionDTO.getTwitterCheck() != null
							&& sessionDTO.getTwitterCheck().equals("Y")) {
						this.setTwitterCheck(true);
					} else {
						this.setTwitterCheck(false);
					}
					if (sessionDTO.getLinkedinCheck() != null
							&& sessionDTO.getLinkedinCheck().equals("Y")) {
						this.setLinkedinCheck(true);
					} else {
						this.setLinkedinCheck(false);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sessionTweets(ActionEvent ae) {
		String sessionTag = sessionSearchTag(this.sessionId);

		System.out.println("sessionTag  >>" + sessionTag);
		searchTweets(sessionTag);
	}

	/**
	 * method attendSession-To attend a session
	 * 
	 * @return String
	 */
	public String sessionSearchTag(String sessionId) {
		ResultDTO resultDTO = null;
		@SuppressWarnings("unused")
		String sessionTag = null;
		SessionController sessionController = null;
		if (sessionId != null) {
			SessionDTO sessionDTO = new SessionDTO();
			eventDTO.setSessionId(sessionId);
			try {

				sessionController =SessionFactory.sessionSearchTag();
				resultDTO = sessionController.sessionSearchTag(sessionDTO);
				sessionTag = resultDTO.getEventDTO().getSessionTag();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sessionTag;

	}

	public String sessionSearchTag() {

		String sessionTag = null;

		sessionTag = sessionSearchTag(this.sessionId);

		return sessionTag;
	}

	public void searchTweets(String tags) {

		FacesContext facesContext = null;
		Application application = null;
		ValueBinding binding = null;

		SocialMediaBean socialMediaBean = null;

		facesContext = FacesContext.getCurrentInstance();
		application = facesContext.getApplication();

		binding = application.createValueBinding("#{socialMediaBean}");
		socialMediaBean = (SocialMediaBean) binding.getValue(facesContext);

		socialMediaBean.searchEventTweets(tags);

	}

	/**
	 * @method updateSessionComment = To update the session comments
	 * @return Void
	 */
	public void updateSessionComment(ActionEvent ae) {
		ResultDTO resultDTO = null;
		TwitterDTO twitterDTO = null;
		FaceBookDTO faceBookDTO = null;
		LinkedInDTO linkedInDTO = null;
		boolean result = false;
		boolean tweetShare = false;
		boolean facebookShare = false;
		boolean linkedinkShare = false;
		String[] tagsToInclude = null;
		HttpSession session = null;
		Object acessToken = null;
		String userTimeZone = null;
		SessionController sesssionController = null;
		SocialMediaController socialMediaController = null;
		FacesMessage facesMessage = null;

		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();

		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		String userId = (String) session.getAttribute("USERID");

		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		}

		tagsToInclude = sessionTag(this.getSessionId());
		
		
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionId(this.getSessionId());
		sessionDTO.setUserId(userId);
		sessionDTO.setUserTimeZone(userTimeZone);
		sessionDTO.setScComment(this.getSessionCommentValue());
		
		tweetShare = this.getTwitterCheck();
		facebookShare = this.getFacebookCheck();
		linkedinkShare = this.getLinkedinCheck();
		String fb = null;
		String errorfooterMsg = null;
		String footerMsg = null;
		String twitter = null;
		footerMsg = "Your comment is shared in ";
		errorfooterMsg = "Your comment is not Shared in ";
		try {
			sesssionController = SessionFactory.updateSessionComment();
			resultDTO = sesssionController.updateSessionComment(sessionDTO);
			// result = resultDTO.isResultStatus();
			if (tweetShare) {
				twitterDTO = new TwitterDTO();
				twitterDTO.setAccessToken(session.getAttribute("USERTWITTERACCESSTOKEN"));
				twitterDTO.setShareMsg(tweetShare);
				twitterDTO.setShareComment(this.getSessionCommentValue());
				if (tagsToInclude != null) {
					// twitterDTO.setSessionTweetTag("#"+sessionTweetTag);tagsToInclude
					if (tagsToInclude[0] != null) {
						twitterDTO.setSessionEventTweetTag(tagsToInclude[0]);
					}
					if (tagsToInclude[1] != null) {
						twitterDTO.setSessionTweetTag(tagsToInclude[1]);
					}
				}
				socialMediaController = SocialMediaFactory
						.shareMsgInTwitter(twitterDTO);
				resultDTO = socialMediaController.shareMsgInTwitter(twitterDTO);
				if (resultDTO.getTwitterDTO().getResultMessage() != null
						&& resultDTO.isResultStatus() == false) {
					errorfooterMsg = errorfooterMsg + "Twitter -Due to "
							+ resultDTO.getTwitterDTO().getResultMessage();
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
							errorfooterMsg, "");
				} else {
					footerMsg = footerMsg + "Twitter ";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
							footerMsg, "");

				}
			}
			if (facebookShare) {
				faceBookDTO = new FaceBookDTO();
				faceBookDTO.setAccessToken(session
						.getAttribute("FACEBOOKACCESSTOKEN"));
				faceBookDTO.setShareMsg(facebookShare);
				faceBookDTO.setShareComment(this.getSessionCommentValue());
				socialMediaController = SocialMediaFactory
						.shareMsgInFaceBook(faceBookDTO);
				resultDTO = socialMediaController
						.shareMsgInFaceBook(faceBookDTO);

				if (resultDTO.isResultStatus() == false) {
					errorfooterMsg = errorfooterMsg + "Facebook .";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
							errorfooterMsg, "");
				} else {
					footerMsg = footerMsg + " Facebook ";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
							footerMsg, "");

				}

			}
			if (linkedinkShare) {
				linkedInDTO = new LinkedInDTO();
				linkedInDTO.setAccessToken(session
						.getAttribute("LINKEDINACCESSTOKEN"));
				linkedInDTO.setShareMsg(linkedinkShare);
				linkedInDTO.setShareComment(this.getSessionCommentValue());
				socialMediaController = SocialMediaFactory
						.postLinkedinComment(linkedInDTO);
				resultDTO = socialMediaController
						.postLinkedinComment(linkedInDTO);

				if (resultDTO.isResultStatus() == false) {
					errorfooterMsg = errorfooterMsg + "LinkedIn .";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
							errorfooterMsg, "");
				} else {
					footerMsg = footerMsg + " LinkedIn ";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
							footerMsg, "");

				}
			}
			if (result) {
				// populateSessionPage();
				sessionCommentsList(this.sessionId);
				this.sessionCommentValue = "";
				this.setTwitterCheck(tweetShare);
				this.setFacebookCheck(facebookShare);
				this.setLinkedinCheck(linkedinkShare);
			} else {
				footerMsg = footerMsg + " Event Attend";
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						footerMsg, "");
				sessionCommentsList(this.sessionId);
			}
			this.sessionCommentValue = "";
			facesContext.addMessage("SOCIALMEDIAINVITE", facesMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method sessionTag- To get the particular Session Tag
	 * @param sessionId
	 */
	public String[] sessionTag(String sessionId) {
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		SessionController sessionController = null;
		String[] includeTag = new String[2];
		HttpSession session = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		String userTimeZone = null;
		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		}

		if (sessionId != null) {
			EventDTO eventDTO = new EventDTO();
			eventDTO.setSessionId(sessionId);
			eventDTO.setUserTimeZone(userTimeZone);
			try {

				sessionController = SessionFactory.sessionInformation(eventDTO);
				resultDTO = sessionController.sessionInformation(eventDTO);

				if (resultDTO != null) {
					if (resultDTO.getEventDTO().getEventTag() != null) {
						includeTag[0] = resultDTO.getEventDTO().getEventTag();
					}
					if (resultDTO.getEventDTO().getSessionTag() != null) {
						includeTag[1] = resultDTO.getEventDTO().getSessionTag();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return includeTag;
	}

	/**
	 * @method sessionSpeakerInformation
	 * @param sessionId
	 */
	public ResultDTO speakerInformation(String sessionId) {
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		SessionController sessionController = null;
		HttpSession session = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		String userTimeZone = null;
		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		}

		if (sessionId != null) {
			EventDTO eventDTO = new EventDTO();
			eventDTO.setSessionId(sessionId);
			eventDTO.setUserTimeZone(userTimeZone);
			try {

				sessionController = SessionFactory
						.sessionSpeakerInformation(eventDTO);
				resultDTO = sessionController
						.sessionSpeakerInformation(eventDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultDTO;
	}

	/**
	 * @method sessionSpeakerInfoListener
	 * @param ae
	 */

	public void attendConfirmListener(ActionEvent ae) {

		System.out.println("In Attend confirm Listener" + this.eventId+" <<>> "+this.sessionId);

	}

	/**
	 * @method sessionSpeakerInfoListener
	 * @param ae
	 */

	public void pageRefreshListener(ActionEvent ae) {

		FacesContext facesContext = null;
		HttpSession session = null;

		String sessionId = null;
		String currentSpeaker = null;

		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			sessionId = (String) session.getAttribute("CURRENTSESSIONID");
			currentSpeaker = (String) session.getAttribute("CURRENTSPEAKER");
			System.out.println("In page Refresh Listener" + sessionId);

			liveSessionAttendee(currentSpeaker);
			
			//getAttendeeBeanInstance().liveSessionAttendees(sessionId,
			//		currentSpeaker);
			sessionCommentsList(sessionId);

			// sessionTag = sessionSearchTag(this.sessionId);

			getSocialMediaBeanInstance().sessionTweets(ae);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method sessionAttendeeRefreshListener
	 * @param ae
	 */

	public void sessionAttendeeRefreshListener(ActionEvent ae) {

		FacesContext facesContext = null;
		HttpSession session = null;
		String sessionId = null;
			try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);
			sessionId = (String) session.getAttribute("CURRENTSESSIONID");	
			System.out.println("Session Attendee Refresh Listener" + sessionId);
			//getAttendeeBeanInstance().sessionAttendees(sessionId);
			sessionAttendee();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @method liveAttendeeRefreshListener
	 * @param ae
	 */

	public void liveAttendeeRefreshListener(ActionEvent ae) {

		FacesContext facesContext = null;
		HttpSession session = null;

		String sessionId = null;
		String currentSpeaker = null;
		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			sessionId = (String) session.getAttribute("CURRENTSESSIONID");
			currentSpeaker = (String) session.getAttribute("CURRENTSPEAKER");

			System.out.println("In live Attendee Refresh Listener" + sessionId);

			liveSessionAttendee(currentSpeaker);
			//getAttendeeBeanInstance().liveSessionAttendees(sessionId,
			//		currentSpeaker);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method sessionCommentRefreshListener
	 * @param ae
	 */

	public void sessionCommentRefreshListener(ActionEvent ae) {

		FacesContext facesContext = null;
		HttpSession session = null;

		String sessionId = null;

		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			sessionId = (String) session.getAttribute("CURRENTSESSIONID");

			System.out.println("In session Comment RefreshListener Listener"
					+ sessionId);

			sessionCommentsList(sessionId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method sessionLikeRefreshListener
	 * @param ae
	 */

	public void sessionLikeRefreshListener(ActionEvent ae) {

		FacesContext facesContext = null;
		HttpSession session = null;

		String sessionId = null;

		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			sessionId = (String) session.getAttribute("CURRENTSESSIONID");

			System.out.println("In session Like RefreshListener Listener==>"
					+ sessionId);

			String sessionLikeCount = getAttendeeBeanInstance().getSessionLike(
					sessionId);

			getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
			getApplicationBeanInstance().setLike("Session");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String shrinkName(String str) {
		String shrinkStr = null;
		shrinkStr = str;
		if (shrinkStr != null && shrinkStr.length() >= 15) {
			shrinkStr = shrinkStr.substring(0, 15);
			shrinkStr = shrinkStr + "...";
		}
		System.out.println("<Session Name shrink>>>  " + shrinkStr);
		return shrinkStr;
	}

	public String speakerToolTip(ProfileDTO profileDTO) {

		String tip = null;
		String name = null;
		String email = null;
		String altEmail = null;
		String mobile = null;
		String home = null;
		String office = null;
		String address = null;
		String city = null;
		String state = null;
		String country = null;
		String zip = null;
		String edu = null;
		String occup = null;
		String website = null;
		String speakerFor = null;
		String keynotes = null;
		if (profileDTO.getFirstName() != null
				&& profileDTO.getLastName() != null) {
			name = profileDTO.getFirstName() + " " + profileDTO.getLastName();
		}

		if (profileDTO.getProfileEmail() != null) {
			email = profileDTO.getProfileEmail();
		}
		if (profileDTO.getAlternativeEmail() != null) {
			altEmail = profileDTO.getAlternativeEmail();
		}
		if (profileDTO.getMobile() != null) {
			mobile = profileDTO.getMobile();
		}
		if (profileDTO.getLandHome() != null) {
			home = profileDTO.getLandHome();
		}
		if (profileDTO.getLandOffice() != null) {
			office = profileDTO.getLandOffice();
		}
		if (profileDTO.getAddress() != null) {
			address = profileDTO.getAddress();
		}
		if (profileDTO.getCity() != null) {
			city = profileDTO.getCity();
		}
		if (profileDTO.getState() != null) {
			state = profileDTO.getState();
		}
		if (profileDTO.getCountry() != null) {
			country = profileDTO.getCountry();
		}
		if (profileDTO.getZip() != null) {
			zip = profileDTO.getZip();
		}
		if (profileDTO.getEducation() != null) {
			edu = profileDTO.getEducation();
		}
		if (profileDTO.getOccupation() != null) {
			occup = profileDTO.getOccupation();
		}
		if (profileDTO.getWebsite() != null) {
			website = profileDTO.getWebsite();
		}
		if (profileDTO.getSpeakerFor() != null) {
			speakerFor = profileDTO.getSpeakerFor();
		}
		if (profileDTO.getSpeakerKeyNotes() != null) {
			keynotes = profileDTO.getSpeakerKeyNotes();
		}

		tip = "<table width='311' border='0' cellpadding='0' cellspacing='0' class='profile-tooltip'><tr><td width='152' class='ea-heading-class'><Strong>Speaker of the Session - ";
		if (name != null) {
			tip = tip + name;
		}
		tip = tip
				+ "</td><td width='159' rowspan='2' align='center'></td></tr><tr><td class='ea-content-class'>";
		if (occup != null) {
			tip = tip + occup;
		}
		tip = tip + "</td></tr><tr><td class='ea-content-class'>";
		if (website != null) {
			tip = tip + website;
		}
		tip = tip + "</td><td align='center' class='ea-content-class'>";
		if (email != null) {
			tip = tip + email;
		}
		tip = tip + "</td></tr><tr><td class='ea-content-class'>";
		if (mobile != null) {
			tip = tip + "<strong>Mobile: </strong>" + mobile;
		}
		tip = tip + "</td><td class='ea-content-class'>";
		if (office != null) {
			tip = tip + "<strong>Office: </strong>" + office;
		}
		tip = tip + "</td></tr><tr><td colspan='2' class='ea-content-class'>";

		if (address != null) {
			tip = tip + "<strong>Address: </strong>" + address;
		}
		if (country != null) {
			tip = tip + ", " + country;
		}
		if (state != null) {
			tip = tip + ", " + state;
		}
		if (city != null) {
			tip = tip + ", " + city;
		}
		if (zip != null) {
			tip = tip + "- " + zip;
		}

		tip = tip + "</td></tr>";

		if (speakerFor != null) {
			tip = tip
					+ "<tr height='2'><td colspan='2'><hr /></td></tr><tr><td colspan='2' class='ea-content-class'><strong>Speaker for: </strong>"
					+ speakerFor + "</td></tr>";

		}
		if (keynotes != null) {
			tip = tip
					+ "<tr><td colspan='2' class='ea-content-class'><strong>Keynotes: </strong>"
					+ keynotes + "</td></tr>";

		}
		tip = tip + "<tr><td>&nbsp;</td><td>&nbsp;</td></tr></table>";

		return tip;

	}


	/**
	 * @method resetLiveSessionInfo - To reset the Live Session Attendess
	 * @param
	 * @return void
	 */
	public void resetLiveSessionInfo() {

		SessionController sessionController = null;
		ResultDTO resultDTO = null;
		SessionDTO sessionDTO = null;
		try {
			sessionController = SessionFactory.resetLiveSessionInfo();
			resultDTO = sessionController.resetLiveSessionInfo(sessionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method resetLiveSessionInfo - Listener to reset the Live Session
	 *         Attendess
	 * @param ActionEvent
	 * @return void
	 */
	public void resetLiveSessionInfo(ActionEvent ae) {
		System.out.println("In resetLiveSessionInfo method");
	}

	/**
	 * @method getSessionByEventId - Getting session for event - to select
	 *         speaker
	 * @param ActionEvent
	 * @return void
	 */
	public List getSessionByEventId(String eventId) {

		SessionController sessionController = null;

		ResultDTO resultDTO = null;

		List sessionList = null;

		try {

			sessionController = SessionFactory.getSessionByEventId();
			resultDTO = sessionController.getSessionByEventId(eventId);

			if (resultDTO != null) {
				sessionList = resultDTO.getSessionDTO().getSessionList();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sessionList;

	}
	
	public String populateSessionPageAction(){
		
		
		
		return null;
	}
	
	
	public void populateSessionPageNew(){
		
		sessionAttendee();
		
		String sessionLikeCount = getAttendeeBeanInstance().getSessionLike(
				this.sessionId);
		getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
		
		sessionInformation();
		
		speakerInformation();
		
		
	}
	
	public void sessionInformation(){
		
	}
	
	public void speakerInformation(){
		
	}
	
	public SpeakerDTO saveSessionIdForSpeaker(String sessionId, String userId) {
		ResultDTO resultDTO = null;
		SessionController sessionController = null;
		SessionDTO sessionDTO = null;
		SpeakerDTO speakerDTO = null;

		try {

			if (sessionId != null) {

				sessionDTO = new SessionDTO();
				sessionDTO.setSessionId(sessionId);
				sessionDTO.setUserId(userId);

				sessionController = SessionFactory.saveSessionIdForSpeaker();

				resultDTO = sessionController
						.saveSessionIdForSpeaker(sessionDTO);

				if (resultDTO != null) {
					speakerDTO = resultDTO.getSpeakerDTO();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return speakerDTO;
	}
	
	public SpeakerDTO removeSessionIdForSpeaker(String sessionId, String userId) {
		ResultDTO resultDTO = null;
		SessionController sessionController = null;
		SessionDTO sessionDTO = null;
		SpeakerDTO speakerDTO = null;

		try {

			if (sessionId != null) {

				sessionDTO = new SessionDTO();
				sessionDTO.setSessionId(sessionId);
				sessionDTO.setUserId(userId);

				sessionController = SessionFactory.removeSessionIdForSpeaker();

				resultDTO = sessionController
						.removeSessionIdForSpeaker(sessionDTO);

				if (resultDTO != null) {
					speakerDTO = resultDTO.getSpeakerDTO();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return speakerDTO;
	}

}
