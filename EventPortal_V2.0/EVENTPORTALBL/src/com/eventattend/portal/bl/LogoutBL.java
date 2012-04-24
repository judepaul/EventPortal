package com.eventattend.portal.bl;

import java.util.Collection;
import java.util.Iterator;

import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.Logout;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.UserNotExistException;

public class LogoutBL extends BusinessLayer {
	
    public ResultDTO isUserLogout(LoginDTO loginDTO){
    	ResultDTO resultDTO = new ResultDTO();
    	Logout logout = new Logout();
    	Collection isUserLogout = null;
    	String retValue = null;
    	
    	logout.setUserId(loginDTO.getUserId());
    	try {
    		isUserLogout = logout.isUserLogout(logout);
		
 
    	if(isUserLogout!=null){
    		Iterator iter = isUserLogout.iterator();
    		while(iter.hasNext()){
    			logout = (Logout)iter.next(); 
    			if(logout!=null){
 	    			if(logout.getUserStatus()!=null && !logout.getUserStatus().equals("N")){	    				
		    				resultDTO.setResultMsg("Your are successfully logged out.");
	    				}	    				
	    			} 
    			}
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

}
