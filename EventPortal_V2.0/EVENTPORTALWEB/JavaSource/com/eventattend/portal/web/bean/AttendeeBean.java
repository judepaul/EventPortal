package com.eventattend.portal.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import com.eventattend.portal.Factory.AttendeeFactory;
import com.eventattend.portal.Factory.SessionFactory;
import com.eventattend.portal.Factory.SocialMediaFactory;
import com.eventattend.portal.Factory.UserFactory;
import com.eventattend.portal.controller.AttendeeController;
import com.eventattend.portal.controller.SessionController;
import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.ApplicationDTO;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.MailDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SessionDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;
import com.google.code.linkedinapi.client.LinkedInApiClientException;


public class AttendeeBean extends ApplicationBean {

	private AttendeeDTO attendeeDTO = null;
	private MailDTO mailDTO = null;
	
	private String profileMailId = null;
	private String hiddenProfileId = null;
	private String profileMailContent = null;
	private String profileMailCount = null;
	private String hiddenLikeId = null;
	private String hiddenToName = null;
	private int mailCount = 0;
	private String profileIdToConnect = null;
	
	private boolean mailOptionsSelected = false;

	private EventDTO eventDTO = null;
	private int sessionCommentLikeCount = 0;	
	private String scCommentId = null;
	private String scSessionId = null;
	
	private FaceBookDTO faceBookDTO = null;
	private String toMailFirstName = null;
	private String toMailLastName = null;

	private String hiddenMailOption = null;
	
	private String profileName= null;
	public AttendeeBean() {
		super();
	}
	
	public String getHiddenLikeId() {
		return hiddenLikeId;
	}

	public void setHiddenLikeId(String hiddenLikeId) {
		this.hiddenLikeId = hiddenLikeId;
	}



	public String getProfileIdToConnect() {
		return profileIdToConnect;
	}

	public void setProfileIdToConnect(String profileIdToConnect) {
		this.profileIdToConnect = profileIdToConnect;
	}


	public MailDTO getMailDTO() {
		return mailDTO;
	}

	public void setMailDTO(MailDTO mailDTO) {
		this.mailDTO = mailDTO;
	}

	public AttendeeDTO getAttendeeDTO() {
		return attendeeDTO;
	}

	public void setAttendeeDTO(AttendeeDTO attendeeDTO) {
		this.attendeeDTO = attendeeDTO;
	}

	private String mailOption = null;
	private SelectItem [] profileMailOption = new SelectItem[]{
			new SelectItem("Select"),
			//new SelectItem("Invite"),
			new SelectItem("message"),
			new SelectItem("message1"),
			new SelectItem("message2"),
	};

	public String getProfileMailId() {
		return profileMailId;
	}

	public void setProfileMailId(String profileMailId) {
		this.profileMailId = profileMailId;
	}	
	
	public String getHiddenProfileId() {
		return hiddenProfileId;
	}


	public void setHiddenProfileId(String hiddenProfileId) {
		this.hiddenProfileId = hiddenProfileId;
	}

	public String getHiddenToName() {
		return hiddenToName;
	}

	public void setHiddenToName(String hiddenToName) {
		this.hiddenToName = hiddenToName;
	}

	public String getProfileMailContent() {
		return profileMailContent;
	}

	public void setProfileMailContent(String profileMailContent) {
		this.profileMailContent = profileMailContent;
	}
		
	public String getProfileMailCount() {
		return profileMailCount;
	}

	public void setProfileMailCount(String profileMailCount) {
		this.profileMailCount = profileMailCount;
	}

	public boolean getMailOptionsSelected() {
		return mailOptionsSelected;
	}

	public void setMailOptionsSelected(boolean mailOptionsSelected) {
		this.mailOptionsSelected = mailOptionsSelected;
	}	

	public SelectItem[] getProfileMailOption() {
		return profileMailOption;
	}

	public void setProfileMailOption(SelectItem[] profileMailOption) {
		this.profileMailOption = profileMailOption;
	}

	public String getMailOption() {
		return mailOption;
	}

	public void setMailOption(String mailOption) {
		this.mailOption = mailOption;
	}

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

	public int getSessionCommentLikeCount() {
		return sessionCommentLikeCount;
	}

	public void setSessionCommentLikeCount(int sessionCommentLikeCount) {
		this.sessionCommentLikeCount = sessionCommentLikeCount;
	}

	public String getScCommentId() {
		return scCommentId;
	}

	public void setScCommentId(String scCommentId) {
		this.scCommentId = scCommentId;
	}

	public String getScSessionId() {
		return scSessionId;
	}

	public void setScSessionId(String scSessionId) {
		this.scSessionId = scSessionId;
	}
	
	public int getMailCount() {
		return mailCount;
	}

	public void setMailCount(int mailCount) {
		this.mailCount = mailCount;
	}

	public String getToMailFirstName() {
		return toMailFirstName;
	}

	public void setToMailFirstName(String toMailFirstName) {
		this.toMailFirstName = toMailFirstName;
	}

	public String getToMailLastName() {
		return toMailLastName;
	}

	public void setToMailLastName(String toMailLastName) {
		this.toMailLastName = toMailLastName;
	}

	public String getHiddenMailOption() {
		return hiddenMailOption;
	}

	public void setHiddenMailOption(String hiddenMailOption) {
		this.hiddenMailOption = hiddenMailOption;
	}


	public String getProfileName() {
		return profileName;
	}



	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}



	public FaceBookDTO getFaceBookDTO() {
		return faceBookDTO;
	}

	public void setFaceBookDTO(FaceBookDTO faceBookDTO) {
		this.faceBookDTO = faceBookDTO;
	}

	/**
	 * @method- updateUserAccountSettings - To update User Account Settings
	 * @param 
	 * @return String
	 * 
	 */
	public void sendMailToAttendees(ActionEvent event){	
		FacesContext facesContext = null;
		String errorMessage = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		String forward = "agenda";
		AttendeeController attendeeController = null;
		ResultDTO resultDTO = null;
		ProfileDTO profileDTO = null;
		String profileId = null;
		String profileMailToId = null;
		profileDTO = new ProfileDTO();	
		ApplicationDTO applicationDTO = null;
		String toProfileId = null;
		MailDTO mailDTO = null;
		mailDTO = new MailDTO();
		try {
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			//loginDTO = (LoginDTO)session.getAttribute("LOGINUSERINFO");		
			profileId = (String)session.getAttribute("USERPROFILEID");
			applicationDTO = (ApplicationDTO) session.getAttribute("APPLICATIONDTO");
			
			if(this.hiddenProfileId!=null){
				toProfileId = this.hiddenProfileId;
				profileDTO.setToProfileId(toProfileId);
			}
			
			
			if(profileId!=null && !profileId.equals("")){
				profileDTO.setProfileId(profileId);
			}	
			if(applicationDTO.getUserProfileDTO().getFirstName()!=null && !applicationDTO.getUserProfileDTO().getFirstName().equals("")){
				profileDTO.setFirstName(applicationDTO.getUserProfileDTO().getFirstName());
			}
			if(applicationDTO.getUserProfileDTO().getLastName()!=null && !applicationDTO.getUserProfileDTO().getLastName().equals("")){
				profileDTO.setLastName(applicationDTO.getUserProfileDTO().getLastName());
			}			
			if(applicationDTO.getUserProfileDTO().getProfileEmail()!=null && !applicationDTO.getUserProfileDTO().getProfileEmail().equals("")){
				profileDTO.setProfileMailFromId(applicationDTO.getUserProfileDTO().getProfileEmail());
			}
/*			if(this.profileMailId!=null && !this.profileMailId.equals("")){
				profileDTO.setProfileMailToId(this.profileMailId);
			}	
*/			
			mailDTO = getToMailProfileInfo(toProfileId);	
			if(mailDTO.getToMailId()!=null){
				profileDTO.setProfileMailToId(mailDTO.getToMailId());
			}
			
			if(this.profileMailContent!=null && !this.profileMailContent.equals("")){
				profileDTO.setProfileMailContent(this.profileMailContent);
			}
			attendeeController = AttendeeFactory.sendMailToAttendees();
			resultDTO = attendeeController.sendMailToAttendees(profileDTO);
			if(errorMessage != null){
				facesMessage = new FacesMessage(errorMessage);
				facesContext.addMessage("LOGINFAILED", facesMessage);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//return forward;
	}
	
	
	
	
	public MailDTO initSendMail(String toProfileId){
		
		MailDTO mailDTO = null;	
		
		if(toProfileId!=null){
			mailDTO = getToMailProfileInfo(toProfileId);
		}
		if(mailDTO!=null){
			this.mailCount = getProfileMailCount(mailDTO);
			this.toMailFirstName = mailDTO.getToMailFirstName();
			this.toMailLastName = mailDTO.getToMailLastName();
			if(this.mailCount==0){
				mailDTO.setFirstMail(true);
			}
		}
		
		return mailDTO;
	}
	
	
	public MailDTO getToMailProfileInfo(String toProfileId){
		
		MailDTO mailDTO = null;
		
		FacesContext facesContext = null;
		
		HttpSession session = null;
		String profileId = null;
		ApplicationDTO applicationDTO = null;
		String profileFirstName = null;
		String profileLastName = null;
		String profileMail = null;
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();
		AttendeeController attendeeController = null;
		ResultDTO resultDTO = null;
		String mailContent = null;
		try {
			
			toProfileId = this.hiddenProfileId;
			
		//profileDTO = getUserBeanInstance().getProfileDetails(toProfileId);
		mailDTO = new MailDTO();
		
		if(toProfileId != null){
			mailDTO.setToMailId(toProfileId);
			profileDTO.setToProfileId(toProfileId);
			
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
					
			profileId = (String)session.getAttribute("USERPROFILEID");
			profileDTO.setProfileId(profileId);
			
			applicationDTO = (ApplicationDTO) session.getAttribute("APPLICATIONDTO");
			if(applicationDTO.getUserProfileDTO().getFirstName()!=null && !applicationDTO.getUserProfileDTO().getFirstName().equals("")){
				profileFirstName = applicationDTO.getUserProfileDTO().getFirstName();
			}
			if(applicationDTO.getUserProfileDTO().getLastName()!=null && !applicationDTO.getUserProfileDTO().getLastName().equals("")){
				profileLastName = applicationDTO.getUserProfileDTO().getLastName();
			}			
			if(applicationDTO.getUserProfileDTO().getProfileEmail()!=null && !applicationDTO.getUserProfileDTO().getProfileEmail().equals("")){
				profileDTO.setProfileMailFromId(applicationDTO.getUserProfileDTO().getProfileEmail());
			}
			
			attendeeController = AttendeeFactory.getToMailProfileInfo();
			resultDTO = attendeeController.getToMailProfileInfo(profileDTO);
			mailDTO = resultDTO.getMailDTO();			
			mailDTO.setFromMailId(profileMail);
			
		}
	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return mailDTO;

	} 
	
	
	public int getProfileMailCount(MailDTO mailDTO){
				
		FacesContext facesContext = null;		
		HttpSession session = null;
		String profileId = null;
		ApplicationDTO applicationDTO = null;
		String profileFirstName = null;
		String profileLastName = null;		
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();
		AttendeeController attendeeController = null;
		ResultDTO resultDTO = null;
		String mailContent = null;
		String toName = null;
		int mailCount = 0;
		
		try {			
			if(mailDTO != null){				
				profileDTO.setToProfileId(mailDTO.getToMailId());
				toName = mailDTO.getToMailFirstName();
			}
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
					
			profileId = (String)session.getAttribute("USERPROFILEID");
			profileDTO.setProfileId(profileId);
			
			applicationDTO = (ApplicationDTO) session.getAttribute("APPLICATIONDTO");
			if(applicationDTO.getUserProfileDTO().getFirstName()!=null && !applicationDTO.getUserProfileDTO().getFirstName().equals("")){
				profileFirstName = applicationDTO.getUserProfileDTO().getFirstName();
			}
			if(applicationDTO.getUserProfileDTO().getLastName()!=null && !applicationDTO.getUserProfileDTO().getLastName().equals("")){
				profileLastName = applicationDTO.getUserProfileDTO().getLastName();
			}			
			if(applicationDTO.getUserProfileDTO().getProfileEmail()!=null && !applicationDTO.getUserProfileDTO().getProfileEmail().equals("")){
				profileDTO.setProfileMailFromId(applicationDTO.getUserProfileDTO().getProfileEmail());
			}			
			if(mailDTO.getToMailId()!=null){
				profileDTO.setProfileMailToId(mailDTO.getToMailId());
			}
			attendeeController = AttendeeFactory.getMailOptionsForAttendees();
			resultDTO = attendeeController.getMailOptionsForAttendees(profileDTO);
			mailCount = resultDTO.getProfileDTO().getProfileMailCount();
			if(resultDTO.getProfileDTO()!= null && resultDTO.getProfileDTO().getProfileMailCount()==0){		
					mailContent = "Hi " +toName +", \n I would like to connect with you via EVENTATTEND.\n Regards, \n " +profileFirstName +" " +profileLastName;
					this.profileMailContent = mailContent;
					this.mailOptionsSelected=true;
				
			}else {
				this.profileMailContent = null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return mailCount;
	}
	
	public void getMailContent(ActionEvent ae){		
		String mailOption = null;
		String mailContent = null;		
		mailOption = this.mailOption;
		mailOption = this.hiddenMailOption;
		if(mailOption!=null && mailOption.equals("message")){
			mailContent = "Hi Test Message";
			this.profileMailContent = mailContent;

		}
		if(mailOption!=null && mailOption.equals("message1")){
			mailContent = "Hi Test Message1";
			this.profileMailContent = mailContent;

		}
		if(mailOption!=null && mailOption.equals("message2")){
			mailContent = "Hi Test Message2";
			this.profileMailContent = mailContent;

		}
		
	}

	public void initSendMail(ActionEvent ae){
		
		FacesContext facesContext = null;
		String errorMessage = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		String profileId = null;
		ApplicationDTO applicationDTO = null;
		String profileFirstName = null;
		String profileLastName = null;		
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();	
		AttendeeController attendeeController = null;
		ResultDTO resultDTO = null;
		String toProfileId = null;
		String toName = null;
		MailDTO mailDTO = null;
		
		mailDTO = initSendMail(this.hiddenProfileId);
		
		this.mailDTO = mailDTO;
	}

	public ResultDTO getAttendees(AttendeeDTO attendeeDTO) {
		ResultDTO resultDTO = null;

		AttendeeController attendeeController = null;

		try {
			
			attendeeController = AttendeeFactory.getAttendees();
			resultDTO = attendeeController.getAttendees(attendeeDTO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return resultDTO;

	}

	
	public void sessionCommentLikeListener(ActionEvent ae){		
		String sessionId = null;
		String sessionLikeCount = null;
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		HttpSession session = null;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		System.out.println("sessionCommentLikeListener===>"+this.hiddenLikeId);
		sessionId = (String)session.getAttribute("CURRENTSESSIONID");
		System.out.println("session ID===>"+this.scSessionId);
		updateSessionCommentLike();
		getSessionBeanInstance().sessionCommentsList(sessionId);
		sessionLikeCount = getSessionLike(sessionId);
		getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
		//String sessionCommentLikeCount = getSessionCommentLike(hiddenLikeId);
		
/*		String sessionLikeCount = getSessionLike(hiddenLikeId);
		getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
*/
		
	}

	/**
	 * @method updateSessionCommentLike = To update the session comments like count
	 * @return Void 
	 */
	public void updateSessionCommentLike() {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessCommentList = new ArrayList();
		AttendeeDTO attendeeDTO = new AttendeeDTO();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		
		//String sessCmtId = this.scCommentId;
		//String scSessId = this.scSessionId;
		//String sessCmtId = "110012";   
		String sessCmntId = this.hiddenLikeId;
		
		if(sessCmntId!=null){			
			attendeeDTO.setSessionCommentId(sessCmntId);
			//attendeeDTO.setSessionCommentSessId(scSessId);				
		}
		
      try {			    	
    	  attendeeController = AttendeeFactory.updateSessionCommentLikeCount(attendeeDTO);
		resultDTO = attendeeController.updateSessionCommentLikeCount(attendeeDTO);
		this.sessionCommentLikeCount = resultDTO.getAttendeeDTO().getSessionLikeCount();		
      } catch (Exception e) {
		e.printStackTrace();
      }
		
	}

	
	/**
	 * @method getSessionCommentLike  To update the session comments like count
	 * @return Void 
	 */
	public String getSessionCommentLike(String sessionCommentId) {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;
		SessionController sesssionController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessCommentList = new ArrayList();
		String sessionCommentLikeCount = null;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		AttendeeDTO attendeeDTO = new AttendeeDTO();
		//String sessCmtId = this.scCommentId;
		
		//String scSessId = this.scSessionId;
		//String sessCmtId = "110012";
		attendeeDTO.setHiddenLikeId("sessionCommentLike");
		
		if(sessionCommentId!=null){			
			attendeeDTO.setSessionCommentId(sessionCommentId);
			//attendeeDTO.setSessionCommentSessId(scSessId);
			attendeeDTO.setHiddenLikeId("sessionCommentLike");
		}
		
      try {			    	
    	attendeeController = AttendeeFactory.getSessionCommentLike(attendeeDTO);
  		resultDTO = attendeeController.getSessionCommentLike(attendeeDTO);
		//this.sessionCommentLikeCount = resultDTO.getAttendeeDTO().getSessionLikeCount();
		sessionCommentLikeCount = String.valueOf(resultDTO.getAttendeeDTO().getSessionLikeCount());
      } catch (Exception e) {
		e.printStackTrace();
      }
		return sessionCommentLikeCount;
	}

	public void sessionLikeListener(ActionEvent ae){
		
		FacesContext facesContext = null;	
		HttpSession session = null;
		String sessionId = null;
		String sessionLikeCount = null;
		
		try{
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			System.out.println("sessionLikeListener===>"+this.hiddenLikeId);
			
			sessionId = (String)session.getAttribute("CURRENTSESSIONID");
			updateSessionLike(sessionId);
			sessionLikeCount = getSessionLike(sessionId);
			getApplicationBeanInstance().setSessionLikeCount(sessionLikeCount);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @method updateSessionLike = To update the session like count
	 * @return Void 
	 */
	public void updateSessionLike(String sessionId) {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessCommentList = new ArrayList();
		AttendeeDTO attendeeDTO = new AttendeeDTO();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		
		//String sessCmtId = this.scCommentId;
		String scSessId = sessionId;
		//String sessCmtId = "110012";   
		//String sessCmntId = this.hiddenLikeId;
		
		//scSessId = (String)session.getAttribute("CURRENTSESSIONID");
		
		if(scSessId!=null){			
			//attendeeDTO.setSessionCommentId(sessCmntId);
			attendeeDTO.setSessionCommentSessId(scSessId);				
		}
		
      try {			    	
    	  attendeeController = AttendeeFactory.updateSessionLikeCount(attendeeDTO);
		resultDTO = attendeeController.updateSessionLikeCount(attendeeDTO);
		this.sessionCommentLikeCount = resultDTO.getAttendeeDTO().getSessionLikeCount();		
      } catch (Exception e) {
		e.printStackTrace();
      }
		
	}


	
	/**
	 * @method getSessionLike  To update the session like count
	 * @return Void 
	 */
	public String getSessionLike(String sessionId) {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;
		SessionController sesssionController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessList = new ArrayList();
		String sessionLikeCount = null;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");		
		//String sessCmtId = this.scCommentId;
		String scSessId = sessionId;
		AttendeeDTO attendeeDTO = new AttendeeDTO();	
		
		if(scSessId!=null){			
			attendeeDTO.setSessionCommentSessId(scSessId);
			attendeeDTO.setHiddenLikeId("sessionLike");
		}
		
      try {			    	
    	attendeeController = AttendeeFactory.getSessionLike(attendeeDTO);
  		resultDTO = attendeeController.getSessionLike(attendeeDTO);
  		if(resultDTO.getAttendeeDTO()!=null){
			this.sessionCommentLikeCount = resultDTO.getAttendeeDTO().getSessionLikeCount();	
			sessionLikeCount = String.valueOf(resultDTO.getAttendeeDTO().getSessionLikeCount());
  		}
      } catch (Exception e) {
		e.printStackTrace();
      }
		return sessionLikeCount;
	}

	
	public void eventLikeListener(ActionEvent ae){	
		String eventLikeCount = null;
		System.out.println("eventLikeListener===>"+this.hiddenLikeId);
		updateEventLike();		
		eventLikeCount = getEventLike(this.hiddenLikeId);
		getApplicationBeanInstance().setEventLikeCount(eventLikeCount);
	}

	/**
	 * @method updateEventLike = To update the individual event like count
	 * @return Void 
	 */
	public void updateEventLike() {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List eventList = new ArrayList();
		int eventLikeCount = 0;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		AttendeeDTO attendeeDTO = new AttendeeDTO();		
		String eventId = this.hiddenLikeId;
		
		if(eventId!=null){			
			//attendeeDTO.setSessionCommentId(sessCmntId);
			attendeeDTO.setEventId(eventId);				
		}
		
      try {			    	
    	attendeeController = AttendeeFactory.updateEventLikeCount(attendeeDTO);
		resultDTO = attendeeController.updateEventLikeCount(attendeeDTO);
		eventLikeCount = resultDTO.getAttendeeDTO().getEventLikeCount();
      } catch (Exception e) {
		e.printStackTrace();
      }
		
	}

	
	/**
	 * @method getSessionLike  To update the session like count
	 * @return Void 
	 */
	public String getEventLike(String eventId) {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;
		SessionController sesssionController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessList = new ArrayList();
		String eventLikeCount = null;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		AttendeeDTO attendeeDTO = new AttendeeDTO();
		//String sessCmtId = this.scCommentId;
		String sessEventId = eventId;
		
		
		
		if(sessEventId!=null){						
			attendeeDTO.setEventId(sessEventId);
			attendeeDTO.setHiddenLikeId("eventLike");
		}
		
      try {			    	
    	attendeeController = AttendeeFactory.getEventLike(attendeeDTO);
  		resultDTO = attendeeController.getEventLike(attendeeDTO);		
  		eventLikeCount = String.valueOf(resultDTO.getAttendeeDTO().getEventLikeCount());
      } catch (Exception e) {
		e.printStackTrace();
      }
		return eventLikeCount;
	}


	/**
	 * @method getSessionLike  To update the session like count
	 * @return Void 
	 */
	public String getTotalEventLike() {
		ResultDTO resultDTO = null;			
		boolean result = false;
		HttpSession session = null;
		AttendeeController attendeeController = null;
		SessionController sesssionController = null;	
		FacesContext facesContext = null;
		facesContext = FacesContext.getCurrentInstance();
		List sessList = new ArrayList();
		String eventLikeCountSum = null;
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		String userId = (String)session.getAttribute("USERID");
		
      try {	
  		AttendeeDTO attendeeDTO = new AttendeeDTO();
		attendeeDTO.setHiddenLikeId("totalEventLike");
    	attendeeController = AttendeeFactory.getTotalEventLike(attendeeDTO);
  		resultDTO = attendeeController.getTotalEventLike(attendeeDTO);		
  		eventLikeCountSum = String.valueOf(resultDTO.getAttendeeDTO().getEventLikeCountSum());
      } catch (Exception e) {
		e.printStackTrace();
      }
		return eventLikeCountSum;
	}
	
	public void eventSumLikeListener(ActionEvent ae){	
		System.out.println("eventSumLikeListener.....");
	}

	public void populateAttendees(AttendeeDTO attendeeDTO){
		
		this.attendeeDTO = attendeeDTO;
	}
	
		
	
}
