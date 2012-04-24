package com.eventattend.portal.controller;

import twitter4j.http.AccessToken;

import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;

public interface SocialMediaController {
	
	public ResultDTO initSocialMedia(SocialMediaDTO socialMediaDTO) throws Exception;
	
	public ResultDTO addTwitterToken(TwitterDTO twitterDTO) throws Exception;
	
	public ResultDTO searchTwitterTweets(TwitterDTO twitterDTO) throws Exception;
	
	public ResultDTO addLinkedInToken(LinkedInDTO linkedInDTO) throws Exception;
	
	public ResultDTO addFaceBookToken(FaceBookDTO faceBookDTO) throws Exception;

	public ResultDTO deleteTwitterToken(TwitterDTO twitterDTO) throws Exception;
	
	public ResultDTO deleteLinkedInToken(LinkedInDTO linkedInDTO) throws Exception;
	
	public ResultDTO deleteFaceBookToken(FaceBookDTO faceBookDTO) throws Exception;
	
	public ResultDTO getTwitterProfileImageURL(TwitterDTO twitterDTO) throws Exception;
	public ResultDTO publicProfile(SocialMediaDTO socialMediaDTO) throws Exception;
	
	
	public ResultDTO shareMsgInTwitter(TwitterDTO twitterDTO) throws Exception;
	
	public ResultDTO shareMsgInFaceBook(FaceBookDTO faceBookDTO) throws Exception;
	
	public ResultDTO postLinkedinComment(LinkedInDTO linkedInDTO) throws Exception;
	
	public ResultDTO checkAlreadyFriend(SocialMediaDTO socialMediaDTO) throws Exception;
	public ResultDTO inviteFriend(SocialMediaDTO socialMediaDTO) throws Exception;
	public ResultDTO getSocialMediaImageURL(SocialMediaDTO socialMediaDTO) throws Exception;
	
	public ResultDTO getSocialMediaProfileData(SocialMediaDTO socialMediaDTO) throws Exception;
}
