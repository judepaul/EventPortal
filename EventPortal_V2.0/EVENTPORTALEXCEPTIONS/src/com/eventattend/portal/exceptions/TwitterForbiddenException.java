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
 * Oct 7, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.exceptions;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 7, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class TwitterForbiddenException  extends BaseAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TwitterForbiddenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param e
	 */
	public TwitterForbiddenException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public TwitterForbiddenException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	
	
}