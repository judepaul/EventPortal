package com.eventattend.portal.bo;

import java.util.Collection;

import com.eventattend.portal.exceptions.EventPortalException;

public class Logout extends BusinessObject  {
	
	private String userId = null;
	private String hiddenAction = null;
	private String userStatus;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHiddenAction() {
		return hiddenAction;
	}
	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
	public Collection isUserLogout(Logout logout) throws EventPortalException {
		logout.setHiddenAction("isUserLogout");
		Collection userList = logout.read();
		return userList;
	}    

}
