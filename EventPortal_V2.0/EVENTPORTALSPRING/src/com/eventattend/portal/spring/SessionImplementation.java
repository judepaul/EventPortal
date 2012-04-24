package com.eventattend.portal.spring;

import com.eventattend.portal.bl.EventBL;
import com.eventattend.portal.bl.SessionBL;
import com.eventattend.portal.controller.SessionController;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.SessionDTO;




public class SessionImplementation implements SessionController{
	
	/**
     * @method populateSessionInfoPage-To get the all session information
	 * @param ResultDTO
	 * @return	
	 */
	public ResultDTO populateSessionInfoPage(EventDTO eventDTO){
		
		EventBL eventBl = new EventBL();
		
		return eventBl.populateSessionInfoPage(eventDTO);
	}
	/**
     * @method sessionCommentsList - To list down session comments
	 * @param ResultDTO
	 * @return	
	 */
	public ResultDTO sessionCommentsList(SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();		
		
		return sessionBL.sessionCommentsList(sessionDTO);
	}
	/**
     * @method updateSessionComment - To update session comment
	 * @param ResultDTO
	 * @return	
	 */
	public ResultDTO updateSessionComment(SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();		
		
		return sessionBL.updateSessionComment(sessionDTO);
	}
	
	public ResultDTO sessionAttendee (SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();		
		
		return sessionBL.sessionAttendee(sessionDTO);
	}
	public ResultDTO attendSession (EventDTO eventDTO){
		
		EventBL eventBl = new EventBL();
		
		return eventBl.attendSession(eventDTO);
	}
	public ResultDTO leaveSession (EventDTO eventDTO){
	
	EventBL eventBl = new EventBL();
	
	return eventBl.leaveSession(eventDTO);
	}
	
	public ResultDTO liveSessionAttendee (SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();
		
		return sessionBL.liveSessionAttendee(sessionDTO);
		}
	
	public ResultDTO userInLiveSession (SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();
		
		return sessionBL.userInLiveSession(sessionDTO);
		}
		
	public ResultDTO sessionSpeakerInformation (EventDTO eventDTO){
		
		EventBL eventBl = new EventBL();
		
		return eventBl.sessionSpeakerInformation(eventDTO);
	}
	
	public ResultDTO resetLiveSessionInfo(SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();

		return sessionBL.resetLiveSessionInfo(sessionDTO);
	}
	
	public ResultDTO getSessionByEventId(String eventId){
		
		SessionBL sessionBL = new SessionBL();
		
		return sessionBL.getSessionByEventId(eventId);
	}

	/**
     * @method sessionSearchTag--To get a session Tag for a session
	 * @param EventDTO
	 * @return	ResultDTO
	 */
	public ResultDTO sessionSearchTag(SessionDTO sessionDTO){
		
		SessionBL sessionBL = new SessionBL();
		
		return sessionBL.sessionSearchTag(sessionDTO);
	}
	
	
	public ResultDTO sessionInformation (EventDTO eventDTO){
	    
	    SessionBL sessionBL = new SessionBL();
	    
	    return sessionBL.sessionInformation(eventDTO);
	    }
	
	public ResultDTO saveSessionIdForSpeaker(SessionDTO sessionDTO){
	    
	    SessionBL sessionBL = new SessionBL();
	    
	    return sessionBL.saveSessionIdForSpeaker(sessionDTO);
	    }
	
	public ResultDTO removeSessionIdForSpeaker(SessionDTO sessionDTO){
	    
	    SessionBL sessionBL = new SessionBL();
	    
	    return sessionBL.removeSessionIdForSpeaker(sessionDTO);
	    }
	


}
