package com.eventattend.portal.controller;

import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.SessionDTO;

public interface SessionController {
	/**
	 * @method populateSessionInfoPage-To get the all session information
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO populateSessionInfoPage (EventDTO eventDTO) throws Exception;
	/**
	 * @method sessionCommentsList - To list down session comments
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO sessionCommentsList (SessionDTO sessionDTO) throws Exception;
	/**
	 * @method updateSessionComment - To update session comment
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO updateSessionComment (SessionDTO sessionDTO) throws Exception;
	public ResultDTO sessionInformation (EventDTO eventDTO) throws Exception;
	
	public ResultDTO sessionSearchTag(SessionDTO sessionDTO) throws Exception;	
	public ResultDTO sessionAttendee(SessionDTO sessionDTO) throws Exception;
	public ResultDTO attendSession (EventDTO eventDTO) throws Exception;
	public ResultDTO leaveSession (EventDTO eventDTO) throws Exception;
	public ResultDTO liveSessionAttendee (SessionDTO sessionDTO) throws Exception;
	public ResultDTO userInLiveSession (SessionDTO sessionDTO) throws Exception;
	public ResultDTO sessionSpeakerInformation (EventDTO eventDTO) throws Exception;
	
	public ResultDTO resetLiveSessionInfo(SessionDTO sessionDTO) throws Exception;
	
	public ResultDTO getSessionByEventId(String eventId) throws Exception;
	
	public ResultDTO saveSessionIdForSpeaker(SessionDTO sessionDTO) throws Exception;
	
	public ResultDTO removeSessionIdForSpeaker(SessionDTO sessionDTO) throws Exception;
}
