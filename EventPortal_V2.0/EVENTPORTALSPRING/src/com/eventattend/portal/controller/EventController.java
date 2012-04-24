package com.eventattend.portal.controller;


import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.google.code.linkedinapi.schema.TwitterAccount;

public interface EventController {
	
    /**
     * @method eventDetails = To get the details\related data of an event
     * @param eventDTO
     * @return
     * @throws Exception
     */
	public ResultDTO eventDetails (EventDTO eventDTO) throws Exception;
	
	
	/**
	 * @method listEvents-To get all the existing events
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO listEvents (EventDTO eventDTO) throws Exception;
	/**
	 * @method eventAttendees-To get the all attendees of the event
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO eventAttendees (EventDTO eventDTO) throws Exception;
	/**
	 * @method populateAgendaSessionPage-To get the all agenda
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO populateAgendaSessionPage (EventDTO eventDTO) throws Exception;
	/**
	 * @method populateSessionInfoPage-To get the all session information
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO populateSessionInfoPage (EventDTO eventDTO) throws Exception;
	
	/**
	 * @method joinEvent-User joins an event 
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO joinEvent(EventDTO eventDTO) throws Exception;
	
	/**
	 * @method unjoinEvent-To unjoin from an event 
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO unjoinEvent(EventDTO eventDTO) throws Exception;
	
	/**
	 * @method eventSearchTag-To get the tag for event
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO eventSearchTag(EventDTO eventDTO) throws Exception;
	
		
	public ResultDTO getEvents(EventDTO eventDTO) throws Exception;
	
	
}
