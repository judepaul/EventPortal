package com.eventattend.portal.dto;

public class UserDTO {

	private String userId = null;
	private String userEmailId = null;
	private String userPassword = null;
	private String userEmailStatus = null;
	private String userStatus = null;
	private String userType = null;
	private String userProfileId = null;
	
	private String userName = null;
	private String profileId = null;
	private String firstName = null;
	private String lastName = null;
	private String eMail = null;
	private String gender = null;
	private ProfileDTO profileDTO = null;
	
	private boolean isEmailExist;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmailStatus() {
		return userEmailStatus;
	}
	public void setUserEmailStatus(String userEmailStatus) {
		this.userEmailStatus = userEmailStatus;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public boolean getEmailExist() {
		return isEmailExist;
	}
	public void setEmailExist(boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}

	public boolean isEmailExist() {
		return isEmailExist;
	}
	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}
	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}		
	

}
