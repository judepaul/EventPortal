package com.eventattend.portal.model.db4o;

import java.sql.Timestamp;
import java.util.Date;

public class EventPOJO {

		private String eventId;
		private String eventName;
		private String eventStartDate;
		private String eventEndDate;
		private String eventStartTime;
		private String eventEndTime;
		private String eventDescription;
		private String eventLocation;
		private String eventNoOfDays;
		private String eventOrganizedBy;
		private String eventWebsite;
		private String eventTag;
		private String eventCountry;
		private String eventCity;
		private String eventState;
		private String eventTimeZone;
		
		
		private String eventLikeCount = null;
		
		public EventPOJO() {			
		}		
		public EventPOJO(String eventId) {
			super();
			this.eventId = eventId;
		}	
		public String getEventId() {
			return eventId;
		}

		
		public void setEventId(String eventId) {
			this.eventId = eventId;
		}


		public String getEventName() {
			return eventName;
		}


		public void setEventName(String eventName) {
			this.eventName = eventName;
		}


		public String getEventStartDate() {
			return eventStartDate;
		}


		public void setEventStartDate(String eventStartDate) {
			this.eventStartDate = eventStartDate;
		}


		public String getEventEndDate() {
			return eventEndDate;
		}


		public void setEventEndDate(String eventEndDate) {
			this.eventEndDate = eventEndDate;
		}


		public String getEventStartTime() {
			return eventStartTime;
		}


		public void setEventStartTime(String eventStartTime) {
			this.eventStartTime = eventStartTime;
		}


		public String getEventEndTime() {
			return eventEndTime;
		}


		public void setEventEndTime(String eventEndTime) {
			this.eventEndTime = eventEndTime;
		}


		public String getEventDescription() {
			return eventDescription;
		}


		public void setEventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
		}


		public String getEventLocation() {
			return eventLocation;
		}


		public void setEventLocation(String eventLocation) {
			this.eventLocation = eventLocation;
		}


		


		public String getEventOrganizedBy() {
			return eventOrganizedBy;
		}


		public void setEventOrganizedBy(String eventOrganizedBy) {
			this.eventOrganizedBy = eventOrganizedBy;
		}


		public String getEventWebsite() {
			return eventWebsite;
		}


		public void setEventWebsite(String eventWebsite) {
			this.eventWebsite = eventWebsite;
		}
	
		public String getEventTag() {
			return eventTag;
		}

		public void setEventTag(String eventTag) {
			this.eventTag = eventTag;
		}

		
		public String getEventNoOfDays() {
			return eventNoOfDays;
		}
		public void setEventNoOfDays(String eventNoOfDays) {
			this.eventNoOfDays = eventNoOfDays;
		}
		public String getEventLikeCount() {
			return eventLikeCount;
		}
		public void setEventLikeCount(String eventLikeCount) {
			this.eventLikeCount = eventLikeCount;
		}
		public String getEventCountry() {
			return eventCountry;
		}
		public void setEventCountry(String eventCountry) {
			this.eventCountry = eventCountry;
		}
		public String getEventCity() {
			return eventCity;
		}
		public void setEventCity(String eventCity) {
			this.eventCity = eventCity;
		}
		public String getEventState() {
			return eventState;
		}
		public void setEventState(String eventState) {
			this.eventState = eventState;
		}
		public String getEventTimeZone() {
			return eventTimeZone;
		}
		public void setEventTimeZone(String eventTimeZone) {
			this.eventTimeZone = eventTimeZone;
		}


}
