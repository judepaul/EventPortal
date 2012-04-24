package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.model.db4o.UserPOJO;

public class UserDB4O {

	
	public static ObjectSet<UserPOJO> getUserDetails(ObjectContainer db) throws DatabaseException{
		
		ObjectSet<UserPOJO> userPOJOResults = null;
		
		userPOJOResults = db.get(new UserPOJO());
		
		if(userPOJOResults.isEmpty()){
			
			userPOJOResults = null;
		}
	
		return userPOJOResults;
		
	}
	
	public static ObjectSet<UserPOJO> getUserDetailsForProfileId(ObjectContainer db, String profileId) throws DatabaseException{
		
		ObjectSet<UserPOJO> userPOJOResults = null;
		
		userPOJOResults = db.get(new UserPOJO(profileId,"",""));
		
		if(userPOJOResults.isEmpty()){
			
			userPOJOResults = null;
		}
	
		return userPOJOResults;
		
	}
	
	
}
