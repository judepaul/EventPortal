package com.eventattend.portal.exceptions;

public class UserAlreadyExistException  extends DatabaseException {

	static final long serialVersionUID = -5829545098534135052L;

	/**
	 * 
	 */
	public UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public UserAlreadyExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 */
	public UserAlreadyExistException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public UserAlreadyExistException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
