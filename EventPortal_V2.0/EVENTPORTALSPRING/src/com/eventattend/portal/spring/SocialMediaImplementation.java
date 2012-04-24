package com.eventattend.portal.spring;

import com.eventattend.portal.bl.FaceBookBL;
import com.eventattend.portal.bl.LinkedInBL;
import com.eventattend.portal.bl.SocialMediaBL;
import com.eventattend.portal.bl.TwitterBL;
import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;

public class SocialMediaImplementation implements  SocialMediaController{

	public ResultDTO initSocialMedia(SocialMediaDTO socialMediaDTO){
    	
    	SocialMediaBL socialMediaBL = new SocialMediaBL();
    	
    	return socialMediaBL.initSocialMedia(socialMediaDTO);
    }

	 public ResultDTO addTwitterToken(TwitterDTO twitterDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.addTwitterToken(twitterDTO);
	    }
	 public ResultDTO searchTwitterTweets(TwitterDTO twitterDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.searchTwitterTweets(twitterDTO);
	    }
	 

	 public ResultDTO addLinkedInToken(LinkedInDTO linkedInDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.addLinkedInToken(linkedInDTO);
	    }
	 
	 public ResultDTO addFaceBookToken(FaceBookDTO faceBookDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.addFaceBookToken(faceBookDTO);
	    }
	
	 public ResultDTO deleteTwitterToken(TwitterDTO twitterDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.deleteTwitterToken(twitterDTO);
	    }
	 
	 public ResultDTO deleteLinkedInToken(LinkedInDTO linkedInDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.deleteLinkedInToken(linkedInDTO);
	    }
	 
	 public ResultDTO deleteFaceBookToken(FaceBookDTO faceBookDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.deleteFaceBookToken(faceBookDTO);
	    }
	 
	 public ResultDTO getTwitterProfileImageURL(TwitterDTO twitterDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.getTwitterProfileImageURL(twitterDTO);
	    }
	 public ResultDTO publicProfile(SocialMediaDTO socialMediaDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.publicProfile(socialMediaDTO);
	    }
	 
	 public ResultDTO shareMsgInTwitter(TwitterDTO twitterDTO){
	    	
	    	TwitterBL twitterBL = new TwitterBL();
	    	
	    	return twitterBL.shareMsgInTwitter(twitterDTO);
	    }
	 
	 public ResultDTO shareMsgInFaceBook(FaceBookDTO faceBookDTO){
	    	
		 FaceBookBL faceBookBL = new FaceBookBL();
	    	
	    	return faceBookBL.shareMsgInFaceBook(faceBookDTO);
	    }
	 
	 public ResultDTO postLinkedinComment(LinkedInDTO linkedInDTO){
	    	
		 LinkedInBL linkedInBL = new LinkedInBL();
	    	
	    	return linkedInBL.postLinkedinComment(linkedInDTO);
	    }
	 public ResultDTO checkAlreadyFriend(SocialMediaDTO socialMediaDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.checkAlreadyFriend(socialMediaDTO);
	    }
	 public ResultDTO inviteFriend(SocialMediaDTO socialMediaDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.inviteFriend(socialMediaDTO);
	    }
	 
	 public ResultDTO getSocialMediaImageURL(SocialMediaDTO socialMediaDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.getSocialMediaImageURL(socialMediaDTO);
	    }
	 public ResultDTO getSocialMediaProfileData(SocialMediaDTO socialMediaDTO){
	    	
	    	SocialMediaBL socialMediaBL = new SocialMediaBL();
	    	
	    	return socialMediaBL.getSocialMediaProfileData(socialMediaDTO);
	    }
	 
}
