package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.model.db4o.UserEventMapPOJO;
import com.eventattend.portal.model.db4o.UserSessionMapPOJO;

public class UserSessionMapDB4O {

	
	public static ObjectSet<UserSessionMapPOJO> getAttendedSessionIdForUserId(ObjectContainer db, String userId) throws DatabaseException{
		
		ObjectSet<UserSessionMapPOJO> userSessionMapPOJOResults = null;
		
		userSessionMapPOJOResults = db.get(new UserSessionMapPOJO(userId,null,null));
		
		if(userSessionMapPOJOResults.isEmpty()){
			
			userSessionMapPOJOResults = null;
		}
	
		return userSessionMapPOJOResults;
		
	}
	
}
