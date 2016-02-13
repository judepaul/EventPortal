/*
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Seedcube("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into 
 * with Seedcube.
 * 
 * CHANGE HISTORY
 * ==================================================================================
 * Sep 22, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.model.db4o;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Sep 22, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class LoginDB {
	private String userName;
	private String password;
	
	public LoginDB(String userName, String passwrod){
		this.userName=userName;
		this.password=passwrod;		
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
	
	
}
