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
 * Sep 22, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Predicate;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.User;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.BaseAppException;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.UserNotExistException;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.UserPOJO;

/**
 * Class Description
 * 
 * @version 1.0
 * @author A.Jude
 * @Date Sep 22, 2010
 * 
 *       Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved. This
 *       software is the proprietary information of Kyyba Ventures Inc. Use is
 *       subject to license terms.
 */
public class LoginDAO extends DataAccessObject {

	/**
	 * @ method read - To call fuction based on hidden action.
	 * @param object
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection read(BusinessObject object) throws EventPortalException {
		Login login = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		login = (Login) object;
		hiddenAction = login.getHiddenAction();
		if (hiddenAction != null) {
			if (hiddenAction.equals("isUserExist")) {
				resultCollection = isUserExist(login);
			}
		}
		return resultCollection;
	}

	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		String hiddenAction = null;
		Login login = (Login) object;
		hiddenAction = login.getHiddenAction();
		if (hiddenAction != null) {
			if (hiddenAction.equals("updateUserLoginStatus")) {
				result = updateUserLoginStatus(login);
			}
		}
		return result;
	}

	static ObjectContainer db = null;

	/**
	 * @method- isUserExist - To check the user is already exist or not
	 * @param Object
	 * @return List
	 * @throws BaseAppException
	 */
	public Collection isUserExist(Login login) throws EventPortalException {
		boolean isExist = false;
		List loginList = null;
		String eMail = null;
		String password = null;
		loginList = new ArrayList();
		ObjectContainer clientConn = null;
		if (login != null) {
			eMail = login.geteMail();
			password = login.getPassword();
		}
		try {
			db = getDbConnection();
			UserPOJO user = readUserPOJO(db,login);
			login = new Login();
			if(user!=null){
				if(user.getUserId()!=null){
					login.setUserId(user.getUserId());
				}
				login.seteMail(user.getUserEmailId());
				login.setProfileId(user.getUserProfileId());
				login.setUserType(user.getUserType());
				login.setEmailStatus(user.getUserEmailStatus());
			}
			isExist = isEmailExist(db, login.geteMail());
			if (isExist) {
				login.setLoginStatus("Valid User");
			} else {
				login.setLoginStatus("InValid User");
				throw new UserNotExistException();
			}

			loginList.add(login);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} catch (DatabaseClosedException dce) {
			dce.printStackTrace();
		} finally {
			db.close();
		}
		return loginList;
	}

	/**
	 * method - updateUserLoginStatus - To update the status to 'Y' when the
	 * user logged-in.
	 * 
	 * @param Object
	 * @return Boolean
	 */
	public boolean updateUserLoginStatus(Login login) {
		String userLoginStatus = null;
		boolean isUserLoginStatus = false;
		String userId = null;
		if(login!=null){
			try {
				isUserLoginStatus = updateUserPOJO(login);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		}
		return isUserLoginStatus;
	}
	
	/**
	 * method - isEmailExist - To check whether the user email is exists or not
	 * @param ObjectContainer
	 * @param String
	 * @return boolean
	 */
	public boolean isEmailExist(ObjectContainer db, String emailId) {
		boolean isEmailIDExist = false;
		if (emailId != null && !emailId.equals("")) {
			ObjectSet<UserPOJO> results = db.get(new UserPOJO(emailId,null));
			if (!results.isEmpty()) {
				isEmailIDExist = true;
			}
		}
		return isEmailIDExist;
	}

	
	public UserPOJO readUserPOJO(ObjectContainer db,Login login){
		UserPOJO userPOJO = null;
		String userEmail = null;
		String password = null;
		try {
			//db = getDbConnection();		
			if(login!=null){
				userEmail = login.geteMail();
				password = login.getPassword();
				ObjectSet<UserPOJO> results = db.get(new UserPOJO(userEmail,null));
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						UserPOJO user = (UserPOJO) results.next();						
						if (user.getUserEmailId().trim().equalsIgnoreCase(userEmail)
								&& user.getUserPassword().trim().equalsIgnoreCase(
										password)) {
							userPOJO = user;
						}
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//db.close();
			}

		return userPOJO;
	}
	
	
	
	public boolean saveUserPOJO(User user){
		boolean isUserPOJOSave = false;
		
		return isUserPOJOSave;
	}
	
	
	
	public boolean updateUserPOJO(Login login){
		boolean isUserPOJOUpdate = false;
		String userId = null;
		try {
			db = getDbConnection();
			if (login.geteMail() != null && !login.geteMail().equals("")) {
				userId = login.geteMail();
				ObjectSet<UserPOJO> results = db.get(new UserPOJO(login.geteMail(),null));
				UserPOJO userPOJO = (UserPOJO) results.next();
				if (userPOJO.getUserStatus() != null
						&& userPOJO.getUserStatus().equals("N")) {
					userPOJO.setUserStatus("Y");					
					isUserPOJOUpdate = true;
				} else {
					userPOJO.setUserStatus("N");					
				}
				db.set(userPOJO);
				db.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isUserPOJOUpdate;
	}

}
