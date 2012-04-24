package com.eventattend.portal.bl;


import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.MaintenanceDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;


public class MaintenanceBL extends BusinessLayer {

	public ResultDTO addEvent(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO = new ResultDTO();
		EventDTO eventDTO = new EventDTO();		
		eventDTO = maintenanceDTO.getEventDTO();
		EventBL eventBl = new EventBL();
		resultDTO = eventBl.addEvent(eventDTO);		
		return resultDTO;
	}
	
	public ResultDTO events(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		EventDTO eventDTO = new EventDTO();			
		eventDTO = maintenanceDTO.getEventDTO();
		EventBL eventBl = new EventBL();
		resultDTO = eventBl.events(eventDTO);		
		return resultDTO;
	}
	public ResultDTO event(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();	
		EventDTO eventDTO = new EventDTO();			
		eventDTO = maintenanceDTO.getEventDTO();
		EventBL eventBl = new EventBL();
		eventDTO.setSelectedEvent("selectedEvent");
		resultDTO = eventBl.eventDetails(eventDTO);				
		return resultDTO;
	}
	
	public ResultDTO updateEvent(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		EventDTO eventDTO = new EventDTO();		
		eventDTO = maintenanceDTO.getEventDTO();
		EventBL eventBl = new EventBL();
		resultDTO = eventBl.addEvent(eventDTO);		
		return resultDTO;
	}
	
	public ResultDTO deleteEvent(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		EventDTO eventDTO = new EventDTO();		
		eventDTO = maintenanceDTO.getEventDTO();
		EventBL eventBl = new EventBL();
		resultDTO = eventBl.deleteEvent(eventDTO);		
		return resultDTO;
	}
	
	public ResultDTO sessions(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		SessionDTO sessionDTO =  new SessionDTO();			
		sessionDTO = maintenanceDTO.getSessionDTO();
		SessionBL sessionBL = new SessionBL();
		resultDTO = sessionBL.sessions(sessionDTO);		
		return resultDTO;
	}
	public ResultDTO session(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();	
		SessionDTO sessionDTO =  new SessionDTO();			
		sessionDTO = maintenanceDTO.getSessionDTO();
		SessionBL sessionBL = new SessionBL();
		resultDTO = sessionBL.session(sessionDTO);		
		return resultDTO;
	}
	public ResultDTO deleteSession(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();	
		
		SessionBL sessionBL = new SessionBL();			
		resultDTO = sessionBL.deleteSession(maintenanceDTO.getSessionDTO());				
		return resultDTO;
	}
	public ResultDTO addSession(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();
		SessionDTO sessionDTO =  new SessionDTO();		
		sessionDTO = maintenanceDTO.getSessionDTO();
		SessionBL sessionBL = new SessionBL();			
		resultDTO = sessionBL.addSession(sessionDTO);		
		return resultDTO;
	}
	
	public ResultDTO updateSession(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();			
		SessionDTO sessionDTO =  new SessionDTO();		
		sessionDTO = maintenanceDTO.getSessionDTO();
		SessionBL sessionBL = new SessionBL();			
		resultDTO = sessionBL.addSession(sessionDTO);		
		return resultDTO;
	}
	public ResultDTO attendee(MaintenanceDTO maintenanceDTO){
		ResultDTO resultDTO = new ResultDTO();		
		ProfileBL profileBL = new ProfileBL();
		resultDTO = profileBL.attendee();		
		return resultDTO;
	}
	
	
}
