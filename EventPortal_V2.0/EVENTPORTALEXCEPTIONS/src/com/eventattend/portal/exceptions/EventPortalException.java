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
 * Oct 6, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.exceptions;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 6, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class EventPortalException extends Exception {

	static final long serialVersionUID = 4157262600607325995L;
	
	 public EventPortalException() {
	   super();
	 }
	
	
	 public EventPortalException(String msg) {
	        super(msg);
	    }


	/**
	 * @param arg0
	 * @param arg1
	 */
	public EventPortalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param arg0
	 */
	public EventPortalException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
