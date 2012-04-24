package com.eventattend.portal.web.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.eventattend.portal.common.util.AppConfigXMLInfo;
import com.eventattend.portal.common.util.DateUtility;

public class ApplicationBean implements AppConfigXMLInfo {
	
	private String currentPage = null;	
	private String appSessionId = null;
	private String appEventId = null;
	private String linkClicked = null;
	private String currentEvent = null;
	private String currentSession = null;
	private String currentSpeaker = null;	
	private String currentSpeakerToolTip = null;
	private String sessionTooptip = null;
	private String eventTooptip = null;
	private String totalEventLikeCount = null;
	private String eventLikeCount = null;
	private String sessionLikeCount = null;
	//private String sessionCommentLikeCount = null;
	private String userPic = null;
	private String  currentServerTimeTZ = null;
	private boolean totalEventLike = false;
	private boolean eventLike = false;
	private String like = null;
	private boolean sessionCommentLike = false;
	
	private boolean autoRefresh = false;
	private boolean attendeeListAutoRefresh = false;
	private boolean eventattendeeAutoRefresh = false;
	private boolean sessionCommentListAutoRefresh = false;
	private boolean sessionTweetListAutoRefresh = false;
	private boolean sessionAttendeeListAutoRefresh = false;
	private boolean likeRefresh = false;
	
	private boolean showStausMessage = false;
	private boolean socialMediaNotSet = false;
	private String timeZoneOption = null;
	private String selectedTimeZone = null;
	private SelectItem[] timeZoneCategory = new SelectItem[] {
			/*new SelectItem("Select"),new SelectItem("ADT  (Atlantic Daylight Time)"),
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
			*/
			
			
		
			};
	private Map timeZoneCategoryMap = null;
	
	private String userType = null;
	
	public ApplicationBean() {
		
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserPic() {
		return userPic;
	}


	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	

	public boolean isSessionAttendeeListAutoRefresh() {
		return sessionAttendeeListAutoRefresh;
	}

	public void setSessionAttendeeListAutoRefresh(
			boolean sessionAttendeeListAutoRefresh) {
		this.sessionAttendeeListAutoRefresh = sessionAttendeeListAutoRefresh;
	}

	public boolean isEventattendeeAutoRefresh() {
		return eventattendeeAutoRefresh;
	}


	public void setEventattendeeAutoRefresh(boolean eventattendeeAutoRefresh) {
		this.eventattendeeAutoRefresh = eventattendeeAutoRefresh;
	}


	public String getCurrentServerTimeTZ() {
		return currentServerTimeTZ;
	}

	public void setCurrentServerTimeTZ(String currentServerTimeTZ) {
		this.currentServerTimeTZ = currentServerTimeTZ;
	}

	public boolean isLikeRefresh() {
		return likeRefresh;
	}


	public void setLikeRefresh(boolean likeRefresh) {
		this.likeRefresh = likeRefresh;
	}



	public boolean isSocialMediaNotSet() {
		return socialMediaNotSet;
	}



	public void setSocialMediaNotSet(boolean socialMediaNotSet) {
		this.socialMediaNotSet = socialMediaNotSet;
	}



	public boolean isShowStausMessage() {
		return showStausMessage;
	}



	public void setShowStausMessage(boolean showStausMessage) {
		this.showStausMessage = showStausMessage;
	}



	public String getCurrentEvent() {
		return currentEvent;
	}



	public void setCurrentEvent(String currentEvent) {
		this.currentEvent = currentEvent;
	}



	public String getCurrentSession() {
		return currentSession;
	}



	public void setCurrentSession(String currentSession) {
		this.currentSession = currentSession;
	}



	public String getLinkClicked() {
		return linkClicked;
	}


	public void setLinkClicked(String linkClicked) {
		this.linkClicked = linkClicked;
	}





	public boolean isAttendeeListAutoRefresh() {
		return attendeeListAutoRefresh;
	}



	public void setAttendeeListAutoRefresh(boolean attendeeListAutoRefresh) {
		this.attendeeListAutoRefresh = attendeeListAutoRefresh;
	}



	public boolean isSessionCommentListAutoRefresh() {
		return sessionCommentListAutoRefresh;
	}



	public void setSessionCommentListAutoRefresh(
			boolean sessionCommentListAutoRefresh) {
		this.sessionCommentListAutoRefresh = sessionCommentListAutoRefresh;
	}



	public boolean isSessionTweetListAutoRefresh() {
		return sessionTweetListAutoRefresh;
	}



	public void setSessionTweetListAutoRefresh(boolean sessionTweetListAutoRefresh) {
		this.sessionTweetListAutoRefresh = sessionTweetListAutoRefresh;
	}



	public boolean isAutoRefresh() {
		return autoRefresh;
	}

	public void setAutoRefresh(boolean autoRefresh) {
		this.autoRefresh = autoRefresh;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getAppSessionId() {
		return appSessionId;
	}

	public void setAppSessionId(String appSessionId) {
		this.appSessionId = appSessionId;
	}

	public String getAppEventId() {
		return appEventId;
	}

	public void setAppEventId(String appEventId) {
		this.appEventId = appEventId;
	}
	

	public String getTotalEventLikeCount() {
		return totalEventLikeCount;
	}

	public void setTotalEventLikeCount(String totalEventLikeCount) {
		this.totalEventLikeCount = totalEventLikeCount;
	}

	public String getEventLikeCount() {
		return eventLikeCount;
	}

	public void setEventLikeCount(String eventLikeCount) {
		this.eventLikeCount = eventLikeCount;
	}

	public String getSessionLikeCount() {
		return sessionLikeCount;
	}

	public void setSessionLikeCount(String sessionLikeCount) {
		this.sessionLikeCount = sessionLikeCount;
	}
	

//	public String getSessionCommentLikeCount() {
//		return sessionCommentLikeCount;
//	}
//
//	public void setSessionCommentLikeCount(String sessionCommentLikeCount) {
//		this.sessionCommentLikeCount = sessionCommentLikeCount;
//	}
	

	public String getTimeZoneOption() {
		return timeZoneOption;
	}

	public void setTimeZoneOption(String timeZoneOption) {
		this.timeZoneOption = timeZoneOption;
	}

	public String getSessionTooptip() {
		return sessionTooptip;
	}



	public void setSessionTooptip(String sessionTooptip) {
		this.sessionTooptip = sessionTooptip;
	}



	public boolean isTotalEventLike() {
		return totalEventLike;
	}

	public void setTotalEventLike(boolean totalEventLike) {
		this.totalEventLike = totalEventLike;
	}

	public boolean isEventLike() {
		return eventLike;
	}

	public void setEventLike(boolean eventLike) {
		this.eventLike = eventLike;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public boolean isSessionCommentLike() {
		return sessionCommentLike;
	}

	public void setSessionCommentLike(boolean sessionCommentLike) {
		this.sessionCommentLike = sessionCommentLike;
	}
	

	public String getEventTooptip() {
		return eventTooptip;
	}



	public void setEventTooptip(String eventTooptip) {
		this.eventTooptip = eventTooptip;
	}



	public String getCurrentSpeaker() {
		return currentSpeaker;
	}



	public void setCurrentSpeaker(String currentSpeaker) {
		this.currentSpeaker = currentSpeaker;
	}



	public String getCurrentSpeakerToolTip() {
		return currentSpeakerToolTip;
	}



	public void setCurrentSpeakerToolTip(String currentSpeakerToolTip) {
		this.currentSpeakerToolTip = currentSpeakerToolTip;
	}



	public SelectItem[] getTimeZoneCategory() {
		return timeZoneCategory;
	}

	public void setTimeZoneCategory(SelectItem[] timeZoneCategory) {
		this.timeZoneCategory = timeZoneCategory;

	}

	public String getSelectedTimeZone() {
		return selectedTimeZone;
	}

	public void setSelectedTimeZone(String selectedTimeZone) {
		this.selectedTimeZone = selectedTimeZone;
	}

	public UserBean getUserBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		UserBean userBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{userBean}"); 
		 userBean = (UserBean)binding.getValue(facesContext);
		 
		return userBean;
	}
	
	public EventBean getEventBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		EventBean eventBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{eventBean}"); 
		 eventBean = (EventBean)binding.getValue(facesContext);
		 
		return eventBean;
	}
	
	public SocialMediaBean getSocialMediaBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		SocialMediaBean socialMediaBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{socialMediaBean}"); 
		 socialMediaBean = (SocialMediaBean)binding.getValue(facesContext);
		 
		return socialMediaBean;
	}
	
	public SessionBean getSessionBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		SessionBean sessionBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{sessionBean}"); 
		 sessionBean = (SessionBean)binding.getValue(facesContext);
		 
		return sessionBean;
	}
	
	public AttendeeBean getAttendeeBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		AttendeeBean attendeeBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{attendeeBean}"); 
		 attendeeBean = (AttendeeBean)binding.getValue(facesContext);
		 
		return attendeeBean;
	}
	
	public ApplicationBean getApplicationBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		ApplicationBean applicationBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{applicationBean}"); 
		 applicationBean = (ApplicationBean)binding.getValue(facesContext);
		 
		return applicationBean;
	}
	
	public LogoutBean getLogoutBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		LogoutBean logoutBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{logoutBean}"); 
		 logoutBean = (LogoutBean)binding.getValue(facesContext);
		 
		return logoutBean;
	}
	
	public MaintenanceBean getMaintenanceBeanInstance(){
		ValueBinding binding = null;
		FacesContext facesContext = null;
		MaintenanceBean maintenanceBean = null;
		
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 binding = application.createValueBinding("#{maintenanceBean}"); 
		 maintenanceBean = (MaintenanceBean)binding.getValue(facesContext);
		 
		return maintenanceBean;
	}
	
	
	public void initApplicationMode(){
		
		FacesContext facesContext = null;
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 HttpSession session = null;
		 session = (HttpSession) facesContext.getExternalContext().getSession(true);	
		this.selectedTimeZone = (String)session.getAttribute("USERTIMEZONE");
		System.out.println("timeZoneOption >> "+timeZoneOption);
		Map timeZoneCategoryMap = new HashMap();
	/*	timeZoneCategoryMap.put("Select", "Select");
		timeZoneCategoryMap.put("ADT  (Atlantic Daylight Time)", "ADT");
		timeZoneCategoryMap.put("AKDT (Alaska Daylight Time)", "AKDT");
		timeZoneCategoryMap.put("AKST (Alaska Standard Time)", "AKST");
		timeZoneCategoryMap.put("AST  (Atlantic Standard Time)", "AST");
		timeZoneCategoryMap.put("CDT  (Central Daylight Time)", "CDT");
		timeZoneCategoryMap.put("CST  (Central Standard Time)", "CST");y
		timeZoneCategoryMap.put("EDT  (Eastern Daylight Time)", "EDT");
		timeZoneCategoryMap.put("EGST (Eastern Greenland Summer)", "EGST");
		timeZoneCategoryMap.put("EGT  (East Greenland Time)", "EGT");
		timeZoneCategoryMap.put("EST  (Eastern Standard Time)", "EST");
		timeZoneCategoryMap.put("HADT (Hawaii-Aleutian Daylight Time)", "HADT");
		timeZoneCategoryMap.put("HAST (Hawaii-Aleutian Standard Time)", "HAST");
		timeZoneCategoryMap.put("MDT  (Mountain Daylight Time)", "MDT");
		timeZoneCategoryMap.put("MST  (Mountain Standard Time)", "MST");
		timeZoneCategoryMap.put("NDT  (Newfoundland Daylight Time)", "NDT");
		timeZoneCategoryMap.put("NST  (Newfoundland Standard Time)", "NST");
		timeZoneCategoryMap.put("PDT  (Pacific Daylight Time)", "PDT");
		timeZoneCategoryMap.put("PMDT (Pierre & Miquelon Daylight Time)", "PMDT");
		timeZoneCategoryMap.put("PMST (Pierre & Miquelon Standard Time)", "PMST");
		timeZoneCategoryMap.put("PST  (Pacific Standard Time)", "PST");
		timeZoneCategoryMap.put("WGST (Western Greenland Summer Time)", "WGST");
		timeZoneCategoryMap.put("WGT  (West Greenland Time)", "WGT");
		timeZoneCategoryMap.put("WGST (Western Greenland Summer Time)", "WGST");
		timeZoneCategoryMap.put("IST  (India Standard Time)", "IST");*/
		
		
		timeZoneCategoryMap.put("EST  (Eastern Standard Time)", "EST");
		timeZoneCategoryMap.put("PST  (Pacific Standard Time)", "PST");
		timeZoneCategoryMap.put("CST  (Central Standard Time)", "CST");
		timeZoneCategoryMap.put("MST  (Mountain Standard Time)", "MST");		
		timeZoneCategoryMap.put("IST  (India Standard Time)", "IST");
		timeZoneCategoryMap.put("HST  (Hawaii Standard Time)", "HST");
		
		//timeZoneCategoryMap.put("AKST  (Alaska Standard Time)", "AKST");
		
	
		
		
		this.timeZoneCategoryMap=timeZoneCategoryMap;
		
		if(APP_MODE != null){
			if(APP_MODE.equals("development")){				
				this.autoRefresh = false;
				this.attendeeListAutoRefresh = false;
				this.sessionCommentListAutoRefresh = false;
				this.sessionTweetListAutoRefresh = false;
				this.likeRefresh = false;
				this.eventattendeeAutoRefresh = false;
				this.sessionAttendeeListAutoRefresh =  false;
			}else{
				this.autoRefresh = true;
				this.attendeeListAutoRefresh = true;
				this.sessionCommentListAutoRefresh = true;
				this.sessionTweetListAutoRefresh = true;
				this.likeRefresh = true;
				this.eventattendeeAutoRefresh = true;
				this.sessionAttendeeListAutoRefresh =  true;
			}
		}
		
		
	}
	
	public void currentServerTimeTZListener(ActionEvent ae){
	
		FacesContext facesContext = null;
		 facesContext = FacesContext.getCurrentInstance();
		 Application application = facesContext.getApplication();
		 HttpSession session = null;
		 session = (HttpSession) facesContext.getExternalContext().getSession(true);	
		 DateUtility dateUtility = new DateUtility();
		if(session.getAttribute("USERTIMEZONE")!=null){
			//System.out.println("TZ >>"+session.getAttribute("USERTIMEZONE")+" server >>"+dateUtility.currentServerTZ());
			if(session.getAttribute("USERTIMEZONE").equals(dateUtility.currentServerTZ())){
				this.currentServerTimeTZ = dateUtility.currentServerTimeTZ();
				//System.out.println("server TZ >>"+dateUtility.currentServerTimeTZ());
				
			}else{
				String userTZ=(String)session.getAttribute("USERTIMEZONE");
				TimeZone tz = TimeZone.getTimeZone(userTZ);
				this.currentServerTimeTZ = "Choosen TZ - "+dateUtility.currentServerTimeTZ(tz);
				//System.out.println("server TZ >>"+dateUtility.currentServerTimeTZ(tz));
				
			}
		}
	
		
	}
	
	public void timeZoneValueChangeListener(ValueChangeEvent vce){
		
		System.out.println("In Value Change Method===>"+this.selectedTimeZone);
	}

	public Map getTimeZoneCategoryMap() {
		return timeZoneCategoryMap;
	}

	public void setTimeZoneCategoryMap(Map timeZoneCategoryMap) {
		this.timeZoneCategoryMap = timeZoneCategoryMap;
	}
	
}
