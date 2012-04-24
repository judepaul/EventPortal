package com.eventattend.portal.model.db4o;

public class AttendeesEmailPOJO {
	
	private String profileId = null;
	private String emailFromId = null;
	private String emailToId = null;
	private String emailContent = null;
	private int emailCount = 0;
	
	public AttendeesEmailPOJO(String profileId, String emailFromId, String emailToId) {
		this.profileId = profileId;
		this.emailFromId = emailFromId;
		this.emailToId = emailToId;
	}
	
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getEmailFromId() {
		return emailFromId;
	}
	public void setEmailFromId(String emailFromId) {
		this.emailFromId = emailFromId;
	}

	public String getEmailToId() {
		return emailToId;
	}
	public void setEmailToId(String emailToId) {
		this.emailToId = emailToId;
	}
	
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
	public int getEmailCount() {
		return emailCount;
	}
	public void setEmailCount(int emailCount) {
		this.emailCount = emailCount;
	}
	
}
