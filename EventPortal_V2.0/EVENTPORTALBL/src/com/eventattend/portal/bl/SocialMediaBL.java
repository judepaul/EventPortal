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
 * Oct 20, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import twitter4j.http.AccessToken;

import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.common.util.DateUtility;

import com.eventattend.portal.dto.AgendaDTO;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;
import com.eventattend.portal.exceptions.BaseAppException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.LinkedInAlreadyInvitedException;
import com.eventattend.portal.exceptions.TwitterServiceUnavailableException;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 20, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class SocialMediaBL extends BusinessLayer {
	

	public ResultDTO initSocialMedia(SocialMediaDTO socialMediaDTO) {
		
		TwitterBL twitterBL = new TwitterBL();
		 LinkedInBL linkedInBL = new LinkedInBL();
		 FaceBookBL faceBookBL = new FaceBookBL();
		 
		 ResultDTO resultDTO = new ResultDTO();
		TwitterDTO twitterDTO = null;
		LinkedInDTO linkedInDTO = null;
		FaceBookDTO faceBookDTO = null;
		
		String profileId = null;
		String adminProfileId = null;
		
		try {
			
			twitterDTO = new TwitterDTO();
			linkedInDTO = new LinkedInDTO();
			faceBookDTO = new FaceBookDTO();
			
			profileId = socialMediaDTO.getProfileId();
			adminProfileId = socialMediaDTO.getAdminProfileId();
			
			twitterDTO.setProfileId(profileId);
			twitterDTO.setAdminProfileId(adminProfileId);
			
			twitterDTO = twitterBL.initTwitter(twitterDTO);
			
			linkedInDTO.setBaseURL(socialMediaDTO.getBaseURL());
			linkedInDTO.setProfileId(profileId);
			linkedInDTO.setAdminProfileId(adminProfileId);
			linkedInDTO = linkedInBL.initialiseLinkedIn(linkedInDTO);
			
			faceBookDTO.setProfileId(profileId);
			faceBookDTO.setAdminProfileId(adminProfileId);
			faceBookDTO = faceBookBL.initialiseFaceBook(faceBookDTO);
			socialMediaDTO = new SocialMediaDTO();
			
			socialMediaDTO.setTwitterDTO(twitterDTO);
			socialMediaDTO.setLinkedInDTO(linkedInDTO);
			socialMediaDTO.setFaceBookDTO(faceBookDTO);
			
			resultDTO.setSocialMediaDTO(socialMediaDTO);
		
	} catch (EventPortalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		resultDTO.setResultMsg(e.getMessage());
		resultDTO.setResultStatus(false);
	} catch (Exception e){
		e.printStackTrace();
		resultDTO.setResultMsg("Internal Error Occured..!");
		resultDTO.setResultStatus(false);
	}
		return resultDTO;
	}
		
	
	/**
	 * @param twitterDTO
	 * @return
	 */
		public ResultDTO addTwitterToken(TwitterDTO twitterDTO) {
		
		ResultDTO resultDTO = new ResultDTO();
		TwitterBL twitterBL = new TwitterBL();
		try {
			twitterDTO = twitterBL.addToken(twitterDTO);
			resultDTO.setTwitterDTO(twitterDTO);
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}
		
		/**
		 * @param twitterDTO
		 * @return
		 */
			public ResultDTO deleteTwitterToken(TwitterDTO twitterDTO) {
			
			ResultDTO resultDTO = new ResultDTO();
			TwitterBL twitterBL = new TwitterBL();
			try {
				twitterDTO = twitterBL.deleteToken(twitterDTO);
				resultDTO.setTwitterDTO(twitterDTO);
				resultDTO.setResultStatus(twitterDTO.isDeleteStatus());
			} catch (EventPortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultDTO.setResultMsg(e.getMessage());
				resultDTO.setResultStatus(false);
			} catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");
				resultDTO.setResultStatus(false);
			}
			
			return resultDTO;
		}
		
		public ResultDTO searchTwitterTweets(TwitterDTO twitterDTO) {
			ResultDTO resultDTO = new ResultDTO();		
			TwitterBL twitterBL = new TwitterBL();
			List tweetList= new ArrayList();
			String searchTweet = null;
			AccessToken accessToken = null;
			String userTimeZone = null;
			try {
				searchTweet = twitterDTO.getSearchTweet();
				accessToken = (AccessToken) twitterDTO.getAccessToken();
				userTimeZone = twitterDTO.getUserTimeZone();
			
				tweetList = twitterBL.searchTweets(searchTweet, accessToken,userTimeZone,twitterDTO);
				
				if(!tweetList.isEmpty()){
					twitterDTO.setTweetList(tweetList);
					resultDTO.setTwitterDTO(twitterDTO);
					resultDTO.setResultStatus(true);
			    }
				
				//throw new TwitterServiceUnavailableException("Unable to connect Twitter at this time. Try after sometime...");
			
			} catch (EventPortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultDTO.setResultMsg(e.getMessage());
				resultDTO.setResultStatus(false);
			} catch (Exception e){
				e.printStackTrace();
				resultDTO.setResultMsg("Internal Error Occured..!");
				resultDTO.setResultStatus(false);
			}
			return resultDTO;
		}
	
		
  public ResultDTO addLinkedInToken(LinkedInDTO linkedInDTO) {
		
		ResultDTO resultDTO = new ResultDTO();
		LinkedInBL linkedInBL = new LinkedInBL();

		try {
			
			linkedInDTO = linkedInBL.addToken(linkedInDTO);
			resultDTO.setLinkedInDTO(linkedInDTO);
			
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}
  
  
  public ResultDTO addFaceBookToken(FaceBookDTO faceBookDTO) {
		
		ResultDTO resultDTO = new ResultDTO();
		FaceBookBL faceBookBL = new FaceBookBL();

		try {
			
			faceBookDTO = faceBookBL.addToken(faceBookDTO);
			resultDTO.setFaceBookDTO(faceBookDTO);
			
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}
  
	/**
	 * @param twitterDTO
	 * @return
	 */
		public ResultDTO deleteLinkedInToken(LinkedInDTO linkedInDTO) {
		
		ResultDTO resultDTO = new ResultDTO();
		LinkedInBL linkedInBL = new LinkedInBL();
		
		try {
			linkedInDTO = linkedInBL.deleteToken(linkedInDTO);
			
			resultDTO.setResultStatus(linkedInDTO.isDeleteStatus());
			
			resultDTO.setLinkedInDTO(linkedInDTO);
			
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}


	public ResultDTO deleteFaceBookToken(FaceBookDTO faceBookDTO) {
		
		ResultDTO resultDTO = new ResultDTO();
		FaceBookBL faceBookBL = new FaceBookBL();

		try {
			
			faceBookDTO = faceBookBL.deleteToken(faceBookDTO);
			resultDTO.setFaceBookDTO(faceBookDTO);
			resultDTO.setResultStatus(faceBookDTO.isDeleteStatus());
		} catch (EventPortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDTO.setResultMsg(e.getMessage());
			resultDTO.setResultStatus(false);
		} catch (Exception e){
			e.printStackTrace();
			resultDTO.setResultMsg("Internal Error Occured..!");
			resultDTO.setResultStatus(false);
		}
		
		return resultDTO;
	}
	
	
	public ResultDTO getTwitterProfileImageURL(TwitterDTO twitterDTO) {
		ResultDTO resultDTO = new ResultDTO();		
		TwitterBL twitterBL = new TwitterBL();		
		AccessToken accessToken = null;
		String profileId = null;
		
		profileId = twitterDTO.getProfileId();
		try {			
			accessToken = (AccessToken) twitterDTO.getAccessToken();
			twitterDTO = twitterBL.getProfileImageURL(accessToken);			
			resultDTO.setTwitterDTO(twitterDTO);
			
		} catch (BaseAppException e) {			
			e.printStackTrace();
		}
		return resultDTO;
	}
	public ResultDTO  publicProfile(SocialMediaDTO socialMediaDTO){
		
		ResultDTO resultDTO = new ResultDTO();		
		TwitterBL twitterBL = new TwitterBL();		
		LinkedInBL linkedInBL = new LinkedInBL();
		FaceBookBL faceBookBL = new FaceBookBL();
		TwitterDTO twitterDTO = new TwitterDTO();	
		LinkedInDTO linkedInDTO = new LinkedInDTO();
		FaceBookDTO faceBookDTO = new FaceBookDTO();
		if(socialMediaDTO.getTwitterDTO()!=null){
		twitterDTO = socialMediaDTO.getTwitterDTO();
		try {
			twitterDTO = twitterBL.publicProfile(twitterDTO);
		} catch (BaseAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultDTO.setTwitterDTO(twitterDTO);	
		}
		if(socialMediaDTO.getLinkedInDTO()!=null){
		linkedInDTO =socialMediaDTO.getLinkedInDTO();
		linkedInDTO = linkedInBL.publicProfile(linkedInDTO);
		resultDTO.setLinkedInDTO(linkedInDTO);
		}
		if(socialMediaDTO.getFaceBookDTO()!=null){
		faceBookDTO = socialMediaDTO.getFaceBookDTO();
		faceBookDTO = faceBookBL.publicProfile(faceBookDTO);
		resultDTO.setFaceBookDTO(faceBookDTO);
		}		
				
		return resultDTO;
	}
public ResultDTO  checkAlreadyFriend(SocialMediaDTO socialMediaDTO){
		
		ResultDTO resultDTO = new ResultDTO();		
		TwitterBL twitterBL = new TwitterBL();		
		LinkedInBL linkedInBL = new LinkedInBL();
		FaceBookBL faceBookBL = new FaceBookBL();
		TwitterDTO twitterDTO = new TwitterDTO();	
		LinkedInDTO linkedInDTO = new LinkedInDTO();
		FaceBookDTO faceBookDTO = new FaceBookDTO();
		if(socialMediaDTO.getTwitterDTO()!=null){
		twitterDTO = socialMediaDTO.getTwitterDTO();
	    twitterDTO = twitterBL.checkAlreadyFriend(twitterDTO);
		resultDTO.setTwitterDTO(twitterDTO);	
		}
		if(socialMediaDTO.getLinkedInDTO()!=null){
		linkedInDTO =socialMediaDTO.getLinkedInDTO();
		linkedInDTO = linkedInBL.checkAlreadyFriend(linkedInDTO);
		if(linkedInDTO.isAlreadyFriends()==false){
		SocialMedia socialMedia = new SocialMedia();
		socialMedia.setSmiFromProfileId(socialMediaDTO.getProfileId());
		socialMedia.setSmiToProfileId(socialMediaDTO.getProfileIdToConnect());
		try {
		 //boolean updatedStatus = socialMedia.updateLinkedInConnectionStatus(socialMedia);	
			Collection coll = socialMedia.readSMInvite(socialMedia);
			if (coll != null) {
				Iterator iter = coll.iterator();
				while (iter.hasNext()) {
					socialMedia = (SocialMedia) iter.next();
					if(socialMedia.getSmiLinkedInConnectStatus()!=null && socialMedia.getSmiLinkedInConnectStatus().equals("Y")){
						linkedInDTO.setAlreadyInvited(true);	
					}else{
						linkedInDTO.setAlreadyInvited(false);
					}
				}
			}
			
			Collection collec = socialMedia.checkRequestPendingInLinkedIn(socialMedia);
			if (collec != null) {
				Iterator iter = collec.iterator();
				while (iter.hasNext()) {
					socialMedia = (SocialMedia) iter.next();
					if(socialMedia.getSmiLinkedInConnectStatus()!=null && socialMedia.getSmiLinkedInConnectStatus().equals("Y")){
						linkedInDTO.setRequestPending(true);	
					}else{
						linkedInDTO.setRequestPending(false);	
					}
				}
			}
			
		} catch (EventPortalException e) {		
			e.printStackTrace();
		}
		}
		resultDTO.setLinkedInDTO(linkedInDTO);
		}
		
		if(socialMediaDTO.getFaceBookDTO()!=null){
		faceBookDTO = socialMediaDTO.getFaceBookDTO();
	faceBookDTO = faceBookBL.checkAlreadyFriend(faceBookDTO);
	faceBookDTO = faceBookBL.inviteFriend(faceBookDTO);
		resultDTO.setFaceBookDTO(faceBookDTO);
		}	
				
		return resultDTO;
	}
public ResultDTO  inviteFriend(SocialMediaDTO socialMediaDTO){
	
	ResultDTO resultDTO = new ResultDTO();		
	DateUtility dateUtility = new DateUtility();
	TwitterBL twitterBL = new TwitterBL();		
	LinkedInBL linkedInBL = new LinkedInBL();
	FaceBookBL faceBookBL = new FaceBookBL();
	TwitterDTO twitterDTO = new TwitterDTO();	
	LinkedInDTO linkedInDTO = new LinkedInDTO();
	FaceBookDTO faceBookDTO = new FaceBookDTO();
	SocialMedia socialMedia= new SocialMedia();
	String todayDate = null;
	try {
		if(socialMediaDTO.getConnectedTimeZone()!=null){			
		todayDate =dateUtility.getTodaysDateInUserTimeZone(socialMediaDTO.getConnectedTimeZone());
		}
		
	if(socialMediaDTO.getTwitterDTO()!=null){
		
	twitterDTO = socialMediaDTO.getTwitterDTO();
    twitterDTO = twitterBL.inviteFriend(twitterDTO);
	resultDTO.setTwitterDTO(twitterDTO);	
	if(resultDTO.getTwitterDTO()!=null){
		if(resultDTO.getTwitterDTO().isFriendInvited()){
			socialMedia.setSmiTwitterConnectStatus("Y");
			socialMedia.setSmiTwitterConnectDate(todayDate);
		}
	}else{
			socialMedia.setSmiTwitterConnectStatus("N");
	}
	}
	
	if(socialMediaDTO.getLinkedInDTO()!=null){
	linkedInDTO =socialMediaDTO.getLinkedInDTO();
	linkedInDTO = linkedInBL.inviteFriend(linkedInDTO);
	resultDTO.setLinkedInDTO(linkedInDTO);
	if(resultDTO.getLinkedInDTO()!=null){
		if(resultDTO.getLinkedInDTO().isInviteFriend()){
			socialMedia.setSmiLinkedInConnectStatus("Y");
			socialMedia.setSmiLinkedInConnectDate(todayDate);
		}
	}else{
			socialMedia.setSmiLinkedInConnectStatus("N");
	}	
	}	
	
	
	/*if(socialMediaDTO.getFaceBookDTO()!=null){
	faceBookDTO = socialMediaDTO.getFaceBookDTO();
   //faceBookDTO = faceBookBL.inviteFriend(faceBookDTO);
	resultDTO.setFaceBookDTO(faceBookDTO);
	if(resultDTO.getFaceBookDTO()!=null){
		if(resultDTO.getFaceBookDTO().isInviteFriend()){
			socialMedia.setSmiFacebookConnectStatus("Y");
			socialMedia.setSmiFacebookConnectDate(todayDate);
		}
	}else{
			socialMedia.setSmiFacebookConnectStatus("N");
	}
	}*/
	
	

	
	
	} catch (LinkedInAlreadyInvitedException e) {		
		e.printStackTrace();	
		socialMedia.setSmiLinkedInConnectStatus("N");
		resultDTO.setLinkedInDTO(linkedInDTO);	
		resultDTO.setResultStatus(false);
	}catch (EventPortalException e) {		
		e.printStackTrace();
		resultDTO.setResultMsg(e.getMessage());
		resultDTO.setResultStatus(false);
	} catch (Exception e){
		e.printStackTrace();		
		resultDTO.setResultMsg("Internal Error Occured..!");
		resultDTO.setResultStatus(false);
	}
	

	boolean saveInviteStatus = false;
	try {

		socialMedia.setSmiFromProfileId(socialMediaDTO.getProfileId());
		socialMedia.setSmiToProfileId(socialMediaDTO.getProfileIdToConnect());		
		if(socialMedia.getSmiFromProfileId()!=null){
		saveInviteStatus = socialMedia.saveSMInvite(socialMedia);
		}
		System.out.println("saveInviteStatus >>"+saveInviteStatus);
		
	} catch (EventPortalException e) {	
		e.printStackTrace();
	}

	
	return resultDTO;	
}

//Refined Methods

public ResultDTO getSocialMediaImageURL(SocialMediaDTO socialMediaDTO){
	ResultDTO resultDTO = null;
	String profileId = null;
	
	try{
		resultDTO = new ResultDTO();
		profileId = socialMediaDTO.getProfileId();
		
		socialMediaDTO = getProfileSocialMediaData(profileId);
			
		resultDTO.setSocialMediaDTO(socialMediaDTO);
		
	}catch (EventPortalException e) {		
		e.printStackTrace();
		resultDTO.setResultMsg(e.getMessage());
		resultDTO.setResultStatus(false);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	return resultDTO;
}


public ResultDTO getSocialMediaProfileData(SocialMediaDTO socialMediaDTO){
	ResultDTO resultDTO = null;
	String profileId = null;
	
	try{
		resultDTO = new ResultDTO();
		profileId = socialMediaDTO.getProfileId();
		
		socialMediaDTO = getProfileSocialMediaData(profileId);
			
		resultDTO.setSocialMediaDTO(socialMediaDTO);
		
	}catch (EventPortalException e) {		
		e.printStackTrace();
		resultDTO.setResultMsg(e.getMessage());
		resultDTO.setResultStatus(false);
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	return resultDTO;
}


public SocialMediaDTO getProfileSocialMediaData(String profileId) throws EventPortalException{
	
	List socialMediaProfileList = null; 
	SocialMedia socialMedia = null;
	SocialMediaDTO socialMediaDTO = null;
	
	socialMedia = new SocialMedia();
	
	socialMedia.setSmProfileId(profileId);
	
	socialMediaProfileList = (ArrayList)socialMedia.getProfileSocialMediaData(socialMedia);
	
	if(socialMediaProfileList != null){
		
		socialMedia = (SocialMedia)socialMediaProfileList.get(0);
		
	}
	
	socialMediaDTO = new SocialMediaDTO();
	
	socialMediaDTO = convertSocialMediaProfileToSocialMediaProfileDTO(socialMedia, socialMediaDTO);
	
	return socialMediaDTO;
}


private SocialMediaDTO convertSocialMediaProfileToSocialMediaProfileDTO(
		SocialMedia socialMedia, SocialMediaDTO socialMediaDTO) {
	
		if(socialMediaDTO == null){
			socialMediaDTO = new SocialMediaDTO();
		}
	
		if(socialMedia.getTwitterId()!= null){
			socialMediaDTO.setTwitterId(socialMedia.getTwitterId());
		}
		
		if(socialMedia.getTwitterImgUrl() != null){
			socialMediaDTO.setTwitterImgUrl(socialMedia.getTwitterImgUrl());
		}
		
		if(socialMedia.getTwitterProfileUrl() != null){
			socialMediaDTO.setTwitterProfileUrl(socialMedia.getTwitterProfileUrl());
		}

		if (socialMedia.getTwtShowTweets()!=null) {
			socialMediaDTO.setTwtShowTweets(socialMedia.getTwtShowTweets());
		}
		if (socialMedia.getTwtAllowFriends()!=null) {
			
			if(socialMedia.getTwtAllowFriends().equals("N")){
				socialMediaDTO.setTwitterFollow("false");
			}else{
				socialMediaDTO.setTwitterFollow("true");
			}
			
			socialMediaDTO.setTwtAllowFriends(socialMedia.getTwtAllowFriends());
		}else{
			socialMediaDTO.setTwitterFollow("false");
		}
		
		if(socialMedia.getFaceBookId() != null){
			socialMediaDTO.setFaceBookId(socialMedia.getFaceBookId());
		}
		
		if(socialMedia.getFaceBookImgUrl() != null){
			socialMediaDTO.setFaceBookImgUrl(socialMedia.getFaceBookImgUrl());
		}
		
		if(socialMedia.getFaceBookProfileUrl() != null){
			socialMediaDTO.setFaceBookProfileUrl(socialMedia.getFaceBookProfileUrl());
		}
		
		if (socialMedia.getFbAllowFriendsToPost()!=null) {
			socialMediaDTO.setFbAllowFriendsToPost(socialMedia.getFbAllowFriendsToPost());
		}
		if (socialMedia.getFbAllowFriendsToConnect()!=null) {
			if(socialMedia.getFbAllowFriendsToConnect().equals("N")){
				socialMediaDTO.setFaceBookFriendsConnect("false");
			}else{
				socialMediaDTO.setFaceBookFriendsConnect("true");
			}
			socialMediaDTO.setFbAllowFriendsToConnect(socialMedia.getFbAllowFriendsToConnect());
		}else{
			socialMediaDTO.setFaceBookFriendsConnect("false");
		}
		
		if(socialMedia.getLinkedInId() != null){
			socialMediaDTO.setLinkedInId(socialMedia.getLinkedInId());
		}
		
		if(socialMedia.getLinkedInImgUrl() != null){
			socialMediaDTO.setLinkedInImgUrl(socialMedia.getLinkedInImgUrl());
		}
		
		if(socialMedia.getLinkedInProfileUrl() != null){
			socialMediaDTO.setLinkedInProfileUrl(socialMedia.getLinkedInProfileUrl());
		}
		
		if (socialMedia.getLiAllowFriendsToFollow()!=null) {
			if(socialMedia.getLiAllowFriendsToFollow().equals("N")){
				socialMediaDTO.setLinkedInFriendsConnect("false");
			}else{
				socialMediaDTO.setLinkedInFriendsConnect("true");
			}
			socialMediaDTO.setLiAllowFriendsToFollow(socialMedia.getLiAllowFriendsToFollow());
		}else{
			socialMediaDTO.setLinkedInFriendsConnect("false");
		}
		if (socialMedia.getLiAllowFriendsToPost()!=null) {
			socialMediaDTO.setLiAllowFriendsToPost(socialMedia.getLiAllowFriendsToPost());
		}

		return socialMediaDTO;
}

	public boolean updateSocialMediaProfileSettings(SocialMediaDTO socialMediaDTO) throws EventPortalException{
	boolean updateResult = false;
	SocialMedia SocialMedia = null;
	
	SocialMedia = new SocialMedia();
	if(socialMediaDTO!=null){
		SocialMedia = convertSocialMediaDTOToSocialMedia(socialMediaDTO,SocialMedia);			
		updateResult = SocialMedia.updateSocialMediaProfileSettings(SocialMedia);
	}
	return updateResult;
}


	private SocialMedia convertSocialMediaDTOToSocialMedia(
			SocialMediaDTO socialMediaDTO, SocialMedia socialMedia) {
	
		if(socialMedia == null){
			socialMedia = new SocialMedia();
		}
	
		if(socialMediaDTO.getProfileId() != null ){
			socialMedia.setSmProfileId(socialMediaDTO.getProfileId());
		}
		
		if(socialMediaDTO.getTwitterId()!= null){
			socialMedia.setTwitterId(socialMediaDTO.getTwitterId());
		}
		
		if(socialMediaDTO.getTwitterImgUrl() != null){
			socialMedia.setTwitterImgUrl(socialMediaDTO.getTwitterImgUrl());
		}
		
		if(socialMediaDTO.getTwitterProfileUrl() != null){
			socialMedia.setTwitterProfileUrl(socialMediaDTO.getTwitterProfileUrl());
		}

		if (socialMediaDTO.getTwtShowTweets()!=null) {
			socialMedia.setTwtShowTweets(socialMediaDTO.getTwtShowTweets());
		}
		if (socialMediaDTO.getTwtAllowFriends()!=null) {
			socialMedia.setTwtAllowFriends(socialMediaDTO.getTwtAllowFriends());
		}
		
		if(socialMediaDTO.getFaceBookId() != null){
			socialMedia.setFaceBookId(socialMediaDTO.getFaceBookId());
		}
		
		if(socialMediaDTO.getFaceBookImgUrl() != null){
			socialMedia.setFaceBookImgUrl(socialMediaDTO.getFaceBookImgUrl());
		}
		
		if(socialMediaDTO.getFaceBookProfileUrl() != null){
			socialMedia.setFaceBookProfileUrl(socialMediaDTO.getFaceBookProfileUrl());
		}
		
		if (socialMediaDTO.getFbAllowFriendsToPost()!=null) {
			socialMedia.setFbAllowFriendsToPost(socialMediaDTO.getFbAllowFriendsToPost());
		}
		if (socialMediaDTO.getFbAllowFriendsToConnect()!=null) {
			socialMedia.setFbAllowFriendsToConnect(socialMediaDTO.getFbAllowFriendsToConnect());
		}
		
		if(socialMediaDTO.getLinkedInId() != null){
			socialMedia.setLinkedInId(socialMediaDTO.getLinkedInId());
		}
		
		if(socialMediaDTO.getLinkedInImgUrl() != null){
			socialMedia.setLinkedInImgUrl(socialMediaDTO.getLinkedInImgUrl());
		}
		
		if(socialMediaDTO.getLinkedInProfileUrl() != null){
			socialMedia.setLinkedInProfileUrl(socialMediaDTO.getLinkedInProfileUrl());
		}
		
		if (socialMediaDTO.getLiAllowFriendsToFollow()!=null) {
			socialMedia.setLiAllowFriendsToFollow(socialMediaDTO.getLiAllowFriendsToFollow());
		}
		if (socialMediaDTO.getLiAllowFriendsToPost()!=null) {
			socialMedia.setLiAllowFriendsToPost(socialMediaDTO.getLiAllowFriendsToPost());
		}
		

		return socialMedia;
}




	
}
