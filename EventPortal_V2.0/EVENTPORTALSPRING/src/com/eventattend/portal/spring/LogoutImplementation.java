package com.eventattend.portal.spring;

import sun.security.krb5.internal.LoginOptions;

import com.eventattend.portal.bl.LoginBL;
import com.eventattend.portal.bl.LogoutBL;
import com.eventattend.portal.controller.LogoutController;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;

public class LogoutImplementation implements LogoutController{

    public ResultDTO isUserLogout(LoginDTO loginDTO) throws Exception {
		
		LogoutBL logoutBl = new LogoutBL();
		
		return logoutBl.isUserLogout(loginDTO);
	}
}
