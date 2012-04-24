package com.eventattend.portal.model.db4o;

public class AgendaPOJO {
	
	private String agendaId;
	private String agendaDate;
	private String agendaStartTime;
	private String agendaEndTime;
	private String agendaEventId;
	
	public AgendaPOJO() {
	}

	public String getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}

	public String getAgendaDate() {
		return agendaDate;
	}

	public void setAgendaDate(String agendaDate) {
		this.agendaDate = agendaDate;
	}

	public String getAgendaStartTime() {
		return agendaStartTime;
	}

	public void setAgendaStartTime(String agendaStartTime) {
		this.agendaStartTime = agendaStartTime;
	}

	public String getAgendaEndTime() {
		return agendaEndTime;
	}

	public void setAgendaEndTime(String agendaEndTime) {
		this.agendaEndTime = agendaEndTime;
	}

	public String getAgendaEventId() {
		return agendaEventId;
	}

	public void setAgendaEventId(String agendaEventId) {
		this.agendaEventId = agendaEventId;
	}
	
	
	
}
