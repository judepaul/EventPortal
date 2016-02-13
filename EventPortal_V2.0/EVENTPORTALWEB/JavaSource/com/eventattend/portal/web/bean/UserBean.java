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
 * March 1, 2011 veeresh created the file.
 * 
 */
package com.eventattend.portal.web.bean;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.eventattend.portal.Factory.LoginFactory;
import com.eventattend.portal.Factory.UserFactory;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.ApplicationDTO;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.SpeakerDTO;
import com.eventattend.portal.dto.UserDTO;
import com.eventattend.portal.mail.MailUtility;

/**
 * Class Description
 * 
 * @version beta
 * @author Veeresh
 * @Date March 1, 2011
 * 
 *Copyright 2009-2010 Seedcube LLC. All Rights Reserved. This
 *software is the proprietary information of Seedcube LLC. Use is
 *subject to license terms.
 */

public class UserBean extends ApplicationBean {
	
	/* Property to store the First name */
	private String firstName;

	/* Property to store the Last name */
	private String lastName;

	/* Property to store the Password */
	private String password;

	/* Property to store the Email */
	private String eMail;

	/* Property to store the Email */
	private String alternativeEmail;

	/* Property to store the Mobile number */
	private String mobile;

	/* Property to store the LandHome number */
	private String landHome;

	/* Property to store the LandOffice number */
	private String landOffice;

	/* Property to store the Address */
	private String address;

	/* Property to store the City */
	private String city;

	/* Property to store the Zip code */
	private String zip;

	/* Property to store the State */
	private String state;

	/* Property to store the Country */
	private String country;

	/* Property to store the Education */
	private String education;

	/* Property to store the Occupation */
	private String occupation;

	/* Property to store the Web site */
	private String website;

	/* Property to store the gender */
	private String gender;

	/* Property to store the old Password */
	private String newPassword;

	/* Property to confirm Password */
	private String confirmPassword;

	/* Property List to set Account Settings */
	private List accountSettings = null;

	/* Property List to set Profile Picture */
	private List profilePic = null;

	/* List Property to set Contact Information */
	private List contactInfo = null;

	/* DTO Property to set ProfileDTO */
	private ProfileDTO profileDTO = null;

	/* Property to set selectedTab */
	private String selectedTab;

	/* Property to set profile Image FileName */
	private String profImgFileName;

	private int uploadsAvailable = 1;

	private boolean autoUpload = false;

	private boolean useFlash = false;

	private String extProfileImgLocation;

	boolean extImage = false;

	private String baseURL = null;

	private String userActivationId = null;

	/* Property to set twtShowTweets */
	private boolean twtShowTweets = false;

	/* Property to set twtAllowFriends */
	private boolean twtAllowFriends = false;

	/* Property to set fbAllowFriendsToPost */
	private boolean fbAllowFriendsToPost = false;

	/* Property to set fbAllowFriendsToConnect */
	private boolean fbAllowFriendsToConnect = false;

	/* Property to set liAllowFriendsToFollow */
	private boolean liAllowFriendsToFollow = false;

	/* Property to set liAllowFriendsToPost */
	private boolean liAllowFriendsToPost = false;

	private String twitterImgUrl = null;

	private String faceBookImgUrl = null;

	private String linkedInImgUrl = null;

	private boolean showEventList = false;

	private boolean showSessionList = false;

	/* Property to set speakerCheckBox */
	private boolean speakerCheckBox = false;

	/* Property to set uploadImgCheckBox in profile picture */
	private boolean uploadImgCheckBox;

	/* Property to set externalImgCheckBox in profile picture */
	private boolean externalImgCheckBox;

	/* Property to set smImgCheckBox in profile picture */
	private boolean smImgCheckBox;

	/* Property to set in twitterCheckBox under smImgCheckBox */
	private boolean twitterCheckBox;

	/* Property to set in fbCheckBox under smImgCheckBox */
	private boolean fbCheckBox;

	/* Property to set in lnCheckBox under smImgCheckBox */
	private boolean lnCheckBox;

	private SelectItem[] genderCategory = new SelectItem[] {
			new SelectItem("Select"), new SelectItem("Male"),
			new SelectItem("Female"), };

	private String activationID = null;

	private boolean chkBoxChecked;

	private boolean success;

	private boolean failure;

	private boolean beforeUserActivation = true;

	private boolean afterUserActivation = false;

	private String inputImageVal = null;

	/* Property to set time zone */
	private String timeZoneOption = null;

	private SelectItem[] timeZoneCategory = new SelectItem[] {
			new SelectItem("Select"),new SelectItem("ADT  (Atlantic Daylight Time)"),
			new SelectItem("AKDT (Alaska Daylight Time)"),new SelectItem("AKST (Alaska Standard Time)"),	
			new SelectItem("AST  (Atlantic Standard Time)"),new SelectItem("CDT  (Central Daylight Time)"),	
			new SelectItem("CST  (Central Standard Time)"),new SelectItem("EDT  (Eastern Daylight Time)"),
			new SelectItem("EGST (Eastern Greenland Summer)"),new SelectItem("EGT  (East Greenland Time)"),
			new SelectItem("EST  (Eastern Standard Time)"),new SelectItem("HADT (Hawaii-Aleutian Daylight Time)"),		
			new SelectItem("HAST (Hawaii-Aleutian Standard Time)"),new SelectItem("MDT  (Mountain Daylight Time)"),
			new SelectItem("MST  (Mountain Standard Time)"),new SelectItem("NDT  (Newfoundland Daylight Time)"),
			new SelectItem("NST  (Newfoundland Standard Time)"),new SelectItem("PDT  (Pacific Daylight Time)"),
			new SelectItem("PMDT (Pierre & Miquelon Daylight Time)"),new SelectItem("PMST (Pierre & Miquelon Standard Time)"),
			new SelectItem("PST  (Pacific Standard Time)"),new SelectItem("WGST (Western Greenland Summer Time)"),
			new SelectItem("WGT  (West Greenland Time)"),new SelectItem("IST  (India Standard Time)")
			};

	/* Property to get selected event */
	private String eventOption = null;

	/* Property to hold events */
	List<SelectItem> eventSelectItems = new ArrayList();

	/* Property to get selected session */
	private String sessionOption = null;

	/* Property to hold sessions under an event */
	List<SelectItem> sessionSelectItems = new ArrayList();

	/* Property to add speakerKeyNotes */
	private String speakerKeyNotes = null;

	private boolean speakersAssigned = false;

	private Map eventMap = null;

	private Map sessionMap = null;

	private String eventOptionId = null;

	public Map getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getEventOptionId() {
		return eventOptionId;
	}

	public void setEventOptionId(String eventOptionId) {
		this.eventOptionId = eventOptionId;
	}

	public Map getEventMap() {
		return eventMap;
	}

	public void setEventMap(Map eventMap) {
		this.eventMap = eventMap;
	}

	public boolean isBeforeUserActivation() {
		return beforeUserActivation;
	}

	public void setBeforeUserActivation(boolean beforeUserActivation) {
		this.beforeUserActivation = beforeUserActivation;
	}

	public boolean isAfterUserActivation() {
		return afterUserActivation;
	}

	public void setAfterUserActivation(boolean afterUserActivation) {
		this.afterUserActivation = afterUserActivation;
	}

	public boolean isShowEventList() {
		return showEventList;
	}

	public void setShowEventList(boolean showEventList) {
		this.showEventList = showEventList;
	}

	public String getInputImageVal() {
		return inputImageVal;
	}

	public void setInputImageVal(String inputImageVal) {
		this.inputImageVal = inputImageVal;
	}

	public boolean isChkBoxChecked() {
		return chkBoxChecked;
	}

	public boolean isBtnDisabled() {
		return !this.chkBoxChecked;
	}

	public void setChkBoxChecked(boolean chkBoxChecked) {
		this.chkBoxChecked = chkBoxChecked;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandHome() {
		return landHome;
	}

	public void setLandHome(String landHome) {
		this.landHome = landHome;
	}

	public String getLandOffice() {
		return landOffice;
	}

	public void setLandOffice(String landOffice) {
		this.landOffice = landOffice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List getAccountSettings() {
		return accountSettings;
	}

	public void setAccountSettings(List accountSettings) {
		this.accountSettings = accountSettings;
	}

	public List getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(List profilePic) {
		this.profilePic = profilePic;
	}

	public List getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(List contactInfo) {
		this.contactInfo = contactInfo;
	}

	public ProfileDTO getProfileDTO() {
		return profileDTO;
	}

	public void setProfileDTO(ProfileDTO profileDTO) {
		this.profileDTO = profileDTO;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isSpeakersAssigned() {
		return speakersAssigned;
	}

	public void setSpeakersAssigned(boolean speakersAssigned) {
		this.speakersAssigned = speakersAssigned;
	}

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public String getProfImgFileName() {
		return profImgFileName;
	}

	public void setProfImgFileName(String profImgFileName) {
		this.profImgFileName = profImgFileName;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public String getActivationID() {
		return activationID;
	}

	public void setActivationID(String activationID) {
		this.activationID = activationID;
	}

	public SelectItem[] getGenderCategory() {
		return genderCategory;
	}

	public void setGenderCategory(SelectItem[] genderCategory) {
		this.genderCategory = genderCategory;
	}

	public String getExtProfileImgLocation() {
		return extProfileImgLocation;
	}

	public void setExtProfileImgLocation(String extProfileImgLocation) {
		this.extProfileImgLocation = extProfileImgLocation;
	}

	public boolean getExtImage() {
		return extImage;
	}

	public void setExtImage(boolean isExtImage) {
		this.extImage = extImage;
	}

	public boolean getTwtShowTweets() {
		return twtShowTweets;
	}

	public void setTwtShowTweets(boolean twtShowTweets) {
		this.twtShowTweets = twtShowTweets;
	}

	public boolean getTwtAllowFriends() {
		return twtAllowFriends;
	}

	public void setTwtAllowFriends(boolean twtAllowFriends) {
		this.twtAllowFriends = twtAllowFriends;
	}

	public boolean getFbAllowFriendsToPost() {
		return fbAllowFriendsToPost;
	}

	public void setFbAllowFriendsToPost(boolean fbAllowFriendsToPost) {
		this.fbAllowFriendsToPost = fbAllowFriendsToPost;
	}

	public boolean getFbAllowFriendsToConnect() {
		return fbAllowFriendsToConnect;
	}

	public void setFbAllowFriendsToConnect(boolean fbAllowFriendsToConnect) {
		this.fbAllowFriendsToConnect = fbAllowFriendsToConnect;
	}

	public boolean getLiAllowFriendsToFollow() {
		return liAllowFriendsToFollow;
	}

	public void setLiAllowFriendsToFollow(boolean liAllowFriendsToFollow) {
		this.liAllowFriendsToFollow = liAllowFriendsToFollow;
	}

	public String getTwitterImgUrl() {
		return twitterImgUrl;
	}

	public void setTwitterImgUrl(String twitterImgUrl) {
		this.twitterImgUrl = twitterImgUrl;
	}

	public String getFaceBookImgUrl() {
		return faceBookImgUrl;
	}

	public void setFaceBookImgUrl(String faceBookImgUrl) {
		this.faceBookImgUrl = faceBookImgUrl;
	}

	public String getLinkedInImgUrl() {
		return linkedInImgUrl;
	}

	public void setLinkedInImgUrl(String linkedInImgUrl) {
		this.linkedInImgUrl = linkedInImgUrl;
	}

	public boolean getUploadImgCheckBox() {
		return uploadImgCheckBox;
	}

	public void setUploadImgCheckBox(boolean uploadImgCheckBox) {
		this.uploadImgCheckBox = uploadImgCheckBox;
	}

	public boolean getExternalImgCheckBox() {
		return externalImgCheckBox;
	}

	public void setExternalImgCheckBox(boolean externalImgCheckBox) {
		this.externalImgCheckBox = externalImgCheckBox;
	}

	public boolean getSmImgCheckBox() {
		return smImgCheckBox;
	}

	public void setSmImgCheckBox(boolean smImgCheckBox) {
		this.smImgCheckBox = smImgCheckBox;
	}

	public boolean getTwitterCheckBox() {
		return twitterCheckBox;
	}

	public void setTwitterCheckBox(boolean twitterCheckBox) {
		this.twitterCheckBox = twitterCheckBox;
	}

	public boolean getFbCheckBox() {
		return fbCheckBox;
	}

	public void setFbCheckBox(boolean fbCheckBox) {
		this.fbCheckBox = fbCheckBox;
	}

	public boolean getLnCheckBox() {
		return lnCheckBox;
	}

	public void setLnCheckBox(boolean lnCheckBox) {
		this.lnCheckBox = lnCheckBox;
	}

	public boolean getLiAllowFriendsToPost() {
		return liAllowFriendsToPost;
	}

	public void setLiAllowFriendsToPost(boolean liAllowFriendsToPost) {
		this.liAllowFriendsToPost = liAllowFriendsToPost;
	}

	public String getEventOption() {
		return eventOption;
	}

	public void setEventOption(String eventOption) {
		this.eventOption = eventOption;
	}

	public boolean isShowSessionList() {
		return showSessionList;
	}

	public void setShowSessionList(boolean showSessionList) {
		this.showSessionList = showSessionList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isFailure() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}

	public String getTimeZoneOption() {
		return timeZoneOption;
	}

	public void setTimeZoneOption(String timeZoneOption) {
		this.timeZoneOption = timeZoneOption;
	}

	public SelectItem[] getTimeZoneCategory() {
		return timeZoneCategory;
	}

	public void setTimeZoneCategory(SelectItem[] timeZoneCategory) {
		this.timeZoneCategory = timeZoneCategory;
	}

	public List<SelectItem> getEventSelectItems() {
		return eventSelectItems;
	}

	public void setEventSelectItems(List<SelectItem> eventSelectItems) {
		this.eventSelectItems = eventSelectItems;
	}

	public List<SelectItem> getSessionSelectItems() {
		return sessionSelectItems;
	}

	public void setSessionSelectItems(List<SelectItem> sessionSelectItems) {
		this.sessionSelectItems = sessionSelectItems;
	}

	public boolean getSpeakerCheckBox() {
		return speakerCheckBox;
	}

	public void setSpeakerCheckBox(boolean speakerCheckBox) {
		this.speakerCheckBox = speakerCheckBox;
	}

	public String getSessionOption() {
		return sessionOption;
	}

	public void setSessionOption(String sessionOption) {
		this.sessionOption = sessionOption;
	}

	public String getSpeakerKeyNotes() {
		return speakerKeyNotes;
	}

	public void setSpeakerKeyNotes(String speakerKeyNotes) {
		this.speakerKeyNotes = speakerKeyNotes;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getUserActivationId() {
		return userActivationId;
	}

	public void setUserActivationId(String userActivationId) {
		this.userActivationId = userActivationId;
	}

	public String profileRefresh(){
    	this.selectedTab = "profilePicture";
		return "profile";
	}
	   
	
	/**
	 * @method- createAccount - To create an account for user if they are not already register
	 *          
	 * @param
	 * @return String
	 */
	   public String createAccount() {		
			FacesContext facesContext = null;
			FacesMessage facesMessage = null;
			HttpSession session = null;
			HttpServletRequest request = null;
			UserController userController = null;
			ResultDTO resultDTO = null;
			UserDTO userDTO = null;
			String userEmail = null;
			//String uid = null;
			MailUtility mailUtility = null;
			//String baseURL = null;
			String forward = null;
			String errorMessage = null;
			
			try {
				facesContext = FacesContext.getCurrentInstance();	
				request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
				session = (HttpSession) facesContext.getExternalContext().getSession(true);				

				userDTO = new UserDTO();
				mailUtility = new MailUtility();
				userDTO.seteMail(eMail);
				userDTO.setUserPassword(password);
				userDTO.setFirstName(firstName);
				userDTO.setLastName(lastName);
				userDTO.setGender(gender);
				
//				if(gender==null || gender.equals("") ||gender.equals("Select")){
//					errorMessage = "Select a gender";					
//					facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
//					
//				}else{		
					
				if(eMail!=null && !eMail.equals("")){
					if(password!=null && !password.equals("")){
						errorMessage = "Email cannot be Blank";	
						userController = UserFactory.createAccount();
						resultDTO = userController.createAccount(userDTO);
						//if(!resultDTO.getUserDTO().getEmailExist()){
							if(resultDTO.getUserDTO().getEmailExist()){
							userEmail = resultDTO.getUserDTO().geteMail();
							//uid = resultDTO.getUserDTO().getUserName();	
							this.userActivationId = resultDTO.getUserDTO().getUserId();	
							this.baseURL = "http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();
							mailUtility.mailAlert(userEmail, this.userActivationId, baseURL, "Account Activation Mail from EVENTATTEND", " ");
							if(resultDTO.isResultStatus()){								
								errorMessage = resultDTO.getResultMsg();
								forward = "register";
								facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,errorMessage,"");
							}else{
								errorMessage = resultDTO.getResultMsg();
								facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
							}
						}else{
							errorMessage = resultDTO.getResultMsg();
							facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
						}
					}else{
						errorMessage = "Password cannot be Blank";
						facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
					}
				}else{
					errorMessage = "Email cannot be Blank";
					facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,"");
				}
				this.firstName = null;
				this.lastName = null;
				this.eMail = null;
				session.setAttribute("activation_id", this.userActivationId);
				//}
			}catch(Exception e){
				e.printStackTrace();
			}
			if(errorMessage != null){
				//facesMessage = new FacesMessage(errorMessage);
				facesContext.addMessage("CREATEACCOUNT", facesMessage);
			}
			return forward; 
		}
	   
	   public void confirmNewUserMailIdListener(ActionEvent ae) {		   
			FacesContext facesContext = null;
			FacesMessage facesMessage = null;
			HttpSession session = null;
			UserController userController = null;
			ResultDTO resultDTO = null;
			UserDTO userDTO = null;
			String userEmailID = null;
			String errorMessage = null;

			try {	
				facesContext = FacesContext.getCurrentInstance();			
				session = (HttpSession) facesContext.getExternalContext().getSession(true);				
				userEmailID = (String) session.getAttribute("ACTIVATION_ID");
				
				userDTO = new UserDTO();	
				
				//if(getActivationID()!=null && !getActivationID().equals("")){
					//userEmailID = getActivationID();
					userDTO.setUserEmailId(userEmailID);
					System.out.println("E-Mail ::" +getActivationID());	
					
				//}
				userController = UserFactory.createAccount();
				resultDTO = userController.confirmNewUserMailId(userDTO);

				if(resultDTO.isUserActivated()){
					errorMessage = resultDTO.getResultMsg();
				}
					
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if(errorMessage != null){
				facesMessage = new FacesMessage(errorMessage);
				facesContext.addMessage("ACTIVATEACCOUNT", facesMessage);
			}
			
			this.afterUserActivation = true;
			this.beforeUserActivation = false;
		   
		   
	   }
	   

		/**
		 * @method- createAccount - To create an account for user if they are not already register
		 *          
		 * @param
		 * @return String
		 */
		public String confirmNewUserMailId() {		
			String forward = null;
			FacesContext facesContext = null;
			FacesMessage facesMessage = null;
			HttpSession session = null;
			UserController userController = null;
			ResultDTO resultDTO = null;
			UserDTO userDTO = null;
			String userEmailID = null;
			String errorMessage = null;

			try {	
				facesContext = FacesContext.getCurrentInstance();			
				session = (HttpSession) facesContext.getExternalContext().getSession(true);				
				userDTO = new UserDTO();	
				
				if(getActivationID()!=null && !getActivationID().equals("")){
					userEmailID = getActivationID();
					userDTO.setUserEmailId(userEmailID);
					System.out.println("E-Mail ::" +getActivationID());	
					
				}
				userController = UserFactory.createAccount();
				resultDTO = userController.confirmNewUserMailId(userDTO);

				if(resultDTO.isUserActivated()){
					errorMessage = resultDTO.getResultMsg();
					forward = "register";
				}
					
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if(errorMessage != null){
				facesMessage = new FacesMessage(errorMessage);
				facesContext.addMessage("ACTIVATEACCOUNT", facesMessage);
			}
			
			this.afterUserActivation = true;
			this.beforeUserActivation = false;
			return forward;
		}
	   
		/**
		 * @method- listener - To upload profile image
		 *          
		 * @param
		 * @return void
		 */		
    public synchronized void listener(UploadEvent event) throws Exception{
    	UploadItem item = event.getUploadItem();
    	String filePath = null;
    	filePath = item.getFileName();
    	item.getContentType();
    	String realPath = null;
    	HttpServletRequest request = null;
    	FacesContext facesContext = null;
		FacesMessage facesMessage = null;	
		HttpSession session = null;	
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		//request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		//realPath = request.getRealPath("images");
    	//System.out.println("realPath===>"+realPath);
      	byte[] bytes = item.getData();
     	FileOutputStream outstream = new FileOutputStream(filePath);
		outstream.write(bytes);
		outstream.close();
    	this.profImgFileName = item.getFileName(); 
    	ResultDTO resultDTO = null;
		LoginDTO loginDTO = null;
		ProfileDTO profileDTO = null;
		boolean resultStatus = false;
		UserController userController = null;		
		profileDTO = new ProfileDTO();	
		profileDTO.setProfImgFileName(this.profImgFileName);  	
    	//loginDTO = (LoginDTO) session.getAttribute("USERPROFILE");
    	String profileImgId = (String) session.getAttribute("USERPROFILEID");
    	profileDTO.setProfileId(profileImgId);
    	profileDTO.setProfImgFileName(this.profImgFileName);
    	profileDTO.setChkImageFromLocal(true);
	    try {
	    	userController = UserFactory.updateUserProfilePic();
			resultDTO = userController.updateUserProfilePic(profileDTO);	
			profileDTO = (ProfileDTO) resultDTO.getProfileDTO();
			System.out.println("profileDTO.getProfImgFileName()==>"+profileDTO.getProfImgFileName());
			
			this.setProfImgFileName(profileDTO.getProfImgFileName());
			this.uploadImgCheckBox = false;
			this.externalImgCheckBox = false;
			this.smImgCheckBox = false;
			this.twitterCheckBox = false;
			this.fbCheckBox = false;
			this.lnCheckBox = false;	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
     }  
    
    
	/**
	 * @method- userProfilePopulate - To populate user profile list
	 * @param 
	 * @return String
	 * 
	 */
	public String userProfilePopulate(){		
		try {
			populateProfileSettings();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "profile";
	}
	
	public void imgFromURLListener(ActionEvent ae){
		if(this.extProfileImgLocation != null && !this.extProfileImgLocation.equals("")){
			this.profImgFileName = this.extProfileImgLocation;
		}else{
			this.profImgFileName = "/images/noPhoto.jpg";
		}
	}
	
	
	/**
	 * @method- populateAccountSettings - To Populate User account settings
	 * @param profileDTO
	 * @return 
	 * 
	 */
	public ProfileDTO getAccountSettingsFormValues(ProfileDTO profileDTO){	
		profileDTO.setFirstName(this.firstName);
		profileDTO.setLastName(this.lastName);	
		
		if(this.newPassword != null && !this.newPassword.equals("")){
			profileDTO.setNewPassword(this.newPassword);	
		}
		return profileDTO;
	}
	
	/**
	 * @method- populateAccountSettings - To Populate User account settings
	 * @param profileDTO
	 * @return 
	 * 
	 */
	public ProfileDTO getSpeakerSettingsFormValues(ProfileDTO profileDTO){	
		
		profileDTO.setSpeakerCheckBox(this.speakerCheckBox);		
		if(this.eventOption!=null && !this.eventOption.equals("Select")){
			profileDTO.setEventOption(this.eventOption);
		}		
		if(this.sessionOption!=null && !this.sessionOption.equals("Select")){
			profileDTO.setSessionOption(this.sessionOption);
		}	
		return profileDTO;
	}
	
	
	public ProfileDTO getTimeZoneSettingsFormValues(ProfileDTO profileDTO){			
		if(this.timeZoneOption!=null && !this.timeZoneOption.equals("Select")){
			profileDTO.setTimeZone(this.timeZoneOption);
		}			
		return profileDTO;
	}
	
	public ProfileDTO getProfilePictureFormValues(ProfileDTO profileDTO){			
		String imgFileName = this.profImgFileName;		
		if(imgFileName != null){
			if(!imgFileName.startsWith("http")){
				int slashIndex = imgFileName.lastIndexOf("\\");
				
				if(slashIndex != -1){
					String fileName = imgFileName.substring(slashIndex);
					System.out.println("Img fileName===>"+fileName);
				}				
			}
		}		
		profileDTO.setProfImgFileName(this.profImgFileName);
		return profileDTO;
	}
	
	
	/**
	 * @method- populateAccountSettings - To Populate User account settings
	 * @param profileDTO
	 * @return 
	 * 
	 */
	public ProfileDTO getContactInfoFormValues(ProfileDTO profileDTO){	
		profileDTO.setMobile(this.mobile);
		profileDTO.setAddress(this.address);
		profileDTO.setCity(this.city);
		profileDTO.setZip(this.zip);
		profileDTO.setState(this.state);
		profileDTO.setCountry(this.country);
		profileDTO.setWebsite(this.website);
		profileDTO.setEducation(this.education);
		profileDTO.setOccupation(this.occupation);
		profileDTO.setSpeakerKeyNotes(this.speakerKeyNotes);
		return profileDTO;
	}

	/**
	 * @method- profileInfo - To Populate User profile information
	 * @param 
	 * @return 
	 * 
	 */
	public void profileInfo() {
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		HttpServletRequest request = null;
		
		UserController usercontroller = null;
		
		ResultDTO resultDTO = null;
		LoginDTO loginUserInfoDTO = null;
		ApplicationDTO applicationDTO = null;
		ProfileDTO profileDTO = null;
		
		String profileId = null;
		String userType = null;
		
		try {
			applicationDTO = new ApplicationDTO();
			profileDTO = new ProfileDTO();
			
			facesContext = FacesContext.getCurrentInstance();
			request = (HttpServletRequest)facesContext.getExternalContext().getRequest();			
			session = (HttpSession) facesContext.getExternalContext().getSession(true);			
			
			profileId = (String)session.getAttribute("USERPROFILEID");
			userType = (String)session.getAttribute("USERTYPE");
			
			profileDTO.setProfileId(profileId);
			
			usercontroller = UserFactory.userProfileInfo();
			resultDTO = usercontroller.userProfileInfo(profileDTO);
			
			applicationDTO.setUserProfileDTO(resultDTO.getProfileDTO());
			
			if(userType.equals("admin")){
				
				applicationDTO.setAdminProfileDTO(resultDTO.getProfileDTO());
				
			}else{
				usercontroller = UserFactory.adminProfileInfo();
			
				resultDTO = usercontroller.adminProfileInfo(profileDTO);
				
				applicationDTO.setAdminProfileDTO(resultDTO.getProfileDTO());
			}
			
			session.setAttribute("APPLICATIONDTO", applicationDTO);			
			session.setAttribute("USERNAME", applicationDTO.getUserProfileDTO().getFirstName()+" "+applicationDTO.getUserProfileDTO().getLastName());
			session.setAttribute("USERTIMEZONE", applicationDTO.getUserProfileDTO().getTimeZone());			
			//session.setAttribute("USERNAME", applicationDTO.getUserProfileDTO().getFirstName() );
			session.setAttribute("USERPIC", applicationDTO.getUserProfileDTO().getProfImgFileName());
			getApplicationBeanInstance().setUserPic(applicationDTO.getUserProfileDTO().getProfImgFileName());
			getApplicationBeanInstance().setUserType(userType);
			System.out.println("Image Path in User Bean===>"+applicationDTO.getUserProfileDTO().getProfImgFileName());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public ResultDTO userProfile(ProfileDTO profileDTO){
		
		UserController usercontroller = null;
		ResultDTO resultDTO = null;
		try {
			usercontroller = UserFactory.userProfileInfo();
			resultDTO = usercontroller.userProfileInfo(profileDTO);
	} catch (Exception e) {		
			e.printStackTrace();
		}			
		
		return resultDTO;
	
	}

	
	public SocialMediaDTO getSocialMediaSettingsFormValues(){
		SocialMediaDTO socialMediaDTO = null;
		boolean twtShowTweets = false;
		boolean twtAllowFriends = false;
		
		boolean fbAllowFriendsToPost = false;
		boolean fbAllowFriendsToConnect = false;
		
		boolean liAllowFriendsToFollow = false;	
		boolean liAllowFriendsToPost = false;	
		
		socialMediaDTO = new SocialMediaDTO();
		
		twtShowTweets = this.twtShowTweets;
		if(twtShowTweets){
			socialMediaDTO.setTwtShowTweets("Y");
		}else{
			socialMediaDTO.setTwtShowTweets("N");
		}
		twtAllowFriends = this.twtAllowFriends;
		if(twtAllowFriends){
			socialMediaDTO.setTwtAllowFriends("Y");
		}else{
			socialMediaDTO.setTwtAllowFriends("N");
		}
		fbAllowFriendsToPost = this.fbAllowFriendsToPost;
		if(fbAllowFriendsToPost){	
			socialMediaDTO.setFbAllowFriendsToPost("Y");
		}else{
			socialMediaDTO.setFbAllowFriendsToPost("N");
		}
		fbAllowFriendsToConnect = this.fbAllowFriendsToConnect;
		if(fbAllowFriendsToConnect){
			socialMediaDTO.setFbAllowFriendsToConnect("Y");
		}else{
			socialMediaDTO.setFbAllowFriendsToConnect("N");
		}
		liAllowFriendsToFollow = this.liAllowFriendsToFollow;
		if(liAllowFriendsToFollow){
			socialMediaDTO.setLiAllowFriendsToFollow("Y");
		}else{
			socialMediaDTO.setLiAllowFriendsToFollow("N");
		}		
		liAllowFriendsToPost = this.liAllowFriendsToPost;
		if(liAllowFriendsToPost){
			socialMediaDTO.setLiAllowFriendsToPost("Y");
		}else{
			socialMediaDTO.setLiAllowFriendsToPost("N");
		}
		
		return socialMediaDTO;
	} 
	
	/**
	 * @method- updateUserProfilePicForSM - To update User Profile picture
	 * @param 
	 * @return String
	 * 
	 */
	public String updateUserProfilePicSocialMedia(ActionEvent event){	
		FacesContext facesContext = null;
		String errorMessage = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		String forward = "profile";

		this.selectedTab = "socialNetworks";

		return forward;
	}
	

	/**
	 * @method- getAllEvents - To populate events in profile for a speaker to select
	 * @param 
	 * @return 
	 * 
	 */		
	public void getAllEvents(){			
		List eventList = new ArrayList();
		Map eventMap = null;
		try{
			//eventList = getEventBeanInstance().getEvents();			
			eventMap = getEventBeanInstance().getEventsForSpeaker();			
			this.eventMap = eventMap;			
			for(int i=0;i<eventList.size();i++)
			{
				eventSelectItems.add(new SelectItem(eventList.get(i)));
			}
			eventList = null;
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void speakerCheckListener(ValueChangeEvent vce){		
		System.out.println("In Value change method");
		if(showEventList){
			this.showEventList = false;
		}else{
			this.showEventList = true;
		}		
	}
	
	public void speakerCheckActionListener(ActionEvent ae){
		if(this.speakerCheckBox){
		this.showEventList = true;
		getAllEvents();
		this.eventOption = "Select an Event";
		}else{
			getAllEvents();
			this.showEventList = false;
			this.showSessionList = false;
			this.speakersAssigned = false;
		}
		System.out.println("In Value change action method");		
	}


	
	// Refined Methods	
	
	
	public void getSessionByEventIdActionListener(ActionEvent ae){		
		this.showSessionList = true;
		//System.out.println("this.eventOption==>"+this.eventOption);
		if(!this.eventOption.equals("Select")){
			getSessionByEventId(this.eventOption);		
		}else{
			this.setShowSessionList(false);
		}
	}
	

	
	
	/**
	 * @method- getSessionByEventId - To populate events in profile according to eventId for a speaker to select
	 * @param 
	 * @return 
	 * 
	 */		
	public void getSessionByEventId(String eventId){			
		List sessionList = null;
		sessionList = new ArrayList();
		SessionDTO sessionDTO = null;
		Map sessionMap = new LinkedHashMap();
		Iterator iterator = null;		
		try{			
			sessionList = getSessionBeanInstance().getSessionByEventId(eventId);
			sessionSelectItems = new ArrayList();
			sessionMap.put("Select a Session", "Select");
			if(sessionList != null && (!sessionList.isEmpty())){				
				iterator = sessionList.iterator();			
				//sessionSelectItems.add(0, new SelectItem("Select"));
				while(iterator.hasNext()){
					sessionDTO = (SessionDTO) iterator.next();
					sessionSelectItems.add(new SelectItem(sessionDTO.getSessionName()));
					sessionMap.put(sessionDTO.getSessionName(), sessionDTO.getSessionId());
				}				
				this.sessionMap = sessionMap;
				this.setShowSessionList(true);
				this.setSpeakersAssigned(false);
				//this.sessionOption = "Select a Session";
			}else{
				sessionList = null;
				this.setShowSessionList(false);
				this.setSpeakersAssigned(true);
				//	this.sessionOption = null;
			}	
			//this.eventOption = null;
			sessionList = null;			
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	

	
	//Refined Methods	

	public void updateProfileSettings(ActionEvent ae) {
		String errorMessage = null;
		String profileId = null;
		String userId = null;
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		ProfileDTO profileDTO = null;
		SocialMediaDTO socialMediaDTO = null;
		HttpSession session = null;
		UserController userController = null;
		ResultDTO resultDTO = null;
		try {
			profileDTO = new ProfileDTO();
			socialMediaDTO = new SocialMediaDTO();
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);
			profileId = (String) session.getAttribute("USERPROFILEID");
			userId = (String) session.getAttribute("USERID");
			profileDTO.setProfileId(profileId);
			profileDTO.setUserId(userId);
			profileDTO = getAccountSettingsFormValues(profileDTO);
			profileDTO = getTimeZoneSettingsFormValues(profileDTO);
			profileDTO = getSpeakerSettingsFormValues(profileDTO);
			profileDTO = getProfilePictureFormValues(profileDTO);
			profileDTO = getContactInfoFormValues(profileDTO);
			socialMediaDTO = getSocialMediaSettingsFormValues();
			socialMediaDTO.setProfileId(profileId);
			profileDTO.setSocialMediaDTO(socialMediaDTO);
			userController = UserFactory.updateProfileSettings();
			resultDTO = userController.updateProfileSettings(profileDTO);
			getApplicationBeanInstance().setUserPic(this.profImgFileName);
			errorMessage = resultDTO.getResultMsg();
			if (resultDTO.isResultStatus()) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						errorMessage, "");
			} else {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						errorMessage, "");
			}
		} catch (Exception e) {
			errorMessage = "Internal Error Occured";
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					errorMessage, "");
			e.printStackTrace();
		}
		if (errorMessage != null) {
			facesContext.addMessage("profileupdate", facesMessage);
		}
	}

	public void populateProfileSettings() {
		FacesContext facesContext = null;
		HttpSession session = null;
		String profileId = null;
		String userId = null;
		UserController userController = null;
		ResultDTO resultDTO = null;
		LoginDTO loginDTO = null;
		ProfileDTO profileDTO = null;
		try {
			profileDTO = new ProfileDTO();
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);
			profileId = (String) session.getAttribute("USERPROFILEID");
			userId = (String) session.getAttribute("USERID");
			profileDTO.setProfileId(profileId);
			profileDTO.setUserId(userId);
			userController = UserFactory.userProfilePopulate();
			resultDTO = userController.userProfilePopulate(profileDTO);
			profileDTO = (ProfileDTO) resultDTO.getProfileDTO();
			populateAccountSettingsForm(profileDTO);
			populateTimeZoneSettingsForm(profileDTO);
			populateSpeakerSettingsForm(profileDTO);
			populateImageSettingsForm(profileDTO);
			populateContactInfoSettingsForm(profileDTO);
			populateSocialMediaSettingsForm(profileDTO.getSocialMediaDTO());
			this.selectedTab = "accountSettings";
			getApplicationBeanInstance().setLike("Profile");
			getApplicationBeanInstance().setCurrentPage("PROFILE");
			getApplicationBeanInstance().setUserPic(this.profImgFileName);
			
			this.setProfileDTO(profileDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void populateAccountSettingsForm(ProfileDTO profileDTO) {
		if (profileDTO != null) {
			this.setFirstName(profileDTO.getFirstName());
			this.setLastName(profileDTO.getLastName());
			this.seteMail(profileDTO.geteMail());
		}
	}

	public void populateTimeZoneSettingsForm(ProfileDTO profileDTO) {
		FacesContext facesContext = null;
		HttpSession session = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		if (profileDTO != null) {
			this.setTimeZoneOption(profileDTO.getTimeZone());
			session.setAttribute("USERTIMEZONE", profileDTO.getTimeZone());
		}
	}

	public void populateSpeakerSettingsForm(ProfileDTO profileDTO) {
		if (profileDTO != null) {
			this.setEventOption(profileDTO.getEventOption());
			this.setSessionOption(profileDTO.getSessionOption());
			this.setSpeakerCheckBox(profileDTO.isSpeakerCheck());
		}
	}

	public void populateImageSettingsForm(ProfileDTO profileDTO) {
		if (profileDTO != null) {
			this.setProfImgFileName(profileDTO.getProfImgFileName());
			this.setTwitterImgUrl(profileDTO.getTwitterImgUrl());
			this.setLinkedInImgUrl(profileDTO.getLinkedInImgUrl());
			this.setFaceBookImgUrl(profileDTO.getFaceBookImgUrl());
		}

	}

	public void populateContactInfoSettingsForm(ProfileDTO profileDTO) {
		if (profileDTO != null) {
			this.setAddress(profileDTO.getAddress());
			this.setMobile(profileDTO.getMobile());
			this.seteMail(profileDTO.getProfileEmail());
			this.setCity(profileDTO.getCity());
			this.setZip(profileDTO.getZip());
			this.setState(profileDTO.getState());
			this.setCountry(profileDTO.getCountry());
			this.setWebsite(profileDTO.getWebsite());
			this.setEducation(profileDTO.getEducation());
			this.setOccupation(profileDTO.getOccupation());
			this.setSpeakerKeyNotes(profileDTO.getSpeakerKeyNotes());
		}

	}

	public void populateSocialMediaSettingsForm(SocialMediaDTO socialMediaDTO) {
		if (socialMediaDTO != null) {
			if (socialMediaDTO.getTwtShowTweets() != null
					&& socialMediaDTO.getTwtShowTweets().equals("Y")) {
				this.twtShowTweets = true;
			} else {
				this.twtShowTweets = false;
			}
			if (socialMediaDTO.getTwtAllowFriends() != null
					&& socialMediaDTO.getTwtAllowFriends().equals("Y")) {
				this.twtAllowFriends = true;
			} else {
				this.twtAllowFriends = false;
			}
			if (socialMediaDTO.getFbAllowFriendsToPost() != null
					&& socialMediaDTO.getFbAllowFriendsToPost().equals("Y")) {
				this.fbAllowFriendsToPost = true;
			} else {
				this.fbAllowFriendsToPost = false;
			}
			if (socialMediaDTO.getFbAllowFriendsToConnect() != null
					&& socialMediaDTO.getFbAllowFriendsToConnect().equals("Y")) {
				this.fbAllowFriendsToConnect = true;
			} else {
				this.fbAllowFriendsToConnect = false;
			}
			if (socialMediaDTO.getLiAllowFriendsToFollow() != null
					&& socialMediaDTO.getLiAllowFriendsToFollow().equals("Y")) {
				this.liAllowFriendsToFollow = true;
			} else {
				this.liAllowFriendsToFollow = false;
			}
			if (socialMediaDTO.getLiAllowFriendsToPost() != null
					&& socialMediaDTO.getLiAllowFriendsToPost().equals("Y")) {
				this.liAllowFriendsToPost = true;
			} else {
				this.liAllowFriendsToPost = false;
			}
			if (socialMediaDTO.getTwitterImgUrl() != null) {
				this.setTwitterImgUrl(socialMediaDTO.getTwitterImgUrl());
			}
			if (socialMediaDTO.getFaceBookImgUrl() != null) {
				this.setFaceBookImgUrl(socialMediaDTO.getFaceBookImgUrl());
			}
			if (socialMediaDTO.getLinkedInImgUrl() != null) {
				this.setLinkedInImgUrl(socialMediaDTO.getLinkedInImgUrl());
			}
		}
	}

	public void imgRefreshListener(ActionEvent ae) {
		System.out.println("imgRefreshListener");
		this.profImgFileName = this.inputImageVal;

	}

	public void removePictureListener(ActionEvent ae) {
		this.profImgFileName = "/images/noPhoto.jpg";
	}
	
	

	public void onTimeZoneSelected(ActionEvent ev) {
		FacesContext facesContext = null;
		UserController userController = null;
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 HttpSession session = null;
		 session = (HttpSession) facesContext.getExternalContext().getSession(true);
		 session.setAttribute("USERTIMEZONE",getApplicationBeanInstance().getSelectedTimeZone());		
		System.out.println("onCountrySelected() invoked!"+getApplicationBeanInstance().getSelectedTimeZone());
		
		String timeZone =  (String)session.getAttribute("USERTIMEZONE");
		String profileId =  (String)session.getAttribute("USERPROFILEID");
		UserDTO userDTO = new UserDTO();
		ProfileDTO profileDTO = new ProfileDTO();		
		profileDTO.setProfileId(profileId);		
		profileDTO.setTimeZone(timeZone);
		userDTO.setProfileDTO(profileDTO);
		try {
			userController = UserFactory.userTimeZone();
			ResultDTO resultDTO = userController.userTimeZone(userDTO);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	

	}
	
	

	public void submitSessionIdForSpeakerActionListener(ActionEvent ae){						
		submitSessionIdForSpeaker(this.sessionOption);		
	}
	
	public void removeSessionIdForSpeakerActionListener(ActionEvent ae){
		this.speakersAssigned = false;
		removeSessionIdForSpeaker(this.sessionOption);	
	}
	
	
	
	public void submitSessionIdForSpeaker(String sessionId){
		
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		String userId = null;
		SpeakerDTO speakerDTO = null;
		try{
			
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		
		userId = (String) session.getAttribute("USERID");
	 
		speakerDTO = getSessionBeanInstance().saveSessionIdForSpeaker(sessionId, userId);
		
		this.profileDTO.setSpeakerDTO(speakerDTO);		
		this.showEventList = false;
		this.showSessionList = false;
		this.speakerCheckBox = false;
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
public void removeSessionIdForSpeaker(String sessionId){
		
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		String userId = null;
		SpeakerDTO speakerDTO = null;
		try{
			
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		
		userId = (String) session.getAttribute("USERID");
	 
		speakerDTO = getSessionBeanInstance().removeSessionIdForSpeaker(sessionId, userId);
		
		this.profileDTO.setSpeakerDTO(speakerDTO);		
		this.showEventList = false;
		this.showSessionList = false;
		this.speakerCheckBox = false;
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
