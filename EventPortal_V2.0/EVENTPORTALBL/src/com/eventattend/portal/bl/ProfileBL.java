package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.Session;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.bo.User;
import com.eventattend.portal.common.util.DateUtility;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.UserDTO;
import com.eventattend.portal.exceptions.EventPortalException;

public class ProfileBL extends BusinessLayer {
	
	
	public boolean updateProfileSettings(ProfileDTO profileDTO) throws EventPortalException{
		boolean updateResult = false;
		Profile profile = null;		
		profile = new Profile();
		SocialMediaBL socialMediaBL = null;		
		socialMediaBL = new SocialMediaBL();
		UserBL userBL = null;
		userBL = new UserBL();
		SessionBL sessionBL = null;
		sessionBL = new SessionBL();
		profile = convertProfileDTOToProfile(profileDTO);				
		updateResult = profile.updateProfileSettings(profile);		
		
		//user = convertProfileToUser(profile, user);
		updateResult = userBL.UpdateUserProfileSettings(profileDTO);
		
		//session = convertProfileToSession(profile, session);
//		if(profileDTO.getSessionOption() != null){
//			updateResult = sessionBL.saveSessionSpeakerMap(profileDTO);
//		}
		//socialMediaBL = convertProfileToSocialMedia(profileDTO);
		updateResult = socialMediaBL.updateSocialMediaProfileSettings(profileDTO.getSocialMediaDTO());
		
		return updateResult;
	}
	
	
	 /**
	 * @method attendeesProfile - To convert the all attendees profile to DTO
	 * @param eventDTO
	 * @return ResultDTO
	 * @throws EventPortalException 
	 */
	public Collection attendeesProfile(List attendeeList,String currentUserProfileId) throws EventPortalException{
  	
    	Profile profile = null;
    	
    	ProfileDTO profileDTO = null;
    	Collection attendeeProfileList = new ArrayList();
  
    	if(attendeeList!=null){
			Iterator iter = attendeeList.iterator();
			while(iter.hasNext()){
				profileDTO = new ProfileDTO();
				 profile = (Profile)iter.next();	
				 profileDTO = convertProfileToProfileDTO(profile);
				/* profileDTO.setProfileToolTip(    						
    						"<table border='0'><tr><td><img src="+profileDTO.getProfilePicture()+"/></td><td></td><td>"+profileDTO.getFirstName()+" "+profileDTO.getLastName()+"</td>" 
    						+"</tr><tr><td>E-mail</td><td>:</td><td>"+profileDTO.getProfileEmail()+"</td></tr><tr><td>Alt-Email</td><td>:</td><td>"+profileDTO.getAlternativeEmail()+"</td></tr>"
    						+"<tr><td>Mobile</td><td>:</td><td>"+profileDTO.getMobile()+"</td></tr><tr><td>HomePhone</td><td>:</td><td>"+profileDTO.getLandHome()+"</td></tr><tr><td>OfficePhone</td><td>:</td><td>"+profileDTO.getLandOffice()+"</td>"
    						+"</tr><tr><td>Address</td><td>:</td><td>"+profileDTO.getAddress()+"<\br>"+profileDTO.getCity()+" - "+profileDTO.getState()+" - "+profileDTO.getCountry()+"("+ profileDTO.getZip()+")</td></tr>"
    						+"<tr><td>Education</td><td>:</td><td>"+profileDTO.getEducation()+"</td></tr>"
    						+"<tr><td>Occupation</td><td>:</td><td>"+profileDTO.getOccupation()+"</td></tr><tr>"
    						+"<td>Website</td><td>:</td><td>"+profileDTO.getWebsite()+"</td></tr><tr>"
    						+"<td>Speaker</td><td>:</td><td>"+profileDTO.getSpeakerFor()+"</td></tr><tr><td>Speaker KeyNotes</td><td>:</td><td>"+profileDTO.getSpeakerKeyNotes()+"</td></tr></table>");*/
					profileDTO.setProfileToolTip(attendeeToolTip(profileDTO));
				  if(currentUserProfileId!=null){
				  if(profile.getProfileId().equals(currentUserProfileId)){
					  profileDTO.setCurrentUser(true);
				  }else{
					  profileDTO.setCurrentUser(false);
				  }
				  }
				  attendeeProfileList.add(profileDTO);
				}
				       		
		 }

    	return attendeeProfileList;
	}
	
	 /**
	 * @method attendeesProfile - To convert the all attendees profile to DTO
	 * @param eventDTO
	 * @return ResultDTO
	 * @throws EventPortalException 
	 */
	public Collection attendeeProfile(List attendeeList,Map speakerMap, String currentUserProfileId) throws EventPortalException{
  	
    	Profile profile = null;
    	
    	ProfileDTO profileDTO = null;
    	Collection attendeeProfileList = new ArrayList();
    	SessionBL sessionBL = null;
    	
    	sessionBL = new SessionBL();
  
    	if(attendeeList!=null){
			Iterator iter = attendeeList.iterator();
			while(iter.hasNext()){				
				profileDTO = (ProfileDTO)iter.next();
				
				if(speakerMap!= null){
					  if(speakerMap.containsKey(profileDTO.getProfileId())){
						  profileDTO.setSpeakerCheck(true);
						  profileDTO.setSpeakerFor(sessionBL.setSpeakerForInfo(profileDTO.getProfileId()));
					  }
					  }
				
					profileDTO.setProfileToolTip(attendeeToolTip(profileDTO));
				  if(currentUserProfileId!=null){
				  if(profileDTO.getProfileId().equals(currentUserProfileId)){
					  profileDTO.setCurrentUser(true);
				  }else{
					  profileDTO.setCurrentUser(false);
				  }
				  }
				  
				  attendeeProfileList.add(profileDTO);
				  
					System.out.println("<============Social Media Connect Checking - Starts==========>");
					
					System.out.println("Name:"+profileDTO.getFirstName());
					System.out.println("Is he/she a current user?:"+profileDTO.isCurrentUser());
					System.out.println("Is he/she allow to  connect Twitter?:"+profileDTO.getSocialMediaDTO().getTwitterFollow());
					System.out.println("Is he/she allow to  connect FaceBook?:"+profileDTO.getSocialMediaDTO().getFaceBookFriendsConnect());
					System.out.println("Is he/she allow to connect LinkedIn ?:"+profileDTO.getSocialMediaDTO().getLinkedInFriendsConnect());
					
					System.out.println("<============Social Media Connect Checking - Ends==========>");

				}
		 }

    	return attendeeProfileList;
	}
	
	
	
public String  attendeeToolTip(ProfileDTO profileDTO){
		
		String tip= null;
		String name = null;
		String email = null;
		String altEmail = null;
		String mobile = null;
		String home = null;
		String office = null;
		String address = null;
		String city = null;
		String state = null;
		String country = null;
		String zip = null;
		String edu = null;
		String occup = null;
		String website = null;
		String speakerFor = null;
		String keynotes = null;
			if(profileDTO.getFirstName()!=null && profileDTO.getLastName()!=null){
				name = profileDTO.getFirstName()+" "+profileDTO.getLastName();
			}
					
			if(profileDTO.getProfileEmail()!=null){
				email = profileDTO.getProfileEmail();
			}
			if(profileDTO.getAlternativeEmail()!=null){
				altEmail = profileDTO.getAlternativeEmail();
			}
			if(profileDTO.getMobile()!=null){
				mobile = profileDTO.getMobile();
			}
			if(profileDTO.getLandHome()!=null){
				home = profileDTO.getLandHome();
			}
			if(profileDTO.getLandOffice()!=null){
				office = profileDTO.getLandOffice() ;				
			}
			if(profileDTO.getAddress()!=null){
				address = profileDTO.getAddress();
			}
			if(profileDTO.getCity()!=null){
				city = profileDTO.getCity();
			}
			if(profileDTO.getState()!=null){
				state =  profileDTO.getState();
			}
			if(profileDTO.getCountry()!=null){
				country = profileDTO.getCountry();
			}
			if(profileDTO.getZip()!=null){
				zip = profileDTO.getZip();
			}
			if(profileDTO.getEducation()!=null){
				edu = profileDTO.getEducation();
			}
			if(profileDTO.getOccupation()!=null){
				occup = profileDTO.getOccupation();				
			}
			if(profileDTO.getWebsite()!=null){
				website = profileDTO.getWebsite();
			}
			if(profileDTO.getSpeakerFor()!=null){
				speakerFor = profileDTO.getSpeakerFor();
			}
			if(profileDTO.getSpeakerKeyNotes()!=null && (!profileDTO.getSpeakerKeyNotes().equals(""))){
				keynotes = profileDTO.getSpeakerKeyNotes();
			}

		    
	             

		tip = "<table width='311' border='0' cellpadding='0' cellspacing='0' class='profile-tooltip'><tr><td width='152' class='ea-heading-class'>";
		if(name!=null){
			tip =tip+name;
		}
		tip=tip+"</td><td width='159' rowspan='2' align='center'></td></tr><tr><td class='ea-content-class'>" ;
		if(occup!=null){
			tip=tip+occup;
		}
		tip =tip+"</td></tr><tr><td class='ea-content-class'>";
		if(website!=null){
			tip =tip+website;
		}
		tip=tip+"</td><td align='center' class='ea-content-class'>";
		if(email!=null){
			tip =tip+email;
		}
		tip=tip+"</td></tr><tr><td class='ea-content-class'>" ;
		if(mobile!=null){
			tip =tip+"<strong>Mobile: </strong>"+mobile;
		}
		tip=tip+"</td><td class='ea-content-class'>";
		if(office!=null){
			tip =tip+"<strong>Office: </strong>"+office;
		}		
		tip=tip+"</td></tr><tr><td colspan='2' class='ea-content-class'>" ;
		
			if(address!=null){
				tip =tip+"<strong>Address: </strong>"+address;
			}
			if(country!=null){
				tip =tip+", "+country;
			}
			if(state!=null){
				tip =tip+", "+state;
			}
			if(city!=null){
				tip =tip+", "+city;
			}
			if(zip!=null){
				tip =tip+"- "+zip;
			}
			 

		tip =tip+"</td></tr>";
		if(speakerFor!=null){
			tip=tip+"<tr height='2'><td colspan='2'><hr /></td></tr><tr><td colspan='2' class='ea-content-class'><strong>Speaker for: </strong>"+speakerFor+"</td></tr>";
			
		}
		if(keynotes!=null){
			tip=tip+"<tr><td colspan='2' class='ea-content-class'><strong>Keynotes: </strong>"+keynotes+"</td></tr>";
			
		}
		tip=tip+"<tr><td>&nbsp;</td><td>&nbsp;</td></tr></table>";
		
		return tip;
		
	}
	/**
	    * @method convertProfileToProfileDTO -To convert Profile business objects into Profile DTO objects
	    * @param profile
	    * @return ProfileDTO
	    */
	public ProfileDTO convertProfileToProfileDTO(Profile profile) {
		ProfileDTO profileDTO = new ProfileDTO();
		if (profile.getProfileId() != null) {
			profileDTO.setProfileId(profile.getProfileId());
		}
		if (profile.getFirstName() != null) {
			profileDTO.setFirstName(profile.getFirstName());
		}
		if (profile.getLastName() != null) {
			profileDTO.setLastName(profile.getLastName());
		}
		if (profile.getGender() != null) {
			profileDTO.setGender(profile.getGender());
		}
		if (profile.getProfileEmail() != null) {
			profileDTO.setProfileEmail(profile.getProfileEmail());
		}
		if (profile.getProfilePicture() != null) {
			profileDTO.setProfilePicture(profile.getProfilePicture());
		}
		if (profile.getAlternativeEmail() != null) {
			profileDTO.setAlternativeEmail(profile.getAlternativeEmail());
		}
		if (profile.getMobile() != null) {
			profileDTO.setMobile(profile.getMobile());
		}
		if (profile.getLandHome() != null) {
			profileDTO.setLandHome(profile.getLandHome());
		}
		if (profile.getLandOffice() != null) {
			profileDTO.setLandOffice(profile.getLandOffice());
		}
		if (profile.getAddress() != null) {
			profileDTO.setAddress(profile.getAddress());
		}
		if (profile.getCity() != null) {
			profileDTO.setCity(profile.getCity());
		}
		if (profile.getCountry() != null) {
			profileDTO.setCountry(profile.getCountry());
		}
		if (profile.getZip() != null) {
			profileDTO.setZip(profile.getZip());
		}
		if (profile.getState() != null) {
			profileDTO.setState(profile.getState());
		}
		if (profile.getEducation() != null) {
			profileDTO.setEducation(profile.getEducation());
		}
		if (profile.getOccupation() != null) {
			profileDTO.setOccupation(profile.getOccupation());
		}
		if (profile.getWebsite() != null) {
			profileDTO.setWebsite(profile.getWebsite());
		}
		if (profile.isSpeaker()) {
			profileDTO.setSpeakerCheck(profile.isSpeaker());
		}
		if (profile.getSpeakerFor()!=null) {
			profileDTO.setSpeakerFor(profile.getSpeakerFor());
		}
		if (profile.getTwitterId() != null) {
			profileDTO.setTwitterId(profile.getTwitterId());
		}
		if (profile.getTwitterProfileUrl() != null) {
			profileDTO.setTwitterProfileUrl(profile.getTwitterProfileUrl());
		}
		if (profile.getTwitterImgUrl() != null) {
			profileDTO.setTwitterImgUrl(profile.getTwitterImgUrl());
		}
		profileDTO.setTwitterFollow(profile.getTwitterCheck());
		
		if (profile.getFaceBookId() != null) {
			profileDTO.setFaceBookId(profile.getFaceBookId());
		}
		if (profile.getFaceBookProfileUrl() != null) {
			profileDTO.setFaceBookProfileUrl(profile.getFaceBookProfileUrl());
		}
		if (profile.getFaceBookImgUrl() != null) {
			profileDTO.setFaceBookImgUrl(profile.getFaceBookImgUrl());
		}
		profileDTO.setFaceBookFriendsConnect(profile.getFbAllowFriendsToConnect());
		if (profile.getLinkedInId() != null) {
			profileDTO.setLinkedInId(profile.getLinkedInId());
		}
		if (profile.getLinkedInProfileUrl() != null) {
			profileDTO.setLinkedInProfileUrl(profile.getLinkedInProfileUrl());
		}
		if (profile.getLinkedInImgUrl() != null) {
			profileDTO.setLinkedInImgUrl(profile.getLinkedInImgUrl());
		}
		profileDTO.setLinkedInFriendsConnect(profile.getLiAllowFriendsToFollow());
		if (profile.getProfilePicture() != null
				&& profile.getProfilePicture().trim().startsWith("http")) {
			profileDTO.setProfImgFileName(profile.getProfilePicture());
		} else {
			profileDTO.setProfImgFileName("/images/"
					+ profile.getProfilePicture());
		}
		return profileDTO;

	}
	
	/**
	 * @method DTOTOBOConversion - DTO object to BO object conversion
	 * @param loginDTO
	 * @return List
	 * 
	 */
	public Profile convertProfileDTOToProfile(ProfileDTO profileDTO){
		Profile profile = null;
		profile = new Profile();		
		if(profile != null){
			profile.setProfileId(profileDTO.getProfileId());
			profile.setUserId(profileDTO.getUserId());
			profile.setFirstName(profileDTO.getFirstName());	
			profile.setLastName(profileDTO.getLastName());
			profile.setPassword(profileDTO.getNewPassword());
			profile.seteMail(profileDTO.geteMail());
			profile.setAlternativeEmail(profileDTO.getAlternativeEmail());
			//profile.setProfilePicture(profileDTO.getProfilePicture());
			profile.setProfilePicture(profileDTO.getProfImgFileName());
			profile.seteMail(profileDTO.geteMail());
			profile.setMobile(profileDTO.getMobile());
			profile.setLandHome(profileDTO.getLandHome());
			profile.setLandOffice(profileDTO.getLandOffice());
			profile.setAddress(profileDTO.getAddress());
			profile.setCity(profileDTO.getCity());
			profile.setZip(profileDTO.getZip());
			profile.setState(profileDTO.getState());
			profile.setCountry(profileDTO.getCountry());
			profile.setEducation(profileDTO.getEducation());
			profile.setOccupation(profileDTO.getOccupation());
			profile.setWebsite(profileDTO.getWebsite());
			profile.setNewPassword(profileDTO.getNewPassword());
			profile.setTimeZone(profileDTO.getTimeZone());
			profile.setSpeakerCheckBox(profileDTO.getSpeakerCheckBox());
			profile.setEventOption(profileDTO.getEventOption());
			profile.setSessionOption(profileDTO.getSessionOption());
			profile.setSpeakerKeyNotes(profileDTO.getSpeakerKeyNotes());
			
		}		
		return profile;
	}
	
	
	public SocialMedia convertProfileToSocialMedia(ProfileDTO profileDTO){
		SocialMedia socialMedia = null;
		if(profileDTO!=null){
			socialMedia = new SocialMedia();
			if(profileDTO.getProfileId()!=null){
				socialMedia.setSmProfileId(profileDTO.getProfileId());
			}
			if (profileDTO.getSocialMediaDTO().getTwtShowTweets()!=null) {
				socialMedia.setTwtShowTweets(profileDTO.getSocialMediaDTO().getTwtShowTweets());
			}
			if (profileDTO.getSocialMediaDTO().getTwtAllowFriends()!=null) {
				socialMedia.setTwtAllowFriends(profileDTO.getSocialMediaDTO().getTwtAllowFriends());
			}			
			if (profileDTO.getSocialMediaDTO().getFbAllowFriendsToPost()!=null) {
				socialMedia.setFbAllowFriendsToPost(profileDTO.getSocialMediaDTO().getFbAllowFriendsToPost());
			}
			if (profileDTO.getSocialMediaDTO().getFbAllowFriendsToConnect()!=null) {
				socialMedia.setFbAllowFriendsToConnect(profileDTO.getSocialMediaDTO().getFbAllowFriendsToConnect());
			}			
			if (profileDTO.getSocialMediaDTO().getLiAllowFriendsToFollow()!=null) {
				socialMedia.setLiAllowFriendsToFollow(profileDTO.getSocialMediaDTO().getLiAllowFriendsToFollow());
			}
			if (profileDTO.getSocialMediaDTO().getLiAllowFriendsToPost()!=null) {
				socialMedia.setLiAllowFriendsToPost(profileDTO.getSocialMediaDTO().getLiAllowFriendsToPost());
			}			
/*			if(profileDTO.getProfImgFileName() != null){
				profile.setImgLocation(profileDTO.getProfImgFileName());
			}
*/		}		
		return socialMedia;
	}
	
	
	
	//Refined Methods
	
	public ProfileDTO getProfileData(String profileId) throws EventPortalException{		
		
		List profileList = null; 
		Profile profile = null;
		ProfileDTO profileDTO = null;
		
		profile = new Profile();
		
		profile.setProfileId(profileId);
		
		profileList = (ArrayList)profile.getProfileData(profile);
		
		if(profileList != null){
			
			profile = (Profile)profileList.get(0);
			
		}
		
		profileDTO = new ProfileDTO();
		
		profileDTO = convertProfileToProfileDTO(profile, profileDTO);		
		
		return profileDTO;
	}
	

	public ProfileDTO convertProfileToProfileDTO(Profile profile, ProfileDTO profileDTO){
		
		
		if (profile.getProfileId() != null) {
			profileDTO.setProfileId(profile.getProfileId());
		}
		if (profile.getFirstName() != null) {
			profileDTO.setFirstName(profile.getFirstName());
		}
		if (profile.getLastName() != null) {
			profileDTO.setLastName(profile.getLastName());
		}
		if (profile.getGender() != null) {
			profileDTO.setGender(profile.getGender());
		}
		if (profile.getProfileEmail() != null) {
			profileDTO.setProfileEmail(profile.getProfileEmail());
		}
		if (profile.getProfilePicture() != null) {
			profileDTO.setProfilePicture(profile.getProfilePicture());
		}
		if (profile.getAlternativeEmail() != null) {
			profileDTO.setAlternativeEmail(profile.getAlternativeEmail());
		}
		if (profile.getMobile() != null) {
			profileDTO.setMobile(profile.getMobile());
		}
		if (profile.getLandHome() != null) {
			profileDTO.setLandHome(profile.getLandHome());
		}
		if (profile.getLandOffice() != null) {
			profileDTO.setLandOffice(profile.getLandOffice());
		}
		if (profile.getAddress() != null) {
			profileDTO.setAddress(profile.getAddress());
		}
		if (profile.getCity() != null) {
			profileDTO.setCity(profile.getCity());
		}
		if (profile.getCountry() != null) {
			profileDTO.setCountry(profile.getCountry());
		}
		if (profile.getZip() != null) {
			profileDTO.setZip(profile.getZip());
		}
		if (profile.getState() != null) {
			profileDTO.setState(profile.getState());
		}
		if (profile.getEducation() != null) {
			profileDTO.setEducation(profile.getEducation());
		}
		if (profile.getOccupation() != null) {
			profileDTO.setOccupation(profile.getOccupation());
		}
		if (profile.getWebsite() != null) {
			profileDTO.setWebsite(profile.getWebsite());
		}
		if (profile.isSpeaker()) {
			profileDTO.setSpeakerCheck(profile.isSpeaker());
		}
		if (profile.getSpeakerKeyNotes()!=null) {
			profileDTO.setSpeakerKeyNotes(profile.getSpeakerKeyNotes());
		}
		if (profile.getSpeakerFor()!=null) {
			profileDTO.setSpeakerFor(profile.getSpeakerFor());
		}
		if (profile.getTwitterId() != null) {
			profileDTO.setTwitterId(profile.getTwitterId());
		}
		if (profile.getTwitterProfileUrl() != null) {
			profileDTO.setTwitterProfileUrl(profile.getTwitterProfileUrl());
		}
		if (profile.getTwitterImgUrl() != null) {
			profileDTO.setTwitterImgUrl(profile.getTwitterImgUrl());
		}
		
		profileDTO.setTwitterFollow(profile.getTwitterCheck());
		
		if (profile.getFaceBookId() != null) {
			profileDTO.setFaceBookId(profile.getFaceBookId());
		}
		if (profile.getFaceBookProfileUrl() != null) {
			profileDTO.setFaceBookProfileUrl(profile.getFaceBookProfileUrl());
		}
		if (profile.getFaceBookImgUrl() != null) {
			profileDTO.setFaceBookImgUrl(profile.getFaceBookImgUrl());
		}
		profileDTO.setFaceBookFriendsConnect(profile.getFbAllowFriendsToConnect());
		if (profile.getLinkedInId() != null) {
			profileDTO.setLinkedInId(profile.getLinkedInId());
		}
		if (profile.getLinkedInProfileUrl() != null) {
			profileDTO.setLinkedInProfileUrl(profile.getLinkedInProfileUrl());
		}
		if (profile.getLinkedInImgUrl() != null) {
			profileDTO.setLinkedInImgUrl(profile.getLinkedInImgUrl());
		}
		profileDTO.setLinkedInFriendsConnect(profile.getLiAllowFriendsToFollow());
		
		if (profile.getImgLocation() != null) {
			profileDTO.setProfImgFileName(profile.getImgLocation());
		}
		
		if(profile.getTimeZone() != null){
			profileDTO.setTimeZone(profile.getTimeZone());
		}
		
		if(profile.getJoinedEventsName() != null){
			profileDTO.setJoinedEventsName(profile.getJoinedEventsName());
		}
		
		if(profile.getAttendedSessionsName() != null){
			profileDTO.setAttendedSessionsName(profile.getAttendedSessionsName());
		}
		
		
		return profileDTO;
	}
	
// A.Jude	
	
	public ProfileDTO getProfileIdByEmail(UserDTO userDTO){
		ProfileDTO profileDTO = null;
		profileDTO = new ProfileDTO();
		Profile profile = new Profile();
		Collection profileList = new ArrayList();
		if(userDTO!=null){
			profile.setProfileEmail(userDTO.geteMail());
	    	try {
	    		profileList = profile.getProfileIdByEmail(profile);
	    		if(!profileList.isEmpty()){
	    			Iterator<Profile> iter = profileList.iterator();
	    			while(iter.hasNext()){
	    				profile = iter.next();
	    				profileDTO.setProfileId(profile.getProfileId());
	    			}
	    		}
	    	} catch(Exception e){
	    		e.printStackTrace();
	    	}

		}
		return profileDTO;		
	}
	
	public boolean saveProfileData(UserDTO userDTO){
		boolean isProfileSave = false;
		Profile profile = null;		
		profile = new Profile();		
		if(userDTO!=null){
			profile.setProfileEmail(userDTO.geteMail());	    	
	    	profile.setFirstName(userDTO.getFirstName());
	    	profile.setLastName(userDTO.getLastName());
	    	profile.setGender(userDTO.getGender());
	    	DateUtility dateUtility = new DateUtility();
			
			String timeZone = dateUtility.currentServerTZ();
			System.out.println("Time Zone is set to==>"+timeZone);
			profile.setTimeZone(timeZone);
			profile.setImgLocation("/images/noPhoto.jpg");
	    	try {
	    		isProfileSave = profile.saveProfileData(profile);
	    	} catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		return isProfileSave;
	}
	
	public List profileDataList(List profileIdList) throws EventPortalException{
		
		List profileDataList = null;
		List resultList = null;
		SocialMediaBL socialMediaBL = null;
		SocialMediaDTO socialMediaDTO = null;
		
		Profile profile = null;
		ProfileDTO profileDTO = null;
		
		profile = new Profile();
		socialMediaBL = new SocialMediaBL();
		
		profile.setProfileIdList(profileIdList);
		
		profileDataList = (ArrayList)profile.profileDataList(profile);
		
		if(profileDataList != null){
		
		resultList = new ArrayList();
		
		Iterator it = profileDataList.iterator();		
		
		while(it.hasNext()){
		
			profile = (Profile)it.next();
			
			profileDTO = new ProfileDTO();
			
			socialMediaDTO = socialMediaBL.getProfileSocialMediaData(profile.getProfileId());
			
			profileDTO = convertProfileToProfileDTO(profile, profileDTO);
			
			profileDTO.setSocialMediaDTO(socialMediaDTO);
			
			resultList.add(profileDTO);
		}
		
		}
		return resultList;
	}
	
	
	public User convertProfileToUser(Profile profile, User user){
		if(profile != null){	
			if(profile.getProfileId()!=null){
				user.setProfileId(profile.getProfileId());
			}
			if (profile.getPassword() != null) {
				user.setUserPassword(profile.getPassword());
			}					
		}
		return user;
	}

	
	public Session convertProfileToSession(Profile profile, Session session){
		if(profile != null){	
			if(profile.getUserId()!=null){
				session.setUserId(profile.getUserId());
			}
			if (profile.getSessionOption() != null) {
				session.setSessionId(profile.getSessionOption());
			}					
		}
		return session;
	}
	
	public ResultDTO attendee(){
	  	ResultDTO resultDTO = new ResultDTO();
    	Profile profile = new Profile();
    	List attendeeProfileList = new ArrayList();
    	List profileList = new ArrayList();
    	try {
			attendeeProfileList = (List) profile.attendee(profile);
			
			if(attendeeProfileList != null){				
				Iterator it = attendeeProfileList.iterator();					
				while(it.hasNext()){				
					profile = (Profile)it.next();					
					ProfileDTO profileDTO = new ProfileDTO();			
					profileDTO = convertProfileToProfileDTO(profile, profileDTO);
					profileList.add(profileDTO);
				}
			}
		} catch (EventPortalException e) {	
			e.printStackTrace();
		}
    	resultDTO.setEventAttendeeProfileList(profileList);
    	
    	return resultDTO;
	}
	
}
