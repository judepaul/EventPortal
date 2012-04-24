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
 * Sep 27, 2010 - A.Jude created the file.
 * 
 */

package com.eventattend.portal.model.db4o;

/**
 * UserPOJO Class Description
 * 
 * @version 1.0
 * @author A.Jude
 * @Date Sep 27, 2010
 * 
 *       Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved. This
 *       software is the proprietary information of Kyyba Ventures Inc. Use is
 *       subject to license terms.
 */

public class UserPOJO {
	
	private String userId;
	private String userEmailId;
	private String userPassword;
	private String userEmailStatus;
	private String userStatus;
	private String userType;	
	private String userProfileId;
	private ProfilePOJO profilePOJO;
	
	// Default constructor to fetch all records in an object.
	public UserPOJO(){		
	}
	
	// Constructor to fetch records in an object based on user Id.
	public UserPOJO(String userId){
		this.userId = userId;
	}

	// Constructor to fetch records in an object based on user Email-ID.
	public UserPOJO(String userEmailId, String id1){
		this.userEmailId = userEmailId;
	}
	
	// Constructor to fetch records in an object based on user Profile Id.
	public UserPOJO(String userProfileId,String id1, String id2){
		this.userProfileId = userProfileId;
	}
	
	// Constructor to fetch records in an object based on the combination of user Profile-Id userType and user Email-ID.
	public UserPOJO(String userProfileId,String userType,String userEmailId, String id1){
		this.userType = userType;
	}
	

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

	public ProfilePOJO getProfilePOJO() {
		return profilePOJO;
	}

	public void setProfilePOJO(ProfilePOJO profilePOJO) {
		this.profilePOJO = profilePOJO;
	}

	public String getUserProfileId() {
		return userProfileId;
	}


	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}	

}
