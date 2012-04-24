/**
 * Class Description
 *    
 * @version 1.0
 * @author A.Jude
 * @Date 14 Mar 2011
 * 
 * Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */


package com.eventattend.portal.controller;


import java.util.Collection;

import com.eventattend.portal.dto.DataTransferObject;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;

public interface LogoutController {

	public ResultDTO isUserLogout(LoginDTO loginDTO) throws Exception;

}