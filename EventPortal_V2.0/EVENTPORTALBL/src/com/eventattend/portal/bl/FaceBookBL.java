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
 * Oct 21, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.eventattend.portal.bo.Keys;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.socialmedia.util.SocialMediaKeys;
import com.google.code.facebookapi.Attachment;
import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;
import com.google.code.facebookapi.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 21, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class FaceBookBL extends BusinessLayer {

	//visiontssrv
	//private static final String API_KEY = "10511c6c70d52eaeb3b961cae9c94f8c";
	//private static final String API_SECRET = "11c61914df8ffc200c7a25263882568a";
	
	
	//EVENT PORCH
	//private static final String API_KEY = "35da82b9e7418dc5c3a10267b1c0812f";
	//private static final String API_SECRET = "00d5cce69386b945539e78a7b7000bfe";

	//CHECK PORTAL
	//private static final String API_KEY = "621c6a40f9a1d794080e10b9cf94c793";
	//private static final String API_SECRET = "fbb4d1ce3acca01efbd1364695e16ec3";

	private static final String API_KEY = SocialMediaKeys.FACEBOOK_API_KEY;
	private static final String API_SECRET = SocialMediaKeys.FACEBOOK_API_SECRET;
	
	private static final String AUTH_URL = "http://www.facebook.com/login.php?api_key="+API_KEY+"&v=1.0&req_perms=read_stream,publish_stream,offline_access,sms,email,user_location";

	
	
 EnumSet<ProfileField> fields = EnumSet.of (
	            com.google.code.facebookapi.ProfileField.NAME,
	            com.google.code.facebookapi.ProfileField.PIC,
	         com.google.code.facebookapi.ProfileField.ABOUT_ME, com.google.code.facebookapi.ProfileField.ACTIVITIES, com.google.code.facebookapi.ProfileField.AFFILIATIONS,
	         com.google.code.facebookapi.ProfileField.BIRTHDAY, com.google.code.facebookapi.ProfileField.BOOKS, com.google.code.facebookapi.ProfileField.CURRENT_LOCATION, com.google.code.facebookapi.ProfileField.FIRST_NAME
	         , com.google.code.facebookapi.ProfileField.LAST_NAME, com.google.code.facebookapi.ProfileField.HOMETOWN_LOCATION, com.google.code.facebookapi.ProfileField.INTERESTS,
	         com.google.code.facebookapi.ProfileField.IS_APP_USER, com.google.code.facebookapi.ProfileField.NAME, com.google.code.facebookapi.ProfileField.NOTES_COUNT,
	         com.google.code.facebookapi.ProfileField.STATUS, com.google.code.facebookapi.ProfileField.WALL_COUNT, com.google.code.facebookapi.ProfileField.WORK_HISTORY,  com.google.code.facebookapi.ProfileField.PROFILE_URL);

	public FaceBookDTO initialiseFaceBook(FaceBookDTO faceBookDTO) throws EventPortalException{
		
		boolean isFaceBookConnected = false;
		String authUrl = null;
		 
		String profileId = null;
		 String adminProfileId = null; 
		 
		 Object accessToken = null;
		 Object adminAccessToken = null;
		 
		if(faceBookDTO != null){
			
			profileId = faceBookDTO.getProfileId();
			accessToken = getAccessToken(profileId);
			
		     if(accessToken != null){
		    	 
		    	 //isFaceBookConnected = testFaceBook(accessToken.toString());

		    	 isFaceBookConnected = true;
		    	 
		    	 if(isFaceBookConnected){
					faceBookDTO = new FaceBookDTO();
					faceBookDTO.setAccessToken(accessToken);
				}else{
					faceBookDTO = new FaceBookDTO();
					faceBookDTO.setAuthUrl(AUTH_URL);
				}
			}else{
				faceBookDTO = new FaceBookDTO();
				faceBookDTO.setAuthUrl(AUTH_URL);
			}
		     
		     if(!profileId.equals(adminProfileId)){
					adminAccessToken = getAccessToken(adminProfileId);
					faceBookDTO.setAdminAccessToken(adminAccessToken);
				}else{
					faceBookDTO.setAdminAccessToken(accessToken);
				}
		     
			
		}else{
			faceBookDTO = new FaceBookDTO();
			faceBookDTO.setAuthUrl(AUTH_URL);
		}
		
		return faceBookDTO;
		
	}
	public FaceBookDTO publicProfile(FaceBookDTO faceBookDTO){
		FacebookJsonRestClient userClient = null;
		userClient = getUserClient((String) faceBookDTO.getAccessToken());
		EnumSet<ProfileField> fields1 = EnumSet.of (
				com.google.code.facebookapi.ProfileField.UID,
	            com.google.code.facebookapi.ProfileField.NAME,
	            com.google.code.facebookapi.ProfileField.PIC,	   
	            com.google.code.facebookapi.ProfileField.ACTIVITIES,
	            com.google.code.facebookapi.ProfileField.AFFILIATIONS,
	            com.google.code.facebookapi.ProfileField.BIRTHDAY,
	            com.google.code.facebookapi.ProfileField.BOOKS,
	            com.google.code.facebookapi.ProfileField.EDUCATION_HISTORY,
	            com.google.code.facebookapi.ProfileField.EMAIL_HASHES,
	            com.google.code.facebookapi.ProfileField.HOMETOWN_LOCATION,
	            com.google.code.facebookapi.ProfileField.HS_INFO,
	            com.google.code.facebookapi.ProfileField.INTERESTS,
	            com.google.code.facebookapi.ProfileField.WALL_COUNT,
	            com.google.code.facebookapi.ProfileField.LOCALE,
	            com.google.code.facebookapi.ProfileField.PIC_BIG,
	            com.google.code.facebookapi.ProfileField.PIC_SMALL_WITH_LOGO,
	            com.google.code.facebookapi.ProfileField.PROFILE_URL,
	            com.google.code.facebookapi.ProfileField.QUOTES,
	            com.google.code.facebookapi.ProfileField.RELATIONSHIP_STATUS,
	            com.google.code.facebookapi.ProfileField.SEX,
	            com.google.code.facebookapi.ProfileField.STATUS,	      
	          com.google.code.facebookapi.ProfileField.CURRENT_LOCATION, 
	          com.google.code.facebookapi.ProfileField.FIRST_NAME, 
	          com.google.code.facebookapi.ProfileField.LAST_NAME, 
	          com.google.code.facebookapi.ProfileField.HOMETOWN_LOCATION,
	          com.google.code.facebookapi.ProfileField.ABOUT_ME,
	          com.google.code.facebookapi.ProfileField.WORK_HISTORY,
	          com.google.code.facebookapi.ProfileField.PROFILE_URL,
	          com.google.code.facebookapi.ProfileField.MOVIES,
	          com.google.code.facebookapi.ProfileField.EMAIL_HASHES);
		long facebookUserID=Long.parseLong(faceBookDTO.getProfileId());
		JSONArray jsonArray = null;
		List currentUser= new ArrayList();
		currentUser.add(facebookUserID);
	
		try {
			faceBookDTO = new FaceBookDTO();
			jsonArray =userClient.users_getInfo(currentUser, fields1);
			 try {
				 
					JSONObject obj = jsonArray.getJSONObject(0);
					faceBookDTO = getUserDetails(obj,faceBookDTO);
					
				} catch (JSONException e) {				
					e.printStackTrace();
				}
				
		} catch (FacebookException e) {		
			e.printStackTrace();
		}
	
	return faceBookDTO;
	}
	
	public FaceBookDTO checkAlreadyFriend(FaceBookDTO faceBookDTO){
		FacebookJsonRestClient userClient = null;	
		JSONArray jsonArray = null;
		userClient = getUserClient((String) faceBookDTO.getAccessToken());
		String friendId=faceBookDTO.getProfileId();
		System.out.println("Facebook friendId>>> "+friendId);
		//	faceBookDTO = new FaceBookDTO();
			faceBookDTO.setProfileId(friendId);
			try {
				jsonArray = userClient.friends_get();
			} catch (FacebookException e1) {				
				e1.printStackTrace();
			}

			
			for (int i=0; i<jsonArray.length(); i++) {
				
				try {					
					
					Object uId = (Object)jsonArray.get(i);
					System.out.println("Facebook>>> "+uId);
					if((uId.toString()).equals(friendId)){
						System.out.println(friendId + " FB Already a Friend !");
						faceBookDTO.setAlreadyFriends(true);
					}
		            System.out.println(">> "+uId);
					
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
				
			
			}
		return faceBookDTO;
	}
	public FaceBookDTO inviteFriend(FaceBookDTO faceBookDTO){
		FacebookJsonRestClient userClient = null;	
		boolean inviteStatus = false;	
		userClient = getUserClient((String) faceBookDTO.getAccessToken());
		String friendId=faceBookDTO.getProfileId();
		System.out.println("Facebook inviteFriend >>> "+friendId);
	
        try {
        	JSONObject jsonObject=userClient.notifications_get();    
        	
        	
        	JSONArray jsonArray=jsonObject.getJSONArray("friend_requests");
         	for (int i=0; i<jsonArray.length(); i++) {				
				try {					
					Object uId = (Object)jsonArray.get(i);	
					if(uId.toString().equals(friendId)){
						System.out.println("Has Already invite you & Accept request in FB >>> "+friendId);
						inviteStatus = true;
					}else{
						inviteStatus = false;
					}
						
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
		
		}
        }catch (JSONException e) {
			
			e.printStackTrace();
		} catch (FacebookException e) {
			inviteStatus = false;
			e.printStackTrace();
		}
	
		faceBookDTO.setFriendAlreadyReqYou(inviteStatus);
		return faceBookDTO;
	}
	/*public FaceBookDTO inviteFriend(FaceBookDTO faceBookDTO){
		FacebookJsonRestClient userClient = null;	
		boolean inviteStatus = false;	
		userClient = getUserClient((String) faceBookDTO.getAccessToken());
		String friendId=faceBookDTO.getProfileId();
		System.out.println("Facebook inviteFriend >>> "+friendId);
		String emailHash = generateEmailHash("mani@maniempire.com");
		System.out.println("emailHash >>> "+emailHash);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email_hashes",emailHash );
		//map.put("account_url ", "http://www.facebook.com/profile.php?id="+friendId);
		map.put("account_id", friendId);
	    Collection<Map<String, String>> accounts = new ArrayList<Map<String, String>>();
        accounts.add(map);
        try {
        
			userClient.connect_registerUsers(accounts);
			inviteStatus = true;
		} catch (FacebookException e) {
			inviteStatus = false;
			e.printStackTrace();
		}
	
		faceBookDTO.setInviteFriend(inviteStatus);
		return faceBookDTO;
	}*/
	  public static String generateEmailHash( String email ) { 
		  email = email.trim().toLowerCase();               
		  CRC32 crc = new CRC32();               
		  crc.update( email.getBytes() );              
		  String md5 = MD5( email );           
		  return crc.getValue() + "_" + md5;        
		  }  
	
	private static String MD5(String email) {
		 String md5val = "";
		 String[] args={email};
	        MessageDigest algorithm = null;

	        try
	        {
	            algorithm = MessageDigest.getInstance("MD5");
	        }
	        catch (NoSuchAlgorithmException nsae)
	        {
	            System.out.println("Cannot find digest algorithm");
	            System.exit(1);
	        }
	        for (String arg : args)
	        {
	            byte[] defaultBytes = arg.getBytes();
	            algorithm.reset();
	            algorithm.update(defaultBytes);
	            byte messageDigest[] = algorithm.digest();
	            StringBuffer hexString = new StringBuffer();

	            for (int i = 0; i < messageDigest.length; i++)
	            {
	                String hex = Integer.toHexString(0xFF & messageDigest[i]);
	                if (hex.length() == 1)
	                {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            md5val = hexString.toString();
	            System.out.println("MD5 (" + arg + ") = " + md5val);
	        }
	   

		return md5val;
	}
	private List personFBFriendsProfile(FacebookJsonRestClient userClient,long userId) {
		 JSONArray jsonArray = null;
		 List friendsList = null;
		 List friendsIds = null;		
		 friendsIds = new ArrayList();
		 JSONObject jsonObject = null;
		

		try {		
			
			jsonArray = userClient.friends_get();

			
			for (int i=0; i<jsonArray.length(); i++) {
				
				try {
					
					
					Object uId = (Object)jsonArray.get(i);
					
					userId = Long.parseLong(uId.toString());
				
					friendsIds.add(userId);
					
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
				
			
			}
			
			jsonArray = userClient.users_getInfo(friendsIds, fields);
			
			friendsList = personFBFriendsList(jsonArray);
		
		} catch (FacebookException e) {	
			e.printStackTrace();
		}
		
		
		return friendsList;
	}
	
public List personFBFriendsList(JSONArray jsonArray){
		
		List friendsList = null;
		JSONObject jsonObject = null;
		friendsList  = new ArrayList();
		
		for (int i=0; i<jsonArray.length(); i++) {
			
			try {
				jsonObject = jsonArray.getJSONObject(i);
			//	 getUserDetails(jsonObject);
				
			} catch (JSONException e) {		
				e.printStackTrace();
			}
			
		}
		return friendsList;
		
	}
	private FaceBookDTO getUserDetails(JSONObject jsonObject,FaceBookDTO faceBookDTO){
		JSONArray jsonArray = null;
		JSONObject jsonObjectLocation = null;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			
			if(jsonObject != null){				
				System.out.println("UId=> "+jsonObject.getString("uid"));
				if(jsonObject.getString("profile_url")!=null){
					faceBookDTO.setProfileUrl(jsonObject.getString("profile_url"));
				System.out.println("PROFILE_URL=> "+jsonObject.getString("profile_url"));
				}
				if(jsonObject.getString("name")!=null){
				System.out.println("Name=> "+jsonObject.getString("name"));
				faceBookDTO.setName(jsonObject.getString("name"));
				}
				if(jsonObject.getString("first_name")!=null){
				System.out.println("First Name=> "+jsonObject.getString("first_name"));
				}
				if(jsonObject.getString("last_name")!=null){
				System.out.println("Last Name=> "+jsonObject.getString("last_name"));
				}
				if(jsonObject.getString("birthday")!=null && (!jsonObject.getString("birthday").equals("null"))){				
				System.out.println("BIRTHDAY=> "+jsonObject.getString("birthday"));
				}
				
				if(jsonObject.getString("about_me")!=null &&  (!jsonObject.getString("about_me").equals("null"))){
				System.out.println("About Me=> "+jsonObject.getString("about_me"));
				}
				
				if(jsonObject.getString("movies")!=null &&  (!jsonObject.getString("movies").equals("null"))){
					System.out.println("MOVIES=> "+jsonObject.getString("movies"));
					}
				if(jsonObject.getString("status")!=null &&  (!jsonObject.getString("status").equals("null"))){
				System.out.println("Status=> "+jsonObject.getString("status"));
				}
				if(jsonObject.getString("relationship_status")!=null){
				System.out.println("RELATIONSHIP_STATUS=> "+jsonObject.getString("relationship_status"));
				}
				if(jsonObject.getString("locale")!=null){
				System.out.println("LOCALE=> "+jsonObject.getString("locale"));
				}
				if(jsonObject.getString("wall_count")!=null){
				System.out.println("WALL_COUNT=> "+jsonObject.getString("wall_count"));
				}
				if(jsonObject.getString("quotes")!=null){
				System.out.println("QUOTES=> "+jsonObject.getString("quotes"));
				}
				if(jsonObject.getString("pic")!=null){
				System.out.println("PIC=> "+jsonObject.getString("pic"));
				faceBookDTO.setProfileImg(jsonObject.getString("pic"));
				}
				if(jsonObject.getString("pic_big")!=null){
				System.out.println("PIC_BIG=> "+jsonObject.getString("pic_big"));
				faceBookDTO.setProfileBigImg(jsonObject.getString("pic_big"));
				}
				if(jsonObject.getString("activities")!=null){
				System.out.println("ACTIVITIES=> "+jsonObject.getString("activities"));
				}
				if(jsonObject.getString("hometown_location")!=null){
				System.out.println("HOMETOWN_LOCATION=> "+jsonObject.getString("hometown_location"));
				}
				
				if(jsonObject.getString("hs_info")!=null){
				System.out.println("hs_info=> "+jsonObject.getString("hs_info"));
				}
				if(jsonObject.getString("education_history")!=null){
					String edu=jsonObject.getString("education_history");
					Collection coll=new ArrayList();
					JSONObject myString = new JSONObject().put(edu, coll);
					
				System.out.println("EDUCATION_HISTORY=> "+jsonObject.getString("education_history"));
	
				}
				if(jsonObject.getString("work_history")!=null){
				System.out.println("WORK_HISTORY=> "+jsonObject.getString("work_history"));
				}
				if(jsonObject.getString("affiliations")!=null){
				System.out.println("WORK_HISTORY=> "+jsonObject.getString("affiliations"));
				}
				if(jsonObject.getString("email_hashes")!=null){
				System.out.println("EMAIL_HASHES=> "+jsonObject.getString("email_hashes"));
				}
				if(jsonObject.getString("interests")!=null){
				System.out.println("INTERESTS=> "+jsonObject.getString("interests"));
				}
				
				
	
				if(jsonObject.getString("current_location") != null){
					if(!jsonObject.getString("current_location").equals("null")){
						jsonObjectLocation = jsonObject.getJSONObject("current_location");
			//			System.out.println("ObjectLocation=> "+jsonObjectLocation);
						
					}else{
					
					}
				}else{
					
				}
			
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return faceBookDTO;
	}

public boolean testFaceBook(String accessToken ){
		
		boolean result = false;
		FacebookJsonRestClient userClient = null;
		 JSONArray jsonArray = null;
		 
		 ProfileDTO profileDTO = null;
		try {
			 
			userClient = getUserClient(accessToken);
			
			profileDTO = getUserProfile(userClient);
			System.out.println("Welcome "+profileDTO.getFirstName()+" "+profileDTO.getLastName()+" to FaceBook! You are successfully connected.");
			result = true;
			
		} catch (Exception e1) {
			System.out.println("Unable to connect to FaceBook..!");
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public FaceBookDTO getFaceBookDetails(FaceBookDTO faceBookDTO) throws EventPortalException {
		FacebookJsonRestClient userClient = null;
		ProfileDTO profileDTO = null;
		 List friendsList = null;
		 String accessToken = null;
		 int friendsCount = 0;
		 if(faceBookDTO != null){
				
				accessToken = (String)faceBookDTO.getAccessToken();
				
				if(accessToken != null){
					userClient = getUserClient(accessToken);
					
					profileDTO = getUserProfile(userClient);
					
					friendsList = getFriendsProfile(userClient);
					
					friendsCount = friendsCount(userClient);
					
					faceBookDTO = new FaceBookDTO();
					faceBookDTO.setProfileDTO(profileDTO);
					faceBookDTO.setFriendsList(friendsList);
					faceBookDTO.setFriendsCount(String.valueOf(friendsCount));

				}
		 }

		 return faceBookDTO;
		
	}
	
	/**
	 * @param faceBookDTO
	 * @return
	 */
	public FaceBookDTO addToken(FaceBookDTO faceBookDTO) throws EventPortalException{
		
		 boolean addStatus = false;
		 String profileId = null;
		 String faceBookId = null;
		 Object authToken = null;
		 Object accessToken = null;
		 String profileURL = null;
		 FacebookJsonRestClient userClient = null;
		if(faceBookDTO != null){
			
			profileId = faceBookDTO.getProfileId();
			authToken = faceBookDTO.getAuthToken();
			
			accessToken = getUserSession((String)authToken);
			
			if(accessToken != null){
				Keys keys = new Keys();
				   
				   keys.setHiddenObject("FACEBOOK");
				   keys.setFaceBookAccessToken(accessToken);
				   keys.setProfileId(profileId);
				   
				   addStatus = keys.saveAccessToken(keys);
				   
				   if(addStatus){
					   //accessToken = getAccessToken(profileId);
					  
					   //faceBookId = getProfileId(accessToken.toString());
					   
					   profileURL = getPublicProfile(accessToken.toString());
					    
					   userClient = getUserClient((String) accessToken);
						
					   ProfileDTO profileDTO =getUserProfile(userClient);
					   
					   addStatus = updatePublicProfile(profileDTO.getProfileId(),profileURL,profileDTO.getFaceBookImgUrl(),profileId);
					   
					   faceBookDTO = new FaceBookDTO();
					   faceBookDTO.setAccessToken(accessToken);			
					   faceBookDTO.setAddStatus(addStatus);	
				   }
			}
			
		}
		
		return faceBookDTO;
	}
	
	public String getUserSession(String authToken){
		
		 String authSession = null;
		 FacebookJsonRestClient userClient = null;
		 
		 userClient = new FacebookJsonRestClient(API_KEY, API_SECRET); 
		 try {
			authSession = userClient.auth_getSession(authToken);
			
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return authSession;
	}
	
	
	public String getAccessToken(String profileId) throws EventPortalException{
		Collection accessTokenCollection = null;
		String accessToken = null;
		Keys keys = null;
		
		keys = new Keys();

		keys.setProfileId(profileId);
		accessTokenCollection = keys.getAccessToken(keys);
	
		Iterator iter1 = accessTokenCollection.iterator();
		
		if(iter1 != null){
			while(iter1.hasNext()){		
				keys = (Keys)iter1.next();
				accessToken = (String)keys.getFaceBookAccessToken();
			}	
		}
		
		return accessToken;
	}
	
	
	
  public int friendsCount(FacebookJsonRestClient userClient ){
		
		int friendsCount = 0;
		
   	  JSONArray arrayObj;
	try {

		arrayObj = (JSONArray)userClient.friends_get();
	
		friendsCount = arrayObj.length();
		 
	} catch (FacebookException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 		 
	return friendsCount;
	
	}
	

	
	/**
	 * @param userClient
	 * @return
	 * @throws EventPortalException 
	 */
	private ProfileDTO getUserProfile(FacebookJsonRestClient userClient) throws EventPortalException {
		JSONArray jsonArray = null;
		ProfileDTO profileDTO = null;
		 List currentUser= new ArrayList();
		EnumSet<ProfileField> fields1 = EnumSet.of (
				com.google.code.facebookapi.ProfileField.UID,
	            com.google.code.facebookapi.ProfileField.NAME,
	            com.google.code.facebookapi.ProfileField.PIC,	        
	          com.google.code.facebookapi.ProfileField.CURRENT_LOCATION, 
	          com.google.code.facebookapi.ProfileField.FIRST_NAME, 
	          com.google.code.facebookapi.ProfileField.LAST_NAME, 
	          com.google.code.facebookapi.ProfileField.HOMETOWN_LOCATION,
	          com.google.code.facebookapi.ProfileField.PROFILE_URL);
		try {
			
			 Long  facebookUserID = userClient.users_getLoggedInUser();
       		 currentUser.add(facebookUserID);
       		 
			jsonArray = userClient.users_getInfo(currentUser, fields1);
			
			 try {
				 
				JSONObject obj = jsonArray.getJSONObject(0);
				profileDTO = getUserData(obj);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FacebookException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new EventPortalException("Facebook Session Invalid");
		
		}
		
		
		return profileDTO;
	}

	
	private ProfileDTO getUserData(JSONObject jsonObject){
		JSONArray jsonArray = null;
		JSONObject jsonObjectLocation = null;
		ProfileDTO profileDTO = null;
		
		try {
			if(jsonObject != null){
				
				profileDTO = new ProfileDTO();
				
				profileDTO.setProfileId(jsonObject.getString("uid"));				
				profileDTO.setFirstName(jsonObject.getString("first_name"));
				profileDTO.setLastName(jsonObject.getString("last_name"));
				profileDTO.setFaceBookImgUrl(jsonObject.getString("pic"));
				if(jsonObject.getString("current_location") != null){
					if(!jsonObject.getString("current_location").equals("null")){
						jsonObjectLocation = jsonObject.getJSONObject("current_location");
						//profileDTO.setLocation(getLocation(jsonObjectLocation));
					}else{
						//profileDTO.setLocation("");
					}
				}else{
					//profileDTO.setLocation("");
				}
				//profileDTO.setFaceBookId(jsonObject.getString("profile_url"));
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profileDTO;
	}	
	
	public FacebookJsonRestClient getUserClient(String authSession){
		
		FacebookJsonRestClient userClient = null;
		
		userClient = new FacebookJsonRestClient(API_KEY, API_SECRET, authSession); 
		
		return userClient;
	}
	
	public String getLocation(JSONObject jsonObject){
		
		String location = "";
		String city = "";
		String state = "";
		String country = "";
		
		try {
			
			//JSONObject obj = jsonObject.getJSONObject(0);
			
			city = jsonObject.getString("city");
			state = jsonObject.getString("state");
			country = jsonObject.getString("country");
		
			location = city+", "+state+", "+country;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return location;
	}
	
	
	
	public boolean updatePublicProfile(String faceBookId, String profileURL,String faceBookImgUrl,String profileId) throws EventPortalException{
		boolean result = false;
		
		SocialMedia socialMedia = new SocialMedia();
		
		socialMedia.setSmProfileId(profileId);
		socialMedia.setFaceBookId(faceBookId);
		socialMedia.setFaceBookProfileUrl(profileURL);
		socialMedia.setFaceBookImgUrl(faceBookImgUrl);
		socialMedia.setHiddenObject("FACEBOOK");
		socialMedia.setFbAllowFriendsToConnect("N");
		socialMedia.setFbAllowFriendsToPost("N");
		
		result = socialMedia.updateSocialMediaProfile(socialMedia);	
		
		return result;
	}
	
	public String getProfileId(String accessToken){
		String profileId = null;
		
		FacebookJsonRestClient userClient = null;
		 JSONArray jsonArray = null;
		 
		 ProfileDTO profileDTO = null;
		try {
			 
			userClient = getUserClient(accessToken);
			
			profileDTO = getUserProfile(userClient);
			
			profileId = profileDTO.getProfileId();
			
		} catch (Exception e1) {
			System.out.println("Unable to connect to FaceBook..!");
			e1.printStackTrace();
		}
		
		return profileId;
		
	}
	
	
	public String getPublicProfile(String accessToken){
		String profileURL = null;
		
		FacebookJsonRestClient userClient = null;
		 JSONArray jsonArray = null;
		 
		 ProfileDTO profileDTO = null;
		try {
			 
			userClient = getUserClient(accessToken);
			
			profileDTO = getUserProfile(userClient);
			
			//profileURL = profileDTO.getFaceBookId();
	
			if(profileURL == null){
				//profileURL = "http://www.facebook.com/"+profileDTO.getProfileId();
				
				profileURL = "http://www.facebook.com/people/"+profileDTO.getFirstName()+"-"+profileDTO.getLastName()+"/"+profileDTO.getProfileId();
				//http://www.facebook.com/people/Manimaran-Malaichamy/100000858117720
		
			}
			
			
		} catch (Exception e1) {
			System.out.println("Unable to connect to FaceBook..!");
			e1.printStackTrace();
		}
		
		return profileURL;
		
	}

	/**
	 * @param faceBookDTO
	 * @return
	 */
	public ResultDTO shareMsgInFaceBook(FaceBookDTO faceBookDTO) {
		ResultDTO resultDTO = null;
		boolean resultSts = false;
		String shareMessage = null;
		boolean isShareMsg = false;
		Object accessToken = null;
		resultDTO = new ResultDTO();
		FacebookJsonRestClient userClient = null;
		
		if(faceBookDTO != null){
			isShareMsg = faceBookDTO.isShareMsg();
			if(isShareMsg){
				shareMessage = faceBookDTO.getShareComment();
				accessToken = faceBookDTO.getAccessToken();
				
				if(accessToken != null){
					
					userClient = getUserClient(accessToken.toString());
					resultSts = shareMsg(userClient, shareMessage, null, null);
				
					
				}
			}
		}
		resultDTO.setResultStatus(resultSts);
		return resultDTO;
	}
	
	
	public boolean shareMsg(FacebookJsonRestClient userClient, String message, Attachment attachment, Collection actionLinks){
		boolean resultSts = false;
		long userId = 0;

		 long targetId = 0;
		try {
			userId = userClient.users_getLoggedInUser();
			targetId = userId;
			userClient.stream_publish(message, attachment, actionLinks, targetId, userId);
			resultSts = true;
		
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSts;
	}
	

	/**
	 * @param userClient
	 * @return
	 */
	private List getFriendsProfile(FacebookJsonRestClient userClient) {
		 JSONArray jsonArray = null;
		 List friendsList = null;
		 List friendsIds = null;
		
		 FaceBookDTO faceBookDTO = null;
		 friendsIds = new ArrayList();
		 JSONObject jsonObject = null;
		long userId = 0;
 
		try {
			userId = userClient.users_getLoggedInUser();
			
			jsonArray = userClient.friends_get(userId);

			
			for (int i=0; i<jsonArray.length(); i++) {
				
				try {
					
					
					Object uId = (Object)jsonArray.get(i);
					
					userId = Long.parseLong(uId.toString());
				
					friendsIds.add(userId);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
			
			jsonArray = userClient.users_getInfo(friendsIds, fields);
			
			friendsList = getFriendsList(jsonArray);
		
		} catch (FacebookException e) {	
			e.printStackTrace();
		}
		
		
		return friendsList;
	}
	
	

	
	public List getFriendsList(JSONArray jsonArray){
		
		List friendsList = null;
		JSONObject jsonObject = null;
		ProfileDTO profileDTO = null;
		
		friendsList  = new ArrayList();
		
		for (int i=0; i<jsonArray.length(); i++) {
			
			try {
				jsonObject = jsonArray.getJSONObject(i);
				 profileDTO = getUserData(jsonObject);
				 friendsList.add(profileDTO);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return friendsList;
		
	}
	
	//Unused Methods
	

	
	public FaceBookDTO initialiseFaceBook1(FaceBookDTO faceBookDTO) throws EventPortalException{
	
		FacebookJsonRestClient frc = null;
		
		frc = new FacebookJsonRestClient(API_KEY, API_SECRET);
		
		String token = null;
		String authUrl = null;
		
		try {
			
			token = frc.auth_createToken();
			
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		authUrl = "http://www.facebook.com/login.php?api_key=" + API_KEY + "&v=1.0" 
		+ "&auth_token=" + token;
		
		authUrl = "http://www.facebook.com/login.php?api_key=" +
				API_KEY+
				"&connect_display=popup" +
				"&v=1.0" +
				"&next=views/faceBook_connect.jsf" +
				"&cancel_url=http://www.facebook.com/connect/login_failure.html" +
				"&fbconnect=true" +
				"&return_session=true" +
				"&session_key_only=true" +
				"&req_perms=read_stream,publish_stream,offline_access,sms,email,user_location";
		
		faceBookDTO = new FaceBookDTO();
		
		faceBookDTO.setAuthUrl(authUrl);
		
		return faceBookDTO;
    }
	

	public boolean userFriends(String authSession){
		
		boolean resultSts = false;
		 FacebookJsonRestClient userClient = null;
		
		 userClient = new FacebookJsonRestClient(API_KEY, API_SECRET,authSession); 
   	  JSONArray arrayObj;
	try {

		arrayObj = (JSONArray)userClient.friends_get();
	
		 System.out.println(arrayObj);
		 resultSts = true;
	} catch (FacebookException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 		 
	return resultSts;
	
	}
	

	/**
	 * @param faceBookDTO
	 * @return
	 * @throws EventPortalException 
	 */
	
	public ProfileDTO getUserDetails(FaceBookDTO faceBookDTO) throws EventPortalException {
		
		FacebookJsonRestClient userClient = null;
		String accessToken = null;
		ProfileDTO profileDTO = null;
		
		if(faceBookDTO != null){
			
			accessToken = (String)faceBookDTO.getAccessToken();
			
			if(accessToken != null){
				
				userClient = getUserClient(accessToken);
				
				profileDTO = getUserProfile(userClient);
				//faceBookDTO = new FaceBookDTO();
				//faceBookDTO.setProfileDTO(profileDTO);
			}
			
		}
		
		return profileDTO;
	}
	
	public List getFriendsProfileList(FaceBookDTO faceBookDTO){
		
		Object accessToken = null;
		FacebookJsonRestClient userClient = null;
		List friendsList = null;
		
		if(faceBookDTO != null){
			
				accessToken = faceBookDTO.getAccessToken();
				
				if(accessToken != null){
					
					userClient = getUserClient(accessToken.toString());
					friendsList = getFriendsProfile(userClient);
					
				}
			}
		
		return friendsList;
	}

	public FaceBookDTO deleteToken(FaceBookDTO faceBookDTO) throws EventPortalException {
		
		String profileId = null;
		
		try {

			profileId = faceBookDTO.getProfileId();
			
			Keys keys = new Keys();
			
			keys.setHiddenObject("FACEBOOK");
			keys.setProfileId(profileId);
			
			boolean retSts = keys.deleteAccessToken(keys);
			
			if(retSts){	
				retSts = updatePublicProfile(null,null, null ,profileId);
				faceBookDTO.setAuthUrl(AUTH_URL);
				faceBookDTO.setDeleteStatus(retSts);			
			}
			
		
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		
		return faceBookDTO;
	}
	
	
}
