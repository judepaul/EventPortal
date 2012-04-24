package com.eventattend.portal.model.db4o;

public class SessionSpeakerMapPOJO {

	private String ssmId;
	private String ssmSessionId;
	private String ssmSpeakerId;
	
	public SessionSpeakerMapPOJO(){
		
    }
	public SessionSpeakerMapPOJO(String ssmSessionId){
		this.ssmSessionId=ssmSessionId;
	}
	public SessionSpeakerMapPOJO(String ssmSpeakerId,String dummy){
		this.ssmSpeakerId= ssmSpeakerId;
	}
	
	public SessionSpeakerMapPOJO(String ssmSessionId,String ssmSpeakerId, String dummy){
		this.ssmSessionId=ssmSessionId;
		this.ssmSpeakerId= ssmSpeakerId;
	}
	
	public String getSsmId() {
		return ssmId;
	}
	public void setSsmId(String ssmId) {
		this.ssmId = ssmId;
	}
	public String getSsmSessionId() {
		return ssmSessionId;
	}
	public void setSsmSessionId(String ssmSessionId) {
		this.ssmSessionId = ssmSessionId;
	}
	public String getSsmSpeakerId() {
		return ssmSpeakerId;
	}
	public void setSsmSpeakerId(String ssmSpeakerId) {
		this.ssmSpeakerId = ssmSpeakerId;
	}
	
	
}
