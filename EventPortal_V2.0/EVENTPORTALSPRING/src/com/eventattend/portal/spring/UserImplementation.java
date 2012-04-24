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
package com.eventattend.portal.spring;

import com.eventattend.portal.bl.LoginBL;
import com.eventattend.portal.bl.UserBL;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.UserDTO;
import com.eventattend.portal.exceptions.EventPortalException;

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


public class UserImplementation implements UserController {

	 public ResultDTO userProfilePopulate(ProfileDTO profileDTO){			
		UserBL userBL = new UserBL();			
		
		return userBL.populateProfileSettings(profileDTO);
	 }
	 
	 public ResultDTO updateProfileSettings(ProfileDTO profileDTO){			
			UserBL userBL = new UserBL();			
			return userBL.updateProfileSettings(profileDTO);
		 }
	 
	 public ResultDTO updateUserAccountSettings(ProfileDTO profileDTO){			
			UserBL userBL = new UserBL();			
			return userBL.updateUserAccountSettings(profileDTO);
		 }
	 
	 public ResultDTO updateUserProfilePic(ProfileDTO profileDTO){			
			UserBL userBL = new UserBL();			
			return userBL.updateUserProfilePic(profileDTO);
		 }
	 
	 public ResultDTO updateUserContactInfo(ProfileDTO profileDTO){			
			UserBL userBL = new UserBL();			
			return userBL.updateUserContactInfo(profileDTO);
		 }
	 
	 public ResultDTO userProfileInfo(ProfileDTO profileDTO){			
			UserBL userBL = new UserBL();			
		//	return userBL.userProfileInfo(profileDTO);
			return userBL.populateProfileInfo(profileDTO);
		 }
	 
	 public ResultDTO adminProfileInfo(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			//return userBL.adminProfileInfo(profileDTO);
			return userBL.populateAdminProfileInfo(profileDTO);
		 }
	 
	 public ResultDTO updateProfileSMInfo(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			return userBL.updateProfileSMInfo(profileDTO);
		 }
	 
	 public ResultDTO getSocialMediaImageURL(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			return userBL.getSocialMediaImageURL(profileDTO);
		 }
	 
    public ResultDTO createAccount(UserDTO userDTO){    	
    	UserBL userBl = new UserBL();    	
    	return userBl.createAccount(userDTO);
    }

    public ResultDTO confirmNewUserMailId(UserDTO userDTO){    	
    	UserBL userBl = new UserBL();      	
    	return userBl.confirmNewUserMailId(userDTO);
    }
    public ResultDTO userTimeZone(UserDTO userDTO){    	
    	UserBL userBl = new UserBL();  
    	
		return userBl.userTimeZone(userDTO);
    }

	 
}
