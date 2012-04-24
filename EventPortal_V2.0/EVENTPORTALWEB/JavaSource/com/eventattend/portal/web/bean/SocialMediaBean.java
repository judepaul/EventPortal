package com.eventattend.portal.web.bean;

import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import twitter4j.http.AccessToken;

import com.eventattend.portal.Factory.SocialMediaFactory;
import com.eventattend.portal.Factory.UserFactory;
import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.ApplicationDTO;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.MailDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;

public class SocialMediaBean extends ApplicationBean {
	

	private TwitterDTO twitterDTO = null;
	private TwitterDTO allEventTwitterDTO = null;
	private TwitterDTO eventTwitterDTO = null;
	private LinkedInDTO linkedInDTO = null;
	private FaceBookDTO faceBookDTO = null;
	private MailDTO mailDTO = null;
	
	private String linkedInOauthToken = null;
	private String linkedInOauthVerifier = null;
	private String faceBookOauthToken = null;
	
	private boolean twitterConnected = false;
	private boolean adminTwitterConnected = false;
	private boolean linkedInConnected = false;
	private boolean faceBookConnected = false;
	private boolean socialMediaPollEnabled = false;
	private boolean socialMediaBeforeConnect = false;
	private boolean socialMediaAfterConnect = false;
	private boolean allSocialMediaConnected = false;
	private boolean anySocialMediaConnected = false;
	
	private boolean twitterPollEnabled = false;

	private String eventTag = null;
	private String sessionTag = null;
	private String eventId = null;
	private String urlToOpen = null;
	private String profileIdToConnect = null;
	private String currentMedia = null;
	
	
	private String twitterId = null;
	private String faceBookId = null;
	private String linkedInId = null;
	
	private String currentUser = null;
	
	private boolean loadingTweetCheck = true;
	
	
	private String profileName= null;
	private boolean alreadyFollowingInTwitter = false;
	private boolean alreadyFriendInFB = false;
	private boolean alreadyFriendInLin = false;
	private boolean alreadyInviteInLin = false; 
	private boolean requestPending = false;
	private boolean faceBookCheck = false;
	private boolean linkedInCheck = false;
	private boolean twitterCheck = false;
	private boolean alreadyConnectedInSM = false;
	private boolean faceBookFriendsConnect =false;
	private boolean linkedinFriendsConnect = false;
	private boolean userTwitterConnected = false;
	private boolean userFaceBookConnect =false;
	private boolean userLinedInFriendsConnect = false;
	private boolean twitterFollow = false;	
	private boolean twitterProblem = false;
	private String twitterProblemMessage = null;
	private String twitterConnectedToolTip = null;
	private String linkedInConnectedToolTip = null;
	private String faceBookConnectedToolTip = null;
	private String linkedInPubUrl = null;
	private String facebookPubUrl = null;
	private String twitterPubUrl = null;
	private String twitterScreenName = null;
	
	private boolean twitterCheckBoxRenderer = false;
	
	private boolean linkedInCheckBoxRenderer = false;
	
	private boolean inviteButtonRenderer = false;
	
	public boolean isInviteButtonRenderer() {
		return inviteButtonRenderer;
	}

	public void setInviteButtonRenderer(boolean inviteButtonRenderer) {
		this.inviteButtonRenderer = inviteButtonRenderer;
	}

	public boolean isTwitterCheckBoxRenderer() {
		return twitterCheckBoxRenderer;
	}

	public void setTwitterCheckBoxRenderer(boolean twitterCheckBoxRenderer) {
		this.twitterCheckBoxRenderer = twitterCheckBoxRenderer;
	}

	public boolean isLinkedInCheckBoxRenderer() {
		return linkedInCheckBoxRenderer;
	}

	public void setLinkedInCheckBoxRenderer(boolean linkedInCheckBoxRenderer) {
		this.linkedInCheckBoxRenderer = linkedInCheckBoxRenderer;
	}

	public String getTwitterProblemMessage() {
		return twitterProblemMessage;
	}

	public void setTwitterProblemMessage(String twitterProblemMessage) {
		this.twitterProblemMessage = twitterProblemMessage;
	}

	public boolean isTwitterProblem() {
		return twitterProblem;
	}

	public void setTwitterProblem(boolean twitterProblem) {
		this.twitterProblem = twitterProblem;
	}

	public TwitterDTO getAllEventTwitterDTO() {
		return allEventTwitterDTO;
	}

	public void setAllEventTwitterDTO(TwitterDTO allEventTwitterDTO) {
		this.allEventTwitterDTO = allEventTwitterDTO;
	}

	public TwitterDTO getEventTwitterDTO() {
		return eventTwitterDTO;
	}

	public void setEventTwitterDTO(TwitterDTO eventTwitterDTO) {
		this.eventTwitterDTO = eventTwitterDTO;
	}

	public boolean isAlreadyInviteInLin() {
		return alreadyInviteInLin;
	}

	public void setAlreadyInviteInLin(boolean alreadyInviteInLin) {
		this.alreadyInviteInLin = alreadyInviteInLin;
	}

	public boolean isRequestPending() {
		return requestPending;
	}

	public void setRequestPending(boolean requestPending) {
		this.requestPending = requestPending;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isTwitterPollEnabled() {
		return twitterPollEnabled;
	}

	public void setTwitterPollEnabled(boolean twitterPollEnabled) {
		this.twitterPollEnabled = twitterPollEnabled;
	}

	public boolean isAnySocialMediaConnected() {
		return anySocialMediaConnected;
	}

	public void setAnySocialMediaConnected(boolean anySocialMediaConnected) {
		this.anySocialMediaConnected = anySocialMediaConnected;
	}

	public boolean isAllSocialMediaConnected() {
		return allSocialMediaConnected;
	}

	public void setAllSocialMediaConnected(boolean allSocialMediaConnected) {
		this.allSocialMediaConnected = allSocialMediaConnected;
	}

	public boolean isSocialMediaBeforeConnect() {
		return socialMediaBeforeConnect;
	}

	public void setSocialMediaBeforeConnect(boolean socialMediaBeforeConnect) {
		this.socialMediaBeforeConnect = socialMediaBeforeConnect;
	}

	public boolean isSocialMediaAfterConnect() {
		return socialMediaAfterConnect;
	}

	public void setSocialMediaAfterConnect(boolean socialMediaAfterConnect) {
		this.socialMediaAfterConnect = socialMediaAfterConnect;
	}

	public boolean isSocialMediaPollEnabled() {
		return socialMediaPollEnabled;
	}

	public void setSocialMediaPollEnabled(boolean socialMediaPollEnabled) {
		this.socialMediaPollEnabled = socialMediaPollEnabled;
	}

	public String getCurrentMedia() {
		return currentMedia;
	}

	public void setCurrentMedia(String currentMedia) {
		this.currentMedia = currentMedia;
	}

	public MailDTO getMailDTO() {
		return mailDTO;
	}

	public void setMailDTO(MailDTO mailDTO) {
		this.mailDTO = mailDTO;
	}

	public String getUrlToOpen() {
		return urlToOpen;
	}

	public void setUrlToOpen(String urlToOpen) {
		this.urlToOpen = urlToOpen;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getFaceBookId() {
		return faceBookId;
	}

	public void setFaceBookId(String faceBookId) {
		this.faceBookId = faceBookId;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	public String getSessionTag() {
		return sessionTag;
	}

	public void setSessionTag(String sessionTag) {
		this.sessionTag = sessionTag;
	}

	public boolean isLoadingTweetCheck() {
		return loadingTweetCheck;
	}

	public void setLoadingTweetCheck(boolean loadingTweetCheck) {
		this.loadingTweetCheck = loadingTweetCheck;
	}

	public String getEventTag() {
		return eventTag;
	}

	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}

	public TwitterDTO getTwitterDTO() {
		return twitterDTO;
	}

	public void setTwitterDTO(TwitterDTO twitterDTO) {
		this.twitterDTO = twitterDTO;
	}
	
	public boolean isAdminTwitterConnected() {
		return adminTwitterConnected;
	}

	public void setAdminTwitterConnected(boolean adminTwitterConnected) {
		this.adminTwitterConnected = adminTwitterConnected;
	}

	public boolean isTwitterConnected() {
		return twitterConnected;
	}

	public void setTwitterConnected(boolean twitterConnected) {
		this.twitterConnected = twitterConnected;
	}

	public String getLinkedInOauthVerifier() {
		return linkedInOauthVerifier;
	}

	public void setLinkedInOauthVerifier(String linkedInOauthVerifier) {
		this.linkedInOauthVerifier = linkedInOauthVerifier;
	}

	public boolean isLinkedInConnected() {
		return linkedInConnected;
	}

	public void setLinkedInConnected(boolean linkedInConnected) {
		this.linkedInConnected = linkedInConnected;
	}

	public String getLinkedInOauthToken() {
		return linkedInOauthToken;
	}

	public void setLinkedInOauthToken(String linkedInOauthToken) {
		this.linkedInOauthToken = linkedInOauthToken;
	}

	public boolean isFaceBookConnected() {
		return faceBookConnected;
	}

	public void setFaceBookConnected(boolean faceBookConnected) {
		this.faceBookConnected = faceBookConnected;
	}

	public String getFaceBookOauthToken() {
		return faceBookOauthToken;
	}

	public void setFaceBookOauthToken(String faceBookOauthToken) {
		this.faceBookOauthToken = faceBookOauthToken;
	}
	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public LinkedInDTO getLinkedInDTO() {
		return linkedInDTO;
	}

	public void setLinkedInDTO(LinkedInDTO linkedInDTO) {
		this.linkedInDTO = linkedInDTO;
	}

	public FaceBookDTO getFaceBookDTO() {
		return faceBookDTO;
	}

	public void setFaceBookDTO(FaceBookDTO faceBookDTO) {
		this.faceBookDTO = faceBookDTO;
	}

	
	public String getProfileIdToConnect() {
		return profileIdToConnect;
	}

	public void setProfileIdToConnect(String profileIdToConnect) {
		this.profileIdToConnect = profileIdToConnect;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public boolean isAlreadyFollowingInTwitter() {
		return alreadyFollowingInTwitter;
	}

	public void setAlreadyFollowingInTwitter(boolean alreadyFollowingInTwitter) {
		this.alreadyFollowingInTwitter = alreadyFollowingInTwitter;
	}

	public boolean isAlreadyFriendInFB() {
		return alreadyFriendInFB;
	}

	public void setAlreadyFriendInFB(boolean alreadyFriendInFB) {
		this.alreadyFriendInFB = alreadyFriendInFB;
	}

	public boolean isAlreadyFriendInLin() {
		return alreadyFriendInLin;
	}

	public void setAlreadyFriendInLin(boolean alreadyFriendInLin) {
		this.alreadyFriendInLin = alreadyFriendInLin;
	}

	public boolean isFaceBookCheck() {
		return faceBookCheck;
	}

	public void setFaceBookCheck(boolean faceBookCheck) {
		this.faceBookCheck = faceBookCheck;
	}

	public boolean isLinkedInCheck() {
		return linkedInCheck;
	}

	public void setLinkedInCheck(boolean linkedInCheck) {
		this.linkedInCheck = linkedInCheck;
	}

	public boolean isTwitterCheck() {
		return twitterCheck;
	}

	public void setTwitterCheck(boolean twitterCheck) {
		this.twitterCheck = twitterCheck;
	}

	public boolean isAlreadyConnectedInSM() {
		return alreadyConnectedInSM;
	}

	public void setAlreadyConnectedInSM(boolean alreadyConnectedInSM) {
		this.alreadyConnectedInSM = alreadyConnectedInSM;
	}

	public boolean isFaceBookFriendsConnect() {
		return faceBookFriendsConnect;
	}

	public void setFaceBookFriendsConnect(boolean faceBookFriendsConnect) {
		this.faceBookFriendsConnect = faceBookFriendsConnect;
	}

	public boolean isLinkedinFriendsConnect() {
		return linkedinFriendsConnect;
	}

	public void setLinkedinFriendsConnect(boolean linkedinFriendsConnect) {
		this.linkedinFriendsConnect = linkedinFriendsConnect;
	}

	public boolean isUserTwitterConnected() {
		return userTwitterConnected;
	}

	public void setUserTwitterConnected(boolean userTwitterConnected) {
		this.userTwitterConnected = userTwitterConnected;
	}

	public boolean isUserFaceBookConnect() {
		return userFaceBookConnect;
	}

	public void setUserFaceBookConnect(boolean userFaceBookConnect) {
		this.userFaceBookConnect = userFaceBookConnect;
	}

	public boolean isUserLinedInFriendsConnect() {
		return userLinedInFriendsConnect;
	}

	public void setUserLinedInFriendsConnect(boolean userLinedInFriendsConnect) {
		this.userLinedInFriendsConnect = userLinedInFriendsConnect;
	}

	public boolean isTwitterFollow() {
		return twitterFollow;
	}

	public void setTwitterFollow(boolean twitterFollow) {
		this.twitterFollow = twitterFollow;
	}

	public String getTwitterConnectedToolTip() {
		return twitterConnectedToolTip;
	}

	public void setTwitterConnectedToolTip(String twitterConnectedToolTip) {
		this.twitterConnectedToolTip = twitterConnectedToolTip;
	}

	public String getLinkedInConnectedToolTip() {
		return linkedInConnectedToolTip;
	}

	public void setLinkedInConnectedToolTip(String linkedInConnectedToolTip) {
		this.linkedInConnectedToolTip = linkedInConnectedToolTip;
	}

	public String getFaceBookConnectedToolTip() {
		return faceBookConnectedToolTip;
	}

	public void setFaceBookConnectedToolTip(String faceBookConnectedToolTip) {
		this.faceBookConnectedToolTip = faceBookConnectedToolTip;
	}

	public String getLinkedInPubUrl() {
		return linkedInPubUrl;
	}

	public void setLinkedInPubUrl(String linkedInPubUrl) {
		this.linkedInPubUrl = linkedInPubUrl;
	}

	public String getFacebookPubUrl() {
		return facebookPubUrl;
	}

	public void setFacebookPubUrl(String facebookPubUrl) {
		this.facebookPubUrl = facebookPubUrl;
	}

	public String getTwitterPubUrl() {
		return twitterPubUrl;
	}

	public void setTwitterPubUrl(String twitterPubUrl) {
		this.twitterPubUrl = twitterPubUrl;
	}

	public String getTwitterScreenName() {
		return twitterScreenName;
	}

	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}

	public void initSocialMedia(){
		System.out.println("Initialising Social Media...");
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		HttpSession session = null;
		HttpServletRequest request = null;
		
		LoginController logincontroller = null;
		SocialMediaController socialMediaController = null;
		
		ResultDTO resultDTO = null;
		LoginDTO loginDTO = null;
		SocialMediaDTO socialMediaDTO = null;
		ApplicationDTO applicationDTO = null;

		String retValue = "failure";
		String userNameInUpperCase = null;
		String tokenSecret = null;
		String token = null;
		String twitterAuthUrl = null;
		String linkedInAuthUrl = null;
		String faceBookAuthUrl = null;
		String linkedInPubUrl = null;
		String facebookPubUrl = null;
		String twitterPubUrl = null;
		String twitterScreenName = null;
		String errorMessage = null;
		Object accessToken = null;
		Object adminTwitterAccessToken = null;
		Object twitterAccessToken = null;
		Object twitterAdminAccessToken = null;
		Object linkedInRequestToken = null;
		Object linkedInAccessToken = null;
		Object linkedInAdminAccessToken = null;
		Object faceBookAccessToken = null;
		Object faceBookAdminAccessToken = null;
		
		boolean allSocialMediaConnected = false;
		boolean anySocialMediaConnected = false;
		
		String baseURL = null;
		
		try{
			
			facesContext = FacesContext.getCurrentInstance();	
			request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);				
			
			socialMediaDTO = new SocialMediaDTO();
			
			baseURL ="http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();
			
			applicationDTO = (ApplicationDTO) session.getAttribute("APPLICATIONDTO");
			
			if(applicationDTO != null){
				socialMediaDTO.setProfileId(applicationDTO.getUserProfileDTO().getProfileId());
				if(applicationDTO.getUserProfileDTO().getSocialMediaDTO() != null && applicationDTO.getUserProfileDTO().getSocialMediaDTO().getLinkedInProfileUrl()!=null){
				linkedInPubUrl = applicationDTO.getUserProfileDTO().getSocialMediaDTO().getLinkedInProfileUrl();
				}
				if(applicationDTO.getUserProfileDTO().getSocialMediaDTO() != null && applicationDTO.getUserProfileDTO().getSocialMediaDTO().getFaceBookProfileUrl()!=null){
				facebookPubUrl = applicationDTO.getUserProfileDTO().getSocialMediaDTO().getFaceBookProfileUrl();
				}
				if(applicationDTO.getUserProfileDTO().getSocialMediaDTO() != null && applicationDTO.getUserProfileDTO().getSocialMediaDTO().getTwitterId()!=null){
				twitterPubUrl = applicationDTO.getUserProfileDTO().getSocialMediaDTO().getTwitterProfileUrl();
				twitterScreenName = applicationDTO.getUserProfileDTO().getSocialMediaDTO().getTwitterId();
				}
				
				socialMediaDTO.setAdminProfileId(applicationDTO.getAdminProfileDTO().getProfileId());
			}
			
			socialMediaDTO.setBaseURL(baseURL);
			
			socialMediaController = SocialMediaFactory.initSocialMedia();
			
			resultDTO = socialMediaController.initSocialMedia(socialMediaDTO);
			
			token = resultDTO.getSocialMediaDTO().getTwitterDTO().getToken();
			tokenSecret = resultDTO.getSocialMediaDTO().getTwitterDTO().getTokenSecret();
			twitterAuthUrl = resultDTO.getSocialMediaDTO().getTwitterDTO().getAuthUrl();
			linkedInAuthUrl = resultDTO.getSocialMediaDTO().getLinkedInDTO().getAuthUrl();
			faceBookAuthUrl = resultDTO.getSocialMediaDTO().getFaceBookDTO().getAuthUrl();
			
			accessToken = resultDTO.getSocialMediaDTO().getTwitterDTO().getAccessToken();
			adminTwitterAccessToken = resultDTO.getSocialMediaDTO().getTwitterDTO().getAdminAccessToken();
			twitterAccessToken = resultDTO.getSocialMediaDTO().getTwitterDTO().getAccessToken();
			twitterAdminAccessToken = resultDTO.getSocialMediaDTO().getTwitterDTO().getAdminAccessToken();
			
			linkedInRequestToken = resultDTO.getSocialMediaDTO().getLinkedInDTO().getRequestToken();
			linkedInAccessToken = resultDTO.getSocialMediaDTO().getLinkedInDTO().getAccessToken();
			linkedInAdminAccessToken = resultDTO.getSocialMediaDTO().getLinkedInDTO().getAdminAccessToken();
			
			faceBookAccessToken = resultDTO.getSocialMediaDTO().getFaceBookDTO().getAccessToken();
			faceBookAdminAccessToken = resultDTO.getSocialMediaDTO().getFaceBookDTO().getAdminAccessToken();
			
			session.setAttribute("Token", token);
			session.setAttribute("TokenSecret", tokenSecret);
			session.setAttribute("TWITTERAUTHURL", twitterAuthUrl);
			session.setAttribute("LINKEDINAUTHURL", linkedInAuthUrl);
			session.setAttribute("FACEBOOKAUTHURL", faceBookAuthUrl);				
			session.setAttribute("ADMINTWITTERACCESSTOKEN", adminTwitterAccessToken);
			session.setAttribute("USERTWITTERACCESSTOKEN", accessToken);			
			session.setAttribute("FACEBOOKAUTHURL", faceBookAuthUrl);
				
			session.setAttribute("ADMINTWITTERACCESSTOKEN", twitterAdminAccessToken);
			session.setAttribute("USERTWITTERACCESSTOKEN", twitterAccessToken);
			
			session.setAttribute("LINKEDINREQUESTTOKEN", linkedInRequestToken);
			session.setAttribute("LINKEDINACCESSTOKEN", linkedInAccessToken);
			session.setAttribute("ADMINLINKEDINACCESSTOKEN", linkedInAdminAccessToken);
			
			session.setAttribute("FACEBOOKACCESSTOKEN",faceBookAccessToken);
			session.setAttribute("ADMINFACEBOOKACCESSTOKEN",faceBookAdminAccessToken);

			 if(session.getAttribute("USERTWITTERACCESSTOKEN") != null){
					this.twitterConnected = true;
					this.setTwitterConnectedToolTip("@"+twitterScreenName+" twitter connected");
					this.setTwitterId(twitterScreenName);
					this.setTwitterPubUrl(twitterPubUrl);
			}else{
				    this.setTwitterConnectedToolTip("Connect Twitter ");
			}
			 
			 if(session.getAttribute("LINKEDINACCESSTOKEN") != null){
					this.linkedInConnected = true;
					this.setLinkedInConnectedToolTip("LinkedIn connected");
					this.setLinkedInPubUrl(linkedInPubUrl);					
			}else{
					this.setLinkedInConnectedToolTip("Connect LinkedIn");
			}
			 
			 if(session.getAttribute("FACEBOOKACCESSTOKEN") != null){
					this.faceBookConnected = true;
					this.setFaceBookConnectedToolTip("Facebook connected");
					this.setFacebookPubUrl(facebookPubUrl);
			}else{
				    this.setFaceBookConnectedToolTip("Connect Facebook");
			}
			 
			 allSocialMediaConnected = this.twitterConnected && this.linkedInConnected  && this.faceBookConnected;
			 
			 this.allSocialMediaConnected = allSocialMediaConnected;
			 
			 anySocialMediaConnected = this.twitterConnected || this.linkedInConnected  || this.faceBookConnected;
			 
			 this.anySocialMediaConnected = anySocialMediaConnected;
			
			 
		} catch(Exception e){
			e.printStackTrace();
		}
		
		getApplicationBeanInstance().setSocialMediaNotSet(false);
	}
	
	public void configureTwitter(ActionEvent ae) {
		
		FacesContext facesContext = null;
		HttpSession session = null;
		SocialMediaDTO socialMediaDTO =  null;
		ResultDTO resultDTO = null;
		TwitterDTO twitterDTO = null;

		Object userAccessToken = null;

		String forward = "profile";
		String tokenSecret = null;
		String token = null;
		String profileId = null;
		boolean anySocialMediaConnected = false;

		try {

			twitterDTO = new TwitterDTO();

			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);

			token = (String) session.getAttribute("Token");
			tokenSecret = (String) session.getAttribute("TokenSecret");
			profileId = (String) session.getAttribute("USERPROFILEID");

			twitterDTO.setToken(token);
			twitterDTO.setTokenSecret(tokenSecret);
			twitterDTO.setProfileId(profileId);

			resultDTO = addTwitterAccessToken(twitterDTO);
			
			if(resultDTO != null){
				
				twitterDTO = resultDTO.getTwitterDTO();
				if(twitterDTO != null){
					
					if (twitterDTO.isAddStatus()) {

						userAccessToken = (AccessToken) twitterDTO.getAccessToken();

							session.setAttribute("USERTWITTERACCESSTOKEN",
									userAccessToken);
							this.twitterConnected = true;
							/*To populate some twitter info in frontpage*/
							
							socialMediaDTO = getSocialMediaProfileData();
							
							if(socialMediaDTO!=null &&socialMediaDTO.getTwitterId()!=null){
							this.setTwitterConnectedToolTip("@"+socialMediaDTO.getTwitterId()+" twitter connected");
							this.setTwitterId(socialMediaDTO.getTwitterId());
							this.setTwitterPubUrl(socialMediaDTO.getTwitterProfileUrl());
							
							getUserBeanInstance().populateSocialMediaSettingsForm(socialMediaDTO);
							
							this.anySocialMediaConnected = this.twitterConnected || this.linkedInConnected  || this.faceBookConnected;;
							
							}
							
					}
					
				}
				
			}
			
			this.currentMedia = "Twitter";

			this.setSocialMediaAfterConnect(true);
			this.setSocialMediaBeforeConnect(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void configureLinkedIn(ActionEvent ae) {
		//String forward = "profile";
		String forward = "SOCIALCONNECT";
		
		FacesContext facesContext = null;
		HttpSession session = null;
		SocialMediaDTO socialMediaDTO =  null;
	
		LinkedInDTO linkedInDTO = null;
		Object linkedInRequestToken = null;
		String profileId = null;
		ResultDTO resultDTO = null;
		Object linkedInAccessToken = null;
		
		try {
	
	
		linkedInDTO = new LinkedInDTO();
		facesContext = FacesContext.getCurrentInstance();
		
		session = (HttpSession) facesContext.getExternalContext()
		.getSession(true);
		
		linkedInRequestToken = session.getAttribute("LINKEDINREQUESTTOKEN");
		
		profileId = (String)session.getAttribute("USERPROFILEID");
		
		linkedInOauthVerifier = (String)session.getAttribute("oauth_verifier");

		linkedInDTO.setOauthVerifier(linkedInOauthVerifier);
		linkedInDTO.setRequestToken(linkedInRequestToken);
		linkedInDTO.setProfileId(profileId);
		
		resultDTO = addLinkedInAccessToken(linkedInDTO);
		
		if(resultDTO != null){
			linkedInDTO = resultDTO.getLinkedInDTO();
			if(linkedInDTO != null){
				if(linkedInDTO.isAddStatus()){				
					linkedInAccessToken = linkedInDTO.getAccessToken();
					session.setAttribute("LINKEDINACCESSTOKEN",linkedInAccessToken);
					this.linkedInConnected = true;
					
					/*To populate some linked info in frontpage*/
				
					socialMediaDTO = getSocialMediaProfileData();
					
					if(socialMediaDTO!=null && socialMediaDTO.getLinkedInProfileUrl()!=null){
						this.setLinkedInConnectedToolTip("LinkedIn connected");
						this.setLinkedInPubUrl(socialMediaDTO.getLinkedInProfileUrl());		
						
						getUserBeanInstance().populateSocialMediaSettingsForm(socialMediaDTO);

						this.anySocialMediaConnected = this.twitterConnected || this.linkedInConnected  || this.faceBookConnected;;
						
					}
				}
			}
		}
		
		this.currentMedia = "LinkedIn";

		this.setSocialMediaAfterConnect(true);
		this.setSocialMediaBeforeConnect(false);
	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
		
	public void configureFaceBook(ActionEvent ae) {
		
		
		FacesContext facesContext = null;
		HttpSession session = null;
	
		FaceBookDTO faceBookDTO = null;
		SocialMediaDTO socialMediaDTO =  null;
		String profileId = null;
		ResultDTO resultDTO = null;
		Object faceBookAccessToken = null;

		String authToken = null;
		
		try {
	
		faceBookDTO = new FaceBookDTO();
		facesContext = FacesContext.getCurrentInstance();
		
		session = (HttpSession) facesContext.getExternalContext()
		.getSession(true);

		profileId = (String)session.getAttribute("USERPROFILEID");

		authToken = (String)session.getAttribute("AUTH_TOKEN");    
		
		faceBookDTO.setAuthToken(authToken);
		faceBookDTO.setProfileId(profileId);
		
		resultDTO = addFaceBookAccessToken(faceBookDTO);

		if(resultDTO != null){
			faceBookDTO = resultDTO.getFaceBookDTO();
			if(faceBookDTO != null){
				if(faceBookDTO.isAddStatus()){				
					faceBookAccessToken = faceBookDTO.getAccessToken();
					session.setAttribute("FACEBOOKACCESSTOKEN",faceBookAccessToken);
					this.faceBookConnected = true;
					/*To populate some Facebook info in frontpage*/
					
					socialMediaDTO = getSocialMediaProfileData();
					
					if(socialMediaDTO!=null && socialMediaDTO.getFaceBookProfileUrl()!=null){
						this.setFaceBookConnectedToolTip("Facebook connected");
						this.setFacebookPubUrl(socialMediaDTO.getFaceBookProfileUrl());	
						
						getUserBeanInstance().populateSocialMediaSettingsForm(socialMediaDTO);
						
						this.anySocialMediaConnected = this.twitterConnected || this.linkedInConnected  || this.faceBookConnected;;
					}
				}
			}
		}
		
		this.currentMedia = "FaceBook";

		this.setSocialMediaAfterConnect(true);
		this.setSocialMediaBeforeConnect(false);

	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void initialiseProfileTab(){

		getUserBeanInstance().setSelectedTab("socialNetworks");
		
	}
	
	

	public ResultDTO addTwitterAccessToken(TwitterDTO twitterDTO) {
		ResultDTO resultDTO = null;

		SocialMediaController socialMediaController = null;

		try {
			socialMediaController = SocialMediaFactory.addTwitterToken();
			resultDTO = socialMediaController.addTwitterToken(twitterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultDTO;

	}
	
	public ResultDTO addLinkedInAccessToken(LinkedInDTO linkedInDTO){
		ResultDTO resultDTO = null;
		
		SocialMediaController socialMediaController = null;
	
		try {
			socialMediaController = SocialMediaFactory.addLinkedInToken();
			resultDTO = socialMediaController.addLinkedInToken(linkedInDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 return resultDTO;
	}
	
	public ResultDTO addFaceBookAccessToken(FaceBookDTO faceBookDTO){
		ResultDTO resultDTO = null;
		
		SocialMediaController socialMediaController = null;
	
		try {
			socialMediaController = SocialMediaFactory.addFaceBookToken();
			resultDTO = socialMediaController.addFaceBookToken(faceBookDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 return resultDTO;
	}

	public TwitterDTO searchEventTweets(String eventName ) {
		ResultDTO resultDTO = null;
		HttpSession session = null;
		String retValue = "failure";
		SocialMediaController socialMediaController = null;
		TwitterDTO twitterDTO = null;
		FacesContext facesContext = null;
		String userTimeZone =null;
		try{

		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
		session.getAttribute("ADMINTWITTERACCESSTOKEN");
		userTimeZone = (String)session.getAttribute("USERTIMEZONE");
		//eventName = "SXSW";
		if (eventName != null) {
			twitterDTO = new TwitterDTO();		
			twitterDTO.setSearchTweet(eventName);
			
			if(userTimeZone!=null){
				twitterDTO.setUserTimeZone(userTimeZone);
			}
			if (session.getAttribute("ADMINTWITTERACCESSTOKEN") != null) {
				twitterDTO.setAccessToken(session
						.getAttribute("ADMINTWITTERACCESSTOKEN"));
				
					socialMediaController = SocialMediaFactory
							.searchTwitterTweets(twitterDTO);
					resultDTO = socialMediaController
							.searchTwitterTweets(twitterDTO);
					if(resultDTO != null){
						if(resultDTO.isResultStatus()){
							twitterDTO = resultDTO.getTwitterDTO();
							this.loadingTweetCheck = true;
						}else{
							this.twitterProblem = true;
							this.twitterProblemMessage = resultDTO.getResultMsg();
						}
						
					}
				
			}
		}else{
			twitterDTO = new TwitterDTO();
			if(session.getAttribute("ALLEVENTTAGS")!=null){
				twitterDTO.setEventTags((String[]) session.getAttribute("ALLEVENTTAGS"));}
				twitterDTO.setSearchTweet(eventName);
			
				if(userTimeZone!=null){
					twitterDTO.setUserTimeZone(userTimeZone);
				}
				if (session.getAttribute("ADMINTWITTERACCESSTOKEN") != null) {
					twitterDTO.setAccessToken(session
							.getAttribute("ADMINTWITTERACCESSTOKEN"));
					
						socialMediaController = SocialMediaFactory
								.searchTwitterTweets(twitterDTO);
						resultDTO = socialMediaController
								.searchTwitterTweets(twitterDTO);
						if(resultDTO != null){
							if(resultDTO.isResultStatus()){
								if(resultDTO.getResultMsg()!=null){
								twitterDTO.setResultMessage(resultDTO.getResultMsg());}
								twitterDTO = resultDTO.getTwitterDTO();
								this.loadingTweetCheck = true;
							}else{
								this.twitterProblem = true;
								this.twitterProblemMessage = resultDTO.getResultMsg();
							}
							
						}
					
				}
		}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return twitterDTO;

	}
	
	
	public void disconnectTwitter(ActionEvent ae){
		
		FacesMessage facesMessage = null;	
		
		FacesContext facesContext = null;
		HttpSession session = null;

		ResultDTO resultDTO = null;
		TwitterDTO twitterDTO = null;
		String profileId = null;
		String errorMessage = null;
		String twitterAuthUrl = null;
		String token = null; 
		String tokenSecret = null;

		try{
			
			twitterDTO = new TwitterDTO();
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
			profileId = (String) session.getAttribute("USERPROFILEID");
			
			twitterDTO.setProfileId(profileId);
			
			resultDTO = deleteTwitterAccessToken(twitterDTO);
			
			if(resultDTO.isResultStatus()){
				if(resultDTO.getTwitterDTO() != null){					
					twitterAuthUrl = resultDTO.getTwitterDTO().getAuthUrl();
					token = resultDTO.getTwitterDTO().getToken();
					tokenSecret = resultDTO.getTwitterDTO().getTokenSecret();
					session.setAttribute("TWITTERAUTHURL", twitterAuthUrl);
					session.removeAttribute("USERTWITTERACCESSTOKEN");
					session.setAttribute("Token", token);
					session.setAttribute("TokenSecret", tokenSecret);
					
				}
				this.setTwitterConnectedToolTip("Connect Twitter");
				this.setTwitterPubUrl(null);
				errorMessage = "Twitter Disconnected Successfully";
				System.out.println(errorMessage);
				this.twitterConnected = false;
			}else{
				errorMessage = "Problem in disconnecting Twitter. Try after sometime!";
			}
		
		}catch(Exception e){
			errorMessage = "Problem in disconnecting Twitter. Try after sometime!";
			e.printStackTrace();
		}
		
		if(errorMessage != null){
			facesMessage = new FacesMessage(errorMessage);
			facesContext.addMessage("SocialMediaDisconnect", facesMessage);
		}
	}
	
	public ResultDTO deleteTwitterAccessToken(TwitterDTO twitterDTO) {
		ResultDTO resultDTO = null;

		SocialMediaController socialMediaController = null;

		try {
			socialMediaController = SocialMediaFactory.deleteTwitterToken();
			resultDTO = socialMediaController.deleteTwitterToken(twitterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultDTO;

	}
	
	public void disconnectLinkedIn(ActionEvent ae){
		
		FacesMessage facesMessage = null;	
		
		FacesContext facesContext = null;
		HttpSession session = null;
		HttpServletRequest request = null;
		ResultDTO resultDTO = null;
		LinkedInDTO linkedInDTO = null;
		String profileId = null;
		String errorMessage = null;
		String baseURL = null;
		String linkedInAuthUrl =  null;
		Object linkedInRequestToken = null;
		try{
			facesContext = FacesContext.getCurrentInstance();	
			
			linkedInDTO = new LinkedInDTO();
			
			facesContext = FacesContext.getCurrentInstance();
			
			request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
			
			session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
			
			baseURL ="http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();
			
			profileId = (String) session.getAttribute("USERPROFILEID");
			
			linkedInDTO.setProfileId(profileId);
			linkedInDTO.setBaseURL(baseURL);
			
			resultDTO = deleteLinkedInAccessToken(linkedInDTO);
			
			if(resultDTO.isResultStatus()){
				if(resultDTO.getLinkedInDTO() != null){					
					linkedInAuthUrl = resultDTO.getLinkedInDTO().getAuthUrl();
					linkedInRequestToken = resultDTO.getLinkedInDTO().getRequestToken();
					session.removeAttribute("LINKEDINACCESSTOKEN");
					session.setAttribute("LINKEDINAUTHURL", linkedInAuthUrl);
					session.setAttribute("LINKEDINREQUESTTOKEN", linkedInRequestToken);
				}
				this.setLinkedInConnectedToolTip("Connect LinkedIn");
				this.setLinkedInPubUrl(null);		
				errorMessage = "LinkedIn Disconnected Successfully";
				System.out.println(errorMessage);
				this.linkedInConnected= false;
			}else{
				errorMessage = "Problem in disconnecting LinkedIn. Try after sometime!";
			}
		
		}catch(Exception e){
			errorMessage = "Problem in disconnecting LinkedIn. Try after sometime!";
			e.printStackTrace();
		}
		
		if(errorMessage != null){
			facesMessage = new FacesMessage(errorMessage);
			facesContext.addMessage("SocialMediaDisconnect", facesMessage);
		}
	}
	
	public ResultDTO deleteLinkedInAccessToken(LinkedInDTO linkedInDTO) {
		ResultDTO resultDTO = null;

		SocialMediaController socialMediaController = null;

		try {
			socialMediaController = SocialMediaFactory.deleteLinkedInToken();
			resultDTO = socialMediaController.deleteLinkedInToken(linkedInDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}

		return resultDTO;

	}
	
	public void disconnectFaceBook(ActionEvent ae){
		FacesMessage facesMessage = null;	
		
		FacesContext facesContext = null;
		HttpSession session = null;

		ResultDTO resultDTO = null;
		FaceBookDTO faceBookDTO = null;
		String profileId = null;
		String errorMessage = null;
		String faceBookAuthUrl = null;

		try{
			
			faceBookDTO = new FaceBookDTO();
			
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(
				true);
			profileId = (String) session.getAttribute("USERPROFILEID");
			
			faceBookDTO.setProfileId(profileId);
			
			resultDTO = deleteFaceBookAccessToken(faceBookDTO);
			
			if(resultDTO.isResultStatus()){
				session.removeAttribute("FACEBOOKACCESSTOKEN");
				if(resultDTO.getFaceBookDTO() != null){					
					faceBookAuthUrl = resultDTO.getFaceBookDTO().getAuthUrl();
					session.setAttribute("FACEBOOKAUTHURL", faceBookAuthUrl);
				}
				this.setFaceBookConnectedToolTip("Connect Facebook");
				this.setFacebookPubUrl(null);
				errorMessage = "FaceBook Disconnected Successfully";
				System.out.println(errorMessage);
				this.faceBookConnected = false;
				
			}else{
				errorMessage = "Problem in disconnecting FaceBook. Try after sometime!";
			}
		
		}catch(Exception e){
			errorMessage = "Problem in disconnecting FaceBook. Try after sometime!";
			e.printStackTrace();
		}
		
		if(errorMessage != null){
			facesMessage = new FacesMessage(errorMessage);
			facesContext.addMessage("SocialMediaDisconnect", facesMessage);
		}
	}
	
	public ResultDTO deleteFaceBookAccessToken(FaceBookDTO faceBookDTO) {
		ResultDTO resultDTO = null;

		SocialMediaController socialMediaController = null;

		try {
			socialMediaController = SocialMediaFactory.deleteFaceBookToken();
			resultDTO = socialMediaController.deleteFaceBookToken(faceBookDTO);
		} catch (Exception e) {	
			e.printStackTrace();
		}

		return resultDTO;

	}
	
	
	public ResultDTO getTwitterProfileImageURL() {
		FacesContext facesContext = null;
		HttpSession session = null;
		ResultDTO resultDTO = null;
		SocialMediaController socialMediaController = null;		
		String profileId = null;
		TwitterDTO twitterDTO = null;
		twitterDTO = new TwitterDTO();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);		
		profileId = (String) session.getAttribute("USERPROFILEID");
		if(profileId!=null && !profileId.equals("")){
			twitterDTO.setProfileId(profileId);
		}
		if (session.getAttribute("USERTWITTERACCESSTOKEN") != null) {
			twitterDTO.setAccessToken(session.getAttribute("USERTWITTERACCESSTOKEN"));
	 		try {
				socialMediaController = SocialMediaFactory.getTwitterProfileImageURL(twitterDTO);
				resultDTO = socialMediaController.getTwitterProfileImageURL(twitterDTO);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		return resultDTO;
	}
	
	
	
	public void allEventTweets(ActionEvent ae){
		
		String eventsTag = null;
		FacesContext facesContext = null;
		HttpSession session = null;
		String eventsTagArray[] = null;
		TwitterDTO twitterDTO = null;
		FacesMessage facesMessage = null;	
		try{
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);	
		
		//if(getApplicationBeanInstance().isSocialMediaNotSet()){
			//getSocialMediaBeanInstance().initSocialMedia();
		//}
		
		
		twitterDTO = (TwitterDTO)session.getAttribute("ALLEVENTTWEETS");
		eventsTag = (String)session.getAttribute("ALLEVENTSTAG");
		
		if(twitterDTO != null){
			if(twitterDTO.getTweetList() != null){
				this.allEventTwitterDTO = twitterDTO;
				this.eventTag  = eventsTag;
			}else{
				
				eventsTagArray = getEventBeanInstance().eventsSearchTag();
		/*	
			Random rnd = new Random(); 
	   	    int index = rnd.nextInt(eventsTagArray.length);
	   	    eventsTag = eventsTagArray[index];*/
	   	     session.setAttribute("ALLEVENTTAGS", eventsTagArray);
			twitterDTO = searchEventTweets(eventsTag);
			if(twitterDTO != null){
				this.eventTag  = eventsTag;
				this.allEventTwitterDTO = twitterDTO;
				//session.setAttribute("ALLEVENTTWEETS", twitterDTO);			
				session.setAttribute("ALLEVENTSTAG", eventsTag);
			}
			}		
		}else{
			eventsTagArray = getEventBeanInstance().eventsSearchTag();
		
		/*Random rnd = new Random(); 
   	    int index = rnd.nextInt(eventsTagArray.length);   	   
   	 eventsTag = eventsTagArray[index];*/
   		session.setAttribute("ALLEVENTTAGS", eventsTagArray);
   	 	twitterDTO = searchEventTweets(eventsTag);
 	
		if(twitterDTO != null){
			this.eventTag  = eventsTag;
			//this.twitterDTO = twitterDTO;
			
			this.allEventTwitterDTO = twitterDTO;
		//	session.setAttribute("ALLEVENTTWEETS", twitterDTO);		
			session.setAttribute("ALLEVENTSTAG", eventsTag);
		}
			
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eventTag = "";
		this.twitterPollEnabled = false;
		String errorMessage = null;
		if(twitterDTO.getResultMessage()!=null){
			errorMessage=twitterDTO.getResultMessage();
		}
		if(errorMessage != null){
			facesMessage = new FacesMessage(errorMessage);
			facesContext.addMessage("SocialMediaDisconnect", facesMessage);
		}
		
	}
	
	public void searchTweets(ActionEvent ae){
		String searchTag = null;
		TwitterDTO twitterDTO = null;
		StringTokenizer strToken = null;

		searchTag = this.eventTag;
		
		if(searchTag != null){
//			
//			if(searchTag.indexOf('#') != -1){
//				strToken = new StringTokenizer(searchTag,"#");
//				searchTag = strToken.nextToken();
//			}
			
			twitterDTO = searchEventTweets(searchTag);
		}	
		if(twitterDTO != null){
			this.eventTag  = searchTag;
			this.twitterDTO = twitterDTO;
			this.allEventTwitterDTO = twitterDTO;
		}
		
	}
	
	public void eventTweets(ActionEvent ae){
		String eventTag = null;
		FacesContext facesContext = null;
		HttpSession session = null;
		
		TwitterDTO twitterDTO = null;
		String tweetSessionAttribute = null;
		
		try{
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);	
		
		tweetSessionAttribute = "EVENTTWEETS"+this.eventId;
		
		twitterDTO = (TwitterDTO)session.getAttribute(tweetSessionAttribute);
		eventTag = (String)session.getAttribute("EVENTTAG");
		if(twitterDTO != null){
			if(twitterDTO.getTweetList() != null){
				this.eventTwitterDTO = twitterDTO;
				this.eventTag  =eventTag;
			}else{

			eventTag = getEventBeanInstance().eventSearchTag(this.eventId);
	 		
			twitterDTO = searchEventTweets(eventTag);
			if(twitterDTO != null){
				this.eventTag  = eventTag;
				this.eventTwitterDTO = twitterDTO;
				//session.setAttribute(tweetSessionAttribute, twitterDTO);
				session.setAttribute("EVENTTAG", eventTag);
			}
			}
			
		}else{

			eventTag = getEventBeanInstance().eventSearchTag(this.eventId);
	 		twitterDTO = searchEventTweets(eventTag);
			if(twitterDTO != null){
				this.eventTag  = eventTag;
				this.eventTwitterDTO = twitterDTO;
				//session.setAttribute(tweetSessionAttribute, twitterDTO);
				session.setAttribute("EVENTTAG", eventTag);
			}
			
		}
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		this.twitterPollEnabled = false;
	}
	
	public void sessionTweets(ActionEvent ae){
		String sessionTag = null;
		FacesContext facesContext = null;
		HttpSession session = null;
		
		TwitterDTO twitterDTO = null;
		
		try{
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);	
		
		twitterDTO = (TwitterDTO)session.getAttribute("SESSIONTWEETS");
		sessionTag = (String)session.getAttribute("SESSIONTAG");
		
		if(twitterDTO != null){
			if(twitterDTO.getTweetList() != null){
				this.sessionTag  = sessionTag;
				this.twitterDTO = twitterDTO;
			}else{

				sessionTag = getSessionBeanInstance().sessionSearchTag();
		 		
				twitterDTO = searchEventTweets(sessionTag);
				if(twitterDTO != null){
					this.sessionTag  = sessionTag;
					this.twitterDTO = twitterDTO;
					session.setAttribute("SESSIONTWEETS", twitterDTO);
					session.setAttribute("SESSIONTAG", sessionTag);
				}
			}
		}else{
			sessionTag = getSessionBeanInstance().sessionSearchTag();
	 		
			twitterDTO = searchEventTweets(sessionTag);
			if(twitterDTO != null){
				this.sessionTag  = sessionTag;
				this.twitterDTO = twitterDTO;
				session.setAttribute("SESSIONTWEETS", twitterDTO);
				session.setAttribute("SESSIONTAG", sessionTag);
			}
		}
		
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		this.twitterPollEnabled = false;
	}

	public ResultDTO publicProfile(String twitterScreenName,String fbProfileId,String linkedPublicURL) {
		ResultDTO resultDTO = null;
		SocialMediaDTO socialMediaDTO = new SocialMediaDTO(); 
		SocialMediaController socialMediaController = null;
		HttpSession session = null;
		String retValue = "failure";		
		TwitterDTO twitterDTO = null;
		LinkedInDTO linkedInDTO = null;
		FaceBookDTO faceBookDTO =null;
		FacesContext facesContext = null;	
		try {
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(
					true);
			
	
		twitterDTO = new TwitterDTO();
		linkedInDTO = new LinkedInDTO();
		faceBookDTO = new FaceBookDTO();
			if (twitterScreenName != null && (!twitterScreenName.equals(""))) {
			twitterDTO.setAccessToken(session.getAttribute("ADMINTWITTERACCESSTOKEN"));
			twitterDTO.setUserScreeName(twitterScreenName);			
			socialMediaDTO.setTwitterDTO(twitterDTO);
			}
			if (linkedPublicURL != null && (!linkedPublicURL.equals(""))) {
			linkedInDTO.setAccessToken(session.getAttribute("ADMINLINKEDINACCESSTOKEN"));
			linkedInDTO.setBaseURL(linkedPublicURL);
			socialMediaDTO.setLinkedInDTO(linkedInDTO);
			}
			if (fbProfileId != null && (!fbProfileId.equals(""))) {
			faceBookDTO.setAccessToken(session.getAttribute("ADMINFACEBOOKACCESSTOKEN"));
			faceBookDTO.setProfileId(fbProfileId);
			socialMediaDTO.setFaceBookDTO(faceBookDTO);			
			}		
			socialMediaController = SocialMediaFactory.publicProfile(socialMediaDTO);
			resultDTO = socialMediaController.publicProfile(socialMediaDTO);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultDTO;

	}
	
public void speakerSocialProfile(ActionEvent ae){
		this.faceBookDTO = null; // Inorder to clear the value in the previous modal panel
		this.linkedInDTO = null;
		this.twitterDTO = null;	
		ResultDTO resultDTO = null;
		resultDTO = this.getSessionBeanInstance().speakerInformation(this.getSessionBeanInstance().getSessionId());
		ProfileDTO profileDTO =resultDTO.getProfileDTO();
		resultDTO = publicProfile(profileDTO.getTwitterId(),profileDTO.getFaceBookId(),profileDTO.getLinkedInProfileUrl());
		
		if(resultDTO != null){
			if(resultDTO.getTwitterDTO()!=null){
			this.setTwitterDTO(resultDTO.getTwitterDTO());}
			if(resultDTO.getLinkedInDTO()!=null){
			this.setLinkedInDTO(resultDTO.getLinkedInDTO());}
			if(resultDTO.getFaceBookDTO()!=null){
			this.setFaceBookDTO(resultDTO.getFaceBookDTO());}
			
		}
		
		
	}


	public void userSocialProfile(ActionEvent ae){
		this.faceBookDTO = null;  // Inorder to clear the value in the previous modal panel
		this.linkedInDTO = null;
		this.twitterDTO = null;		
		System.out.println("LI >> "+this.linkedInId);
		System.out.println("FB >> "+this.faceBookId);
		System.out.println("T >> "+this.twitterId);
		ResultDTO resultDTO = null;
			
		if((this.linkedInId!=null && (!this.linkedInId.equals(""))) || (this.faceBookId!=null && (!this.faceBookId.equals(""))) || (this.twitterId!=null&& (!this.twitterId.equals("")))){
//		this.linkedInId=null;
//		this.faceBookId=null;
//		this.twitterId=null;
		resultDTO = publicProfile(this.twitterId,this.faceBookId,this.getLinkedInId());
		}
		
		if(resultDTO != null){
			if(resultDTO.getTwitterDTO()!=null){
			this.setTwitterDTO(resultDTO.getTwitterDTO());}
			if(resultDTO.getLinkedInDTO()!=null){
			this.setLinkedInDTO(resultDTO.getLinkedInDTO());}
			if(resultDTO.getFaceBookDTO()!=null){
			this.setFaceBookDTO(resultDTO.getFaceBookDTO());}
			
		}
	}
	
	public void urlOpenListener(ActionEvent ae){
		
		String urlToOpen = null;
		
		urlToOpen = this.urlToOpen;
		
		System.out.println("urlOpenListener Method"+urlToOpen);
		
		this.setUrlToOpen(urlToOpen);
		
	}

public void initSendMail(ActionEvent ae){
		
		MailDTO mailDTO = null;
		
		mailDTO = getAttendeeBeanInstance().initSendMail("");
		
		this.setMailDTO(mailDTO);
		this.twitterConnected = true;
	}
  public void socialMediaConnectInitListener(ActionEvent ae){
	  
		String currentMedia = null;
		
		currentMedia = this.currentMedia;
	  
		System.out.println("Connecting to ==>"+currentMedia);
		
		this.setSocialMediaBeforeConnect(true);
		
	}
  
  public void socialMediaConnectRefreshListener(ActionEvent ae){
	  
	  	this.setSocialMediaBeforeConnect(false);
		this.setSocialMediaAfterConnect(false);
	  
		System.out.println("Refreshing the Connection of ==>"+currentMedia);
  }
  
  public void socialMediaConnectListener(ActionEvent ae){
	  
	  String errorMessage = null;
	  FacesMessage facesMessage = null;	
		
		FacesContext facesContext = null;
		try{
		
		facesContext = FacesContext.getCurrentInstance();	
		errorMessage = this.currentMedia+" connected successfully";
		
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Problem in connecting "+this.currentMedia;
		}
		
		System.out.println(errorMessage);

		facesMessage = new FacesMessage(errorMessage);
		facesContext.addMessage("SocialMediaConnect", facesMessage);
	}
  
  
  public void socialMediaDisConnectListener(ActionEvent ae){
	  
		String currentMedia = null;
		
		currentMedia = this.currentMedia;
	  
		System.out.println("Disconnecting ==>"+currentMedia);
		
	}
  
  public void socialMediaConnectTabListener(ActionEvent ae){
	  
	  System.out.println("social Media Connect Tab Listener");
	  
	  getUserBeanInstance().setSelectedTab("socialNetworks");
	  
  }
  
 

  public void connectViaSMListener(ActionEvent ae){
    FacesContext facesContext = null;
    HttpSession session = null;
    Object currentUserTwitterAccessToken = null;
    String currentUserFBAccessToken = null;
    Object currentUserLinAccessToken = null;
    ProfileDTO profileDTO = null;
    UserController usercontroller = null;
    ResultDTO resultDTO = null;
    String friendTwitterId = null;    
    String friendFaceBookId = null;
    String friendLinkedInId = null;
    String currentUserProfileID = null;
    String profileName = null;
    facesContext = FacesContext.getCurrentInstance();
    session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    currentUserTwitterAccessToken =session.getAttribute("USERTWITTERACCESSTOKEN");    
    currentUserLinAccessToken =session.getAttribute("LINKEDINACCESSTOKEN");
    currentUserFBAccessToken = (String)session.getAttribute("FACEBOOKACCESSTOKEN");
    currentUserProfileID = (String)session.getAttribute("USERPROFILEID");
  
  if(currentUserTwitterAccessToken!=null){this.setUserTwitterConnected(true);}else{this.setUserTwitterConnected(false);}
  if(currentUserFBAccessToken!=null){this.setUserFaceBookConnect(true);}else{this.setUserFaceBookConnect(false);}
  if(currentUserLinAccessToken!=null && !currentUserLinAccessToken.equals("null")){this.setUserLinedInFriendsConnect(true);}else{this.setUserLinedInFriendsConnect(false);}
  
  System.out.println("Current user >> "+currentUserTwitterAccessToken+" <<>> "+currentUserFBAccessToken+" <<>> "+currentUserLinAccessToken);
  System.out.println("User :"+this.isUserTwitterConnected()+" "+this.isUserFaceBookConnect()+" "+this.isUserLinedInFriendsConnect());
  System.out.println("Friends :"+this.isTwitterFollow()+" "+this.isFaceBookFriendsConnect()+" "+this.isLinkedinFriendsConnect());
 
  this.setProfileName(this.getProfileName());
  profileDTO = new ProfileDTO();
  profileDTO.setProfileId(this.profileIdToConnect);

  try {
    usercontroller = UserFactory.userProfileInfo();
    resultDTO = usercontroller.userProfileInfo(profileDTO);
    friendTwitterId = resultDTO.getProfileDTO().getSocialMediaDTO().getTwitterId();   
    friendFaceBookId = resultDTO.getProfileDTO().getSocialMediaDTO().getFaceBookId();
    friendLinkedInId = resultDTO.getProfileDTO().getSocialMediaDTO().getLinkedInId();
    profileName = resultDTO.getProfileDTO().getFirstName();
    System.out.println(" Twitter Friend<< "+friendTwitterId+" >>");
    System.out.println(" FB Friend << "+friendFaceBookId+" >>");
    System.out.println(" Lin Friend << "+friendLinkedInId+" >>");
    
    } catch (Exception e) {
  
    e.printStackTrace();
  }
    SocialMediaDTO socialMediaDTO = new SocialMediaDTO();
    TwitterDTO twitterDTO = null;
    FaceBookDTO faceBookDTO = null;
    LinkedInDTO linkedInDTO = null;
    socialMediaDTO.setProfileId(currentUserProfileID);
    socialMediaDTO.setProfileIdToConnect(this.profileIdToConnect);
    boolean twitterPossile=false;
    boolean fbPossile=false;
    boolean liPossile=false;
    if(twitterFollow==true &&  currentUserTwitterAccessToken!=null && !currentUserTwitterAccessToken.equals("null") && friendTwitterId!=null){
     twitterDTO = new TwitterDTO();
    twitterDTO.setAccessToken(currentUserTwitterAccessToken);
    twitterDTO.setUserScreeName(friendTwitterId);
    socialMediaDTO.setTwitterDTO(twitterDTO);
    
    twitterPossile = true;
    }   
    
    if(faceBookFriendsConnect==true && currentUserFBAccessToken!=null && !currentUserFBAccessToken.equals("null") && friendFaceBookId!=null){
    faceBookDTO = new FaceBookDTO();
    faceBookDTO.setAccessToken(currentUserFBAccessToken);
    faceBookDTO.setProfileId(friendFaceBookId);   
    socialMediaDTO.setFaceBookDTO(faceBookDTO);
    fbPossile = true;
    }
  
    if(linkedinFriendsConnect==true && currentUserLinAccessToken!=null && !currentUserLinAccessToken.equals("null") && friendLinkedInId!=null){
    linkedInDTO = new LinkedInDTO();
    linkedInDTO.setAccessToken(currentUserLinAccessToken);
    linkedInDTO.setProfileId(friendLinkedInId);
    socialMediaDTO.setLinkedInDTO(linkedInDTO);
    liPossile = true;
    }
    
    SocialMediaController socialMediaController = null;

    try {
      
      socialMediaController =SocialMediaFactory.checkAlreadyFriend(socialMediaDTO);
      resultDTO = socialMediaController.checkAlreadyFriend(socialMediaDTO);
      
    } catch (Exception e) {
        e.printStackTrace();
    }
//    boolean alreadyFollowingInTwitter = false;
//    boolean alreadyFriendInFB = false;
//    boolean alreadyFriendInLin = false;
    if(resultDTO.getTwitterDTO()!=null){
      this.setAlreadyFollowingInTwitter(resultDTO.getTwitterDTO().isAlreadyFriends());
      alreadyFollowingInTwitter = resultDTO.getTwitterDTO().isAlreadyFriends();
      TwitterDTO resultTwitterDTO=resultDTO.getTwitterDTO();
      if(alreadyFollowingInTwitter){
    	  resultTwitterDTO.setAlreadyFollowingInTwitterMsg("You are following "+profileName+" in");
      }  
      this.setTwitterDTO(resultTwitterDTO);
      //this.setTwitterCheck(true);
      
    }
  
    if(resultDTO.getFaceBookDTO()!=null){     
        this.setAlreadyFriendInFB(resultDTO.getFaceBookDTO().isAlreadyFriends());
        alreadyFriendInFB = resultDTO.getFaceBookDTO().isAlreadyFriends();     
      //this.setFaceBookCheck(true);    
        this.setFaceBookDTO(resultDTO.getFaceBookDTO());
    }
    
    if(resultDTO.getLinkedInDTO()!=null){
      this.setAlreadyFriendInLin(resultDTO.getLinkedInDTO().isAlreadyFriends());
      alreadyFriendInLin=resultDTO.getLinkedInDTO().isAlreadyFriends();
      alreadyInviteInLin =resultDTO.getLinkedInDTO().isAlreadyInvited();
      requestPending =resultDTO.getLinkedInDTO().isRequestPending();
      this.setLinkedInDTO(resultDTO.getLinkedInDTO());
      //this.setLinkedInCheck(true);
    
  }
    this.setAlreadyConnectedInSM(false);
    
    boolean twitterCheckBoxRenderer = false;
    boolean linkedInCheckBoxRenderer = false;
    boolean inviteButtonRenderer = false;
    
    twitterCheckBoxRenderer = this.userTwitterConnected==true && this.isTwitterFollow() == true && this.alreadyFollowingInTwitter== false;
  
    linkedInCheckBoxRenderer = this.userLinedInFriendsConnect==true && this.alreadyFriendInLin==false && this.alreadyInviteInLin==false && this.requestPending==false;
    
    System.out.println("this.userLinedInFriendsConnect===>"+this.userLinedInFriendsConnect);
    System.out.println("this.alreadyFriendInLin===>"+this.alreadyFriendInLin);
    System.out.println("this.alreadyInviteInLin===>"+this.alreadyInviteInLin);
    System.out.println("this.requestPending===>"+this.requestPending);
    
    System.out.println("linkedInCheckBoxRenderer===>"+linkedInCheckBoxRenderer);
    
    inviteButtonRenderer = twitterCheckBoxRenderer || linkedInCheckBoxRenderer;
    
    this.twitterCheckBoxRenderer = twitterCheckBoxRenderer;
    this.linkedInCheckBoxRenderer = linkedInCheckBoxRenderer;
    this.inviteButtonRenderer = inviteButtonRenderer;
    
    
    /*if(twitterPossile ){
    	if(alreadyFollowingInTwitter==true){
    		  this.setAlreadyConnectedInSM(true);
    	}
    }else if(fbPossile){
    	if(alreadyFriendInFB==true){
  		  this.setAlreadyConnectedInSM(true);
  	}
    }else if(liPossile){
    	if(alreadyFriendInLin==true){
  		  this.setAlreadyConnectedInSM(true);
  	}
    }else if(twitterPossile==true && fbPossile==true){
    	if(alreadyFollowingInTwitter==true || alreadyFriendInFB==true){
  		  this.setAlreadyConnectedInSM(true);
  	}
    }else if(twitterPossile==true && liPossile==true){
    	if(alreadyFollowingInTwitter==true || alreadyFriendInLin==true){
    		  this.setAlreadyConnectedInSM(true);
    	}
    }else if(fbPossile==true && liPossile==true){
    	if(alreadyFriendInFB==true || alreadyFriendInLin==true){
  		  this.setAlreadyConnectedInSM(true);
  	}
    }else{
    	this.setAlreadyConnectedInSM(false);
    }
	*/
    
 
  }

    public void inviteFriends(ActionEvent ae){
      boolean followInTwitter =this.twitterCheck;
      boolean inviteInFB = this.faceBookCheck;
      boolean inviteInLin =this.linkedInCheck;
      String profileName =  null;
      String severity = null;
      System.out.println(this.profileIdToConnect);
      System.out.println(this.twitterCheck);
      System.out.println(this.faceBookCheck);
      System.out.println(this.linkedInCheck);
      
      FacesContext facesContext = null;
      FacesMessage facesMessage = null;
      HttpSession session = null;
      Object currentUserTwitterAccessToken = null;
      String currentUserFBAccessToken = null;
      Object currentUserLinAccessToken = null;    
      UserController usercontroller = null;
      ResultDTO resultDTO = null;
      String friendTwitterId = null;    
      String friendFaceBookId = null;
      String friendLinkedInId = null;
      String currentUserProfileID = null;
      String userTimeZone = null;
      String footerMsg = "";
      boolean alreadyInvited = false;
      SocialMediaController socialMediaController = null;
      ProfileDTO profileDTO = new ProfileDTO();
      profileDTO.setProfileId(this.profileIdToConnect);
      facesContext = FacesContext.getCurrentInstance();
      session = (HttpSession) facesContext.getExternalContext().getSession(true);   
      currentUserTwitterAccessToken =session.getAttribute("USERTWITTERACCESSTOKEN");    
      currentUserLinAccessToken = session.getAttribute("LINKEDINACCESSTOKEN");
      currentUserFBAccessToken = (String)session.getAttribute("FACEBOOKACCESSTOKEN");
      currentUserProfileID = (String)session.getAttribute("USERPROFILEID");
      userTimeZone = (String)session.getAttribute("USERTIMEZONE");

      try {
        usercontroller = UserFactory.userProfileInfo();
        
        resultDTO = usercontroller.userProfileInfo(profileDTO);
        friendTwitterId = resultDTO.getProfileDTO().getSocialMediaDTO().getTwitterId();   
        friendFaceBookId = resultDTO.getProfileDTO().getSocialMediaDTO().getFaceBookId();
        friendLinkedInId = resultDTO.getProfileDTO().getSocialMediaDTO().getLinkedInId();
        System.out.println(" Twitter Friend<< "+friendTwitterId+" >>");
        System.out.println(" FB Friend << "+friendFaceBookId+" >>");
        System.out.println(" Lin Friend << "+friendLinkedInId+" >>");
        profileName = resultDTO.getProfileDTO().getFirstName();
        } catch (Exception e) {   
        e.printStackTrace();
        }
        SocialMediaDTO socialMediaDTO = new SocialMediaDTO();
        socialMediaDTO.setProfileId(currentUserProfileID);
        socialMediaDTO.setProfileIdToConnect(this.profileIdToConnect);
        socialMediaDTO.setConnectedTimeZone(userTimeZone);
        TwitterDTO twitterDTO = null;
        FaceBookDTO faceBookDTO = null;
        LinkedInDTO linkedInDTO = null;
        
        if(followInTwitter){
        if(currentUserTwitterAccessToken!=null && !currentUserTwitterAccessToken.equals("null") && friendTwitterId!=null){
        twitterDTO = new TwitterDTO();
        twitterDTO.setAccessToken(currentUserTwitterAccessToken);
        twitterDTO.setUserScreeName(friendTwitterId);
        socialMediaDTO.setTwitterDTO(twitterDTO);
        }}  
        if(inviteInFB){
        if(currentUserFBAccessToken!=null && !currentUserFBAccessToken.equals("null") && friendFaceBookId!=null){
        faceBookDTO = new FaceBookDTO();
        faceBookDTO.setAccessToken(currentUserFBAccessToken);
        faceBookDTO.setProfileId(friendFaceBookId);   
        faceBookDTO.setProfileUrl( resultDTO.getProfileDTO().getFaceBookProfileUrl());
        socialMediaDTO.setFaceBookDTO(faceBookDTO);
        
        }}
        if(inviteInLin){
        if(currentUserLinAccessToken!=null && !currentUserLinAccessToken.equals("null") && friendLinkedInId!=null){
        linkedInDTO = new LinkedInDTO();
        linkedInDTO.setAccessToken(currentUserLinAccessToken);
        linkedInDTO.setProfileId(friendLinkedInId);
        linkedInDTO.setInviteMessage("Like to connect with you in LinkedIn Vis Event Portal");
        linkedInDTO.setInviteSubject("Invitation -EventPortal User");
        socialMediaDTO.setLinkedInDTO(linkedInDTO);
        }
        }
        try {
          if(followInTwitter==true || inviteInFB==true ||inviteInLin==true){
          socialMediaController =SocialMediaFactory.inviteFriend(socialMediaDTO);
          resultDTO = socialMediaController.inviteFriend(socialMediaDTO);
          }
        }catch(Exception e){
            e.printStackTrace();
        }
          if(resultDTO!=null && resultDTO.getResultMsg()!=null){
             footerMsg =resultDTO.getResultMsg();
          }
          if(resultDTO.getTwitterDTO()!=null){
      
         if(resultDTO.getTwitterDTO().isFriendInvited()&& resultDTO.getTwitterDTO().getResultMessage()==null){
   			 footerMsg = footerMsg +"Following "+profileName+" in Twitter. ";
   			 facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,footerMsg,"");
         	
   		 }else{
   			 if(resultDTO.getTwitterDTO().getResultMessage()!=null){
   	   		footerMsg = footerMsg +"Unable to follow "+profileName+" in Twitter. Due to "+resultDTO.getTwitterDTO().getResultMessage()+".";
   	   		 facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,footerMsg,"");
   	   		 }else{
   			 footerMsg = footerMsg +"Unable to follow "+profileName+" in Twitter. ";
   			 facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,footerMsg,"");
   		
   	   		 }
   		 }
   		
            
          }       
          if(resultDTO.getLinkedInDTO()!=null){
        	  
        		 if(linkedInDTO.isInviteFriend()&& resultDTO.getLinkedInDTO().getResultMessage()==null){
        			 footerMsg = footerMsg +"Connection invitation send to "+profileName+" in LinkedIn";        		
        			 facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,footerMsg,"");
        		 }else{
        			 if(resultDTO.getLinkedInDTO().getResultMessage()!=null){
            			 footerMsg = footerMsg +"Connection invitation not send to "+profileName+" in LinkedIn.Due to "+resultDTO.getLinkedInDTO().getResultMessage()+".";            			
            			 facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,footerMsg,"");
            		 }else{
        		     	 footerMsg = footerMsg +"Connection invitation not send to "+profileName+" in LinkedIn.";
        			     facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,footerMsg,"");
            		 }
        		 }
           }
         
            facesContext.addMessage("SOCIALMEDIAINVITE", facesMessage);
         
    }
    
    //Refined Methods
    
    public void refreshSocialMediaImages(){
    	
    	SocialMediaDTO socialMediaDTO = null;
    	
    	socialMediaDTO = getSocialMediaImageURL();
    	
    	getUserBeanInstance().populateSocialMediaSettingsForm(socialMediaDTO);
    	
    }
    
    
    
    public SocialMediaDTO getSocialMediaImageURL(){
    	
    	FacesContext facesContext = null;
		HttpSession session = null;
		String profileId = null;
    	SocialMediaController socialMediaController = null;
    	ResultDTO resultDTO = null;
    	SocialMediaDTO socialMediaDTO = null;
    	try{
    		socialMediaDTO = new SocialMediaDTO();
    		
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			
			profileId = (String)session.getAttribute("USERPROFILEID");	
    		
    		socialMediaController = SocialMediaFactory.getSocialMediaImageURL();
    		
    		resultDTO = socialMediaController.getSocialMediaImageURL(socialMediaDTO);
    		
    		if(resultDTO != null){
    			socialMediaDTO = resultDTO.getSocialMediaDTO();
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	return socialMediaDTO;
    }
    
    
public SocialMediaDTO getSocialMediaProfileData(){
    	
    	FacesContext facesContext = null;
		HttpSession session = null;
		String profileId = null;
    	SocialMediaController socialMediaController = null;
    	ResultDTO resultDTO = null;
    	SocialMediaDTO socialMediaDTO = null;
    	try{
    		socialMediaDTO = new SocialMediaDTO();
    		
			facesContext = FacesContext.getCurrentInstance();
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			
			profileId = (String)session.getAttribute("USERPROFILEID");
			socialMediaDTO.setProfileId(profileId);
    		
    		socialMediaController = SocialMediaFactory.getSocialMediaProfileData();
    		
    		resultDTO = socialMediaController.getSocialMediaProfileData(socialMediaDTO);
    		
    		if(resultDTO != null){
    			socialMediaDTO = resultDTO.getSocialMediaDTO();
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	return socialMediaDTO;
    }
    
    
    public void socialMediaImgRefreshListener(ActionEvent ae){
 		
		System.out.println("socialMediaImgRefreshListener");
		
		refreshSocialMediaImages();
		
		this.anySocialMediaConnected = this.twitterConnected || this.linkedInConnected  || this.faceBookConnected;;
	}
    
    
  
}


