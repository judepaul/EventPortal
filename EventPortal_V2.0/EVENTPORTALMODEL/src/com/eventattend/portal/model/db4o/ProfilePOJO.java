package com.eventattend.portal.model.db4o;

import java.io.File;

import com.db4o.ext.Status;
import com.db4o.types.Blob;

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
 * Sep 27, 2010 - A.Jude created the file.
 * 
 */

public class ProfilePOJO {

	private String profileId;
	private String profileFirstName;
	private String profileEmail;
	private String profileLastName;
	private String profileGender;
	private Blob profileImage;
	private String imgLocation;
	private String profileAlternateEmail;
	private String profileMobilePhone;
	private String profileHomePhone;
	private String profileOfficePhone;
	private String profileAddress;
	private String profileCity;
	private String profileZip;
	private String profileState;
	private String profileCountry;
	private String profileEducation;
	private String profileOccupation;
	private String profileWebsite;
	private String profileImgLocation;
	private String profileKeyNote;
	private String profileTimeZone;
	
	private boolean profileChkSpeaker = false;
	private String profileEvent = null;
	private String profileSession = null;
	
	private String speakerKeyNotes = null;

	public ProfilePOJO(){
		
	}	
	public ProfilePOJO(String profileId){
		this.profileId = profileId;
	}	
	public ProfilePOJO(String profileEmailId, String id1){
		this.profileEmail = profileEmailId;
	}	

	
	public String getProfileEmail() {
		return profileEmail;
	}


	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}


	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileFirstName() {
		return profileFirstName;
	}
	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}
	public String getProfileLastName() {
		return profileLastName;
	}
	public void setProfileLastName(String profileLastName) {
		this.profileLastName = profileLastName;
	}	
	public String getProfileGender() {
		return profileGender;
	}
	public void setProfileGender(String profileGender) {
		this.profileGender = profileGender;
	}	
	public String getProfileAlternateEmail() {
		return profileAlternateEmail;
	}
	public void setProfileAlternateEmail(String profileAlternateEmail) {
		this.profileAlternateEmail = profileAlternateEmail;
	}
	public String getProfileMobilePhone() {
		return profileMobilePhone;
	}
	public void setProfileMobilePhone(String profileMobilePhone) {
		this.profileMobilePhone = profileMobilePhone;
	}
	public String getProfileHomePhone() {
		return profileHomePhone;
	}
	public void setProfileHomePhone(String profileHomePhone) {
		this.profileHomePhone = profileHomePhone;
	}
	public String getProfileOfficePhone() {
		return profileOfficePhone;
	}
	public void setProfileOfficePhone(String profileOfficePhone) {
		this.profileOfficePhone = profileOfficePhone;
	}
	public String getProfileAddress() {
		return profileAddress;
	}
	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}
	public String getProfileCity() {
		return profileCity;
	}
	public void setProfileCity(String profileCity) {
		this.profileCity = profileCity;
	}
	public String getProfileZip() {
		return profileZip;
	}
	public void setProfileZip(String profileZip) {
		this.profileZip = profileZip;
	}
	public String getProfileState() {
		return profileState;
	}
	public void setProfileState(String profileState) {
		this.profileState = profileState;
	}
	public String getProfileCountry() {
		return profileCountry;
	}
	public void setProfileCountry(String profileCountry) {
		this.profileCountry = profileCountry;
	}
	public String getProfileEducation() {
		return profileEducation;
	}
	public void setProfileEducation(String profileEducation) {
		this.profileEducation = profileEducation;
	}
	public String getProfileOccupation() {
		return profileOccupation;
	}
	public void setProfileOccupation(String profileOccupation) {
		this.profileOccupation = profileOccupation;
	}
	public String getProfileWebsite() {
		return profileWebsite;
	}
	public void setProfileWebsite(String profileWebsite) {
		this.profileWebsite = profileWebsite;
	}
	
	public String getImgLocation() {
		return imgLocation;
	}
	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}	
	public String getProfileImgLocation() {
		return profileImgLocation;
	}
	public void setProfileImgLocation(String profileImgLocation) {
		this.profileImgLocation = profileImgLocation;
	}
	
	public String getProfileKeyNote() {
		return profileKeyNote;
	}
	public void setProfileKeyNote(String profileKeyNote) {
		this.profileKeyNote = profileKeyNote;
	}	
	
	public String getProfileTimeZone() {
		return profileTimeZone;
	}
	public void setProfileTimeZone(String profileTimeZone) {
		this.profileTimeZone = profileTimeZone;
	}

	public boolean getProfileChkSpeaker() {
		return profileChkSpeaker;
	}
	public void setProfileChkSpeaker(boolean profileChkSpeaker) {
		this.profileChkSpeaker = profileChkSpeaker;
	}

	public String getProfileEvent() {
		return profileEvent;
	}
	public void setProfileEvent(String profileEvent) {
		this.profileEvent = profileEvent;
	}

	public String getProfileSession() {
		return profileSession;
	}
	public void setProfileSession(String profileSession) {
		this.profileSession = profileSession;
	}

	public String getSpeakerKeyNotes() {
		return speakerKeyNotes;
	}
	public void setSpeakerKeyNotes(String speakerKeyNotes) {
		this.speakerKeyNotes = speakerKeyNotes;
	}


	// For profile image store
	public boolean readProfileImgFile(String profileImgFile) throws java.io.IOException {
		//profileLargePhoto.readFrom(new File(fileName));
		 
		profileImage.readFrom(new File(profileImgFile));	 	
		double status = profileImage.getStatus();
		while (status > Status.COMPLETED) {
			try {
				Thread.sleep(50);
				status = profileImage.getStatus();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return (status == Status.COMPLETED);
	}
	
	
	// To retrieve profile image store
	public boolean writeUserImgFile(String basePath) throws java.io.IOException {

		profileImage.writeTo(new File(basePath +System.getProperty("file.separator")+profileImage.getFileName()));
		double status = profileImage.getStatus();
		while (status > Status.COMPLETED) {
			try {
				Thread.sleep(50);
				status = profileImage.getStatus();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return (status == Status.COMPLETED);
	}

	
}