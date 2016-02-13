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
 * Oct 1, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.web.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.eventattend.portal.Factory.LoginFactory;
import com.eventattend.portal.Factory.LogoutFactory;
import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.controller.LogoutController;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 1, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class LogoutBean extends ApplicationBean {

	
	public String logOut() {
				
		LoginDTO loginDTO = null;
		LogoutController logoutController = null;		
		ResultDTO resultDTO = null;
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);	
			
			String userId = (String)session.getAttribute("USERID");				
				
			if(userId!=null && !userId.equals("")){
			loginDTO = new LoginDTO();
			loginDTO.setUserId(userId);
			
			logoutController = LogoutFactory.isUserLogout();
			resultDTO = logoutController.isUserLogout(loginDTO);
			
			session.invalidate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	return "logout";
	}
	
	public String testUI() {
		System.out.println("In Test Link");
		return "profile";
	}
	
}
