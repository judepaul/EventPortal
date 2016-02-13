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
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class BaseAppException extends EventPortalException {

	 static final long serialVersionUID = -5829545098534135052L;

	    /**
	     * the message of the BaseAppException.
	     */
	    private String exceptionMessage;

	    /**
	     * A public constructor for BaseAppException containing no arguments.
	     *  
	     */
	 
	    public BaseAppException() {
	    	super();
	    }

	    /**
	     * A public constructor for BaseAppException specifying exception message.
	     * <p>
	     * 
	     * @param msg
	     *            exception message.
	     *  
	     */
	    public BaseAppException(String msg) {
	    	super(msg);
	        this.exceptionMessage = msg;
	    }

	    /**
	     * A public constructor of <code>BaseAppException</code> containing
	     * message and root cause (as <code>Throwable</code>) of the exception.
	     * 
	     * @param msg
	     *            exception message.
	     * @param e
	     *            Throwable object.
	     *  
	     */
	    public BaseAppException(String msg, Throwable e) {
	    	super(msg);
	        this.exceptionMessage = msg;
	        this.initCause(e);
	    }

	    /**
	     * sets the root cause of the exception. Used for setting Java built in
	     * exception in <code>BaseAppException</code>.
	     * 
	     * @param e
	     *            Throwable object.
	     *  
	     */
	    public void setCause(Throwable e) {
	        this.initCause(e);
	    }

	    /*
	     * Gets the class name and exception message.
	     * 
	     * @see java.lang.Object#toString()
	     */
	    public String toString() {
	        String s = getClass().getName();
	        return s + ": " + exceptionMessage;
	    }

	    /*
	     * Gets the message of the exception. equivalent to Exception.getMessage().
	     * 
	     * @see java.lang.Throwable#getMessage()
	     */
	    public String getMessage() {
	        return exceptionMessage;
	    }

		/**
		 * @param arg0
		 */
		public BaseAppException(Throwable arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
	
}
