package com.eventattend.portal.web.bean;



import java.util.Date;
import java.util.Map;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.eventattend.portal.Factory.MaintenanceFactory;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.controller.MaintenanceController;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.MaintenanceDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;

public class MaintenanceBean extends ApplicationBean {
	private String eventId = null;	
	private String selectedEventId = null;	
	private String selectedSessionId = null;	
	private String eventStartTime = null;
	private String eventEndTime = null;	
	private String eventLocation = null;
	private String eventNoOfDays = null;
	private String eventOrganizedBy = null;
	private String eventWebsite = null;	
	private String eventTag = null;
	private String eventCountry = null;
	private String eventCity = null;
	private String eventState = null;
	private String eventTimezone = null;
	private String eventLikeCount = null;
	private String sessionId = null;
	private String sessionName = null;
	private String sessionEventName = null;
	private Date sessionStartDate = null;
	private Date sessionEndDate = null;
	private String sessionKeyNote = null;
	private String sessionVenueName = null;
	private String sessionTag = null;

	private boolean addButtonEnable = false;;
	private boolean showEventList = false;
	private boolean showSessionList = false;
	private List eventsList = null;
	private List sessionList = null;
	private List attendeeList = null;
	private Date checkDate = null;
	private Date eventStartDate = null;
	private Date eventEndDate = null;
	private String eventName = null;
	private String eventDescription = null;	
	private Date newStartDate = null;
	private Date newEndDate = null;	
	private String newEventName = null;
	private String newEventDesc = null;
	private String sessionEventId = null;
	
	private Map eventMap = null;
	
	
	public List getAttendeeList() {
		return attendeeList;
	}


	public void setAttendeeList(List attendeeList) {
		this.attendeeList = attendeeList;
	}


	public String getSessionEventId() {
		return sessionEventId;
	}


	public void setSessionEventId(String sessionEventId) {
		this.sessionEventId = sessionEventId;
	}


	public Map getEventMap() {
		return eventMap;
	}


	public void setEventMap(Map eventMap) {
		this.eventMap = eventMap;
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


	public String getEventDescription() {
		return eventDescription;
	}


	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}


	public String getSelectedEventId() {
		return selectedEventId;
	}


	public void setSelectedEventId(String selectedEventId) {
		this.selectedEventId = selectedEventId;
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


	public String getEventTag() {
		return eventTag;
	}


	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
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


	public String getEventTimezone() {
		return eventTimezone;
	}


	public void setEventTimezone(String eventTimezone) {
		this.eventTimezone = eventTimezone;
	}


	public String getEventLikeCount() {
		return eventLikeCount;
	}


	public void setEventLikeCount(String eventLikeCount) {
		this.eventLikeCount = eventLikeCount;
	}


	public Date getCheckDate() {
		return checkDate;
	}


	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public List getEventsList() {
		return eventsList;
	}


	public void setEventsList(List eventsList) {
		this.eventsList = eventsList;
	}
	


	public boolean isAddButtonEnable() {
		return addButtonEnable;
	}


	public void setAddButtonEnable(boolean addButtonEnable) {
		this.addButtonEnable = addButtonEnable;
	}
	
	public boolean isShowEventList() {
		return showEventList;
	}


	public void setShowEventList(boolean showEventList) {
		this.showEventList = showEventList;
	}

	
	

	public Date getNewStartDate() {
		return newStartDate;
	}


	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}


	public Date getNewEndDate() {
		return newEndDate;
	}


	public void setNewEndDate(Date newEndDate) {
		this.newEndDate = newEndDate;
	}


	public String getNewEventName() {
		return newEventName;
	}


	public void setNewEventName(String newEventName) {
		this.newEventName = newEventName;
	}


	public String getNewEventDesc() {
		return newEventDesc;
	}


	public void setNewEventDesc(String newEventDesc) {
		this.newEventDesc = newEventDesc;
	}
	
	
	public Date getEventStartDate() {
		return eventStartDate;
	}


	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}


	public Date getEventEndDate() {
		return eventEndDate;
	}


	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}


	public List getSessionList() {
		return sessionList;
	}


	public void setSessionList(List sessionList) {
		this.sessionList = sessionList;
	}


	public boolean isShowSessionList() {
		return showSessionList;
	}


	public void setShowSessionList(boolean showSessionList) {
		this.showSessionList = showSessionList;
	}


	public String getSessionName() {
		return sessionName;
	}


	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}


	public String getSessionEventName() {
		return sessionEventName;
	}


	public void setSessionEventName(String sessionEventName) {
		this.sessionEventName = sessionEventName;
	}


	public Date getSessionStartDate() {
		return sessionStartDate;
	}


	public void setSessionStartDate(Date sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}


	public Date getSessionEndDate() {
		return sessionEndDate;
	}


	public void setSessionEndDate(Date sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}


	public String getSessionKeyNote() {
		return sessionKeyNote;
	}


	public void setSessionKeyNote(String sessionKeyNote) {
		this.sessionKeyNote = sessionKeyNote;
	}


	public String getSessionVenueName() {
		return sessionVenueName;
	}


	public void setSessionVenueName(String sessionVenueName) {
		this.sessionVenueName = sessionVenueName;
	}


	public String getSessionTag() {
		return sessionTag;
	}


	public void setSessionTag(String sessionTag) {
		this.sessionTag = sessionTag;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String dataMaintenanceAction(){
		
	System.out.println("In Data Maintenace Action Method");
	
	events();
	//sessions();
	//attendee();
	return "MAINTENANCE";
	}
	
	public void manageEventsListener(ActionEvent ae){
		
		events();
	}
	
	public void manageSessionsListener(ActionEvent ae){
		
		sessions();
	}
	
	public void manageAttendeeListener(ActionEvent ae){
		
	attendee();
	
	}
	
	public void attendee() {			
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		MaintenanceController maintenanceController = null;	
		MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();		
		try {
			
			facesContext = FacesContext.getCurrentInstance();
			maintenanceController = MaintenanceFactory.attendee();
		    resultDTO =maintenanceController.attendee(maintenanceDTO);
		    this.setAttendeeList(resultDTO.getEventAttendeeProfileList());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sessions() {
		resetSessionBean();
		HttpSession session = null;
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		MaintenanceController maintenanceController = null;	
		MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();		
		SessionDTO sessionDTO = new SessionDTO();
		
		String userTimeZone = null;
		Map eventMap = null;

		try {
			
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
			
			
			sessionDTO.setUserTimeZone(userTimeZone);
			maintenanceDTO.setSessionDTO(sessionDTO);	
			
			maintenanceController = MaintenanceFactory.sessions();
		    resultDTO =maintenanceController.sessions(maintenanceDTO);
		    	
		    this.setSessionList(resultDTO.getEventSessionList());
		    
		    eventMap = getEventBeanInstance().getEventsForSpeaker();
		    
			this.eventMap = eventMap;
			
		    this.setShowSessionList(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void events() {
		resetBean();
		HttpSession session = null;
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		MaintenanceController maintenanceController = null;		
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String userTimeZone = null;
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		EventDTO eventDTO = new EventDTO();
		MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
		eventDTO.setUserTimeZone(userTimeZone);
		maintenanceDTO.setEventDTO(eventDTO);
		try {
			maintenanceController = MaintenanceFactory.events();
		    resultDTO =maintenanceController.events(maintenanceDTO);
		    this.setEventsList(resultDTO.getEventList());
		    this.setAddButtonEnable(true);
		    this.setShowEventList(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		/**
	 * @param ae
	 */
		  public void addEventListener(ActionEvent ae) {
		  FacesContext facesContext = null;
		  FacesMessage facesMessage = null;
		  EventDTO eventDTO = new EventDTO();
		  MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
		  ResultDTO resultDTO = null;
		  MaintenanceController maintenanceController = null;
		  String errorMessage = null;
		  try {
		   
		   
		   facesContext = FacesContext.getCurrentInstance();
		   eventDTO.setEventName(this.getEventName());
		   eventDTO.setEventDescription(this.getEventDescription());
		   eventDTO.setEventCountry(this.getEventCountry());
		   eventDTO.setEventState(this.getEventState());  
		   
		   System.out.println("Start Date==>"+this.getEventStartDate());
		   
		   System.out.println("End Date==>"+this.getEventEndDate());
	   
		   if(this.getEventStartDate() != null){
			   eventDTO.setEventStartDate(this.getEventStartDate().toString());  
		   }
		   
		   if(this.getEventEndDate() != null){
			   eventDTO.setEventEndDate(this.getEventEndDate().toString());  
		   }
		  
		   eventDTO.setEventState(this.getEventState());
		   eventDTO.setEventCity(this.getEventCity());
		   eventDTO.setEventLocation(this.getEventLocation());
		   eventDTO.setEventTag(this.getEventTag());
		   eventDTO.setEventOrganizedBy(this.getEventOrganizedBy());
		   eventDTO.setEventWebsite(this.getEventWebsite());
		  // eventDTO.setEventNoOfDays("2");
		   eventDTO.setEventTimeZone(this.getEventTimezone());
		   maintenanceDTO.setEventDTO(eventDTO);

		   maintenanceController = MaintenanceFactory.addEvent();
		      resultDTO =maintenanceController.addEvent(maintenanceDTO);
		      events();
		      resetBean();
		      
		      if (resultDTO.isResultStatus()) {
		       errorMessage = "Event added sucessfully";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   } else {
		    errorMessage = "Failed to add Event";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   }
		      
		  } catch (Exception e) {
		   errorMessage = "Internal Error Occured";
		   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
		     errorMessage, "");
		   e.printStackTrace();
		  }
		  if (errorMessage != null) {
		   facesContext.addMessage("profileupdate", facesMessage);
		  }
		  
		  System.out.println(">>> "+this.getNewEventName()+" "+this.getNewStartDate()+" "+this.getNewEndDate());
		  
		 }
		  public void addSessionListener(ActionEvent ae) {
			  FacesContext facesContext = null;
			  FacesMessage facesMessage = null;
			  SessionDTO sessionDTO = new SessionDTO();
			  MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
			  ResultDTO resultDTO = null;
			  MaintenanceController maintenanceController = null;
			  String errorMessage = null;
			 
			  try {
								
				  sessionDTO.setSessionName(this.getSessionName());
				  
				  sessionDTO.setSessionEventId(this.getSessionEventId());
				  
				  sessionDTO.setSessionStartDate(this.getSessionStartDate().toString());
				  sessionDTO.setSessionEndDate(this.getSessionEndDate().toString());
				  sessionDTO.setSessionKeyNote(this.getSessionKeyNote());
				  sessionDTO.setSessionVenueName(this.getSessionVenueName());
				  sessionDTO.setSessionTag(this.getSessionTag());
					
			   facesContext = FacesContext.getCurrentInstance();
			      maintenanceDTO.setSessionDTO(sessionDTO);

			   maintenanceController = MaintenanceFactory.addSession();
			      resultDTO =maintenanceController.addSession(maintenanceDTO);
			      sessions();
			      resetSessionBean();
			      
			      if (resultDTO.isResultStatus()) {
			       errorMessage = "Session added sucessfully";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   } else {
			    errorMessage = "Failed to add Session";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   }
			      
			  } catch (Exception e) {
			   errorMessage = "Internal Error Occured";
			   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			     errorMessage, "");
			   e.printStackTrace();
			  }
			  if (errorMessage != null) {
			   facesContext.addMessage("profileupdate", facesMessage);
			  }
			 }
			  
		  
		  public void sessionListener(ActionEvent ae) {
				HttpSession session = null;
				FacesContext facesContext = null;
				ResultDTO resultDTO = null;
				MaintenanceController maintenanceController = null;						
				try {
					
					facesContext = FacesContext.getCurrentInstance();
					session = (HttpSession) facesContext.getExternalContext().getSession(true);
					String userTimeZone = null;
					userTimeZone = (String) session.getAttribute("USERTIMEZONE");
					SessionDTO sessionDTO = new SessionDTO();
					MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
					sessionDTO.setUserTimeZone(userTimeZone);
					sessionDTO.setSessionId(this.getSelectedSessionId());
					maintenanceDTO.setSessionDTO(sessionDTO);					
					maintenanceController = MaintenanceFactory.session();				   
					resultDTO = maintenanceController.session(maintenanceDTO);
					this.selectedSessionId =resultDTO.getSessionDTO().getSessionId();
					this.sessionId =resultDTO.getSessionDTO().getSessionId();
					this.sessionName = resultDTO.getSessionDTO().getSessionName();
					this.sessionEventName = resultDTO.getSessionDTO().getSessionEventName();
					this.sessionStartDate = resultDTO.getSessionDTO().getSessionStartDateWithTime();
					this.sessionEndDate = resultDTO.getSessionDTO().getSessionEndDateWithTime();
					this.sessionKeyNote = resultDTO.getSessionDTO().getSessionKeyNote();
					this.sessionVenueName = resultDTO.getSessionDTO().getSessionVenueName();
					this.sessionTag = resultDTO.getSessionDTO().getSessionTag();
					this.setAddButtonEnable(false);
					 this.setShowSessionList(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		  public void resetSessionBean(){
				this.sessionId = null;
				this.sessionName = null;
				this.sessionEventName = null;
				this.sessionStartDate = null;
				this.sessionEndDate = null;
				this.sessionKeyNote = null;
				this.sessionVenueName = null;
				this.sessionTag = null;
			}
			
	public void resetBean(){
		
		this.eventName = null;
		this.eventDescription = null;
		this.eventCountry = null;
		this.eventCity = null;
		this.eventState = null;
		this.eventStartDate = null;
		this.eventEndDate = null;
		this.eventTag = null;
		this.eventOrganizedBy = null;
		this.eventLocation = null;
		this.eventWebsite = null;
		this.eventNoOfDays = null;
		this.eventTimezone = null;
		
	}
	
	public String getSelectedSessionId() {
		return selectedSessionId;
	}


	public void setSelectedSessionId(String selectedSessionId) {
		this.selectedSessionId = selectedSessionId;
	}


	public void eventListener(ActionEvent ae) {
		HttpSession session = null;
		FacesContext facesContext = null;
		ResultDTO resultDTO = null;
		MaintenanceController maintenanceController = null;		
		
		try {
			
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			String userTimeZone = null;
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
			EventDTO eventDTO = new EventDTO();
			MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
			eventDTO.setUserTimeZone(userTimeZone);
			eventDTO.setEventId(this.getSelectedEventId());
			maintenanceDTO.setEventDTO(eventDTO);
			
			maintenanceController = MaintenanceFactory.event();
		   
			resultDTO = maintenanceController.event(maintenanceDTO);
			
		    this.eventId =  resultDTO.getEventDTO().getEventId();
			this.eventName = resultDTO.getEventDTO().getEventName();
			this.eventDescription = resultDTO.getEventDTO().getEventDescription();
			this.eventCountry = resultDTO.getEventDTO().getEventCountry();
			this.eventCity = resultDTO.getEventDTO().getEventCity();
			this.eventState = resultDTO.getEventDTO().getEventState();
			
			this.eventStartDate = resultDTO.getEventDTO().getEventStartDateWithTime();
			this.eventEndDate = resultDTO.getEventDTO().getEventEndDateWithTime();
			
			this.eventTag = resultDTO.getEventDTO().getEventTag();
			this.eventOrganizedBy = resultDTO.getEventDTO().getEventOrganizedBy();
			this.eventLocation = resultDTO.getEventDTO().getEventLocation();
			this.eventWebsite = resultDTO.getEventDTO().getEventWebsite();
			this.eventNoOfDays = resultDTO.getEventDTO().getEventNoOfDays();
			this.eventTimezone = resultDTO.getEventDTO().getEventTimeZone();
			this.setAddButtonEnable(false);
			 this.setShowEventList(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateEventListener(ActionEvent ae) {
		HttpSession session = null;
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;			
		ResultDTO resultDTO = null;
		  String errorMessage = null;
		MaintenanceController maintenanceController = null;		
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String userTimeZone = null;
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		EventDTO eventDTO = new EventDTO();
		MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
		eventDTO.setUserTimeZone(userTimeZone);
		eventDTO.setEventId(this.eventId);
		eventDTO.setEventName(this.getEventName());
		eventDTO.setEventDescription(this.getEventDescription());
		eventDTO.setEventCountry(this.getEventCountry());
		eventDTO.setEventState(this.getEventState());  
		 if(this.getEventStartDate() != null){
			   eventDTO.setEventStartDate(this.getEventStartDate().toString());  
		   }
		   
		   if(this.getEventEndDate() != null){
			   eventDTO.setEventEndDate(this.getEventEndDate().toString());  
		   }
		  
		eventDTO.setEventState(this.getEventState());
		eventDTO.setEventCity(this.getEventCity());
		eventDTO.setEventLocation(this.getEventLocation());
		eventDTO.setEventTag(this.getEventTag());
		eventDTO.setEventOrganizedBy(this.getEventOrganizedBy());
		eventDTO.setEventWebsite(this.getEventWebsite());
		  // eventDTO.setEventNoOfDays("2");
		eventDTO.setEventTimeZone(this.getEventTimezone());
		maintenanceDTO.setEventDTO(eventDTO);
		System.out.println(">>> Update"+this.eventId);
		try {
		 facesContext = FacesContext.getCurrentInstance();
		   
		   maintenanceController = MaintenanceFactory.updateEvent();
		      resultDTO =maintenanceController.updateEvent(maintenanceDTO);
		      events();
		      resetBean();
		      this.setShowEventList(true);
		      
		      if (resultDTO.isResultStatus()) {
		       errorMessage = "Event updated sucessfully";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   } else {
		    errorMessage = "Failed to update Event";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   }
		      
		  } catch (Exception e) {
		   errorMessage = "Internal Error Occured";
		   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
		     errorMessage, "");
		   e.printStackTrace();
		  }
		  if (errorMessage != null) {
		   facesContext.addMessage("profileupdate", facesMessage);
		  }	
	}
	
	public void updateSessionListener(ActionEvent ae) {
		HttpSession session = null;
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;			
		ResultDTO resultDTO = null;
		  String errorMessage = null;
		MaintenanceController maintenanceController = null;		
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String userTimeZone = null;
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		SessionDTO sessionDTO = new SessionDTO();
		MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
		sessionDTO.setUserTimeZone(userTimeZone);		
		  sessionDTO.setSessionName(this.getSessionName());
		  sessionDTO.setSessionEventId(this.getSessionEventId());
		  sessionDTO.setSessionStartDate(this.getSessionStartDate().toString());
		  sessionDTO.setSessionEndDate(this.getSessionEndDate().toString());
		  sessionDTO.setSessionKeyNote(this.getSessionKeyNote());
		  sessionDTO.setSessionVenueName(this.getSessionVenueName());
		  sessionDTO.setSessionTag(this.getSessionTag());
		  sessionDTO.setSessionId(this.sessionId);
		maintenanceDTO.setSessionDTO(sessionDTO);
		System.out.println(">>> Update"+this.sessionId);
		try {
		 facesContext = FacesContext.getCurrentInstance();
		   
		   maintenanceController = MaintenanceFactory.updateSession();
		      resultDTO =maintenanceController.updateSession(maintenanceDTO);
		      sessions();
		      resetSessionBean();
		      this.setShowSessionList(true);
		      
		      if (resultDTO.isResultStatus()) {
		       errorMessage = "Session updated sucessfully";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   } else {
		    errorMessage = "Failed to update Session";
		    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		      errorMessage, "");
		   }
		      
		  } catch (Exception e) {
		   errorMessage = "Internal Error Occured";
		   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
		     errorMessage, "");
		   e.printStackTrace();
		  }
		  if (errorMessage != null) {
		   facesContext.addMessage("profileupdate", facesMessage);
		  }	
	}
	public void deleteEventListener(ActionEvent ae) {

		  HttpSession session = null;
			FacesContext facesContext = null;
			FacesMessage facesMessage = null;			
			ResultDTO resultDTO = null;
			  String errorMessage = null;
			MaintenanceController maintenanceController = null;		
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			String userTimeZone = null;
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
			EventDTO eventDTO = new EventDTO();
			MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
			eventDTO.setUserTimeZone(userTimeZone);
			eventDTO.setEventId(this.eventId);
			maintenanceDTO.setEventDTO(eventDTO);
			  System.out.println(">>> Delete"+this.eventId);
			try {
			 facesContext = FacesContext.getCurrentInstance();
			   
			   maintenanceController = MaintenanceFactory.deleteEvent();
			      resultDTO =maintenanceController.deleteEvent(maintenanceDTO);
			      events();
			      resetBean();
			      this.setShowEventList(true);
			      if (resultDTO.isResultStatus()) {
			       errorMessage = "Event deleted sucessfully";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   } else {
			    errorMessage = "Failed to delete Event";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   }
			      
			  } catch (Exception e) {
			   errorMessage = "Internal Error Occured";
			   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			     errorMessage, "");
			   e.printStackTrace();
			  }
			  if (errorMessage != null) {
			   facesContext.addMessage("profileupdate", facesMessage);
			  }	
	}
	
	
	public void deleteSessionListener(ActionEvent ae) {

		  	HttpSession session = null;
			FacesContext facesContext = null;
			FacesMessage facesMessage = null;			
			ResultDTO resultDTO = null;
			String errorMessage = null;
			MaintenanceController maintenanceController = null;		
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			String userTimeZone = null;
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
			SessionDTO sessionDTO = new SessionDTO();
			MaintenanceDTO  maintenanceDTO = new MaintenanceDTO();
			sessionDTO.setUserTimeZone(userTimeZone);
			sessionDTO.setSessionId(this.sessionId);
			maintenanceDTO.setSessionDTO(sessionDTO);
			  System.out.println(">>> Delete"+this.sessionId);
			try {
			 facesContext = FacesContext.getCurrentInstance();
			   
			   maintenanceController = MaintenanceFactory.deleteSession();
			      resultDTO =maintenanceController.deleteSession(maintenanceDTO);
			      sessions();
			      resetSessionBean();
			      this.setShowSessionList(true);			      
			      if (resultDTO.isResultStatus()) {
			       errorMessage = "Session deleted sucessfully";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   } else {
			    errorMessage = "Failed to delete Session";
			    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
			      errorMessage, "");
			   }
			      
			  } catch (Exception e) {
			   errorMessage = "Internal Error Occured";
			   facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
			     errorMessage, "");
			   e.printStackTrace();
			  }
			  if (errorMessage != null) {
			   facesContext.addMessage("profileupdate", facesMessage);
			  }	
	}
	
	
	
	public void showAddModifyListener(ActionEvent ae){
		resetBean();
		this.setAddButtonEnable(true);
		this.showEventList = false;
		System.out.println("Show Add Modify Method");
	}
	
	public void showEventListListener(ActionEvent ae){
		this.showEventList = true;
		System.out.println("Show Event List Method");
	}
	public void showSessionListListener(ActionEvent ae){
		this.showSessionList = true;
		System.out.println("Show Session List Method");
	}
	public void showSessionAddModifyListener(ActionEvent ae){
		resetSessionBean();
		this.setAddButtonEnable(true);
		this.showSessionList = false;
		System.out.println("Show Session Add Modify Method");
	}
	public void deleteConfirmListener(ActionEvent ae){
		this.selectedEventId = this.eventId;
		System.out.println("Delete confirm Method eventId===>"+this.selectedEventId);
	}
	public void deleteSessionConfirmListener(ActionEvent ae){
		this.selectedSessionId = this.sessionId;
		System.out.println("Delete Session confirm Method eventId===>"+this.selectedSessionId);
	}
}
