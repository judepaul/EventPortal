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
 * March 1, 2011 veeresh created the file.
 * 
 */
package com.eventattend.portal.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.eventattend.portal.DAO.Blob.BlobImageRetrival;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.User;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.db4o.util.Db4oUtil;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.model.db4o.AttendeesEmailPOJO;
import com.eventattend.portal.model.db4o.EventPOJO;
import com.eventattend.portal.model.db4o.KeysPOJO;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.SMProfilePOJO;
import com.eventattend.portal.model.db4o.SessionPOJO;
import com.eventattend.portal.model.db4o.SessionSpeakerMapPOJO;
import com.eventattend.portal.model.db4o.UserEventMapPOJO;
import com.eventattend.portal.model.db4o.UserPOJO;
import com.eventattend.portal.model.db4o.UserSessionMapPOJO;
import com.eventattend.portal.model.db4o.query.EventDB4O;
import com.eventattend.portal.model.db4o.query.SessionDB4O;
import com.eventattend.portal.model.db4o.query.SessionSpeakerMapDB4O;
import com.eventattend.portal.model.db4o.query.UserDB4O;
import com.eventattend.portal.model.db4o.query.UserEventMapDB4O;
import com.eventattend.portal.model.db4o.query.UserSessionMapDB4O;

/**
 * Class Description
 * 
 * @version beta
 * @author Veeresh
 * @Date March 1, 2011
 * 
 *       Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved. This
 *       software is the proprietary information of Kyyba Ventures Inc. Use is
 *       subject to license terms.
 */
public class ProfileDAO extends  DataAccessObject{

	ObjectContainer db = null;
	
	/**
	 * @method- read - To read/access the list
	 * @param object	     
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection read(BusinessObject object) throws EventPortalException {
		Profile profile = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		String profileId = null;
		profile = (Profile) object;
		hiddenAction = profile.getHiddenAction();

		if (hiddenAction != null) {
			try{
				db = getDbConnection();
				if (hiddenAction.equals("UPDATEUSERPROFILEPIC")) {
					resultCollection = updateProfilePic(db,profile);
				
				}else if (hiddenAction.equals("PROFILEDATALIST")) {					
					resultCollection = profileDataList(db,profile);
				}else if (hiddenAction.equals("PROFILEDATA")) {
					resultCollection = getProfileData(db,profile);				
				}else if (hiddenAction.equals("USERPROFILEINFO")) {
					resultCollection = userProfileInfo(db,profile);
				}else if (hiddenAction.equals("ADMINPROFILEINFO")) {
						//profileId = getAdminProfileId(db);
						//profile.setProfileId(profileId);				
						//resultCollection = userProfileInfo(db,profile);
				}else if (hiddenAction.equals("GETPROFILEIDBYEMAIL")) {
					resultCollection = getProfileIdByEmail(db, profile);				
				}else if (hiddenAction.equals("socialMediaImageURL")) {
					//resultCollection = getSocialMediaImageURL(profile);
				}else if (hiddenAction.equals("sendMailToAttendees")) {
					resultCollection = getAttendeesEmailInfo(db,profile);
				}else if (hiddenAction.equals("getMailOptionsForAttendees")) {
					resultCollection = getMailOptionsForAttendees(profile);
				}else if (hiddenAction.equals("ATTENDEE")) {
					resultCollection = attendee(db);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				db.close();
			}
		}
		return resultCollection;
	}
	

	/**
	 * @method- save - To save/update the list
	 * @param object	     
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		Profile profile = null;		
		String hiddenAction = null;
		profile = (Profile) object;
		hiddenAction = profile.getHiddenAction();
		if (hiddenAction != null) {
			try{
				db = getDbConnection();
				if (hiddenAction.equals("SAVEPROFILEPOJO")) {
					result = saveProfilePOJO(db,profile);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				db.close();
			}
		}
		return result;
	}
	

	/**
	 * @method- update - To update the list
	 * @param object	     
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public boolean update(BusinessObject object) throws EventPortalException {		
		Profile profile = (Profile) object;
		boolean updateResult = false;
		try{		
			if(profile != null){			
				db = getDbConnection();			
				if(profile.getHiddenAction().equals("UpdateSocialMediaProfileURL")){			
					//updateResult = updateSocialMediaProfile(db, profile);			
				}else if(profile.getHiddenAction().equals("UPDATEPROFILESETTINGS")){			
					updateResult = updateProfileSettings1(db, profile);		
				}else{			
				}			
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			db.close();
		}		
		return updateResult;
	}

	private boolean populateProfilePOJO(ObjectContainer db,ProfilePOJO profilePOJO, Profile profile) {		
		boolean updateResult = false;
		if(profilePOJO != null && profile != null){			
			profilePOJO.setProfileId(profile.getProfileId());		
			profilePOJO.setProfileFirstName(profile.getFirstName());
			profilePOJO.setProfileLastName(profile.getLastName());
			profilePOJO.setProfileGender(profile.getGender());
			profilePOJO.setProfileEmail(profile.getProfileEmail());		
			profilePOJO.setProfileImgLocation(profile.getProfilePicture());	
			profilePOJO.setProfileAlternateEmail(profile.getAlternativeEmail());						
			profilePOJO.setProfileMobilePhone(profile.getMobile());
			profilePOJO.setProfileHomePhone(profile.getLandHome());
			profilePOJO.setProfileOfficePhone(profile.getLandOffice());
			profilePOJO.setProfileAddress(profile.getAddress());						
			profilePOJO.setProfileCity(profile.getCity());
			profilePOJO.setProfileZip(profile.getZip());						
			profilePOJO.setProfileState(profile.getState());
			profilePOJO.setProfileCountry(profile.getCountry());
			profilePOJO.setProfileEducation(profile.getEducation());
			profilePOJO.setProfileOccupation(profile.getOccupation());						
			profilePOJO.setProfileWebsite(profile.getWebsite());			
			profilePOJO.setProfileTimeZone(profile.getTimeZone());			
			profilePOJO.setProfileChkSpeaker(profile.getSpeakerCheckBox());
			profilePOJO.setProfileEvent(profile.getEventOption());
			profilePOJO.setProfileSession(profile.getSessionOption());
			profilePOJO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
			db.store(profilePOJO);
			db.commit();
			updateResult = true;
		}else {
			updateResult = false;
		}
		
		return updateResult;
	}

	/**
	 * @method- userProfileInfo - To get user profile info
	 * @param profile	     
	 * @return List
	 * @throws EventPortalException 
	 */
	public List userProfileInfo(ObjectContainer db, Profile profile) throws EventPortalException {		
		List userInfoList = null;
		userInfoList = new ArrayList();	
		ProfilePOJO profilePOJO = null;
		if(profile!=null){
			try {
				profilePOJO = readProfilePOJO(profile);
				profile = convertProfilePOJOToProfile(profilePOJO, profile);						
					
				userInfoList.add(profile);
			} catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			} 
		}		
		return userInfoList;
	}
	
	
/*	public String getAdminProfileId() throws EventPortalException{
		String adminProfileId = null;
		UserPOJO userPOJO = null;
		try {
			userPOJO = readUserPOJO(null);
			adminProfileId =  userPOJO.getUserProfileId();
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return adminProfileId;
	}

*/	
	// Start of store profile Image
	private static void storeProfileImage(ProfilePOJO profileImg) {
		try {
			
			if (profileImg.getProfileImgLocation() != null
					&& !profileImg.getProfileImgLocation().equals("")) {
				profileImg.readProfileImgFile(profileImg.getProfileImgLocation());
			}
		} catch (java.io.IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	// end of store profile Image

	private String retrieveUserProfileImgFile(String profileId) throws EventPortalException {
		String getImageLoc = null;
		try {
			db = getDbConnection();
			ProfilePOJO query = new ProfilePOJO(profileId);
			ObjectSet result = db.get(query);
			getImageLoc = getUserProfileImages(result);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	return getImageLoc;
	}	

	private String getUserProfileImages(List<ProfilePOJO> result) {
		Profile profile = null;
		String destFile = null;
		for (ProfilePOJO profilePOJO : result) {
			profile = new Profile();
			destFile = profilePOJO.getProfileImage().getFileName();
			//destFile = basePath +System.getProperty("file.separator")+profilePOJO.getProfileLargePhoto().getFileName();
			// System.out.println(destFile);
			try {
				profilePOJO.writeUserImgFile(Db4oUtil.imageBasePath);
			} catch (Exception ex) {
				System.out.print(ex.getMessage());
			}
		}
	return destFile;
	}
	
	
	/**
	 * @method- getMailOptionsForAttendees - To get attendees email count info
	 * @param profile	     
	 * @return List
	 * @throws EventPortalException 
	 */
	public List getMailOptionsForAttendees(Profile profile) throws EventPortalException {
		String profileId = null;
		List profileEmailInfoList = null;
		profileEmailInfoList = new ArrayList();		
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		if(profile!=null && !profile.equals("")){
			
			try {
				attendeesEmailPOJO = readAttendeesEmailPOJO(profile);
				if(attendeesEmailPOJO!=null){
					profile.setProfileMailCount(attendeesEmailPOJO.getEmailCount());
				}
				profileEmailInfoList.add(profile);
			} catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			} 
		}		
		return profileEmailInfoList;
	}
	
	
	/**
	 * @method- getAttendeesEmailInfo - To get attendees email info
	 * @param profile	     
	 * @return List
	 * @throws EventPortalException 
	 */
	public List getAttendeesEmailInfo(ObjectContainer db, Profile profile) throws EventPortalException {
		String profileId = null;
		List profileEmailInfoList = null;
		profileEmailInfoList = new ArrayList();
		profileId = profile.getProfileId();		
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		boolean isAttendeeMailExist = false;
		boolean saveAttendeeMail = false;
		boolean updateAttendeeMail = false;
		
		if(profileId!=null && !profileId.equals("")){
			try {
				isAttendeeMailExist = isAttendeesEmailExist(db, profile);
				if(!isAttendeeMailExist){
					saveAttendeeMail = saveAttendeesEmailPOJO(db, profile);
				} else {
					updateAttendeeMail = updateAttendeesEmailPOJO(db, profile);
				}
				attendeesEmailPOJO = readAttendeesEmailPOJO(profile);
				if(attendeesEmailPOJO!= null){
					profile.setProfileMailCount(attendeesEmailPOJO.getEmailCount());
				}
								
				profileEmailInfoList.add(profile);

			} catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			}
		}		
		return profileEmailInfoList;
	}

	
	public boolean saveAttendeesEmailPOJO(ObjectContainer db, Profile profile){
		boolean isAttendeesEmailPOJOSave = false;
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		String profileId = null;
		String profileMailFromId = null;
		String profileMailToId = null;

		try {
			if(profile!=null){				
				profileId = profile.getProfileId();
				profileMailFromId = profile.getProfileMailFromId();
				profileMailToId = profile.getProfileMailToId();
					
				attendeesEmailPOJO = new AttendeesEmailPOJO(profileId,profileMailFromId,profileMailToId);			
				attendeesEmailPOJO.setProfileId(profileId);
				attendeesEmailPOJO.setEmailFromId(profile.getProfileMailFromId());
				attendeesEmailPOJO.setEmailToId(profile.getProfileMailToId());
				attendeesEmailPOJO.setEmailContent(profile.getProfileMailContent());				
				isAttendeesEmailPOJOSave = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		return isAttendeesEmailPOJOSave;
	}

	
	public ProfilePOJO readProfilePOJO(Profile profile){
		String profileId = null;		
		ProfilePOJO profilePOJO = null;
		try {
			db = getDbConnection();		
			if(profile!=null){
				profileId = profile.getProfileId();
				ObjectSet<ProfilePOJO> result = db.get(new ProfilePOJO(profileId));
				if (!result.isEmpty()) {
					while(result.hasNext()){
						ProfilePOJO profPOJO = result.next();						
							profilePOJO = profPOJO;															
					}			
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				db.close();
			}

		return profilePOJO;
	}
	
	
	public AttendeesEmailPOJO readAttendeesEmailPOJO(Profile profile){
		String profileId = null;	
		String profileMailFromId = null;
		String profileMailToId = null;
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		try {
			db = getDbConnection();		
			if(profile!=null){
				profileId = profile.getProfileId();
				profileMailFromId = profile.getProfileMailFromId();
				profileMailToId = profile.getProfileMailToId();

				ObjectSet<AttendeesEmailPOJO> result = db.get(new AttendeesEmailPOJO(profileId,profileMailFromId,profileMailToId));
				if (!result.isEmpty()) {
					while(result.hasNext()){
						AttendeesEmailPOJO attdEmailPOJO = result.next();
						attendeesEmailPOJO = attdEmailPOJO;										
					}			
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				db.close();
			}
		return attendeesEmailPOJO;
	}
	
		
	public boolean isAttendeesEmailExist(ObjectContainer db, Profile profile){
		boolean isAttendeesEmailExist = false; 
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		String profileId = null;
		String profileMailFromId = null;
		String profileMailToId = null;
		
		try {				
			if(profile!=null){				
				profileId = profile.getProfileId();
				profileMailFromId = profile.getProfileMailFromId();
				profileMailToId = profile.getProfileMailToId();

				ObjectSet<AttendeesEmailPOJO> result = db.get(new AttendeesEmailPOJO(profileId,profileMailFromId,profileMailToId));
				if (!result.isEmpty()) {
					while (result.hasNext()) {
						isAttendeesEmailExist = true;
					}
				}						
			}
			}catch(Exception e){
				e.printStackTrace();
			} 

		return isAttendeesEmailExist;
	}

	
	
	public boolean saveProfilePOJO(Profile profile){
		boolean isProfilePOJOSave = false;
		ProfilePOJO profilePOJO = null;
		String profileId = null;
		
		try {
			db = getDbConnection();		
			if(profile!=null){
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isProfilePOJOSave;
	}



	public boolean updateAttendeesEmailPOJO(ObjectContainer db, Profile profile){
		boolean isAttendeesEmailPOJOUpdate = false;
		AttendeesEmailPOJO attendeesEmailPOJO = null;
		String profileId = null;
		String profileMailFromId = null;
		String profileMailToId = null;
		
		if(profile!=null && !profile.equals("")){
			profileId = profile.getProfileId();
			profileMailFromId = profile.getProfileMailFromId();
			profileMailToId = profile.getProfileMailToId();
			try {								
				ObjectSet<AttendeesEmailPOJO> results = db.get(new AttendeesEmailPOJO(profileId,profileMailFromId,profileMailToId));
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						attendeesEmailPOJO = (AttendeesEmailPOJO) results.next();						
						if(attendeesEmailPOJO.getEmailCount()>= 0){
							attendeesEmailPOJO.setEmailContent(profile.getProfileMailContent());
							attendeesEmailPOJO.setEmailCount(attendeesEmailPOJO.getEmailCount()+1);			
						}
					}
					db.store(attendeesEmailPOJO);
					db.commit();
					isAttendeesEmailPOJOUpdate = true;	
				}	
			}catch(Exception e){
				e.printStackTrace();
			} 
		}
		return isAttendeesEmailPOJOUpdate;
	}

	public Collection updateProfilePic(ObjectContainer db,Profile profile){
		boolean isProfilePOJOUpdate = false;
		ProfilePOJO profilePOJO = null;
		String profileId = null;
		List profileList = new ArrayList();	
		if(profile!=null && !profile.equals("")){
			profileId = profile.getProfileId();
			try {
				//db = getDbConnection();					
				ObjectSet<ProfilePOJO> results = db.get(new ProfilePOJO(profileId));
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						profilePOJO = (ProfilePOJO) results.next();

						if(profile.getProfilePicture()!=null){
							profilePOJO.setProfileImgLocation(profile.getProfilePicture());
							storeProfileImage(profilePOJO);
						}
						if(profile.getExtProfileImgLocation()!=null){
							profilePOJO.setProfileImgLocation(profile.getExtProfileImgLocation());
						}						
	
						String blobImageFileName = BlobImageRetrival.retrieveUserProfileImgFile(profileId);
						
						blobImageFileName = "/images/"+blobImageFileName;
						
						profile.setProfilePicture(blobImageFileName);						
						profile.setImgLocation(profilePOJO.getProfileImgLocation());
						profilePOJO.setImgLocation(blobImageFileName);
						
						db.store(profilePOJO);
						db.commit();
						isProfilePOJOUpdate = true;	
						profileList.add(profile);
					}			
				}	

			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//db.close();
			}
		}
		return profileList;
	}

	
	
	public boolean updateProfilePOJO(ObjectContainer db,Profile profile){
		boolean isProfilePOJOUpdate = false;
		ProfilePOJO profilePOJO = null;
		String profileId = null;
		
		if(profile!=null && !profile.equals("")){
			profileId = profile.getProfileId();
			try {
				//db = getDbConnection();					
				ObjectSet<ProfilePOJO> results = db.get(new ProfilePOJO(profileId));
				if (!results.isEmpty()) {
					while (results.hasNext()) {
						profilePOJO = (ProfilePOJO) results.next();
						if (profile.getFirstName()!= null){
							profilePOJO.setProfileFirstName(profile.getFirstName());
						}
						if (profile.getLastName()!= null){
							profilePOJO.setProfileLastName(profile.getLastName());
						}
						if(profile.getTimeZone()!=null){
							profilePOJO.setProfileTimeZone(profile.getTimeZone());
						}						
						profilePOJO.setProfileChkSpeaker(profile.getSpeakerCheckBox());	
						if(profile.getEventOption()!=null){
							profilePOJO.setProfileEvent(profile.getEventOption());
						}
						if(profile.getSessionOption()!=null){
							profilePOJO.setProfileSession(profile.getSessionOption());
						}	
						if(profile.getProfilePicture()!=null){
							profilePOJO.setProfileImgLocation(profile.getProfilePicture());
							storeProfileImage(profilePOJO);
						}
						if(profile.getExtProfileImgLocation()!=null){
							profilePOJO.setProfileImgLocation(profile.getExtProfileImgLocation());
						}						
						if (profile.getMobile() != null) {
							profilePOJO.setProfileMobilePhone(profile.getMobile());
						}
						if (profile.getAddress() != null) {
							profilePOJO.setProfileAddress(profile.getAddress());
						}
						if (profile.getCity() != null) {
							profilePOJO.setProfileCity(profile.getCity());
						}
						if (profile.getZip() != null) {
							profilePOJO.setProfileZip(profile.getZip());
						}
						if (profile.getState() != null) {
							profilePOJO.setProfileState(profile.getState());
						}
						if (profile.getCountry() != null) {
							profilePOJO.setProfileCountry(profile.getCountry());
						}
						if (profile.getEducation() != null) {
							profilePOJO.setProfileEducation(profile.getEducation());
						}
						if (profile.getOccupation() != null) {
							profilePOJO.setProfileOccupation(profile.getOccupation());
						}
						if (profile.getWebsite() != null) {
							profilePOJO.setProfileWebsite(profile.getWebsite());
						}
						if (profile.getSpeakerKeyNotes() != null) {
							profilePOJO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
						}

						db.store(profilePOJO);
						db.commit();
						isProfilePOJOUpdate = true;	
					}			
				}	

			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//db.close();
			}
		}
		return isProfilePOJOUpdate;
	}
	

//Refined Methods
	
	private boolean updateProfileSettings1(ObjectContainer dbContainer, Profile profile) {
		boolean updateStatus = false;
		String sessionId = null;
		updateStatus = updateProfilePOJO1(dbContainer, profile);
		return updateStatus;
	}

	
	
	public boolean saveProfilePOJO1(ObjectContainer dbContainer, Profile profile){				
		ProfilePOJO profilePOJO = null;		
		String profileId = null;		
		boolean saveResult = false;		
		try{		
			profileId = getNewId();		
			profilePOJO = new ProfilePOJO();		
			profilePOJO.setProfileId(profileId);		
			profilePOJO = convertProfileToProfilePOJO(profile, profilePOJO);		
			dbContainer.store(profilePOJO);		
			dbContainer.commit();		
			saveResult = true;			
		}catch(Exception e){
			e.printStackTrace();
		}
		return saveResult;
	}
	
	
	public boolean updateProfilePOJO1(ObjectContainer dbContainer, Profile profile){		
		ProfilePOJO profilePOJO = null;		
		String profileId = null;		
		boolean updateResult = false;		
		try{		
			profileId = profile.getProfileId();		
			profilePOJO = getProfilePOJOByProfileId(dbContainer, profileId);		
			if(profilePOJO != null){			
				profilePOJO = convertProfileToProfilePOJO(profile, profilePOJO);			
				dbContainer.store(profilePOJO);			
				dbContainer.commit();			
				updateResult = true;			
			}			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return updateResult;
	}
	
	
	public ProfilePOJO convertProfileToProfilePOJO(Profile profile, ProfilePOJO profilePOJO){
		
		if(profilePOJO != null){
			
			if(profile.getFirstName() != null){
				profilePOJO.setProfileFirstName(profile.getFirstName());
			}	
			
			if(profile.getLastName() != null){
				profilePOJO.setProfileLastName(profile.getLastName());
			}
			
			if(profile.getGender() != null){
				profilePOJO.setProfileGender(profile.getGender());
			}
			
			if(profile.getProfileEmail() != null){
				profilePOJO.setProfileEmail(profile.getProfileEmail());
			}
			
			if(profile.getProfilePicture() != null){
				profilePOJO.setProfileImgLocation(profile.getProfilePicture());
			}		
				
			if(profile.getAlternativeEmail() != null){
				profilePOJO.setProfileAlternateEmail(profile.getAlternativeEmail());
			}
			
			if(profile.getMobile() != null){
				profilePOJO.setProfileMobilePhone(profile.getMobile());
			}
									
			if(profile.getLandHome() != null){
				profilePOJO.setProfileHomePhone(profile.getLandHome());
			}
			
			if(profile.getLandOffice() != null){
				profilePOJO.setProfileOfficePhone(profile.getLandOffice());
			}
			
			if(profile.getAddress() != null){
				profilePOJO.setProfileAddress(profile.getAddress());
			}
			
			if(profile.getCity() != null){
				profilePOJO.setProfileCity(profile.getCity());
			}
			
			if(profile.getZip() != null){
				profilePOJO.setProfileZip(profile.getZip());	
			}
			
			if(profile.getState() != null){
				profilePOJO.setProfileState(profile.getState());
			}
			
			if(profile.getCountry() != null){
				profilePOJO.setProfileCountry(profile.getCountry());
			}
			
			if(profile.getEducation() != null){
				profilePOJO.setProfileEducation(profile.getEducation());
			}
			
			if(profile.getOccupation() != null){
				profilePOJO.setProfileOccupation(profile.getOccupation());		
			}
			
			if(profile.getWebsite() != null){
				profilePOJO.setProfileWebsite(profile.getWebsite());	
			}
			
			if(profile.getTimeZone() != null){
				profilePOJO.setProfileTimeZone(profile.getTimeZone());		
			}
			
			if(profile.getSpeakerCheckBox()){
				profilePOJO.setProfileChkSpeaker(profile.getSpeakerCheckBox());
			}
				
			if(profile.getEventOption() != null){
				profilePOJO.setProfileEvent(profile.getEventOption());
			}
			
			if(profile.getSessionOption() != null){
				profilePOJO.setProfileSession(profile.getSessionOption());
			}
			
			if(profile.getSpeakerKeyNotes() != null){
				profilePOJO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
			}
			
		}
		
		return profilePOJO;		
	}
	
	
	
	public Profile convertProfilePOJOToProfile(ProfilePOJO profilePOJO, Profile profile){		
		if(profile != null){
			
			if(profilePOJO.getProfileId() != null){
				profile.setProfileId(profilePOJO.getProfileId());
			}	
			
			if(profilePOJO.getProfileFirstName() != null){
				profile.setFirstName(profilePOJO.getProfileFirstName());
			}			
			if(profilePOJO.getProfileLastName() != null){
				profile.setLastName(profilePOJO.getProfileLastName());
			}			
			if(profilePOJO.getProfileGender() != null){
				profile.setGender(profilePOJO.getProfileGender());
			}			
			if(profilePOJO.getProfileEmail() != null){
				profile.setProfileEmail(profilePOJO.getProfileEmail());
			}			
			if(profilePOJO.getProfileImgLocation() != null){
				
				if(profilePOJO.getImgLocation() != null){
					String imgLocation = BlobImageRetrival.retrieveUserProfileImgFile(profile.getProfileId());
					imgLocation = "/images/"+imgLocation;
					
					profile.setImgLocation(imgLocation);
					System.out.println("Image path in DAO==>"+imgLocation);
				}
					profile.setImgLocation(profilePOJO.getProfileImgLocation());
				
				
			}				
			if(profilePOJO.getProfileAlternateEmail() != null){
				profile.setAlternativeEmail(profilePOJO.getProfileAlternateEmail());
			}			
			if(profilePOJO.getProfileMobilePhone() != null){
				profile.setMobile(profilePOJO.getProfileMobilePhone());
			}				
			if(profilePOJO.getProfileHomePhone() != null){
				profile.setLandHome(profilePOJO.getProfileHomePhone());
			}			
			if(profilePOJO.getProfileOfficePhone() != null){
				profile.setLandOffice(profilePOJO.getProfileOfficePhone());
			}			
			if(profilePOJO.getProfileAddress() != null){
				profile.setAddress(profilePOJO.getProfileAddress());
			}			
			if(profilePOJO.getProfileCity() != null){
				profile.setCity(profilePOJO.getProfileCity());
			}			
			if(profilePOJO.getProfileZip() != null){
				profile.setZip(profilePOJO.getProfileZip());	
			}			
			if(profilePOJO.getProfileState() != null){
				profile.setState(profilePOJO.getProfileState());
			}			
			if(profilePOJO.getProfileCountry() != null){
				profile.setCountry(profilePOJO.getProfileCountry());
			}			
			if(profilePOJO.getProfileEducation() != null){
				profile.setEducation(profilePOJO.getProfileEducation());
			}			
			if(profilePOJO.getProfileOccupation() != null){
				profile.setOccupation(profilePOJO.getProfileOccupation());		
			}			
			if(profilePOJO.getProfileWebsite() != null){
				profile.setWebsite(profilePOJO.getProfileWebsite());	
			}			
			if(profilePOJO.getProfileTimeZone() != null){
				profile.setTimeZone(profilePOJO.getProfileTimeZone());		
			}			
			if(profilePOJO.getProfileChkSpeaker()){
				profile.setSpeakerCheckBox(profilePOJO.getProfileChkSpeaker());
			}				
			if(profilePOJO.getProfileEvent() != null){
				profile.setEventOption(profilePOJO.getProfileEvent());
			}			
			if(profilePOJO.getProfileSession() != null){
				profile.setSessionOption(profilePOJO.getProfileSession());
			}			
			if(profilePOJO.getSpeakerKeyNotes() != null){
				profile.setSpeakerKeyNotes(profilePOJO.getSpeakerKeyNotes());
			}			
		}		
		return profile;		
	}

	
	private List getProfileData(ObjectContainer dbContainer, Profile profile) {		
		List profileList = null;
		ProfilePOJO profilePOJO = null;				
		profilePOJO = getProfilePOJOByProfileId(dbContainer, profile.getProfileId());		
		if(profilePOJO!= null){			
			profile = convertProfilePOJOToProfile(profilePOJO, profile);
			profileList = new ArrayList();
			profileList.add(profile);
		}		
		return profileList;
	}

	public Profile getProfileData(ObjectContainer dbContainer, String profileId) {		
		Profile profile = null;
		
		ProfilePOJO profilePOJO = null;				
		profilePOJO = getProfilePOJOByProfileId(dbContainer, profileId);		
		if(profilePOJO!= null){	
			profile = new Profile();
			profile = convertProfilePOJOToProfile(profilePOJO, profile);		
		}		
		return profile;
	}

	
	
	private ProfilePOJO getProfilePOJOByProfileId(ObjectContainer dbContainer, String profileId){		
		ProfilePOJO profilePOJO = null;		
		try{			
			ObjectSet<ProfilePOJO> profilePOJOSet = dbContainer.get(new ProfilePOJO(profileId));			
			if(!profilePOJOSet.isEmpty()){			
				profilePOJO = profilePOJOSet.next();
			}				
		}catch(Exception e){
			e.printStackTrace();
		}			
		return profilePOJO;
	}


	private List profileDataList(ObjectContainer dbContainer, Profile profile) {
		
		List profileList = null;
		ProfilePOJO profilePOJO = null;
		List userIdList = null;
		String profileId  = null;
		
		profileList = new ArrayList();
		
		userIdList = profile.getProfileIdList();
		
		Iterator it = userIdList.iterator();
		
		while(it.hasNext()){
			
			profileId = it.next().toString();
			
			profilePOJO = getProfilePOJOByProfileId(dbContainer, profileId);
			
			if(profilePOJO!= null){
				profile = new Profile();
				profile = convertProfilePOJOToProfile(profilePOJO, profile);
				
				profileList.add(profile);

			}
		}

		return profileList;
	}	
	private List<Profile> attendee(ObjectContainer dbContainer) throws DatabaseException {
		
		List<Profile> profileList = new ArrayList<Profile>();
		
		UserPOJO userPOJO = null;
		
		Profile profile = null;
		ObjectSet<UserPOJO> userPOJOResults = null;
		
		String joinedEventsName = null;
		String attendedSessionsName = null;
		String speakerFor = null;		
		
			userPOJOResults = UserDB4O.getUserDetails(dbContainer);
			
			if(userPOJOResults != null){
				
				while(userPOJOResults.hasNext()){
					
					userPOJO = userPOJOResults.next();
					
					profile = getProfileData(dbContainer,userPOJO.getUserProfileId());
					
					profile = socialMediaProfile(userPOJO.getUserProfileId(),profile,dbContainer);
					
					joinedEventsName = userJoinedEventsName(dbContainer, userPOJO.getUserId());
					
					profile.setJoinedEventsName(joinedEventsName);
					
					attendedSessionsName = userAttendedSessionsName(dbContainer, userPOJO.getUserId());
					
					profile.setAttendedSessionsName(attendedSessionsName);
					
					//if(userPOJO.getUserId().equals("100007")){
						
						//speakerFor = speakerFor(userPOJO.getUserId(),dbContainer);
					speakerFor = speakerFor(dbContainer,userPOJO.getUserId());
						
					//}

					profile.setSpeakerFor(speakerFor);
		
					profileList.add(profile);
				}
			}

		

		return profileList;
	}	
			
	
	public String userJoinedEventsName(ObjectContainer dbContainer, String userId) throws DatabaseException{
	
		UserEventMapPOJO userEventMapPOJO = null;
		EventPOJO eventPOJO = null;
		ObjectSet<UserEventMapPOJO> userEventMapPOJOResults = null;
		ObjectSet<EventPOJO> eventPOJOResults = null;
		String eventId = null;
		
		String joinedEventsName = "";
		
		userEventMapPOJOResults = UserEventMapDB4O.getJoinedEventIdForUserId(dbContainer, userId);
		
		if(userEventMapPOJOResults != null){
		
			while(userEventMapPOJOResults.hasNext()){
				
				userEventMapPOJO = userEventMapPOJOResults.next();
				
				eventId = userEventMapPOJO.getUemEventId();
				
				eventPOJOResults = EventDB4O.getEventDetailForEventId(dbContainer, eventId);
				
				if(eventPOJOResults != null){
					
					eventPOJO = eventPOJOResults.next();
					
					if(joinedEventsName.equals("")){
						joinedEventsName = eventPOJO.getEventName();						
					}else{
						joinedEventsName = joinedEventsName + ","+ eventPOJO.getEventName();
					}
			}
			}
		}
		
		return joinedEventsName;
	}
	

	
	public String userAttendedSessionsName(ObjectContainer dbContainer, String userId) throws DatabaseException{
		
		String[] sessionId = null;
		
		String attendedSessionsName = null;
		
		sessionId = sessionIDsForUserAttend(dbContainer, userId);
		
		attendedSessionsName = eventSessionName(dbContainer, sessionId);
		
		return attendedSessionsName;
	}
	
	public String speakerFor(ObjectContainer dbContainer, String userId) throws DatabaseException{
		
		String[] sessionId = null;
		
		String speakerFor = null;
		
		sessionId = sessionIDsForSpeaker(dbContainer, userId);
		
		speakerFor = eventSessionName(dbContainer, sessionId);
		
		return speakerFor;
	}


	public String[] sessionIDsForUserAttend(ObjectContainer dbContainer, String userId) throws DatabaseException{
		int i = 0;
		String[] sessionId = null;
		UserSessionMapPOJO userSessionMapPOJO = null;
		ObjectSet<UserSessionMapPOJO> userSessionMapPOJOResults = null;
		
		userSessionMapPOJOResults = UserSessionMapDB4O.getAttendedSessionIdForUserId(dbContainer, userId);
		
		if(userSessionMapPOJOResults != null){
			
			sessionId = new String[userSessionMapPOJOResults.size()];
			
			while (userSessionMapPOJOResults.hasNext()					
					&& i < userSessionMapPOJOResults.size()) {
				
				userSessionMapPOJO = userSessionMapPOJOResults.next();
				sessionId[i] = userSessionMapPOJO.getUsmSessionId();
				i++;				
			}	
		}
		
		return sessionId;
	}
	
	
	public String[] sessionIDsForSpeaker(ObjectContainer dbContainer, String userId) throws DatabaseException{
		int i = 0;
		String[] sessionId = null;
		SessionSpeakerMapPOJO sessionSpeakerMapPOJO = null;
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = null;
		
		sessionSpeakerMapPOJOResults = SessionSpeakerMapDB4O.getSessionIdForSpeakerUserId(dbContainer, userId);
		
		if(sessionSpeakerMapPOJOResults != null){
			
			sessionId = new String[sessionSpeakerMapPOJOResults.size()];
			
			while (sessionSpeakerMapPOJOResults.hasNext()
					&& i < sessionSpeakerMapPOJOResults.size()) {
				
				sessionSpeakerMapPOJO = sessionSpeakerMapPOJOResults.next();
				sessionId[i] = sessionSpeakerMapPOJO.getSsmSessionId();
				i++;				
			}	
		}
		
		return sessionId;
	}
	
	public String eventSessionName(ObjectContainer db, String[] sessionId){
		
		ObjectSet<EventPOJO> eventPOJOResults = null;
		ObjectSet<SessionPOJO> sessionPOJOResults = null;
		
		SessionPOJO sessionPOJO = null;
		EventPOJO eventPOJO = null;
		
		String eventSessionName = null;
		String eventName = null;
		
		if (sessionId != null && sessionId.length != 0) {
			
			int j = 0;
			
			while (j < sessionId.length) {
				
				sessionPOJOResults = SessionDB4O.getSessionDetailForSessionId(db, sessionId[j]);
				
				if(sessionPOJOResults != null){
				sessionPOJO = sessionPOJOResults.next();
				
				eventPOJOResults = EventDB4O.getEventDetailForEventId(db, sessionPOJO.getSessionEventId());
				
				if(eventPOJOResults != null){
					
					eventPOJO = eventPOJOResults.next();

				if (eventSessionName == null) {
					eventName = eventPOJO.getEventName();
					eventSessionName = eventName + " - " + sessionPOJO.getSessionName();
				} else {
					
					if (eventName != null
							&& eventName.equals(eventPOJO.getEventName())) {
						eventSessionName = eventSessionName + ", "
								+ sessionPOJO.getSessionName();
					} else {

						eventSessionName = eventSessionName + " | "
								+ eventPOJO.getEventName() + " - "
								+ sessionPOJO.getSessionName();
					}
					eventName = eventPOJO.getEventName();
				}
				j++;
			}
		}
			}
		}
		
		return eventSessionName;
	}
	

	public String speakerFor(String userId, ObjectContainer db) {
	String speakerFor = null;
	
	String[] sessionId = null;
	String eventName = null;
	int i = 0;
	try {
	
		ObjectSet<SessionSpeakerMapPOJO> sessionSpeakerMapPOJOResults = db
				.get(new SessionSpeakerMapPOJO(userId, ""));
		if (!sessionSpeakerMapPOJOResults.isEmpty()) {
			sessionId = new String[sessionSpeakerMapPOJOResults.size()];
			while (sessionSpeakerMapPOJOResults.hasNext()
					&& i < sessionSpeakerMapPOJOResults.size()) {
				SessionSpeakerMapPOJO sessionSpeakerMapPOJO = (SessionSpeakerMapPOJO) sessionSpeakerMapPOJOResults
						.next();
				sessionId[i] = sessionSpeakerMapPOJO.getSsmSessionId();
				i++;
			}
		}
		if (sessionId != null && sessionId.length != 0) {
			int j = 0;
			while (j < sessionId.length) {
				ObjectSet<SessionPOJO> sessionPOJOResults = db
						.get(new SessionPOJO(sessionId[j], ""));
				SessionPOJO sessionPOJO = (SessionPOJO) sessionPOJOResults
						.next();
				ObjectSet<EventPOJO> eventPOJOResults = db
						.get(new EventPOJO(sessionPOJO.getSessionEventId()));
				EventPOJO eventPOJO = (EventPOJO) eventPOJOResults.next();

				if (speakerFor == null) {
					eventName = eventPOJO.getEventName();
					speakerFor = eventName + " - "
							+ sessionPOJO.getSessionName();
				} else {

					if (eventName != null
							&& eventName.equals(eventPOJO.getEventName())) {
						speakerFor = speakerFor + ", "
								+ sessionPOJO.getSessionName();
					} else {

						speakerFor = speakerFor + " | "
								+ eventPOJO.getEventName() + " - "
								+ sessionPOJO.getSessionName();
					}
					eventName = eventPOJO.getEventName();
				}
				j++;
			}
		}

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		// db.close();
	}

	return speakerFor;
}

	
	public Collection getProfileIdByEmail(ObjectContainer dbContainer, Profile profile){
		ProfilePOJO profilePOJO = null;
		List profileList = new ArrayList();
		try {			
			if(profile!=null){
				ObjectSet<ProfilePOJO> result = dbContainer.get(new ProfilePOJO(profile.getProfileEmail(),null));
				while(result.hasNext()){
					profilePOJO = result.next();
					profile.setProfileId(profilePOJO.getProfileId());
					profileList.add(profile);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			} 
		return profileList;
	}

	
	

	public boolean saveProfilePOJO(ObjectContainer dbContainer, Profile profile){
		boolean isProfilePOJOSave = false;
		ProfilePOJO profilePOJO = null;
		String profileId = null;
		
		try {
			db = getDbConnection();		
			if(profile!=null){
				profilePOJO = new ProfilePOJO();
				profileId = getNewId();
				profilePOJO.setProfileId(profileId);
	
				if (profile.getProfileEmail() != null) {
					profilePOJO.setProfileEmail(profile.getProfileEmail());
				}	
				if (profile.getFirstName() != null) {
					profilePOJO.setProfileFirstName(profile.getFirstName());
				}
				if (profile.getLastName() != null) {
					profilePOJO.setProfileLastName(profile.getLastName());
				}
				if(profile.getTimeZone()!=null){					
					profilePOJO.setProfileTimeZone(profile.getTimeZone());
				}	
				if (profile.getGender() != null) {
					profilePOJO.setProfileGender(profile.getGender());
				}
				if (profile.getImgLocation() != null) {
					profilePOJO.setProfileImgLocation(profile.getImgLocation());
				}
				db.store(profilePOJO);
				db.commit();
				isProfilePOJOSave = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			db.close();
		}
		return isProfilePOJOSave;
	}
	
	
	public Profile socialMediaProfile(String profileId, Profile profile,
			ObjectContainer db) {
		if (profileId != null) {
			try {
				// db = getDbConnection();
				ObjectSet<SMProfilePOJO> smProfilePOJOResults = db
						.get(new SMProfilePOJO(profileId));
				if (!smProfilePOJOResults.isEmpty()) {
					while (smProfilePOJOResults.hasNext()) {
						SMProfilePOJO smProfilePOJO = (SMProfilePOJO) smProfilePOJOResults
								.next();
						profile.setTwitterId(smProfilePOJO.getSmpTwitterId());
						profile.setTwitterProfileUrl(smProfilePOJO
								.getSmpTwitterProfileUrl());
						profile.setTwitterImgUrl(smProfilePOJO
								.getSmpTwitterImgUrl());
						if (smProfilePOJO.getTwtShowTweets() != null
								&& (!smProfilePOJO.getTwtShowTweets().equals(
										"null"))) {
							if (smProfilePOJO.getTwtShowTweets().equals("N")) {
								profile.setTwtShowTweets("false");
							} else {
								profile.setTwtShowTweets("true");
							}
						} else {
							profile.setTwtShowTweets("false");
						}
						if (smProfilePOJO.getTwtAllowFriends() != null
								&& (!smProfilePOJO.getTwtAllowFriends().equals(
										"null"))) {
							if (smProfilePOJO.getTwtAllowFriends().equals("N")) {
								profile.setTwitterCheck("false");							
							} else {
								profile.setTwitterCheck("true");
							}
						}

						profile.setFaceBookId(smProfilePOJO.getSmpFacebookId());
						profile.setFaceBookProfileUrl(smProfilePOJO
								.getSmpFaceBookProfileUrl());
						profile.setFaceBookImgUrl(smProfilePOJO
								.getSmpFacebookImgUrl());
						if (smProfilePOJO.getFbAllowFriendsToConnect() != null
								&& (!smProfilePOJO.getFbAllowFriendsToConnect()
										.equals("null"))) {
							if (smProfilePOJO.getFbAllowFriendsToConnect()
									.equals("N")) {
								profile.setFbAllowFriendsToConnect("false");
							} else {
								profile.setFbAllowFriendsToConnect("true");
							}
						}
						if (smProfilePOJO.getFbAllowFriendsToPost() != null
								&& (!smProfilePOJO.getFbAllowFriendsToPost()
										.equals("null"))) {
							if (smProfilePOJO.getFbAllowFriendsToPost().equals(
									"N")) {
								profile.setFbAllowFriendsToPost("false");
							} else {
								profile.setFbAllowFriendsToPost("true");
							}
						}
						profile.setLinkedInId(smProfilePOJO.getSmpLinkedInId());
						profile.setLinkedInProfileUrl(smProfilePOJO
								.getSmpLinkedInProfileUrl());
						profile.setLinkedInImgUrl(smProfilePOJO
								.getSmpLinkedInImgUrl());
						if (smProfilePOJO.getLiAllowFriendsToFollow() != null
								&& (!smProfilePOJO.getLiAllowFriendsToFollow()
										.equals("null"))) {
							if (smProfilePOJO.getLiAllowFriendsToFollow()
									.equals("N")) {
								profile.setLiAllowFriendsToFollow("false");
							} else {
								profile.setLiAllowFriendsToFollow("true");
							}
						}
						if (smProfilePOJO.getLiAllowFriendsToPost() != null
								&& (!smProfilePOJO.getLiAllowFriendsToPost()
										.equals("null"))) {
							if (smProfilePOJO.getLiAllowFriendsToPost().equals(
									"N")) {
								profile.setLiAllowFriendsToPost("false");
							} else {
								profile.setLiAllowFriendsToPost("true");
							}
						}
					}
				} else {
					/*
					 * When new user created & before connecting social media by
					 * the user
					 */
					profile.setTwitterCheck("false");
					profile.setTwtShowTweets("false");
					profile.setFbAllowFriendsToConnect("false");
					profile.setFbAllowFriendsToPost("false");
					profile.setLiAllowFriendsToPost("false");
					profile.setLiAllowFriendsToFollow("false");

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// db.close();
			}
		}
		return profile;
	}
	
}
