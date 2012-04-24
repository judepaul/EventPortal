package com.eventattend.portal.bo;

import java.util.ArrayList;
import java.util.Collection;

import com.eventattend.portal.exceptions.EventPortalException;

public class SocialMedia extends BusinessObject {

	private String hiddenAction = null;
	private String smiFromProfileId = null;
	private String smiToProfileId = null;
	private String smiTwitterConnectStatus = null;
	private String smiFacebookConnectStatus = null;
	private String smiLinkedInConnectStatus = null;
	private String smiTwitterConnectDate  = null;
	private String smiFacebookConnectDate = null;
	private String smiLinkedInConnectDate = null;
	
	private String smProfileId = null;
	
	private String twtShowTweets = null;
	private String twtAllowFriends = null;
	private String fbAllowFriendsToPost = null;
	private String fbAllowFriendsToConnect = null;
	private String liAllowFriendsToFollow = null;
	private String liAllowFriendsToPost = null;
	
	private String twitterId = null;
	private String faceBookId = null;
	private String linkedInId = null;
	
	private String twitterProfileUrl = null;
	private String faceBookProfileUrl = null;
	private String linkedInProfileUrl = null;	
	private String twitterImgUrl = null;
	private String faceBookImgUrl = null;
	private String linkedInImgUrl = null;

	
	public String getHiddenAction() {
		return hiddenAction;
	}


	public String getSmiFromProfileId() {
		return smiFromProfileId;
	}


	public void setSmiFromProfileId(String smiFromProfileId) {
		this.smiFromProfileId = smiFromProfileId;
	}


	public String getSmiToProfileId() {
		return smiToProfileId;
	}


	public void setSmiToProfileId(String smiToProfileId) {
		this.smiToProfileId = smiToProfileId;
	}


	public String getSmiTwitterConnectStatus() {
		return smiTwitterConnectStatus;
	}


	public void setSmiTwitterConnectStatus(String smiTwitterConnectStatus) {
		this.smiTwitterConnectStatus = smiTwitterConnectStatus;
	}


	public String getSmiFacebookConnectStatus() {
		return smiFacebookConnectStatus;
	}


	public void setSmiFacebookConnectStatus(String smiFacebookConnectStatus) {
		this.smiFacebookConnectStatus = smiFacebookConnectStatus;
	}


	public String getSmiLinkedInConnectStatus() {
		return smiLinkedInConnectStatus;
	}


	public void setSmiLinkedInConnectStatus(String smiLinkedInConnectStatus) {
		this.smiLinkedInConnectStatus = smiLinkedInConnectStatus;
	}


	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}


	public String getSmProfileId() {
		return smProfileId;
	}


	public void setSmProfileId(String smProfileId) {
		this.smProfileId = smProfileId;
	}


	public String getTwtShowTweets() {
		return twtShowTweets;
	}


	public void setTwtShowTweets(String twtShowTweets) {
		this.twtShowTweets = twtShowTweets;
	}


	public String getTwtAllowFriends() {
		return twtAllowFriends;
	}


	public void setTwtAllowFriends(String twtAllowFriends) {
		this.twtAllowFriends = twtAllowFriends;
	}


	public String getFbAllowFriendsToPost() {
		return fbAllowFriendsToPost;
	}


	public void setFbAllowFriendsToPost(String fbAllowFriendsToPost) {
		this.fbAllowFriendsToPost = fbAllowFriendsToPost;
	}


	public String getFbAllowFriendsToConnect() {
		return fbAllowFriendsToConnect;
	}


	public void setFbAllowFriendsToConnect(String fbAllowFriendsToConnect) {
		this.fbAllowFriendsToConnect = fbAllowFriendsToConnect;
	}


	public String getLiAllowFriendsToFollow() {
		return liAllowFriendsToFollow;
	}


	public void setLiAllowFriendsToFollow(String liAllowFriendsToFollow) {
		this.liAllowFriendsToFollow = liAllowFriendsToFollow;
	}


	public String getLiAllowFriendsToPost() {
		return liAllowFriendsToPost;
	}


	public void setLiAllowFriendsToPost(String liAllowFriendsToPost) {
		this.liAllowFriendsToPost = liAllowFriendsToPost;
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


	public String getTwitterProfileUrl() {
		return twitterProfileUrl;
	}


	public void setTwitterProfileUrl(String twitterProfileUrl) {
		this.twitterProfileUrl = twitterProfileUrl;
	}


	public String getFaceBookProfileUrl() {
		return faceBookProfileUrl;
	}


	public void setFaceBookProfileUrl(String faceBookProfileUrl) {
		this.faceBookProfileUrl = faceBookProfileUrl;
	}


	public String getLinkedInProfileUrl() {
		return linkedInProfileUrl;
	}


	public void setLinkedInProfileUrl(String linkedInProfileUrl) {
		this.linkedInProfileUrl = linkedInProfileUrl;
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


	/**
	 * 
	 * @param socialMedia
	 * @return
	 * @throws EventPortalException
	 */
	public boolean saveSMInvite(SocialMedia  socialMedia)throws EventPortalException {
		socialMedia.setHiddenAction("saveSMInvite");
		boolean resultStatus = socialMedia.save();
		return resultStatus;
	}
	
	public boolean updateLinkedInConnectionStatus(SocialMedia  socialMedia)throws EventPortalException {
		socialMedia.setHiddenAction("updateLinkedInConnectionStatus");
		boolean resultStatus = socialMedia.save();
		return resultStatus;
	}
	
	public Collection readSMInvite(SocialMedia  socialMedia) throws EventPortalException {
		socialMedia.setHiddenAction("readSMInvite");
		Collection sMInviteList = socialMedia.read();
		return sMInviteList;
	}  

	public Collection checkRequestPendingInLinkedIn(SocialMedia  socialMedia) throws EventPortalException {
		socialMedia.setHiddenAction("checkRequestPendingInLinkedIn");
		Collection sMInviteList = socialMedia.read();
		return sMInviteList;
	}  
	public boolean save() throws EventPortalException {
		return getDao().save(this);
	}


	public String getSmiTwitterConnectDate() {
		return smiTwitterConnectDate;
	}


	public void setSmiTwitterConnectDate(String smiTwitterConnectDate) {
		this.smiTwitterConnectDate = smiTwitterConnectDate;
	}


	public String getSmiFacebookConnectDate() {
		return smiFacebookConnectDate;
	}


	public void setSmiFacebookConnectDate(String smiFacebookConnectDate) {
		this.smiFacebookConnectDate = smiFacebookConnectDate;
	}


	public String getSmiLinkedInConnectDate() {
		return smiLinkedInConnectDate;
	}


	public void setSmiLinkedInConnectDate(String smiLinkedInConnectDate) {
		this.smiLinkedInConnectDate = smiLinkedInConnectDate;
	}


	public Collection getProfileSocialMediaData(SocialMedia socialMedia) throws EventPortalException {
		
		socialMedia.setHiddenAction("SOCIALMEDIAPROFILEDATA");
		
		return socialMedia.read();
	}


	public boolean updateSocialMediaProfileSettings(SocialMedia socialMedia) throws EventPortalException {		
		socialMedia.setHiddenAction("SOCIALMEDIAPROFILEUPDATE");		
		return socialMedia.update();
	}
	
	
	public boolean updateSMProfile(SocialMedia socialMedia) throws EventPortalException {		
		socialMedia.setHiddenAction("updateSMProfile");		
		return socialMedia.update();
	}
	
	public boolean updateSocialMediaProfile(SocialMedia socialMedia) throws EventPortalException{
		socialMedia.setHiddenAction("SOCIALMEDIAPROFILEUPDATE");
		  
		  return socialMedia.update();
		 }
	
	
	
}
