package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.bo.SocialMedia;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.model.db4o.EventPOJO;
import com.eventattend.portal.model.db4o.ProfilePOJO;
import com.eventattend.portal.model.db4o.SMInvitePOJO;
import com.eventattend.portal.model.db4o.SMProfilePOJO;
import com.eventattend.portal.model.db4o.UserSessionMapPOJO;


public class SocialMediaDAO extends  DataAccessObject{
	ObjectContainer db = null;
	
	@SuppressWarnings("unchecked")
	public Collection read(BusinessObject object) throws EventPortalException {
		SocialMedia socialMedia = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		socialMedia =(SocialMedia)object;
		hiddenAction = socialMedia.getHiddenAction();
		if (hiddenAction != null) {
			if (hiddenAction.equals("readSMInvite")) {
				resultCollection = readSMInvite(socialMedia);
			}else if (hiddenAction.equals("checkRequestPendingInLinkedIn")) {
				resultCollection = checkRequestPendingInLinkedIn(socialMedia);
			}else if (hiddenAction.equals("SOCIALMEDIAPROFILEDATA")) {
				
				try{
					db = getDbConnection();
					
				resultCollection = getProfileSocialMediaData(db,socialMedia);
				
			} catch (DatabaseFileLockedException e) {
				e.printStackTrace();
				
			} finally {
				db.close();
			}
				
			}else{
				
			}
		}
		return resultCollection;
	}
	
	

	public Collection readSMInvite(SocialMedia socialMedia) throws EventPortalException {
		List sminviteList = new ArrayList();
		String currentUserProfileId = null;
		String profileIdToConnect = null;
		if(socialMedia!=null){
			currentUserProfileId = socialMedia.getSmiFromProfileId();
			profileIdToConnect = socialMedia.getSmiToProfileId();
		}		
		try {
			db = getDbConnection();
			ObjectSet<SMInvitePOJO> results = db.get(new SMInvitePOJO(currentUserProfileId,profileIdToConnect));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					SMInvitePOJO smInvitePOJO = (SMInvitePOJO) results.next();
					socialMedia = new SocialMedia();
					if(smInvitePOJO.getSmiFromProfileId()!=null){
						socialMedia.setSmiFromProfileId(smInvitePOJO.getSmiFromProfileId());}
						if(smInvitePOJO.getSmiToProfileId()!=null){				
							socialMedia.setSmiToProfileId(smInvitePOJO.getSmiToProfileId());};
						if(smInvitePOJO.getSmiTwitterConnectStatus()!=null){					
							socialMedia.setSmiTwitterConnectStatus(smInvitePOJO.getSmiTwitterConnectStatus());}
						if(smInvitePOJO.getSmiFacebookConnectStatus()!=null){
							socialMedia.setSmiFacebookConnectStatus(smInvitePOJO.getSmiFacebookConnectStatus());};
						if(smInvitePOJO.getSmiLinkedInConnectStatus()!=null){
							socialMedia.setSmiLinkedInConnectStatus(smInvitePOJO.getSmiLinkedInConnectStatus());};
						if(smInvitePOJO.getSmiTwitterConnectDate()!=null){
							socialMedia.setSmiTwitterConnectDate(smInvitePOJO.getSmiTwitterConnectDate());};
						if(smInvitePOJO.getSmiFacebookConnectDate()!=null){
							socialMedia.setSmiFacebookConnectDate(smInvitePOJO.getSmiFacebookConnectDate());};
						if(smInvitePOJO.getSmiLinkedInConnectDate()!=null){
							socialMedia.setSmiLinkedInConnectDate(smInvitePOJO.getSmiLinkedInConnectDate());};
				}
				
			
				sminviteList.add(socialMedia);
			}
			}catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			} catch (Db4oIOException e) {
				e.printStackTrace();
				throw new DatabaseException("Database Connection Error");
			} finally {
				db.close();
			} 
			return sminviteList;
		
		
	}
	public Collection checkRequestPendingInLinkedIn(SocialMedia socialMedia) throws EventPortalException {
		List sminviteList = new ArrayList();
		String currentUserProfileId = null;
		String profileIdToConnect = null;
		if(socialMedia!=null){
			currentUserProfileId = socialMedia.getSmiFromProfileId();
			profileIdToConnect = socialMedia.getSmiToProfileId();
		}		
		try {
			db = getDbConnection();
			ObjectSet<SMInvitePOJO> results = db.get(new SMInvitePOJO(profileIdToConnect,currentUserProfileId));
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					SMInvitePOJO smInvitePOJO = (SMInvitePOJO) results.next();
					socialMedia = new SocialMedia();
					if(smInvitePOJO.getSmiFromProfileId()!=null){
						socialMedia.setSmiFromProfileId(smInvitePOJO.getSmiFromProfileId());}
						if(smInvitePOJO.getSmiToProfileId()!=null){				
							socialMedia.setSmiToProfileId(smInvitePOJO.getSmiToProfileId());};
						if(smInvitePOJO.getSmiTwitterConnectStatus()!=null){					
							socialMedia.setSmiTwitterConnectStatus(smInvitePOJO.getSmiTwitterConnectStatus());}
						if(smInvitePOJO.getSmiFacebookConnectStatus()!=null){
							socialMedia.setSmiFacebookConnectStatus(smInvitePOJO.getSmiFacebookConnectStatus());};
						if(smInvitePOJO.getSmiLinkedInConnectStatus()!=null){
							socialMedia.setSmiLinkedInConnectStatus(smInvitePOJO.getSmiLinkedInConnectStatus());};
						if(smInvitePOJO.getSmiTwitterConnectDate()!=null){
							socialMedia.setSmiTwitterConnectDate(smInvitePOJO.getSmiTwitterConnectDate());};
						if(smInvitePOJO.getSmiFacebookConnectDate()!=null){
							socialMedia.setSmiFacebookConnectDate(smInvitePOJO.getSmiFacebookConnectDate());};
						if(smInvitePOJO.getSmiLinkedInConnectDate()!=null){
							socialMedia.setSmiLinkedInConnectDate(smInvitePOJO.getSmiLinkedInConnectDate());};
				}
				
			
				sminviteList.add(socialMedia);
			}
			}catch (DatabaseFileLockedException e) {
				e.printStackTrace();
			} catch (Db4oIOException e) {
				e.printStackTrace();
				throw new DatabaseException("Database Connection Error");
			} finally {
				db.close();
			} 
			return sminviteList;
		
		
	}
	public boolean save(BusinessObject object) throws EventPortalException {
		boolean result = false;
		SocialMedia socialMedia = null;
		String hiddenAction = null;
		socialMedia =(SocialMedia)object;
		hiddenAction = socialMedia.getHiddenAction();
		if (hiddenAction != null) {
			if (hiddenAction.equals("saveSMInvite")) {
				result =saveSMInvite(socialMedia);
			}else if (hiddenAction.equals("updateLinkedInConnectionStatus")) {
				result = updateLinkedInConnectionStatus(socialMedia);
			}
		
	}
		return result;
	
   }
	
	
	public boolean update(BusinessObject object) throws EventPortalException {
		boolean result = false;
		SocialMedia socialMedia = null;
		String hiddenAction = null;
		socialMedia =(SocialMedia)object;
		hiddenAction = socialMedia.getHiddenAction();
		if (hiddenAction != null) {
			if (hiddenAction.equals("SOCIALMEDIAPROFILEUPDATE")) {
				try {
					db = getDbConnection();
				result =updateSocialMediaProfile(db, socialMedia);
				
				} catch (Exception e) {
					result = false;
					e.printStackTrace();			
				} finally {
					db.close();
				}	
			}else if (hiddenAction.equals("updateLinkedInConnectionStatus")) {
				result = updateLinkedInConnectionStatus(socialMedia);
			}else if(hiddenAction.equals("UpdateSocialMediaProfileURL")){
				result = updateSocialMediaProfile(db, socialMedia);			
				
			}else if (hiddenAction.equals("updateSMProfile")) {
				try {
					db = getDbConnection();
					result = updateSocialMediaProfile(db, socialMedia);
				} catch (Exception e) {
					result = false;
					e.printStackTrace();			
				} finally {
					db.close();
				}	
			}
		
	}
		return result;
	
   }
	
	private boolean updateSocialMediaProfile(ObjectContainer dbContainer,
			SocialMedia socialMedia) {
		boolean result = false;
		SMProfilePOJO smProfilePOJO = null;
		
		smProfilePOJO = getSMProfilePOJOBySMProfileId(dbContainer, socialMedia.getSmProfileId());
		
		if(smProfilePOJO!= null){				
			updateSMProfilePOJO(dbContainer, socialMedia);
			result = true;
		}else{
				saveSMProfilePOJO(dbContainer, socialMedia);
				result = true;			
			}
			//smProfilePOJO = convertSocialMediaToSMProfilePOJO(socialMedia, smProfilePOJO);
			
			//db.store(smProfilePOJO);
			//db.commit();
			result = true;
			
		return result;
	}



	public boolean updateLinkedInConnectionStatus(SocialMedia socialMedia){
		boolean result = false;
		SMInvitePOJO smInvitePOJO = null;
		try {
			db = getDbConnection();
			if(socialMedia!=null){
				smInvitePOJO = new SMInvitePOJO(socialMedia.getSmiFromProfileId(),socialMedia.getSmiToProfileId());
				ObjectSet<SMInvitePOJO> results = db.get(smInvitePOJO);
				
				if(!results.isEmpty()){							
						smInvitePOJO = results.get(0);					
						if(smInvitePOJO.getSmiLinkedInConnectStatus()!=null && smInvitePOJO.getSmiLinkedInConnectStatus().equals("Y")){ 
							smInvitePOJO.setSmiLinkedInConnectStatus("N");			
							smInvitePOJO.setSmiLinkedInConnectDate(null);
						}
						db.store(smInvitePOJO);
						db.commit();
						result = true;
				}
				
				
			}
			
		} catch (EventPortalException e) {
			result = false;
			e.printStackTrace();			
		} finally {
			db.close();
		}	
		return result;
	}
	/**
	 * @param socialMedia
	 * @return
	 */
	public boolean saveSMInvite(SocialMedia socialMedia){
		boolean result = false;
		SMInvitePOJO smInvitePOJO = null;
		try {
			db = getDbConnection();
			if(socialMedia!=null){
				smInvitePOJO = new SMInvitePOJO(socialMedia.getSmiFromProfileId(),socialMedia.getSmiToProfileId());
				ObjectSet<SMInvitePOJO> results = db.get(smInvitePOJO);
				if(results.isEmpty()){			
					smInvitePOJO.setSmiId(getNewId());
					if(socialMedia.getSmiFromProfileId()!=null){
					smInvitePOJO.setSmiFromProfileId(socialMedia.getSmiFromProfileId());}
					if(socialMedia.getSmiToProfileId()!=null){				
					smInvitePOJO.setSmiToProfileId(socialMedia.getSmiToProfileId());};
					if(socialMedia.getSmiTwitterConnectStatus()!=null){					
					smInvitePOJO.setSmiTwitterConnectStatus(socialMedia.getSmiTwitterConnectStatus());}
					if(socialMedia.getSmiFacebookConnectStatus()!=null){
					smInvitePOJO.setSmiFacebookConnectStatus(socialMedia.getSmiFacebookConnectStatus());};
					if(socialMedia.getSmiLinkedInConnectStatus()!=null){
					smInvitePOJO.setSmiLinkedInConnectStatus(socialMedia.getSmiLinkedInConnectStatus());};
					if(socialMedia.getSmiTwitterConnectDate()!=null){
					smInvitePOJO.setSmiTwitterConnectDate(socialMedia.getSmiTwitterConnectDate());};
					if(socialMedia.getSmiFacebookConnectDate()!=null){
					smInvitePOJO.setSmiFacebookConnectDate(socialMedia.getSmiFacebookConnectDate());};
					if(socialMedia.getSmiLinkedInConnectDate()!=null){
					smInvitePOJO.setSmiLinkedInConnectDate(socialMedia.getSmiLinkedInConnectDate());};
				}else{
					if(!results.isEmpty()){	
						smInvitePOJO = results.get(0);
					}
					if(socialMedia.getSmiTwitterConnectStatus()!=null){
						smInvitePOJO.setSmiTwitterConnectStatus(socialMedia.getSmiTwitterConnectStatus());};
					if(socialMedia.getSmiFacebookConnectStatus()!=null){
					smInvitePOJO.setSmiFacebookConnectStatus(socialMedia.getSmiFacebookConnectStatus());};
					if(socialMedia.getSmiLinkedInConnectStatus()!=null){
					smInvitePOJO.setSmiLinkedInConnectStatus(socialMedia.getSmiLinkedInConnectStatus());};
					if(socialMedia.getSmiTwitterConnectDate()!=null){
					smInvitePOJO.setSmiTwitterConnectDate(socialMedia.getSmiTwitterConnectDate());}
					if(socialMedia.getSmiFacebookConnectDate()!=null){
					smInvitePOJO.setSmiFacebookConnectDate(socialMedia.getSmiFacebookConnectDate());};
					if(socialMedia.getSmiLinkedInConnectDate()!=null){
					smInvitePOJO.setSmiLinkedInConnectDate(socialMedia.getSmiLinkedInConnectDate());};
				}
				db.store(smInvitePOJO);
				db.commit();
				result = true;
			}
		} catch (EventPortalException e) {
			result = false;
			e.printStackTrace();			
		} finally {
			db.close();
		}	
		return result;
	}
	
	//refined methods
	
	private List getProfileSocialMediaData(ObjectContainer dbContainer, SocialMedia socialMedia) {
		
		List profileList = null;
		SMProfilePOJO smProfilePOJO = null;
				
		smProfilePOJO = getSMProfilePOJOBySMProfileId(dbContainer, socialMedia.getSmProfileId());
		
		if(smProfilePOJO!= null){
			
			socialMedia = convertSMProfilePOJOToSM(smProfilePOJO, socialMedia);
			profileList = new ArrayList();
			profileList.add(socialMedia);
		}
		
		return profileList;
	}
	
private SocialMedia convertSMProfilePOJOToSM(SMProfilePOJO smProfilePOJO,
			SocialMedia socialMedia) {
		
	if(socialMedia != null){
		
		if(smProfilePOJO.getSmpTwitterId() != null){
			socialMedia.setTwitterId(smProfilePOJO.getSmpTwitterId());
		}
		
		if(smProfilePOJO.getSmpTwitterProfileUrl() != null){
			socialMedia.setTwitterProfileUrl(smProfilePOJO.getSmpTwitterProfileUrl());
		}
		
		if(smProfilePOJO.getSmpTwitterImgUrl() != null){
			socialMedia.setTwitterImgUrl(smProfilePOJO.getSmpTwitterImgUrl());
		}
		
		if (smProfilePOJO.getTwtShowTweets()!=null) {
			socialMedia.setTwtShowTweets(smProfilePOJO.getTwtShowTweets());
		}
		if (smProfilePOJO.getTwtAllowFriends()!=null) {
			socialMedia.setTwtAllowFriends(smProfilePOJO.getTwtAllowFriends());
		}

		if(smProfilePOJO.getSmpFacebookId() != null){
			socialMedia.setFaceBookId(smProfilePOJO.getSmpFacebookId());
		}
		
		if(smProfilePOJO.getSmpFaceBookProfileUrl() != null){
			socialMedia.setFaceBookProfileUrl(smProfilePOJO.getSmpFaceBookProfileUrl());
		}
		
		if(smProfilePOJO.getSmpFacebookImgUrl() != null){
			socialMedia.setFaceBookImgUrl(smProfilePOJO.getSmpFacebookImgUrl());
		}
		
		if (smProfilePOJO.getFbAllowFriendsToPost()!=null) {
			socialMedia.setFbAllowFriendsToPost(smProfilePOJO.getFbAllowFriendsToPost());
		}
		if (smProfilePOJO.getFbAllowFriendsToConnect()!=null) {
			socialMedia.setFbAllowFriendsToConnect(smProfilePOJO.getFbAllowFriendsToConnect());
		}
		
		if(smProfilePOJO.getSmpLinkedInId() != null){
			socialMedia.setLinkedInId(smProfilePOJO.getSmpLinkedInId());
		}
		
		if(smProfilePOJO.getSmpLinkedInProfileUrl() != null){
			socialMedia.setLinkedInProfileUrl(smProfilePOJO.getSmpLinkedInProfileUrl());
		}
		
		if(smProfilePOJO.getSmpLinkedInImgUrl() != null){
			socialMedia.setLinkedInImgUrl(smProfilePOJO.getSmpLinkedInImgUrl());
		}
			
		if (smProfilePOJO.getLiAllowFriendsToFollow()!=null) {
			socialMedia.setLiAllowFriendsToFollow(smProfilePOJO.getLiAllowFriendsToFollow());
		}
		if (smProfilePOJO.getLiAllowFriendsToPost()!=null) {
			socialMedia.setLiAllowFriendsToPost(smProfilePOJO.getLiAllowFriendsToPost());
		}
	}
	
		return socialMedia;
	}



private SMProfilePOJO getSMProfilePOJOBySMProfileId(ObjectContainer dbContainer, String profileId){
		
		SMProfilePOJO smProfilePOJO = null;
		
		try{
			
			ObjectSet<SMProfilePOJO> smProfilePOJOSet = dbContainer.get(new SMProfilePOJO(profileId));
			
			if(!smProfilePOJOSet.isEmpty()){			
				smProfilePOJO = smProfilePOJOSet.next();
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return smProfilePOJO;
	}


	public SMProfilePOJO convertProfileToSMProfilePOJO(Profile profile, SMProfilePOJO smProfilePOJO){
	
	if(smProfilePOJO != null){
		
		if(profile.getProfileId()!=null){
			smProfilePOJO.setSmpProfileId(profile.getProfileId());
		}
		
		if(profile.getTwitterId() != null){
			smProfilePOJO.setSmpTwitterId(profile.getTwitterId());
		}
		
		if(profile.getTwitterProfileUrl() != null){
			smProfilePOJO.setSmpTwitterProfileUrl(profile.getTwitterProfileUrl());
		}
		
		if(profile.getTwitterImgUrl() != null){
			smProfilePOJO.setSmpTwitterImgUrl(profile.getTwitterImgUrl());
		}
		
		if (profile.getTwtShowTweets()!=null) {
			smProfilePOJO.setTwtShowTweets(profile.getTwtShowTweets());
		}
		if (profile.getTwtAllowFriends()!=null) {
			smProfilePOJO.setTwtAllowFriends(profile.getTwtAllowFriends());
		}

		if(profile.getFaceBookId() != null){
			smProfilePOJO.setSmpFacebookId(profile.getFaceBookId());
		}
		
		if(profile.getFaceBookProfileUrl() != null){
			smProfilePOJO.setSmpFaceBookProfileUrl(profile.getFaceBookProfileUrl());
		}
		
		if(profile.getFaceBookImgUrl() != null){
			smProfilePOJO.setSmpFacebookImgUrl(profile.getFaceBookImgUrl());
		}
		
		if (profile.getFbAllowFriendsToPost()!=null) {
			smProfilePOJO.setFbAllowFriendsToPost(profile.getFbAllowFriendsToPost());
		}
		if (profile.getFbAllowFriendsToConnect()!=null) {
			smProfilePOJO.setFbAllowFriendsToConnect(profile.getFbAllowFriendsToConnect());
		}
		
		if(profile.getLinkedInId() != null){
			smProfilePOJO.setSmpLinkedInId(profile.getLinkedInId());
		}
		
		if(profile.getLinkedInProfileUrl() != null){
			smProfilePOJO.setSmpLinkedInProfileUrl(profile.getLinkedInProfileUrl());
		}
		
		if(profile.getLinkedInImgUrl() != null){
			smProfilePOJO.setSmpLinkedInImgUrl(profile.getLinkedInImgUrl());
		}
			
		if (profile.getLiAllowFriendsToFollow()!=null) {
			smProfilePOJO.setLiAllowFriendsToFollow(profile.getLiAllowFriendsToFollow());
		}
		if (profile.getLiAllowFriendsToPost()!=null) {
			smProfilePOJO.setLiAllowFriendsToPost(profile.getLiAllowFriendsToPost());
		}				
		
	}
	
	return smProfilePOJO;
	
}
	
	public SMProfilePOJO convertSocialMediaToSMProfilePOJO(SocialMedia socialMedia, SMProfilePOJO smProfilePOJO){
		
		if(socialMedia != null){
			
			if(socialMedia.getSmProfileId()!=null){
				smProfilePOJO.setSmpProfileId(socialMedia.getSmProfileId());
			}
			
			
			if(socialMedia.getHiddenObject() != null){
				
			if(socialMedia.getHiddenObject().equals("TWITTER")){	
				smProfilePOJO.setSmpTwitterId(socialMedia.getTwitterId());	
				smProfilePOJO.setSmpTwitterProfileUrl(socialMedia.getTwitterProfileUrl());
				smProfilePOJO.setSmpTwitterImgUrl(socialMedia.getTwitterImgUrl());
			}

			if(socialMedia.getHiddenObject().equals("FACEBOOK")){
				smProfilePOJO.setSmpFacebookId(socialMedia.getFaceBookId());
				smProfilePOJO.setSmpFaceBookProfileUrl(socialMedia.getFaceBookProfileUrl());
				smProfilePOJO.setSmpFacebookImgUrl(socialMedia.getFaceBookImgUrl());
			}
			
			if(socialMedia.getHiddenObject().equals("LINKEDIN")){		
				smProfilePOJO.setSmpLinkedInId(socialMedia.getLinkedInId());
				smProfilePOJO.setSmpLinkedInProfileUrl(socialMedia.getLinkedInProfileUrl());
				smProfilePOJO.setSmpLinkedInImgUrl(socialMedia.getLinkedInImgUrl());
			}
			}
			
			if (socialMedia.getTwtShowTweets()!=null) {
				smProfilePOJO.setTwtShowTweets(socialMedia.getTwtShowTweets());
			}
			if (socialMedia.getTwtAllowFriends()!=null) {
				smProfilePOJO.setTwtAllowFriends(socialMedia.getTwtAllowFriends());
			}
			
			if (socialMedia.getFbAllowFriendsToPost()!=null) {
				smProfilePOJO.setFbAllowFriendsToPost(socialMedia.getFbAllowFriendsToPost());
			}
			if (socialMedia.getFbAllowFriendsToConnect()!=null) {
				smProfilePOJO.setFbAllowFriendsToConnect(socialMedia.getFbAllowFriendsToConnect());
			}
				
			if (socialMedia.getLiAllowFriendsToFollow()!=null) {
				smProfilePOJO.setLiAllowFriendsToFollow(socialMedia.getLiAllowFriendsToFollow());
			}
			if (socialMedia.getLiAllowFriendsToPost()!=null) {
				smProfilePOJO.setLiAllowFriendsToPost(socialMedia.getLiAllowFriendsToPost());
			}				
			
		}
		
		return smProfilePOJO;
		
	}
	
	
	
	//

	
	public boolean isSMProfileExists(ObjectContainer db, Profile profile){
		boolean isSMProfileExist= false; 
		SMProfilePOJO smProfilePOJO = null;
		String smProfileId = null;
		
		try {
			//db = getDbConnection();		
			if(profile!=null){
				smProfileId = profile.getProfileId();
				//ObjectSet<SMProfilePOJO> result = db.get(new SMProfilePOJO(smProfileId));
				ObjectSet<SMProfilePOJO> result = db.get(new SMProfilePOJO());
				if (!result.isEmpty()) {
					while (result.hasNext()) {						
						SMProfilePOJO smProfPOJO = (SMProfilePOJO) result.next();		
						if(smProfPOJO.getSmpProfileId()!=null && smProfPOJO.getSmpProfileId().equals(smProfileId)){
							isSMProfileExist = true;
						}
					}						
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//db.close();
			}

		return isSMProfileExist;
	}

	
	public SMProfilePOJO getSMProfilePOJO(ObjectContainer db,String profileId){
			
			SMProfilePOJO smProfilePOJO = null;
			
			try {
				//db = getDbConnection();		
			
			ObjectSet<SMProfilePOJO> results = db.get(new SMProfilePOJO(profileId));
			
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					smProfilePOJO = results.next();
				}
			}		
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				//db.close();
			}
			
		return 	smProfilePOJO;
		}

	public boolean saveSMProfilePOJO(ObjectContainer dbContainer, SocialMedia socialMedia){		
		SMProfilePOJO smProfilePOJO = null;		
		String smId = null;		
		boolean saveResult = false;		
		try{		
			smId = getNewId();
			smProfilePOJO = new SMProfilePOJO();
			smProfilePOJO.setSmpId(smId);
			//smProfilePOJO = convertProfileToSMProfilePOJO(socialMedia, smProfilePOJO);
			smProfilePOJO = convertSocialMediaToSMProfilePOJO(socialMedia, smProfilePOJO);
			dbContainer.store(smProfilePOJO);
			dbContainer.commit();
			saveResult = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return saveResult;
	}

	
	public boolean updateSMProfilePOJO(ObjectContainer dbContainer, SocialMedia socialMedia){		
		SMProfilePOJO smProfilePOJO = null;		
		String profileId = null;		
		boolean updateResult = false;		
		Profile profile = new Profile();
		try{			
			profileId = socialMedia.getSmProfileId();
					
				ObjectSet<SMProfilePOJO> smProfilePOJOSet = dbContainer.get(new SMProfilePOJO(profileId));
		
				if(!smProfilePOJOSet.isEmpty()){	
			
					smProfilePOJO = smProfilePOJOSet.next();
					smProfilePOJO = convertSocialMediaToSMProfilePOJO(socialMedia, smProfilePOJO);
			
					dbContainer.store(smProfilePOJO);
			
					dbContainer.commit();
			
					updateResult = true;
			
				}else{
				updateResult = false;
				}
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return updateResult;
	}

	
	
	
	
}