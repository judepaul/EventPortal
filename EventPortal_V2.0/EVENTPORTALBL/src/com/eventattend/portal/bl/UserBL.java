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
package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ws.scout.registry.infomodel.UserImpl;

import com.eventattend.portal.bl.BusinessLayer;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.User;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.MailDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.SpeakerDTO;
import com.eventattend.portal.dto.UserDTO;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.mail.SendMail;

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


public class UserBL extends BusinessLayer{
	
	
	public boolean isEmailExist(User user){
		boolean isEmailAlreadyExist = false;		
		try {
			isEmailAlreadyExist = user.isEmailExist(user);
		} catch (Exception e){
			e.printStackTrace();
		}
		return isEmailAlreadyExist;
		
	}
	
	
    public ResultDTO createAccount(UserDTO userDTO){
    	ResultDTO resultDTO = new ResultDTO();
    	User user = new User();
    	Collection createUserAccountList = null;
    	String retValue = null;
    	ProfileBL profileBL = new ProfileBL();
    	boolean isUserSave = false;
    	boolean isProfileSave = false;
    	ProfileDTO profileDTO = null;
    	user = convertUserDTOToUserBO(userDTO,user);
    	try {
    		if(!isEmailExist(user)){    				
    				isProfileSave = profileBL.saveProfileData(userDTO);    				
    				profileDTO = profileBL.getProfileIdByEmail(userDTO);  
    				user = convertProfileDTOToUserBO(profileDTO,user);
    				isUserSave = user.createAccount(user);
    				userDTO = getUserData(user);
    				userDTO.setProfileId(profileDTO.getProfileId()); 
    				userDTO.setUserStatus("success");
					resultDTO.setResultMsg("Account created successfully. A confirmation email has been sent to your Mail.");
					resultDTO.setResultStatus(true);
	    		}
    	userDTO.setEmailExist(isEmailExist(user));
    	resultDTO.setUserDTO(userDTO);
 		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
    	return resultDTO;    	
    }


    public ResultDTO confirmNewUserMailId(UserDTO userDTO){
    	ResultDTO resultDTO = new ResultDTO();
    	boolean isUserActivated = false;
    	User user = new User();
    	Collection updateEmailStatusList = null;
    	
    	if(userDTO!=null && !userDTO.getUserEmailId().equals("")){
    		String uid = userDTO.getUserEmailId();
    		user.setUserId(uid);
    	}
    	try {
    		isUserActivated = user.confirmNewUserMailId(user);
    		if(isUserActivated){
    			resultDTO.setUserActivated(isUserActivated);
    			resultDTO.setResultStatus(true);
    			resultDTO.setResultMsg("Your account has been activated.");
    		}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return resultDTO;
    } 

    
    public UserDTO getUserData(User user){
    	Collection userInfoList = new ArrayList();
    	UserDTO userDTO = null;
    	if(user!=null){
    		try {
    			userInfoList = user.getUserData(user);
    			if(!userInfoList.isEmpty()){
    				userDTO = new UserDTO();
    				Iterator iter = userInfoList.iterator();
    				while(iter.hasNext()){
    					user = (User)iter.next();
    					userDTO = convertUserBOToUserDTO(user, userDTO);
    				}
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
        		
    	}    	
    	
    	return userDTO;
    	
    }
    
    
    
	/**
	 * @method userProfilePopulate - To List User Profile List
	 * @param loginDTO
	 * @return ResultDTO
	 * 
	 */
	public ResultDTO userProfilePopulate(ProfileDTO profileDTO){		
		ResultDTO resultDTO = null;	
		//ProfileDTO profileDTO = null;
		
		List accountSettingsList = null;
		List profilePicList = null;
		List contactInfoList = null;
		
		resultDTO = new ResultDTO();
		try{
			
			accountSettingsList = (List)getAccountSettingsList(profileDTO);
			profilePicList = (List)getProfilePic(profileDTO);
			contactInfoList = (List) getContactInfoList(profileDTO);
			
			profileDTO = new ProfileDTO();
			
			profileDTO.setAccountSettingsList(accountSettingsList);
			profileDTO.setProfilePic(profilePicList);
			profileDTO.setContactInfoList(contactInfoList);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;
	}
	
	/**
	 * @method AccountSettingsList - To List User Account setting List
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public List getAccountSettingsList(ProfileDTO profileDTO){
		//ProfileDTO profileDTO = null;
		Profile profile = null;
		Collection accountSettingCollection = null;		
		List accountSettingList = null;		
		
		accountSettingList = new ArrayList();	
		profile = new Profile();	
		accountSettingCollection = new ArrayList();				
		
		if(profileDTO!=null){
			profile.setProfileId(profileDTO.getProfileId());		
			try{
				//accountSettingCollection = profile.accountSettingsList(profile);			
				Iterator iter1 = accountSettingCollection.iterator();
				while(iter1.hasNext()){
					profileDTO = new ProfileDTO();				
					Profile profilelist = (Profile)iter1.next();				
					profileDTO = BoToDTOConversion(profilelist);				
					accountSettingList.add(profileDTO);						
			}					
			}catch (Exception e) {			
				e.printStackTrace();
			}	
		}
		return accountSettingList;	
	}
	
	/**
	 * @method AccountSettingsList - To List User Account setting List
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public List getProfilePic(ProfileDTO profileDTO){
		//ProfileDTO profileDTO = null;
		Profile profile = null;
		Collection userPicCollection = null;		
		List userPicList = null;		
		
		userPicList = new ArrayList();	
		profile = new Profile();	
		userPicCollection = new ArrayList();				
		
		if(profileDTO!=null){
			profile.setProfileId(profileDTO.getProfileId());		
			try{
				//userPicCollection = profile.profilePic(profile);			
				Iterator iter1 = userPicCollection.iterator();
				while(iter1.hasNext()){
					profileDTO = new ProfileDTO();				
					Profile profilelist = (Profile)iter1.next();				
					profileDTO = BoToDTOConversion(profilelist);					
					userPicList.add(profileDTO);						
			}					
			}catch (Exception e) {			
				e.printStackTrace();
			}
		}
		return userPicList;	
	}
	
	/**
	 * @method ContactInfoList - To List User Contact Information List
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public List getContactInfoList(ProfileDTO profileDTO){
		//ProfileDTO profileDTO = null;
		Profile profile = null;
		Collection contactInfoCollection = null;		
		List contactInfoList = null;		
		
		contactInfoList = new ArrayList();	
		profile = new Profile();	
		contactInfoCollection = new ArrayList();				
		
		if(profileDTO!=null){
			profile.setProfileId(profileDTO.getProfileId());		
			try{
				//contactInfoCollection = profile.contactInfoList(profile);			
				Iterator iter1 = contactInfoCollection.iterator();
				while(iter1.hasNext()){
					profileDTO = new ProfileDTO();				
					Profile profilelist = (Profile)iter1.next();				
					profileDTO = BoToDTOConversion(profilelist);
					contactInfoList.add(profileDTO);						
			}					
			}catch (Exception e) {			
				e.printStackTrace();
			}	
		}
		return contactInfoList;	
	}
	
	/**
	 * @method updateUserAccountSettings - To update User Account Settings
	 * @param profileDTO
	 * @return ResultDTO
	 * 
	 */
	public ResultDTO updateProfileSettings(ProfileDTO profileDTO){		
		boolean updateResult = false;
		ResultDTO resultDTO = null;
		ProfileBL profileBL = null;	
		SocialMediaBL socialMediaBL = null;
				
			try{
				
				profileBL = new ProfileBL();
				resultDTO = new ResultDTO();
				socialMediaBL = new SocialMediaBL();
				
				updateResult = profileBL.updateProfileSettings(profileDTO);
				
				//updateResult = socialMediaBL.updateSocialMediaProfileSettings(profileDTO.getSocialMediaDTO());
				
				if(updateResult){
					resultDTO.setResultMsg("Profile settings updated successfully");
					profileDTO.setResultMessage("Profile settings updated successfully");
					
				}else{
					resultDTO.setResultMsg("Profile settings updated successfully");					
					profileDTO.setResultMessage("Failed to update Profile settings!");
				}					
				resultDTO.setResultStatus(updateResult);
				
			}catch (EventPortalException e) {	
				resultDTO.setResultStatus(false);
				e.printStackTrace();
			}	
		
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;	
	}
	
	/**
	 * @method updateUserAccountSettings - To update User Account Settings
	 * @param profileDTO
	 * @return ResultDTO
	 * 
	 */
	public ResultDTO updateUserAccountSettings(ProfileDTO profileDTO){		
		boolean result = false;
		ResultDTO resultDTO = null;
		Profile profile = null;			
		
		profile = new Profile();	
		resultDTO = new ResultDTO();		
		if(profileDTO!=null){			
			try{
				profile = DTOToBOConversion(profileDTO);
				//result = profile.updateUserAccountSettings(profile);			
				if(result){
					profileDTO.setResultMessage("Account Settings updated successfully");
				}else{
					profileDTO.setResultMessage("User Account Settings Failed to update!");
				}			
			}catch (Exception e) {			
				e.printStackTrace();
			}	
		}
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;	
	}
	
	/**
	 * @method updateUserProfilePic - To update User Profile picture
	 * @param profileDTO
	 * @return ResultDTO
	 * 
	 */
	public ResultDTO updateUserProfilePic(ProfileDTO profileDTO){		
		ResultDTO resultDTO = null;
		Profile profile = null;	
		Collection profilePicCollection = null;		
		
		profile = new Profile();	
		resultDTO = new ResultDTO();		
		if(profileDTO!=null){			
			try{
				profile = profilePICDTOToBOConversion(profileDTO);
				profilePicCollection = profile.updateUserProfilePic(profile);		
				Iterator iter1 = profilePicCollection.iterator();
				while(iter1.hasNext()){
					profileDTO = new ProfileDTO();				
					Profile profilelist = (Profile)iter1.next();				
					profileDTO = profilePicBoToDTOConversion(profilelist);
					
			}					

				if(profileDTO.getProfImgFileName()!=null && !profileDTO.getProfImgFileName().equals("") && !profileDTO.getProfImgFileName().equals("/images/null")){
					profileDTO.setResultMessage("User Profile Picture Updated!");
				} else if (profileDTO.getExtProfileImgLocation()!=null && !profileDTO.getExtProfileImgLocation().equals("")){
					profileDTO.setResultMessage("User Profile Picture Updated!");
				}else{
					profileDTO.setResultMessage("User Profile Picture Failed to update!");
				}
				
			}catch (Exception e) {			
				e.printStackTrace();
			}	
		}
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;	
	}
	
	/**
	 * @method updateUserContactInfo - To update User Contact Information
	 * @param profileDTO
	 * @return ResultDTO
	 * 
	 */
	public ResultDTO updateUserContactInfo(ProfileDTO profileDTO){	
		boolean result = false;
		ResultDTO resultDTO = null;
		Profile profile = null;				
		
		profile = new Profile();	
		resultDTO = new ResultDTO();		
		if(profileDTO!=null){			
			try{
				profile = DTOToBOConversion(profileDTO);
				//result = profile.updateUserContactInfo(profile);			
				if(result){
					profileDTO.setResultMessage("User Contact Information Updated!");
				}else{
					profileDTO.setResultMessage("User Contact Information Failed to update!");
				}
			}catch (Exception e) {			
				e.printStackTrace();
			}	
		}
		resultDTO.setProfileDTO(profileDTO);
		return resultDTO;	
	}
	
	
	/**
	 * @method BoToDTOConversion - BO object to DTO object conversion
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public ProfileDTO BoToDTOConversion(Profile profile){
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();		
		if(profile != null){	
			if (profile.getProfileId() != null) {
				profileDTO.setProfileId(profile.getProfileId());
			}
			if (profile.getFirstName() != null) {
				profileDTO.setFirstName(profile.getFirstName());
			}
			if (profile.getLastName() != null) {
				profileDTO.setLastName(profile.getLastName());
			}
			if (profile.getGender() != null) {
				profileDTO.setGender(profile.getGender());
			}
			if (profile.getProfileEmail() != null) {
				profileDTO.setProfileEmail(profile.getProfileEmail());
			}
			if (profile.getProfilePicture() != null) {
				profileDTO.setProfilePicture(profile.getProfilePicture());
			}
			if (profile.getAlternativeEmail() != null) {
				profileDTO.setAlternativeEmail(profile.getAlternativeEmail());
			}
			if (profile.getMobile() != null) {
				profileDTO.setMobile(profile.getMobile());
			}
			if (profile.getLandHome() != null) {
				profileDTO.setLandHome(profile.getLandHome());
			}
			if (profile.getLandOffice() != null) {
				profileDTO.setLandOffice(profile.getLandOffice());
			}
			if (profile.getAddress() != null) {
				profileDTO.setAddress(profile.getAddress());
			}
			if (profile.getCity() != null) {
				profileDTO.setCity(profile.getCity());
			}
			if (profile.getCountry() != null) {
				profileDTO.setCountry(profile.getCountry());
			}
			if (profile.getZip() != null) {
				profileDTO.setZip(profile.getZip());
			}
			if (profile.getState() != null) {
				profileDTO.setState(profile.getState());
			}
			if (profile.getEducation() != null) {
				profileDTO.setEducation(profile.getEducation());
			}
			if (profile.getOccupation() != null) {
				profileDTO.setOccupation(profile.getOccupation());
			}
			if (profile.getWebsite() != null) {
				profileDTO.setWebsite(profile.getWebsite());
			}
			if (profile.isSpeaker()) {
				profileDTO.setSpeakerCheck(profile.isSpeaker());
			}
			if (profile.getTwitterId() != null) {
				profileDTO.setTwitterId(profile.getTwitterId());
			}
			if (profile.getTwitterProfileUrl() != null) {
				profileDTO.setTwitterProfileUrl(profile.getTwitterProfileUrl());
			}
			if (profile.getTwitterImgUrl() != null) {
				profileDTO.setTwitterImgUrl(profile.getTwitterImgUrl());
			}
			profileDTO.setTwitterFollow(profile.getTwitterCheck());
			
			if (profile.getFaceBookId() != null) {
				profileDTO.setFaceBookId(profile.getFaceBookId());
			}
			if (profile.getFaceBookProfileUrl() != null) {
				profileDTO.setFaceBookProfileUrl(profile.getFaceBookProfileUrl());
			}
			if (profile.getFaceBookImgUrl() != null) {
				profileDTO.setFaceBookImgUrl(profile.getFaceBookImgUrl());
			}
			profileDTO.setFaceBookFriendsConnect(profile.getFbAllowFriendsToConnect());
			if (profile.getLinkedInId() != null) {
				profileDTO.setLinkedInId(profile.getLinkedInId());
			}
			if (profile.getLinkedInProfileUrl() != null) {
				profileDTO.setLinkedInProfileUrl(profile.getLinkedInProfileUrl());
			}
			if (profile.getLinkedInImgUrl() != null) {
				profileDTO.setLinkedInImgUrl(profile.getLinkedInImgUrl());
			}
			profileDTO.setLinkedInFriendsConnect(profile.getFbAllowFriendsToConnect());
			if (profile.getProfilePicture() != null
					&& profile.getProfilePicture().trim().startsWith("http")) {
				profileDTO.setProfImgFileName(profile.getProfilePicture());
			} else {
				profileDTO.setProfImgFileName("/images/"
						+ profile.getProfilePicture());
			}
		
	     	profileDTO.seteMail(profile.geteMail());
			profileDTO.setProfileEmail(profile.getProfileEmail());
			profileDTO.setAlternativeEmail(profile.getAlternativeEmail());			
			profileDTO.setMobile(profile.getMobile());
			profileDTO.setLandHome(profile.getLandHome());
			profileDTO.setLandOffice(profile.getLandOffice());
			profileDTO.setAddress(profile.getAddress());
			profileDTO.setCity(profile.getCity());
			profileDTO.setZip(profile.getZip());
			profileDTO.setState(profile.getState());
			profileDTO.setCountry(profile.getCountry());
			profileDTO.setEducation(profile.getEducation());
			profileDTO.setOccupation(profile.getOccupation());
			profileDTO.setWebsite(profile.getWebsite());
			if (profile.getProfilePicture() != null
					&& profile.getProfilePicture().trim().startsWith("http")) {
				profileDTO.setProfImgFileName(profile.getProfilePicture());
			} else {
				if(profile.getImgLocation()==null || profile.getImgLocation().equalsIgnoreCase("/images/null")){
					profileDTO.setProfImgFileName("/images/noPhoto.jpg");
						
				}else{
				profileDTO.setProfImgFileName("/images/"
						+ profile.getProfilePicture());
				}
			}
			profileDTO.setExtProfileImgLocation(profile.getExtProfileImgLocation());
			profileDTO.setTimeZone(profile.getTimeZone());
			profileDTO.setSpeakerCheckBox(profile.getSpeakerCheckBox());
			profileDTO.setEventOption(profile.getEventOption());
			profileDTO.setSessionOption(profile.getSessionOption());
			profileDTO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
		}		
		return profileDTO;
	}	
	
	/**
	 * @method DTOTOBOConversion - DTO object to BO object conversion
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public Profile DTOToBOConversion(ProfileDTO profileDTO){
		Profile profile = null;
		profile = new Profile();		
		if(profile != null){
			profile.setProfileId(profileDTO.getProfileId());	
			profile.setFirstName(profileDTO.getFirstName());	
			profile.setLastName(profileDTO.getLastName());
			profile.setPassword(profileDTO.getPassword());
			profile.seteMail(profileDTO.geteMail());
			profile.setAlternativeEmail(profileDTO.getAlternativeEmail());
			profile.setProfilePicture(profileDTO.getProfilePicture());
			profile.seteMail(profileDTO.geteMail());
			profile.setMobile(profileDTO.getMobile());
			profile.setLandHome(profileDTO.getLandHome());
			profile.setLandOffice(profileDTO.getLandOffice());
			profile.setAddress(profileDTO.getAddress());
			profile.setCity(profileDTO.getCity());
			profile.setZip(profileDTO.getZip());
			profile.setState(profileDTO.getState());
			profile.setCountry(profileDTO.getCountry());
			profile.setEducation(profileDTO.getEducation());
			profile.setOccupation(profileDTO.getOccupation());
			profile.setWebsite(profileDTO.getWebsite());
			profile.setNewPassword(profileDTO.getNewPassword());
			profile.setTimeZone(profileDTO.getTimeZone());
			profile.setSpeakerCheckBox(profileDTO.getSpeakerCheckBox());
			profile.setEventOption(profileDTO.getEventOption());
			profile.setSessionOption(profileDTO.getSessionOption());
			profile.setSpeakerKeyNotes(profileDTO.getSpeakerKeyNotes());
		}		
		return profile;
	}	
	
	
	public ProfileDTO profilePicBoToDTOConversion(Profile profile){
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();		
		if(profile != null){
			profileDTO.setProfileId(profile.getProfileId());
			profileDTO.setProfImgFileName(profile.getProfilePicture());
			if (profile.getProfilePicture() != null
					&& profile.getProfilePicture().trim().startsWith("http")) {
				profileDTO.setProfImgFileName(profile.getProfilePicture());
			} else {
				if(profile.getImgLocation()==null || profile.getImgLocation().equalsIgnoreCase("/images/null")){
					profileDTO.setProfImgFileName("/images/noPhoto.jpg");
						
				}else{
/*				profileDTO.setProfImgFileName("/images/"
						+ profile.getProfilePicture());
*/				profileDTO.setProfImgFileName(profile.getProfilePicture());	
				}
			}
		}		
		return profileDTO;
	}	

	
	public Profile profilePICDTOToBOConversion(ProfileDTO profileDTO){
		Profile profile = null;
		profile = new Profile();		
		if(profile != null){
			profile.setProfileId(profileDTO.getProfileId());	
			profile.setProfilePicture(profileDTO.getProfImgFileName());	
			//profile.setExtProfileImgLocation(profileDTO.getExtProfileImgLocation());
			profile.setChkImageFromLocal(profileDTO.getChkImageFromLocal());
		}
		return profile;
	}
	
	   public ResultDTO userProfileInfo(ProfileDTO profileDTO){
	    	ResultDTO resultDTO = new ResultDTO();
	    	Profile profile = new Profile();
	    	Collection userProfileInfoList = null;
	    	String retValue = null; 
	    	
	    	profile.setProfileId(profileDTO.getProfileId());
	    	
	    	try {
	    		
	    		//userProfileInfoList = profile.userProfileInfo(profile);				
		    	if(userProfileInfoList!=null){
		    		Iterator iter = userProfileInfoList.iterator();
		    		while(iter.hasNext()){
		    			profileDTO = new ProfileDTO();
		    			profile = (Profile)iter.next();		    			
		    			profileDTO = BoToDTOConversion(profile);		    			
		    		}
		    		resultDTO.setProfileDTO(profileDTO);	    		
		    	}    	
			}catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");			
			}		
	    	return resultDTO;    
	    }
	   
	   public ResultDTO adminProfileInfo(ProfileDTO profileDTO){
	    	ResultDTO resultDTO = new ResultDTO();
	    	Profile profile = new Profile();
	    	Collection userProfileInfoList = null;
	    	String retValue = null; 
	    	
	    	try {
	    		
	    		userProfileInfoList = profile.adminProfileInfo(profile);				
				
		    	if(userProfileInfoList!=null){
		    		Iterator iter = userProfileInfoList.iterator();
		    		while(iter.hasNext()){
		    			profileDTO = new ProfileDTO();
		    			profile = (Profile)iter.next();		    			
		    			profileDTO = BoToDTOConversion(profile);		    			
		    		}
		    		resultDTO.setProfileDTO(profileDTO);	    		
		    	}    	
			}catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");			
			}		
	    	return resultDTO;    
	    }
	   
		public ResultDTO updateProfileSMInfo(ProfileDTO profileDTO) {
			boolean result = false;
			ResultDTO resultDTO = null;
			Profile profile = null;				
			
			profile = new Profile();	
			resultDTO = new ResultDTO();		
			if(profileDTO!=null){
			 try {
				 profile = socialMediaProfileDTOToBOConversion(profileDTO);
				 result = profile.updateProfileSMInfo(profile);
					if(result){
						profileDTO.setResultMessage("Social Media Information Updated!");
					}else{
						profileDTO.setResultMessage("Social Media Information Failed to update!");
					}
				 resultDTO.setProfileDTO(profileDTO);
				} catch (EventPortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultDTO.setResultMsg(e.getMessage());
					resultDTO.setResultStatus(false);
				} catch (Exception e){
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}
			  
			}
			return resultDTO;
		}

		
		public ResultDTO getSocialMediaImageURL(ProfileDTO profileDTO){
			ResultDTO resultDTO = null;
			Profile profile = null;				
			Collection smProfileInfoList = null;
			profile = new Profile();	
			resultDTO = new ResultDTO();		
			if(profileDTO!=null){
			 try {
				 profile.setProfileId(profileDTO.getProfileId());
				 smProfileInfoList = profile.getSocialMediaImageURL(profile);
				 
			    	if(smProfileInfoList!=null){
			    		Iterator iter = smProfileInfoList.iterator();
			    		while(iter.hasNext()){
			    			profileDTO = new ProfileDTO();
			    			profile = (Profile)iter.next();		    			
			    			profileDTO.setTwitterImgUrl(profile.getTwitterImgUrl());
			    			profileDTO.setFaceBookImgUrl(profile.getFaceBookImgUrl());
			    			profileDTO.setLinkedInImgUrl(profile.getLinkedInImgUrl());
			    			
			    			profileDTO.setFbAllowFriendsToConnect(profile.getFbAllowFriendsToConnect());
			    			profileDTO.setFbAllowFriendsToPost(profile.getFbAllowFriendsToPost());
			    			profileDTO.setLiAllowFriendsToFollow(profile.getLiAllowFriendsToFollow());
			    			profileDTO.setLiAllowFriendsToPost(profile.getLiAllowFriendsToPost());
			    			profileDTO.setTwtAllowFriends(profile.getTwtAllowFriends());
			    			profileDTO.setTwtShowTweets(profile.getTwtShowTweets());
			    		}
			    		resultDTO.setProfileDTO(profileDTO);	    		
			    	}    	

				} catch (Exception e){
					e.printStackTrace();
					resultDTO.setResultMsg("Internal Error Occured..!");
					resultDTO.setResultStatus(false);
				}
			}
			return resultDTO;
		}
		
		public Profile socialMediaProfileDTOToBOConversion(ProfileDTO profileDTO){
			Profile profile = null;
			profile = new Profile();		
			if(profileDTO != null){
				profile.setProfileId(profileDTO.getProfileId());		
				
				profile.setTwtShowTweets(profileDTO.getSocialMediaDTO().getTwtShowTweets());
				profile.setTwtAllowFriends(profileDTO.getSocialMediaDTO().getTwtAllowFriends());
				
				profile.setFbAllowFriendsToPost(profileDTO.getSocialMediaDTO().getFbAllowFriendsToPost());
				profile.setFbAllowFriendsToConnect(profileDTO.getSocialMediaDTO().getFbAllowFriendsToConnect());
				
				profile.setLiAllowFriendsToFollow(profileDTO.getSocialMediaDTO().getLiAllowFriendsToFollow());
				profile.setLiAllowFriendsToPost(profileDTO.getSocialMediaDTO().getLiAllowFriendsToPost());
			}
			return profile;
		}
		

		public ResultDTO getMailOptionsForAttendees(ProfileDTO profileDTO) {
			Collection profileEmailCountList = null;
			ResultDTO resultDTO = null;
			Profile profile = null;				
			profile = new Profile();	
			resultDTO = new ResultDTO();
			String profileId = null;
			int profileMailCount = 0;
			if(profileDTO!=null){
				 try {					 
					 profile = emailProfileDTOToBOConversion(profileDTO);
					 profileEmailCountList = profile.getMailOptionsForAttendees(profile);
					 
					 if(profileEmailCountList!=null){
				    		Iterator iter = profileEmailCountList.iterator();
				    		while(iter.hasNext()){
				    			profileDTO = new ProfileDTO();
				    			profile = (Profile)iter.next();		    			
				    			profileDTO = emailProfileBOToDTOConversion(profile);		
				    		}
				    		resultDTO.setProfileDTO(profileDTO);	    		
						 }

					} catch (Exception e){
						e.printStackTrace();						
					}
			}
			return resultDTO;
		}
		
		
		public ResultDTO sendMailToAttendees(ProfileDTO profileDTO) {
			boolean result = false;
			Collection profileEmailInfoList = null;
			ResultDTO resultDTO = null;
			Profile profile = null;				
			profile = new Profile();	
			resultDTO = new ResultDTO();
			String toProfileId = null;
			String profileFirstName = null;
			String profileLastName = null;
			String profileMailFromId = null;
			String profileMailToId = null;
			String profileMailContent = null;			
			SendMail mail = new SendMail();
			String chkMailStatus = null;
			
			String toProfileFirstName = null;
			String toProfileLastName = null;
			
			if(profileDTO!=null){
			 try {				 
				 if(profileDTO.getToProfileId()!=null && !profileDTO.getToProfileId().equals("")){
					 toProfileId = profileDTO.getToProfileId();
					 EventBL eventBL = new EventBL();
					 ResultDTO profileResultDTO = new ResultDTO();
					 profileResultDTO = eventBL.getProfileDetailsById(toProfileId);	
					 toProfileFirstName = profileResultDTO.getProfileDTO().getFirstName();
					 toProfileLastName = profileResultDTO.getProfileDTO().getLastName();
					 profileMailToId = profileResultDTO.getProfileDTO().getProfileEmail();
				 }
				 
				 if(profileDTO.getFirstName()!=null && !profileDTO.getFirstName().equals("")){
					 profileFirstName = profileDTO.getFirstName();					 
				 }
				 if(profileDTO.getLastName()!=null && !profileDTO.getLastName().equals("")){
					 profileLastName = profileDTO.getLastName();					 
				 }				 
				 if(profileDTO.getProfileMailFromId()!=null && !profileDTO.getProfileMailFromId().equals("")){
					 profileMailFromId = profileDTO.getProfileMailFromId();					 
				 }
/*				 if(profileDTO.getProfileMailToId()!=null && !profileDTO.getProfileMailToId().equals("")){
					 profileMailToId = profileDTO.getProfileMailToId();					 
				 }
*/				 if(profileDTO.getProfileMailContent()!=null && !profileDTO.getProfileMailContent().equals("")){
					 profileMailContent = profileDTO.getProfileMailContent();					
				 }
				 chkMailStatus = mail.validateMail(profileFirstName, profileLastName, toProfileFirstName, toProfileLastName, profileMailFromId, profileMailToId, profileMailContent);
				 System.out.println(chkMailStatus);
				 profile = emailProfileDTOToBOConversion(profileDTO);
				 profileEmailInfoList = profile.getAttendeesEmailInfo(profile);
				 
				 if(profileEmailInfoList!=null){
		    		Iterator iter = profileEmailInfoList.iterator();
		    		while(iter.hasNext()){
		    			profileDTO = new ProfileDTO();
		    			profile = (Profile)iter.next();		    			
		    			profileDTO = emailProfileBOToDTOConversion(profile);		
		    		}
		    		resultDTO.setProfileDTO(profileDTO);	    		
				 }
			} catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");
				resultDTO.setResultStatus(false);
			}
		}
		return resultDTO;
	}

		public Profile emailProfileDTOToBOConversion(ProfileDTO profileDTO){
			Profile profile = null;
			profile = new Profile();
			
			if(profileDTO!=null){	
				profile.setProfileId(profileDTO.getProfileId());
				profile.setFirstName(profileDTO.getFirstName());
				profile.setLastName(profileDTO.getLastName());
				profile.setProfileMailFromId(profileDTO.getProfileMailFromId());
				profile.setProfileMailToId(profileDTO.getProfileMailToId());
				profile.setProfileMailContent(profileDTO.getProfileMailContent());
			}
			return profile;			
		} 		
	   
		public ProfileDTO emailProfileBOToDTOConversion(Profile profile){
			ProfileDTO profileDTO = null;
			profileDTO = new ProfileDTO();			
			if(profile!=null){	
				profileDTO.setProfileId(profile.getProfileId());
				profileDTO.setFirstName(profile.getFirstName());
				profileDTO.setLastName(profile.getLastName());
				profileDTO.setProfileMailFromId(profile.getProfileMailFromId());
				profileDTO.setProfileMailToId(profile.getProfileMailToId());
				profileDTO.setProfileMailContent(profile.getProfileMailContent());
				profileDTO.setProfileMailCount(profile.getProfileMailCount());
				
			}
			return profileDTO;			
		} 
		
		
		public ResultDTO getToMailProfileInfo(ProfileDTO profileDTO) {
			boolean result = false;
			Collection profileEmailInfoList = null;			
			ResultDTO resultDTO = null;
			Profile profile = null;				
			profile = new Profile();	
			resultDTO = new ResultDTO();
			String toProfileId = null;
			String profileFirstName = null;
			String profileLastName = null;
			String profileMailFromId = null;
			String profileMailToId = null;
			String profileMailContent = null;			
			MailDTO mailDTO = new MailDTO();
			String toProfileFirstName = null;
			String toProfileLastName = null;
			resultDTO = new ResultDTO();
			
			if(profileDTO!=null){
			 try {				 
				 if(profileDTO.getToProfileId()!=null && !profileDTO.getToProfileId().equals("")){
					 toProfileId = profileDTO.getToProfileId();
					 EventBL eventBL = new EventBL();
					 ResultDTO profileResultDTO = new ResultDTO();
					 profileResultDTO = eventBL.getProfileDetailsById(toProfileId);	
					 if(profileResultDTO != null){
						 toProfileFirstName = profileResultDTO.getProfileDTO().getFirstName();
						 toProfileLastName = profileResultDTO.getProfileDTO().getLastName();
						 profileMailToId = profileResultDTO.getProfileDTO().getProfileEmail();
						 mailDTO.setToMailFirstName(toProfileFirstName);
						 mailDTO.setToMailLastName(toProfileLastName);
						 mailDTO.setToMailId(profileMailToId);
						 resultDTO.setMailDTO(mailDTO);
					 }
				 }
				 

			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return resultDTO;
	}
		
//Refined Methods	
		
		public ResultDTO populateProfileSettings(ProfileDTO profileDTO){		
			
			ResultDTO resultDTO = null;
			SocialMediaDTO socialMediaDTO = null;				
			SpeakerDTO speakerDTO = null;
			
			ProfileBL profileBL = null;
			SocialMediaBL socialMediaBL = null;
			SessionBL sessionBL = null;		

			String profileId = null;
			String userId = null;
			
			try{
				
				profileBL = new ProfileBL();
				socialMediaBL = new SocialMediaBL();
				sessionBL = new SessionBL();
				
				socialMediaDTO = new SocialMediaDTO();
				resultDTO = new ResultDTO();
				
				profileId = profileDTO.getProfileId();
				userId = profileDTO.getUserId();
				
				profileDTO = profileBL.getProfileData(profileId);
				
				socialMediaDTO = socialMediaBL.getProfileSocialMediaData(profileId);
				
				profileDTO.setSocialMediaDTO(socialMediaDTO);
				
				speakerDTO = sessionBL.sessionListForSpeaker(userId);
				
				profileDTO.setSpeakerDTO(speakerDTO);				
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			resultDTO.setProfileDTO(profileDTO);
			return resultDTO;
		}
		
		
		
		
		public ResultDTO populateProfileInfo(ProfileDTO profileDTO){
			
			ResultDTO resultDTO = null;	
			ProfileBL profileBL = null;
			SocialMediaBL socialMediaBL = null;
			SocialMediaDTO socialMediaDTO = null;	
			String profileId = null;			
			
			try{
				
				profileBL = new ProfileBL();
				socialMediaBL = new SocialMediaBL();
				socialMediaDTO = new SocialMediaDTO();
				resultDTO = new ResultDTO();
				
				profileId = profileDTO.getProfileId();
				
				profileDTO = profileBL.getProfileData(profileId);
				
				socialMediaDTO = socialMediaBL.getProfileSocialMediaData(profileId);
				
				profileDTO.setSocialMediaDTO(socialMediaDTO);
		
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			resultDTO.setProfileDTO(profileDTO);
			
			return resultDTO;
	    }
		
		public ResultDTO populateAdminProfileInfo(ProfileDTO profileDTO){
	    	ResultDTO resultDTO = new ResultDTO();
	    	Profile profile = new Profile();
	    	List adminUserList = null; 
    		User user = null;
	    	
	    	ProfileBL profileBL = null;
			
			String adminProfileId = null;
	    	try {
	    		profileBL = new ProfileBL();	
	    		user = new User();
	    		
	    		adminUserList = (ArrayList)user.getAdminProfileId(user);
	    		
	    		if(adminUserList != null){
	    			
	    			user = (User)adminUserList.get(0);
	    			
	    		}
	    		if(user != null){
	    			adminProfileId = user.getProfileId();
	    			profileDTO = profileBL.getProfileData(adminProfileId);
	    		}
		    		resultDTO.setProfileDTO(profileDTO);	    		
		    	   	
			}catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");			
			}		
	    	return resultDTO;    
	    }


		public User convertUserDTOToUserBO(UserDTO userDTO, User user){
			if(userDTO!=null){
		    	user.seteMail(userDTO.geteMail());
		    	user.setUserPassword(userDTO.getUserPassword());
		    	user.setFirstName(userDTO.getFirstName());
		    	user.setLastName(userDTO.getLastName());
		    	user.setGender(userDTO.getGender());
			}			
			return user;
		}
		
		public UserDTO convertUserBOToUserDTO(User user, UserDTO userDTO){
			if(user!=null){
				userDTO.setUserName(user.getUserName());				
				userDTO.setUserType(user.getUserType());
				userDTO.seteMail(user.geteMail());
				userDTO.setUserId(user.getUserId());
			}			
			return userDTO;
		}
		

	public List profileIdListFromUserIdList(List userIdList) throws EventPortalException{
		
		List profileIdList = null;
		
		User user = null;
		
		user = new User();
		
		user.setUserIdList(userIdList);		
		
		profileIdList = (ArrayList)user.profileIdListFromUserIdList(user);		
		
		return profileIdList;
	}
	
	public User convertProfileDTOToUserBO(ProfileDTO profileDTO, User user){
		if(profileDTO!=null){
			if(profileDTO.getProfileId()!=null){
				user.setProfileId(profileDTO.getProfileId());
			}
			if(profileDTO.getNewPassword()!=null){
				user.setUserPassword(profileDTO.getNewPassword());
			}
	    	
		}			
		return user;
	}
	
	
	public boolean UpdateUserProfileSettings(ProfileDTO profileDTO) throws EventPortalException {
		boolean updateResult = false;
		User user = new User();
		if(profileDTO!=null){
			user = convertProfileDTOToUserBO(profileDTO, user);
			updateResult = user.UpdateUserProfileSettings(user);
		}
		
		return updateResult;
	}

	public ResultDTO userTimeZone(UserDTO userDTO)  {
		ResultDTO resultDTO = new ResultDTO();
		User user = new User();
		boolean updateResult= false;
		Profile profile = new Profile();
		String profileId = null;
		String timeZone = null;
		ProfileBL profileBL = new ProfileBL();
		if(userDTO!=null){
			try {
			if(userDTO.getProfileDTO()!=null){		
			
				updateResult = profileBL.updateProfileSettings(userDTO.getProfileDTO());
			}
			} catch (EventPortalException e) {		
				e.printStackTrace();
			}
			resultDTO.setResultStatus(updateResult);
		}
		
		return resultDTO;
	}

}
