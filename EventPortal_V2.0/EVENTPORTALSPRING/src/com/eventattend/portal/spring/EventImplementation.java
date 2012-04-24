package com.eventattend.portal.spring;


import com.eventattend.portal.bl.EventBL;
import com.eventattend.portal.controller.EventController;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ResultDTO;

public class EventImplementation implements EventController{

		/**
		 * @method eventDetails = To get the details\related data of an event
		 * @param eventDTO
		 * @return ResultDTO
		 */
		public ResultDTO eventDetails(EventDTO eventDTO){
				
				EventBL eventBl = new EventBL();
				
				return eventBl.eventDetails(eventDTO);
		}
		
		

		/**
		 * @method listEvents-To get all the existing events
		 * @param eventDTO
		 * @return ResultDTO		
		 */
		public ResultDTO listEvents(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.listEvents(eventDTO);
		}
		/**
	     * @method eventAttendees-To get the all attendees of the event
		 * @param ResultDTO
		 * @return	
		 */
		public ResultDTO eventAttendees (EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.eventAttendee(eventDTO);
		}
		/**
	     * @method populateAgendaSessionPage-To get the all agenda
		 * @param ResultDTO
		 * @return	
		 */
		public ResultDTO populateAgendaSessionPage(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.populateAgendaSessionPage(eventDTO);
		}
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
	     * @method joinEvent-users joins an event
		 * @param EventDTO
		 * @return	ResultDTO
		 */
		public ResultDTO joinEvent(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.joinEvent(eventDTO);
		}
		
		/**
	     * @method unjoinEvent-To unjoin from an event
		 * @param EventDTO
		 * @return	ResultDTO
		 */
		public ResultDTO unjoinEvent(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.unjoinEvent(eventDTO);
		}
		
		/**
	     * @method firstEventSearchTag-To get the tag for  event
		 * @param EventDTO
		 * @return	ResultDTO
		 */
		public ResultDTO eventSearchTag(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.eventSearchTag(eventDTO);
		}
		
		
		
		public ResultDTO getEvents(EventDTO eventDTO){
			
			EventBL eventBl = new EventBL();
			
			return eventBl.getEvents(eventDTO);
		}


}

