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

package com.eventattend.portal.web.bean;

import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpSession;
import com.eventattend.portal.Factory.LoginFactory;
import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ResultDTO;

/**
 * Class Description
 * 
 * @version 1.0
 * @author M.Mani Maran
 * @Date 01 Mar 2011
 * 
 *Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved. This
 *software is the proprietary information of Kyyba Ventures Inc. Use is
 *subject to license terms.
 */

public class LoginBean extends ApplicationBean {

	/* Property to store the Password */
	private String password = null;

	/* Property to store the eMail */
	private String eMail = null;

	List privilegeList = null;
	int rolePrivilge = 0;
	boolean adminTwitterConnected = false;
	boolean adminUserType = false;
	String activationID = null;

	public boolean isAdminTwitterConnected() {
		return adminTwitterConnected;
	}

	public void setAdminTwitterConnected(boolean adminTwitterConnected) {
		this.adminTwitterConnected = adminTwitterConnected;
	}

	public int getRolePrivilge() {
		return rolePrivilge;
	}

	public void setRolePrivilge(int rolePrivilge) {
		this.rolePrivilge = rolePrivilge;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdminUserType() {
		return adminUserType;
	}

	public void setAdminUserType(boolean adminUserType) {
		this.adminUserType = adminUserType;
	}

	public List getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getActivationID() {
		return activationID;
	}

	public void setActivationID(String activationID) {
		this.activationID = activationID;
	}

	/**
	 * @method- checkLogin - To check whether the user is logged-in into the
	 *          application or not
	 * @param
	 * @return String
	 */
	public String checkLogin() {

		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		LoginController logincontroller = null;
		ResultDTO resultDTO = null;
		LoginDTO loginDTO = null;
		String retValue = "failure";
		String errorMessage = null;

		try {
			facesContext = FacesContext.getCurrentInstance();
			if ((eMail != null && !eMail.equals(""))
					|| (password != null && !password.equals(""))) {

				session = (HttpSession) facesContext.getExternalContext()
						.getSession(true);

				loginDTO = new LoginDTO();
				loginDTO.seteMail(eMail);
				loginDTO.setPassword(password);

				logincontroller = LoginFactory.isUserExist();
				resultDTO = logincontroller.isUserExist(loginDTO);

				if (resultDTO.isResultStatus()) {
					if (resultDTO.getLoginDTO().getEmailStatus() != null
							&& !resultDTO.getLoginDTO().getEmailStatus()
									.equals("N")) {

						session.setAttribute("USERTYPE", resultDTO
								.getLoginDTO().getUserType());
						session.setAttribute("USERID", resultDTO.getLoginDTO()
								.getUserId());
						session.setAttribute("USERPROFILEID", resultDTO
								.getLoginDTO().getProfileId());

						initialiseBeforeLogin();

						retValue = "event";

					} else {
						errorMessage = resultDTO.getResultMsg();
						facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
					}
				} else {
					errorMessage = resultDTO.getResultMsg();
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
				}
			}

		} catch (Exception e) {
			errorMessage = "Internal Error occured..! Please Login Again!";
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
			e.printStackTrace();
		}
		if (errorMessage != null) {			
			facesContext.addMessage("LOGINFAILED", facesMessage);
		}
		return retValue;

	}

	public void initialiseBeforeLogin(){
	
		System.out.println("Initialising App Mode...");
		getApplicationBeanInstance().initApplicationMode();
		//currentServerTime With TimeZone
		getApplicationBeanInstance().getCurrentServerTimeTZ();
		System.out.println("Initialising Profile info...");
		getUserBeanInstance().profileInfo();
	
		//System.out.println("Initialising Social Media...");
		getSocialMediaBeanInstance().initSocialMedia();
		
		//System.out.println("Resetting Live Session info...");
		//getSessionBeanInstance().resetLiveSessionInfo();
		//getApplicationBeanInstance().setSocialMediaNotSet(true);

		System.out.println("Fetching Event info...");
		getEventBeanInstance().populateEventsPage();
		
	}
	/**
	 * @method- userRegister - Get the user into create account page
	 * @param
	 * @return void
	 */
	public String userRegister() {
		String forward = "register";
		return forward;
	}
	
	public String loginHome() {
		System.out.println("Login...");
		return "login";
	}

}
