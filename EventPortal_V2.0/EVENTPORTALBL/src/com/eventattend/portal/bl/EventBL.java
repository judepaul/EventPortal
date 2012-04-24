package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import com.eventattend.portal.bl.BusinessLayer;
import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.common.util.StringUtility;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.exceptions.EventPortalException;

public class EventBL extends BusinessLayer {

	/**
	 * @method eventDetails- Collect all the event related data
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO eventDetails(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Event event = new Event();
		Collection eventDetailList = null;
		Event eventBO = new Event();
		String userTimeZone = null;
		if (eventDTO != null) {
			if (eventDTO.getEventId() != null) {
				if(eventDTO.getSelectedEvent()!=null){
				  eventBO.setSelectedEvent(eventDTO.getSelectedEvent());
				}
				eventBO.setEventId(eventDTO.getEventId());
				eventBO.setUserId(eventDTO.getUserId());
				userTimeZone = eventDTO.getUserTimeZone();
				try {
					eventDetailList = eventBO.eventDetails(eventBO);
					if (eventDetailList != null) {
						Iterator iter = eventDetailList.iterator();
						while (iter.hasNext()) {
							event = (Event) iter.next();
							eventDTO = convertEventToEvenDTO(event,userTimeZone);
							if(eventBO.getSelectedEvent()!=null){
								String eventTag =eventDTO.getEventTag();
								 String[] temp = eventTag.split("#");
								 for(int i =0; i < temp.length ; i++){								
									    eventDTO.setEventTag(temp[1]);									  
								 }
							
							}
						}

						resultDTO.setEventDTO(eventDTO);
						resultDTO.setResultStatus(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;
	}

	/**
	 * @method listEvents - To get the all the events
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO listEvents(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Collection eventDetailList = null;
		String userTimeZone = null;
		Event eventBO = new Event();
		if (eventDTO != null) {
			if (eventDTO.getUserId() != null) {
				eventBO.setUserId(eventDTO.getUserId());
			}
			if (eventDTO.getEventName() != null) {
				eventBO.setEventName(eventDTO.getEventName());
			}
			if (eventDTO.getUserTimeZone() != null) {
				userTimeZone = eventDTO.getUserTimeZone();
			}
			try {
				eventDetailList = eventBO.listEvents(eventBO);
				// System.out.println("Event Search List ");
				eventDTO = separateEvents(eventDetailList, userTimeZone);
			} catch (Exception e) {
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");

			}

		} else {
			try {
				eventDetailList = eventBO.listEvents(eventBO);
				// System.out.println("Event List ");
				eventDTO = separateEvents(eventDetailList, userTimeZone);

			} catch (Exception e) {
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");

			}
		}

		resultDTO.setEventDTO(eventDTO);

		return resultDTO;
	}

	/**
	 * @method listEvents - To get the all the events
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO events(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Collection eventDetailList = null;
		String userTimeZone = null;
		Event eventBO = new Event();
		Event event =null;
		List eventList = new ArrayList();
		if (eventDTO != null) {
			if (eventDTO.getUserTimeZone() != null) {
				userTimeZone = eventDTO.getUserTimeZone();
			}
			try {
				eventDetailList = eventBO.listEvents(eventBO);
				Iterator iter = eventDetailList.iterator();
				while (iter.hasNext()) {
					eventDTO = new EventDTO();					
					event = (Event) iter.next();
					eventDTO = convertEventToEvenDTO(event,userTimeZone);
					eventList.add(eventDTO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");

			}		
		}
		resultDTO.setEventList((List) eventList);	
		return resultDTO;
	}

	/**
	 * @method separateEvents - To get the past,present & future events from the
	 *         event list
	 * @param eventDetailList
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public EventDTO separateEvents(Collection eventDetailList,
			String userTimeZone) {
		ResultDTO resultDTO = new ResultDTO();
		Event event = new Event();
		DateUtility dateUtility = new DateUtility();
		String eventEndDate = null;
		String startDate = null;
		String comparedResult = null;
		String compareTwoDateResult = null;
		Date utilEndDate = null;
		Date utilStartDate = null;
		List liveEventList = new ArrayList();
		List pastEventList = new ArrayList();
		List futureEventList = new ArrayList();

		EventDTO eventDTO = null;
		EventDTO resultEventDTO = null;

		resultEventDTO = new EventDTO();
		if (eventDetailList != null) {
			Iterator iter = eventDetailList.iterator();
			while (iter.hasNext()) {
				eventDTO = new EventDTO();
				event = (Event) iter.next();
				eventEndDate = dateUtility.convertDateSeperator(event
						.getEventEndDate());
				utilEndDate = dateUtility.convertStringToUtilDate(eventEndDate,
						"yyyy-MM-dd");
				startDate = dateUtility.convertDateSeperator(event
						.getEventStartDate());
				utilStartDate = dateUtility.convertStringToUtilDate(startDate,
						"yyyy-MM-dd");
				/* To check the days in the Events & in DB */
				checkEventDays(event, utilStartDate, utilEndDate);

				comparedResult = dateUtility.compareWithToday(utilStartDate);
				if (comparedResult.equals("EQUAL")) {
					eventDTO = convertEventToEvenDTO(event, userTimeZone);
					liveEventList.add(eventDTO);

				} else if (comparedResult.equals("LESSER")) {
					comparedResult = dateUtility.compareWithToday(utilEndDate);
					if (comparedResult.equals("GREATER")
							|| comparedResult.equals("EQUAL")) {
						eventDTO = convertEventToEvenDTO(event, userTimeZone);
						liveEventList.add(eventDTO);
					} else {
						eventDTO = convertEventToEvenDTO(event, userTimeZone);
						pastEventList.add(eventDTO);
					}
				} else {
					eventDTO = convertEventToEvenDTO(event, userTimeZone);
					futureEventList.add(eventDTO);

				}
			}

			resultEventDTO.setLiveEventList(liveEventList);
			resultEventDTO.setPastEventList(pastEventList);
			resultEventDTO.setFutureEventList(futureEventList);
		}
		return resultEventDTO;

	}
	
	public String retEventType(Event event){
		DateUtility dateUtility = new DateUtility();
		
		String eventEndDate = null;
		Date utilEndDate = null;
		String startDate = null;
		Date utilStartDate = null;
		String comparedResult = null;
		String eventType = "";
		
		if(event != null){
		
		eventEndDate = dateUtility.convertDateSeperator(event
				.getEventEndDate());
		utilEndDate = dateUtility.convertStringToUtilDate(eventEndDate,
				"yyyy-MM-dd");
		startDate = dateUtility.convertDateSeperator(event
				.getEventStartDate());
		utilStartDate = dateUtility.convertStringToUtilDate(startDate,
				"yyyy-MM-dd");
		
		comparedResult = dateUtility.compareWithToday(utilStartDate);
		
		if (comparedResult.equals("EQUAL")) {
			
			eventType = "LIVE";
			
		} else if (comparedResult.equals("LESSER")) {
			comparedResult = dateUtility.compareWithToday(utilEndDate);
			if (comparedResult.equals("GREATER")
					|| comparedResult.equals("EQUAL")) {
				
				eventType = "LIVE";
				
			} else {
				eventType = "PAST";
			}
		} else {
			eventType = "FUTURE";
		}
		
		
		}
		
		return eventType;
	}
	

	/**
	 * @method convertEventToEvenDTO
	 * @param event
	 * @return EventDTO
	 */
	public EventDTO convertEventToEvenDTO(Event event, String userTimeZone) {
		DateUtility dateUtility = new DateUtility();
		String inputFormat = "yyyy-MM-dd hh:mm:ss";
		String outputFormat = "EEE MMM dd hh:mm:ss z yyyy";
		EventDTO eventDTO = new EventDTO();
		if (event != null) {
			if (event.getEventId() != null) {
				eventDTO.setEventId(event.getEventId());
			}
			if (event.getEventName() != null) {
				eventDTO.setEventName(event.getEventName());
			}
			if (event.getEventStartDate() != null) {
				eventDTO.setEventStartDate(String.valueOf(event
						.getEventStartDate()));
			}
			if (event.getEventEndDate() != null) {
				eventDTO.setEventEndDate(String
						.valueOf(event.getEventEndDate()));
			}
			if (event.getEventStartTime() != null) {
				
				eventDTO.setEventStartTime(dateUtility.convertDateToGivenTime(
						event.getEventStartTime(), userTimeZone));
				
				Date eventStartDateWithTime = dateUtility.convertStringToDateFormat(event.getEventStartTime(), inputFormat, outputFormat);
				
				eventDTO.setEventStartDateWithTime(eventStartDateWithTime);
				
			}
			if (event.getEventEndTime() != null) {
				eventDTO.setEventEndTime(dateUtility.convertDateToGivenTime(
						event.getEventEndTime(), userTimeZone));
				
				Date eventEndDateWithTime = dateUtility.convertStringToDateFormat(event.getEventEndTime(), inputFormat, outputFormat);
				
				eventDTO.setEventEndDateWithTime(eventEndDateWithTime);
			}
			if (event.getEventDescription() != null) {
				eventDTO.setEventDescription(event.getEventDescription());
				StringUtility stringUtility = new StringUtility();
				String eventShortDesc = stringUtility.retShortDescription(event
						.getEventDescription());
				eventDTO.setEventShortDesc(eventShortDesc);
			}
			if (event.getEventLocation() != null) {
				// eventDTO.setEventLocation(" - "+event.getEventLocation());
				eventDTO.setEventLocation(event.getEventLocation());
			}
			if (event.getEventCountry() != null) {
				eventDTO.setEventCountry(event.getEventCountry());
			}
			if (event.getEventState() != null) {
				eventDTO.setEventState(event.getEventState());
			}
			if (event.getEventCity() != null) {
				eventDTO.setEventCity(event.getEventCity());
			}
			if (event.getEventNoOfDays() != null) {
				eventDTO.setEventNoOfDays(event.getEventNoOfDays());
			}
			if (event.getEventOrganizedBy() != null) {
				eventDTO.setEventOrganizedBy(event.getEventOrganizedBy());
			}
			if (event.getEventWebsite() != null) {
				eventDTO.setEventWebsite(event.getEventWebsite());
			}
			if (event.getSessionId() != null) {
				eventDTO.setSessionId(event.getSessionId());
			}
			if (event.getSessionName() != null) {
				eventDTO.setSessionName(event.getSessionName());
			}

			if (event.getSessionStartDate() != null) {
				eventDTO.setSessionStartDate(event.getSessionStartDate());
			}
			if (event.getSessionStartTime() != null) {
				eventDTO.setSessionStartTime(dateUtility
						.convertDateToGivenTime(event.getSessionStartTime(),
								userTimeZone));
			}
			if (event.getSessionEndDate() != null) {
				eventDTO.setSessionEndDate(event.getSessionEndDate());
			}
			if (event.getSessionEndTime() != null) {
				eventDTO.setSessionEndTime(dateUtility.convertDateToGivenTime(
						event.getSessionEndTime(), userTimeZone));
			}
			if (event.getSessionKeyNote() != null) {
				eventDTO.setSessionKeyNote(event.getSessionKeyNote());
			}
			if (event.getSessionTag() != null) {
				eventDTO.setSessionTag(event.getSessionTag());
			}
			if (event.getSessionVenueName() != null) {
				eventDTO.setSessionVenueName(event.getSessionVenueName());
			}
			if (event.getEventTag() != null) {
				eventDTO.setEventTag(event.getEventTag());
			}
			if (event.getEventTimeZone()!= null) {
				eventDTO.setEventTimeZone(event.getEventTimeZone());
			}
			eventDTO.setJoinedEvent(event.isJoinedEvent());
			eventDTO.setSessionLikeCount(event.getSessionLikeCount());

		}
		return eventDTO;
	}

	
	public Event convertEventDTOToEvent(EventDTO eventDTO) {	
		DateUtility dateUtility = new DateUtility();
		String eventStartDate = null;
		String eventEndDate = null;
		String inputFormat = "EEE MMM dd hh:mm:ss z yyyy";
		String outputFormat = "yyyy-MM-dd";
		String retTime = null;
		int noOfDays = 0;
		
		Event event = new Event();
		if (eventDTO != null) {
			if (eventDTO.getEventId() != null) {
				event.setEventId(eventDTO.getEventId());
			}
			if (eventDTO.getEventName() != null) {
				event.setEventName(eventDTO.getEventName());
			}
		
			if (eventDTO.getEventDescription() != null) {
				event.setEventDescription(eventDTO.getEventDescription());
				}
			
			if (eventDTO.getEventStartDate() != null) {
				retTime = retTime(eventDTO.getEventStartDate());
				eventStartDate = dateUtility.convertDateFormat(eventDTO.getEventStartDate(), inputFormat, outputFormat);
				event.setEventStartDate(eventStartDate);
				event.setEventStartTime(eventStartDate+" "+retTime);
			}
			
			if (eventDTO.getEventEndDate() != null) {
				retTime = retTime(eventDTO.getEventEndDate());
				eventEndDate = dateUtility.convertDateFormat(eventDTO.getEventEndDate(), inputFormat, outputFormat);
				event.setEventEndDate(eventEndDate);
				event.setEventEndTime(eventEndDate+" "+retTime);
			}
			
			noOfDays = calculateNoOfDays(eventStartDate, eventEndDate);
			
			event.setEventNoOfDays(String.valueOf(noOfDays));
			
			if (eventDTO.getEventLocation() != null) {			
				event.setEventLocation(eventDTO.getEventLocation());
			}
			if (eventDTO.getEventCountry() != null) {
				event.setEventCountry(eventDTO.getEventCountry());
			}
			if (eventDTO.getEventState() != null) {
				event.setEventState(eventDTO.getEventState());
			}
			if (eventDTO.getEventCity() != null) {
				event.setEventCity(eventDTO.getEventCity());
			}
			if (eventDTO.getEventTimeZone() != null) {
				event.setEventTimeZone(eventDTO.getEventTimeZone());
			}
			if (eventDTO.getEventNoOfDays() != null) {
				event.setEventNoOfDays(eventDTO.getEventNoOfDays());
			}
			if (eventDTO.getEventOrganizedBy() != null) {
				event.setEventOrganizedBy(eventDTO.getEventOrganizedBy());
			}
			if (eventDTO.getEventWebsite() != null) {
				event.setEventWebsite(eventDTO.getEventWebsite());
			}
			if (eventDTO.getSessionId() != null) {
				event.setSessionId(eventDTO.getSessionId());
			}
			if (eventDTO.getSessionName() != null) {
				event.setSessionName(eventDTO.getSessionName());
			}

			if (eventDTO.getSessionStartDate() != null) {
				event.setSessionStartDate(eventDTO.getSessionStartDate());
			}
		
			if (eventDTO.getSessionKeyNote() != null) {
				event.setSessionKeyNote(eventDTO.getSessionKeyNote());
			}
			if (eventDTO.getSessionTag() != null) {
				event.setSessionTag(eventDTO.getSessionTag());
			}
			if (eventDTO.getSessionVenueName() != null) {
				event.setSessionVenueName(eventDTO.getSessionVenueName());
			}
			if (eventDTO.getEventTag() != null) {
				event.setEventTag(eventDTO.getEventTag());
			}
			event.setJoinedEvent(eventDTO.isJoinedEvent());
			event.setSessionLikeCount(eventDTO.getSessionLikeCount());

		}
		return event;
	}
	
	public String retTime(String inpDate){
		
		
		String strToken[] = null;
		String retTime = "";
		
		if(inpDate != null){
			
			strToken = inpDate.split(" ");
			retTime = strToken[3];
		}
		
		
		return retTime;
	}
	
	public int retDay(String inpDate){
		
		
		String strToken[] = null;
		int retDay = 0;
		
		if(inpDate != null){
			
			strToken = inpDate.split("-");
			retDay = Integer.parseInt(strToken[2]);
		}
		
		
		return retDay;
	}
	
	public int calculateNoOfDays(String strtDate, String endDate){
		
		int startDay = retDay(strtDate);
		int endDay = retDay(endDate); 
		
		int totalDays = 0;
		
		totalDays = endDay - startDay;
		
		if(totalDays < 0 ){
			totalDays = 0;
		}
		
		
		return totalDays + 1;
	}
	

	/**
	 * @method checkEventDays -To check the Event days between NoOfDays in DB &
	 *         actual event days
	 * @param event
	 * @param startDate
	 * @param endDate
	 */
	@SuppressWarnings("deprecation")
	public void checkEventDays(Event event, Date startDate, Date endDate) {

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.set(startDate.getYear(), startDate.getMonth(), startDate.getDay());
		cal2.set(endDate.getYear(), endDate.getMonth(), endDate.getDay());
		int actualDays = daysBetween(cal1.getTime(), cal2.getTime());
		int noOfDays = 0;
		if(event.getEventNoOfDays() != null){
			noOfDays = Integer.parseInt(event.getEventNoOfDays());
		}
		if (noOfDays != actualDays) {
			System.out.println("Days MISMATCH for following events");
			System.out.println(event.getEventName()
					+ "  || No of Days in DB =>" + event.getEventNoOfDays()
					+ " ||Event Days -Start & End Date => " + actualDays);
		} else {
			System.out.println("CORRECT Days for following events");
			System.out.println(event.getEventName()
					+ "  || No of Days in DB =>" + event.getEventNoOfDays()
					+ " ||Event Days -Start & End Date => " + actualDays);

		}

	}

	

	/**
	 * @method convertProfileToProfileDTO -To convert Profile business objects
	 *         into Profile DTO objects
	 * @param profile
	 * @return ProfileDTO
	 */
	public ProfileDTO convertProfileToProfileDTO(Profile profile) {
		ProfileDTO profileDTO = new ProfileDTO();
		if (profile.getProfileId() != null) {
			profileDTO.setProfileId(profile.getProfileId());
		}
		if (profile.getFirstName() != null) {
			profileDTO.setFirstName(profile.getFirstName());
		}
		if (profile.getLastName() != null) {
			profileDTO.setLastName(profile.getLastName());
		}
		if (profile.getGender() != null) {
			profileDTO.setGender(profile.getGender());
		}
		if (profile.getProfileEmail() != null) {
			profileDTO.setProfileEmail(profile.getProfileEmail());
		}
		if (profile.getProfilePicture() != null) {
			profileDTO.setProfilePicture(profile.getProfilePicture());
		}
		if (profile.getAlternativeEmail() != null) {
			profileDTO.setAlternativeEmail(profile.getAlternativeEmail());
		}
		if (profile.getMobile() != null) {
			profileDTO.setMobile(profile.getMobile());
		}
		if (profile.getLandHome() != null) {
			profileDTO.setLandHome(profile.getLandHome());
		}
		if (profile.getLandOffice() != null) {
			profileDTO.setLandOffice(profile.getLandOffice());
		}
		if (profile.getAddress() != null) {
			profileDTO.setAddress(profile.getAddress());
		}
		if (profile.getCity() != null) {
			profileDTO.setCity(profile.getCity());
		}
		if (profile.getCountry() != null) {
			profileDTO.setCountry(profile.getCountry());
		}
		if (profile.getZip() != null) {
			profileDTO.setZip(profile.getZip());
		}
		if (profile.getState() != null) {
			profileDTO.setState(profile.getState());
		}
		if (profile.getEducation() != null) {
			profileDTO.setEducation(profile.getEducation());
		}
		if (profile.getOccupation() != null) {
			profileDTO.setOccupation(profile.getOccupation());
		}
		if (profile.getWebsite() != null) {
			profileDTO.setWebsite(profile.getWebsite());
		}
		if (profile.isSpeaker()) {
			profileDTO.setSpeakerCheck(profile.isSpeaker());
		}
		if (profile.getSpeakerFor() != null) {
			profileDTO.setSpeakerFor(profile.getSpeakerFor());
		}
		if (profile.getSpeakerKeyNotes() != null) {
			profileDTO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
		}
		if (profile.getTwitterId() != null) {
			profileDTO.setTwitterId(profile.getTwitterId());
		}
		if (profile.getTwitterProfileUrl() != null) {
			profileDTO.setTwitterProfileUrl(profile.getTwitterProfileUrl());
		}
		if (profile.getTwitterImgUrl() != null) {
			profileDTO.setTwitterImgUrl(profile.getTwitterImgUrl());
		}
		profileDTO.setTwitterFollow(profile.getTwitterCheck());

		if (profile.getFaceBookId() != null) {
			profileDTO.setFaceBookId(profile.getFaceBookId());
		}
		if (profile.getFaceBookProfileUrl() != null) {
			profileDTO.setFaceBookProfileUrl(profile.getFaceBookProfileUrl());
		}
		if (profile.getFaceBookImgUrl() != null) {
			profileDTO.setFaceBookImgUrl(profile.getFaceBookImgUrl());
		}
		profileDTO.setFaceBookFriendsConnect(profile
				.getFbAllowFriendsToConnect());
		if (profile.getLinkedInId() != null) {
			profileDTO.setLinkedInId(profile.getLinkedInId());
		}
		if (profile.getLinkedInProfileUrl() != null) {
			profileDTO.setLinkedInProfileUrl(profile.getLinkedInProfileUrl());
		}
		if (profile.getLinkedInImgUrl() != null) {
			profileDTO.setLinkedInImgUrl(profile.getLinkedInImgUrl());
		}
		profileDTO.setLinkedInFriendsConnect(profile
				.getLiAllowFriendsToFollow());
		if (profile.getProfilePicture() != null
				&& profile.getProfilePicture().trim().startsWith("http")) {
			profileDTO.setProfImgFileName(profile.getProfilePicture());
		} else {
			if(profile.getImgLocation()==null || profile.getImgLocation().equals("/images/null")){
				profileDTO.setProfImgFileName("/images/noPhoto.jpg");
					
			}else{
			profileDTO.setProfImgFileName("/images/"
					+ profile.getProfilePicture());
			}
		}
		profileDTO.setLinkedinCheck(profile.isLinkedinCheck());
		profileDTO.setTwitterCheck(profile.getTwitterCheck());
		profileDTO.setFacebookCheck(profile.getFacebookCheck());
		return profileDTO;

	}

	/**
	 * @method - To find the days between two dates
	 * @param d1
	 * @param d2
	 * @return int
	 */
	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * @method populateAgendaSessionPage - To get the all the Event Session list
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO populateAgendaSessionPage(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		List sessionList = null;
		List sessList = null;
		Event event = new Event();
		try {
			sessionList = agendaSessionList(eventDTO);

		} catch (Exception e) {
			e.printStackTrace();

		}
		resultDTO.setAgendaSessionList(sessionList);
		return resultDTO;
	}

	/**
	 * @ agendaList() - Method to get agenda list
	 * 
	 * @param null
	 * @return List
	 */
	public List agendaSessionList(EventDTO eventDTO) {
		Event event = null;
		EventDTO eventSessionDTO = null;
		List sesionList = null;
		Collection agendaSessionColl = null;
		List regSesionList = null;
		List sessionListForAgenda = null;
		sessionListForAgenda = new ArrayList();
		event = new Event();
		String userTimeZone = null;
		try {

			event.setEventId(eventDTO.getEventId());
			userTimeZone = eventDTO.getUserTimeZone();
			agendaSessionColl = event.agendaSessionList(event);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!agendaSessionColl.isEmpty()) {
			Iterator it = agendaSessionColl.iterator();
			while (it.hasNext()) {
				Event eventList = (Event) it.next();
				eventSessionDTO = new EventDTO();
				eventSessionDTO.setEventDescription(eventList
						.getEventDescription());
				eventSessionDTO.setEventEndDate(eventList.getEventEndDate());
				eventSessionDTO
						.setEventStartDate(eventList.getEventStartDate());
				eventSessionDTO.setEventLocation(eventList.getEventLocation());
				eventSessionDTO.setEventCountry(eventList.getEventCountry());
				eventSessionDTO.setEventCity(eventList.getEventCity());
				eventSessionDTO.setEventState(eventList.getEventState());
				eventSessionDTO.setEventName(eventList.getEventName());
				eventSessionDTO.setEventOrganizedBy(eventList
						.getEventOrganizedBy());
				eventSessionDTO.setEventId(eventList.getEventId());
				eventSessionDTO.setEventWebsite(eventList.getEventWebsite());
				eventSessionDTO.setJoinedEvent(eventList.isJoinedEvent());
				// sesionList = eventList.getSessionList();
				sesionList = sessionBOToDTO(eventList.getSessionList(),
						userTimeZone);
				eventSessionDTO.setSessionList(sesionList);
			}
		}
		sessionListForAgenda.add(eventSessionDTO);
		return sessionListForAgenda;
	}

	public List sessionBOToDTO(List sessList, String userTimeZone) {
		List sessionList = null;
		sessionList = new ArrayList();
		EventDTO eventSessionDTO = null;

		if (sessList != null) {
			for (int i = 0; i < sessList.size(); i++) {
				List sassList = sessionSingleList((ArrayList) sessList.get(i),
						userTimeZone);
				sessionList.add(sassList);
			}
		}
		return sessionList;
	}

	public List sessionSingleList(List sessList, String userTimeZone) {
		List sessionList = null;
		sessionList = new ArrayList();
		EventDTO eventSessionDTO = null;
		DateUtility dateutility = new DateUtility();
		if (sessList != null) {
			List sassList = (ArrayList) sessList;
			Iterator it = sassList.iterator();
			while (it.hasNext()) {
				Event eventSessionList = (Event) it.next();
				eventSessionDTO = new EventDTO();
				eventSessionDTO.setSessionId(eventSessionList.getSessionId());
				eventSessionDTO.setSessionEndDate(eventSessionList
						.getSessionEndDate());
				eventSessionDTO.setSessionStartDate(eventSessionList
						.getSessionStartDate());
				eventSessionDTO.setSessionStartTime(dateutility
						.convertDateToGivenTime(eventSessionList
								.getSessionStartTime(), userTimeZone));
				eventSessionDTO.setSessionEndTime(dateutility
						.convertDateToGivenTime(eventSessionList
								.getSessionEndTime(), userTimeZone));
				eventSessionDTO.setLiveSession(checkLiveSession(
						eventSessionList.getSessionId(), eventSessionList
								.getSessionStartDate(), eventSessionList
								.getSessionEndDate(), eventSessionList
								.getSessionStartTime(), eventSessionList
								.getSessionEndTime(), userTimeZone));
				// System.out.println("List >>"+eventSessionDTO.getSessionStartTime()+
				// " - " + eventSessionDTO.getSessionEndTime());
				eventSessionDTO.setSessionTime(eventSessionDTO
						.getSessionStartTime()
						+ " - " + eventSessionDTO.getSessionEndTime());
				eventSessionDTO.setSessionEventId(eventSessionList
						.getSessionEventId());
				eventSessionDTO.setSessionKeyNote(eventSessionList
						.getSessionKeyNote());
				eventSessionDTO.setSessionName(eventSessionList
						.getSessionName());
				eventSessionDTO.setSessionVenueId(eventSessionList
						.getSessionVenueId());
				eventSessionDTO.setSessionVenueName(eventSessionList
						.getSessionVenueName());
				sessionList.add(eventSessionDTO);
			}
		}
		return sessionList;
	}

	public boolean checkLiveSession(String sessionId, String sessionStartDate,
			String sessionEndDate, String sessionStartTime,
			String sessionEndTime, String userTimeZone) {
		DateUtility dateUtility = new DateUtility();
		boolean liveStatus = false;
		String eventEndDate = null;
		String startDate = null;
		String comparedResult = null;
		String compareTwoDateResult = null;
		Date utilEndDate = null;
		Date utilStartDate = null;

		eventEndDate = dateUtility.convertDateSeperator(sessionEndDate);
		utilEndDate = dateUtility.convertStringToUtilDate(eventEndDate,
				"yyyy-MM-dd");
		startDate = dateUtility.convertDateSeperator(sessionStartDate);
		utilStartDate = dateUtility.convertStringToUtilDate(startDate,
				"yyyy-MM-dd");
		dateUtility = new DateUtility();
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = dateFormat.format(cal.getTime());
		// get current date time with Calendar()
		// String currTime
		// =dateUtility.getTodaysDateInUserTimeZone(userTimeZone) ;
		// System.out.println(currTime);

		// sessionStartTime =
		// dateUtility.convertDateToGivenTimeZone(sessionStartTime,
		// userTimeZone);
		// sessionEndTime =
		// dateUtility.convertDateToGivenTimeZone(sessionEndTime,userTimeZone);
		List timeList = parseTime(currTime);
		List starttimeList = parseTime(sessionStartTime);
		List endtimeList = parseTime(sessionEndTime);
		int startHH = Integer.parseInt(String.valueOf(starttimeList.get(0)));
		int currentHH = Integer.parseInt(String.valueOf(timeList.get(0)));
		int endHH = Integer.parseInt(String.valueOf(endtimeList.get(0)));
		int startMM = Integer.parseInt(String.valueOf(starttimeList.get(1)));
		int currentMM = Integer.parseInt(String.valueOf(timeList.get(1)));
		int endMM = Integer.parseInt(String.valueOf(endtimeList.get(1)));
		int startSS = Integer.parseInt(String.valueOf(starttimeList.get(2)));
		int currentSS = Integer.parseInt(String.valueOf(timeList.get(2)));
		int endSS = Integer.parseInt(String.valueOf(endtimeList.get(2)));
		comparedResult = dateUtility.compareWithToday(utilStartDate);
		// System.out.println("*** >: "+
		// currentHH+":"+currentMM+" >> "+startHH+":"+startMM
		// +" || "+endHH+":"+endMM);

		if (comparedResult.equals("EQUAL")) {
			if (startHH == currentHH && endHH > currentHH) {
				// System.out.println(currTime);
				// System.out.println("*Live >: "+
				// currentHH+":"+currentMM+" >> "+startHH+":"+startMM
				// +" || "+endHH+":"+endMM);

				if (currentMM == 0 && startMM >= currentMM) {
					// System.out.println("*ELive1 >: "+currentHH+":"+currentMM+" >> "+startHH+":"+startMM
					// +" || "+endHH+":"+endMM);
					liveStatus = true;
				}
				if ((startMM <= currentMM) && (endMM == 0 || endMM > currentMM)) {
					// System.out.println("*ELive2 >: "+currentHH+":"+currentMM+" >> "+startHH+":"+startMM
					// +" || "+endHH+":"+endMM);
					liveStatus = true;
				}

			} else if (startHH < currentHH && endHH >= currentHH) {
				// System.out.println("**Live >: "+
				// currentHH+":"+currentMM+" >> "+startHH+":"+startMM
				// +" || "+endHH+":"+endMM);
				if (endMM == 0 && currentMM >= endMM && currentHH < endHH) {
					// System.out.println("**ELive1 >: "+
					// currentHH+":"+currentMM+" >> "+startHH+":"+startMM
					// +" || "+endHH+":"+endMM);
					liveStatus = true;
				}
				if ((startMM != 0 || startMM <= currentMM)) {
					if (endMM >= currentMM) {
						// System.out.println("**ELive2 >: "+
						// currentHH+":"+currentMM+" >> "+startHH+":"+startMM
						// +" || "+endHH+":"+endMM);
						liveStatus = true;
					}
				}
			} else if (startHH > currentHH && endHH > currentHH) {
				liveStatus = false;
			}
		}
		return liveStatus;
	}

	public List parseTime(String dateTime) {
		List parseList = new ArrayList();
		StringTokenizer st = new StringTokenizer(dateTime, " ");
		String val = null;
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			val = st.nextToken();
		}
		StringTokenizer timeTokenizer = new StringTokenizer(val, ":");
		while (timeTokenizer.hasMoreTokens()) {

			long hh = Long.parseLong(timeTokenizer.nextToken());
			long mm = Long.parseLong(timeTokenizer.nextToken());
			long ss = Long.parseLong(timeTokenizer.nextToken());
			parseList.add(hh);
			parseList.add(mm);
			parseList.add(ss);
			// System.out.println(hh + "\\t" + mm+" "+ss);
		}
		return parseList;
	}

	/**
	 * @ method getDateFormate - To get Date format
	 * 
	 * @param String
	 *            stringTime
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public String getDateFormate(String stringTime) {
		String dateString = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.util.Date d1 = df.parse(stringTime);
			DateFormat df2 = DateFormat.getTimeInstance(DateFormat.SHORT);
			Timestamp timestamp = new Timestamp(d1.getTime());
			// System.out.println("date type(HH:MM): " +df2.format(timestamp));
			dateString = df2.format(timestamp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateString;
	}

	/**
	 * @method populateSessionInfoPage - To get the session page information
	 *         list
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO populateSessionInfoPage(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		List sessionSpeakerInfoList = null;
		List sessList = null;
		Event event = new Event();
		try {
			sessionSpeakerInfoList = sessionSummaryInfo(eventDTO);

		} catch (Exception e) {
			e.printStackTrace();

		}
		resultDTO.setAgendaSessionList(sessionSpeakerInfoList);
		return resultDTO;
	}

	/**
	 * @method sessionSpeakerInfo - To get the session information list
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public List sessionSummaryInfo(EventDTO eventDTO) {
		List sessionList = null;
		List sessionSpeakerInfo = null;
		List sessList = null;
		SessionDTO sessionDTO = null;
		ProfileDTO profileDTO = null;
		Event event = new Event();
		sessionList = new ArrayList();
		DateUtility dateutility = new DateUtility();
		String userTimeZone = null;
		Profile profile = null;
		SocialMediaDTO socialMediaDTO = null;
		SocialMediaBL socialMediaBL = null;
		try {
			socialMediaBL = new SocialMediaBL();
			event.setSessionId(eventDTO.getSessionId());
			sessionSpeakerInfo = (List) event.sessionSummary(event);
			if (eventDTO.getUserTimeZone() != null) {
				userTimeZone = eventDTO.getUserTimeZone();
			}
			if (sessionSpeakerInfo.size() == 2) {
				event = (Event) sessionSpeakerInfo.get(0);
				profile = (Profile) sessionSpeakerInfo.get(1);
			}
			if (sessionSpeakerInfo.size() == 1) {
				event = (Event) sessionSpeakerInfo.get(0);
			}

			if (event != null) {
				eventDTO = convertEventToEvenDTO(event, userTimeZone);
				sessionList.add(eventDTO);
			}
			if (profile != null && (!profile.equals("null"))) {
				ProfileBL profileBL = null; 
				profileBL = new ProfileBL();
				profileDTO = new ProfileDTO();
				profileDTO = profileBL.convertProfileToProfileDTO(profile,profileDTO);
				socialMediaDTO = socialMediaBL.getProfileSocialMediaData(profile.getProfileId());
				profileDTO.setSocialMediaDTO(socialMediaDTO);				
				sessionList.add(profileDTO);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionList;
	}

	/**
	 * @method joinEvent- To join an user in an event
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO joinEvent(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		boolean resultStatus = false;
		Event event = new Event();
		Event eventBO = new Event();
		if (eventDTO != null) {
			if (eventDTO.getEventId() != null && eventDTO.getUserId() != null
					&& eventDTO.getJoinEventDate() != null) {
				eventBO.setUserId(eventDTO.getUserId());
				eventBO.setEventId(eventDTO.getEventId());
				eventBO.setJoinEventDate(eventDTO.getJoinEventDate());
				try {
					resultStatus = eventBO.joinEvent(eventBO);
					resultDTO.setResultStatus(resultStatus);

				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;
	}

	/**
	 * @method unjoinEvent- To unjoin an user from an event
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO unjoinEvent(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		boolean resultStatus = false;
		Event event = new Event();
		Event eventBO = new Event();
		if (eventDTO != null) {
			if (eventDTO.getEventId() != null && eventDTO.getUserId() != null) {
				eventBO.setUserId(eventDTO.getUserId());
				eventBO.setEventId(eventDTO.getEventId());
				try {
					resultStatus = eventBO.unjoinEvent(eventBO);
					resultDTO.setResultStatus(resultStatus);

				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;
	}

	/**
	 * @method attendSession- To attend a session
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO attendSession(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		boolean resultStatus = false;
		Event event = new Event();
		Event eventBO = new Event();
		if (eventDTO != null) {
			if (eventDTO.getSessionId() != null && eventDTO.getUserId() != null &&  eventDTO.getEventId()!=null ) {
				eventBO.setUserId(eventDTO.getUserId());
				eventBO.setSessionId(eventDTO.getSessionId());
				eventBO.setEventId(eventDTO.getEventId());
				try {
					resultStatus = eventBO.attendSession(eventBO);
					resultDTO.setResultStatus(resultStatus);

				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;
	}

	/**
	 * @method attendSession- To leave from a session
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO leaveSession(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		boolean resultStatus = false;
		Event event = new Event();
		Event eventBO = new Event();
		if (eventDTO != null) {
			if (eventDTO.getSessionId() != null && eventDTO.getUserId() != null) {
				eventBO.setUserId(eventDTO.getUserId());
				eventBO.setSessionId(eventDTO.getSessionId());
				try {
					resultStatus = eventBO.leaveSession(eventBO);
					resultDTO.setResultStatus(resultStatus);

				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;
	}

	
	/**
	 * @method eventSearchTag-To get the tag for event
	 * @param eventDTO
	 * @return ResultDTO
	 */
	public ResultDTO eventSearchTag(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Event event = null;
		Event eventBO = new Event();
		Collection eventSearchTagList = new ArrayList();
		try {
			if (eventDTO != null) {
				if (eventDTO.getEventId() != null) {
					eventBO.setEventId(eventDTO.getEventId());
					eventSearchTagList = eventBO.eventSearchTag(eventBO);
					if (eventSearchTagList != null) {
						Iterator iter = eventSearchTagList.iterator();
						while (iter.hasNext()) {
							event = (Event) iter.next();
							eventDTO = new EventDTO();
							eventDTO.setEventTag(event.getEventTag());
						}
					}
				}
			} else {

				eventSearchTagList = eventBO.eventSearchTag(eventBO);
				if (eventSearchTagList != null) {
					Iterator iter = eventSearchTagList.iterator();
					while (iter.hasNext()) {
						event = (Event) iter.next();
						eventDTO = new EventDTO();
						eventDTO.setEventsTag(event.getEventsTag());
					}
				}
			}
		} catch (EventPortalException e) {
			e.printStackTrace();
		}
		resultDTO.setEventDTO(eventDTO);
		return resultDTO;
	}

	/**
	 * @method updateSessionCommentLikeCount - To update session comment like
	 *         count
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO updateSessionCommentLikeCount(AttendeeDTO attendeeDTO) {
		List sessionCommentList = null;
		ResultDTO resultDTO = null;
		Collection sessionCommentsLikeCountList = null;
		Event event = new Event();
		sessionCommentList = new ArrayList();
		resultDTO = new ResultDTO();
		int sessCmtLikeCount = 0;
		try {
			event.setSessionCommentId(attendeeDTO.getSessionCommentId());
			event
					.setSessionCommentSessId(attendeeDTO
							.getSessionCommentSessId());
			event.setSessionLikeCount(attendeeDTO.getSessionLikeCount() + 1);

			sessionCommentsLikeCountList = event
					.updateSessionCommentLikeCount(event);
			if (!sessionCommentsLikeCountList.isEmpty()) {
				Iterator it = sessionCommentsLikeCountList.iterator();
				while (it.hasNext()) {
					Event sessionComentsLikeCount = (Event) it.next();
					sessCmtLikeCount = sessionComentsLikeCount
							.getSessionCommentLikeCount();
					attendeeDTO.setSessionLikeCount(sessCmtLikeCount);
					resultDTO.setAttendeeDTO(attendeeDTO);
					resultDTO.setResultStatus(sessionComentsLikeCount
							.getResult());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}

	/**
	 * @method updateSessionCommentLikeCount - To update session comment like
	 *         count
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO updateSessionLikeCount(AttendeeDTO attendeeDTO) {
		List sessionList = null;
		ResultDTO resultDTO = null;
		Collection sessionLikeCountList = null;
		Event event = new Event();
		sessionList = new ArrayList();
		resultDTO = new ResultDTO();
		int sessLikeCount = 0;
		try {
			event
					.setSessionCommentSessId(attendeeDTO
							.getSessionCommentSessId());
			// event.setSessionLikeCount(attendeeDTO.getSessionLikeCount()+1);

			sessionLikeCountList = event.updateSessionLikeCount(event);
			if (!sessionLikeCountList.isEmpty()) {
				Iterator it = sessionLikeCountList.iterator();
				while (it.hasNext()) {
					Event sessionLikeCount = (Event) it.next();
					sessLikeCount = sessionLikeCount.getSessionLikeCountSum();
					attendeeDTO.setSessionLikeCount(sessLikeCount);
					resultDTO.setAttendeeDTO(attendeeDTO);
					resultDTO.setResultStatus(sessionLikeCount.getResult());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}

	/**
	 * @method updateEventLikeCount - To update individual event comment like
	 *         count
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO updateEventLikeCount(AttendeeDTO attendeeDTO) {
		List eventList = null;
		ResultDTO resultDTO = null;
		Collection eventLikeCountList = null;
		Event event = new Event();
		eventList = new ArrayList();
		resultDTO = new ResultDTO();
		int eventLikeCount = 0;
		try {
			event.setEventId(attendeeDTO.getEventId());

			eventLikeCountList = event.updateEventLikeCount(event);
			if(eventLikeCountList != null){
			if (!eventLikeCountList.isEmpty()) {
				Iterator it = eventLikeCountList.iterator();
				while (it.hasNext()) {
					Event eventLike = (Event) it.next();
					eventLikeCount = eventLike.getEventLikeCount();
					attendeeDTO.setEventLikeCount(eventLikeCount);
					resultDTO.setAttendeeDTO(attendeeDTO);
					resultDTO.setResultStatus(eventLike.getResult());
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}

	/**
	 * @method getEventLike - To get event comment like count
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO getEventLike(AttendeeDTO attendeeDTO) {
		Collection eventLikeCountList = null;
		ResultDTO resultDTO = null;
		Event event = new Event();
		resultDTO = new ResultDTO();
		int eventLikeCount = 0;
		try {
			event.setEventId(attendeeDTO.getEventId());

			eventLikeCountList = event.getEventLike(event);
			if (!eventLikeCountList.isEmpty()) {
				Iterator it = eventLikeCountList.iterator();
				while (it.hasNext()) {
					Event eventLike = (Event) it.next();
					eventLikeCount = eventLike.getEventLikeCount();
					attendeeDTO.setEventLikeCount(eventLikeCount);
					resultDTO.setAttendeeDTO(attendeeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}

	/**
	 * @method getEventLike - To get event comment like count
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO getTotalEventLike(AttendeeDTO attendeeDTO) {
		Collection eventLikeCountList = null;
		ResultDTO resultDTO = null;
		Event event = new Event();
		resultDTO = new ResultDTO();
		int eventLikeCountSum = 0;
		try {
			eventLikeCountList = event.getTotalEventLike(event);
			if (!eventLikeCountList.isEmpty()) {
				Iterator it = eventLikeCountList.iterator();
				while (it.hasNext()) {
					Event eventLike = (Event) it.next();
					eventLikeCountSum = eventLike.getEventLikeCountSum();
					attendeeDTO.setEventLikeCountSum(eventLikeCountSum);
					resultDTO.setAttendeeDTO(attendeeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}

	
	public ResultDTO sessionSpeakerInformation(EventDTO eventDTO) {
		List sessionSpeakerInfo = null;
		List sessList = null;
		ProfileDTO profileDTO = null;
		Profile profile = null;
		Event event = new Event();
		ResultDTO resultDTO = new ResultDTO();

		try {
			event.setSessionId(eventDTO.getSessionId());
			sessionSpeakerInfo = (List) event.sessionSummary(event);
			if (sessionSpeakerInfo.size() == 1) {
				profile = (Profile) sessionSpeakerInfo.get(0);
			}else{
				profile = (Profile) sessionSpeakerInfo.get(1);
			}
			if (profile != null) {
				profileDTO = convertProfileToProfileDTO(profile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;
	}


	/**
	 * @method getEvents - To get the all the events
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO getEvents(EventDTO eventDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Collection eventDetail = null;
		List eventDetailList = null;
		eventDetailList = new ArrayList();
		String userTimeZone = null;
		Event eventBO = new Event();
		String eventName = null;
		String eventId = null;
		String eventType = null;
		//Map eventMap = new HashMap();
		Map eventMap = new LinkedHashMap();
		try {
			eventDetail = eventBO.getEvents(eventBO);
			if (eventDetail != null) {
				Iterator iter = eventDetail.iterator();
				eventMap.put("Select an Event", "Select");
				while (iter.hasNext()) {
					eventBO = (Event) iter.next();
					eventDTO = new EventDTO();
					// eventDTO.setEventId(eventBO.getEventId());
					// eventDTO.setEventName(eventBO.getEventName());
					eventName = eventBO.getEventName();
					eventId = eventBO.getEventId();
					eventDetailList.add(eventId);
					
					eventType = retEventType(eventBO);
					
					if(!eventType.equals("PAST")){
						eventMap.put(eventName, eventId);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		eventDTO = new EventDTO();
		eventDTO.setEventMap(eventMap);
		resultDTO.setEventDTO(eventDTO);

		resultDTO.setEventList(eventDetailList);
		return resultDTO;
	}

	public ResultDTO getProfileDetailsById(String toProfileId) {
		  ResultDTO resultDTO = new ResultDTO();
		  Collection profileInfoList = new ArrayList();
		  Event event = new Event();
		  Profile profile = new Profile();
		  ProfileDTO profileDTO = new ProfileDTO();
		  try {
		   if (toProfileId != null) {
		    profile.setToProfileId(toProfileId);
		    event.setProfile(profile);
		    profileInfoList = event.getProfileDetailsById(event);    if (profileInfoList != null) {
		     Iterator iter = profileInfoList.iterator();
		     while (iter.hasNext()) {
		      profile = (Profile) iter.next();
		      profileDTO = new ProfileDTO();
		      profileDTO = convertProfileToProfileDTO(profile);
		     }
		    }
		   }
		  } catch (EventPortalException e) {
		   e.printStackTrace();
		  }
		  resultDTO.setProfileDTO(profileDTO);  return resultDTO;
}
	
	
	/**
	 * @method eventAttendees-To get all attendees of the event
	 * @param eventDTO
	 * @return ResultDTO
	 * @throws EventPortalException
	 */

	public ResultDTO eventAttendee(EventDTO eventDTO){
		
		ResultDTO resultDTO = null;
		
		AttendeeDTO attendeeDTO = null;

		List eventUserList = null;
		
		List sessionList = null;
		Map speakerMap = null;
		String eventId = null;
		Event event = null;
		List profileIdList = null;
		List profileDataList = null;
		List attendeeList = null;
		
		SessionBL sessionBL = null;
		UserBL userBL = null;
		ProfileBL profileBL = null;
		
		try {
			
			resultDTO = new ResultDTO();
			sessionBL = new SessionBL();
			userBL = new UserBL();
			profileBL = new ProfileBL();
			
			eventId = eventDTO.getEventId();
			event = new Event();
			event.setEventId(eventId);
			
			eventUserList = (ArrayList) event.eventUserList(event);
			
			sessionList = (ArrayList)sessionBL.getSessionListForEvent(eventId);

			speakerMap = sessionBL.speakerIdMap(sessionList);
			
			profileIdList = (ArrayList) userBL.profileIdListFromUserIdList(eventUserList);
			
			profileDataList = (ArrayList)profileBL.profileDataList(profileIdList);
			
			attendeeList = (List)profileBL.attendeeProfile(
					(List) profileDataList,speakerMap,eventDTO.getUserId());
		
			attendeeDTO = new AttendeeDTO();
			
			if(attendeeList != null){
				
				attendeeDTO.setAttendeeCount(String.valueOf(attendeeList.size()));
				attendeeDTO.setAttendeeList(attendeeList);
			}
			
			
			resultDTO.setAttendeeDTO(attendeeDTO);
			
		} catch (EventPortalException e) {			
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}
	public ResultDTO addEvent(EventDTO eventDTO){
		ResultDTO resultDTO = new ResultDTO();
		Event event = convertEventDTOToEvent(eventDTO);
		try {
			boolean result = event.addEvent(event);
			resultDTO.setResultStatus(result);
		} catch (EventPortalException e) {
			e.printStackTrace();
		}
		
		return resultDTO;
	}
	
	public ResultDTO deleteEvent(EventDTO eventDTO){
		ResultDTO resultDTO = new ResultDTO();
		Event event = convertEventDTOToEvent(eventDTO);
		try {
			boolean result = event.deleteEvent(event);
			resultDTO.setResultStatus(result);
		} catch (EventPortalException e) {
			e.printStackTrace();
		}
		
		return resultDTO;
	}
	
	public int  noOfDayss(Date startDate, Date endDate) {
		
	  Calendar cal1 = new GregorianCalendar();
	  Calendar cal2 = new GregorianCalendar();
	  cal1.set(startDate.getYear(), startDate.getMonth(), startDate.getDay());
	  cal2.set(endDate.getYear(), endDate.getMonth(), endDate.getDay());
	  int actualDays = daysBetween(cal1.getTime(), cal2.getTime());
	  return actualDays;
	 }

}
