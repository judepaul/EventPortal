/*
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Seedcube("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into 
 * with Seedcube.
 * 
 * CHANGE HISTORY
 * ==================================================================================
 * March 1, 2011 veeresh created the file.
 * 
 */
package com.eventattend.portal.controller;

import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.UserDTO;

/**
 * Class Description
 * 
 * @version beta
 * @author Veeresh
 * @Date March 1, 2011
 * 
 *       Copyright 2009-2010 Seedcube LLC. All Rights Reserved. This
 *       software is the proprietary information of Seedcube LLC. Use is
 *       subject to license terms.
 */

public interface UserController {

	public ResultDTO userProfilePopulate(ProfileDTO profileDTO) throws Exception;
	public ResultDTO updateUserAccountSettings(ProfileDTO profileDTO) throws Exception;
	public ResultDTO updateUserProfilePic(ProfileDTO profileDTO) throws Exception;
	public ResultDTO updateUserContactInfo(ProfileDTO profileDTO) throws Exception;
	public ResultDTO userProfileInfo(ProfileDTO profileDTO) throws Exception;
	public ResultDTO adminProfileInfo(ProfileDTO profileDTO) throws Exception;
	public ResultDTO updateProfileSMInfo(ProfileDTO profileDTO) throws Exception;
	public ResultDTO getSocialMediaImageURL(ProfileDTO profileDTO) throws Exception;
	
	public ResultDTO updateProfileSettings(ProfileDTO profileDTO) throws Exception;
	
	public ResultDTO createAccount(UserDTO userDTO) throws Exception;
	public ResultDTO confirmNewUserMailId(UserDTO userDTO) throws Exception;
	public ResultDTO userTimeZone(UserDTO userDTO) throws Exception;


}
