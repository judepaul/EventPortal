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
 * March 1, 2011 veeresh created the file.
 * 
 */
package com.eventattend.portal.bo;

import java.util.Collection;
import java.util.List;

import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.exceptions.EventPortalException;

/**
 * Class Description
 * 
 * @version beta
 * @author Veeresh
 * @Date March 1, 2011
 * 
 *       Copyright 2009-2010 Seedcube LLC. All Rights Reserved. This
 *       software is the proprietary information of Seedcube LLC. Use is
 *       subject to license terms.
 */

public class Profile extends BusinessObject {
	
	/*Property to store the First name */
	private String firstName;
	/*Property to store the Last name */
	private String lastName;
	/*Property to store the Password */
	private String password;
	/*Property to store the Email */
	private String eMail;
	/*Property to store the Mobile */
	private String mobile;
	/*Property to store the LandHome number */
	private String landHome;
	/*Property to store the LandOffice number */
	private String landOffice;
	/*Property to store the Address */
	private String address;
	/*Property to store the City */
	private String city;
	/*Property to store the Zip code */
	private String zip;
	/*Property to store the State */
	private String state;
	/*Property to store the Country */
	private String country;
	/*Property to store the Education */
	private String education;
	/*Property to store the Occupation */
	private String occupation;
	/*Property to store the Web site */
	private String website;
	/*Property to store the First name */
	private String hiddenAction;
	/*Property to store the Profile Id */
	private String profileId;
	/*Property to store the profile Picture */
	private String profilePicture;
	/*Property to store the Email */
	private String alternativeEmail;
	/*Property to store the gender */
	private String gender;
	/*Property to store the new Password */
	private String newPassword;
	/*Property to store the result Check */
	private String profileEmail;
	
	private String twitterId = null;
	private String faceBookId = null;
	private String linkedInId = null;
	
	private String twitterProfileUrl = null;
	private String faceBookProfileUrl = null;
	private String linkedInProfileUrl = null;	
	private String twitterImgUrl = null;
	private String faceBookImgUrl = null;
	private String linkedInImgUrl = null;
	private String hiddenObject = null;
	
	private boolean resultCheck;
	
	private boolean isSpeaker = false;
	private String speakerFor = null;
	
	// To check whether SM has images or not
	private String smpTwitterImgUrl;
	private String smpFacebookImgUrl;
	private String smpLinkedInImgUrl;
	
	private boolean chkImageFromLocal;
	private String twitterCheck = null;
	private String facebookCheck = null;
	
	private String profileMailFromId = null;
	private String profileMailToId = null;
	private String profileMailContent = null;
	private int profileMailCount = 0;

	private boolean isProfileImgUpdate;
	private String extProfileImgLocation;
	
	private String twtShowTweets = null;
	private String twtAllowFriends = null;
	private String fbAllowFriendsToPost = null;
	private String fbAllowFriendsToConnect = null;
	private String liAllowFriendsToFollow = null;
	private String liAllowFriendsToPost = null;
	
	private String timeZone = null;
	private String toProfileId = null;
	
	private boolean speakerCheckBox = false;
	private String eventOption = null;
	private String sessionOption = null;

	private String speakerKeyNotes = null;
	
	private String linkedinCheck = null;
	
	private String userId = null;
	
	private String imgLocation = null;
	
	private List profileIdList = null;
	
	private String joinedEventsName = null;
	
	private String attendedSessionsName = null;

	
	public String getAttendedSessionsName() {
		return attendedSessionsName;
	}
	public void setAttendedSessionsName(String attendedSessionsName) {
		this.attendedSessionsName = attendedSessionsName;
	}
	public String getJoinedEventsName() {
		return joinedEventsName;
	}
	public void setJoinedEventsName(String joinedEventsName) {
		this.joinedEventsName = joinedEventsName;
	}
	public String getImgLocation() {
		return imgLocation;
	}
	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProfileEmail() {
		return profileEmail;
	}
	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
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
	public String getHiddenObject() {
		return hiddenObject;
	}
	public void setHiddenObject(String hiddenObject) {
		this.hiddenObject = hiddenObject;
	}
	public String getTwitterCheck() {
		return twitterCheck;
	}
	public void setTwitterCheck(String twitterCheck) {
		this.twitterCheck = twitterCheck;
	}
	public String getFacebookCheck() {
		return facebookCheck;
	}
	public void setFacebookCheck(String facebookCheck) {
		this.facebookCheck = facebookCheck;
	}	
	public boolean isSpeaker() {
		return isSpeaker;
	}
	public void setSpeaker(boolean isSpeaker) {
		this.isSpeaker = isSpeaker;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLandHome() {
		return landHome;
	}
	public void setLandHome(String landHome) {
		this.landHome = landHome;
	}
	public String getLandOffice() {
		return landOffice;
	}
	public void setLandOffice(String landOffice) {
		this.landOffice = landOffice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getHiddenAction() {
		return hiddenAction;
	}
	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getAlternativeEmail() {
		return alternativeEmail;
	}
	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}	
	public boolean getResultCheck() {
		return resultCheck;
	}
	public void setResultCheck(boolean resultCheck) {
		this.resultCheck = resultCheck;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public boolean isProfileImgUpdate() {
		return isProfileImgUpdate;
	}
	public void setProfileImgUpdate(boolean isProfileImgUpdate) {
		this.isProfileImgUpdate = isProfileImgUpdate;
	}	
	public String getExtProfileImgLocation() {
		return extProfileImgLocation;
	}
	public void setExtProfileImgLocation(String extProfileImgLocation) {
		this.extProfileImgLocation = extProfileImgLocation;
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
	public boolean getChkImageFromLocal() {
		return chkImageFromLocal;
	}
	public void setChkImageFromLocal(boolean chkImageFromLocal) {
		this.chkImageFromLocal = chkImageFromLocal;
	}
		
	public String getProfileMailFromId() {
		return profileMailFromId;
	}
	public void setProfileMailFromId(String profileMailFromId) {
		this.profileMailFromId = profileMailFromId;
	}
	public String getProfileMailToId() {
		return profileMailToId;
	}
	public void setProfileMailToId(String profileMailToId) {
		this.profileMailToId = profileMailToId;
	}
	public String getProfileMailContent() {
		return profileMailContent;
	}
	public void setProfileMailContent(String profileMailContent) {
		this.profileMailContent = profileMailContent;
	}	
	public int getProfileMailCount() {
		return profileMailCount;
	}
	public void setProfileMailCount(int profileMailCount) {
		this.profileMailCount = profileMailCount;
	}	
	public String getLiAllowFriendsToPost() {
		return liAllowFriendsToPost;
	}
	public void setLiAllowFriendsToPost(String liAllowFriendsToPost) {
		this.liAllowFriendsToPost = liAllowFriendsToPost;
	}	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}	
	public String getToProfileId() {
		return toProfileId;
	}
	public void setToProfileId(String toProfileId) {
		this.toProfileId = toProfileId;
	}
		
	public boolean getSpeakerCheckBox() {
		return speakerCheckBox;
	}
	public void setSpeakerCheckBox(boolean speakerCheckBox) {
		this.speakerCheckBox = speakerCheckBox;
	}
	public String getEventOption() {
		return eventOption;
	}
	public void setEventOption(String eventOption) {
		this.eventOption = eventOption;
	}
	public String getSessionOption() {
		return sessionOption;
	}
	public void setSessionOption(String sessionOption) {
		this.sessionOption = sessionOption;
	}	
	public String getSpeakerKeyNotes() {
		return speakerKeyNotes;
	}
	public void setSpeakerKeyNotes(String speakerKeyNotes) {
		this.speakerKeyNotes = speakerKeyNotes;
	}
	public String isLinkedinCheck() {
		return linkedinCheck;
	}
	public void setLinkedinCheck(String linkedinCheck) {
		this.linkedinCheck = linkedinCheck;
	}
	
	public String getSpeakerFor() {
		return speakerFor;
	}
	public void setSpeakerFor(String speakerFor) {
		this.speakerFor = speakerFor;
	}
	
	
	public List getProfileIdList() {
		return profileIdList;
	}
	public void setProfileIdList(List profileIdList) {
		this.profileIdList = profileIdList;
	}
/*	*//**
	 * @method AccountSettingsList - To List User account settings
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public Collection accountSettingsList(Profile profile) throws EventPortalException {
		profile.setHiddenAction("AccountSettings");
		Collection AccountSettingsList = profile.read();
		return AccountSettingsList;
	} 	
	*//**
	 * @method ProfilePic - To get User profile picture 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public Collection profilePic(Profile profile) throws EventPortalException {
		profile.setHiddenAction("ProfilePicture");
		Collection profilePicSettingsList = profile.read();
		return profilePicSettingsList;
	} 
	*//**
	 * @method ContactInfoList - To get User Contact Information List 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public Collection contactInfoList(Profile profile) throws EventPortalException {
		profile.setHiddenAction("ContactInformation");
		Collection AccountSettingsList = profile.read();
		return AccountSettingsList;
	}
	
	*//**
	 * @method updateUserAccountSettings - To update User Account Settings
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public boolean updateUserAccountSettings(Profile profile) throws EventPortalException {
		profile.setHiddenAction("UpdateUserAccountSettings");
		boolean resultCheck = profile.save();
		return resultCheck;
	}
*/	
	/**
	 * @method updateUserAccountSettings - To update User Account Settings
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public boolean updateProfileSettings(Profile profile) throws EventPortalException {
		profile.setHiddenAction("UPDATEPROFILESETTINGS");
		boolean resultCheck = profile.update();
		return resultCheck;
	}
	
	
	
/*	*//**
	 * @method updateUserProfilePic - To update User Profile Picture
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public boolean updateUserProfilePic(Profile profile) throws EventPortalException {
		profile.setHiddenAction("UpdateUserProfilePic");
		boolean resultCheck = profile.save();
		return resultCheck;
	}
*/	
	
	/**
	 * @method updateUserProfilePic - To update User Profile Picture
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection updateUserProfilePic(Profile profile) throws EventPortalException {
		profile.setHiddenAction("UPDATEUSERPROFILEPIC");
		Collection profilePicSettingsList = profile.read();
		return profilePicSettingsList;
	}
	
	
	
	
/*	*//**
	 * @method updateUserContactInfo - To update User Contact Information
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 *//*
	public boolean updateUserContactInfo(Profile profile) throws EventPortalException {
		profile.setHiddenAction("UpdateUserContactInfo");
		boolean resultCheck = profile.save();
		return resultCheck;
	}
*/	
	/**
	 * @method ContactInfoList - To get User profile Info 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection userProfileInfo(Profile profile) throws EventPortalException {
		profile.setHiddenAction("USERPROFILEINFO");
		Collection profileInfoList = profile.read();
		return profileInfoList;
	}
	
	/**
	 * @method ContactInfoList - To get admin Profile Info 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection adminProfileInfo(Profile profile) throws EventPortalException {
		profile.setHiddenAction("ADMINPROFILEINFO");
		Collection profileInfoList = profile.read();
		return profileInfoList;
	}

	
	/**
	 * @method updateProfileSMInfo - To get User profile SM Info 
	 * @param profile
	 * @return boolean
	 * @throws EventPortalException 
	 */
	public boolean updateProfileSMInfo(Profile profile) throws EventPortalException {
		profile.setHiddenAction("updateProfileSMInfo");
		boolean resultCheck = profile.save();
		return resultCheck;
	}
	
	public boolean updateSocialMediaProfile(Profile profile) throws EventPortalException{
		  profile.setHiddenAction("UpdateSocialMediaProfileURL");
		  
		  return profile.update();
		 }
	
	/**
	 * @method getSocialMediaImageURL - To get SM Profile Info 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection getSocialMediaImageURL(Profile profile) throws EventPortalException {
		profile.setHiddenAction("socialMediaImageURL");
		Collection smProfileImgList = profile.read();
		return smProfileImgList;
	}
	
	/**
	 * @method sendMailToAttendees - To get Profile Email Info 
	 * @param profile
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection getAttendeesEmailInfo(Profile profile) throws EventPortalException {
		profile.setHiddenAction("sendMailToAttendees");
		Collection profileEmailInfoList = profile.read();
		return profileEmailInfoList;
	}
	
	public Collection getMailOptionsForAttendees(Profile profile) throws EventPortalException {
		profile.setHiddenAction("getMailOptionsForAttendees");
		Collection profileEmailCountList = profile.read();
		return profileEmailCountList;
	}
	
	
	//Refined Methods	
	public Collection getProfileData(Profile profile) throws EventPortalException {		
		profile.setHiddenAction("PROFILEDATA");		
		return profile.read();
	}	
	public Collection profileDataList(Profile profile) throws EventPortalException {
		profile.setHiddenAction("PROFILEDATALIST");
		return profile.read();
	}	
	public Collection getProfileIdByEmail(Profile profile) throws EventPortalException {		
		profile.setHiddenAction("GETPROFILEIDBYEMAIL");		
		return profile.read();
	}
	public boolean saveProfileData(Profile profile) throws EventPortalException{
		profile.setHiddenAction("SAVEPROFILEPOJO");		
		return profile.save();
	}
	public Collection attendee(Profile profile) throws EventPortalException {		
		profile.setHiddenAction("ATTENDEE");		
		return profile.read();
	}	

	
}
