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
 * Sep 21, 2010 mmanimaran created the file.
 * 
 */
package com.eventattend.portal.spring;



import java.util.Collection;

import com.eventattend.portal.bl.LoginBL;
import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.dto.DataTransferObject;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;



/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class LoginImplementation implements LoginController {


	
	public String checkLayer(){
		
		LoginBL loginBl = new LoginBL();
		
		return loginBl.checkLayer();
	}
	
    public ResultDTO isUserExist(LoginDTO loginDTO){
		
		LoginBL loginBl = new LoginBL();
		
		return loginBl.isUserExist(loginDTO);
	}    
  
 public ResultDTO loginUserInfo(LoginDTO loginDTO){
		
		LoginBL loginBl = new LoginBL();
		
		return loginBl.loginUserInfo(loginDTO);
	}

}
