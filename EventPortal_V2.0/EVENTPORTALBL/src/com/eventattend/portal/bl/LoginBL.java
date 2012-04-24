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
 * Sep 21, 2010 mmanimaran created the file.
 * 
 */

package com.eventattend.portal.bl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.eventattend.portal.bl.BusinessLayer;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.UserDTO;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.UserNotExistException;

/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */

public class LoginBL extends BusinessLayer {
	
    public String checkLayer(){
    	
    	Login login = new Login();
    	//login.checkLayer();
    	
 		return "success";
	}
    
    public ResultDTO isUserExist(LoginDTO loginDTO){
    	ResultDTO resultDTO = new ResultDTO();
    	Login login = new Login();
    	Collection isUserExist = null;
    	String retValue = null;  
    	String userLoginStatus = null;
    	boolean isUserStatus = false;
    	login.seteMail(loginDTO.geteMail());
    	login.setPassword(loginDTO.getPassword());
    	UserDTO userDTO = new UserDTO();
    	ProfileBL profileBL = new ProfileBL();
    	ProfileDTO profileDTO = null;
    	String profileId = null;
    	try {
			isUserExist = login.isUserExist(login);
    	if(isUserExist!=null){
    		userDTO.seteMail(loginDTO.geteMail());
    		profileDTO = profileBL.getProfileIdByEmail(userDTO);
    		profileId = profileDTO.getProfileId();
    		Iterator iter = isUserExist.iterator();
    		while(iter.hasNext()){
    			login = (Login)iter.next(); 
    			if(login!=null){
    				loginDTO.setUserId(login.getUserId());
	    			loginDTO.setUserName(login.getUserName());
	    			//loginDTO.setProfileId(login.getProfileId()); 
	    			loginDTO.setProfileId(profileId); 
	    			loginDTO.setUserType(login.getUserType());
	    			loginDTO.setEmailStatus(login.getEmailStatus());
	    			if(login.getLoginStatus()!=null && !login.getLoginStatus().equals("") && login.getLoginStatus().equals("Valid User")){
	    				if(loginDTO.getEmailStatus()!=null && !loginDTO.getEmailStatus().equals("N")){
			    				loginDTO.setUserStatus("success");	    	    		
			    	    		resultDTO.setResultStatus(true);
		    					isUserStatus = login.updateUserLoginStatus(login);
	    				}else {
	    					resultDTO.setResultMsg("Your account has not activated.");
	    				}
	    				
	    			}else {
	    				resultDTO.setResultMsg("User Name or Password is incorrect");
	    			} 
   			
    			}
    		}
    		resultDTO.setLoginDTO(loginDTO);
    	}
    	}catch (UserNotExistException e) {
    		e.printStackTrace();
    		resultDTO.setResultMsg("User Name or Password is incorrect");
			resultDTO.setResultStatus(false);
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
		
    	return resultDTO;    	
    }
 
 
     public ResultDTO loginUserInfo(LoginDTO loginDTO){
    	ResultDTO resultDTO = new ResultDTO();
    	Login login = new Login();
    	Collection isUserExist = null;
    	String retValue = null;    	
    	login.seteMail(loginDTO.geteMail());
    	login.setPassword(loginDTO.getPassword());
    	try {
			isUserExist = login.loginUserInfo(login);	
			
	    	if(isUserExist!=null){
	    		Iterator iter = isUserExist.iterator();
	    		while(iter.hasNext()){
	    			login = (Login)iter.next(); 
	    			if(login!=null){
		    			loginDTO.setUserName(login.getUserName());
		    			loginDTO.setProfileId(login.getProfileId()); 
		    			loginDTO.seteMail(login.geteMail());	
		    			loginDTO.setUserType(login.getUserType()); 
		    			loginDTO.setUserId(login.getUserId());
		    			loginDTO.setGender(login.getGender());		    			
	    			}
	    		}
	    		resultDTO.setLoginDTO(loginDTO);	    		
	    	}    	
		}catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");			
		}		
    	return resultDTO;    
    }
 
 
}


