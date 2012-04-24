package com.eventattend.portal.model.db4o;

public class KeysPOJO {
	private Object twitterAccessToken;
	private Object facebookAccessToken;
	private Object linkedInAccessToken;
	private Object emailAccessToken;
	//
	private String profileId;
	
	public KeysPOJO() {		
	}	
	public KeysPOJO(String profileId) {		
		this.profileId = profileId;
	}

	public KeysPOJO(String profileId, Object twitterAccessToken, Object facebookAccessToken,
			Object linkedInAccessToken, Object emailAccessToken) {	
		this.profileId = profileId;
		this.twitterAccessToken = twitterAccessToken;
		this.facebookAccessToken = facebookAccessToken;
		this.linkedInAccessToken = linkedInAccessToken;
		this.emailAccessToken = emailAccessToken;		
	}
	
	public Object getTwitterAccessToken() {
		return twitterAccessToken;
	}
	public void setTwitterAccessToken(Object twitterAccessToken) {
		this.twitterAccessToken = twitterAccessToken;
	}
	public Object getFacebookAccessToken() {
		return facebookAccessToken;
	}
	public void setFacebookAccessToken(Object facebookAccessToken) {
		this.facebookAccessToken = facebookAccessToken;
	}
	public Object getLinkedInAccessToken() {
		return linkedInAccessToken;
	}
	public void setLinkedInAccessToken(Object linkedInAccessToken) {
		this.linkedInAccessToken = linkedInAccessToken;
	}
	public Object getEmailAccessToken() {
		return emailAccessToken;
	}
	public void setEmailAccessToken(Object emailAccessToken) {
		this.emailAccessToken = emailAccessToken;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	
}
