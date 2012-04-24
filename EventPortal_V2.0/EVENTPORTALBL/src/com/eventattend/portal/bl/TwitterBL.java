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
package com.eventattend.portal.bl;

import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

import com.eventattend.portal.bo.Keys;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.common.util.AppConfigXMLInfo;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.LoginDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.TwitterDTO;
import com.eventattend.portal.exceptions.BaseAppException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.TwitterForbiddenException;
import com.eventattend.portal.exceptions.TwitterRateLimitException;
import com.eventattend.portal.exceptions.TwitterServiceUnavailableException;
import com.eventattend.portal.exceptions.TwitterUnauthorizedException;
import com.eventattend.portal.socialmedia.util.SocialMediaKeys;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Location;

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
public class TwitterBL implements AppConfigXMLInfo {
	
	 Twitter twitter = null;

	private static final String CONSUMER_KEY = SocialMediaKeys.TWITTER_API_KEY;
	private static final String CONSUMER_SECRET = SocialMediaKeys.TWITTER_API_SECRET;

	 
	 public TwitterBL(){
     twitter = new Twitter();
	 twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
   	}
	
	public TwitterDTO initTwitter(TwitterDTO twitterDTO) throws EventPortalException{

		//TwitterDTO twitterDTO = new TwitterDTO();
		boolean isTwitterConnected = false;
		RequestToken requestToken;
		String tokenSecret = null;
		String token = null;
		String authUrl = null;
		AccessToken adminAccessToken = null;
		AccessToken userAccessToken = null;
		
		try {

			System.out.println("Initialising Social Media ==>Twitter");
			
			adminAccessToken = getAccessToken(twitterDTO.getAdminProfileId());

			userAccessToken = getAccessToken(twitterDTO.getProfileId());
			
			twitterDTO = new TwitterDTO();
			
			if(adminAccessToken != null){
				
				//isTwitterConnected = testTwitter(adminAccessToken);
				isTwitterConnected = true;
				
				if(isTwitterConnected){
					twitterDTO.setAdminAccessToken(adminAccessToken);
				}else{
					adminAccessToken = null;
				}
			}
			
			if(userAccessToken != null){
				//isTwitterConnected = testTwitter(userAccessToken);
				isTwitterConnected = true;
				
				if(isTwitterConnected){
					twitterDTO.setAccessToken(userAccessToken);
				}else{
					userAccessToken = null;
				}
			}
			
			if(adminAccessToken == null || userAccessToken == null){
				
				twitter = new Twitter();
				twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
				requestToken = twitter.getOAuthRequestToken();

				token = requestToken.getToken();
				tokenSecret = requestToken.getTokenSecret();
				authUrl = requestToken.getAuthorizationURL();

				System.out.println("Request token: " + token);
				System.out.println("Token secret: " + tokenSecret);
				System.out.println("authUrl: " + authUrl);

				twitterDTO.setToken(token);
				twitterDTO.setTokenSecret(tokenSecret);
				twitterDTO.setAuthUrl(authUrl);
			}
			
		} catch (TwitterException e) {

			processTwitterException(e);	
			
		}
		

		return twitterDTO;
	}
	
	public String initAuthUrl() throws BaseAppException{
		
		RequestToken requestToken = null;
		
		String authUrl = null;
		
		
		try {			
			
			requestToken = twitter.getOAuthRequestToken();
			
		} catch (TwitterException e) {
			
			processTwitterException(e);	
		}

		authUrl = requestToken.getAuthorizationURL();
		
		return authUrl;
	}
	
	
	public TwitterDTO initTwitter() throws BaseAppException{
		TwitterDTO twitterDTO = null;
		RequestToken requestToken = null;
		
		try {			
			
			twitterDTO = new TwitterDTO();
			
			requestToken = twitter.getOAuthRequestToken();
			
			twitterDTO.setToken(requestToken.getToken());
			twitterDTO.setTokenSecret(requestToken.getTokenSecret());
			twitterDTO.setAuthUrl(requestToken.getAuthorizationURL());
			
		} catch (TwitterException e) {
			
			processTwitterException(e);	
		}
		
		return twitterDTO;
	}
	
	 public boolean testTwitter(AccessToken accessToken) throws BaseAppException	{
			boolean result = false;
		  User user = null;
		try {
			 twitter.setOAuthAccessToken(accessToken);
			 user = twitter.verifyCredentials();
			 System.out.println("Welcome "+user.getName()+" to Twitter! You are successfully connected.");
			 result = true;
			   
			  } catch (TwitterException e) {
				  processTwitterException(e);	
			   System.out.println("Unable to connect to Twitter..!");
			  }
		
		return result;
		
		}
	
	public AccessToken getAccessToken(String profileId) throws EventPortalException{
		Collection accessTokenCollection = null;
		AccessToken accessToken = null;
		Keys keys = null;
		
		keys = new Keys();
		
		keys.setProfileId(profileId);
		accessTokenCollection = keys.getTwitterAccessToken(keys);
	
		Iterator iter1 = accessTokenCollection.iterator();
		if(iter1 != null){
			while(iter1.hasNext()){		
				keys = (Keys)iter1.next();
				accessToken = (AccessToken)keys.getTwitterAccessToken();
			}	
		}
		return accessToken;
	}
	 
	/**
	 * @param twitterDTO
	 * @return
	 * @throws EventPortalException 
	 */
	public TwitterDTO addToken(TwitterDTO twitterDTO) throws EventPortalException {
		
		AccessToken accessToken = null; 	
		String tokenSecret = null;
		String token = null;
		String profileId = null;
		String profileURL = null;
		String profileImgURL = null;
		String twitterId = null;

		try {
			
			token = twitterDTO.getToken();
			tokenSecret = twitterDTO.getTokenSecret();
			
			accessToken = twitter.getOAuthAccessToken(token,tokenSecret);
			profileId = twitterDTO.getProfileId();
			
			Keys keys = new Keys();
			
			keys.setHiddenObject("TWITTER");
			keys.setTwitterAccessToken(accessToken);
			keys.setProfileId(profileId);
			boolean retSts = keys.saveTwitterAccessToken(keys);
			
			if(retSts){
				//accessToken = getAccessToken(profileId);
				
				twitterId = getTwitterId(accessToken);
				
				//profileURL = getPublicProfile(accessToken);
				
				profileURL = "http://twitter.com/intent/user?screen_name="+twitterId;
				
				profileImgURL = profileImageURL(accessToken);
				
				retSts = updatePublicProfile(twitterId, profileImgURL,profileURL,profileId);			
				
				twitterDTO.setAccessToken(accessToken);
						
				//twitterDTO = getProfileImageURL(accessToken);
				
				twitterDTO.setAddStatus(retSts);
				
				
			}
			
		
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			processTwitterException(e);	
		}
		
		return twitterDTO;
	}

	  public List searchTweets(String msg,AccessToken accessToken,String userTimeZone,TwitterDTO twitterDTO) throws BaseAppException{
		  
		  twitter.setOAuthAccessToken(accessToken);
		  List twitterSearchList = null;
			DateUtility dateUtility = new DateUtility();
		   twitterSearchList =new ArrayList();
		  // TwitterDTO twitterDTO = null;		
		   String tweetContect =null;
		   String lang = null;
		   Query query = null;		
		    QueryResult result = null;
			try {
				//query.setSince("2010-10-09"); 
				if(twitterDTO.getEventTags()!=null){
					String[] eventTags=twitterDTO.getEventTags();
					for(int i=0;i<eventTags.length;i++){
						query =new Query(eventTags[i]);
						result = twitter.search(query);
						int tweetBreak = 0;
						if(APP_MODE.equalsIgnoreCase("DEVELOPMENT")){
							tweetBreak = 1;
						}else{
							tweetBreak = 5;
						}
			
			int initTweetCount = 0;
		    for (Tweet tweet : result.getTweets()) {
		    
		    	twitterDTO = new TwitterDTO();
		        User user =null;
		        lang=tweet.getIsoLanguageCode();
		        
		        /*To include RichContent to the Tweet Created Time*/
		        Date dt =tweet.getCreatedAt();
		        /*To convert the Tweeted date user Time Zone*/
		        TimeZone userTimeZ = TimeZone.getTimeZone(userTimeZone);		
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        df.setTimeZone(userTimeZ);
		        String today = df.format(dt);     
		        String createdTime= today;
		        String tweetCreatedAt = dateUtility.sessionCommentTimeCheck(createdTime,userTimeZone);
		        //System.out.println(initTweetCount +" "+tweet.getIsoLanguageCode()+" "+tweet.getCreatedAt()+" "+tweet.getFromUserId()+" <<>> "+tweet.getFromUser()+" <<>> "+tweet.getText()+" << >> "+tweet.getProfileImageUrl());
		        if(lang.equals("en")){
		        	initTweetCount++;
		        	System.out.println(initTweetCount +" "+tweet.getIsoLanguageCode()+" "+tweet.getCreatedAt()+" "+tweet.getFromUserId()+" <<>> "+tweet.getFromUser()+" <<>> "+tweet.getText()+" << >> "+tweet.getProfileImageUrl());
				      
		        try {
		        	if(tweet.getFromUser()!=null){
					user= twitter.showUser(tweet.getFromUser());
		        	}
			
				} catch (TwitterException e) {
					processTwitterException(e);										
				}
				
				if(user!=null){
				if(tweet.getFromUser()!=null){
				twitterDTO.setTweetCreatedAt(tweetCreatedAt);
				twitterDTO.setUserImg(tweet.getProfileImageUrl());
				twitterDTO.setUserScreeName("http://twitter.com/"+tweet.getFromUser());
			    twitterDTO.setUserName(user.getName());			
				tweetContect =tweet.getText();
				
				/*To include RichContent to the Tweet*/
				StringBuffer tweetText = new StringBuffer(tweetContect);		    	 
		    	String delimiters=" ";
			    String rep= null;
			    String repText =tweetText.toString();
			    repText= changeTweetToRichContent(repText);
			    //System.out.println("repText  "+repText);
				twitterDTO.setTweet(repText);
				
					}
				}	
				twitterSearchList.add(twitterDTO);
		    }

				if(tweetBreak == initTweetCount){					
					break;
				}
	  }
	}
}else{
			query =new Query(msg);
     		result = twitter.search(query);		
     		int tweetBreak = 0;
			if(APP_MODE.equalsIgnoreCase("DEVELOPMENT")){
				tweetBreak = 1;
			}else{
				tweetBreak = 5;
			}
			int initTweetCount = 0;
		    for (Tweet tweet : result.getTweets()) {
		    
		    	twitterDTO = new TwitterDTO();
		        User user =null;
		        lang=tweet.getIsoLanguageCode();
		        
		        /*To include RichContent to the Tweet Created Time*/
		        Date dt =tweet.getCreatedAt();
		        /*To convert the Tweeted date user Time Zone*/
		        TimeZone userTimeZ = TimeZone.getTimeZone(userTimeZone);		
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        df.setTimeZone(userTimeZ);
		        String today = df.format(dt);     
		        String createdTime= today;
		        String tweetCreatedAt = dateUtility.sessionCommentTimeCheck(createdTime,userTimeZone);
		      
		        if(lang.equals("en")){
		           	initTweetCount++;
		        	  System.out.println(initTweetCount +" "+tweet.getIsoLanguageCode()+" "+tweet.getCreatedAt()+" "+tweet.getFromUserId()+" <<>> "+tweet.getFromUser()+" <<>> "+tweet.getText()+" << >> "+tweet.getProfileImageUrl());
			
		        try {
		        	if(tweet.getFromUser()!=null){
					user= twitter.showUser(tweet.getFromUser());
		        	}
			
				} catch (TwitterException e) {
					processTwitterException(e);										
				}
				
				if(user!=null){
					if(tweet.getFromUser()!=null){
				twitterDTO.setTweetCreatedAt(tweetCreatedAt);
				twitterDTO.setUserImg(tweet.getProfileImageUrl());
				twitterDTO.setUserScreeName("http://twitter.com/"+tweet.getFromUser());
			    twitterDTO.setUserName(user.getName());			
				tweetContect =tweet.getText();
				
				/*To include RichContent to the Tweet*/
				StringBuffer tweetText = new StringBuffer(tweetContect);		    	 
		    	String delimiters=" ";
			    String rep= null;
			    String repText =tweetText.toString();
			    repText= changeTweetToRichContent(repText);
			    //System.out.println("repText  "+repText);
				twitterDTO.setTweet(repText);
				
					}
				}	
				twitterSearchList.add(twitterDTO);
		    }

				if(tweetBreak == initTweetCount){					
					break;
				}
	  }
} 
			} catch (TwitterException e) {
				
				processTwitterException(e);
				
			} 
			return twitterSearchList;
		}
	  
	  public void processTwitterException(TwitterException e) throws BaseAppException{
		  
		  if(e.getStatusCode()==400){
				System.out.println("Limit exceeded");
				 throw new TwitterRateLimitException();
			}else if (e.getStatusCode()==401) {
				System.out.println("Authentication credentials were missing or incorrect.");
				throw new TwitterUnauthorizedException();
			}else if (e.getStatusCode()==403) {
				System.out.println("The request is understood, but it has been refused.  An accompanying error message will explain why. This code is used when requests are being denied due to update limits.");
				throw new TwitterForbiddenException();
			}else if (e.getStatusCode()==404) {
				System.out.println(" The URI requested is invalid or the resource requested, such as a user, does not exists. ");
			}else if(e.getStatusCode()==503){
				System.out.println("Service Unavailable: The Twitter servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.");
				throw new TwitterServiceUnavailableException("Service Unavailable: The Twitter servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.");
			}else if(e.getStatusCode()==500){
				System.out.println("Something is broken.  Please post to the group so the Twitter team can investigate.");
				throw new TwitterServiceUnavailableException("Something is broken.  Please post to the group so the Twitter team can investigate.");
			}else if (e.getStatusCode()== -1 ) {				
				if(e.isCausedByNetworkIssue()){
					System.out.println("Unable to connect to Twitter. Host Not found. Check for Internet Connection.");
				}else{
					System.out.println("Unknown Twitter problem.");
				}
				
			}else{
				e.printStackTrace();
			}
	  }
	  
	  
	  public TwitterDTO  publicProfile(TwitterDTO twitterDTO) throws BaseAppException{
		  String screenName = null;
		  AccessToken accessToken = (AccessToken) twitterDTO.getAccessToken();
		  twitter.setOAuthAccessToken(accessToken);
		  screenName =twitterDTO.getUserScreeName();		
		  User user =null;
		  List friendsList = null;			
		  List followersList = null;
		  List tweetList = null;
			try {
				  twitterDTO = new TwitterDTO();
				user = twitter.showUser(screenName);				
				System.out.println("**************** >> Profile of "+screenName+" << ******************");
				twitterDTO = fetchProfile(user,twitterDTO);			
				System.out.println("**************** >> Friends\\Following of "+screenName+" << ******************");
		/*		//friendsList = getFriendsProfile(twitter,screenName,twitterDTO);
				if(!friendsList.isEmpty()){
				twitterDTO.setFriendsList(friendsList);
				}
				System.out.println("**************** >> Followers of "+screenName+" << ******************");
				//followersList = getFollowersProfile(twitter,screenName,twitterDTO);
				if(!followersList.isEmpty()){
				twitterDTO.setFollowersList(followersList);
				}
				System.out.println("**************** >> Tweets of "+screenName+" << ******************");
				//tweetList =tweets(twitter,screenName,twitterDTO);
				if(!tweetList.isEmpty()){
				twitterDTO.setTweetList(tweetList);
				}*/
			} catch (TwitterException e) {			
				processTwitterException(e);	
			}
			
return twitterDTO;
	  }
	  public TwitterDTO  checkAlreadyFriend(TwitterDTO twitterDTO){
		  String friendScreenName = null;
		  int friendId = 0;
		  AccessToken accessToken =(AccessToken) twitterDTO.getAccessToken();
		  twitter.setOAuthAccessToken(accessToken);
		  friendScreenName =twitterDTO.getUserScreeName();	
		   try {
			   twitterDTO = new TwitterDTO();
			User profile = twitter.showUser(friendScreenName);
			friendId = profile.getId();
			IDs friendsIds = twitter.getFriendsIDs();
			int i=0;
			if(friendsIds != null){
				for (int userId : friendsIds.getIDs()) {
				
					if(userId==friendId){
						twitterDTO.setAlreadyFriends(true);
					}
				}
			}
			
			IDs followersIds = twitter.getFollowersIDs();
			if(followersIds != null){
				for (int userId : followersIds.getIDs()) {
					i++;
					System.out.println(i+" - "+ userId+ " Already you are follwoing !");
				
							
					fetchProfile(twitter.showUser(userId),twitterDTO);
					
					if(userId==friendId){
						System.out.println(friendId + " Friend is following you !");
						twitterDTO.setFriendIsFollowing(true);
					}
				}
			}
		} catch (TwitterException e) {		
			e.printStackTrace();
		}
			
		  return twitterDTO;
	  }
	  public TwitterDTO  inviteFriend(TwitterDTO twitterDTO){
		 boolean result = false;
		 String friendScreenName = null;
		 AccessToken accessToken = null; 
		 friendScreenName =twitterDTO.getUserScreeName();		
		 if(twitterDTO != null){				 
			 accessToken = (AccessToken)twitterDTO.getAccessToken();	
			 if(accessToken != null){
				 twitter.setOAuthAccessToken(accessToken);
				 if(friendScreenName!=null){
				 try {
					User user = twitter.createFriendship(String.valueOf(friendScreenName));
					twitterDTO.setFriendInvited(true);
					result = true;
				} catch (TwitterException e) {	
					twitterDTO.setFriendInvited(false);
					result = false;
					twitterDTO.setResultMessage(e.getMessage());
					e.printStackTrace();
				}
				
			   }
				 twitterDTO.setFriendInvited(result);
		}
				 
	}		 
			
	return twitterDTO;
  }
	  
	  
	  private List getFollowersProfile(Twitter twitterClient,String screenName,TwitterDTO twitterDTO) throws BaseAppException {
			//ProfileDTO profileDTO = null;
			User profile = null;
			List followersList = null;
			
			try {
				followersList = new ArrayList();
				int i=0;
				IDs friendsIds = twitterClient.getFollowersIDs(screenName);
				
				if(friendsIds != null){
					for (int userId : friendsIds.getIDs()) {
						//if(i<5){
						profile = twitterClient.showUser(userId);
						
						twitterDTO= fetchProfile(profile,twitterDTO);
						followersList.add(twitterDTO);
						}
						//i++;
						
					//}
				}
	
			} catch (TwitterException e) {
				
				processTwitterException(e);	
			}
			
			return followersList;
		}
		private List getFriendsProfile(Twitter twitterClient,String screenName,TwitterDTO twitterDTO) throws BaseAppException {
			//ProfileDTO profileDTO = null;
			User profile = null;
			List friendsList = null;
			
			try {
				friendsList = new ArrayList();
			
				IDs friendsIds = twitterClient.getFriendsIDs(screenName);
				int i=0;
				if(friendsIds != null){
					for (int userId : friendsIds.getIDs()) {
						//if(i<5){
						profile = twitterClient.showUser(userId);
						
						twitterDTO = fetchProfile(profile,twitterDTO);
						friendsList.add(twitterDTO);
						}
						//i++;
						
					//}
				}
	
			} catch (TwitterException e) {
				
				processTwitterException(e);	
			}
			
			return friendsList;
		}
public TwitterDTO fetchProfile(User user,TwitterDTO twitterDTO){
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	String userDesc= null;
	String favCount =null;
	String followCount= null;
	String friendsCount = null;
	String location = null;
	String name = null;
	String profImgUrl= null;
	String personalUrl =null;
	String twitterURL ="http://twitter.com/"+user.getScreenName();
	      userDesc=user.getDescription();
	        favCount =String.valueOf(user.getFavouritesCount());
		    followCount=String.valueOf(user.getFollowersCount());
			friendsCount = String.valueOf(user.getFriendsCount());
			location =user.getLocation();
			name =user.getName();
			profImgUrl=String.valueOf(user.getProfileImageURL());
			//	System.out.println("User ID   >>  "+user.getId());
			//System.out.println("TwitterA/C CreatedAt  >>  "+user.getCreatedAt());
			System.out.println("Description   >>  "+user.getDescription());
			System.out.println("FavouritesCount   >>  "+user.getFavouritesCount());
			System.out.println("FollowersCoun   >>  "+user.getFollowersCount());
			System.out.println("FriendsCount   >>  "+user.getFriendsCount());					
			System.out.println("Language   >>  "+user.getLang());
			System.out.println("Location >>  "+user.getLocation());
			System.out.println("Name  >>  "+user.getName());
			System.out.println("ProfileImageURL   >>  "+user.getProfileImageURL());
			System.out.println("Total tweets   >>  "+user.getStatusesCount());	
			
			
	if(twitterURL!=null){
	twitterDTO.setUserURL(twitterURL);
	System.out.println("Twitter URL   >>  "+twitterURL);
	}
	if(userDesc!=null && (!userDesc.equals(""))){
		twitterDTO.setUserDesc(userDesc);
	}else{
		twitterDTO.setUserDesc(null);
	}
	if(favCount!=null){
		twitterDTO.setUserFavCount(favCount);
	}
	if(followCount!=null){
		twitterDTO.setFollowersCount(followCount);
	}
	if(friendsCount!=null){
		
		twitterDTO.setFriendsCount(friendsCount);
	}
	if(location!=null){
		twitterDTO.setUserLocation(location);
	}
	if(name!=null){
			twitterDTO.setUserName(name);
	}
	if(profImgUrl!=null){
		twitterDTO.setUserImg(profImgUrl);
	}
	if(user.getURL()!=null){
	if(!user.getURL().equals(twitterURL)){
		personalUrl=String.valueOf(user.getURL());
		System.out.println("Personal URL   >>  "+user.getURL());
		twitterDTO.setPersonalUrl(personalUrl);
	}
	}
//	Status status=user.getStatus();
//	System.out.println("Latest Tweet  >> TweetId "+status.getId()+"|Tweet> "+status.getText()+" |via> "+status.getSource()
//			+"|Loc >"+status.getGeoLocation()+"|tweetTime >"+status.getCreatedAt()+"Place Details we can get >"+status.getPlace());			
	/*System.out.println("RateLimitStatus   >>  "+user.getRateLimitStatus());
	System.out.println("ProfileBackgroundColor   >>  "+user.getProfileBackgroundColor());
	System.out.println("ProfileBackgroundImageUrl   >>  "+user.getProfileBackgroundImageUrl());
	System.out.println("ProfileLinkColor   >>  "+user.getProfileLinkColor());
	System.out.println("ProfileSidebarBorderColor   >>  "+user.getProfileSidebarBorderColor());
	System.out.println("user.getProfileSidebarFillColor()   >>  "+user.getProfileSidebarFillColor());
	System.out.println("ProfileTextColor   >>  "+user.getProfileTextColor());*/
	return twitterDTO;
}
	  /**
		 * @param twitterDTO
		 * @return
		 * @throws EventPortalException 
		 */
		public TwitterDTO deleteToken(TwitterDTO twitterDTO) throws EventPortalException {

			String profileId = null;

			try {

				profileId = twitterDTO.getProfileId();
				
				Keys keys = new Keys();
				
				keys.setHiddenObject("TWITTER");
				keys.setProfileId(profileId);
				
				boolean retSts = keys.deleteAccessToken(keys);
				
				if(retSts){	
					retSts = updatePublicProfile(null, null,null,profileId);
					
					twitterDTO = initTwitter();
					
					twitterDTO.setDeleteStatus(retSts);			
				}
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return twitterDTO;
		}
		
		
		public String getTwitterId(AccessToken accessToken) throws BaseAppException{
			 
			String twitterId = null;
			
			User user = null;
			try {
				 twitter.setOAuthAccessToken(accessToken);
				 
				 user = twitter.verifyCredentials();
				 
				 twitterId = user.getScreenName();
				
				  } catch (TwitterException e) {
					  processTwitterException(e);	
				 
				  }
			
			return twitterId;
		}
		
		public String getPublicProfile(AccessToken accessToken) throws BaseAppException{
			 
			String profileURL = null;
			
			User user = null;
			try {
				 twitter.setOAuthAccessToken(accessToken);
				 user = twitter.verifyCredentials();
				 
				 profileURL = "http://www.twitter.com/"+user.getScreenName();
				
				  } catch (TwitterException e) {
					  processTwitterException(e);	
				 
				  }
			
			return profileURL;
		}
		
		
		
		
		public boolean updatePublicProfile(String twitterId, String twitterImgUrl,String profileURL, String profileId) throws EventPortalException{
			boolean result = false;
			
			SocialMedia socialMedia = new SocialMedia();
			
			socialMedia.setSmProfileId(profileId);
			socialMedia.setTwitterId(twitterId);
			socialMedia.setTwitterProfileUrl(profileURL);
			socialMedia.setTwitterImgUrl(twitterImgUrl);
			socialMedia.setHiddenObject("TWITTER");
			socialMedia.setTwtShowTweets("N");
			socialMedia.setTwtAllowFriends("N");
			
			
			result = socialMedia.updateSocialMediaProfile(socialMedia);		
		
			
			return result;
		}

		
		  public TwitterDTO getProfileImageURL(AccessToken accessToken) throws BaseAppException{
			  twitter.setOAuthAccessToken(accessToken);
			  TwitterDTO twitterDTO = null;	
			  twitterDTO = new TwitterDTO();
			  User user = null;
				try {
					user = twitter.showUser(twitter.getId());
					URL url = user.getProfileImageURL();					
					
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					processTwitterException(e);	
				}  
			        System.out.println(user.getName()+"<< >>"+user.getProfileImageURL());
					
					if(user!=null){
						if(user.getProfileImageURL()!=null){
							System.out.println("TwitterImageURL=> "+user.getProfileImageURL());
							twitterDTO.setUserImg(user.getProfileImageURL().toString());
						}
					 twitterDTO.setUserName(user.getName());
					}
				return twitterDTO;
			}
		
		
		  public String profileImageURL(AccessToken accessToken) throws BaseAppException{
			  twitter.setOAuthAccessToken(accessToken);
			  TwitterDTO twitterDTO = null;	
			  twitterDTO = new TwitterDTO();
			  User user = null;
			  String profileImgURL = null;
				try {
					user = twitter.showUser(twitter.getId());
					URL url = user.getProfileImageURL();					
					
				} catch (TwitterException e) {
					processTwitterException(e);	
				}  
			   		if(user!=null){
						if(user.getProfileImageURL()!=null){
							System.out.println("TwitterImageURL=> "+user.getProfileImageURL());
							profileImgURL =user.getProfileImageURL().toString();
							}
					}
				return profileImgURL;
			}
		
		
		 public List  tweets(Twitter twitter,String screenName,TwitterDTO twitterDTO) throws BaseAppException{
		  List tweetList =new ArrayList();

	    List<Status> statuses = null;
	    
	    Paging paging = new Paging(1, 10);

		try {
			statuses = twitter.getUserTimeline(screenName,paging);
		} catch (TwitterException e) {
			processTwitterException(e);	
		}
		int i=1;
		if(!statuses.isEmpty()){
		
		for (Status status : statuses) {
			if(i<=10){
		    	User user = status.getUser(); 
		    	twitterDTO =  new TwitterDTO();
		    //	
		    //	if(userId==status.getUser().getId()){
		    	if(status.getId()!=0){
		    		 System.out.println(i+ "TweetId=> "+status.getId());
		    	twitterDTO.setTweetId(String.valueOf(status.getId()));
		    	}
		    	if(user.getProfileImageURL()!=null){				
						 twitterDTO.setUserImg(user.getProfileImageURL().toString());
					}
					if(user.getScreenName()!=null){			
						 twitterDTO.setUserScreeName("http://twitter.com/"+user.getScreenName());
					}
					if(user.getName()!=null){			
							twitterDTO.setUserName(user.getName());
							}
					if(status.getText()!=null){			
						twitterDTO.setTweet(status.getText());}
			         System.out.println(status.getId()+" : "+status.getCreatedAt()+ " >> " +status.getText());
			         tweetList.add(twitterDTO);
			         i++;
				}else{
					   break;
				   }
					
		   }
			
		}
	 

		return tweetList;
	  }
		
			public ResultDTO shareMsgInTwitter(TwitterDTO twitterDTO){
			ResultDTO resultDTO = null;
			 boolean result = false;
			 String shareMessage = null;
			 AccessToken accessToken = null; 
			 String sessionTweetTag = null;
			 String sessionEventTweetTag = null;
			 resultDTO = new ResultDTO();
			 if(twitterDTO != null){
		 
				 accessToken = (AccessToken)twitterDTO.getAccessToken();
				 shareMessage = twitterDTO.getShareComment();
				 sessionTweetTag = twitterDTO.getSessionTweetTag();
				 sessionEventTweetTag = twitterDTO.getSessionEventTweetTag();
				 System.out.println(sessionEventTweetTag+"sessionTweetTag "+sessionTweetTag);
				 if(twitterDTO.isShareMsg()){
					 
					 if(accessToken != null){
						 twitter.setOAuthAccessToken(accessToken);
						 shareMessage = sessionEventTweetTag+" "+sessionTweetTag+" "+shareMessage ;
	
						 try {
							 if(sessionEventTweetTag!=null && sessionTweetTag!=null){
							result = shareMsgInTwitter(twitter, shareMessage);
							 }
						} catch (BaseAppException e) {
							result = false;
							twitterDTO.setResultMessage(e.getMessage());						
							e.printStackTrace();
						}
					 }
				 }
			 }
			 resultDTO.setTwitterDTO(twitterDTO);
			 resultDTO.setResultStatus(result);
			 return resultDTO;
		}
		
		public boolean shareMsgInTwitter(Twitter twitter,String Msg) throws BaseAppException{
		    
		    boolean result = false;
		    Status status = null;
		    try {
		     if(Msg!=null){		   
		    	 if(Msg.length()>=140){
		    		String st[]=splitTweetToLimit(Msg);		 	        
		 	        for (int c=0;c<=st.length;c++){		 	        	
		 	        	if(st[c]==null){
		 	        		break;
		 	        	}
		 	        	System.out.println("Splited Tweets "+c+" >> "+st[c]);
		 	        	status = twitter.updateStatus(st[c]);
		 	        	result = true;
		 	        }
		    	 }else{
		     status = twitter.updateStatus(Msg);		     
		     result = true;
		    	 }
		     }
		    } catch (TwitterException e) {
		     result=false;
		     System.out.println("Exception while updating the status to [" + status.getText() + "].");
		     processTwitterException(e);	
		    }  
		   return result;
		   }
	
		public boolean followInTwitter(long userId,Twitter twitter) throws BaseAppException{    User user = null;
	    boolean result = false;
	  try {
		  
		  
	   user = twitter.createFriendship(String.valueOf(userId));
	    result = true;
	  } catch (TwitterException e) {
		  processTwitterException(e);	
	  }
	  return result;
	   }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  
	  /*
	  public int getUserId(Twitter twitter){
		    User user = null;
		    int userId = 0;
			try {
				user = twitter.verifyCredentials();
				
				System.out.println(user.getId()+" !!!!!  Name of User "+user.getName()+"Screen "+user.getScreenName()+""+user.getStatusesCount());
				userId = user.getId();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  return userId;
	  }

	
	  
	  public TwitterDTO getTwitterDetails(TwitterDTO twitterDTO){
		  
		  AccessToken accessToken = null;
		  ProfileDTO profileDTO = null;
			List friendsList = null;
			 int friendsCount = 0;
		  if(twitterDTO != null){
			  
			  accessToken = (AccessToken)twitterDTO.getAccessToken();
			 
			  if(accessToken != null){
				  
				  twitter.setOAuthAccessToken(accessToken);
				  
				  profileDTO = getUserProfile(twitter);
				  
				  friendsList = getFriendsProfile(twitter);
				  
				  friendsCount = friendsCount(twitter);
				  
				  twitterDTO = new TwitterDTO();
				  
				  twitterDTO.setProfileDTO(profileDTO);
				  twitterDTO.setFriendsList(friendsList);
				  twitterDTO.setFriendsCount(String.valueOf(friendsCount));
				  
			  }
			  
		  }
		  
		  return twitterDTO;
	  }
	*/
//	private ProfileDTO getUserProfile(Twitter twitterClient) {
//		ProfileDTO profileDTO = null;
//		User profile = null;
//		
//		try {
//			
//			profile = twitterClient.showUser(twitterClient.getId());
//			
//			profileDTO = getProfile(profile);
//		
//		} catch (TwitterException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return profileDTO;
//	}
	
//	private List getFriendsProfile(Twitter twitterClient) {
//		ProfileDTO profileDTO = null;
//		User profile = null;
//		List friendsList = null;
//		
//		try {
//			friendsList = new ArrayList();
//			
//			IDs friendsIds = twitterClient.getFriendsIDs();
//			
//			if(friendsIds != null){
//				for (int userId : friendsIds.getIDs()) {
//					
//					profile = twitterClient.showUser(userId);
//					
//					profileDTO = getProfile(profile);
//					friendsList.add(profileDTO);
//					
//				}
//			}
//	
//		} catch (TwitterException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return friendsList;
//	}
	
//	 public int friendsCount(Twitter twitterClient) {
//		 
//		 int friendsCount = 0;
//		 List friendsList = null;
//		 
//		 try {
//			 
//			IDs ids = twitterClient.getFriendsIDs();
//			if(ids != null){
//				friendsCount = ids.getIDs().length;
//			}
//			
//		} catch (TwitterException e) {
//			
//			e.printStackTrace();
//		}
//		 
//		 return friendsCount;
//	 }
	 

//		
//	public ProfileDTO getProfile(User profile){
//		ProfileDTO profileDTO = null;
//		if(profile != null){
//			
//			profileDTO = new ProfileDTO();
//			
//			profileDTO.setProfileId(String.valueOf(profile.getId()));
//			profileDTO.setFirstName(profile.getName());
//			profileDTO.setProfileLargePhotoLoc(profile.getProfileImageURL().toString());
//			profileDTO.setLocation(profile.getLocation());
//					
//		}
//		
//		return profileDTO;
//	}
//
//	public TwitterDTO getUserDetails(TwitterDTO twitterDTO){
//		  AccessToken accessToken = null;
//		  
//		  accessToken = (AccessToken)twitterDTO.getAccessToken();
//		  if(accessToken != null){
//			  
//		  twitter.setOAuthAccessToken((AccessToken)twitterDTO.getAccessToken());
//		 
//		  twitterDTO = new TwitterDTO();
//		    User user = null;
//		    int userId = 0;
//			try {
//				user = twitter.verifyCredentials();
//				
//				twitterDTO.setUserName(user.getName());
//				twitterDTO.setUserBgImgUrl(user.getProfileImageURL().toString());
//				twitterDTO.setUserLocation(user.getLocation());
//				System.out.println(user.getId()+" !!!!!  Name of User "+user.getName()+"Screen "+user.getScreenName()+""+user.getStatusesCount());
//				userId = user.getId();
//			} catch (TwitterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			  
//		  }
//		  return twitterDTO;
//	  }
//	  
//	  
//	  
//	  
//	  public List getGeneralTweetList(TwitterDTO twitterDTO) throws BaseAppException{
//		  List list =new ArrayList();
//		  AccessToken accessToken = null;
//		  AccessToken userAccessToken = null;
//		  accessToken = (AccessToken)twitterDTO.getAdminAccessToken();
//		  
//		  if(accessToken != null){
//			  //list = getTimeline(accessToken);
//			  //list = getUserTimeline(accessToken);
//			  list = getFriendsTimeline(accessToken);
//			  
//			  /*Filtering TieCon Tweets*/
//			  list = filterTweets(list);
//			  
//			  /*To Tweet on User Tweeter*/
//			  
//			/* userAccessToken = getAccessToken("600004");			  
//			  twitter.setOAuthAccessToken(userAccessToken);			  
//			  boolean isTweeted = shareMsgInTwitter(twitter,"#TieCon Tweet From Portal ");
//			  System.out.println("shareMsgInTwitter"+isTweeted);*/
//			 
//		  }
//		  return list;
//	  }
//	  
//
//	
//		
//	  public List getUserTimeline(AccessToken accessToken) throws BaseAppException {
//
//		    twitter.setOAuthAccessToken(accessToken);
//			
//			List tweetList =new ArrayList();
//			   
//		    List<Status> statuses = null;
//		    
//			try {
//				
//				statuses = twitter.getUserTimeline();
//				tweetList = getTweets(statuses);
//			
//			} catch (TwitterException e) {
//				if(e.getStatusCode()==400){
//					System.out.println("Limit exceeded");
//					 throw new TwitterRateLimitException();
//				}else if (e.getStatusCode()==401) {
//					System.out.println("Authentication credentials were missing or incorrect.");
//					throw new TwitterUnauthorizedException();
//				}else if (e.getStatusCode()==403) {
//					System.out.println("The request is understood, but it has been refused.  An accompanying error message will explain why. This code is used when requests are being denied due to update limits.");
//					throw new TwitterForbiddenException();
//				}else if (e.getStatusCode()==404) {
//					System.out.println(" The URI requested is invalid or the resource requested, such as a user, does not exists. ");
//				}else{
//					e.printStackTrace();
//				}
//			}
//
//		   return tweetList;
//			
//		}
//	  
//	  public List getFriendsTimeline(AccessToken accessToken) throws BaseAppException {
//
//		    twitter.setOAuthAccessToken(accessToken);
//			
//			List tweetList =new ArrayList();
//			   
//		    List<Status> statuses = null;
//		    
//			try {
//				
//				statuses = twitter.getFriendsTimeline();
//				tweetList = getTweets(statuses);
//			
//			} catch (TwitterException e) {
//				if(e.getStatusCode()==400){
//					System.out.println("Limit exceeded");
//					 throw new TwitterRateLimitException();
//				}else if (e.getStatusCode()==401) {
//					System.out.println("Authentication credentials were missing or incorrect.");
//					throw new TwitterUnauthorizedException();
//				}else if (e.getStatusCode()==403) {
//					System.out.println("The request is understood, but it has been refused.  An accompanying error message will explain why. This code is used when requests are being denied due to update limits.");
//					throw new TwitterForbiddenException();
//				}else if (e.getStatusCode()==404) {
//					System.out.println(" The URI requested is invalid or the resource requested, such as a user, does not exists. ");
//				}else{
//					e.printStackTrace();
//				}
//			}
//
//		   return tweetList;
//			
//		}
//	  
//	  
//	  public List getTweets(List<Status> statuses){
//		  List tweetList = null;
//		  TwitterDTO twitterDTO = null;
//			
//		  tweetList = new ArrayList();
//		  
//		  for (Status status : statuses) {
//			  twitterDTO =  new TwitterDTO();
//			  User user = status.getUser(); 
//		            if(status.getId()!=0){
//			    	//	 System.out.println("TweetId=> "+status.getId());
//			    	twitterDTO.setTweetId(String.valueOf(status.getId()));
//			    	}
//			    	if(user.getProfileImageURL()!=null){				
//						 twitterDTO.setUserImg(user.getProfileImageURL().toString());
//					}
//					if(user.getScreenName()!=null){			
//						 twitterDTO.setUserScreeName("http://twitter.com/"+user.getScreenName());
//					}
//					if(user.getName()!=null){			
//							twitterDTO.setUserName(user.getName());}
//					if(status.getText()!=null){			
//						twitterDTO.setTweet(status.getText());}
//			       System.out.println(status.getUser().getId()+" : "+status.getUser().getName() + " >> " +status.getText());
//					tweetList.add(twitterDTO);
//					}
//		  
//		  return tweetList;
//	  }
//	  
//	  
//	  public List getTimeline(AccessToken accessToken) throws BaseAppException {
//
//		    twitter.setOAuthAccessToken(accessToken);
//		  
//			List myTweets =new ArrayList();
//			List followersTweets =new ArrayList();
//			List list =new ArrayList();
//			/** Four tweets in the first page will be displayed*/
//
//		   Paging paging = new Paging(5, 100);
//		   
//		    List<Status> statuses = null;
//			try {
//			
//				statuses = 	twitter.getFriendsTimeline(paging);
//			} catch (TwitterException e) {
//				if(e.getStatusCode()==400){
//					System.out.println("Limit exceeded");
//					 throw new TwitterRateLimitException();
//				}else if (e.getStatusCode()==401) {
//					System.out.println("Authentication credentials were missing or incorrect.");
//					throw new TwitterUnauthorizedException();
//				}else if (e.getStatusCode()==403) {
//					System.out.println("The request is understood, but it has been refused.  An accompanying error message will explain why. This code is used when requests are being denied due to update limits.");
//					throw new TwitterForbiddenException();
//				}else if (e.getStatusCode()==404) {
//					System.out.println(" The URI requested is invalid or the resource requested, such as a user, does not exists. ");
//				}else{
//					e.printStackTrace();
//				}
//			}
//			
//			/** All the Tweets in the first page will be displayed*/
//			 //List<Status> statuses = twitter.getFriendsTimeline();	  
//			 
//			 TwitterDTO twitterDTO = null;
//		
//			 int userId = getUserId(twitter);
//			 
//		    for (Status status : statuses) {
//		    	User user = status.getUser(); 
//		    	twitterDTO =  new TwitterDTO();
//		    	
//		    //	if(userId==status.getUser().getId()){
//		    	if(status.getId()!=0){
//		    		 System.out.println("TweetId=> "+status.getId());
//		    	twitterDTO.setTweetId(String.valueOf(status.getId()));
//		    	}
//		    	if(user.getProfileImageURL()!=null){				
//						 twitterDTO.setUserImg(user.getProfileImageURL().toString());
//					}
//					if(user.getScreenName()!=null){			
//						 twitterDTO.setUserScreeName("http://twitter.com/"+user.getScreenName());
//					}
//					if(user.getName()!=null){			
//							twitterDTO.setUserName(user.getName());}
//					if(status.getText()!=null){			
//						twitterDTO.setTweet(status.getText());}
//			        System.out.println(status.getUser().getId()+" : "+status.getUser().getName() + " >> " +status.getText());
//			    	followersTweets.add(twitterDTO);
//				
//					
//				}
//		   // }
//		  
//		    
//		 
//		   return followersTweets;
//			
//		}
//	  
//	
//	  public List filterTweets(List list){
//		  boolean result = false;
//		  List tweetList =new ArrayList();
//		  TwitterDTO twitterDTO=null;
//		  Iterator it= list.iterator();
//		  
//		  while(it.hasNext()){
//			twitterDTO=(TwitterDTO) it.next();
//			int intTieIndex = twitterDTO.getTweet().indexOf("#TieCon");
//			int intTiEIndex = twitterDTO.getTweet().indexOf("#TiECon");
//			  if((intTieIndex == - 1)){
//			//	  System.out.println("! Tie >>"+twitterDTO.getTweet());					  
//			  }else{
//			//	  System.out.println(" #Tie  >>"+twitterDTO.getTweet());
//					  tweetList.add(twitterDTO);			  }
//			  if((intTiEIndex == - 1)){
//			//	  System.out.println("! Tie >>"+twitterDTO.getTweet());
//			  }else{
//			//	  System.out.println(" #Tie  >>"+twitterDTO.getTweet());
//					  tweetList.add(twitterDTO);					  
//			  }
//		  }
//	
//		  return tweetList ;
//	  }
//

//	
//	public boolean shareMsgInTwitter(TwitterDTO twitterDTO){
//		
//		 boolean result = false;
//		 String shareMessage = null;
//		 AccessToken accessToken = null; 
//
//		 if(twitterDTO != null){
//			 
//			 accessToken = (AccessToken)twitterDTO.getAccessToken();
//			 shareMessage = twitterDTO.getShareComment();
//			 
//			 if(twitterDTO.isShareMsg()){
//				 
//				 if(accessToken != null){
//					 twitter.setOAuthAccessToken(accessToken);
//					 shareMessage = shareMessage + " #TiECon";
//
//					 result = shareMsgInTwitter(twitter, shareMessage);
//				 }
//			 }
//		 }
//		 
//		 return result;
//	}
//	
//	public boolean shareMsgInTwitter(Twitter twitter,String Msg){
//	    
//	    boolean result = false;
//	    Status status = null;
//	    try {
//	     if(Msg!=null){
//	     status = twitter.updateStatus(Msg);
//	     result = true;
//	     }
//	    } catch (TwitterException e) {
//	     result=false;
//	     System.out.println("Exception while updating the status to [" + status.getText() + "].");
//	     e.printStackTrace();
//	    }  
//	   return result;
//	   }
//
//	public boolean followInTwitter(long userId,Twitter twitter){    User user = null;
//    boolean result = false;
//  try {
//	  
//	  
//   user = twitter.createFriendship(String.valueOf(userId));
//    result = true;
//  } catch (TwitterException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//  return result;
//   }
//	
//	public boolean followInTwitter(TwitterDTO twitterDTO, Profile profile) throws EventPortalException {
//		 boolean result = false;
//		 AccessToken accessToken = null; 
//		 String screenName = null;
//		 String inviteMessage = null;
//		 
//		 if(twitterDTO != null){
//			 
//			 accessToken = (AccessToken)twitterDTO.getAccessToken();
//			 screenName = profile.getTwitterId();
//			 inviteMessage = twitterDTO.getInviteMessage();
//			 
//				 if(accessToken != null){
//				 twitter.setOAuthAccessToken(accessToken);
//				 result = followInTwitter(twitter, screenName,inviteMessage);
//				 
//				 }
//			 
//		 }
//		 
//		 return result;
//	}
//	
//public boolean followInTwitter(Twitter twitter,String screenName, String inviteMessage){    User user = null;
//    boolean result = false;
//  try {
//   user = twitter.createFriendship(screenName, true);
//   twitter.sendDirectMessage(screenName, inviteMessage);
// 
//   result = true;
//   
//  } catch (TwitterException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//  return result;
//   }
//
	
//	
	
//	
	
//
//	public TwitterDTO getTwitterMoreInfo(TwitterDTO twitterDTO) throws EventPortalException {
//		 AccessToken accessToken = null;
//		  ProfileDTO profileDTO = null;
//			List friendsList = null;
//			 int friendsCount = 0;
//		 
//			 if(twitterDTO != null){
//			  
//			  accessToken = (AccessToken)twitterDTO.getAccessToken();
//			 
//			  if(accessToken != null){
//				  
//				  twitter.setOAuthAccessToken(accessToken);
//				  
//				  //profileDTO = getUserProfile(twitter);
//				  
//				  friendsList = getFriendsProfile(twitter);
//				  
//				  friendsCount = friendsCount(twitter);
//				  
//				  twitterDTO = new TwitterDTO();
//				  
//				  twitterDTO.setProfileDTO(profileDTO);
//				  twitterDTO.setFriendsList(friendsList);
//				  twitterDTO.setFriendsCount(String.valueOf(friendsCount));
//				  
//			  }
//			  
//		  }
//		  
//		  return twitterDTO;
//	
//	}

		 public static String changeTweetToRichContent(String repText){
		    	String delimiters=" ";
		    	String repStr =null;
	    		StringTokenizer st = new StringTokenizer(repText, delimiters, true);			
		    	 while (st.hasMoreTokens()) {
			         String w = st.nextToken();   
			      
			         if(w.contains("#")){
			        	 if(w.length()!=1){
				        		// System.out.println(w);
				        		 String strExcl=w.substring(w.length()-1);   // To get ! near to #abc!:
					        	 if(strExcl.equals("!")){					        	
					        	 String[] st1=w.split(strExcl);
					        	 w=st1[0];					        
					        	 }				        
				         	 repStr= replaceTweetSpecialChar(w,"#");
				        	 repText = repText.replace(w, repStr);
				        	 }
			        	
			         }else if(w.contains("@")){
			        	 if(w.length()!=1 && w.length()!=2){
			        	 String strColon=w.substring(w.length()-1);   // To get : near to @abc:
			        	 String strColonCur=w.substring(w.length()-2);// To get : near to @abc_:
			        	 if(strColon.equals(":")){
			        	 String[] st1=w.split(strColon);
			        	 w=st1[0];
			        	 }
			        	 if(strColonCur.equals("_:")){
				        	String[] st1=w.split(strColonCur);
				        	w= w.substring(0, st1[0].length()-1);
				          }
			        	 repStr= replaceTweetSpecialChar(w,"@");
			        	 repText = repText.replace(w, repStr);
			        	 }
			     
			          }else if(w.contains("http")){
				        	 repStr= replaceTweetSpecialChar(w,"http");
				        	 repText = repText.replace(w, repStr);	
				        	
				          } 
			       //  System.out.println(">> "+repText);
		    	 }
		    	
				return repText;
		    }
		    	
		    
		
			  public static String replaceTweetSpecialChar(String word,String specialChar){
			    	String repText=null;
			  	
			  	repText = word;
		     	  if(repText.contains(specialChar)&& specialChar.equals("@")){
		     		 String[] st1=repText.split(specialChar);    
		     	    repText="<a href='http://twitter.com/"+st1[1]+"' target='blank'>"+repText+"</a>";
		     	   	
		     	 }else if((word.charAt(0)=='#') && word.contains(specialChar)&& specialChar.contains("#")){
		     		 String[] st1=word.split(specialChar);
		        	 repText="<a href='http://twitter.com/search?q=%23"+st1[1]+"' target='blank'>"+repText+"</a>";
		     	 }else if(repText.contains(specialChar)&& specialChar.contains("http")){
		     		repText="<a href='"+repText+"' target='blank'>"+repText+"</a>";	 
			  	 }
		       	 return repText;
			  }
			  
			  public String[] splitTweetToLimit(String tweet){
				  
			    	String splitText[]=new String[100];
			        int totalSize =tweet.length();
			        int fixed=0;
			        int j=0;
			        int i=0;
			        int k=140;
			        System.out.println("totalSize >>"+totalSize);
			  int inc=0;
			        while (fixed<=totalSize){
			        	
			       // 	System.out.println(j+"fixed >>"+fixed);
			        	if(i==0){		        	
			        		splitText[inc]=tweet.substring(i,140);
			        System.out.println(tweet.substring(i,140).length()+" >> "+tweet.substring(i,140));
			        i++;
			        	}
			        	
			        	if(j==0){
			        		j=k+1;
				        	fixed =j+140;
			      	//  System.out.println(j+"fixed >>"+fixed);
			      	 if(fixed>totalSize){
			      		inc++;
			      		
			      		splitText[inc]=tweet.substring(j,totalSize);
			      		System.out.println(tweet.substring(j,totalSize).length()+" >> "+tweet.substring(j,totalSize));
			      	 }else{
			      		inc++;
			      		splitText[inc]=tweet.substring(j,fixed);
			      		System.out.println(tweet.substring(j,fixed).length()+" >> "+tweet.substring(j,fixed));	 
			      	 }
			        	}else{
			        		
			        		j=fixed+1;
				        	fixed =j+140;
				        	// System.out.println(j+"!!!"+fixed+"!!!"+(totalSize-fixed));
				        	 if((totalSize-fixed)<140){
				        	//	 System.out.println(Msg.substring(j,totalSize).length());
				        		if(tweet.substring(j,totalSize).length()<140){
				        			inc++;
				        			splitText[inc]=tweet.substring(j-1,totalSize);
				        		 System.out.println(tweet.substring(j-1,totalSize).length()+" >> "+tweet.substring(j-1,totalSize));
				        		 break;
				        		}else{
				        			inc++;
				        			splitText[inc]=tweet.substring(j,fixed);
				        			 System.out.println(tweet.substring(j,fixed).length()+" >> "+tweet.substring(j,fixed));
				        		}
				        	
				        	 }else{
				        		 inc++;
				        		 splitText[inc]=tweet.substring(j,fixed);
				        			System.out.println(tweet.substring(j,fixed).length()+" >> "+tweet.substring(j,fixed));
			        		
				        	 }
			        	}
			      
			       	}
			        return splitText;
			  }
	

		
}

