package com.eventattend.portal.model.db4o;

public class SMProfilePOJO {
	
	private String smpId;
	private String smpTwitterId;
	private String smpFacebookId;
	private String smpLinkedInId;
	private String smpTwitterImgUrl;
	private String smpFacebookImgUrl;
	private String smpLinkedInImgUrl;
	private String smpTwitterProfileUrl;
	private String smpFaceBookProfileUrl;
	private String smpLinkedInProfileUrl;
	private String smpProfileId;	
	
	private String twtShowTweets = null;
	private String twtAllowFriends = null;
	private String fbAllowFriendsToPost = null;
	private String fbAllowFriendsToConnect = null;
	private String liAllowFriendsToFollow = null;
	private String liAllowFriendsToPost = null;

	
	public SMProfilePOJO() {
	}

	public SMProfilePOJO(String smpProfileId) {
		this.smpProfileId = smpProfileId;
	}
	
	public String getSmpId() {
		return smpId;
	}

	public void setSmpId(String smpId) {
		this.smpId = smpId;
	}

	public String getSmpTwitterId() {
		return smpTwitterId;
	}

	public void setSmpTwitterId(String smpTwitterId) {
		this.smpTwitterId = smpTwitterId;
	}

	public String getSmpFacebookId() {
		return smpFacebookId;
	}

	public void setSmpFacebookId(String smpFacebookId) {
		this.smpFacebookId = smpFacebookId;
	}

	public String getSmpLinkedInId() {
		return smpLinkedInId;
	}

	public void setSmpLinkedInId(String smpLinkedInId) {
		this.smpLinkedInId = smpLinkedInId;
	}

	public String getSmpTwitterImgUrl() {
		return smpTwitterImgUrl;
	}

	public void setSmpTwitterImgUrl(String smpTwitterImgUrl) {
		this.smpTwitterImgUrl = smpTwitterImgUrl;
	}

	public String getSmpFacebookImgUrl() {
		return smpFacebookImgUrl;
	}

	public void setSmpFacebookImgUrl(String smpFacebookImgUrl) {
		this.smpFacebookImgUrl = smpFacebookImgUrl;
	}

	public String getSmpLinkedInImgUrl() {
		return smpLinkedInImgUrl;
	}

	public void setSmpLinkedInImgUrl(String smpLinkedInImgUrl) {
		this.smpLinkedInImgUrl = smpLinkedInImgUrl;
	}

	public String getSmpProfileId() {
		return smpProfileId;
	}

	public void setSmpProfileId(String smpProfileId) {
		this.smpProfileId = smpProfileId;
	}

	public String getSmpTwitterProfileUrl() {
		return smpTwitterProfileUrl;
	}

	public void setSmpTwitterProfileUrl(String smpTwitterProfileUrl) {
		this.smpTwitterProfileUrl = smpTwitterProfileUrl;
	}

	public String getSmpFaceBookProfileUrl() {
		return smpFaceBookProfileUrl;
	}

	public void setSmpFaceBookProfileUrl(String smpFaceBookProfileUrl) {
		this.smpFaceBookProfileUrl = smpFaceBookProfileUrl;
	}

	public String getSmpLinkedInProfileUrl() {
		return smpLinkedInProfileUrl;
	}

	public void setSmpLinkedInProfileUrl(String smpLinkedInProfileUrl) {
		this.smpLinkedInProfileUrl = smpLinkedInProfileUrl;
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


}
