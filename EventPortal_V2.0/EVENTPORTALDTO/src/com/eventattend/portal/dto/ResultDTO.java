package com.eventattend.portal.dto;

import java.util.List;

public class ResultDTO {
	
	/*To set Final List*/
	private List resultList = null;
	/*To set Attendee List*/
	private List attendeeList = null;
	/*To set Agenda List*/	
	private List agendaList = null;
	/*To set Agenda DTO Object*/	
	
	private LoginDTO loginDTO = null;
	
	
	private boolean resultStatus = false;
	
	private String resultMsg = null;
	
	private ProfileDTO profileDTO = null;
	private SpeakerDTO speakerDTO = null;
	private EventDTO eventDTO = null;	
	private boolean isUserActivated = false;
	private List pastEventList = null;
	private List liveEventList = null;
	private List futureEventList = null;
	private List agendaSessionList = null;
	private List eventAttendeeProfileList = null;
	private List sessionCommentsList = null;
	private List eventList = null;
	private List eventSessionList = null;
	
	private TwitterDTO twitterDTO = null;
	
	private SocialMediaDTO socialMediaDTO = null;
	private LinkedInDTO linkedInDTO = null;
	private FaceBookDTO faceBookDTO = null;
	
	private SessionDTO sessionDTO = null;
	private AttendeeDTO attendeeDTO = null;
	
	private MailDTO mailDTO = null;
	
	private UserDTO userDTO = null;
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public AttendeeDTO getAttendeeDTO() {
		return attendeeDTO;
	}

	public void setAttendeeDTO(AttendeeDTO attendeeDTO) {
		this.attendeeDTO = attendeeDTO;
	}

	public TwitterDTO getTwitterDTO() {
		return twitterDTO;
	}

	public void setTwitterDTO(TwitterDTO twitterDTO) {
		this.twitterDTO = twitterDTO;
	}

	public SocialMediaDTO getSocialMediaDTO() {
		return socialMediaDTO;
	}

	public void setSocialMediaDTO(SocialMediaDTO socialMediaDTO) {
		this.socialMediaDTO = socialMediaDTO;
	}

	public List getEventAttendeeProfileList() {
		return eventAttendeeProfileList;
	}

	public void setEventAttendeeProfileList(List eventAttendeeProfileList) {
		this.eventAttendeeProfileList = eventAttendeeProfileList;
	}	

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	
	public List getAttendeeList() {
		return attendeeList;
	}

	public void setAttendeeList(List attendeeList) {
		this.attendeeList = attendeeList;
	}

	public List getAgendaList() {
		return agendaList;
	}

	public void setAgendaList(List agendaList) {
		this.agendaList = agendaList;
	}

	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public boolean isResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(boolean resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}
	
	public boolean isUserActivated() {
		return isUserActivated;
	}

	public void setUserActivated(boolean isUserActivated) {
		this.isUserActivated = isUserActivated;
	}
	
	public EventDTO getEventDTO() {
		return eventDTO;
	}
	
	

	public List getPastEventList() {
		return pastEventList;
	}



	public void setPastEventList(List pastEventList) {
		this.pastEventList = pastEventList;
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



	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

	public List getAgendaSessionList() {
		return agendaSessionList;
	}

	public void setAgendaSessionList(List agendaSessionList) {
		this.agendaSessionList = agendaSessionList;
	}

	public LinkedInDTO getLinkedInDTO() {
		return linkedInDTO;
	}

	public void setLinkedInDTO(LinkedInDTO linkedInDTO) {
		this.linkedInDTO = linkedInDTO;
	}

	public FaceBookDTO getFaceBookDTO() {
		return faceBookDTO;
	}

	public void setFaceBookDTO(FaceBookDTO faceBookDTO) {
		this.faceBookDTO = faceBookDTO;
	}

	public List getSessionCommentsList() {
		return sessionCommentsList;
	}

	public void setSessionCommentsList(List sessionCommentsList) {
		this.sessionCommentsList = sessionCommentsList;
	}

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}
	
	public List getEventList() {
		return eventList;
	}
	public void setEventList(List eventList) {
		this.eventList = eventList;
	}

	public List getEventSessionList() {
		return eventSessionList;
	}

	public void setEventSessionList(List eventSessionList) {
		this.eventSessionList = eventSessionList;
	}

	public MailDTO getMailDTO() {
		return mailDTO;
	}

	public void setMailDTO(MailDTO mailDTO) {
		this.mailDTO = mailDTO;
	}

	public SpeakerDTO getSpeakerDTO() {
		return speakerDTO;
	}

	public void setSpeakerDTO(SpeakerDTO speakerDTO) {
		this.speakerDTO = speakerDTO;
	}

		
	
}
