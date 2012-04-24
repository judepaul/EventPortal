package com.eventattend.portal.model.db4o;

public class SMInvitePOJO {
	private String smiId;
	private String smiFromProfileId;
	private String smiToProfileId;
	private String smiTwitterConnectDate;
	private String smiFacebookConnectDate;
	private String smiLinkedInConnectDate;
	private String smiTwitterConnectStatus;
	private String smiFacebookConnectStatus;
	private String smiLinkedInConnectStatus;
	

	public SMInvitePOJO() {	
	}	
	public SMInvitePOJO(String smiFromProfileId,String smiToProfileId) {	
		this.smiFromProfileId = smiFromProfileId;
		this.smiToProfileId = smiToProfileId;
	}	
	
	public String getSmiId() {
		return smiId;
	}
	public void setSmiId(String smiId) {
		this.smiId = smiId;
	}
	public String getSmiFromProfileId() {
		return smiFromProfileId;
	}
	public void setSmiFromProfileId(String smiFromProfileId) {
		this.smiFromProfileId = smiFromProfileId;
	}
	public String getSmiToProfileId() {
		return smiToProfileId;
	}
	public void setSmiToProfileId(String smiToProfileId) {
		this.smiToProfileId = smiToProfileId;
	}	
	public String getSmiTwitterConnectStatus() {
		return smiTwitterConnectStatus;
	}
	public void setSmiTwitterConnectStatus(String smiTwitterConnectStatus) {
		this.smiTwitterConnectStatus = smiTwitterConnectStatus;
	}
	public String getSmiFacebookConnectStatus() {
		return smiFacebookConnectStatus;
	}
	public void setSmiFacebookConnectStatus(String smiFacebookConnectStatus) {
		this.smiFacebookConnectStatus = smiFacebookConnectStatus;
	}
	public String getSmiLinkedInConnectStatus() {
		return smiLinkedInConnectStatus;
	}
	public void setSmiLinkedInConnectStatus(String smiLinkedInConnectStatus) {
		this.smiLinkedInConnectStatus = smiLinkedInConnectStatus;
	}
	public String getSmiTwitterConnectDate() {
		return smiTwitterConnectDate;
	}
	public void setSmiTwitterConnectDate(String smiTwitterConnectDate) {
		this.smiTwitterConnectDate = smiTwitterConnectDate;
	}
	public String getSmiFacebookConnectDate() {
		return smiFacebookConnectDate;
	}
	public void setSmiFacebookConnectDate(String smiFacebookConnectDate) {
		this.smiFacebookConnectDate = smiFacebookConnectDate;
	}
	public String getSmiLinkedInConnectDate() {
		return smiLinkedInConnectDate;
	}
	public void setSmiLinkedInConnectDate(String smiLinkedInConnectDate) {
		this.smiLinkedInConnectDate = smiLinkedInConnectDate;
	}
	
	
	
}
