package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.Session;
import com.eventattend.portal.bo.Speaker;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.common.util.StringUtility;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.SpeakerDTO;
import com.eventattend.portal.exceptions.EventPortalException;

public class SessionBL extends BusinessLayer {

	
	/**
     * @method getSessionCommentLike - To get session comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getSessionCommentLike(AttendeeDTO attendeeDTO){		    	
        	Collection sessionCommentsLikeCountList = null;	
        	ResultDTO resultDTO = null;        	
        	Event event = new Event();     	
        	resultDTO = new ResultDTO();
        	int sessCmtLikeCount = 0;
    		try {
    			event.setSessionCommentId(attendeeDTO.getSessionCommentId());
    			event.setSessionCommentSessId(attendeeDTO.getSessionCommentSessId());
    			event.setSessionLikeCount(attendeeDTO.getSessionLikeCount()+1);
    			
    			sessionCommentsLikeCountList = event.getSessionCommentLike(event);				
    			if (!sessionCommentsLikeCountList.isEmpty()) {
    				Iterator it = sessionCommentsLikeCountList.iterator();
    				while (it.hasNext()) {
    					Event sessionComentsLikeCount = (Event) it.next();
    					sessCmtLikeCount = sessionComentsLikeCount.getSessionLikeCount();
    					attendeeDTO.setSessionLikeCount(sessCmtLikeCount);
    					resultDTO.setAttendeeDTO(attendeeDTO);    							
    				}			
    			}    			
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }
	
    
	/**
     * @method getSessionLike - To get session comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getSessionLike(AttendeeDTO attendeeDTO){		    	
        	Collection sessionLikeCountList = null;	
        	ResultDTO resultDTO = null;        	
        	Event event = new Event();     	
        	resultDTO = new ResultDTO();
        	int sessCmtLikeCount = 0;
    		try {
    			event.setSessionCommentId(attendeeDTO.getSessionCommentId());
    			event.setSessionCommentSessId(attendeeDTO.getSessionCommentSessId());
    			event.setSessionLikeCount(attendeeDTO.getSessionLikeCount()+1);
    			
    			sessionLikeCountList = event.getSessionLike(event);				
    			if (!sessionLikeCountList.isEmpty()) {
    				Iterator it = sessionLikeCountList.iterator();
    				while (it.hasNext()) {
    					Event sessionLikeCount = (Event) it.next();
    					sessCmtLikeCount = sessionLikeCount.getSessionLikeCount();
    					attendeeDTO.setSessionLikeCount(sessCmtLikeCount);
    					resultDTO.setAttendeeDTO(attendeeDTO);    							
    				}			
    			}    			
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }


	public ResultDTO resetLiveSessionInfo(SessionDTO sessionDTO) {
		 
		ResultDTO resultDTO = new ResultDTO();
		Session session = new Session();
		boolean resultSts = false;
		try {
			
			
			resultSts = session.resetLiveSessionInfo(session);
			resultDTO.setResultStatus(resultSts);
			
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
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
    
	
	
	
	/**
	 * @method getEvents - To get the all the events
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO getSessionByEventId(String eventId) {
		
		ResultDTO resultDTO = new ResultDTO();
		SessionDTO sessionDTO = null;
		
		List sessionList = null;
		
		try {
				
			sessionList = getSessionListByEventId(eventId);
			
			sessionDTO = new SessionDTO();
			
			sessionDTO.setSessionList(sessionList);
			
			resultDTO = new ResultDTO();
			
			resultDTO.setSessionDTO(sessionDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return resultDTO;
	}

	
	
	/**
	 * @method getEvents - To get the all the session details in DTO Format
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public List getSessionListByEventId(String eventId) {
		
		ResultDTO resultDTO = new ResultDTO();
		SessionDTO sessionDTO = null;
		
		Collection sessionCollection = null;
		List sessionList = null;
		Iterator iterator = null;
		
		Session session = null;
		
		try {
			
			session = new Session();
			
			session.setEventId(eventId);
			
			sessionCollection = session.getSessionByEventId(session);
			
			if (sessionCollection != null) {
				
				iterator = sessionCollection.iterator();
				
				sessionList = new ArrayList();
				
				while(iterator.hasNext()) {	
					
					session = (Session) iterator.next();
					
					sessionDTO = convertSessionBOToSessionDTO(session);
					
					sessionList.add(sessionDTO);
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessionList;
	}
	
	/**
	 * @method getEvents - To get the all the session IDs in list
	 * @param eventDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public List getSessionListForEvent(String eventId) {
		
		ResultDTO resultDTO = new ResultDTO();
		SessionDTO sessionDTO = null;
		
		Collection sessionCollection = null;
		List sessionList = null;
		Iterator iterator = null;
		
		Session session = null;
		
		try {
			
			session = new Session();
			
			session.setEventId(eventId);
			
			sessionCollection = session.sessionListForEvent(session);
			
			if (sessionCollection != null) {
				
				iterator = sessionCollection.iterator();
				
				sessionList = new ArrayList();
				
				while(iterator.hasNext()) {	
					
					session = (Session) iterator.next();
					
					sessionList.add(session.getSessionId());
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessionList;
	}
	

	private SessionDTO convertSessionBOToSessionDTO(Session session) {
		SessionDTO sessionDTO = null;
		
		if(session != null){
			
			sessionDTO = new SessionDTO();
			
			sessionDTO.setSessionId(session.getSessionId());
			sessionDTO.setSessionName(session.getSessionName());
			
		}
		
		return sessionDTO;
	}

	
	public Map speakerIdMap(List sessionList) throws EventPortalException{
		List speakerList = null;
		List speakerProfileList = null;
		Session session = null;
		Map speakerMap = null;
		UserBL userBL = null;
		
		session = new Session();
		userBL = new UserBL();
		session.setSessionList(sessionList);
		
		speakerList = (ArrayList)session.speakerIdList(session);
		
		speakerProfileList = (ArrayList)userBL.profileIdListFromUserIdList(speakerList);
		
		if(speakerProfileList != null){
			speakerMap = new HashMap();
			Iterator it = speakerProfileList.iterator();
			
			while(it.hasNext()){
				speakerMap.put(it.next().toString(), "SPEAKER");
			}
		}
		
		return speakerMap;
	}
	
	public String setSpeakerForInfo(String profileId) throws EventPortalException{
		
		String speakerInfo = null;
		List speakerInfoList = null;
		
		Session session = null;
		
		session = new Session();
		session.setUserId(profileId);
		
		speakerInfoList = (ArrayList)session.speakerForInfo(session); 
		
		if(speakerInfoList != null){
			speakerInfo = (String)speakerInfoList.get(0);
		}
		
		
		return speakerInfo;
	}
	
	
	/**
	 * @Method - To get Session Attendee
	 * @param sessionDTO
	 * @return {@link ResultDTO}
	 */	
	public ResultDTO sessionAttendee(SessionDTO sessionDTO){
			
			ResultDTO resultDTO = null;
			
			AttendeeDTO attendeeDTO = null;

			List sessionUserList = null;
			
			String sessionId = null;
			String speakerId = null;
			Session session = null;
			List attendeeWithSpeakerList = new ArrayList();
			List sessionList = null;
			try {
				
				resultDTO = new ResultDTO();
				sessionList = new ArrayList();
				speakerId = sessionDTO.getSessionSpeakerSMPId();
				sessionId = sessionDTO.getSessionId();
				session = new Session();
				session.setSessionId(sessionId);

				sessionList.add(sessionDTO.getSessionId());
				
				session.setSessionList(sessionList);
				
				sessionUserList = (ArrayList) session.sessionUserList(session);
		 
				attendeeDTO = getSessionAttendee(sessionDTO, sessionUserList);
					
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

	/**
	 * @Method - To get Live Session Attendee
	 * @param sessionDTO
	 * @return {@link ResultDTO}
	 */	
	public ResultDTO liveSessionAttendee(SessionDTO sessionDTO){
	
	ResultDTO resultDTO = null;
	
	AttendeeDTO attendeeDTO = null;

	List sessionUserList = null;
	
	String sessionId = null;

	Session session = null;
	
	List sessionList = null;
	try {
		
		resultDTO = new ResultDTO();
		sessionList = new ArrayList();
		
		sessionId = sessionDTO.getSessionId();
		session = new Session();
		session.setSessionId(sessionId);
		sessionList.add(sessionDTO.getSessionId());
		session.setSessionList(sessionList);
		sessionUserList = (ArrayList) session.liveSessionUserList(session);
 
		attendeeDTO = getSessionAttendee(sessionDTO, sessionUserList);
			
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

	/**
	 * @Method - Common method to get Session Attendee DTO
	 * @param sessionDTO
	 * @return {@link ResultDTO}
	 */	
	
public AttendeeDTO getSessionAttendee(SessionDTO sessionDTO, List userList) throws EventPortalException{
		
		ResultDTO resultDTO = null;
		
		AttendeeDTO attendeeDTO = null;

		List sessionUserList = null;
		List profileIdList = null;
		List profileDataList = null;
		List sessionList = null;
		Map speakerMap = null;
		String sessionId = null;
		String speakerId = null;
		Session session = null;
		List attendeeList = null;
		SessionBL sessionBL = null;
		AttendeeBL attendeeBL = null;
		List attendeeWithSpeakerList = new ArrayList();
		
			
			resultDTO = new ResultDTO();
			sessionBL = new SessionBL();
			attendeeBL = new AttendeeBL();
			sessionList = new ArrayList();
			UserBL userBL = new UserBL();
			ProfileBL profileBL = new ProfileBL();
			
			speakerId = sessionDTO.getSessionSpeakerSMPId();
			sessionId = sessionDTO.getSessionId();
			session = new Session();
			session.setSessionId(sessionId);
			
			sessionList.add(sessionDTO.getSessionId());
			
			if(userList != null){
				
			speakerMap = sessionBL.speakerIdMap(sessionList);
			
			profileIdList = (ArrayList) userBL.profileIdListFromUserIdList(userList);
			
			profileDataList = (ArrayList)profileBL.profileDataList(profileIdList);
			
			attendeeList = (List)profileBL.attendeeProfile(
					(List) profileDataList,speakerMap,sessionDTO.getCurrentUserProfileId());
			
			attendeeDTO = new AttendeeDTO();
			
			if(attendeeList != null){
				if (!attendeeList.isEmpty()) {
    				Iterator it = attendeeList.iterator();
    				while (it.hasNext()) {
    					ProfileDTO profileDTO = (ProfileDTO) it.next();
    					//profileDTO.setProfileToolTip(attendeeToolTip(profileDTO));
        			if(speakerId!=null){
    					if(profileDTO.getProfileId().equals(speakerId)){
    							profileDTO.setCurrentSessionSpeaker(true);
    						attendeeWithSpeakerList.add(0, profileDTO);
    					}else{
    						attendeeWithSpeakerList.add(profileDTO);
    					}
        			}else{
        				attendeeDTO.setAttendeeCount(String.valueOf(attendeeList.size()));
    					attendeeDTO.setAttendeeList(attendeeList);	
        			}
    				}
				}
			}
			if(attendeeList != null && (!attendeeList.isEmpty())){
				
				attendeeDTO = new AttendeeDTO();
				ProfileDTO profiledto= null;
				if((!attendeeWithSpeakerList.isEmpty())&&(attendeeWithSpeakerList.get(0)!=null)){
				 profiledto=(ProfileDTO) attendeeWithSpeakerList.get(0);}
				if(speakerId!=null  && profiledto.getProfileId().equals(speakerId)){
					attendeeDTO.setAttendeeCount(String.valueOf(attendeeWithSpeakerList.size()));
					attendeeDTO.setAttendeeList(attendeeWithSpeakerList);	
				}else{
					attendeeDTO.setAttendeeCount(String.valueOf(attendeeList.size()));
					attendeeDTO.setAttendeeList(attendeeList);	
				}
			}

			}
			
			if(attendeeList != null){
				int attendeeCount = 0;
				attendeeCount = attendeeList.size();
				if(attendeeCount <= 0){
					attendeeDTO.setAttendeeCount("0");
				}else{
					attendeeDTO.setAttendeeCount(String.valueOf(attendeeCount));
				}
			} else{
				attendeeDTO.setAttendeeCount("0");
			}
		
		return attendeeDTO;
	}
	

	/**
	 * @method updateSessionComment - To update session comment
	 * @param sessionDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO updateSessionComment(SessionDTO sessionDTO) {	
		ResultDTO resultDTO = null;	
		Session session = new Session();		
		resultDTO = new ResultDTO();
		String userTimezone = null;
		DateUtility dateUtility = null;
		boolean result =false; 
		
		try {
			dateUtility = new DateUtility();
			userTimezone = sessionDTO.getUserTimeZone();
			session.setScComment(sessionDTO.getScComment());
			session.setScUserId(sessionDTO.getUserId());
			session.setScSessionId(sessionDTO.getSessionId());
			session.setScSessionCommentTime(dateUtility.getTodaysDateInServerTimeZone());// updated to fix the negative value in comment
			//session.setScSessionCommentTime(dateUtility.getTodaysDateInUserTimeZone(userTimezone));
			result = session.updateSessionComment(session);
			resultDTO.setResultStatus(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}
	
	/**
	 * @method sessionCommentsList - To list down session comments
	 * @param sessionDTO
	 * @return ResultDTO
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO sessionCommentsList(SessionDTO sessionDTO) {
		List sessionCommentList = null;
		List sessionComList = null;
		List userSocialList = null;
		ResultDTO resultDTO = null;
		Collection sessionCommentsColl = null;
		ProfileDTO profileDTO = null;
		Session session = new Session();
		sessionCommentList = new ArrayList();
		sessionComList = new ArrayList();
		userSocialList = new ArrayList();
		resultDTO = new ResultDTO();
		String userTimeZone = null;
		DateUtility dateUtility = null;
		Map speakerMap = null;
		SessionBL sessionBL = null;
		List sessionList = null;
		try {
			dateUtility = new DateUtility();
			sessionBL = new SessionBL();
			userTimeZone = sessionDTO.getUserTimeZone();
			session.setSessionId(sessionDTO.getSessionId());
			session.setUserId(sessionDTO.getUserId());
			session.setUserTimeZone(userTimeZone);
			// sessionCommentsColl = event.sessionCommentsList(event);
			List sessionCommentsList = (List) session.sessionCommentsList(session);
			List sessionComment = (List) sessionCommentsList.get(0);
			List userTwitterFacebookCheck = (List) sessionCommentsList.get(1);
			
			sessionList = new ArrayList();
			sessionList.add(sessionDTO.getSessionId());
			speakerMap = sessionBL.speakerIdMap(sessionList);
			
			if (!sessionComment.isEmpty()) {
				Iterator it = sessionComment.iterator();
				while (it.hasNext()) {
					Session sessionRes = (Session) it.next();
					sessionDTO = new SessionDTO();
					sessionDTO.setScId(sessionRes.getScId());
					sessionDTO.setScComment(sessionRes.getScComment());
					sessionDTO.setScSessionId(sessionRes.getScSessionId());
					sessionDTO.setScUserId(sessionRes.getScUserId());
					// get the like count for session comment from this list
					sessionDTO.setSessionLikeCount(Integer.parseInt(sessionRes.getSessionLikeCount()));
					if (sessionRes.getScSessionCommentTime() != null && !sessionRes.getScSessionCommentTime().equals("")) {
						sessionDTO.setSessionCommentTime(dateUtility.sessionCommentTimeCheck(sessionRes.getScSessionCommentTime(),userTimeZone));
					} else {
						sessionDTO.setSessionCommentTime("");
					}
					Profile profile = sessionRes.getProfile();
					ProfileBL profileBL = new ProfileBL();
					profileDTO = new ProfileDTO();
					profileDTO = profileBL.convertProfileToProfileDTO(profile, profileDTO);
					
					if(speakerMap!= null){
						  if(speakerMap.containsKey(profileDTO.getProfileId())){
							  profileDTO.setSpeakerCheck(true);
							  profileDTO.setSpeakerFor(sessionBL.setSpeakerForInfo(profileDTO.getProfileId()));
						  }
						  }
					
					profileDTO.setProfileToolTip(profileBL.attendeeToolTip(profileDTO));
					sessionDTO.setProfileDTO(profileDTO);					
					sessionComList.add(sessionDTO);
				}
			}
			if (!userTwitterFacebookCheck.isEmpty()) {
				Iterator it = userTwitterFacebookCheck.iterator();
				while (it.hasNext()) {
					Session sessTweetFaceList = (Session) it.next();
					sessionDTO = new SessionDTO();
					sessionDTO.setFacebookCheck(sessTweetFaceList
							.getFacebookCheck());
					sessionDTO.setTwitterCheck(sessTweetFaceList
							.getTwitterCheck());
					sessionDTO.setLinkedinCheck(sessTweetFaceList
							.getLinkedinCheck());
					userSocialList.add(sessionDTO);
				}
			}
			sessionCommentList.add(sessionComList);
			sessionCommentList.add(userSocialList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultDTO.setSessionCommentsList(sessionCommentList);
		return resultDTO;
	}

	/**
	 * @method sessionSearchTag--To get a session Tag for a session
	 * @param sessionDTO
	 * @return ResultDTO
	 */
	public ResultDTO sessionSearchTag(SessionDTO sessionDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Session session  = new Session();
		Collection sessionSearchTagList = new ArrayList();
		try {
			if (sessionDTO != null) {
				session.setSessionId(sessionDTO.getSessionId());
				sessionSearchTagList = session.sessionSearchTag(session);
				if (sessionSearchTagList != null) {
					Iterator iter = sessionSearchTagList.iterator();
					while (iter.hasNext()) {
						session = (Session) iter.next();
						sessionDTO = new SessionDTO();
						sessionDTO.setSessionTag(session.getSessionTag());
					}
				}
			}
		} catch (EventPortalException e) {
			e.printStackTrace();
		}
		resultDTO.setSessionDTO(sessionDTO);
		return resultDTO;
	}


	public ResultDTO userInLiveSession(SessionDTO sessionDTO) {
		ResultDTO resultDTO = new ResultDTO();
		Collection resultCollection;
		Session session = new Session();
		
		if (sessionDTO != null) {
			if (sessionDTO.getSessionId() != null && sessionDTO.getUserId() != null) {
				session.setUserId(sessionDTO.getUserId());
				session.setSessionId(sessionDTO.getSessionId());
				try {
					resultCollection = session.userInLiveSession(session);
					if (!resultCollection.isEmpty()) {
						Iterator it = resultCollection.iterator();
						while (it.hasNext()) {
							Session resSession = (Session) it.next();
							sessionDTO = new SessionDTO();
							sessionDTO.setUserInLiveSession(resSession.isUserInLiveSession());
						}
					}
					resultDTO.setSessionDTO(sessionDTO);

				} catch (Exception e) {
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}

			}
		}
		return resultDTO;

	}

	
	public ResultDTO sessionInformation(EventDTO eventDTO) {
	    List sessionSpeakerInfo = null;
	    SessionDTO sessionDTO = null;
	    String userTimeZone = null;
	    ResultDTO resultDTO = new ResultDTO();
	    DateUtility dateutility = new DateUtility();
	    Event event = new Event();
	    EventBL eventBL = new EventBL();
	    try {
	      event.setSessionId(eventDTO.getSessionId());
	      if (eventDTO.getUserTimeZone() != null) {
	        userTimeZone = eventDTO.getUserTimeZone();
	      }

	      sessionSpeakerInfo = (List) event.sessionInformation(event);
	      if (!sessionSpeakerInfo.isEmpty()) {
	        Iterator it = sessionSpeakerInfo.iterator();
	        while (it.hasNext()) {
	          event = (Event) it.next();
	        }
	      }

	      if (event != null) {
	        eventDTO = eventBL.convertEventToEvenDTO(event, userTimeZone);
	      }
	      resultDTO.setEventDTO(eventDTO);

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return resultDTO;
	  }
	
	
	public Session ConvertProfileDTOToSession(ProfileDTO profileDTO, Session session){
		if(profileDTO!=null){
			if(profileDTO.getUserId()!=null){
				session.setUserId(profileDTO.getUserId());
			}
			if(profileDTO.getSessionOption()!=null){
				session.setSessionId(profileDTO.getSessionOption());
			}
	    	
		}			
		return session;

		
	}
	
	public boolean saveSessionSpeakerMap(ProfileDTO profileDTO) throws EventPortalException {
		boolean updateResult = false;
		Session session = new Session();
		if(profileDTO!=null){
			session = ConvertProfileDTOToSession(profileDTO,session);
			updateResult = session.saveSessionSpeakerMap(session);
		}
		
		return updateResult;
	}
	
	public ResultDTO sessionPage(SessionDTO sessionDTO){
		
		ResultDTO resultDTO = null;
		SpeakerDTO speakerDTO = null;
		
		try {
		
			speakerDTO = speakerInformation(sessionDTO.getSessionId());
		
		
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return resultDTO;
	}


	public SpeakerDTO speakerInformation(String sessionId) throws EventPortalException {
		
		Session session = null;
		List speakerList = null;
		
		session = new Session();
		
		session.setSessionId(sessionId);
		
		speakerList = (ArrayList)session.speakerForInfo(session);
		
		
		return null;
	}
	
	public SpeakerDTO sessionListForSpeaker(String speakerId) throws EventPortalException{
		
		Session session = null;
		List speakerSessionList = null;
		Collection resultCollection = null;
		SpeakerDTO speakerDTO = null;
		Speaker speaker = null;
		
		session = new Session();
		
		session.setSpeakerUserId(speakerId);
		
		resultCollection =  session.sessionListForSpeaker(session);
		
		if(resultCollection != null){
			
			Iterator it = null;
			
			it = resultCollection.iterator();
			
			speakerSessionList = new ArrayList();
			
			while(it.hasNext()){
				speakerDTO = new SpeakerDTO();
				
				speaker = (Speaker)it.next();
				
				speakerDTO = convertSpeakerToSpeakerDTO(speaker, speakerDTO);
				
				speakerSessionList.add(speakerDTO);
				
			}
		}
		speakerDTO = new SpeakerDTO();
		speakerDTO.setSessionList(speakerSessionList);
		
		return speakerDTO;
		
	}


	public SpeakerDTO convertSpeakerToSpeakerDTO(Speaker speaker,
			SpeakerDTO speakerDTO) {
		
		if(speaker != null){
			
			if(speaker.getSessionId() != null){				
				speakerDTO.setSessionId(speaker.getSessionId());				
			}			
			if(speaker.getSessionName() != null){				
				speakerDTO.setSessionName(speaker.getSessionName());				
			}
			if(speaker.getEventName() != null){				
				speakerDTO.setEventName(speaker.getEventName());				
			}
			
		}
		
		return speakerDTO;
	}


	public ResultDTO saveSessionIdForSpeaker(SessionDTO sessionDTO) {
		
		String sessionId = null;
		String speakerUserId = null;
		Session session = null;
		boolean saveStatus = false;
		SpeakerDTO speakerDTO = null;
		ResultDTO resultDTO = null;
		
		try {
		sessionId = sessionDTO.getSessionId();
		speakerUserId = sessionDTO.getUserId();
		
		session = new Session();
		resultDTO = new ResultDTO();
		
		session.setSessionId(sessionId);
		session.setSpeakerUserId(speakerUserId);
		
			saveStatus = session.saveSessionIdForSpeaker(session);
			
			speakerDTO = sessionListForSpeaker(speakerUserId);
			
			resultDTO.setResultStatus(saveStatus);
			resultDTO.setSpeakerDTO(speakerDTO);
			
		} catch (EventPortalException e) {
			resultDTO.setResultStatus(saveStatus);
			
			e.printStackTrace();
		}
		
		
		return resultDTO;
	}
	
public ResultDTO removeSessionIdForSpeaker(SessionDTO sessionDTO) {
		
		String sessionId = null;
		String speakerUserId = null;
		Session session = null;
		boolean deleteStatus = false;
		SpeakerDTO speakerDTO = null;
		ResultDTO resultDTO = null;
		
		try {
		sessionId = sessionDTO.getSessionId();
		speakerUserId = sessionDTO.getUserId();
		
		session = new Session();
		resultDTO = new ResultDTO();
		
		session.setSessionId(sessionId);
		session.setSpeakerUserId(speakerUserId);
		
			deleteStatus = session.removeSessionIdForSpeaker(session);
			
			speakerDTO = sessionListForSpeaker(speakerUserId);
			
			resultDTO.setResultStatus(deleteStatus);
			resultDTO.setSpeakerDTO(speakerDTO);
			
		} catch (EventPortalException e) {
			resultDTO.setResultStatus(deleteStatus);
			
			e.printStackTrace();
		}
		
		
		return resultDTO;
	}
	
public ResultDTO sessions(SessionDTO sessionDTO){
	
	ResultDTO resultDTO = null;	
	List sessionList = null;	
	Session session = null;
	String userTimeZone = null;
	try {
		session = new Session();
		resultDTO = new ResultDTO();
		sessionList = new ArrayList();			
		if (sessionDTO != null) {
		if (sessionDTO.getUserTimeZone() != null) {
			userTimeZone = sessionDTO.getUserTimeZone();
			session.setUserTimeZone(userTimeZone);
		}		
		sessionList = (ArrayList) session.sessions(session); 	
		if(sessionList != null){
			Iterator it = sessionList.iterator();			
			sessionList = new ArrayList();			
			while(it.hasNext()){
				session = (Session)it.next();				
				sessionDTO = convertSessionToSessionDTO(session,userTimeZone);				
				sessionList.add(sessionDTO);
				
			}
		}
		
		resultDTO.setEventSessionList(sessionList);
		}
		
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


public SessionDTO convertSessionToSessionDTO(Session session,String userTimeZone) {
	DateUtility dateUtility = new DateUtility();
	SessionDTO sessionDTO = new SessionDTO();

	String inputFormat = "yyyy-MM-dd hh:mm:ss";
	String outputFormat = "EEE MMM dd hh:mm:ss z yyyy";
	if (session != null) {
		if (session.getSessionId() != null) {
			sessionDTO.setSessionId(session.getSessionId());
		}
		if (session.getSessionName() != null) {
			sessionDTO.setSessionName(session.getSessionName());
		}
		if (session.getSessionEventId() != null) {
			sessionDTO.setSessionEventId(session.getSessionEventId());
		}
		if (session.getSessionEventName() != null) {
			sessionDTO.setSessionEventName(session.getSessionEventName());
		}		
		if (session.getSessionStartDate() != null) {
			sessionDTO.setSessionStartDate(session.getSessionStartDate());
		}
		if (session.getSessionStartTime() != null) {
			sessionDTO.setSessionStartTime(dateUtility.convertDateToGivenTime(session.getSessionStartTime(),userTimeZone));
		}
		if (session.getSessionEndDate() != null) {
			sessionDTO.setSessionEndDate(session.getSessionEndDate());
		}
		if (session.getSessionEndTime() != null) {
			sessionDTO.setSessionEndTime(dateUtility.convertDateToGivenTime(session.getSessionEndTime(), userTimeZone));
		}
		if (session.getSessionKeyNote() != null) {
			sessionDTO.setSessionKeyNote(session.getSessionKeyNote());
		}
		if (session.getSessionTag() != null) {
			sessionDTO.setSessionTag(session.getSessionTag());
		}
		if (session.getSessionVenueName() != null) {
			sessionDTO.setSessionVenueName(session.getSessionVenueName());
		}
		
		if (session.getSessionStartTime() != null) {
			
			sessionDTO.setSessionStartTime(dateUtility.convertDateToGivenTime(session.getSessionStartTime(), userTimeZone));
			Date eventStartDateWithTime = dateUtility.convertStringToDateFormat(session.getSessionStartTime(), inputFormat, outputFormat);
			sessionDTO.setSessionStartDateWithTime(eventStartDateWithTime);
			
		}
		if (session.getSessionEndTime() != null) {
			sessionDTO.setSessionEndTime(dateUtility.convertDateToGivenTime(
			session.getSessionEndTime(), userTimeZone));			
			Date eventEndDateWithTime = dateUtility.convertStringToDateFormat(session.getSessionEndTime(), inputFormat, outputFormat);
			sessionDTO.setSessionEndDateWithTime(eventEndDateWithTime);
		}
	}
	return sessionDTO;
}
@SuppressWarnings("unchecked")
public ResultDTO session(SessionDTO sessionDTO) {
	ResultDTO resultDTO = new ResultDTO();
	Event event = new Event();
	Collection sessionDetailList = null;
	Session sessionBO = new Session();
	String userTimeZone = null;
	if (sessionDTO != null) {
		if (sessionDTO.getSessionId()!= null) {
	
			sessionBO.setSelectedSession(sessionDTO.getSessionId());
			}
				userTimeZone = sessionDTO.getUserTimeZone();
			try {
				sessionDetailList = sessionBO.session(sessionBO);
				if (sessionDetailList != null) {
					Iterator iter = sessionDetailList.iterator();
					while (iter.hasNext()) {
						sessionBO = (Session) iter.next();
						sessionDTO = convertSessionToSessionDTO(sessionBO,userTimeZone);
						if(sessionBO.getSelectedSession()!=null){
							String sessionTag =sessionDTO.getSessionTag();
							 String[] temp = sessionTag.split("#");
							 for(int i =0; i < temp.length ; i++){
								    System.out.println(i+"Tag  >> "+temp[i]);
								    sessionDTO.setSessionTag(temp[1]);									  
							 }
						
						}
					}

					resultDTO.setSessionDTO(sessionDTO);
					resultDTO.setResultStatus(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");
				resultDTO.setResultStatus(false);
			}

		}
	
	return resultDTO;
}

public ResultDTO deleteSession(SessionDTO sessionDTO){
	ResultDTO resultDTO = new ResultDTO();
	Session session  = new Session();
	session.setSelectedSession(sessionDTO.getSessionId());
	try {
		boolean result = session.deleteSession(session);
		resultDTO.setResultStatus(result);
	} catch (EventPortalException e) {
		e.printStackTrace();
	}
	
	return resultDTO;
}


public Session convertSessionDTOToSession(SessionDTO sessionDTO,String userTimeZone){
	Session session = new Session();
	DateUtility dateUtility = new DateUtility();
	String sessionStartDate = null;
	String sessionEndDate = null;
	String inputFormat = "EEE MMM dd hh:mm:ss z yyyy";
	String outputFormat = "yyyy-MM-dd";
	String retTime = null;
	if (session != null) {
		if (sessionDTO.getSessionId() != null) {
			session.setSessionId(sessionDTO.getSessionId());
		}
		if (sessionDTO.getSessionName() != null) {
			session.setSessionName(sessionDTO.getSessionName());
		}
		if (sessionDTO.getSessionEventId() != null) {
			session.setSessionEventId(sessionDTO.getSessionEventId());
		}
		if (sessionDTO.getSessionEventName() != null) {
			session.setSessionEventName(sessionDTO.getSessionEventName());
		}		
		if (sessionDTO.getSessionStartDate() != null) {
			session.setSessionStartDate(sessionDTO.getSessionStartDate());
		}
		if (sessionDTO.getSessionStartTime() != null) {
			session.setSessionStartTime(dateUtility.convertDateToGivenTime(sessionDTO.getSessionStartTime(),userTimeZone));
		}
		if (sessionDTO.getSessionEndDate() != null) {
			session.setSessionEndDate(session.getSessionEndDate());
		}
		if (sessionDTO.getSessionEndTime() != null) {
			session.setSessionEndTime(dateUtility.convertDateToGivenTime(sessionDTO.getSessionEndTime(), userTimeZone));
		}
		if (sessionDTO.getSessionKeyNote() != null) {
			session.setSessionKeyNote(sessionDTO.getSessionKeyNote());
		}
		if (sessionDTO.getSessionTag() != null) {
			session.setSessionTag(sessionDTO.getSessionTag());
		}
		if (sessionDTO.getSessionVenueName() != null) {
			session.setSessionVenueName(sessionDTO.getSessionVenueName());
		}		
		
		if (sessionDTO.getSessionStartDate() != null) {
			retTime = retTime(sessionDTO.getSessionStartDate());
			sessionStartDate = dateUtility.convertDateFormat(sessionDTO.getSessionStartDate(), inputFormat, outputFormat);
			session.setSessionStartDate(sessionStartDate);
			session.setSessionStartTime(sessionStartDate+" "+retTime);
		}
		
		if (sessionDTO.getSessionEndDate() != null) {
			retTime = retTime(sessionDTO.getSessionEndDate());
			sessionEndDate = dateUtility.convertDateFormat(sessionDTO.getSessionEndDate(), inputFormat, outputFormat);
			session.setSessionEndDate(sessionEndDate);
			session.setSessionEndTime(sessionEndDate+" "+retTime);
		}
		
	}
	return session;
}
public ResultDTO addSession(SessionDTO sessionDTO){
	ResultDTO resultDTO = new ResultDTO();
	Session session = convertSessionDTOToSession(sessionDTO,sessionDTO.getUserTimeZone());
	try {
		boolean result = session.addSession(session);
		resultDTO.setResultStatus(result);
	} catch (EventPortalException e) {
		e.printStackTrace();
	}
	
	return resultDTO;
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
}