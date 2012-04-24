package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.User;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.UserAlreadyExistException;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.UserPOJO;

public class UserDAO extends DataAccessObject {

	static ObjectContainer db = null;
	
	/**
	 * @ method read - To call fuction based on hidden action.
	 * 
	 * @param object
	 * @return Collection
	 * @throws EventPortalException
	 */
	public Collection read(BusinessObject object) throws EventPortalException {
		User user = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		user = (User) object;
		hiddenAction = user.getHiddenAction();
		
		if (hiddenAction != null) {
			try{
				db = getDbConnection();
				if(hiddenAction.equals("getUserData")){
					resultCollection = getUserByEmailId(db,user);	
				}else if(hiddenAction.equals("ADMINPROFILEID")){
					resultCollection = getAdminProfileId(db);	
				}else if(hiddenAction.equals("PROFILEIDLIST")){
					resultCollection = profileIdList(db, user);
				}
				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				db.close();
			}
		}
		return resultCollection;
	}

	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		String hiddenAction = null;
		User user  = (User) object;
		hiddenAction = user.getHiddenAction();
		if (hiddenAction != null) {
			try{
				db = getDbConnection();
				if (hiddenAction.equals("createAccount")) {
					result = createAccount(db, user);
				}else if (hiddenAction.equals("confirmNewUserMail")) {
					result = confirmNewUserMailId(db, user);
				}else if (hiddenAction.equals("isEmailExist")){
					result = isEmailExist(db,user);
				}
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				db.close();
			}

		}
		return result;
	}
	
	public boolean update(BusinessObject object) throws EventPortalException {
		boolean result = false;
		String hiddenAction = null;
		User user  = (User) object;
		hiddenAction = user.getHiddenAction();
		if (hiddenAction != null) {
			try{
				db = getDbConnection();	
				if(hiddenAction.equals("UpdateUserProfileSettings")){
					result = UpdateUserProfileSettings(db, user);
				}
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				db.close();
			}

		}
		return result;
	}
	
	public boolean delete(BusinessObject object) throws EventPortalException {
		boolean result = false;
		String hiddenAction = null;
		User user  = (User) object;
		hiddenAction = user.getHiddenAction();
		if (hiddenAction != null) {
			try{
				db = getDbConnection();				
			} catch (Exception e) {
				e.printStackTrace();				
			} finally {
				db.close();
			}

		}
		return result;
	}
	
	
	/**
	 * method - createAccount - To create an account for new user
	 * param Object
	 * return Collection
	 */
	public boolean createAccount(ObjectContainer dbContainer, User user) {
		List userList = null;
		userList = new ArrayList();		
		boolean saveUserInfo = false;
		UserPOJO userPOJO = null;
		ProfilePOJO profilePOJO = null;
		String pwd = null;
		String profileId = null;
		String userId = null;
		User user1 = new User();
		boolean isEmailIDExist = false;

		try {
			//db = getDbConnection();
			if(user!=null){				
				isEmailIDExist = isEmailExist(dbContainer, user);
				if(!isEmailIDExist){
	//saveProfilePOJO(user,db);
	//profilePOJO = readProfilePOJO(user, db);
	//user.setProfileId(profilePOJO.getProfileId());					
					saveUserPOJO(dbContainer,user);				
					saveUserInfo = true;
				}else {
					throw new UserAlreadyExistException("E-Mail ID already Exist");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return saveUserInfo;
	}
	
	
	/**
	 * method - createAccount - To check whether the user Email is already exist
	 * param ObjectContainer, String
	 * return boolean
	 */
	public boolean isEmailExist(ObjectContainer dbContainer, User user) {
		boolean isEmailIDExist = false;
		String emailId = null;
		if (user != null) {
			emailId = user.geteMail();
			ObjectSet<UserPOJO> results = dbContainer.get(new UserPOJO(emailId, null));
			if (!results.isEmpty()) {
				isEmailIDExist = true;
			}
		}
		return isEmailIDExist;
	}

	
	/**
	 * method - confirmNewUserMailId
	 * 
	 * 
	 */
	public boolean confirmNewUserMailId(ObjectContainer dbContainer, User user){	
		boolean userLoginStatus = false;
		UserPOJO userPOJO = null;
		try {						
			if (user != null) {		
				userLoginStatus = updateUserPOJO(dbContainer,user);
			}
		} catch(Exception e){
			e.printStackTrace();
		} 
		return userLoginStatus;
	}
	

	
	public List getAdminProfileId(ObjectContainer dbContainer) throws EventPortalException{
		List adminDataList = null;
		UserPOJO userPOJO = null;
		User user = null;
		try {			
			userPOJO = getUserPOJOByUserType(dbContainer, "admin");			
			if(userPOJO!= null){
				user = new User();
				user = convertUserPOJOToUser(userPOJO, user);
				adminDataList = new ArrayList();
				adminDataList.add(user);
			}			
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			dbContainer.close();
		}
		return adminDataList;
	}

	
	public UserPOJO readUserPOJO(ObjectContainer dbContainer, User user){
		UserPOJO userPOJO = null;
		try {					
			if(user!=null){
				ObjectSet<UserPOJO> result = dbContainer.get(new UserPOJO(user.geteMail(), null));
				while(result.hasNext()){
					userPOJO = result.next();					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userPOJO;
	}	
	
	
	public boolean saveUserPOJO(ObjectContainer dbContainer, User user){
		boolean isUserPOJOSave = false;
		boolean isEmailIDExist = false;
		String userId = null;
		UserPOJO userPOJO = null;
		try {
			userId = getNewId();
			userPOJO = new UserPOJO();			
			if (user.geteMail() != null) {
				isEmailIDExist = isEmailExist(dbContainer, user);
				if(!isEmailIDExist){
					userPOJO = convertUserToUserPOJO(user, userPOJO);
					userPOJO.setUserId(userId);
					userPOJO.setUserEmailStatus("N");
					userPOJO.setUserStatus("N");
					userPOJO.setUserType("attendee");				
					dbContainer.store(userPOJO);
					dbContainer.commit();
					isUserPOJOSave = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUserPOJOSave;
	}

	
	public boolean updateUserPOJO(ObjectContainer dbContainer, User user){
		boolean isUserPOJOUpdate = false;
		String userId = null;
		UserPOJO userPOJO = null;
		ProfilePOJO profilePOJO = null;
		try {							
			if(user!=null){
				userId = user.getUserId();	
				userPOJO = getUserPOJOByUserId(dbContainer, userId);
				if(userPOJO!=null){				
					if(userPOJO.getUserId()!=null && userPOJO.getUserId().equalsIgnoreCase(user.getUserId())){
						if(userPOJO.getUserEmailStatus()!=null){
							userPOJO.setUserEmailStatus("Y");
						}
					}
					dbContainer.store(userPOJO);
					dbContainer.commit();
					isUserPOJOUpdate = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUserPOJOUpdate;
	}

	public List getUserData(ObjectContainer dbContainer, User user) {		
		List userDataList = null;
		UserPOJO userPOJO = null;				
		userPOJO = getUserPOJOByUserId(dbContainer, user.getUserId());		
		if(userPOJO!= null){			
			user = convertUserPOJOToUser(userPOJO, user);
			userDataList = new ArrayList();
			userDataList.add(user);
		}		
		return userDataList;
	}
	
	public List getUserByEmailId(ObjectContainer dbContainer, User user) {		
		List userDataList = null;
		UserPOJO userPOJO = null;				
		userPOJO = getUserPOJOByUserEmailId(dbContainer, user.geteMail());		
		if(userPOJO!= null){			
			user = convertUserPOJOToUser(userPOJO, user);
			userDataList = new ArrayList();
			userDataList.add(user);
		}		
		return userDataList;
	}

	
	private UserPOJO getUserPOJOByUserId(ObjectContainer dbContainer, String userId){		
		UserPOJO userPOJO = null;
		try{
			ObjectSet<UserPOJO> userPOJOSet = dbContainer.get(new UserPOJO(userId));
			if(!userPOJOSet.isEmpty()){			
				userPOJO = userPOJOSet.next();
			}
		}catch(Exception e){
			e.printStackTrace();
		}			
		return userPOJO;
	}

	private UserPOJO getUserPOJOByUserEmailId(ObjectContainer dbContainer, String userEmailId){		
		UserPOJO userPOJO = null;
		try{
			ObjectSet<UserPOJO> userPOJOSet = dbContainer.get(new UserPOJO(userEmailId,null));
			if(!userPOJOSet.isEmpty()){			
				userPOJO = userPOJOSet.next();
			}
		}catch(Exception e){
			e.printStackTrace();
		}			
		return userPOJO;
	}

	private UserPOJO getUserPOJOByUserType(ObjectContainer dbContainer, String userType){	
		UserPOJO userPOJO = null;		
		try{			
			ObjectSet<UserPOJO> userPOJOSet = dbContainer.get(new UserPOJO(null,userType,null,null));			
			if(!userPOJOSet.isEmpty()){			
				userPOJO = userPOJOSet.next();
			}				
			}catch(Exception e){
				e.printStackTrace();
			}			
		return userPOJO;
	}
	
	
	public UserPOJO convertUserToUserPOJO(User user, UserPOJO userPOJO){		
		if (user.geteMail() != null) {
			userPOJO.setUserEmailId(user.geteMail());
		}
		if (user.getUserPassword() != null) {
			userPOJO.setUserPassword(user.getUserPassword());
		}
		if(user.getProfileId()!=null){
			userPOJO.setUserProfileId(user.getProfileId());
		}
		return userPOJO;
	}
	
	
	private User convertUserPOJOToUser(UserPOJO userPOJO, User user) {		
		if(userPOJO != null){		
			if(userPOJO.getUserId() != null){
				user.setUserId(userPOJO.getUserId());
			}
			if(userPOJO.getUserProfileId() != null){
				user.setProfileId(userPOJO.getUserProfileId());
			}
			
			if(userPOJO.getUserPassword() != null){
				user.setUserPassword(userPOJO.getUserPassword());
			}
			
			if(userPOJO.getUserType() != null){
				user.setUserType(userPOJO.getUserType());
			}
			
			if(userPOJO.getUserStatus() != null){
				user.setUserStatus(userPOJO.getUserStatus());
			}
			
			if(userPOJO.getUserEmailId() != null){
				user.seteMail(userPOJO.getUserEmailId());
			}			
		}	
		return user;
	}

	private Collection profileIdList(ObjectContainer db, User user) {
		
		List profileList = null;
		
		List userIdList = null;
		String userId  = null;
		String profileId = null;
		UserPOJO userPOJO = null;
		
		profileList = new ArrayList();
		
		userIdList = user.getUserIdList();
		
		Iterator it = userIdList.iterator();
		
		while(it.hasNext()){
			
			userId = it.next().toString();
			
			userPOJO = getUserPOJOByUserId(db, userId);
			
			if(userPOJO!= null){
				
				profileId = userPOJO.getUserProfileId();
				
				profileList.add(profileId);

			}
		}

		return profileList;
	}
	
	

public boolean UpdateUserProfileSettings(ObjectContainer dbContainer, User user){
		
		UserPOJO userPOJO = null;
		
		String profileId = null;
		
		boolean updateResult = false;
		
		try{
		
		profileId = user.getProfileId();
		
		ObjectSet<UserPOJO> userPOJOSet = dbContainer.get(new UserPOJO(profileId,null,null));
		
		if(!userPOJOSet.isEmpty()){	
			
			userPOJO = userPOJOSet.next();
			userPOJO = convertUserToUserPOJO(user, userPOJO);
			
			dbContainer.store(userPOJO);
			
			dbContainer.commit();
			
			updateResult = true;
			
		}else{
			updateResult = false;
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return updateResult;
	}




}