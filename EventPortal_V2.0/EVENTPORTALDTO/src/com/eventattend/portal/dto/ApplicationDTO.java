package com.eventattend.portal.dto;

public class ApplicationDTO {
	
	private String userId = null;
	private String profileId = null;
	private String userType = null;
	
	private ProfileDTO userProfileDTO = null;
	private ProfileDTO adminProfileDTO = null;	
	private TwitterDTO twitterDTO = null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public TwitterDTO getTwitterDTO() {
		return twitterDTO;
	}
	public void setTwitterDTO(TwitterDTO twitterDTO) {
		this.twitterDTO = twitterDTO;
	}
	public ProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}
	public void setUserProfileDTO(ProfileDTO userProfileDTO) {
		this.userProfileDTO = userProfileDTO;
	}
	public ProfileDTO getAdminProfileDTO() {
		return adminProfileDTO;
	}
	public void setAdminProfileDTO(ProfileDTO adminProfileDTO) {
		this.adminProfileDTO = adminProfileDTO;
	}
	
	
	

}
