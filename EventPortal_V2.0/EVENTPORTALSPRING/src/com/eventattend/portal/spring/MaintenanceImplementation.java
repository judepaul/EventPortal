package com.eventattend.portal.spring;

import com.eventattend.portal.bl.EventBL;
import com.eventattend.portal.bl.MaintenanceBL;
import com.eventattend.portal.controller.MaintenanceController;
import com.eventattend.portal.dto.MaintenanceDTO;
import com.eventattend.portal.dto.ResultDTO;

public class MaintenanceImplementation implements MaintenanceController {

	@Override
	public ResultDTO addEvent(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.addEvent(maintenanceDTO);
	}
		
	@Override
	public ResultDTO events(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.events(maintenanceDTO);
	}
	
	@Override
	public ResultDTO event(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.event(maintenanceDTO);
	}
	

	@Override
	public ResultDTO updateEvent(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.updateEvent(maintenanceDTO);
	}
	

	@Override
	public ResultDTO deleteEvent(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.deleteEvent(maintenanceDTO);
	}
	
	@Override
	public ResultDTO sessions(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.sessions(maintenanceDTO);
	}
	
	@Override
	public ResultDTO session(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.session(maintenanceDTO);
	}
	
	@Override
	public ResultDTO deleteSession(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.deleteSession(maintenanceDTO);
	}
	
	@Override
	public ResultDTO addSession(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.addSession(maintenanceDTO);
	}
	
	@Override
	public ResultDTO updateSession(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.updateSession(maintenanceDTO);
	}
	
	@Override
	public ResultDTO attendee(MaintenanceDTO maintenanceDTO) throws Exception {
		MaintenanceBL  maintenanceBl = new  MaintenanceBL();
		return maintenanceBl.attendee(maintenanceDTO);
	}
}
