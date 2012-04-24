package com.eventattend.portal.model.db4o.query;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.eventattend.portal.model.db4o.EventPOJO;
import com.eventattend.portal.model.db4o.SessionPOJO;

public class EventDB4O {

	
	public static ObjectSet<EventPOJO> getEventDetailForEventId(ObjectContainer db, String eventId){
		
		ObjectSet<EventPOJO> eventPOJOResults = null;
		
		eventPOJOResults = db.get(new EventPOJO(eventId));
		
		if(eventPOJOResults.isEmpty()){
			
			eventPOJOResults = null;
		}
	
		return eventPOJOResults;
		
	}
	
}
