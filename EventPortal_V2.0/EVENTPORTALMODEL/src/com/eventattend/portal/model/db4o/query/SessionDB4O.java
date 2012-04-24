package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.model.db4o.SessionPOJO;
import com.eventattend.portal.model.db4o.SessionSpeakerMapPOJO;

public class SessionDB4O {

	
	public static ObjectSet<SessionPOJO> getSessionDetailForSessionId(ObjectContainer db, String sessionId){
		
		ObjectSet<SessionPOJO> sessionPOJOResults = null;		
		
		sessionPOJOResults = db.get(new SessionPOJO(sessionId, ""));
	
		if(sessionPOJOResults.isEmpty()){
			sessionPOJOResults = null;
		}
		
		return sessionPOJOResults;
		
	}
	
public static ObjectSet<SessionPOJO> getSessions(ObjectContainer db){
		
		ObjectSet<SessionPOJO> sessionPOJOResults = null;		
		
		sessionPOJOResults = db.get(new SessionPOJO());
	
		if(sessionPOJOResults.isEmpty()){
			sessionPOJOResults = null;
		}
		
		return sessionPOJOResults;
		
	}

public static boolean isDataExist(ObjectContainer db,String sessionId) {
	boolean dataExist = false;

	ObjectSet<SessionPOJO> sessionPOJOResults = null;
	
	sessionPOJOResults = db.get(new SessionPOJO(sessionId,""));
			if(!sessionPOJOResults.isEmpty()){
				dataExist = true;
			}
	
	return dataExist;
	
}


public static boolean isDataEventExist(ObjectContainer db,String eventId) {
	boolean dataExist = false;

	ObjectSet<SessionPOJO> sessionPOJOResults = null;
	
	sessionPOJOResults = db.get(new SessionPOJO(eventId));
			if(!sessionPOJOResults.isEmpty()){
				dataExist = true;
			}
	
	return dataExist;
	
}
public static boolean deleteSessionPOJO(ObjectContainer db, String sessionId) 
throws DatabaseException{
	
	ObjectSet<SessionPOJO> sessionPOJOResults = null;
	
	SessionPOJO sessionPOJO = null;
	
	sessionPOJOResults = db.get(new SessionPOJO(sessionId,""));
	
	sessionPOJO = sessionPOJOResults.next();
	
	db.delete(sessionPOJO);
	
	db.commit();
	
	return true;
}
	
}
