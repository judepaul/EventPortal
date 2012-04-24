/*
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Kyyba ventures("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into 
 * with Kyyba Ventures.
 * 
 * CHANGE HISTORY
 * ==================================================================================
 * Sep 21, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.bo;

import java.util.Collection;

import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.exceptions.EventPortalException;


/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Sep 21, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class Login extends BusinessObject {

	private String userId = null;
	private String userName = null;
	private String password = null;
	private String hiddenAction = null;
	// To get user profile
	private String profileId;
	// To get user type
	private String userType;
	// To get user type
	private String userStatus;
	
	private String firstName;
	private String lastName;
	private String eMail;
	private String gender;
	
	private String emailStatus;
	private String loginStatus;
	private boolean isEmailExist;
    	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHiddenAction() {
		return hiddenAction;
	}

	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
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
	public String getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isEmailExist() {
		return isEmailExist;
	}
	public void setEmailExist(boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}
	
	
	
	public String checkLayer() throws EventPortalException{
    	 Login login = new Login();
    	 login.setHiddenAction(null);
    	 Collection userList = login.read();
    	 isUserExist(login);		
		 return "success";
	}
	
	/**
	 * @method isUserExist - To check User Existence
	 * @param loginDTO
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection isUserExist(Login login) throws EventPortalException {
		login.setHiddenAction("isUserExist");
		Collection userList = login.read();
		return userList;
	}    
     
	public Collection loginUserInfo(Login login) throws EventPortalException {
		login.setHiddenAction("loginUserInformation");
		Collection userList = login.read();
		return userList;
	} 
	
	public boolean updateUserLoginStatus(Login login) throws EventPortalException {
		boolean isUserLoginStatus = false;
		login.setHiddenAction("updateUserLoginStatus");
		isUserLoginStatus = login.save();
		return isUserLoginStatus;
	} 

	

}
