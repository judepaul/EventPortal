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

package com.eventattend.portal.dto;

import twitter4j.Twitter;

/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */

public class LoginDTO {

	private String userName = null;
	private String password = null;
	// To get user profile
	private String profileId;
	// To get admin profile
	private String adminProfileId;	
	// To get user type
	private String userType;
	// To get user type
	private String userStatus;
	//
	private String basePath;
	
	private Twitter twitter = null;
	
	private int tweetUserId = 0;
	
	private String firstName;
	private String lastName;
	private String eMail;
	private String userId;
	private String gender;
	
	private String emailStatus;
	private String eMailStatus = null;
	private boolean isEmailExist;

	public int getTweetUserId() {
		return tweetUserId;
	}
	public void setTweetUserId(int tweetUserId) {
		this.tweetUserId = tweetUserId;
	}
	public Twitter getTwitter() {
		return twitter;
	}
	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
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
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getAdminProfileId() {
		return adminProfileId;
	}
	public void setAdminProfileId(String adminProfileId) {
		this.adminProfileId = adminProfileId;
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
	public String geteMailStatus() {
		return eMailStatus;
	}
	public void seteMailStatus(String eMailStatus) {
		this.eMailStatus = eMailStatus;
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
	public boolean isEmailExist() {
		return isEmailExist;
	}
	public void setEmailExist(boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}
	public String getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	
	
}
