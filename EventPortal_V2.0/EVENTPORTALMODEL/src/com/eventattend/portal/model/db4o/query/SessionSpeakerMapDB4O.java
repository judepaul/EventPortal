package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.model.db4o.SessionSpeakerMapPOJO;

public class SessionSpeakerMapDB4O extends DB4O  {

	
	
	public static ObjectSet<SessionSpeakerMapPOJO> getSessionIdForSpeakerUserId(ObjectContainer db, String userId){
		
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = null;
		
		sessionSpeakerMapPOJOResults = db.get(new SessionSpeakerMapPOJO(userId, ""));
	
		if(sessionSpeakerMapPOJOResults.isEmpty()){
			
			sessionSpeakerMapPOJOResults = null;
			
		}
		
		return sessionSpeakerMapPOJOResults;
		
	}
	
	public static boolean saveSessionSpeakerMapPOJO(ObjectContainer db, String ssmId, String sessionId, String userId) 
	throws DatabaseException{
		
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		
		sessionSpeakerMapPOJO = new SessionSpeakerMapPOJO();
		
		sessionSpeakerMapPOJO.setSsmId(ssmId);
		
		sessionSpeakerMapPOJO.setSsmSessionId(sessionId);
		
		sessionSpeakerMapPOJO.setSsmSpeakerId(userId);
		
		db.store(sessionSpeakerMapPOJO);
		
		db.commit();
		
		return true;
	}
	
	public static boolean deleteSessionSpeakerMapPOJO(ObjectContainer db, String sessionId, String userId) 
	throws DatabaseException{
		
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = null;
		
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		
		sessionSpeakerMapPOJOResults = db.get(new SessionSpeakerMapPOJO(sessionId,userId,""));
		
		sessionSpeakerMapPOJO = sessionSpeakerMapPOJOResults.next();
		
		db.delete(sessionSpeakerMapPOJO);
		
		db.commit();
		
		return true;
	}
	
	
	
	
	public static boolean isDataExist(ObjectContainer db,String sessionId, String userId) {
		boolean dataExist = false;
	
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = null;
		
		sessionSpeakerMapPOJOResults = db.get(new SessionSpeakerMapPOJO(sessionId,userId,""));
				if(!sessionSpeakerMapPOJOResults.isEmpty()){
					dataExist = true;
				}
		
		return dataExist;
		
	}
	
	
	
}
