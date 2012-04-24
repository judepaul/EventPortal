package com.eventattend.portal.controller;


import com.eventattend.portal.dto.MaintenanceDTO;
import com.eventattend.portal.dto.ResultDTO;

public interface MaintenanceController {
	public ResultDTO addEvent(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO events(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO event(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO updateEvent(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO deleteEvent(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO sessions(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO session(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO deleteSession(MaintenanceDTO maintenanceDTO) throws Exception;	
	public ResultDTO addSession(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO updateSession(MaintenanceDTO maintenanceDTO) throws Exception;
	public ResultDTO attendee(MaintenanceDTO maintenanceDTO) throws Exception;
}
