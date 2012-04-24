package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.model.db4o.UserEventMapPOJO;
import com.eventattend.portal.model.db4o.UserPOJO;

public class UserEventMapDB4O {

public static ObjectSet<UserEventMapPOJO> getJoinedEventIdForUserId(ObjectContainer db, String userId) throws DatabaseException{
		
		ObjectSet<UserEventMapPOJO> userEventMapPOJOResults = null;
		
		userEventMapPOJOResults = db.get(new UserEventMapPOJO(userId,null,null));
		
		if(userEventMapPOJOResults.isEmpty()){
			
			userEventMapPOJOResults = null;
		}
	
		return userEventMapPOJOResults;
		
	}
	
	
}
