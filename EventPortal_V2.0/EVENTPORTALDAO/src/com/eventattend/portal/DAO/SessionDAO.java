package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.eventattend.portal.DAO.Blob.BlobImageRetrival;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.Speaker;

import com.eventattend.portal.bo.Session;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;

import com.eventattend.portal.model.db4o.EventPOJO;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.SMProfilePOJO;
import com.eventattend.portal.model.db4o.SessionCommentPOJO;
import com.eventattend.portal.model.db4o.SessionPOJO;
import com.eventattend.portal.model.db4o.SessionSpeakerMapPOJO;
import com.eventattend.portal.model.db4o.UserEventMapPOJO;
import com.eventattend.portal.model.db4o.UserPOJO;
import com.eventattend.portal.model.db4o.UserSessionMapPOJO;
import com.eventattend.portal.model.db4o.VenuePOJO;
import com.eventattend.portal.model.db4o.query.EventDB4O;
import com.eventattend.portal.model.db4o.query.SessionDB4O;
import com.eventattend.portal.model.db4o.query.SessionSpeakerMapDB4O;

public class SessionDAO extends DataAccessObject {

	ObjectContainer db = null;

	/**
	 * @method Common method to update the records
	 * @param BusinessObject
	 * @return boolean
	 * @throws EventPortalException
	 */
	public Collection read(BusinessObject object) throws EventPortalException {

		Collection resultCollection = null;

		Session session = (Session) object;
		String hiddenAction = session.getHiddenAction();
		if (hiddenAction != null) {

			try {
				db = getDbConnection();

				if (hiddenAction.equals("getSessionByEventId")) {
					resultCollection = getSpeakerUnAssignedSessionByEventId(session); 
				} else if (hiddenAction.equals("SESSIONATTENDEE")) {

					resultCollection = sessionAttendee(db, session);

				} else if (hiddenAction.equals("SESSIONLISTFOREVENT")) {

					resultCollection = getSessionListForEvent(db, session);

				} else if (hiddenAction.equals("SESSIONLISTFORSPEAKER")) {

					resultCollection = sessionListForSpeaker(db, session);

				} else if (hiddenAction.equals("SESSIONUSERLIST")) {

					resultCollection = usersOfSession(session.getSessionList(),
							db);

				} else if (hiddenAction.equals("LIVESESSIONUSERLIST")) {

					resultCollection = usersOfLiveSession(session
							.getSessionList(), db);

				} else if (hiddenAction.equals("SPEAKERINFORMATION")) {

					resultCollection = speakerInformation(db, session);

				} else if (hiddenAction.equals("SPEAKERFORINFO")) {
					List speakerForList = new ArrayList();

					String speakerFor = eventSessionName(session.getUserId(),
							db);
					speakerForList.add(speakerFor);

					resultCollection = speakerForList;

				} else if (hiddenAction.equals("SPEAKERIDMAP")) {
					resultCollection = speakerIdMap(db, session);
				} else if (hiddenAction.equals("SPEAKERIDLIST")) {
					resultCollection = speakerIdList(db, session);
				} else if (hiddenAction.equals("SESSIONCOMMENTS")) {
					resultCollection = sessionCommentsList(db, session);
				} else if (hiddenAction.equals("SESSIONSEARCHTAG")) {
					resultCollection = sessionSearchTag(db, session);
				} else if (hiddenAction.equals("USERINLIVESESSION")) {
					resultCollection = userInLiveSession(db, session);
				} else if (hiddenAction.equals("SESSIONS")) {
					resultCollection = sessions(db, session);
				} else if (hiddenAction.equals("SESSION")) {
					resultCollection = session(db, session);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return resultCollection;
	}

	private Collection session(ObjectContainer db2, Session session) {
		EventPOJO eventPOJO = null;
		String sessionId = null;
		SessionPOJO sessionPOJO = null;
		List<Session> sessionList = null;
		ObjectSet<EventPOJO> eventPOJOResultSet = null;
		ObjectSet<SessionPOJO> sessionPOJOResultSet = null;
		sessionId = session.getSelectedSession();
		sessionPOJOResultSet = SessionDB4O.getSessionDetailForSessionId(db2,
				sessionId);
		if (sessionPOJOResultSet != null) {
			sessionList = new ArrayList<Session>();
			while (sessionPOJOResultSet.hasNext()) {
				session = new Session();
				sessionPOJO = sessionPOJOResultSet.next();
				session.setSessionId(sessionPOJO.getSessionId());
				session.setSessionName(sessionPOJO.getSessionName());
				session.setSessionStartDate(sessionPOJO.getSessionStartDate());
				session.setSessionStartTime(sessionPOJO.getSessionStartTime());
				session.setSessionEndDate(sessionPOJO.getSessionEndDate());
				session.setSessionEndTime(sessionPOJO.getSessionEndTime());
				session.setSessionVenueId(sessionPOJO.getSessionVenueId());
				session.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
				session.setSessionVenueName(getVenuName(sessionPOJO
						.getSessionVenueId(), db));
				session.setSessionTag(sessionPOJO.getSessionTag());
				session.setSessionEventId(sessionPOJO.getSessionEventId());
				eventPOJOResultSet = EventDB4O.getEventDetailForEventId(db2,
						sessionPOJO.getSessionEventId());
				if (eventPOJOResultSet != null) {
					eventPOJO = eventPOJOResultSet.next();
					session.setSessionEventName(eventPOJO.getEventName());
				}
				sessionList.add(session);
			}
		}
		return sessionList;
	}

	private Collection sessions(ObjectContainer db2, Session session) {
		EventPOJO eventPOJO = null;
		SessionPOJO sessionPOJO = null;
		List<Session> sessionList = null;
		ObjectSet<EventPOJO> eventPOJOResultSet = null;
		ObjectSet<SessionPOJO> sessionPOJOResultSet = null;
		sessionPOJOResultSet = SessionDB4O.getSessions(db2);
		if (sessionPOJOResultSet != null) {
			sessionList = new ArrayList<Session>();
			while (sessionPOJOResultSet.hasNext()) {
				session = new Session();
				sessionPOJO = sessionPOJOResultSet.next();
				session.setSessionId(sessionPOJO.getSessionId());
				session.setSessionName(sessionPOJO.getSessionName());
				session.setSessionStartDate(sessionPOJO.getSessionStartDate());
				session.setSessionStartTime(sessionPOJO.getSessionStartTime());
				session.setSessionEndDate(sessionPOJO.getSessionEndDate());
				session.setSessionEndTime(sessionPOJO.getSessionEndTime());
				session.setSessionVenueId(sessionPOJO.getSessionVenueId());
				session.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
				session.setSessionVenueName(getVenuName(sessionPOJO
						.getSessionVenueId(), db));
				session.setSessionTag(sessionPOJO.getSessionTag());
				session.setSessionEventId(sessionPOJO.getSessionEventId());
				eventPOJOResultSet = EventDB4O.getEventDetailForEventId(db2,
						sessionPOJO.getSessionEventId());
				if (eventPOJOResultSet != null) {
					eventPOJO = eventPOJOResultSet.next();
					session.setSessionEventName(eventPOJO.getEventName());
				}
				sessionList.add(session);
			}
		}
		return sessionList;
	}

	private Collection sessionListForSpeaker(ObjectContainer db2,
			Session session) {

		String speakerUserId = null;
		List<Speaker> sessionListForspeakerList = null;
		Speaker speaker = null;

		EventPOJO eventPOJO = null;
		SessionPOJO sessionPOJO = null;
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;

		ObjectSet<EventPOJO> eventPOJOResultSet = null;
		ObjectSet<SessionPOJO> sessionPOJOResultSet = null;
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResultSet = null;

		String sessionId = null;

		speakerUserId = session.getSpeakerUserId();

		sessionSpeakerMapPOJOResultSet = SessionSpeakerMapDB4O
				.getSessionIdForSpeakerUserId(db2, speakerUserId);

		if (sessionSpeakerMapPOJOResultSet != null) {
			sessionListForspeakerList = new ArrayList<Speaker>();

			while (sessionSpeakerMapPOJOResultSet.hasNext()) {

				speaker = new Speaker();

				sessionSpeakerMapPOJO = sessionSpeakerMapPOJOResultSet.next();

				sessionId = sessionSpeakerMapPOJO.getSsmSessionId();

				speaker.setSessionId(sessionId);

				sessionPOJOResultSet = SessionDB4O
						.getSessionDetailForSessionId(db2, sessionId);

				if (sessionPOJOResultSet != null) {

					sessionPOJO = sessionPOJOResultSet.next();

					speaker.setSessionName(sessionPOJO.getSessionName());

					eventPOJOResultSet = EventDB4O.getEventDetailForEventId(
							db2, sessionPOJO.getSessionEventId());

					if (eventPOJOResultSet != null) {
						eventPOJO = eventPOJOResultSet.next();
						speaker.setEventName(eventPOJO.getEventName());
					}

					sessionListForspeakerList.add(speaker);

				}
			}
		}

		return sessionListForspeakerList;
	}

	private Collection speakerInformation(ObjectContainer db, Session session) {

		return null;
	}

	/**
	 * @method- save - To save/update the list
	 * @param object
	 * @return Collection
	 * @throws EventPortalException
	 */
	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		Session session = null;
		String hiddenAction = null;
		session = (Session) object;
		hiddenAction = session.getHiddenAction();

		if (hiddenAction != null) {
			try {
				db = getDbConnection();

				if (hiddenAction.equals("SAVESESSIONSPEAKERMAP")) {

					result = saveSessionSpeakerMapPOJO(db, session);

				} else if (hiddenAction.equals("SAVESESSIONIDFORSPEAKER")) {

					result = saveSessionIdForSpeaker(db, session);

				} else if (hiddenAction.equals("ADDSESSION")) {

					result = addSession(db, session);

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return result;
	}

	/**
	 * @method- delete - To delete the list
	 * @param object
	 * @return Collection
	 * @throws EventPortalException
	 */
	public boolean delete(BusinessObject object) throws EventPortalException {
		boolean result = false;
		Session session = null;
		String hiddenAction = null;
		session = (Session) object;
		hiddenAction = session.getHiddenAction();

		if (hiddenAction != null) {
			try {
				db = getDbConnection();

				if (hiddenAction.equals("REMOVESESSIONIDFORSPEAKER")) {

					result = removeSessionIdForSpeaker(db, session);

				} else if (hiddenAction.equals("DELETESESSION")) {
					result = deleteSession(db, session);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return result;
	}

	private boolean saveSessionIdForSpeaker(ObjectContainer dbContainer,
			Session session) throws DatabaseException {

		String ssmId = null;
		String sessionId = null;
		String userId = null;

		boolean saveResult = false;
		boolean dataExist = false;

		sessionId = session.getSessionId();
		userId = session.getSpeakerUserId();

		dataExist = SessionSpeakerMapDB4O.isDataExist(dbContainer, sessionId,
				userId);

		if (!dataExist) {

			ssmId = getNewId();

			saveResult = SessionSpeakerMapDB4O.saveSessionSpeakerMapPOJO(
					dbContainer, ssmId, sessionId, userId);

		}

		return saveResult;

	}

	private boolean removeSessionIdForSpeaker(ObjectContainer dbContainer,
			Session session) throws DatabaseException {

		String ssmId = null;
		String sessionId = null;
		String userId = null;

		boolean deleteResult = false;
		boolean dataExist = false;

		sessionId = session.getSessionId();
		userId = session.getSpeakerUserId();

		dataExist = SessionSpeakerMapDB4O.isDataExist(dbContainer, sessionId,
				userId);

		if (dataExist) {

			deleteResult = SessionSpeakerMapDB4O.deleteSessionSpeakerMapPOJO(
					dbContainer, sessionId, userId);

		}

		return deleteResult;

	}

	private boolean deleteSession(ObjectContainer dbContainer, Session session)
			throws DatabaseException {

		String sessionId = null;
		boolean deleteResult = false;
		boolean dataExist = false;
		sessionId = session.getSelectedSession();
		dataExist = SessionDB4O.isDataExist(dbContainer, sessionId);
		if (dataExist) {
			deleteResult = SessionDB4O
					.deleteSessionPOJO(dbContainer, sessionId);
		}
		return deleteResult;

	}

	/**
	 * @method userInLiveSession - To check whether the user is in the live
	 *         session or not
	 * @param event
	 * @return boolean
	 */
	public Collection userInLiveSession(ObjectContainer db, Session session) {
		Collection userInLiveSessionList = new ArrayList();
		UserSessionMapPOJO userSessionMapPOJO = null;

		try {
			// db = getDbConnection();
			if (session != null) {
				userSessionMapPOJO = new UserSessionMapPOJO(
						session.getUserId(), session.getSessionId());
				ObjectSet<UserSessionMapPOJO> results = db
						.get(userSessionMapPOJO);
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						userSessionMapPOJO = (UserSessionMapPOJO) results
								.next();
						if (userSessionMapPOJO.getUsmStatus().equals("Y")) {
							session = new Session();
							session.setUserInLiveSession(true);
							userInLiveSessionList.add(session);
						} else {
							session.setUserInLiveSession(false);
							userInLiveSessionList.add(session);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// db.close();
		}
		return userInLiveSessionList;
	}

	/**
	 * @method sessionSearchTagList - To get the sessionTag for a session
	 * @param session
	 * @return Collection
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public Collection sessionSearchTag(ObjectContainer db, Session session) {
		Collection sessionSearchTagList = new ArrayList();
		SessionPOJO sessionPOJO = null;

		try {
			// db = getDbConnection();
			sessionPOJO = new SessionPOJO(session.getSessionId(), null);
			ObjectSet<SessionPOJO> results = db.get(sessionPOJO);
			if (!results.isEmpty()) {
				sessionPOJO = (SessionPOJO) results.next();
				session = new Session();
				session.setSessionTag("#" + sessionPOJO.getSessionTag());
				// event.setSessionTag(sessionPOJO.getSessionTag());
				sessionSearchTagList.add(session);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return sessionSearchTagList;
	}

	/**
	 * @method sessionCommentsList - To get session comments list
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection sessionCommentsList(ObjectContainer db, Session session)
			throws EventPortalException {
		List eventDetailsList = null;
		List eventSessionCommentList = null;
		List eventSocialCheckList = null;
		String sessionId = null;
		String userId = null;
		String profileId = null;
		eventDetailsList = new ArrayList();
		eventSessionCommentList = new ArrayList();
		eventSocialCheckList = new ArrayList();
		Profile profile = null;
		if (session != null) {
			sessionId = session.getSessionId();
			userId = session.getUserId();
		}
		try {
			// db = getDbConnection();
			ObjectSet<SessionCommentPOJO> sessionCommentResult = db
					.get(new SessionCommentPOJO(sessionId));
			if (!sessionCommentResult.isEmpty()) {
				while (sessionCommentResult.hasNext()) {
					SessionCommentPOJO sessionCommentPOJO = (SessionCommentPOJO) sessionCommentResult
							.next();
					session = new Session();
					profile = new Profile();
					session.setScId(sessionCommentPOJO.getScId());
					session.setScComment(sessionCommentPOJO.getScComment());
					session.setScSessionId(sessionCommentPOJO.getScSessionId());
					session.setScUserId(sessionCommentPOJO.getScUserId());
					// get the like count for session comment from this list

					ObjectSet<UserPOJO> smUserProfilResult = db
							.get(new UserPOJO(sessionCommentPOJO.getScUserId()));
					if (!smUserProfilResult.isEmpty()) {
						while (smUserProfilResult.hasNext()) {
							UserPOJO userPOJO = (UserPOJO) smUserProfilResult
									.next();
							profileId = userPOJO.getUserProfileId();
						}
					}
					// to get the profile details.
					Map profileIdMap = new HashMap();
					profileIdMap.put(profileId, profileId);

					Profile userProfile = null;

					ProfileDAO profileDAO = new ProfileDAO();

					userProfile = profileDAO.getProfileData(db, profileId);
					session.setProfile(userProfile);
					// session.setProfile((Profile)
					// eventUsersProfile(profileIdMap,db).get(0));
					session
							.setSessionLikeCount(String
									.valueOf(sessionCommentPOJO
											.getScCommentLikeCount()));
					session.setScSessionCommentTime(sessionCommentPOJO
							.getScTime());
					eventSessionCommentList.add(session);
				}
			}

			ObjectSet<UserPOJO> smUserProfilResult = db.get(new UserPOJO(
					userId, null, null, null));
			if (!smUserProfilResult.isEmpty()) {
				while (smUserProfilResult.hasNext()) {
					UserPOJO userPOJO = (UserPOJO) smUserProfilResult.next();
					profileId = userPOJO.getUserProfileId();
				}
			}

			ObjectSet<SMProfilePOJO> smProfilResult = db.get(new SMProfilePOJO(
					profileId));
			if (!smProfilResult.isEmpty()) {
				while (smProfilResult.hasNext()) {
					SMProfilePOJO smProfilePOJO = (SMProfilePOJO) smProfilResult
							.next();
					if (smProfilePOJO.getFbAllowFriendsToPost() != null) {
						if (smProfilePOJO.getFbAllowFriendsToPost().equals("N")) {
							session.setFacebookCheck("false");
						} else {
							session.setFacebookCheck("true");
						}
					}
					if (smProfilePOJO.getTwtShowTweets() != null) {
						if (smProfilePOJO.getTwtShowTweets().equals("N")) {
							session.setTwitterCheck("false");
						} else {
							session.setTwitterCheck("true");
						}
					}

					if (smProfilePOJO.getLiAllowFriendsToPost() != null) {
						if (smProfilePOJO.getLiAllowFriendsToPost().equals("N")) {
							session.setLinkedinCheck("false");
						} else {
							session.setLinkedinCheck("true");
						}
					}
					eventSocialCheckList.add(session);
				}
			}
			Collections.reverse(eventSessionCommentList);
			eventDetailsList.add(eventSessionCommentList);
			eventDetailsList.add(eventSocialCheckList);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			// db.close();
		}
		return eventDetailsList;
	}

	private Collection speakerIdMap(ObjectContainer db, Session session) {

		List speakerList = new ArrayList();
		Map speakersMap = new HashMap();

		List sessionList = null;

		Iterator it = null;
		String sessionId = null;
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapResults = null;

		try {

			sessionList = session.getSessionList();

			if (sessionList != null) {
				it = sessionList.iterator();
				while (it.hasNext()) {
					sessionId = (String) it.next();
					sessionSpeakerMapResults = db
							.get(new SessionSpeakerMapPOJO(sessionId));

					if (!sessionSpeakerMapResults.isEmpty()) {
						while (sessionSpeakerMapResults.hasNext()) {
							SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapResults
									.next();

							speakersMap.put(sessionSpeakerMapPOJO
									.getSsmSpeakerId(), "");
						}
					}

				}

			}
			speakerList.add(speakersMap);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}

		return speakerList;
	}

	private Collection speakerIdList(ObjectContainer db, Session session) {

		List speakerList = new ArrayList();
		Map speakersMap = new HashMap();

		List sessionList = null;

		Iterator it = null;
		String sessionId = null;
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapResults = null;

		try {

			sessionList = session.getSessionList();

			if (sessionList != null) {
				it = sessionList.iterator();
				while (it.hasNext()) {
					sessionId = (String) it.next();
					sessionSpeakerMapResults = db
							.get(new SessionSpeakerMapPOJO(sessionId));

					if (!sessionSpeakerMapResults.isEmpty()) {
						while (sessionSpeakerMapResults.hasNext()) {
							SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapResults
									.next();

							speakerList.add(sessionSpeakerMapPOJO
									.getSsmSpeakerId());
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}

		return speakerList;
	}

	public Collection sessionAttendee(ObjectContainer db, Session session)
			throws EventPortalException {
		String sessionId = null;
		List userProfileDetailsList = null;
		try {
			// db = getDbConnection();
			if (session != null) {
				sessionId = session.getSessionId();
			}
			List sessionList = new ArrayList();
			List eventUserList = new ArrayList();
			Map userProfileIdMap = new HashMap();
			Map speakersMap = new HashMap();
			speakersMap = speakersForSession(sessionId, db);

			userProfileDetailsList = new ArrayList();
			sessionList.add(sessionId);
			eventUserList = usersOfSession(sessionList, db);
			userProfileIdMap = eventUsersProfileIdMap(eventUserList,
					speakersMap, sessionId, db);
			userProfileDetailsList = eventUsersProfile(userProfileIdMap, db);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return userProfileDetailsList;
	}

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
								//
								if (profilePOJO.getProfileImgLocation() != null
										&& !profilePOJO.getProfileImgLocation()
												.trim().startsWith("http")) {
									// profile.setProfilePicture(retrieveUserProfileImgFile(profileId));
									profile.setProfilePicture(BlobImageRetrival
											.retrieveUserProfileImgFile(profile
													.getProfileId()));
								} else {
									profile.setProfilePicture(profilePOJO
											.getProfileImgLocation());
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

	public String eventSessionName(String profileId, ObjectContainer db) {
		String speakerFor = null;
		String userId = null;
		String[] sessionId = null;
		String eventName = null;
		int i = 0;
		try {
			// db = getDbConnection();
			ObjectSet<UserPOJO> userResults = db.get(new UserPOJO(profileId,
					"", ""));
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

	// TODO to shift this method in Session
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
								System.out.println(" >>"
										+ userSessionMapPOJO.getUsmSessionId()
										+ " >> " + sessionId);
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
	 * @method usersOfLiveSession - To get the users who is in live session
	 * @param eventSessionsList
	 * @return List
	 */

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
	 * @method Common method to update the records
	 * @param BusinessObject
	 * @return boolean
	 * @throws EventPortalException
	 */
	public boolean update(BusinessObject object) throws EventPortalException {

		boolean resultSts = false;
		db = getDbConnection();

		Session session = (Session) object;
		String hiddenAction = null;
		hiddenAction = session.getHiddenAction();
		if (hiddenAction.equals("resetLiveSessionInfo")) {

			resultSts = resetLiveSessionInfo(session);
		} else if (hiddenAction.equals("UPDATESESSIONCOMMENT")) {
			resultSts = updateSessionComment(db, session);
		}

		return resultSts;
	}

	public boolean updateSessionComment(ObjectContainer db, Session session)
			throws EventPortalException {

		boolean result = false;
		SessionCommentPOJO sessionCommentPOJO = null;
		try {
			sessionCommentPOJO = new SessionCommentPOJO();
			ObjectSet<SessionCommentPOJO> results = db.get(sessionCommentPOJO);
			sessionCommentPOJO.setScId(getNewId());
			if (session.getScUserId() != null) {
				sessionCommentPOJO.setScUserId(session.getScUserId());
			}
			if (session.getScSessionId() != null) {
				sessionCommentPOJO.setScSessionId(session.getScSessionId());
			}
			if (session.getScComment() != null) {
				sessionCommentPOJO.setScComment(session.getScComment());
			}
			if (session.getScSessionCommentTime() != null) {
				sessionCommentPOJO.setScTime(session.getScSessionCommentTime());
			}
			db.store(sessionCommentPOJO);
			db.commit();
			result = true;
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			// db.close();
		}

		return result;
	}

	/**
	 * @method Common method to check for existence of records
	 * @param BusinessObject
	 * @return boolean
	 * @throws EventPortalException
	 */

	public boolean isExist(BusinessObject object) throws EventPortalException {

		boolean resultSts = false;

		Session session = (Session) object;

		if (session.getHiddenAction().equals("resetLiveSessionInfo")) {

			resultSts = resetLiveSessionInfo(session);
		}

		return resultSts;
	}

	/**
	 * @method Method to reset the Live Session Attendees
	 * @param session
	 * @return boolean
	 * @throws EventPortalException
	 */
	private boolean resetLiveSessionInfo(Session session)
			throws EventPortalException {
		boolean resultSts = false;
		UserSessionMapPOJO userSessionMapPOJO = null;

		try {
			db = getDbConnection();
			ObjectSet<UserSessionMapPOJO> result = db
					.get(new UserSessionMapPOJO());

			if (!result.isEmpty()) {
				while (result.hasNext()) {
					userSessionMapPOJO = (UserSessionMapPOJO) result.next();
					userSessionMapPOJO.setUsmStatus("N");
					db.store(userSessionMapPOJO);
				}
				db.commit();
				resultSts = true;
			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			db.close();
		}

		return resultSts;
	}

	/**
	 * @method userInLiveSession - To check whether the user is in the live
	 *         session or not
	 * @param event
	 * @return boolean
	 */
	public boolean userInLiveSession(Session session)
			throws EventPortalException {
		boolean resultSts = false;
		UserSessionMapPOJO userSessionMapPOJO = null;

		try {
			db = getDbConnection();
			if (session != null) {
				userSessionMapPOJO = new UserSessionMapPOJO(
						session.getUserId(), session.getSessionId());
				ObjectSet<UserSessionMapPOJO> results = db
						.get(userSessionMapPOJO);
				if (!results.isEmpty()) {
					while (results.hasNext()) {

						userSessionMapPOJO = (UserSessionMapPOJO) results
								.next();

						if (userSessionMapPOJO.getUsmStatus().equals("Y")) {
							resultSts = true;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return resultSts;
	}

	/**
	 * @method getSessionByEventId - To get all the existing events
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getSessionListForEvent(ObjectContainer db, Session session)
			throws EventPortalException {

		List sessionList = null;
		String eventName = null;
		String eventId = null;

		SessionPOJO sessionPOJO = null;

		try {

			// db = getDbConnection();

			if (session.getEventId() != null) {
				eventId = session.getEventId();
			}

			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(eventId));

			if (!results.isEmpty()) {

				sessionList = new ArrayList();

				while (results.hasNext()) {

					sessionPOJO = (SessionPOJO) results.next();

					session = convertSessionPOJOToSessionBO(sessionPOJO);

					sessionList.add(session);

				}

			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			// db.close();
		}
		return sessionList;
	}

	/**
	 * @method getSessionByEventId - To get all the existing events
	 * @param event
	 * @return Collection
	 * @throws EventPortalException
	 */
	@SuppressWarnings("unchecked")
	public Collection getSpeakerUnAssignedSessionByEventId(Session session)
			throws EventPortalException {

		List sessionList = null;
		String eventName = null;
		String eventId = null;

		SessionPOJO sessionPOJO = null;

		try {

			db = getDbConnection();

			if (session.getEventId() != null) {
				eventId = session.getEventId();
			}

			ObjectSet<SessionPOJO> results = db.get(new SessionPOJO(eventId));

			if (!results.isEmpty()) {

				sessionList = new ArrayList();

				while (results.hasNext()) {

					sessionPOJO = (SessionPOJO) results.next();
					if (!sessionSpeakerAssigned(sessionPOJO.getSessionId())) {
						session = convertSessionPOJOToSessionBO(sessionPOJO);
						sessionList.add(session);
					}

				}

			}
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			db.close();
		}
		return sessionList;
	}

	public boolean sessionSpeakerAssigned(String sessionId) {
		boolean result = false;
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		ObjectSet<SessionSpeakerMapPOJO> results = db
				.get(new SessionSpeakerMapPOJO(sessionId));
		if (!results.isEmpty()) {

			while (results.hasNext()) {
				sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) results.next();
				if (sessionSpeakerMapPOJO.getSsmSpeakerId() != null) {
					result = true;
				}
			}

		}
		return result;
	}

	private Session convertSessionPOJOToSessionBO(SessionPOJO sessionPOJO) {

		Session session = null;

		if (sessionPOJO != null) {

			session = new Session();

			if (sessionPOJO.getSessionId() != null) {
				session.setSessionId(sessionPOJO.getSessionId());
			}

			if (sessionPOJO.getSessionName() != null) {
				session.setSessionName(sessionPOJO.getSessionName());
			}

			if (sessionPOJO.getSessionEventId() != null) {
				session.setEventId(sessionPOJO.getSessionEventId());
			}

			if (sessionPOJO.getSessionKeyNote() != null) {
				session.setSessionKeyNote(sessionPOJO.getSessionKeyNote());
			}

			if (sessionPOJO.getSessionLikeCount() != 0) {
				session.setSessionLikeCount(String.valueOf(sessionPOJO
						.getSessionLikeCount()));
			}

			if (sessionPOJO.getSessionStartDate() != null) {
				session.setSessionStartDate(sessionPOJO.getSessionStartDate());
			}

			if (sessionPOJO.getSessionStartTime() != null) {
				session.setSessionStartTime(sessionPOJO.getSessionStartTime());
			}

			if (sessionPOJO.getSessionEndDate() != null) {
				session.setSessionEndDate(sessionPOJO.getSessionEndDate());
			}

			if (sessionPOJO.getSessionEndTime() != null) {
				session.setSessionEndTime(sessionPOJO.getSessionEndTime());
			}

			if (sessionPOJO.getSessionTag() != null) {
				session.setSessionTag(sessionPOJO.getSessionTag());
			}

			if (sessionPOJO.getSessionVenueId() != null) {
				session.setSessionVenueId(sessionPOJO.getSessionVenueId());
			}

		}

		return session;

	}

	private List sessionUsers(ObjectContainer dbContainer, Session session) {

		UserSessionMapPOJO userSessionMapPOJO = null;

		List sessionUserIdList = null;

		String userId = null;

		try {

			if (session != null) {

				ObjectSet<UserSessionMapPOJO> userSessionMapPOJOSet = dbContainer
						.get(new UserSessionMapPOJO(session.getSessionId()));

				if (!userSessionMapPOJOSet.isEmpty()) {
					sessionUserIdList = new ArrayList();
					while (userSessionMapPOJOSet.hasNext()) {

						userSessionMapPOJO = userSessionMapPOJOSet.next();

						userId = userSessionMapPOJO.getUsmUserId();

						sessionUserIdList.add(userId);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sessionUserIdList;
	}

	public boolean addSession(ObjectContainer dbContainer, Session session) {

		boolean addSessionStatus = false;
		try {
			if (session != null) {
				addSessionStatus = saveSessionPOJO(db, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addSessionStatus = false;
		}
		return addSessionStatus;
	}

	public boolean saveSessionPOJO(ObjectContainer db, Session session) {
		boolean isSessionPOJOSave = false;
		SessionPOJO sessionPOJO = null;
		String sessionId = null;
		try {

			sessionPOJO = readSessionPOJOById(db, session);
			if (sessionPOJO == null) {
				if (session != null) {
					sessionPOJO = new SessionPOJO();
					if (sessionPOJO.getSessionId() == null) {
						sessionId = getNewId();
						sessionPOJO.setSessionId(sessionId);
					}
				}
			}
			sessionPOJO.setSessionName(session.getSessionName());
			sessionPOJO.setSessionEventId(session.getSessionEventId());
			sessionPOJO.setSessionStartDate(session.getSessionStartDate());
			sessionPOJO.setSessionStartTime(session.getSessionStartTime());
			sessionPOJO.setSessionEndDate(session.getSessionEndDate());
			sessionPOJO.setSessionEndTime(session.getSessionEndTime());
			sessionPOJO.setSessionStartDate(session.getSessionStartDate());
			sessionPOJO.setSessionStartTime(session.getSessionStartTime());
			sessionPOJO.setSessionKeyNote(session.getSessionKeyNote());
			sessionPOJO.setSessionTag(session.getSessionTag());
			sessionPOJO.setSessionVenueId(saveVenuePOJO(db, session
					.getSessionVenueName()));
			db.store(sessionPOJO);
			db.commit();
			isSessionPOJOSave = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSessionPOJOSave;
	}

	public String saveVenuePOJO(ObjectContainer db, String venueName) {
		String venueId = null;
		VenuePOJO venuePOJO = null;
		try {

			venuePOJO = new VenuePOJO();
			venueId = getNewId();
			venuePOJO.setVenueId(venueId);
			venuePOJO.setVenueName(venueName);
			db.store(venuePOJO);
			db.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return venueId;
	}

	public SessionPOJO readSessionPOJOById(ObjectContainer db, Session session) {
		SessionPOJO sessionPOJO = null;
		String sessionId = null;

		try {
			// db = getDbConnection();
			if (session != null) {
				sessionId = session.getSessionId();
				if (sessionId != null) {
					ObjectSet<SessionPOJO> result = db.get(new SessionPOJO(sessionId,""));
					if (!result.isEmpty()) {
						while (result.hasNext()) {
							sessionPOJO = result.next();
						}
					}
				} else {
					sessionPOJO = null;
				}
			} else {
				sessionPOJO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}

		return sessionPOJO;
	}

	public boolean saveSessionSpeakerMapPOJO(ObjectContainer dbContainer,
			Session session) {

		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;

		String ssmId = null;

		boolean saveResult = false;
		boolean dataExist = false;

		try {

			dataExist = isUserAlreadyAssignedAsSpeakerForSession(dbContainer,
					session);

			if (!dataExist) {

				ssmId = getNewId();

				sessionSpeakerMapPOJO = new SessionSpeakerMapPOJO();

				sessionSpeakerMapPOJO.setSsmId(ssmId);

				sessionSpeakerMapPOJO.setSsmSessionId(session.getSessionId());

				sessionSpeakerMapPOJO.setSsmSpeakerId(session.getUserId());

				dbContainer.store(sessionSpeakerMapPOJO);

				dbContainer.commit();

			}

			saveResult = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveResult;
	}

	public boolean isUserAlreadyAssignedAsSpeakerForSession(ObjectContainer db,
			Session session) {
		boolean dataExist = false;
		String sessionId = null;
		String userId = null;
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		try {

			if (session != null) {

				sessionId = session.getSessionId();
				userId = session.getUserId();

				ObjectSet<SessionSpeakerMapPOJO> result = db
						.get(new SessionSpeakerMapPOJO(sessionId, userId, ""));
				if (!result.isEmpty()) {
					dataExist = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.close();
		}
		return dataExist;

	}

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
}
