package com.eventattend.portal.web.bean;

import javax.faces.application.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.http.AccessToken;
import com.eventattend.portal.Factory.EventFactory;
import com.eventattend.portal.Factory.SessionFactory;
import com.eventattend.portal.Factory.SocialMediaFactory;
import com.eventattend.portal.bo.Event;
import com.eventattend.portal.common.util.StringUtility;
import com.eventattend.portal.controller.EventController;
import com.eventattend.portal.controller.SessionController;
import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.MapDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.TwitterDTO;
import com.eventattend.portal.socialmedia.util.SocialMediaKeys;

public class EventBean extends ApplicationBean {

	private String userId = null;
	private String eventId = null;
	private String eventName = null;
	private String eventStartDate = null;
	private String eventEndDate = null;
	private String eventStartTime = null;
	private String eventEndTime = null;
	private String eventDescription = null;
	private String eventLocation = null;
	private String eventNoOfDays = null;
	private String eventOrganizedBy = null;
	private String eventWebsite = null;
	private List agendaSessionList = null;
	private List agendaSessionTabList = null;
	private List agendaSessionList1 = null;
	private String speakerName = null;
	private boolean joinedEvent = false;
	private String eventTag = null;
	private String searchKey = null;
	private EventDTO eventDTO = null;
	private TwitterDTO twitterDTO = null;
	private EventDTO eventDetailDTO = null;

	private String searchValue = null;
	private boolean searchResult = false;
	private String unjoinEventId = null;
	private String eventCategory = null;

	public String getUnjoinEventId() {
		return unjoinEventId;
	}

	public void setUnjoinEventId(String unjoinEventId) {
		this.unjoinEventId = unjoinEventId;
	}

	public EventBean() {
		super();
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public boolean isSearchResult() {
		return searchResult;
	}

	public void setSearchResult(boolean searchResult) {
		this.searchResult = searchResult;
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

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public TwitterDTO getTwitterDTO() {
		return twitterDTO;
	}

	public void setTwitterDTO(TwitterDTO twitterDTO) {
		this.twitterDTO = twitterDTO;
	}

	public List getAgendaSessionList() {
		return agendaSessionList;
	}

	public void setAgendaSessionList(List agendaSessionList) {
		this.agendaSessionList = agendaSessionList;
	}

	public List getAgendaSessionTabList() {
		return agendaSessionTabList;
	}

	public void setAgendaSessionTabList(List agendaSessionTabList) {
		this.agendaSessionTabList = agendaSessionTabList;
	}

	public List getAgendaSessionList1() {
		return agendaSessionList1;
	}

	public void setAgendaSessionList1(List agendaSessionList1) {
		this.agendaSessionList1 = agendaSessionList1;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public EventDTO getEventDetailDTO() {
		return eventDetailDTO;
	}

	public void setEventDetailDTO(EventDTO eventDetailDTO) {
		this.eventDetailDTO = eventDetailDTO;
	}

	/**
	 * @method - to populate event list page
	 * @param ae
	 */
	public String populateEventsPage() {

		listEvents();

		getApplicationBeanInstance().setCurrentPage("EVENTLIST");
		getApplicationBeanInstance().setLike("EventSum");
		getApplicationBeanInstance().setTotalEventLikeCount(
				getAttendeeBeanInstance().getTotalEventLike());

		getSocialMediaBeanInstance().setTwitterPollEnabled(true);
		getSocialMediaBeanInstance().setEventTag("");

		return "event";

	}

	/**
	 * @method listEvents-To get all the existing events
	 * @return String
	 */
	public String listEvents() {
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String retValue = "failure";
		String userTimeZone = null;
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		userId = (String) session.getAttribute("USERID");
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		eventName = this.getSearchKey();
		try {
			if (eventName != null && !eventName.equals("")) {
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setEventName(eventName);
				eventDTO.setUserTimeZone(userTimeZone);
				eventController = EventFactory.listEvents(eventDTO);
				resultDTO = eventController.listEvents(eventDTO);
				this.searchValue = eventName;
				this.searchResult = true;
			} else {
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setUserTimeZone(userTimeZone);
				eventController = EventFactory.listEvents(eventDTO);
				resultDTO = eventController.listEvents(eventDTO);
				this.searchResult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setEventDTO(resultDTO.getEventDTO());

		this.searchKey = null;
		System.out.println("In Event List Method");
		retValue = "event";

		return retValue;
	}

	public void searchEventsListener(ActionEvent ae) {

		listEvents();
		// this.searchResult = true;
	}

	/**
	 * @method eventDetails = To get the details\related data of an event
	 * @return Void (actionListioner user in jsp)
	 */
	public void eventDetails(ActionEvent ae) {

		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		HttpSession session = null;
		String retValue = "failure";
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		String userTimeZone = null;
		userId = (String) session.getAttribute("USERID");
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		eventId = this.getEventId();

		try {
			if (eventId != null) {
				facesContext = FacesContext.getCurrentInstance();
				// session = (HttpSession)
				// facesContext.getExternalContext().getSession(true);
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setEventId(eventId);
				eventDTO.setUserTimeZone(userTimeZone);
				eventController = EventFactory.eventDetails(eventDTO);
				resultDTO = eventController.eventDetails(eventDTO);
				this.setEventDTO(resultDTO.getEventDTO());
				// session.setAttribute("EVENTID", eventId);
			} else {
				System.out.print("EventID to be passed ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("In Event Details Method");
	}

	public String[] eventsSearchTag() {
		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String[] eventsTag = null;
		HttpSession session = null;
		String userTimeZone = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		userId = (String) session.getAttribute("USERID");
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		eventId = this.getEventId();

		facesContext = FacesContext.getCurrentInstance();
		// session = (HttpSession)
		// facesContext.getExternalContext().getSession(true);
		eventDTO = new EventDTO();
		eventDTO.setUserId(userId);
		eventDTO.setEventId(eventId);
		eventDTO.setUserTimeZone(userTimeZone);
		try {
			eventController = EventFactory.listEvents(eventDTO);
			resultDTO = eventController.listEvents(eventDTO);

			List liveList = resultDTO.getEventDTO().getLiveEventList();
			List pastList = resultDTO.getEventDTO().getPastEventList();
			int totalTags = 0;
			int liveListSize = 0;
			int pastListSize = 0;

			if (liveList != null) {
				liveListSize = liveList.size();
			}
			if (pastList != null) {
				pastListSize = pastList.size();
			}

			totalTags = liveListSize + pastListSize;

			eventsTag = new String[totalTags];
			int j = 0;
			while (j < totalTags) {
				if (liveList != null) {
					Iterator iter = liveList.iterator();
					while (iter.hasNext()) {
						eventDTO = (EventDTO) iter.next();
						eventDTO.setEventTag(eventDTO.getEventTag());
						eventsTag[j] = eventDTO.getEventTag();
						j++;
					}
				}
				if (pastList != null) {
					Iterator iter = pastList.iterator();
					while (iter.hasNext()) {

						eventDTO = (EventDTO) iter.next();
						eventDTO.setEventTag(eventDTO.getEventTag());
						eventsTag[j] = eventDTO.getEventTag();
						j++;
					}
				}
			}
			System.out.print(eventsTag);
			System.out.print("********");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eventsTag;
	}

	public String[] eventSearchTag(EventDTO eventDTO) {
		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		String[] eventsTag = null;
		try {
			facesContext = FacesContext.getCurrentInstance();
			eventController = EventFactory.eventSearchTag(eventDTO);
			resultDTO = eventController.eventSearchTag(eventDTO);
			eventsTag = resultDTO.getEventDTO().getEventsTag();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventsTag;
	}

	public EventDTO eventDetails() {

		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		HttpSession session = null;
		String userTimeZone = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		userId = (String) session.getAttribute("USERID");
		userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		eventId = this.getEventId();

		try {
			if (eventId != null) {
				facesContext = FacesContext.getCurrentInstance();
				// session = (HttpSession)
				// facesContext.getExternalContext().getSession(true);
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setEventId(eventId);
				eventDTO.setUserTimeZone(userTimeZone);
				eventController = EventFactory.eventDetails(eventDTO);
				resultDTO = eventController.eventDetails(eventDTO);
				this.setEventDetailDTO(resultDTO.getEventDTO());
				// this.setEventDTO(resultDTO.getEventDTO());
				// session.setAttribute("EVENTID", eventId);
			} else {
				System.out.print("EventID to be passed ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDTO.getEventDTO();
	}

	public String agendaDetails() {

		eventId = this.getEventId();
		System.out.println("eventId===>" + eventId);
		System.out.println("In agenda Details Method");
		return "agenda";

	}

	public String populateAgendaPage() {

		super.setAppEventId(this.getEventId());

		EventDTO eventDTO = new EventDTO();

		eventDTO = eventDetails();

		eventAttendee();

		getApplicationBeanInstance().setLike("Event");

		populateAgendaSessionPage();

		getApplicationBeanInstance().setEventLikeCount(
				getAttendeeBeanInstance().getEventLike(this.getEventId()));

		getApplicationBeanInstance().setCurrentEvent(
				StringUtility.shrinkName(eventDTO.getEventName()));
		getApplicationBeanInstance().setEventTooptip(eventTooltip(eventDTO));
		setCurrentPage("EVENTHOME");

		String country = eventDTO.getEventCountry();
		String location = eventDTO.getEventLocation();
		String placeName = eventDTO.getEventCity();
		System.out.println("<< >> " + placeName);

		//System.out.println("<< >> " + placeName);
		// String gmapKey =
		// "ABQIAAAAiqblAQNWNoua1VYnGtU1dBRedtL7gCpXlU8FuammbFvwIMVSlhRL4gzsM_7WZ6hbScCw1YuLXHNjwA";
		String gmapKey = SocialMediaKeys.GOOGLE_MAP_KEY;
		URL mapurl = geoMapURL(location, country, placeName);
		MapDTO mapDTO = getGeoLocation(mapurl);
		eventDTO.setMapDTO(mapDTO);
		this.setEventDetailDTO(eventDTO);

		getSocialMediaBeanInstance().setTwitterPollEnabled(true);

		return "agenda";

	}

	public void eventAttendee() {

		EventDTO eventDTO = null;
		ResultDTO resultDTO = null;
		AttendeeDTO attendeeDTO = null;
		String profileId = null;

		EventController eventController = null;
		FacesContext facesContext = null;
		HttpSession session = null;

		try {

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);
			profileId = (String) session.getAttribute("USERPROFILEID");
			eventDTO = new EventDTO();
			attendeeDTO = new AttendeeDTO();
			if (profileId != null) {
				eventDTO.setUserId(profileId);// Cuurent user profile Id
			}
			eventDTO.setEventId(this.eventId);

			eventController = EventFactory.eventAttendees(eventDTO);

			resultDTO = eventController.eventAttendees(eventDTO);

			if (resultDTO != null) {
				attendeeDTO = resultDTO.getAttendeeDTO();
			}

			getAttendeeBeanInstance().populateAttendees(attendeeDTO);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * @method populateAgendaSessionPage - To get all the existing Session of
	 *         the Event
	 * @return String
	 */
	public void populateAgendaSessionPage() {
		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		HttpSession session = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		String userTimeZone = null;
		if (session.getAttribute("USERTIMEZONE") != null) {
			userTimeZone = (String) session.getAttribute("USERTIMEZONE");
		}

		try {
			eventDTO = new EventDTO();
			eventDTO.setEventId(this.eventId);
			eventDTO.setUserTimeZone(userTimeZone);
			eventController = EventFactory.populateAgendaSessionPage(eventDTO);
			resultDTO = eventController.populateAgendaSessionPage(eventDTO);
			populateAgendaList(resultDTO.getAgendaSessionList());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method - to populate agenda list
	 * @param ae
	 */
	public void populateAgendaList(List agendaSessionList) {
		EventDTO eventDTO = null;
		if (agendaSessionList != null) {
			Iterator it = agendaSessionList.iterator();
			while (it.hasNext()) {
				eventDTO = (EventDTO) it.next();
				this.setEventName(eventDTO.getEventName());
				this.setEventLocation(eventDTO.getEventLocation());
				this.setJoinedEvent(eventDTO.isJoinedEvent());
				List sessionList = eventDTO.getSessionList();
				this.setAgendaSessionTabList(sessionList);
				if (sessionList != null) {
					if (!sessionList.isEmpty()) {
						this.setAgendaSessionList((List) sessionList.get(0));
						this.setAgendaSessionList1((List) sessionList.get(1));
					}
				}

			}
		}
	}

	/**
	 * @method - to search event tweets
	 * @param ae
	 */
	public void eventTweets(ActionEvent ae) {
		// String eventTag =eventSearchTag("300003");
		String eventTag = eventSearchTag(this.eventId);
		System.out.println("eventTweets Tag  >>" + eventTag);
		getSocialMediaBeanInstance().searchEventTweets(eventTag);
	}

	/**
	 * @method - to search the tags for events
	 * @param ae
	 */
	public String eventSearchTag(String eventId) {
		FacesContext facesContext = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String firstEventTag = null;
		eventId = this.eventId;
		try {
			if (eventId != null) {
				eventDTO = new EventDTO();
				eventDTO.setEventId(eventId);
			}
			facesContext = FacesContext.getCurrentInstance();
			eventController = EventFactory.eventSearchTag(eventDTO);
			resultDTO = eventController.eventSearchTag(eventDTO);
			firstEventTag = resultDTO.getEventDTO().getEventTag();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return firstEventTag;
	}

	/**
	 * @method - to listen join event
	 * @param ae
	 */
	public void joinEventListener(ActionEvent ae) {

		joinEventNew();
		eventAttendee();
		// getAttendeeBeanInstance().eventAttendees(this.getEventId());
		eventDTO = eventDetails();
	}

	/**
	 * @method - to listen unjoin event
	 * @param ae
	 */
	public void unJoinEventListener(ActionEvent ae) {

		unJoinEvent();

	}

	public String populateEventPageAction() {

		populateAgendaPage();

		return "agenda";
	}

	/**
	 * @method joinEvent-To join an event
	 * @return String
	 */
	public String joinEvent() {

		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String retValue = "failure";
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String joinDate = formatter.format(currentDate.getTime());

		userId = (String) session.getAttribute("USERID");
		eventId = this.getEventId();
		try {
			if (eventId != null && userId != null) {
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setJoinEventDate(joinDate);
				eventDTO.setEventId(eventId);
				eventController = EventFactory.joinEvent(eventDTO);
				resultDTO = eventController.joinEvent(eventDTO);
				if (resultDTO.isResultStatus()) {
					this.joinedEvent = true;
					session.setAttribute("EVENTJOINED", "true");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEventDTO(resultDTO.getEventDTO());

		populateAgendaPage();
		System.out.println("In Event Join Method");
		retValue = "agenda";
		return retValue;
	}

	/**
	 * @method joinEvent-To join an event
	 * @return String
	 */
	public void joinEventNew() {

		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String retValue = "failure";
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String joinDate = formatter.format(currentDate.getTime());

		userId = (String) session.getAttribute("USERID");
		eventId = this.getEventId();
		try {
			if (eventId != null && userId != null) {
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setJoinEventDate(joinDate);
				eventDTO.setEventId(eventId);
				eventController = EventFactory.joinEvent(eventDTO);
				resultDTO = eventController.joinEvent(eventDTO);
				if (resultDTO.isResultStatus()) {
					this.joinedEvent = true;
					session.setAttribute("EVENTJOINED", "true");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setEventDTO(resultDTO.getEventDTO());

		System.out.println("In Event Join Method");
		retValue = "agenda";

	}

	/**
	 * @method unJoinEvent- To unjoin from an event
	 * @return String
	 */
	public String unJoinEvent() {
		String unjoinEventId = null;
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		String retValue = "failure";
		HttpSession session = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);

		userId = (String) session.getAttribute("USERID");
		// eventId= this.getEventId();
		unjoinEventId = this.getUnjoinEventId();
		try {
			if (unjoinEventId != null && userId != null) {
				eventDTO = new EventDTO();
				eventDTO.setUserId(userId);
				eventDTO.setEventId(unjoinEventId);
				eventController = EventFactory.unjoinEvent(eventDTO);
				resultDTO = eventController.unjoinEvent(eventDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("In unJoinEvent Method");
		listEvents();
		this.unjoinEventId = null;
		retValue = "event";
		return retValue;
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
	 * @method populateSessionPage - To get the Session details.
	 * @return String
	 */
	public String populateSessionPage() {
		String retValue = "session";

		setCurrentPage("SESSIONSUMMARY");

		return retValue;

	}

	public void searchEventTweets(String eventName) {

		FacesContext facesContext = null;
		Application application = null;
		ValueBinding binding = null;

		SocialMediaBean socialMediaBean = null;

		facesContext = FacesContext.getCurrentInstance();
		application = facesContext.getApplication();

		binding = application.createValueBinding("#{socialMediaBean}");
		socialMediaBean = (SocialMediaBean) binding.getValue(facesContext);

		socialMediaBean.searchEventTweets(eventName);

	}

	public String searchEventTweets() {
		ResultDTO resultDTO = null;
		HttpSession session = null;
		String retValue = "failure";
		SocialMediaController socialMediaController = null;
		TwitterDTO twitterDTO = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		session.getAttribute("ADMINTWITTERACCESSTOKEN");
		eventName = "SXSW";
		if (eventName != null) {
			twitterDTO = new TwitterDTO();
			twitterDTO.setSearchTweet(eventName);
			if (session.getAttribute("ADMINTWITTERACCESSTOKEN") != null) {
				twitterDTO.setAccessToken(session
						.getAttribute("ADMINTWITTERACCESSTOKEN"));

				try {
					socialMediaController = SocialMediaFactory
							.searchTwitterTweets(twitterDTO);
					resultDTO = socialMediaController
							.searchTwitterTweets(twitterDTO);

					if (resultDTO != null) {
						this.twitterDTO = resultDTO.getTwitterDTO();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		retValue = "agenda";

		return retValue;

	}

	

	public URL geoMapURL(String locationName, String country, String placeName) {
		URL url = null;
		String gmapKey = SocialMediaKeys.GOOGLE_MAP_KEY;
		if (locationName != null) {
			locationName = locationName.replace(" ", "%20");
		} else {
			locationName = "";
		}
		if (country != null) {
			country = country.replace(" ", "%20");
		} else {
			country = "";
		}
		if (placeName != null) {
			placeName = placeName.replace(" ", "%20");
		} else {
			placeName = "";
		}
		try {
			url = new URL("http://maps.google.com/maps/geo?q=" + locationName
					+ "," + placeName + "," + country + "&output=csv&key="
					+ gmapKey);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

	public MapDTO getGeoLocation(URL url) {
		String splitData[] = new String[4];
		MapDTO mapDTO = null;
		String gmapKey = SocialMediaKeys.GOOGLE_MAP_KEY;
		try {

			System.out.println("url >>" + url);
			URLConnection urlConnection = url.openConnection();
			BufferedReader in;
			String inputLine;
			String data = null;

			in = new BufferedReader(new InputStreamReader(urlConnection
					.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				data = inputLine;
			}
			in.close();
			System.out.println("DataToMap >>" + data);
			StringTokenizer st = new StringTokenizer(data, ",");
			int i = 0;
			while (st.hasMoreTokens()) {
				String key = st.nextToken();
				splitData[i] = key;
				i++;

			}

			mapDTO = new MapDTO();
			mapDTO.setAccuracy(splitData[0]);
			mapDTO.setStatus(splitData[1]);
			mapDTO.setLatitude(splitData[2]);
			mapDTO.setLongitude(splitData[3]);
			mapDTO.setGmapKey(gmapKey);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapDTO;
	}

	public List getEvents() {
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		List eventList = new ArrayList();
		try {
			eventController = EventFactory.getEvents(eventDTO);
			resultDTO = eventController.getEvents(eventDTO);
			eventList = resultDTO.getEventList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventList;

	}

	public Map getEventsForSpeaker() {
		EventController eventController = null;
		ResultDTO resultDTO = null;
		EventDTO eventDTO = null;
		List eventList = new ArrayList();
		Map eventMap = null;
		try {
			eventController = EventFactory.getEvents(eventDTO);
			resultDTO = eventController.getEvents(eventDTO);
			eventMap = resultDTO.getEventDTO().getEventMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventMap;

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

	public void eventAttendeeRefreshListener(ActionEvent ae) {
		eventAttendee();
	}

}