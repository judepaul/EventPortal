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
 * Oct 19, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Keys;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.exceptions.BaseAppException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.LinkedInAlreadyInvitedException;
import com.eventattend.portal.socialmedia.util.SocialMediaKeys;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.ApiStandardProfileRequest;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Educations;
import com.google.code.linkedinapi.schema.Headers;
import com.google.code.linkedinapi.schema.Location;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.SiteStandardProfileRequest;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 19, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class LinkedInBL extends BusinessLayer{

	final String CONSUMER_KEY = SocialMediaKeys.LINKEDIN_API_KEY;
    final String CONSUMER_SECRET = SocialMediaKeys.LINKEDIN_API_SECRET;
   
    final LinkedInOAuthService oauthService;
    
    
    public LinkedInBL(){
    	oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(CONSUMER_KEY, CONSUMER_SECRET);
    }
    
	public LinkedInDTO connectLinkedIn(LinkedInDTO linkedInDTO){
		String AUTHORIZE_URL = "https://api.linkedin.com/uas/oauth/authorize?oauth_token=";
       
		//LinkedInDTO linkedInDTO = null;
		String token = null;
		String tokenSecret = null;		
		String authUrl = null; 

		//LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken();
		String baseURL = null;
		
		baseURL = linkedInDTO.getBaseURL()+"/views/config_linkedin_callback.jsf";
		LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(baseURL);
		//LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken("http://ec2-50-16-39-131.compute-1.amazonaws.com:8080/EVENTPORTAL/views/linkedIn_connect.jsf");
		linkedInDTO = new LinkedInDTO();
        token = requestToken.getToken();
        tokenSecret = requestToken.getTokenSecret();
        authUrl = requestToken.getAuthorizationUrl(); 
        
        System.out.println("Request token: " + token); 
        System.out.println("Token secret: " + tokenSecret);         
        System.out.println("authUrl: " + authUrl);
        
        linkedInDTO.setToken(token);
        linkedInDTO.setTokenSecret(tokenSecret);
        linkedInDTO.setAuthUrl(authUrl);
        linkedInDTO.setRequestToken(requestToken);
        
		return linkedInDTO;
	}
	
	public LinkedInDTO initialiseLinkedIn(LinkedInDTO linkedInDTO) throws EventPortalException{
		
		boolean isLinkedInConnected = false;
		
		 String profileId = null;
		 String adminProfileId = null;
		 
		 LinkedInRequestToken requestToken = null;
		 LinkedInAccessToken accessToken = null;
		 LinkedInAccessToken adminAccessToken = null;
		
		if(linkedInDTO != null){
			profileId = linkedInDTO.getProfileId();
			adminProfileId = linkedInDTO.getAdminProfileId();

			accessToken = getAccessToken(profileId);
			
			if(accessToken != null){
				linkedInDTO = new LinkedInDTO();
				
				//isLinkedInConnected = testLinkedIn(accessToken);
				
				isLinkedInConnected = true;
				
			if(isLinkedInConnected){
				
				linkedInDTO.setAccessToken(accessToken);
			}else{
				linkedInDTO = connectLinkedIn(linkedInDTO);
			}
		}else{
			linkedInDTO = connectLinkedIn(linkedInDTO);
		}
			
			if(!profileId.equals(adminProfileId)){
				adminAccessToken = getAccessToken(adminProfileId);
				linkedInDTO.setAdminAccessToken(adminAccessToken);
			}else{
				linkedInDTO.setAdminAccessToken(accessToken);
			}
			
		}else{
			linkedInDTO = connectLinkedIn(linkedInDTO);
		}
	
		return linkedInDTO;
	}
	
   public LinkedInAccessToken retrieveAccessToken(LinkedInRequestToken requestToken, String oauthVerifier){
		
		final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(CONSUMER_KEY, CONSUMER_SECRET);
		LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
		
		return accessToken;
	}

/**
 * @param linkedInDTO
 * @return
 */
   public LinkedInDTO addToken(LinkedInDTO linkedInDTO) throws EventPortalException {
	
	   boolean addStatus = false;
	   String oauthVerifier = null;
	   String profileId = null;
	   LinkedInRequestToken requestToken = null;
	   LinkedInAccessToken accessToken = null;
	   String profileURL = null;
		
		if(linkedInDTO != null){
			profileId = linkedInDTO.getProfileId();
			 oauthVerifier = linkedInDTO.getOauthVerifier();
			 requestToken = (LinkedInRequestToken) linkedInDTO.getRequestToken();
		}
	   
	   accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
	   
	   Keys keys = new Keys();
	   
	   keys.setHiddenObject("LINKEDIN");
	   keys.setLinkedInAccessToken(accessToken);
	   keys.setProfileId(profileId);
	   
	   addStatus = keys.saveLinkedInAccessToken(keys);
	   
	   if(addStatus){
		   //accessToken = getAccessToken(profileId);
		  
		  //profileURL = getPublicProfile(accessToken);
		  
		  Person profile = getProfile(accessToken);
		  
		  if(profile != null){
			  addStatus = updatePublicProfile(profile.getId(),profile.getPublicProfileUrl(), profile.getPictureUrl() ,profileId);
		  }
		   
		   //addStatus = updatePublicProfile(profileURL, profileId);
		   
		   linkedInDTO.setAccessToken(accessToken);			
		   linkedInDTO.setAddStatus(addStatus);	
	   }
	   
	return linkedInDTO;
}
	
	public LinkedInAccessToken getAccessToken(String profileId) throws EventPortalException{
		Collection accessTokenCollection = null;
		LinkedInAccessToken accessToken = null;
		Keys keys = null;
		
		keys = new Keys();

		keys.setProfileId(profileId);
		accessTokenCollection = keys.getLinkedInAccessToken(keys);
	
		Iterator iter1 = accessTokenCollection.iterator();
		if(iter1 != null){
			while(iter1.hasNext()){		
				keys = (Keys)iter1.next();
				
				if(keys.getLinkedInAccessToken() instanceof LinkedInAccessToken){
					accessToken = (LinkedInAccessToken)keys.getLinkedInAccessToken();
				}
			}	
		}
		
		return accessToken;
	}
  
	
	public LinkedInDTO getLinkedInProfile(LinkedInDTO linkedInDTO){
		Person profile = null;
		ProfileDTO profileDTO = null;
		 LinkedInAccessToken accessToken = null;
		 LinkedInApiClient client = null;
		 
		 if(linkedInDTO != null){
			 
			 accessToken = (LinkedInAccessToken)linkedInDTO.getAccessToken();
			 
			 if(accessToken != null){

				 client = getLinkedInApiClient(accessToken);
				 
				 profile = getUserProfile(client);
				 
				 profileDTO = getProfileData(profile);
				 
				 linkedInDTO = new LinkedInDTO();
				 
				 linkedInDTO.setProfileDTO(profileDTO);
			 }

		 }
		 
		return linkedInDTO;
	}
	
	
	public LinkedInDTO checkAlreadyFriend(LinkedInDTO linkedInDTO){
		 LinkedInAccessToken accessToken = null;
		 LinkedInApiClient client = null;
		 Person profile = null;
		 String friendId = null;
		 if(linkedInDTO != null){
			 
			 accessToken = (LinkedInAccessToken)linkedInDTO.getAccessToken();
			 friendId = linkedInDTO.getProfileId();
			 if(accessToken != null){
				 linkedInDTO = new LinkedInDTO();
				 client = getLinkedInApiClient(accessToken);
				 profile = getUserProfile(client);
				
				 Connections connections = profile.getConnections();
				 if(connections!=null){
				 if(!connections.getPersonList().isEmpty()){					
						Iterator it = connections.getPersonList().iterator();
						while (it.hasNext()) {
							Person person = (Person) it.next();
							if(person.getId().equals(friendId)){
								System.out.println(friendId + " LIn- Already a Friend !");
								linkedInDTO.setAlreadyFriends(true);
							}
						}
				 }
				 }
		
			 }
		 }
		return linkedInDTO;
	}
	public LinkedInDTO inviteFriend(LinkedInDTO linkedInDTO) throws BaseAppException{
		 LinkedInAccessToken accessToken = null;
		 LinkedInApiClient client = null;
		 boolean inviteStatus = false;
		 String tokenValue= null;
		 Person profile =null;
		 String friendId = null;
		 String subject=linkedInDTO.getInviteSubject();
		 String message=linkedInDTO.getInviteMessage();  
		 friendId = linkedInDTO.getProfileId();
		 
		 accessToken = (LinkedInAccessToken) linkedInDTO.getAccessToken();
		
		 if(accessToken != null){
			 subject=linkedInDTO.getInviteSubject();
			 client = getLinkedInApiClient(accessToken);
			 profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.FIRST_NAME,ProfileField.LAST_NAME,ProfileField.HEADLINE,ProfileField.INDUSTRY,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.PICTURE_URL, ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION,ProfileField.API_STANDARD_PROFILE_REQUEST));
			 if(profile.getApiStandardProfileRequest()!=null){
			 tokenValue=profile.getApiStandardProfileRequest().getHeaders().getHttpHeaderList().get(0).getValue();
			 }
			 try{
			 if(friendId!=null & subject!=null &&  message!=null && tokenValue!=null){
			 client.sendInviteById(friendId, subject, message,tokenValue);				
			 inviteStatus = true;
			 }else{
			 inviteStatus = false;
			 }
			 }catch(Exception e){
				 linkedInDTO.setResultMessage(e.getMessage());
				 throw new LinkedInAlreadyInvitedException(e.getMessage());
			 }
			 
		 }
		
		 linkedInDTO.setInviteFriend(inviteStatus);
		return linkedInDTO;
	}
	
	public LinkedInApiClient getLinkedInApiClient(LinkedInAccessToken accessToken){
		
		final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(CONSUMER_KEY, CONSUMER_SECRET); 
		   final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);

		   return client;
	}
	
	public ProfileDTO getProfileData(Person profile){
		
		ProfileDTO profileDTO = null;
		
		if(profile != null){
			
			profileDTO = new ProfileDTO();
			
			profileDTO.setProfileId(profile.getId());
			profileDTO.setFirstName(profile.getFirstName());
			profileDTO.setLastName(profile.getLastName());
			//profileDTO.setProfileLargePhotoLoc(profile.getPictureUrl());
			
			Location location = profile.getLocation();
			
			if(location != null){
				//profileDTO.setLocation(location.getName());
			}
			
			
		}
		
		return profileDTO;
	}
	
   
	 public Person  getUserProfile(LinkedInApiClient client){
		 client.getRequestHeaders();
		 
		 Person initProfile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
		 System.out.print("Current User-Id >>"+initProfile.getId());
	     Person userprofile = client.getProfileById(initProfile.getId(), EnumSet.of(ProfileField.FIRST_NAME,ProfileField.LAST_NAME,ProfileField.HEADLINE,ProfileField.INDUSTRY,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.PICTURE_URL, ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION,ProfileField.CONNECTIONS));
         printResult(userprofile);
		 
		 return userprofile;
	  
	   }
	
	
   public Person  viewUserProfile(LinkedInApiClient client){
	   
  Person profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
  System.out.print("Current User-Id >>"+profile.getId());
  Person profile2 = client.getProfileById(profile.getId(), EnumSet.of(ProfileField.SITE_STANDARD_PROFILE_REQUEST,ProfileField.FIRST_NAME,ProfileField.LAST_NAME,ProfileField.HEADLINE,ProfileField.INDUSTRY,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.PICTURE_URL, ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION));
  printResult(profile2);
  return profile2;
   }
   
   
   public  void printResult(Person profile) { 
  System.out.println("================================");
  System.out.println("PersonID:" + profile.getId()); 
  System.out.println("Name:" + profile.getFirstName() + " " + profile.getLastName());   System.out.println("Headline:" + profile.getHeadline());   System.out.println("API Request:" + profile.getApiStandardProfileRequest());   System.out.println("Industry:" + profile.getIndustry());   System.out.println("Picture:" + profile.getPictureUrl());  
  
  System.out.println("Public URL:" + profile.getPublicProfileUrl());
  System.out.println("ConnectionsL:" + profile.getConnections());
   ApiStandardProfileRequest apiStandardProfileRequest=profile.getApiStandardProfileRequest();
   Headers headers=apiStandardProfileRequest.getHeaders();
   List list =headers.getHttpHeaderList();
   
  System.out.println("Header:" + apiStandardProfileRequest.getHeaders());
  System.out.println("================================");
 
 }
/**
 * @param linkedInDTO
 * @return
 */
public LinkedInDTO getUserDetails(LinkedInDTO linkedInDTO) {
	
	linkedInDTO = getLinkedInProfile(linkedInDTO);
	
	return linkedInDTO;
}

/**
 * @param linkedInDTO
 * @return
 */
public boolean inviteFriend(LinkedInDTO linkedInDTO, Profile profile) {
	boolean inviteStatus = false;
	 LinkedInAccessToken accessToken = null;
	 LinkedInApiClient client = null;
	 String inviteMessage = null;
	 
	if(linkedInDTO != null){
			accessToken = (LinkedInAccessToken)linkedInDTO.getAccessToken();
			 inviteMessage = linkedInDTO.getInviteMessage();
			 
			 if(accessToken != null){

				 client = getLinkedInApiClient(accessToken);
				 
				 inviteStatus = sendInvitation(client, inviteMessage, profile);
			 
		}
	}
	
	
	return inviteStatus;
}

	public void sendInvitation(LinkedInApiClient client, String emailId, String firstName, String lastName, String subject, String message){
	
		client.sendInviteByEmail(emailId, firstName, lastName, subject, message);
		
	}
	
	public boolean sendInvitation(LinkedInApiClient client, String inviteMessage, Profile profile){
		
		boolean inviteStatus = false;
		String subject = null;
		
		subject = "Invitaion through Event Attend";
		
		if(profile != null){
			
			try{
				//client.sendInviteByEmail(profile.getEmailId(), profile.getFirstName(), profile.getLastName(), subject, inviteMessage);	
				inviteStatus = true;
				//client.sendMessage(arg0, arg1, arg2)(arg0, arg1, arg2)
			}catch(Exception e){
				
				e.printStackTrace();
			}
				
		}
		
		return inviteStatus;
		
	}
	
	public boolean testLinkedIn(LinkedInAccessToken accessToken){
		
		boolean result = false;
		
		LinkedInApiClient client = null;
		
		try{
		
			client = getLinkedInApiClient(accessToken);
			
			 Person profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
			 Person profile2 = client.getProfileById(profile.getId(), EnumSet.of(ProfileField.FIRST_NAME,ProfileField.LAST_NAME));
			 result = true;
			 System.out.println("Welcome "+profile2.getFirstName()+" "+profile2.getLastName()+" to LinkedIn ! You are successfully connected.");
			 

			
		//	  Person userprofile =  client.getProfileByUrl("http://www.linkedin.com/in/telkganesan",ProfileType.PUBLIC,EnumSet.of(ProfileField.FIRST_NAME,ProfileField.LAST_NAME,ProfileField.HEADLINE,ProfileField.INDUSTRY,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.PICTURE_URL, ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION,ProfileField.CONNECTIONS,ProfileField.EDUCATIONS,ProfileField.HONORS,ProfileField.HEADLINE,ProfileField.SUMMARY,ProfileField.SPECIALTIES,ProfileField.SITE_STANDARD_PROFILE_REQUEST));
				                                                                                                                                                                 
		//	 printResult1(userprofile);             
			                                                                                                                                                                                                                                                                                                      
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Unable to connect to LinkedIn..!");
		}
		return result;
		
	}
	
	public LinkedInDTO publicProfile(LinkedInDTO linkedInDTO){
		LinkedInAccessToken accessToken =(LinkedInAccessToken) linkedInDTO.getAccessToken();
		LinkedInApiClient client = null;
		client = getLinkedInApiClient(accessToken);
		
		Person userprofile =  client.getProfileByUrl(linkedInDTO.getBaseURL(),ProfileType.PUBLIC,EnumSet.of(ProfileField.FIRST_NAME,ProfileField.LAST_NAME,ProfileField.HEADLINE,ProfileField.INDUSTRY,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.PICTURE_URL, ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION,ProfileField.CONNECTIONS,ProfileField.EDUCATIONS,ProfileField.HONORS,ProfileField.HEADLINE,ProfileField.SUMMARY,ProfileField.SPECIALTIES,ProfileField.SITE_STANDARD_PROFILE_REQUEST));
		linkedInDTO = new  LinkedInDTO();
		linkedInDTO= fetchProfile(userprofile,linkedInDTO);  
		return linkedInDTO;
	}
	
	  public  LinkedInDTO fetchProfile(Person profile,LinkedInDTO linkedInDTO) {           
	 System.out.println("================================");
	 
	 String name = profile.getFirstName() + " " + profile.getLastName();
	 String headLine =profile.getHeadline();
	 String industry =profile.getIndustry();
	 String profileImg = profile.getPictureUrl();
	 String pubProfielUrl =  profile.getPublicProfileUrl();
	// String education =profile.getEducations();
		 String specialities = profile.getSpecialties();
			 String summary =profile.getSummary();	
	 String locationName=null;
	 String connectionTotal=null;
if(profile.getId()!=null){
	 System.out.println("PersonID:" + profile.getId());
}
	 System.out.println("Name:" + profile.getFirstName() + " " + profile.getLastName());      
	 System.out.println("Headline:" + profile.getHeadline());                             
	// System.out.println("API Request:" + profile.getApiStandardProfileRequest());         
	 System.out.println("Industry:" + profile.getIndustry());     
	 System.out.println("Picture:" + profile.getPictureUrl());    
	 System.out.println("Public URL:" + profile.getPublicProfileUrl()); 
	 //System.out.println("Location:" + profile.getLocation());        
	 if( profile.getLocation()!=null){
		 Location location = profile.getLocation();
		 System.out.println("Country :"+location.getCountry()+"Name :"+location.getName()+"HashCode:"+ location.hashCode());
				  if(location.getName()!=null){
				 locationName=location.getName();
				 }
	 }
	 //System.out.println("Connections:" + profile.getConnections());  
	 List personlist= new ArrayList();
	 if(profile.getConnections()!=null){
		 Connections connections=profile.getConnections();
		if(connections.getCount()!=null){
		
		 System.out.println("Connection Count :"+connections.getCount());
		}
		if( connections.getTotal()!=null){
		 System.out.println("Connection Total :"+ connections.getTotal());
			connectionTotal = String.valueOf(connections.getTotal());
		}
		 if(!connections.getPersonList().isEmpty()){
		System.out.println("=============getPersonList==================");  
		  personlist=connections.getPersonList();
		 }
		 
	 }
	 Educations edu = profile.getEducations();
	 if(profile.getEducations()!=null){
	 edu.getEducationList();
	 }
	 
	 System.out.println("Educations:" + profile.getEducations());    
	 System.out.println("Summary:" + profile.getSummary());          
	 System.out.println("Specialties:" + profile.getSpecialties());   
	 System.out.println("SiteStandardProfileRequest:" + profile.getSiteStandardProfileRequest());
	 
	 SiteStandardProfileRequest req=profile.getSiteStandardProfileRequest();
	 if(req!=null){
	 System.out.println("====== "+req.getUrl());
	 
			 Headers headers=req.getHeaders();
			 if(headers!=null){
			 System.out.println("====== "+ headers.getHttpHeaderList() +" "+headers.getTotal());
			 }
	 }
	  //ApiStandardProfileRequest apiStandardProfileRequest=profile.getApiStandardProfileRequest();    
	 //System.out.println("Header:" + apiStandardProfileRequest.getHeaders());                   
	 System.out.println("================================");  
	
	 
	 if(name!=null){
		 linkedInDTO.setName(name);
	 }
	 if(headLine!=null){
		 linkedInDTO.setHeadLine(headLine);
	 }
	 if(industry!=null){		 
		 linkedInDTO.setIndustry(industry);
	 }
	 if(profileImg!=null){
		 linkedInDTO.setProfileImg(profileImg);
	 }
	 if(pubProfielUrl!=null){
		 linkedInDTO.setPubProfielUrl(pubProfielUrl);
	 }
	 if(specialities!=null){
		 linkedInDTO.setSpecialities(specialities);
	 }
	 if(summary!=null){
		 linkedInDTO.setSummary(summary);
	 }	
	 if(locationName !=null){
		 linkedInDTO.setLocationName(locationName);
	 }
	 if(connectionTotal!=null){
		 linkedInDTO.setConnectionTotal(connectionTotal);
	 }
	 return linkedInDTO;
	  }
	public boolean updatePublicProfile(String linkedInId, String profileURL, String profileImgUrl, String profileId) throws EventPortalException{
		boolean result = false;
		
		SocialMedia socialMedia = new SocialMedia();
		
		socialMedia.setSmProfileId(profileId);
		socialMedia.setLinkedInId(linkedInId);
		socialMedia.setLinkedInProfileUrl(profileURL);
		socialMedia.setLinkedInImgUrl(profileImgUrl);
		socialMedia.setHiddenObject("LINKEDIN");
		socialMedia.setLiAllowFriendsToFollow("N");
		socialMedia.setLiAllowFriendsToPost("N");
		
		result = socialMedia.updateSocialMediaProfile(socialMedia);	
		
		return result;
	}
	
	public String getPublicProfile(LinkedInAccessToken accessToken){
		 
		String profileURL = null;
		LinkedInApiClient client = null;
		
		try{
		
			client = getLinkedInApiClient(accessToken);
			
			 Person profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
			 Person profile2 = client.getProfileById(profile.getId(), EnumSet.of(ProfileField.PUBLIC_PROFILE_URL));
			
			 profileURL = profile2.getPublicProfileUrl();
			 
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return profileURL;
	}
	
	
	public Person getProfile(LinkedInAccessToken accessToken){
		 
		String profileURL = null;
		LinkedInApiClient client = null;
		Person profile = null;
		Person profile2 = null;
		
		try{
		
			client = getLinkedInApiClient(accessToken);
			
			 profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
			profile2 = client.getProfileById(profile.getId(), EnumSet.of(ProfileField.ID, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.HEADLINE, ProfileField.INDUSTRY, ProfileField.CURRENT_STATUS, ProfileField.CURRENT_STATUS_TIMESTAMP,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.EDUCATIONS,ProfileField.PUBLIC_PROFILE_URL, ProfileField.POSITIONS, ProfileField.LOCATION, ProfileField.PICTURE_URL));
			
			 profileURL = profile2.getPublicProfileUrl();
			 
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return profile2;
	}
	
	public LinkedInDTO getLinkedInDetails(LinkedInDTO linkedInDTO) throws EventPortalException {
		ProfileDTO profileDTO = null;
		LinkedInAccessToken accessToken = null;
		 LinkedInApiClient client = null;
		 List friendsList = null;
		 int friendsCount = 0;
		 if(linkedInDTO != null){
			 
			 accessToken = (LinkedInAccessToken)linkedInDTO.getAccessToken();
			 
			 if(accessToken != null){

				 client = getLinkedInApiClient(accessToken);
				 
				 profileDTO = getUserLinkedInProfile(client);
				 friendsList = getFriendsProfile(client);
				 friendsCount = friendsCount(client);

				 linkedInDTO = new LinkedInDTO();
				 linkedInDTO.setProfileDTO(profileDTO);
				 linkedInDTO.setFriendsList(friendsList);
				 linkedInDTO.setFriendsCount(String.valueOf(friendsCount));
			 }
		 }
		
		
		return linkedInDTO;
	}

	/**
	 * @param client
	 * @return
	 */
	private List getFriendsProfile(LinkedInApiClient client) {
		List friendsList = null;
		final Set<ProfileField> connectionFields = EnumSet.of(ProfileField.ID, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.HEADLINE, ProfileField.INDUSTRY, ProfileField.CURRENT_STATUS, ProfileField.CURRENT_STATUS_TIMESTAMP,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.EDUCATIONS,ProfileField.PUBLIC_PROFILE_URL, ProfileField.POSITIONS, ProfileField.LOCATION, ProfileField.PICTURE_URL);
		Connections tryconnections = client.getConnectionsForCurrentUser(connectionFields);
		
		friendsList = getFriendsProfile(tryconnections);
		
		return friendsList;
	}
	
	private List getFriendsProfile(Connections connections){
		
		List friendsList = null;
		ProfileDTO profileDTO = null;
		
		friendsList  = new ArrayList();
		for (Person person : connections.getPersonList()) {
			
			profileDTO = getProfileData(person);
			friendsList.add(profileDTO);
		}
		
		return friendsList;
	}

	/**
	 * @param client
	 * @return
	 */
	private ProfileDTO getUserLinkedInProfile(LinkedInApiClient client) {
		
		ProfileDTO profileDTO = null;
		Person profile = null;
		
		 profile = getUserProfile(client);
		 
		 profileDTO = getProfileData(profile);
		
		return profileDTO;
	}	

	 public int friendsCount(LinkedInApiClient client) {
		 
		 int friendsCount = 0;
		 List friendsList = null;
		 final Set<ProfileField> connectionFields = EnumSet.of(ProfileField.ID, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.HEADLINE, ProfileField.INDUSTRY, ProfileField.CURRENT_STATUS, ProfileField.CURRENT_STATUS_TIMESTAMP,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.EDUCATIONS,ProfileField.PUBLIC_PROFILE_URL, ProfileField.POSITIONS, ProfileField.LOCATION, ProfileField.PICTURE_URL);
		 Connections connections = client.getConnectionsForCurrentUser(connectionFields);
		 friendsList  = new ArrayList();
		 
		 friendsList = connections.getPersonList();
		 
		 if(friendsList != null){
			 friendsCount = friendsList.size();
		 }
		 
		 return friendsCount;
	 }

	public LinkedInDTO deleteToken(LinkedInDTO linkedInDTO) throws EventPortalException {
		
		String profileId = null;

		try {

			profileId = linkedInDTO.getProfileId();
			
			Keys keys = new Keys();
			
			keys.setHiddenObject("LINKEDIN");
			keys.setProfileId(profileId);
			
			boolean retSts = keys.deleteAccessToken(keys);
			
			if(retSts){
				retSts = updatePublicProfile(null,null, null ,profileId);
				linkedInDTO = connectLinkedIn(linkedInDTO);
				
				linkedInDTO.setDeleteStatus(retSts);
				
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return linkedInDTO;
	}

	public ResultDTO postLinkedinComment(LinkedInDTO linkedInDTO){
		ResultDTO resultDTO = null;
		resultDTO = new ResultDTO();
		LinkedInApiClient client = null;
		LinkedInAccessToken accessToken = null;
		String comment = null;
		comment = linkedInDTO.getShareComment();
		boolean result = false;
		accessToken = (LinkedInAccessToken)linkedInDTO.getAccessToken();		 
		 if(accessToken != null){
			 client = getLinkedInApiClient(accessToken);
		 }
		if(comment!=null && client!=null){  
		  client.updateCurrentStatus(comment);
		  result = true;
		  
		  }
		 resultDTO.setResultStatus(result);
		 return resultDTO;
		 }
	
	/*Connections tryconnections = 

		client.getConnectionsForCurrentUser(connectionFields); 
		visionsundari (4:00:19 PM): 01.for (Person person : connections.getPersonList()) 

		final Set<ProfileField> connectionFields = EnumSet.of(ProfileField.ID, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.HEADLINE, ProfileField.INDUSTRY, ProfileField.CURRENT_STATUS, ProfileField.CURRENT_STATUS_TIMESTAMP,ProfileField.API_STANDARD_PROFILE_REQUEST, ProfileField.EDUCATIONS,ProfileField.PUBLIC_PROFILE_URL, ProfileField.POSITIONS, ProfileField.LOCATION, ProfileField.PICTURE_URL);
		Connections tryconnections = client.getConnectionsForCurrentUser(connectionFields);

		{   
		System.out.println( "PersonId :"+ person.getId());  
		System.out.println("Name:" + person.getFirstName() + " " + 

		person.getLastName());   
		System.out.println("Headline:" + person.getHeadline());   
		System.out.println("Industry:" + person.getIndustry());   
		System.out.println("Picture:" + person.getPictureUrl());   
*/
	
	
	
}  
 
   

