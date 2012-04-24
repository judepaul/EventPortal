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
 * Oct 21, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.dto;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 21, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class SocialMediaDTO extends DataTransferObject {

	private String profileId = null;
	private String profileIdToConnect = null;
	private String connectedTimeZone = null;
	private String adminProfileId = null;
	private String baseURL = null;
	
	private TwitterDTO twitterDTO = null;
	
	private LinkedInDTO linkedInDTO = null;
	
	private FaceBookDTO faceBookDTO = null;
	
	private String twitterId = null;
	private String faceBookId = null;
	private String linkedInId = null;

	private String twtShowTweets = null;
	private String twtAllowFriends = null;
	private String fbAllowFriendsToPost = null;
	private String fbAllowFriendsToConnect = null;
	private String liAllowFriendsToFollow = null;
	private String liAllowFriendsToPost = null;
	
	private String twitterProfileUrl = null;
	private String faceBookProfileUrl = null;
	private String linkedInProfileUrl = null;	
	private String twitterImgUrl = null;
	private String faceBookImgUrl = null;
	private String linkedInImgUrl = null;
	
	private String twitterFollow = null;
	
	private String faceBookFriendsConnect = null;
	
	private String linkedInFriendsConnect = null;
	
	public String getFaceBookFriendsConnect() {
		return faceBookFriendsConnect;
	}

	public void setFaceBookFriendsConnect(String faceBookFriendsConnect) {
		this.faceBookFriendsConnect = faceBookFriendsConnect;
	}

	public String getLinkedInFriendsConnect() {
		return linkedInFriendsConnect;
	}

	public void setLinkedInFriendsConnect(String linkedInFriendsConnect) {
		this.linkedInFriendsConnect = linkedInFriendsConnect;
	}

	public String getTwitterFollow() {
		return twitterFollow;
	}

	public void setTwitterFollow(String twitterFollow) {
		this.twitterFollow = twitterFollow;
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

	public TwitterDTO getTwitterDTO() {
		return twitterDTO;
	}

	public void setTwitterDTO(TwitterDTO twitterDTO) {
		this.twitterDTO = twitterDTO;
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

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getAdminProfileId() {
		return adminProfileId;
	}

	public void setAdminProfileId(String adminProfileId) {
		this.adminProfileId = adminProfileId;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
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

	public String getProfileIdToConnect() {
		return profileIdToConnect;
	}

	public void setProfileIdToConnect(String profileIdToConnect) {
		this.profileIdToConnect = profileIdToConnect;
	}

	public String getConnectedTimeZone() {
		return connectedTimeZone;
	}

	public void setConnectedTimeZone(String connectedTimeZone) {
		this.connectedTimeZone = connectedTimeZone;
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

	
	
	
}
