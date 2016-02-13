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
 * Oct 7, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.bo;

import java.util.Collection;

import com.eventattend.portal.exceptions.EventPortalException;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 7, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class Keys extends BusinessObject {

	private String hiddenObject = null;
	private String profileId;
	private Object twitterAccessToken = null;
	private Object linkedInAccessToken = null;
	private Object faceBookAccessToken = null;

	public Object getFaceBookAccessToken() {
		return faceBookAccessToken;
	}
	public void setFaceBookAccessToken(Object faceBookAccessToken) {
		this.faceBookAccessToken = faceBookAccessToken;
	}
	public String getHiddenObject() {
		return hiddenObject;
	}
	public void setHiddenObject(String hiddenObject) {
		this.hiddenObject = hiddenObject;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public Object getTwitterAccessToken() {
		return twitterAccessToken;
	}
	public void setTwitterAccessToken(Object twitterAccessToken) {
		this.twitterAccessToken = twitterAccessToken;
	}	
	
	public Object getLinkedInAccessToken() {
		return linkedInAccessToken;
	}
	public void setLinkedInAccessToken(Object linkedInAccessToken) {
		this.linkedInAccessToken = linkedInAccessToken;
	}

	public boolean saveTwitterAccessToken(Keys keys) throws EventPortalException{
		
		return keys.save();
		
	}
	
	public boolean saveLinkedInAccessToken(Keys keys) throws EventPortalException{
		
		return keys.save();
		
	}
	
	public boolean saveAccessToken(Keys keys) throws EventPortalException{
		
		return keys.save();
		
	}
	
	public Collection getTwitterAccessToken(Keys keys) throws EventPortalException {		
		
		Collection accessTokenList = keys.read();
		
		return accessTokenList;
	}
	
   public Collection getLinkedInAccessToken(Keys keys) throws EventPortalException {		
		
		Collection accessTokenList = keys.read();
		
		return accessTokenList;
	}
	
   public Collection getAccessToken(Keys keys) throws EventPortalException {		
		
		Collection accessTokenList = keys.read();
		
		return accessTokenList;
	}
   
   public boolean deleteAccessToken(Keys keys) throws EventPortalException{
		
		return keys.delete();
		
	}
   
}
