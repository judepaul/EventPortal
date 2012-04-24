package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Predicate;
import com.eventattend.portal.DAO.Blob.BlobImageRetrival;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.Session;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.model.db4o.EventPOJO;
import com.eventattend.portal.model.db4o.KeysPOJO;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.SMProfilePOJO;
import com.eventattend.portal.model.db4o.SessionCommentPOJO;
import com.eventattend.portal.model.db4o.SessionPOJO;
import com.eventattend.portal.model.db4o.SessionSpeakerMapPOJO;
import com.eventattend.portal.model.db4o.UserEventMapPOJO;
import com.eventattend.portal.model.db4o.UserPOJO;
import com.eventattend.portal.model.db4o.UserSessionMapPOJO;
import com.eventattend.portal.model.db4o.VenuePOJO;
import com.eventattend.portal.model.db4o.query.SessionDB4O;

public class EventDAO extends DataAccessObject {

	static ObjectContainer db = null;

	/**
	 * @ method read - To call function based on hidden action.
	 * 
	 * @param object
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection read(BusinessObject object) throws EventPortalException {
		Event event = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		event = (Event) object;
		hiddenAction = event.getHiddenAction();
		if (hiddenAction != null) {
			
			try{
				db = getDbConnection();
			if (hiddenAction.equals("EVENTINFO")) {
				resultCollection = eventInfo(db,event);
			} else if (hiddenAction.equals("EVENTS")) {
				resultCollection = events(db,event);
			} else if (hiddenAction.equals("EVENTATTENDEE")) {
				resultCollection = eventAttendee(db, event);
			} else if (hiddenAction.equals("EVENTUSERLIST")) {
				resultCollection = eventUsers(db, event);				
			} else if (hiddenAction.equals("eventSearchTag")) {
				resultCollection = eventSearchTag(db,event);
			}else if (hiddenAction.equals("getEvents")) {
				resultCollection = getEvents(db,event);
			} else if (hiddenAction.equals("getEventLike")) {
				resultCollection = getEventLike(db,event);
			} else if (hiddenAction.equals("updateEventLikeCount")) {
				resultCollection = updateEventLikeCount(db,event);
			} else if (hiddenAction.equals("getTotalEventLike")) {
				resultCollection = getTotalEventLike(db,event);
			} else if (hiddenAction.equals("AgendaSessionList")) {
				resultCollection = agendaSessionList(db,event);
			} else if (hiddenAction.equals("sessionSummary")) {
				resultCollection = sessionSummary(db,event);
			}  else if (hiddenAction.equals("liveSessionAttendees")) {
				resultCollection = liveSessionAttendees(db,event);
			} else if (hiddenAction.equals("updateSessionCommentLikeCount")) {
				resultCollection = updateSessionCommentLikeCount(db,event);
			} else if (hiddenAction.equals("updateSessionLikeCount")) {
				resultCollection = updateSessionLikeCount(db,event);
			} else if (hiddenAction.equals("getSessionCommentLike")) {
				resultCollection = getSessionCommentLike(db,event);
			} else if (hiddenAction.equals("getSessionLike")) {
				resultCollection = getSessionLike(db,event);
			} else if (hiddenAction.equals("getProfileDetailsById")) {
				resultCollection = getProfileDetailsById(db,event);
			} else if (hiddenAction.equals("sessionInformation")) {
		        resultCollection = sessionInformation(db,event);
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return resultCollection;
	}

	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		Event event = null;
		String hiddenAction = null;
		event = (Event) object;
		hiddenAction = event.getHiddenAction();
		db = getDbConnection();
		try{	
		if (hiddenAction != null) {
			if (hiddenAction.equals("joinEvent")) {
				result = joinEvent(db,event);
			} else if (hiddenAction.equals("unjoinEvent")) {
				result = unjoinEvent(event);
			} else if (hiddenAction.equals("attendSession")) {
				result = attendSession(event);
			} else if (hiddenAction.equals("leaveSession")) {
				result = leaveSession(event);
			}else if (hiddenAction.equals("ADDEVENT")) {
				result = addEvent(db,event);
			}

		}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return result;
	}
	
	public boolean addEvent(ObjectContainer db,Event event) {
		boolean addEventStatus = false;
		try {
			if (event != null) {
				addEventStatus =saveEventPOJO(db,event);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addEventStatus = false;
		}
		return addEventStatus;
	}

	

	/**
	 * @method joinEvent - append the userId,eventId, and join date in the USer
	 *         Event Map.
	 * @param event
	 * @return boolean
	 */
	public boolean joinEvent(ObjectContainer db,Event event) {
		boolean userEventJoinStatus = false;
		UserEventMapPOJO userEventMapPOJO = null;
		EventPOJO eventPOJO = null;
		try {
			if (event != null) {
				userEventJoinStatus = saveUserEventMapPOJO(db,event);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userEventJoinStatus = false;
		}
		return userEventJoinStatus;
	}
	
	

	/**
	 * @method unjoinEvent - Unjoin from an event -Delete a record from USer
	 *         Event Map. based on eventId & userId
	 * @param event
	 * @return boolean
	 */
	public boolean unjoinEvent(Event event) {
		boolean userEventUnjoinStatus = false;
		UserEventMapPOJO userEventMapPOJO = null;
		try {			
			userEventUnjoinStatus = deleteUserEventMapPOJO(event);
		} catch (Exception e1) {
			userEventUnjoinStatus = false;
			e1.printStackTrace();
		}
		return userEventUnjoinStatus;
	}

	

	/**
	 * @method eventSearchTag - To get the eventTag for a event
	 * @param event
	 * @return Collection
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public Collection eventSearchTag(ObjectContainer db, Event event) {
		Collection eventSearchTagList = new ArrayList();
		EventPOJO eventPOJO = null;
		try {
			//db = getDbConnection();
			if (event != null && event.getEventId() != null) {

				ObjectSet<EventPOJO> results = db.get(new EventPOJO(event
						.getEventId()));
				if (!results.isEmpty()) {
					eventPOJO = (EventPOJO) results.next();
					event = new Event();
					event.setEventTag("#" + eventPOJO.getEventTag());
					eventSearchTagList.add(event);
				}
			} else {
				ObjectSet<EventPOJO> results = db.get(new EventPOJO());
				int i = 0;
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						eventPOJO = (EventPOJO) results.next();
						if (eventPOJO.getEventId() != null) {
							i++;
						}
					}
				}

				String[] eventsTag = new String[i];
				int j = 0;
				ObjectSet<EventPOJO> result = db.get(new EventPOJO());
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						eventPOJO = (EventPOJO) result.next();
						event = new Event();
						eventsTag[j] = "#" + eventPOJO.getEventTag();
						// eventsTag[j]=eventPOJO.getEventTag();
						event.setEventsTag(eventsTag);
						eventSearchTagList.add(event);
						j++;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return eventSearchTagList;
	}

	

	/**
	 * @method attendSession - Insert the userId,session Id & set status as YES
	 *         in the User Session Map.
	 * @param event
	 * @return boolean
	 */
	public boolean attendSession(Event event) {
		boolean userSessionJoinStatus = false;
		UserSessionMapPOJO userSessionMapPOJO = null;
		UserSessionMapPOJO userSessionMapExsistPOJO = null;
		UserEventMapPOJO userEventMapPOJO =null;
		String eventId  = null;
		try {
			db = getDbConnection();
			
			eventId = event.getEventId();
			if(eventId == null || eventId.equals("")){
				eventId = getEventIdBySessionId(db, event.getSessionId());
			}
			
			userEventMapPOJO = new UserEventMapPOJO(event.getUserId(),event.getEventId());
			ObjectSet<UserEventMapPOJO> eventMapPOJOresults = db.get(userEventMapPOJO);
			if(eventMapPOJOresults.isEmpty()){
				System.out.println("Attending a Session Without Joining the Event ");
				joinEvent(db,event); // Join the event
				if (event != null) {
					userSessionMapPOJO = new UserSessionMapPOJO(event.getUserId(),
							event.getSessionId());
					ObjectSet<UserSessionMapPOJO> results = db
							.get(userSessionMapPOJO);
					if (results.isEmpty()) {
						userSessionMapPOJO.setUsmId(getNewId());
						userSessionMapPOJO.setUsmUserId(event.getUserId());
						userSessionMapPOJO.setUsmSessionId(event.getSessionId());
						userSessionMapPOJO.setUsmStatus("Y");
						db.store(userSessionMapPOJO);

					} else {
						userSessionMapPOJO = results.get(0);
						if (!userSessionMapPOJO.getUsmStatus().equals("Y")) {
							userSessionMapPOJO.setUsmStatus("Y");
							db.store(userSessionMapPOJO);
						}

					}
				
				}
			}else{			
			if (event != null) {
				userSessionMapPOJO = new UserSessionMapPOJO(event.getUserId(),
						event.getSessionId());
				ObjectSet<UserSessionMapPOJO> results = db
						.get(userSessionMapPOJO);
				if (results.isEmpty()) {
					userSessionMapPOJO.setUsmId(getNewId());
					userSessionMapPOJO.setUsmUserId(event.getUserId());
					userSessionMapPOJO.setUsmSessionId(event.getSessionId());
					userSessionMapPOJO.setUsmStatus("Y");
					db.store(userSessionMapPOJO);

				} else {
					userSessionMapPOJO = results.get(0);
					if (!userSessionMapPOJO.getUsmStatus().equals("Y")) {
						userSessionMapPOJO.setUsmStatus("Y");
						db.store(userSessionMapPOJO);
					}

				}
			}
			}
			db.commit();
			userSessionJoinStatus = true;
			} catch (Exception e) {
			e.printStackTrace();
			userSessionJoinStatus = false;
		} finally {
			db.close();
		}
		return userSessionJoinStatus;
	}

	private String getEventIdBySessionId(ObjectContainer db, String sessionId) {
		
		SessionPOJO sessionPOJO = null;
		String eventId = null;
		
		sessionPOJO = getProfilePOJOByProfileId(db, sessionId);
		
		if(sessionPOJO != null){
			eventId = sessionPOJO.getSessionEventId();
		}
		
		return eventId;
	}
	
	private SessionPOJO getProfilePOJOByProfileId(ObjectContainer dbContainer, String sessionId){
		
		SessionPOJO sessionPOJO = null;
		
		try{
			
			ObjectSet<SessionPOJO> sessionPOJOSet = dbContainer.get(new SessionPOJO(sessionId,null));
			
			if(!sessionPOJOSet.isEmpty()){			
				sessionPOJO = sessionPOJOSet.next();
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return sessionPOJO;
	}
	

	/**
	 * @method leaveSession - Update the status as No in the User Session Map
	 *         for the user.
	 * @param event
	 * @return boolean
	 */
	public boolean leaveSession(Event event) {
		boolean userLeaveSessionStatus = false;
		UserSessionMapPOJO userSessionMapPOJO = null;
		try {
			db = getDbConnection();
			if (event != null) {
				userSessionMapPOJO = new UserSessionMapPOJO(event.getUserId(),
						event.getSessionId());
				ObjectSet<UserSessionMapPOJO> results = db
						.get(userSessionMapPOJO);
				if (!results.isEmpty()) {

					userSessionMapPOJO = results.get(0);
					if (userSessionMapPOJO.getUsmStatus() != null
							&& userSessionMapPOJO.getUsmStatus().equals("Y")) {
						userSessionMapPOJO.setUsmStatus("N");

					}
					db.store(userSessionMapPOJO);
					db.commit();
					userLeaveSessionStatus = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userLeaveSessionStatus = false;
		} finally {
			db.close();
		}

		return userLeaveSessionStatus;
	}

	/**
	 * @method - events
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection events(ObjectContainer db, Event event) throws EventPortalException {

		List eventDetailsList = null;
		String eventName = null;
		String userId = null;
		eventDetailsList = new ArrayList();
		List userJoinedEventList = new ArrayList();
		List eventPOJOList = new ArrayList();
		if (event != null) {
			eventName = event.getEventName();
			userId = event.getUserId();
		}
		try {
			userJoinedEventList = joinedEventStatusList(db,userId);
			if (eventName != null) {
				eventPOJOList = readEventPOJO(db);
				if (!eventPOJOList.isEmpty()) {
					Iterator iter = eventPOJOList.iterator();
					while (iter.hasNext()) {
						EventPOJO eventPOJO = (EventPOJO) iter.next();
						event = new Event();
						event  = convertEventPOJOToEvent(eventPOJO,event);
						event.setJoinedEvent(joinedEventStatus(userJoinedEventList, eventPOJO.getEventId()));
						int intIndex = (eventPOJO.getEventName().toLowerCase())
								.indexOf(eventName.toLowerCase());
						if (intIndex == -1) {
							// System.out.println("The event name not found ");
						} else {
							eventDetailsList.add(event);
						}
					}

				} 
			} else {
				eventPOJOList = readEventPOJO(db);
				if (!eventPOJOList.isEmpty()) {
					Iterator iter = eventPOJOList.iterator();
					while (iter.hasNext()) {
						EventPOJO eventPOJO = (EventPOJO) iter.next();
						event = new Event();
						event  = convertEventPOJOToEvent(eventPOJO,event);
						event.setJoinedEvent(joinedEventStatus(
						userJoinedEventList, eventPOJO.getEventId()));
						eventDetailsList.add(event);
					}

				}
			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventDetailsList;
	}

	/**
	 * @method joinedEventStatus
	 * @param userJoinedEventList
	 * @param eventId
	 * @return boolean
	 */
	public boolean joinedEventStatus(List userJoinedEventList, String eventId) {
		boolean joinedStatus = false;
		if (!userJoinedEventList.isEmpty()) {
			for (int i = 0; i < userJoinedEventList.size(); i++) {
				joinedStatus = userJoinedEventList.get(i).equals(eventId);
				if (joinedStatus == true) {
					break;
				}
			}
		}
		return joinedStatus;
	}

	/**
	 * @method checkUserJoinedEvent
	 * @param userId
	 * @param eventId
	 * @return boolean
	 */
	public boolean checkUserJoinedEvent(ObjectContainer db, String userId, String eventId) {
		boolean joinedStatus = false;
		List userEventMapPOJOList = null;
		UserEventMapPOJO userEventMapPOJO = null;
		userEventMapPOJOList = new ArrayList();
		try {
			userEventMapPOJOList = readUserEventMapPOJO(db);
			if (!userEventMapPOJOList.isEmpty()) {
				Iterator iter = userEventMapPOJOList.iterator();
				while (iter.hasNext()) {
					userEventMapPOJO = (UserEventMapPOJO) iter.next();
					if (userEventMapPOJO.getUemUserId().equals(userId)
							&& userEventMapPOJO.getUemEventId().equals(eventId)) {
						joinedStatus = true;
					} else {
						joinedStatus = false;
					}
				}
			}
		} catch (Exception e1) {
			joinedStatus = false;
			e1.printStackTrace();
		}
		return joinedStatus;
	}

	/**
	 * @method joinedEventStatusList -To get the events joined by an user
	 * @param userId
	 * @return joined event list
	 */
	public List joinedEventStatusList(ObjectContainer db, String userId) {

		List userJoinedEventsList = new ArrayList();
		List UserEventMapPOJOList = new ArrayList();
		try {
			//db = getDbConnection();
			UserEventMapPOJOList = readUserEventMapPOJO(db);
			Iterator iter = UserEventMapPOJOList.iterator();
			while (iter.hasNext()) {
				UserEventMapPOJO userEventMapPOJO = (UserEventMapPOJO) iter
						.next();
				if (userEventMapPOJO.getUemUserId().equals(userId)) {
					userJoinedEventsList.add(userEventMapPOJO.getUemEventId());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();

		} finally {
			//db.close();
		}
		return userJoinedEventsList;
	}

	/**
	 * @methods joinedEventStatusList -To get the events joined by an user
	 * @param userId
	 * @return joined event list
	 */
	public boolean isUserJoinedInEvent(String userId) {

		boolean userJoined = false;
		List userJoinedEventsList = new ArrayList();
		try {
			db = getDbConnection();
			ObjectSet<UserEventMapPOJO> userEventMapPOJOResults = db
					.get(new UserEventMapPOJO());
			if (!userEventMapPOJOResults.isEmpty()) {
				while (userEventMapPOJOResults.hasNext()) {
					UserEventMapPOJO userEventMapPOJO = (UserEventMapPOJO) userEventMapPOJOResults
							.next();
					if (userEventMapPOJO.getUemUserId().equals(userId)) {
						userJoinedEventsList.add(userEventMapPOJO
								.getUemEventId());
					}
				}

			}

		} catch (EventPortalException e1) {
			e1.printStackTrace();
		} finally {
			db.close();
		}

		return userJoined;
	}

	/**
	 * @method eventDetails- To get the all the related data of an event
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection eventInfo(ObjectContainer db, Event event) throws EventPortalException {
		List eventDetailsList = null;
		String eventId = null;
		String userId = null;
		eventDetailsList = new ArrayList();
		EventPOJO eventPOJO = null;
		boolean dataExist = false;
		if (event != null) {
			eventId = event.getEventId();
			userId = event.getUserId();
		}
		try {
			eventPOJO = readEventPOJOById(db,event);			
			event = convertEventPOJOToEvent(eventPOJO,event);
			
			dataExist = isUserAlreadyJoined(db, event);
			
			if(dataExist){
				event.setJoinedEvent(true);
			}
			
			eventDetailsList.add(event);

		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		}
		return eventDetailsList;
	}
	

	

	public Collection sessionSpeakerDetails(ObjectContainer db, Event event)
			throws EventPortalException {
		List speakerInfoList = new ArrayList();
		Map userSpeakerIdMap = new HashMap();
		Map userSpeakerProfileIdMap = new HashMap();
		String sessionId = null;
		try {
			// db = getDbConnection();
			if (event != null) {
				sessionId = event.getSessionId();
			}
			userSpeakerIdMap = speakersForSession(sessionId, db);
			if (!userSpeakerIdMap.isEmpty()) {
				userSpeakerProfileIdMap = speakersProfileId(userSpeakerIdMap,
						db);
			}
			if (!userSpeakerProfileIdMap.isEmpty()) {
				speakerInfoList = eventUsersProfile(userSpeakerProfileIdMap, db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return speakerInfoList;
	}

	public Event aboutSession(String sessionId) {
		Event event = null;
		try {
			db = getDbConnection();
			event = new Event();
			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(sessionId,
					null));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					SessionPOJO sessionPOJO = (SessionPOJO) results.next();
					event.setSessionId(sessionPOJO.getSessionId());
					event.setSessionName(sessionPOJO.getSessionName());
					event.setSessionEventId(sessionPOJO.getSessionEventId());
					event
							.setSessionStartDate(sessionPOJO
									.getSessionStartDate());
					event.setSessionEndDate(sessionPOJO.getSessionEndDate());
					event
							.setSessionStartTime(sessionPOJO
									.getSessionStartTime());
					event.setSessionEndTime(sessionPOJO.getSessionEndTime());
					event.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
					event.setSessionTag("#" + sessionPOJO.getSessionTag());
					event.setSessionVenueName(getVenuName(sessionPOJO
							.getSessionVenueId(), db));
					event
							.setSessionLikeCount(sessionPOJO
									.getSessionLikeCount());
				}
			}
		} catch (EventPortalException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return event;
	}

	public Event aboutEvent(String eventId, Event event) {
		try {
			db = getDbConnection();
			ObjectSet<EventPOJO> results = db.get(new EventPOJO(eventId));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					EventPOJO eventPOJO = (EventPOJO) results.next();
					event = convertEventPOJOToEvent(eventPOJO,event);			
				
				}
			}
		} catch (EventPortalException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return event;
	}

	//TODO to shift this method in Session
	public Collection liveSessionAttendees(ObjectContainer db, Event event)
			throws EventPortalException {
		List userProfileDetailsList = null;
		String sessionId = null;
		try {
			//db = getDbConnection();
			if (event != null) {
				sessionId = event.getSessionId();
			}
			List sessionList = new ArrayList();
			List eventUserList = new ArrayList();
			Map userProfileIdMap = new HashMap();
			Map speakersMap = new HashMap();
			speakersMap = speakersForSession(sessionId, db);
			userProfileDetailsList = new ArrayList();
			sessionList.add(sessionId);
			eventUserList = usersOfLiveSession(sessionList, db);
			userProfileIdMap = eventUsersProfileIdMap(eventUserList,speakersMap, sessionId, db);
			userProfileDetailsList = eventUsersProfile(userProfileIdMap, db);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return userProfileDetailsList;
	}

	/**
	 * @method eventAttendee -To get the all attendees of the event
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection eventAttendee(ObjectContainer db, Event event) throws EventPortalException {
		List userProfileDetailsList = null;
		String eventId = null;
		try {
			//db = getDbConnection();
			if (event != null) {
				eventId = event.getEventId();
			}
			List eventSessionList = new ArrayList();
			List eventUserList = new ArrayList();
			Map userProfileIdMap = new HashMap();
			userProfileDetailsList = new ArrayList();
			Map speakersMap = new HashMap();
			speakersMap = speakers(db);
			// eventSessionList = eventSessions(eventId);
			// eventUserList = usersOfEvent(); /*All attendees*/
			if (eventId != null) {
				eventUserList = usersOfEvent(eventId, db); /*
															 * only event
															 * attendees
															 */
				userProfileIdMap = eventUsersProfileIdMap(eventUserList,
						speakersMap, null, db);
				userProfileDetailsList = eventUsersProfile(userProfileIdMap, db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return userProfileDetailsList;
	}

	//TODO to shift this method in Social Media
	public Profile socialMediaProfile(String profileId, Profile profile,
			ObjectContainer db) {
		if (profileId != null) {
			try {
				// db = getDbConnection();
				ObjectSet<SMProfilePOJO> smProfilePOJOResults = db
						.get(new SMProfilePOJO(profileId));
				if (!smProfilePOJOResults.isEmpty()) {
					while (smProfilePOJOResults.hasNext()) {
						SMProfilePOJO smProfilePOJO = (SMProfilePOJO) smProfilePOJOResults
								.next();
						profile.setTwitterId(smProfilePOJO.getSmpTwitterId());
						profile.setTwitterProfileUrl(smProfilePOJO
								.getSmpTwitterProfileUrl());
						profile.setTwitterImgUrl(smProfilePOJO
								.getSmpTwitterImgUrl());
						if (smProfilePOJO.getTwtShowTweets() != null
								&& (!smProfilePOJO.getTwtShowTweets().equals(
										"null"))) {
							if (smProfilePOJO.getTwtShowTweets().equals("N")) {
								profile.setTwtShowTweets("false");
							} else {
								profile.setTwtShowTweets("true");
							}
						} else {
							profile.setTwtShowTweets("false");
						}
						if (smProfilePOJO.getTwtAllowFriends() != null
								&& (!smProfilePOJO.getTwtAllowFriends().equals(
										"null"))) {
							if (smProfilePOJO.getTwtAllowFriends().equals("N")) {
								profile.setTwitterCheck("false");							
							} else {
								profile.setTwitterCheck("true");
							}
						}

						profile.setFaceBookId(smProfilePOJO.getSmpFacebookId());
						profile.setFaceBookProfileUrl(smProfilePOJO
								.getSmpFaceBookProfileUrl());
						profile.setFaceBookImgUrl(smProfilePOJO
								.getSmpFacebookImgUrl());
						if (smProfilePOJO.getFbAllowFriendsToConnect() != null
								&& (!smProfilePOJO.getFbAllowFriendsToConnect()
										.equals("null"))) {
							if (smProfilePOJO.getFbAllowFriendsToConnect()
									.equals("N")) {
								profile.setFbAllowFriendsToConnect("false");
							} else {
								profile.setFbAllowFriendsToConnect("true");
							}
						}
						if (smProfilePOJO.getFbAllowFriendsToPost() != null
								&& (!smProfilePOJO.getFbAllowFriendsToPost()
										.equals("null"))) {
							if (smProfilePOJO.getFbAllowFriendsToPost().equals(
									"N")) {
								profile.setFbAllowFriendsToPost("false");
							} else {
								profile.setFbAllowFriendsToPost("true");
							}
						}
						profile.setLinkedInId(smProfilePOJO.getSmpLinkedInId());
						profile.setLinkedInProfileUrl(smProfilePOJO
								.getSmpLinkedInProfileUrl());
						profile.setLinkedInImgUrl(smProfilePOJO
								.getSmpLinkedInImgUrl());
						if (smProfilePOJO.getLiAllowFriendsToFollow() != null
								&& (!smProfilePOJO.getLiAllowFriendsToFollow()
										.equals("null"))) {
							if (smProfilePOJO.getLiAllowFriendsToFollow()
									.equals("N")) {
								profile.setLiAllowFriendsToFollow("false");
							} else {
								profile.setLiAllowFriendsToFollow("true");
							}
						}
						if (smProfilePOJO.getLiAllowFriendsToPost() != null
								&& (!smProfilePOJO.getLiAllowFriendsToPost()
										.equals("null"))) {
							if (smProfilePOJO.getLiAllowFriendsToPost().equals(
									"N")) {
								profile.setLiAllowFriendsToPost("false");
							} else {
								profile.setLiAllowFriendsToPost("true");
							}
						}
					}
				} else {
					/*
					 * When new user created & before connecting social media by
					 * the user
					 */
					profile.setTwitterCheck("false");
					profile.setTwtShowTweets("false");
					profile.setFbAllowFriendsToConnect("false");
					profile.setFbAllowFriendsToPost("false");
					profile.setLiAllowFriendsToPost("false");
					profile.setLiAllowFriendsToFollow("false");

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return profile;
	}

	/**
	 * @method eventSessions -To get the sessions in the event
	 * @param eventId
	 * @return List
	 */
	//TODO to shift this method in Session
	public List eventSessions(String eventId) {
		List eventSessionList = new ArrayList();
		if (eventId != null) {
			try {
				db = getDbConnection();
				ObjectSet<SessionPOJO> sessionResults = db
						.get(new SessionPOJO());
				if (!sessionResults.isEmpty()) {
					while (sessionResults.hasNext()) {
						SessionPOJO sessionPojo = (SessionPOJO) sessionResults
								.next();

						if (sessionPojo.getSessionEventId() != null) {
							if (sessionPojo.getSessionEventId().equals(eventId)) {
								eventSessionList
										.add(sessionPojo.getSessionId());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return eventSessionList;
	}

	//TODO to shift this method in Session
	public Map speakers(ObjectContainer db) {
		List eventUserList = new ArrayList();
		Map speakersMap = new HashMap();
		try {
			// db = getDbConnection();
			ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapResults = db
					.get(new SessionSpeakerMapPOJO());
			if (!sessionSpeakerMapResults.isEmpty()) {
				while (sessionSpeakerMapResults.hasNext()) {
					SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapResults
							.next();
					speakersMap.put(sessionSpeakerMapPOJO.getSsmSessionId(),
							sessionSpeakerMapPOJO.getSsmSpeakerId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return speakersMap;
	}

	//TODO to shift this method in Session
	public Map speakersForSession(String sessionId, ObjectContainer db) {
		List eventUserList = new ArrayList();
		Map speakersMap = new HashMap();
		try {
			// db = getDbConnection();
			ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapResults = db
					.get(new SessionSpeakerMapPOJO());
			if (!sessionSpeakerMapResults.isEmpty()) {
				while (sessionSpeakerMapResults.hasNext()) {
					SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapResults
							.next();
					if (sessionId != null
							&& sessionId.equals(sessionSpeakerMapPOJO
									.getSsmSessionId())) {
						speakersMap.put(
								sessionSpeakerMapPOJO.getSsmSessionId(),
								sessionSpeakerMapPOJO.getSsmSpeakerId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return speakersMap;
	}

	//TODO to shift this method in User
	public Map speakersProfileId(Map speakersMap, ObjectContainer db) {
		List eventUserList = new ArrayList();
		Map userProfileIdMap = new HashMap();
		try {
			// db = getDbConnection();
			ObjectSet<UserPOJO> userResults = db.get(new UserPOJO());
			if (!userResults.isEmpty()) {
				while (userResults.hasNext()) {
					UserPOJO userPOJO = (UserPOJO) userResults.next();
					if (speakersMap.containsValue(userPOJO.getUserId())) {
						userProfileIdMap.put(userPOJO.getUserProfileId(),
								"SPEAKER");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return userProfileIdMap;
	}

	/**
	 * @method usersOfLiveSession - To get the users who is in live session
	 * @param eventSessionsList
	 * @return List
	 */
	//TODO to shift this method in Session
	public List usersOfLiveSession(List eventSessionsList, ObjectContainer db) {
		List eventUserList = new ArrayList();
		if (!eventSessionsList.isEmpty()) {
			try {
				// db = getDbConnection();
				ObjectSet<UserSessionMapPOJO> userMapResults = db
						.get(new UserSessionMapPOJO());
				if (!userMapResults.isEmpty()) {
					while (userMapResults.hasNext()) {
						UserSessionMapPOJO userSessionMapPOJO = (UserSessionMapPOJO) userMapResults
								.next();
						Iterator sessionIdIter = eventSessionsList.iterator();
						while (sessionIdIter.hasNext()) {
							String sessionId = (String) sessionIdIter.next();
							if (userSessionMapPOJO.getUsmSessionId() != null) {
								if (userSessionMapPOJO.getUsmStatus() != null) {
									if (userSessionMapPOJO.getUsmSessionId()
											.equals(sessionId)
											&& userSessionMapPOJO
													.getUsmStatus().equals("Y")) {
										eventUserList.add(userSessionMapPOJO
												.getUsmUserId());
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return eventUserList;
	}

	/**
	 * @method usersOfEvent - To get the users who attend the event from UserMap
	 * @param eventSessionsList
	 * @return List
	 */
	//TODO to shift this method in Session
	public List usersOfSession(List eventSessionsList, ObjectContainer db) {
		List eventUserList = new ArrayList();
		if (!eventSessionsList.isEmpty()) {
			try {
				// db = getDbConnection();
				ObjectSet<UserSessionMapPOJO> userMapResults = db
						.get(new UserSessionMapPOJO());
				if (!userMapResults.isEmpty()) {
					while (userMapResults.hasNext()) {
						UserSessionMapPOJO userSessionMapPOJO = (UserSessionMapPOJO) userMapResults
								.next();
						Iterator sessionIdIter = eventSessionsList.iterator();
						while (sessionIdIter.hasNext()) {
							String sessionId = (String) sessionIdIter.next();
							if (userSessionMapPOJO.getUsmSessionId() != null) {
								System.out.println(" >>"+userSessionMapPOJO.getUsmSessionId()+" >> "+sessionId);
								if (userSessionMapPOJO.getUsmSessionId()
										.equals(sessionId)) {
									eventUserList.add(userSessionMapPOJO
											.getUsmUserId());
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return eventUserList;
	}

	/**
	 * @method usersOfEvent - To get the users who attend the event from
	 *         UserEventMap
	 * @param eventUsersList
	 * @return List
	 */
	public List usersOfEvent() {
		List eventUserList = new ArrayList();
		try {
			db = getDbConnection();
			ObjectSet<UserEventMapPOJO> userEventMapResults = db
					.get(new UserEventMapPOJO());
			if (!userEventMapResults.isEmpty()) {
				while (userEventMapResults.hasNext()) {
					UserEventMapPOJO userEventMapPOJO = (UserEventMapPOJO) userEventMapResults
							.next();

					if (userEventMapPOJO.getUemId() != null) {
						eventUserList.add(userEventMapPOJO.getUemUserId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return eventUserList;
	}

	/**
	 * @method usersOfEvent - To get the users who attend the event from
	 *         UserEventMap
	 * @param eventUsersList
	 * @return List
	 */
	public List usersOfEvent(String eventId, ObjectContainer db) {
		List eventUserList = new ArrayList();

		ObjectSet<UserEventMapPOJO> userEventMapResults = db
					.get(new UserEventMapPOJO());
			if (!userEventMapResults.isEmpty()) {
				while (userEventMapResults.hasNext()) {
					UserEventMapPOJO userEventMapPOJO = (UserEventMapPOJO) userEventMapResults
							.next();
					
					if (userEventMapPOJO.getUemEventId().equals(eventId)) {
						eventUserList.add(userEventMapPOJO.getUemUserId());
						
					}
				}
			}
		 
		return eventUserList;
	}
	
	
	/**
	 * @method usersOfEvent - To get the users who attend the event from
	 *         UserEventMap
	 * @param eventUsersList
	 * @return List
	 */
	public List eventUsers(ObjectContainer db, Event event) {
		List eventUserList = new ArrayList();
		UserEventMapPOJO userEventMapPOJO = null;

		ObjectSet<UserEventMapPOJO> userEventMapResults = db
					.get(new UserEventMapPOJO(event.getEventId()));
		
			if (!userEventMapResults.isEmpty()) {
				while (userEventMapResults.hasNext()) {
					userEventMapPOJO = (UserEventMapPOJO) userEventMapResults
							.next();
						eventUserList.add(userEventMapPOJO.getUemUserId());					
				}
			}
		 
		return eventUserList;
	}

	/**
	 * @method eventUsersProfileId -To get the ProfileId of the user(Who
	 *         attended the session)
	 * @param eventUsersList
	 * @return List
	 */
	
	public Map eventUsersProfileIdMap(List eventUsersList, Map speakersMap,
			String sessionId, ObjectContainer db) {
		Map userProfileIdMap = new HashMap();
		if (!eventUsersList.isEmpty()) {
			try {
				// db = getDbConnection();
				ObjectSet<UserPOJO> userResults = db.get(new UserPOJO());
				if (!userResults.isEmpty()) {
					while (userResults.hasNext()) {
						UserPOJO userPOJO = (UserPOJO) userResults.next();
						Iterator eventUserIter = eventUsersList.iterator();
						while (eventUserIter.hasNext()) {
							String userId = (String) eventUserIter.next();
							if (userPOJO.getUserId() != null) {
								if (userPOJO.getUserId().equals(userId)) {
									if (sessionId == null) {
										if (speakersMap.containsValue(userPOJO
												.getUserId())) {
											userProfileIdMap.put(userPOJO
													.getUserProfileId(),
													"SPEAKER");
										} else {
											userProfileIdMap.put(userPOJO
													.getUserProfileId(), "");
										}
									} else {
										if (speakersMap.containsValue(userPOJO
												.getUserId())
												&& (speakersMap
														.containsKey(sessionId))) {
											userProfileIdMap.put(userPOJO
													.getUserProfileId(),
													"SPEAKER");
										} else {
											userProfileIdMap.put(userPOJO
													.getUserProfileId(), "");
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return userProfileIdMap;
	}

	/**
	 * @method eventUsersProfile- To get the profile of the users(Who attended
	 *         the Event)
	 * @param eventUsersProfileIdList
	 * @return
	 */
	
	//TODO to shift this method in Session
	public List eventUsersProfile(Map userProfileIdMap, ObjectContainer db) {
		Profile profile = null;
		String speakerfor = null;
		List userProfileDetailsList = new ArrayList();
		if (!userProfileIdMap.isEmpty()) {
			try {
				// db = getDbConnection();
				ObjectSet<ProfilePOJO> userProfileResults = db
						.get(new ProfilePOJO());
				if (!userProfileResults.isEmpty()) {
					while (userProfileResults.hasNext()) {
						ProfilePOJO profilePOJO = (ProfilePOJO) userProfileResults
								.next();
						profile = new Profile();
						if (profilePOJO.getProfileId() != null) {

							if (userProfileIdMap.containsKey(profilePOJO
									.getProfileId())) {
								/*
								 * if(userProfileIdMap.get(profilePOJO.getProfileId
								 * ()).equals("SPEAKER")){
								 * profile.setSpeaker(true); }else{
								 * profile.setSpeaker(false); }
								 */
								// System.out.print("profilePOJO.getProfileId()  >>"+profilePOJO.getProfileId());
								profile.setProfileEmail(profilePOJO
										.getProfileEmail());
								profile
										.setProfileId(profilePOJO
												.getProfileId());
								profile.setFirstName(profilePOJO
										.getProfileFirstName());
								profile.setLastName(profilePOJO
										.getProfileLastName());
								profile.setGender(profilePOJO
										.getProfileGender());
								// profile.setProfilePicture(String.valueOf(profilePOJO.getProfileImage()));
								profile.setAlternativeEmail(profilePOJO
										.getProfileAlternateEmail());
								profile.setMobile(profilePOJO
										.getProfileMobilePhone());
								profile.setLandHome(profilePOJO
										.getProfileHomePhone());
								profile.setLandOffice(profilePOJO
										.getProfileOfficePhone());
								profile.setAddress(profilePOJO
										.getProfileAddress());
								profile.setCity(profilePOJO.getProfileCity());
								profile.setCountry(profilePOJO
										.getProfileCountry());
								profile.setZip(profilePOJO.getProfileZip());
								profile.setState(profilePOJO.getProfileState());
								profile.setEducation(profilePOJO
										.getProfileEducation());
								profile.setOccupation(profilePOJO
										.getProfileOccupation());
								profile.setWebsite(profilePOJO
										.getProfileWebsite());
								profile.setSpeakerKeyNotes(profilePOJO
										.getSpeakerKeyNotes());
								profile = socialMediaProfile(profilePOJO
										.getProfileId(), profile, db);
								speakerfor = eventSessionName(profilePOJO
										.getProfileId(), db);
								if (speakerfor != null) {
									profile.setSpeaker(true);
									profile.setSpeakerFor(speakerfor);
								}
//								//
//								if (profilePOJO.getProfileImgLocation() != null
//										&& !profilePOJO.getProfileImgLocation()
//												.trim().startsWith("http")) {
//									// profile.setProfilePicture(retrieveUserProfileImgFile(profileId));
//									profile.setImgLocation(BlobImageRetrival
//											.retrieveUserProfileImgFile(profile
//													.getProfileId()));
//								} else {
//									profile.setImgLocation(profilePOJO
//											.getProfileImgLocation());
//								}
								
								if(profilePOJO.getProfileImgLocation() != null){
									
									if(profilePOJO.getImgLocation() != null){
										String imgLocation = BlobImageRetrival.retrieveUserProfileImgFile(profile.getProfileId());
										imgLocation = "/images/"+imgLocation;
										
										profile.setImgLocation(imgLocation);
										System.out.println("Image path in DAO==>"+imgLocation);
									}
										profile.setImgLocation(profilePOJO.getProfileImgLocation());
									
									
								}	
								

								userProfileDetailsList.add(profile);
							}
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return userProfileDetailsList;
	}

	//TODO to shift this method in Session
	public String eventSessionName(String profileId, ObjectContainer db) {
		String speakerFor = null;
		String userId = null;
		String[] sessionId = null;
		String eventName = null;
		int i = 0;
		try {
			// db = getDbConnection();
			ObjectSet<UserPOJO> userResults = db
					.get(new UserPOJO(profileId, "",""));
			if (!userResults.isEmpty()) {
				UserPOJO userPOJO = (UserPOJO) userResults.next();
				userId = userPOJO.getUserId();

			}
			ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = db
					.get(new SessionSpeakerMapPOJO(userId, ""));
			if (!sessionSpeakerMapPOJOResults.isEmpty()) {
				sessionId = new String[sessionSpeakerMapPOJOResults.size()];
				while (sessionSpeakerMapPOJOResults.hasNext()
						&& i < sessionSpeakerMapPOJOResults.size()) {
					SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapPOJOResults
							.next();
					sessionId[i] = sessionSpeakerMapPOJO.getSsmSessionId();
					i++;
				}
			}
			if (sessionId != null && sessionId.length != 0) {
				int j = 0;
				while (j < sessionId.length) {
					ObjectSet<SessionPOJO> sessionPOJOResults = db
							.get(new SessionPOJO(sessionId[j], ""));
					SessionPOJO sessionPOJO = (SessionPOJO) sessionPOJOResults
							.next();
					ObjectSet<EventPOJO> eventPOJOResults = db
							.get(new EventPOJO(sessionPOJO.getSessionEventId()));
					EventPOJO eventPOJO = (EventPOJO) eventPOJOResults.next();

					if (speakerFor == null) {
						eventName = eventPOJO.getEventName();
						speakerFor = eventName + " - "
								+ sessionPOJO.getSessionName();
					} else {

						if (eventName != null
								&& eventName.equals(eventPOJO.getEventName())) {
							speakerFor = speakerFor + ", "
									+ sessionPOJO.getSessionName();
						} else {

							speakerFor = speakerFor + " | "
									+ eventPOJO.getEventName() + " - "
									+ sessionPOJO.getSessionName();
						}
						eventName = eventPOJO.getEventName();
					}
					j++;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// db.close();
		}

		return speakerFor;
	}

	/**
	 * @method agendaSessionList - To get all the sessions of event
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection agendaSessionList(ObjectContainer db, Event event)
			throws EventPortalException {
		int noOfDays = 0;
		List eventDetailsList = null;
		String eventId = null;
		eventDetailsList = new ArrayList();
		if (event != null) {
			eventId = event.getEventId();
		}
		try {
			//db = getDbConnection();
			ObjectSet<EventPOJO> results = db.get(new EventPOJO(eventId));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					EventPOJO eventPOJO = (EventPOJO) results.next();
					event = new Event();
					event = convertEventPOJOToEvent(eventPOJO,event);
					if(eventPOJO.getEventNoOfDays()!= null){
						noOfDays = Integer.parseInt(eventPOJO.getEventNoOfDays());
					}else{
						noOfDays = 2;
					}
					
					List sessionList = getsessionList(event, noOfDays,eventPOJO.getEventStartDate(),
							eventPOJO.getEventEndDate(), db);
					event.setSessionList(sessionList);
					eventDetailsList.add(event);

				}

			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	/**
	 * @method getsessionList - To get Session List by date wise
	 * @param event
	 *            , noOfDays, db, start
	 * @return List
	 * @throws
	 */
	//TODO to shift this method in Session
	public List getsessionList(Event eventList, int noOfDays, String start,String end,
			ObjectContainer db) {
		List sessionList = null;
		Event event = null;
		List sesList = null;
		sessionList = new ArrayList();
		for (int i = 1; i <=noOfDays; i++) {	
			sesList = new ArrayList();
			try {
				ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(eventList.getEventId()));
				
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						SessionPOJO sessionPOJO = (SessionPOJO) results.next();
						if(i==1){
						if (sessionPOJO.getSessionStartDate().equals(start)) {
							event = new Event();
							event.setSessionId(sessionPOJO.getSessionId());
							event.setSessionName(sessionPOJO.getSessionName());
							event.setSessionStartDate(sessionPOJO
									.getSessionStartDate());
							event.setSessionEndDate(sessionPOJO
									.getSessionEndDate());
							event.setSessionStartTime(sessionPOJO
									.getSessionStartTime());
							event.setSessionEndTime(sessionPOJO
									.getSessionEndTime());
							event.setSessionVenueName(getVenuName(sessionPOJO
									.getSessionVenueId(), db));
							sesList.add(event);
						}
						}else{
							if (sessionPOJO.getSessionStartDate().equals(end)) {
							event = new Event();
							event.setSessionId(sessionPOJO.getSessionId());
							event.setSessionName(sessionPOJO.getSessionName());
							event.setSessionStartDate(sessionPOJO
									.getSessionStartDate());
							event.setSessionEndDate(sessionPOJO
									.getSessionEndDate());
							event.setSessionStartTime(sessionPOJO
									.getSessionStartTime());
							event.setSessionEndTime(sessionPOJO
									.getSessionEndTime());
							event.setSessionVenueName(getVenuName(sessionPOJO
									.getSessionVenueId(), db));
							sesList.add(event);
							}
						}
						}
				}
				
			} catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			} catch (Db4oIOException e) {
				e.printStackTrace();
			}
			sessionList.add(sesList);
		//	start = dateIncreament(start);
		}
		return sessionList;
	}

	/**
	 * @method getVenuName - To get Session Venue Name
	 * @param VenuId
	 *            , db
	 * @return String
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String getVenuName(String VenuId, ObjectContainer db) {
		String venuName = null;
		try {
			ObjectSet<VenuePOJO> results = db.get(new VenuePOJO(VenuId));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					VenuePOJO venuePOJO = (VenuePOJO) results.next();
					venuName = venuePOJO.getVenueName();
				}
			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
		}
		return venuName;
	}
	
	//TODO to shift this method in Date Utility
	public String dateIncreament(String date) {
		String sessionDate = null;
		String zeroCheck = date.substring(8, 9);
		String finalString = date.substring(0, 8);
		sessionDate = date.substring(8);
		int dateInt = Integer.parseInt(sessionDate);
		dateInt = dateInt + 1;
		sessionDate = Integer.toString(dateInt);
		if (zeroCheck.equals("0")) {
			if (!sessionDate.equals("10")) {
				sessionDate = "0" + sessionDate;
			}
		}
		finalString = finalString.concat(sessionDate);
		return finalString;
	}

	/**
	 * @method sessionSpeakerInfo - To get the sessions speaker information
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection sessionSummary(ObjectContainer db, Event event) throws EventPortalException {
		List eventSummaryList = null;
		String sessionId = null;
		String sessionVenuId = null;
		String usmProfileId = null;
		Profile profile = null;
		eventSummaryList = new ArrayList();
		if (event != null) {
			sessionId = event.getSessionId();
		}
		String evenId = null;
		try {
			//db = getDbConnection();
			event = new Event();

			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(sessionId,
					null));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					SessionPOJO sessionPOJO = (SessionPOJO) results.next();
					evenId = sessionPOJO.getSessionEventId();
					event.setSessionId(sessionPOJO.getSessionId());
					event.setSessionName(sessionPOJO.getSessionName());
					event
							.setSessionStartDate(sessionPOJO
									.getSessionStartDate());
					event.setSessionEndDate(sessionPOJO.getSessionEndDate());
					event
							.setSessionStartTime(sessionPOJO
									.getSessionStartTime());
					event.setSessionEndTime(sessionPOJO.getSessionEndTime());
					event.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
					event.setSessionVenueName(getVenuName(sessionPOJO
							.getSessionVenueId(), db));
				}
			}
			if (evenId != null) {
				ObjectSet<EventPOJO> evenresults = db
						.get(new EventPOJO(evenId));
				if (!evenresults.isEmpty()) {
					while (evenresults.hasNext()) {
						EventPOJO eventPOJO = (EventPOJO) evenresults.next();
						event = convertEventPOJOToEvent(eventPOJO,event);			
						}
				}
			}
			List sessionSpeakerInfoList = (List) sessionSpeakerDetails(db,event);
			eventSummaryList.add(event);

			if (!sessionSpeakerInfoList.isEmpty()) {
				Profile speakerProfile = null;
				
			speakerProfile = (Profile) sessionSpeakerInfoList
						.get(0);

//				ObjectSet<SMProfilePOJO> smProfilResult = db
//						.get(new SMProfilePOJO(speakerProfile.getProfileId()));
//				if (!smProfilResult.isEmpty()) {
//					while (smProfilResult.hasNext()) {
//						SMProfilePOJO smProfilePOJO = (SMProfilePOJO) smProfilResult
//								.next();
//						if (smProfilePOJO.getFbAllowFriendsToPost() != null) {
//							if (smProfilePOJO.getFbAllowFriendsToPost().equals(
//									"N")) {
//								speakerProfile.setFacebookCheck("false");
//							} else {
//								speakerProfile.setFacebookCheck("true");
//							}
//						}
//						if (smProfilePOJO.getTwtShowTweets() != null) {
//							if (smProfilePOJO.getTwtShowTweets().equals("N")) {
//								speakerProfile.setTwitterCheck("false");
//							} else {
//								speakerProfile.setTwitterCheck("true");
//							}
//						}
//						if (smProfilePOJO.getLiAllowFriendsToPost() != null) {
//							if (smProfilePOJO.getLiAllowFriendsToPost().equals(
//									"N")) {
//								speakerProfile.setLinkedinCheck("false");
//							} else {
//								speakerProfile.setLinkedinCheck("true");
//							}
//						}
//					}
//				}
				eventSummaryList.add(speakerProfile);
			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		return eventSummaryList;
	}

	
	/**
	 * @method updateSessionCommentLikeCount - To update session comment
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection updateSessionCommentLikeCount(ObjectContainer db, Event event)
			throws EventPortalException {
		List eventDetailsList = null;
		boolean result = false;
		String sessionComment = null;
		eventDetailsList = new ArrayList();
		SessionCommentPOJO sessionCommentPOJO = null;
		SessionPOJO sessionPOJO = null;
		EventPOJO eventPOJO = null;
		String scSessionCmtId = null;
		int scLikecount = 0;
		int scLikeCountSum = 0;
		String scSessionId = null;
		int sessLikeCountSum = 0;
		String scEventId = null;
		int eventLikeCountSum = 0;
		int incCount = 0;
		Map scSessionLikeCountMap = new HashMap();

		try {
			//db = getDbConnection();
			if (event.getSessionCommentId() != null
					&& !event.getSessionCommentId().equals("")) {
				scSessionCmtId = event.getSessionCommentId();
				ObjectSet<SessionCommentPOJO> results = db
						.get(new SessionCommentPOJO());
				while (results.hasNext()) {
					sessionCommentPOJO = (SessionCommentPOJO) results.next();
					if (sessionCommentPOJO.getScId().equals(scSessionCmtId)) {
						scLikecount = event.getSessionLikeCount();
						incCount = sessionCommentPOJO.getScCommentLikeCount()
								+ scLikecount;
						sessionCommentPOJO.setScCommentLikeCount(incCount);

						scSessionId = sessionCommentPOJO.getScSessionId();
						db.store(sessionCommentPOJO);
						db.commit();
						result = true;
						event.setSessionCommentLikeCount(sessionCommentPOJO
								.getScCommentLikeCount());
						event.setResult(result);
					}
				}
			}
			if (scSessionId != null && !scSessionId.equals("")) {
				ObjectSet<SessionPOJO> results = db.get(new SessionPOJO());
				while (results.hasNext()) {
					sessionPOJO = (SessionPOJO) results.next();
					if (sessionPOJO.getSessionId().equals(scSessionId)) {
						int value = sessionPOJO.getSessionLikeCount() + 1;
						sessionPOJO.setSessionLikeCount(value);
						scEventId = sessionPOJO.getSessionEventId();
						db.store(sessionPOJO);
						db.commit();
						result = true;
						event.setSessionLikeCount(sessionPOJO
								.getSessionLikeCount());
					}
				}
			}

			if (scEventId != null && !scEventId.equals("")) {
				ObjectSet<EventPOJO> results = db.get(new EventPOJO());
				while (results.hasNext()) {
					eventPOJO = (EventPOJO) results.next();
					if (eventPOJO.getEventId().equals(scEventId)) {
						if(eventPOJO.getEventLikeCount() != null){
							int value = Integer.parseInt(eventPOJO.getEventLikeCount()) + 1;
							eventPOJO.setEventLikeCount(String.valueOf(value));							
							event.setEventLikeCount(Integer.parseInt(eventPOJO.getEventLikeCount()));
						}
						db.store(eventPOJO);
						db.commit();
						result = true;
					}
				}
			}

			event.setSessionCommentLikeCountSum(event
					.getSessionCommentLikeCount());
			event.setSessionLikeCountSum(event.getSessionLikeCount());
			event.setEventLikeCountSum(event.getEventLikeCount());

		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		eventDetailsList.add(event);
		return eventDetailsList;
	}

	/**
	 * @method updateSessionLikeCount - To update session comment
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection updateSessionLikeCount(ObjectContainer db, Event event)
			throws EventPortalException {
		List eventDetailsList = null;
		boolean result = false;
		String sessionComment = null;
		eventDetailsList = new ArrayList();
		SessionPOJO sessionPOJO = null;
		EventPOJO eventPOJO = null;

		String scSessionId = null;
		int sessLikeCount = 0;
		int sessLikeCountSum = 0;
		String scEventId = null;
		int eventLikeCountSum = 0;
		int incCount = 0;
		// Map sessEventLikeCountMap = new HashMap();

		try {
			//db = getDbConnection();
			if (event.getSessionCommentSessId() != null
					&& !event.getSessionCommentSessId().equals("")) {
				scSessionId = event.getSessionCommentSessId();
				
				ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(scSessionId,null));
				
				if(!results.isEmpty()){
					sessionPOJO = (SessionPOJO) results.next();
					sessLikeCount = sessLikeCount + 1;
					incCount = sessionPOJO.getSessionLikeCount()
							+ sessLikeCount;
					sessionPOJO.setSessionLikeCount(incCount);
					scEventId = sessionPOJO.getSessionEventId();
					event.setSessionLikeCount(sessionPOJO
							.getSessionLikeCount());
					// sessEventLikeCountMap.put(scEventId, incCount);
					System.out.println("IN Session Like Count--Inner"+sessionPOJO.getSessionId());
					System.out.println("IN Session Like Count--Eevnt"+sessionPOJO.getSessionEventId());
				}
				
//				ObjectSet<SessionPOJO> results = db.get(new SessionPOJO());
//				while (results.hasNext()) {
//					sessionPOJO = (SessionPOJO) results.next();
//					if (sessionPOJO.getSessionId().equals(scSessionId)) {
//						sessLikeCount = sessLikeCount + 1;
//						incCount = sessionPOJO.getSessionLikeCount()
//								+ sessLikeCount;
//						sessionPOJO.setSessionLikeCount(incCount);
//						scEventId = sessionPOJO.getSessionEventId();
//						event.setSessionLikeCount(sessionPOJO
//								.getSessionLikeCount());
//						// sessEventLikeCountMap.put(scEventId, incCount);
//						System.out.println("IN Session Like Count--Inner");
//					}
					db.store(sessionPOJO);
					db.commit();
					result = true;
					if(sessionPOJO != null){
						//sessLikeCountSum += sessionPOJO.getSessionLikeCount();
					}
					sessLikeCountSum = totalSessionLikeCount(db,scEventId);
					
				}
				 //sessLikeCountSum +=incCount;
				
			

			if (scEventId != null && !scEventId.equals("")) {
				ObjectSet<EventPOJO> results = db.get(new EventPOJO());
				while (results.hasNext()) {
					eventPOJO = (EventPOJO) results.next();
					if (eventPOJO.getEventId().equals(scEventId)) {
						eventPOJO.setEventLikeCount(String.valueOf(sessLikeCountSum));
						db.store(eventPOJO);
						db.commit();
						result = true;
						event.setEventLikeCount(Integer.parseInt(eventPOJO.getEventLikeCount()));
					}
					if(eventPOJO.getEventLikeCount() != null){
						eventLikeCountSum += Integer.parseInt(eventPOJO.getEventLikeCount());
					}
					
				}
			}

			event.setSessionLikeCountSum(sessLikeCountSum);
			event.setEventLikeCountSum(eventLikeCountSum);

		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		eventDetailsList.add(event);
		return eventDetailsList;
	}

	/**
	 * @method updateSessionComment - To update session comment
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection getSessionCommentLike(ObjectContainer db, Event event)
			throws EventPortalException {
		List eventDetailsList = null;
		String sessionComment = null;
		eventDetailsList = new ArrayList();
		SessionCommentPOJO sessionCommentPOJO = null;
		SessionPOJO sessionPOJO = null;
		EventPOJO eventPOJO = null;
		String scSessionCmtId = null;
		int scLikeCountSum = 0;
		String scSessionId = null;
		int sessLikeCountSum = 0;
		String scEventId = null;
		int eventLikeCountSum = 0;
		Map scSessionLikeCountMap = new HashMap();

		try {
			//db = getDbConnection();
			if (event.getSessionCommentId() != null
					&& !event.getSessionCommentId().equals("")) {
				scSessionCmtId = event.getSessionCommentId();
				ObjectSet<SessionCommentPOJO> results = db
						.get(new SessionCommentPOJO());
				while (results.hasNext()) {
					sessionCommentPOJO = (SessionCommentPOJO) results.next();
					if (sessionCommentPOJO.getScId().equals(scSessionCmtId)) {
						event.setSessionCommentLikeCount(sessionCommentPOJO
								.getScCommentLikeCount());
						eventDetailsList.add(event);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	/**
	 * @method updateSessionComment - To update session comment
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	//TODO to shift this method in Session
	public Collection getSessionLike(ObjectContainer db, Event event) throws EventPortalException {
		List eventDetailsList = null;
		String sessionComment = null;
		eventDetailsList = new ArrayList();
		SessionPOJO sessionPOJO = null;
		EventPOJO eventPOJO = null;
		String scSessionId = null;
		int scLikeCountSum = 0;
		String scSessionCmntId = null;
		int sessLikeCountSum = 0;
		String scEventId = null;
		int eventLikeCountSum = 0;
		Map scSessionLikeCountMap = new HashMap();

		try {
			//db = getDbConnection();
			if (event.getSessionCommentSessId() != null
					&& !event.getSessionCommentSessId().equals("")) {
				scSessionId = event.getSessionCommentSessId();
				ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(
						scSessionId, ""));
				while (results.hasNext()) {
					sessionPOJO = (SessionPOJO) results.next();
					if (sessionPOJO.getSessionId().equals(scSessionId)) {
						scSessionId = event.getSessionId();
						event.setSessionLikeCount(sessionPOJO
								.getSessionLikeCount());
						eventDetailsList.add(event);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	/**
	 * @method updateEventLikeCount - To update individual event like count
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection updateEventLikeCount(ObjectContainer db, Event event)
			throws EventPortalException {
		List eventDetailsList = null;
		boolean result = false;
		String sessionComment = null;
		eventDetailsList = new ArrayList();
		EventPOJO eventPOJO = null;
		String scEventId = null;
		int eventLikeCount = 0;
		int eventLikeCountSum = 0;
		int incCount = 0;

		try {
			//db = getDbConnection();
			if (event.getEventId() != null && !event.getEventId().equals("")) {
				scEventId = event.getEventId();
				ObjectSet<EventPOJO> results = db.get(new EventPOJO());
				while (results.hasNext()) {
					eventPOJO = (EventPOJO) results.next();
					if (eventPOJO.getEventId().equals(scEventId)) {
						eventLikeCount = eventLikeCount + 1;
						
						if(eventPOJO.getEventLikeCount() != null){
							incCount = Integer.parseInt(eventPOJO.getEventLikeCount())
							+ eventLikeCount;
							eventPOJO.setEventLikeCount(String.valueOf(incCount));
							event.setEventLikeCount(Integer.parseInt(eventPOJO.getEventLikeCount()));
				
						}
						
					}
					db.store(eventPOJO);
					db.commit();
					result = true;

					if(eventPOJO.getEventLikeCount() != null){
						eventLikeCountSum +=Integer.parseInt(eventPOJO.getEventLikeCount());	
					}
					
				}
			}
			event.setEventLikeCountSum(eventLikeCountSum);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		eventDetailsList.add(event);
		return eventDetailsList;
	}

	/**
	 * @method getEventLike - To get event like count
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getEventLike(ObjectContainer db, Event event) throws EventPortalException {
		List eventDetailsList = null;
		eventDetailsList = new ArrayList();
		EventPOJO eventPOJO = null;
		String scEventId = null;
		int eventLikeCountSum = 0;

		try {
			//db = getDbConnection();
			if (event.getEventId() != null && !event.getEventId().equals("")) {
				scEventId = event.getEventId();
				ObjectSet<EventPOJO> results = db.get(new EventPOJO());
				while (results.hasNext()) {
					eventPOJO = (EventPOJO) results.next();
					if (eventPOJO.getEventId().equals(scEventId)) {
						scEventId = eventPOJO.getEventId();
						if(eventPOJO.getEventLikeCount() != null){
							event.setEventLikeCount(Integer.parseInt(eventPOJO.getEventLikeCount()));
						}
						
						eventDetailsList.add(event);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	/**
	 * @method getEventLike - To get event like count
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getTotalEventLike(ObjectContainer db, Event event)
			throws EventPortalException {
		List eventDetailsList = null;
		eventDetailsList = new ArrayList();
		EventPOJO eventPOJO = null;
		String scEventId = null;
		int eventLikeCountSum = 0;
		try {
			//db = getDbConnection();
			ObjectSet<EventPOJO> results = db.get(new EventPOJO());
			while (results.hasNext()) {
				eventPOJO = (EventPOJO) results.next();
				if(eventPOJO.getEventLikeCount()!= null){
					eventLikeCountSum += Integer.parseInt(eventPOJO.getEventLikeCount());
				}
				
			}
			event.setEventLikeCountSum(eventLikeCountSum);
			eventDetailsList.add(event);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	//TODO to shift this method in Profile
	
	public Collection getProfileDetailsById(ObjectContainer db, Event event)
			throws EventPortalException {
		List profileDetailsList = null;
		profileDetailsList = new ArrayList();
		Map profileIdMap = new HashMap();
		String profileId = null;

		if (event != null) {
			profileId = event.getProfile().getToProfileId();
			profileIdMap.put(profileId, "");
			try {
				//db = getDbConnection();
				profileDetailsList = eventUsersProfile(profileIdMap, db);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//db.close();
			}
		}
		return profileDetailsList;
	}

	/**
	 * @method getEvents - To get all the existing events
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getEvents(ObjectContainer db, Event event) throws EventPortalException {

		List eventDetailsList = null;
		String eventName = null;
		String userId = null;
		eventDetailsList = new ArrayList();
		try {
			//db = getDbConnection();
			ObjectSet<EventPOJO> results = db.get(new EventPOJO());
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					EventPOJO eventPOJO = (EventPOJO) results.next();
					event = new Event();
					event = convertEventPOJOToEvent(eventPOJO, event);
				    eventDetailsList.add(event);
				}

			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			//db.close();
		}
		return eventDetailsList;
	}

	/*
	 * 
	 */

	public List readUserSessionMapPOJO() {
		UserSessionMapPOJO userSessionMapPOJO = null;
		List userSessionMapList = new ArrayList();
		try {
			db = getDbConnection();
			ObjectSet<UserSessionMapPOJO> result = db
					.get(new UserSessionMapPOJO());
			while (result.hasNext()) {
				userSessionMapPOJO = result.next();
				userSessionMapList.add(userSessionMapPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return userSessionMapList;
	}

	//TODO to shift this method in Session
	
	public UserSessionMapPOJO readUserSessionMapPOJOById(Event event) {
		UserSessionMapPOJO userSessionMapPOJO = null;
		String userId = null;
		String sessionId = null;

		try {
			db = getDbConnection();
			if (event != null) {
				userId = event.getUserId();
				sessionId = event.getSessionId();
				ObjectSet<UserSessionMapPOJO> result = db
						.get(new UserSessionMapPOJO(userId, sessionId));
				while (result.hasNext()) {
					userSessionMapPOJO = result.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return userSessionMapPOJO;
	}

	public List readUserEventMapPOJO(ObjectContainer db) {
		List userEventList = null;
		UserEventMapPOJO userEventMapPOJO = null;
		userEventList = new ArrayList();

		try {
			//db = getDbConnection();
			ObjectSet<UserEventMapPOJO> result = db.get(new UserEventMapPOJO());
			while (result.hasNext()) {
				userEventMapPOJO = result.next();
				if (userEventMapPOJO != null) {
					userEventList.add(userEventMapPOJO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return userEventList;
	}

	
	
	public List readEventPOJO(ObjectContainer db) {
		List eventPOJOList = null;
		EventPOJO eventPOJO = null;
		eventPOJOList = new ArrayList();
		try {
			//db = getDbConnection();
			ObjectSet<EventPOJO> result = db.get(new EventPOJO());
			while (result.hasNext()) {
				eventPOJO = result.next();
				eventPOJOList.add(eventPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return eventPOJOList;
	}
	
	

	public EventPOJO readEventPOJOById(ObjectContainer db, Event event) {
		EventPOJO eventPOJO = null;
		String eventId = null;

		try {
			//db = getDbConnection();
			if (event != null) {
				eventId = event.getEventId();
				if(eventId != null){
				ObjectSet<EventPOJO> result = db.get(new EventPOJO(eventId));
				if(!result.isEmpty()){
				while (result.hasNext()) {
					eventPOJO = result.next();
				}
			}
			}else{
				eventPOJO = null;
			}
			}else{
				eventPOJO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}

		return eventPOJO;
	}

	//TODO to shift this method in Session
	
	public List readSessionPOJO() {
		List sessionList = null;
		SessionPOJO sessionPOJO = null;
		sessionList = new ArrayList();
		String sessionId = null;
		try {
			db = getDbConnection();
			ObjectSet<SessionPOJO> result = db.get(new SessionPOJO());
			if (!result.isEmpty()) {
				while (result.hasNext()) {
					sessionPOJO = result.next();
					sessionList.add(sessionPOJO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return sessionList;
	}

	//TODO to shift this method in Session
	
	public SessionPOJO readSessionPOJOById(Event event) {
		SessionPOJO sessionPOJO = null;
		String sessionId = null;
		try {
			db = getDbConnection();
			if (event != null) {
				sessionId = event.getSessionId();
				ObjectSet<SessionPOJO> result = db.get(new SessionPOJO(
						sessionId, null));
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						sessionPOJO = (SessionPOJO) result.next();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return sessionPOJO;
	}

	//TODO to shift this method in Session
	
	public SessionPOJO readSessionPOJOByEvent(Event event) {
		SessionPOJO sessionPOJO = null;
		String eventId = null;
		try {
			db = getDbConnection();
			if (event != null) {
				eventId = event.getSessionId();
				ObjectSet<SessionPOJO> result = db
						.get(new SessionPOJO(eventId));
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						sessionPOJO = (SessionPOJO) result.next();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return sessionPOJO;
	}

	
	
	public VenuePOJO readVenuePOJO(String venueId) {
		VenuePOJO venuePOJO = null;
		try {
			if (venueId != null) {
				ObjectSet<VenuePOJO> result = db.get(new VenuePOJO(venueId));
				while (result.hasNext()) {
					venuePOJO = result.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return venuePOJO;
	}

	//TODO to shift this method in Session
	
	public List readSessionSpeakerMapPOJO() {
		List sessSpkMapList = null;
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		sessSpkMapList = new ArrayList();
		try {
			ObjectSet<SessionSpeakerMapPOJO> result = db
					.get(new SessionSpeakerMapPOJO());
			while (result.hasNext()) {
				sessionSpeakerMapPOJO = result.next();
				sessSpkMapList.add(sessionSpeakerMapPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return sessSpkMapList;
	}

	//TODO to shift this method in User
	
	public List readUserPOJO() {
		List userPOJOList = null;
		UserPOJO userPOJO = null;
		userPOJOList = new ArrayList();
		try {
			db = getDbConnection();
			ObjectSet<UserPOJO> result = db.get(new UserPOJO());
			while (result.hasNext()) {
				userPOJO = result.next();
				userPOJOList.add(userPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return userPOJOList;
	}

	//TODO to shift this method in User
	
	public UserPOJO readUserPOJOById(Event event) {
		UserPOJO userPOJO = null;
		String scUserId = null;
		try {
			if (event != null) {
				scUserId = event.getScUserId();
				db = getDbConnection();
				ObjectSet<UserPOJO> result = db.get(new UserPOJO(scUserId,
						null, null, null));
				while (result.hasNext()) {
					userPOJO = result.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return userPOJO;
	}

	//TODO to shift this method in Profile
	public List readProfilePOJO() {
		List profilePOJOList = null;
		ProfilePOJO profilePOJO = null;
		profilePOJOList = new ArrayList();
		try {
			db = getDbConnection();
			ObjectSet<ProfilePOJO> result = db.get(new ProfilePOJO());
			while (result.hasNext()) {
				profilePOJO = result.next();
				profilePOJOList.add(profilePOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return profilePOJOList;
	}

	//TODO to shift this method in Social Media
	public SMProfilePOJO readSMProfilePOJO(String profileId) {
		SMProfilePOJO smProfilePOJO = null;
		try {
			db = getDbConnection();
			if (profileId != null) {
				ObjectSet<SMProfilePOJO> result = db.get(new SMProfilePOJO(
						profileId));
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						SMProfilePOJO smProfPOJO = result.next();
						if (smProfPOJO.getSmpProfileId() != null
								&& smProfPOJO.getSmpProfileId().equals(
										profileId)) {
							smProfilePOJO = smProfPOJO;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return smProfilePOJO;
	}

	//TODO to shift this method in Session
	public List readSessionCommentPOJO() {
		List sessionCommentList = null;
		SessionCommentPOJO sessionCommentPOJO = null;
		sessionCommentList = new ArrayList();
		String scSessionId = null;
		try {
			db = getDbConnection();
			ObjectSet<SessionCommentPOJO> result = db
					.get(new SessionCommentPOJO());
			if (!result.isEmpty()) {
				while (result.hasNext()) {
					sessionCommentPOJO = result.next();
					sessionCommentList.add(sessionCommentPOJO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return sessionCommentList;
	}

	//TODO to shift this method in Session
	public SessionCommentPOJO readSessionCommentPOJOById(Event event) {
		SessionCommentPOJO sessionCommentPOJO = null;
		String scSessionId = null;
		try {
			db = getDbConnection();
			if (event != null) {
				scSessionId = event.getSessionId();
				ObjectSet<SessionCommentPOJO> result = db
						.get(new SessionCommentPOJO(scSessionId));
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						sessionCommentPOJO = result.next();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return sessionCommentPOJO;
	}

	//TODO to shift this method in Session
	public boolean isUserSessionMapExist(Event event) {
		boolean isUserSessionMapExist = false;
		UserSessionMapPOJO userSessionMapPOJO = null;
		String userId = null;
		String sessionId = null;

		try {
			db = getDbConnection();
			if (event != null) {
				userId = event.getUserId();
				sessionId = event.getSessionId();
				ObjectSet<UserSessionMapPOJO> result = db
						.get(new UserSessionMapPOJO(userId, sessionId));
				while (result.hasNext()) {
					// userSessionMapPOJO = result.next();
					isUserSessionMapExist = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return isUserSessionMapExist;
	}
	
	//TODO to shift this method in Session
	public boolean saveUserSessionMapPOJO(Event event) {
		boolean isUserSessionMapPOJOSave = false;
		String usmId = null;
		UserSessionMapPOJO userSessionMapPOJO = null;

		try {
			db = getDbConnection();
			if (event != null) {
				userSessionMapPOJO = new UserSessionMapPOJO();
				if (userSessionMapPOJO != null) {
					if (userSessionMapPOJO.getUsmId() == null) {
						usmId = getNewId();
						userSessionMapPOJO.setUsmId(usmId);
					}
					if (event.getUserId() != null) {
						userSessionMapPOJO.setUsmUserId(event.getUserId());
					}
					if (event.getSessionId() != null) {
						userSessionMapPOJO
								.setUsmSessionId(event.getSessionId());
					}
					userSessionMapPOJO.setUsmStatus("Y");

					db.store(userSessionMapPOJO);
					db.commit();
					isUserSessionMapPOJOSave = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isUserSessionMapPOJOSave;
	}

	public boolean saveUserEventMapPOJO(ObjectContainer db,Event event) {
		boolean isUserEventMapPOJOSave = false;
		boolean dataExist = false;
		String uemId = null;
		UserEventMapPOJO userEventMapPOJO = null;
		boolean isEmailIDExist = false;

		try {

			dataExist = isUserAlreadyJoined(db, event);
			
			if(!dataExist){
			if (event != null) {
				userEventMapPOJO = new UserEventMapPOJO();
				if (userEventMapPOJO.getUemId() == null) {
					uemId = getNewId();
					userEventMapPOJO.setUemId(uemId);
				}
				if (event.getEventId() != null) {
					userEventMapPOJO.setUemEventId(event.getEventId());
				}
				if (event.getUserId() != null) {
					userEventMapPOJO.setUemUserId(event.getUserId());
				}
				if (event.getJoinEventDate() != null) {
					userEventMapPOJO.setUemDate(event.getJoinEventDate());
				}
				db.store(userEventMapPOJO);
				db.commit();
				isUserEventMapPOJOSave = true;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return isUserEventMapPOJOSave;
	}
	
	
	public boolean saveEventPOJO(ObjectContainer db,Event event) {
		boolean isEventPOJOSave = false;				
		EventPOJO eventPOJO = null;
		String eventId = null;
		try {
			
			eventPOJO = readEventPOJOById(db,event);	
			if(eventPOJO==null){
			if (event != null) {
				eventPOJO = new EventPOJO();				
				if (eventPOJO.getEventId() == null){
					eventId = getNewId();
					eventPOJO.setEventId(eventId);
				}
			}
			}
				if (event.getEventName() != null) {
					eventPOJO.setEventName(event.getEventName());
				}
				
				if (event.getEventStartDate() != null) {
					eventPOJO.setEventStartDate(event.getEventStartDate());
				}
				
				if (event.getEventStartTime() != null) {
					eventPOJO.setEventStartTime(event.getEventStartTime());
				}
				
				if (event.getEventEndDate() != null) {
					eventPOJO.setEventEndDate(event.getEventEndDate());
				}
				
				if (event.getEventEndTime() != null) {
					eventPOJO.setEventEndTime(event.getEventEndTime());
				}
				
				if (event.getEventCity() != null) {
					eventPOJO.setEventCity(event.getEventCity());
				}
				if (event.getEventCountry() != null) {
					eventPOJO.setEventCountry(event.getEventCountry());
				}
				if (event.getEventDescription()!= null) {
					eventPOJO.setEventDescription(event.getEventDescription());
				}
				if (event.getEventLocation()!= null) {
					eventPOJO.setEventLocation(event.getEventLocation());
				}
				if (event.getEventOrganizedBy()!= null) {
					eventPOJO.setEventOrganizedBy(event.getEventOrganizedBy());
				}
				if (event.getEventNoOfDays()!= null) {
					eventPOJO.setEventNoOfDays(event.getEventNoOfDays());
				}
				if (event.getEventWebsite()!= null) {
					eventPOJO.setEventWebsite(event.getEventWebsite());
				}
				if (event.getEventTag()!= null) {
					eventPOJO.setEventTag(event.getEventTag());
				}
				if (event.getEventTimeZone()!= null) {
					eventPOJO.setEventTimeZone(event.getEventTimeZone());
				}
				if (event.getEventState()!= null) {
					eventPOJO.setEventState(event.getEventState());
				}
				eventPOJO.setEventLikeCount("0");
				db.store(eventPOJO);
				db.commit();
				isEventPOJOSave = true;		
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return isEventPOJOSave;
	}
	
	
	public boolean isUserAlreadyJoined(ObjectContainer db,Event event) {
		boolean dataExist = false;
		String eventId = null;
		String userId = null;
		UserEventMapPOJO userEventMapPOJO = null;
		try {
			
			if (event != null) {
				
				eventId = event.getEventId();
				userId = event.getUserId();
				
				ObjectSet<UserEventMapPOJO> result = db.get(new UserEventMapPOJO(
						userId,eventId));
				if(!result.isEmpty()){
					dataExist = true;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//db.close();
		}
		return dataExist;
		
	}
	public boolean delete(BusinessObject object) throws EventPortalException {
		boolean result = false;
		boolean sessionExsist = false;
		Event event = null;		
		String hiddenAction = null;
		event = (Event) object;
		hiddenAction = event.getHiddenAction();

		if (hiddenAction != null) {
			try{
				db = getDbConnection();
				if( event.getEventId()!=null){
					sessionExsist = SessionDB4O.isDataEventExist(db, event.getEventId());
				}
				if(sessionExsist==false){
					if(hiddenAction.equals("DELETEEVENT")) {					
						result = deleteEvent(db, event);					
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return result;
	}
	
	public boolean deleteEvent(ObjectContainer db,Event event) {
		boolean isEventPOJODelete = false;
		EventPOJO eventPOJO = null;		
		String eventId = null;
		try {
			if (event != null) {			
				eventId = event.getEventId();
				ObjectSet<EventPOJO> eventPOJOResults = db.get(new EventPOJO(eventId));
				if (!eventPOJOResults.isEmpty()) {
					eventPOJO = (EventPOJO) eventPOJOResults.next();
					db.delete(eventPOJO);
					db.commit();
					isEventPOJODelete = true;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return isEventPOJODelete;
	}
	
//TODO to shift this method in Session
	public boolean saveSessionCommentPOJO(Event event) {
		boolean isSessionCommentPOJOSave = false;
		SessionCommentPOJO sessionCommentPOJO = null;
		boolean isEmailIDExist = false;
		String scId = null;

		try {
			db = getDbConnection();
			if (event != null) {
				sessionCommentPOJO = new SessionCommentPOJO();
				if (sessionCommentPOJO.getScId() == null) {
					scId = getNewId();
					sessionCommentPOJO.setScId(scId);
				}
				if (event.getScUserId() != null) {
					sessionCommentPOJO.setScUserId(event.getScUserId());
				}
				if (event.getScSessionId() != null) {
					sessionCommentPOJO.setScSessionId(event.getScSessionId());
				}
				if (event.getSessionCommentValue() != null) {
					sessionCommentPOJO.setScComment(event
							.getSessionCommentValue());
				}
				if (event.getSessionCommentTime() != null) {
					sessionCommentPOJO.setScTime(event.getSessionCommentTime());
				}
				if (event.getSessionCommentLikeCount() > 0) {
					sessionCommentPOJO.setScCommentLikeCount(event
							.getSessionCommentLikeCount());
				}
				db.store(sessionCommentPOJO);
				db.commit();
				isSessionCommentPOJOSave = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isSessionCommentPOJOSave;
	}
	
	//TODO to shift this method in Session
	public boolean updateSessionPOJO(Event event) {
		boolean isSessionPOJOUpdate = false;
		String sessionId = null;
		SessionPOJO sessionPOJO = null;
		try {
			db = getDbConnection();
			if (event != null) {
				sessionId = event.getSessionId();
				ObjectSet<SessionPOJO> result = db.get(new SessionPOJO(
						sessionId));
				while (result.hasNext()) {
					sessionPOJO = result.next();
					if (sessionPOJO.getSessionLikeCount() > 0) {
						sessionPOJO.setSessionLikeCount(event
								.getSessionLikeCount());
					}
				}
				db.store(sessionPOJO);
				db.commit();
				isSessionPOJOUpdate = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isSessionPOJOUpdate;
	}

	public boolean updateEventPOJO(Event event) {
		boolean isEventPOJOUpdate = false;
		String eventId = null;
		EventPOJO eventPOJO = null;
		try {
			db = getDbConnection();
			if (event != null) {
				eventId = event.getEventId();
				ObjectSet<EventPOJO> result = db.get(new EventPOJO(eventId));
				while (result.hasNext()) {
					eventPOJO = result.next();
					if (Integer.parseInt(eventPOJO.getEventLikeCount()) > 0) {
						eventPOJO.setEventLikeCount(eventPOJO
								.getEventLikeCount());
					}
				}
				db.store(eventPOJO);
				db.commit();
				isEventPOJOUpdate = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isEventPOJOUpdate;
	}

	
	//TODO to shift this method in Session
	
	public boolean updateUserSessionMapPOJO(Event event) {
		boolean isUserSessionMapPOJOUpdate = false;
		String userId = null;
		String sessionId = null;
		UserSessionMapPOJO userSessionMapPOJO = null;
		try {
			db = getDbConnection();
			if (event != null) {
				userId = event.getUserId();
				sessionId = event.getSessionId();
				ObjectSet<UserSessionMapPOJO> result = db
						.get(new UserSessionMapPOJO(userId, sessionId));
				while (result.hasNext()) {
					userSessionMapPOJO = result.next();
					if (!userSessionMapPOJO.getUsmStatus().equals("Y")) {
						userSessionMapPOJO.setUsmStatus("Y");
					}
					if (userSessionMapPOJO.getUsmStatus().equals("Y")) {
						userSessionMapPOJO.setUsmStatus("N");
					}

				}
				db.store(userSessionMapPOJO);
				db.commit();
				isUserSessionMapPOJOUpdate = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isUserSessionMapPOJOUpdate;
	}

	public boolean deleteUserEventMapPOJO(Event event) {
		boolean isUserEventMapPOJODelete = false;
		UserEventMapPOJO userEventMapPOJO = null;
		String userId = null;
		String eventId = null;
		try {
			db = getDbConnection();
			if (event != null) {
				userId = event.getUserId();
				eventId = event.getEventId();
				ObjectSet<UserEventMapPOJO> userEventMapPOJOResults = db
						.get(new UserEventMapPOJO(userId, eventId));
				if (!userEventMapPOJOResults.isEmpty()) {
					userEventMapPOJO = (UserEventMapPOJO) userEventMapPOJOResults
							.next();
					db.delete(userEventMapPOJO);
					db.commit();
					isUserEventMapPOJODelete = true;
				}
			}
		} catch (EventPortalException e1) {
			e1.printStackTrace();
		} finally {
			db.close();
		}
		return isUserEventMapPOJODelete;
	}

	
private EventPOJO getEventPOJOByEventId(ObjectContainer dbContainer, String eventId){
		
	EventPOJO eventPOJO = null;
		
		try{
			
			ObjectSet<EventPOJO> eventPOJOSet = dbContainer.get(new EventPOJO(eventId));
			
			if(!eventPOJOSet.isEmpty()){			
				eventPOJO = eventPOJOSet.next();
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return eventPOJO;
	}
	
public Event convertEventPOJOToEvent(EventPOJO eventPOJO, Event event){
	
		
		if(event == null){
			event = new Event();
		}
		
		if(eventPOJO != null){
			
			if(eventPOJO.getEventId() != null ){
				event.setEventId(eventPOJO.getEventId());
			}
			
			if(eventPOJO.getEventName() != null ){
				event.setEventName(eventPOJO.getEventName());
			}
			
			if(eventPOJO.getEventStartDate()!= null ){
				event.setEventStartDate(eventPOJO.getEventStartDate());
			}
			
			if(eventPOJO.getEventEndDate()!= null ){
				event.setEventEndDate(eventPOJO.getEventEndDate());
			}
			
			if(eventPOJO.getEventStartTime() != null ){
				event.setEventStartTime(eventPOJO.getEventStartTime());
			}
			
			if(eventPOJO.getEventEndTime() != null ){
				event.setEventEndTime(eventPOJO.getEventEndTime());
			}
			
			if(eventPOJO.getEventDescription() != null ){
				event.setEventDescription(eventPOJO.getEventDescription());
			}
			
			if(eventPOJO.getEventLocation() != null ){
				event.setEventLocation(eventPOJO.getEventLocation());
			}
			
			if(eventPOJO.getEventCity() != null ){
				event.setEventCity(eventPOJO.getEventCity());
			}
			
			if(eventPOJO.getEventState() != null ){
				event.setEventState(eventPOJO.getEventState());
			}
			
			if(eventPOJO.getEventCountry() != null ){
				event.setEventCountry(eventPOJO.getEventCountry());
			}
		
			if(eventPOJO.getEventOrganizedBy() != null ){
				event.setEventOrganizedBy(eventPOJO.getEventOrganizedBy());
			}
			
			if(eventPOJO.getEventWebsite() != null ){
				event.setEventWebsite(eventPOJO.getEventWebsite());
			}
			if(eventPOJO.getEventTimeZone()!= null ){
				event.setEventTimeZone(eventPOJO.getEventTimeZone());
			}
			if(eventPOJO.getEventTag()!= null ){				
				event.setEventTag("#" + eventPOJO.getEventTag());
			}
			
			if(eventPOJO.getEventNoOfDays()!= null ){
				event.setEventNoOfDays(eventPOJO.getEventNoOfDays());
			}
			if(eventPOJO.getEventLikeCount()!= null ){
				event.setEventLikeCountSum(Integer.parseInt(eventPOJO.getEventLikeCount()));
			}
		
		}
		
		return event;
	}

public EventPOJO convertEventToEventPOJO(Event event, EventPOJO eventPOJO){
	
	
	if(event != null){
		
		if(event.getEventId() != null ){
			eventPOJO.setEventId(event.getEventId());
		}
		
		if(event.getEventName() != null ){
			eventPOJO.setEventName(event.getEventName());
		}
		
		if(event.getEventStartDate()!= null ){
			eventPOJO.setEventStartDate(event.getEventStartDate());
		}
		
		if(event.getEventEndDate()!= null ){
			eventPOJO.setEventEndDate(event.getEventEndDate());
		}
		
		if(event.getEventStartTime() != null ){
			eventPOJO.setEventStartTime(event.getEventStartTime());
		}
		
		if(event.getEventEndTime() != null ){
			eventPOJO.setEventEndTime(event.getEventEndTime());
		}
		
		if(event.getEventDescription() != null ){
			eventPOJO.setEventDescription(event.getEventDescription());
		}
		
		if(event.getEventLocation() != null ){
			eventPOJO.setEventLocation(event.getEventLocation());
		}
		
		if(event.getEventCity() != null ){
			eventPOJO.setEventCity(event.getEventCity());
		}
		
		if(event.getEventState() != null ){
			eventPOJO.setEventState(event.getEventState());
		}
		
		if(event.getEventCountry() != null ){
			eventPOJO.setEventCountry(event.getEventCountry());
		}
	
		
		if(event.getEventOrganizedBy() != null ){
			eventPOJO.setEventOrganizedBy(event.getEventOrganizedBy());
		}
		
		if(event.getEventWebsite() != null ){
			eventPOJO.setEventWebsite(event.getEventWebsite());
		}
	
	}
	
	return eventPOJO;
}

	public Collection sessionInformation(ObjectContainer db, Event event)
			throws EventPortalException {
			List eventSummaryList = null;
			String sessionId = null;
			String sessionVenuId = null;
			String usmProfileId = null;
			Profile profile = null;
			eventSummaryList = new ArrayList();
			if (event != null) {
			sessionId = event.getSessionId();
			}
			String evenId = null;
			try {
			//db = getDbConnection();
			event = new Event();
			
			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(sessionId,
			    null));
			if (!results.isEmpty()) {
			  while (results.hasNext()) {
			    SessionPOJO sessionPOJO = (SessionPOJO) results.next();
			    evenId = sessionPOJO.getSessionEventId();
			    event.setSessionId(sessionPOJO.getSessionId());
			    event.setSessionName(sessionPOJO.getSessionName());
			    event
			        .setSessionStartDate(sessionPOJO
			            .getSessionStartDate());
			    event.setSessionEndDate(sessionPOJO.getSessionEndDate());
			    event
			        .setSessionStartTime(sessionPOJO
			            .getSessionStartTime());
			    event.setSessionEndTime(sessionPOJO.getSessionEndTime());
			    event.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
			    event.setSessionVenueName(getVenuName(sessionPOJO
			        .getSessionVenueId(), db));
			    event.setSessionTag("#" + sessionPOJO.getSessionTag());
			  }
			}
			if (evenId != null) {
			  ObjectSet<EventPOJO> evenresults = db
			      .get(new EventPOJO(evenId));
			  if (!evenresults.isEmpty()) {
			    while (evenresults.hasNext()) {
			      EventPOJO eventPOJO = (EventPOJO) evenresults.next();
			      event = convertEventPOJOToEvent(eventPOJO,event);           
			    }
			  }
			
			  eventSummaryList.add(event);
			
			}
			} catch (Exception e) {
			e.printStackTrace();
			} finally {
			//db.close();
			}
			
			return eventSummaryList;
		}
	
	
	
	public int totalSessionLikeCount(ObjectContainer db, String eventId){
		int totalSessLikeCount = 0;
		try {
			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(eventId));
			if (!results.isEmpty()) {
				  while (results.hasNext()) {
				    SessionPOJO sessionPOJO = (SessionPOJO) results.next();
				    totalSessLikeCount += sessionPOJO.getSessionLikeCount();
				  }
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return totalSessLikeCount;		
	}
				
	
}
