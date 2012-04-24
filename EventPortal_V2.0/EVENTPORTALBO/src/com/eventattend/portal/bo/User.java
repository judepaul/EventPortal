package com.eventattend.portal.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eventattend.portal.exceptions.EventPortalException;

public class User extends BusinessObject {

	private String userName = null;
	private String userPassword = null;
	private String firstName = null;
	private String lastName = null;
	private String eMail = null;
	private String userId = null;
	private String gender = null;
	private String hiddenAction = null;
	
	private String profileId;
	private String userType;
	private String userStatus;

	private boolean isEmailExist;	
	
	private List userIdList = null;
		
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}	
	public boolean getEmailExist() {
		return isEmailExist;
	}
	public void setEmailExist(boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}
	public String getHiddenAction() {
		return hiddenAction;
	}
	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}
	
	public List getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List userIdList) {
		this.userIdList = userIdList;
	}
	
	
	public boolean createAccount(User user) throws EventPortalException {
		user.setHiddenAction("createAccount");
		boolean result = false;
		result = this.save();
		return result;
	} 

	public boolean confirmNewUserMailId(User user) throws EventPortalException {
		user.setHiddenAction("confirmNewUserMail");
		boolean result = false;
		result = this.save();
		return result;
	}
	public Collection getAdminProfileId(User user) throws EventPortalException {
		
		user.setHiddenAction("ADMINPROFILEID");
		
		return user.read();
	} 
	public Collection getUserData(User user) throws EventPortalException {		
		user.setHiddenAction("getUserData");
		return user.read();
	} 
	
	public boolean isEmailExist(User user) throws EventPortalException {
		user.setHiddenAction("isEmailExist");
		boolean result = false;
		result = this.save();
		return result;
	}

	public Collection profileIdListFromUserIdList(User user) throws EventPortalException {
		user.setHiddenAction("PROFILEIDLIST");
		
		return user.read();
	} 
	
	public boolean UpdateUserProfileSettings(User user) throws EventPortalException {
		user.setHiddenAction("UpdateUserProfileSettings");
		boolean result = false;
		result = this.update();
		return result;
	}

	
}
