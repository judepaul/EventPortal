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

package com.eventattend.portal.controller;

import java.util.Collection;

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
 * Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */

public interface LoginController {

public String checkLayer() throws Exception;

public ResultDTO isUserExist(LoginDTO loginDTO) throws Exception;

public ResultDTO loginUserInfo(LoginDTO loginDTO) throws Exception;



}
