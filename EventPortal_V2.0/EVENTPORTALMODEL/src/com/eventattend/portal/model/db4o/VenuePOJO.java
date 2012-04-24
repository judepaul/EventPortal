
package com.eventattend.portal.model.db4o;

public class VenuePOJO {

	private String venueId;
	private String venueName;
	public VenuePOJO() {			
	}
	public VenuePOJO(String venueId){
		super();
		this.venueId = venueId;
	}
	
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
}
