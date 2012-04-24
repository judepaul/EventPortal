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
 * Oct 7, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.ext.DatabaseFileLockedException;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Keys;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.model.db4o.KeysPOJO;
import com.eventattend.portal.model.db4o.UserPOJO;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 7, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class KeysDAO extends DataAccessObject {

	ObjectContainer db = null;
	
	public Collection read(BusinessObject object) throws EventPortalException {
		
		Collection resultCollection = null;
		String hiddenAction = null;
		Keys keys = (Keys)object;
		resultCollection = getAccessToken(keys);
		
		return resultCollection;
	}
	
	public boolean save(BusinessObject object) throws EventPortalException {		
		
		boolean saveStatus = false;
		
		Keys keys = (Keys)object;
		
		if(keys != null){

			saveStatus = saveToken(keys);
				
		}
		
		return saveStatus;
	}

	
	public boolean delete(BusinessObject object) throws EventPortalException {		
		
		boolean deleteStatus = false;
		
		Keys keys = (Keys)object;
		
		if(keys != null){

			deleteStatus = deleteToken(keys);
				
		}
		
		return deleteStatus;
	}
	
	public Collection getAccessToken(Keys keys) throws EventPortalException{
		List accessTokenList = new ArrayList();
		
		try {

			db = getDbConnection();
			
			ObjectSet<KeysPOJO> results = db.get(new KeysPOJO(keys.getProfileId()));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					KeysPOJO keysPOJO = results.next();
					keys = new Keys();
					keys.setTwitterAccessToken(keysPOJO.getTwitterAccessToken());
					keys.setLinkedInAccessToken(keysPOJO.getLinkedInAccessToken());
					keys.setFaceBookAccessToken(keysPOJO.getFacebookAccessToken());
					accessTokenList.add(keys);					
				}				
			} 
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		
		return accessTokenList;
	}
	
	public boolean saveToken(Keys keys) throws EventPortalException{

		boolean saveToken = false;
		try {
			db = getDbConnection();

			KeysPOJO keysPOJO = new KeysPOJO(keys.getProfileId());

			ObjectSet<KeysPOJO> results = db.get(keysPOJO);
			
			if(!results.isEmpty()){	
				keysPOJO = results.get(0);
			}
			if(keys != null){
				
				if(keys.getHiddenObject().equals("TWITTER")){
					keysPOJO.setTwitterAccessToken(keys.getTwitterAccessToken());
					
				}
				if(keys.getHiddenObject().equals("LINKEDIN")){
					keysPOJO.setLinkedInAccessToken(keys.getLinkedInAccessToken());
				}
				if(keys.getHiddenObject().equals("FACEBOOK")){
					keysPOJO.setFacebookAccessToken(keys.getFaceBookAccessToken());
				}
			}
				db.store(keysPOJO);	
				saveToken = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return saveToken;
	}
	
	public boolean deleteToken(Keys keys) throws EventPortalException{

		boolean deleteToken = false;
		try {
			db = getDbConnection();

			KeysPOJO keysPOJO = new KeysPOJO(keys.getProfileId());

			ObjectSet<KeysPOJO> results = db.get(keysPOJO);
			
			if(!results.isEmpty()){	
				keysPOJO = results.get(0);
				
				if(keys != null){
					
					if(keys.getHiddenObject().equals("TWITTER")){
						keysPOJO.setTwitterAccessToken(null);
					}
					if(keys.getHiddenObject().equals("LINKEDIN")){
						keysPOJO.setLinkedInAccessToken(null);
					}
					if(keys.getHiddenObject().equals("FACEBOOK")){
						keysPOJO.setFacebookAccessToken(null);
					}
				}
					db.store(keysPOJO);	
					deleteToken = true;		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return deleteToken;
	}
}
