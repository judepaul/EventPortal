package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import twitter4j.http.AccessToken;

import com.eventattend.portal.controller.SocialMediaController;
import com.eventattend.portal.dto.FaceBookDTO;
import com.eventattend.portal.dto.LinkedInDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.dto.SocialMediaDTO;
import com.eventattend.portal.dto.TwitterDTO;

public class SocialMediaFactory {

	
	public static SocialMediaController initSocialMedia() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("initSocialMedia");
	}
	
	public static SocialMediaController addTwitterToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("addTwitterToken");
	}
	
	public static SocialMediaController addLinkedInToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("addLinkedInToken");
	}
	
	public static SocialMediaController addFaceBookToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("addFaceBookToken");
	}
	
	public static SocialMediaController searchTwitterTweets(TwitterDTO twitterDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("searchTweets");
	}
	
	public static SocialMediaController deleteTwitterToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("deleteTwitterToken");
	}
	
	public static SocialMediaController deleteLinkedInToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("deleteLinkedInToken");
	}
	
	public static SocialMediaController deleteFaceBookToken() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("deleteFaceBookToken");
	}
	
	public static SocialMediaController getTwitterProfileImageURL(TwitterDTO twitterDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("searchTweets");
	}
	public static SocialMediaController  publicProfile(SocialMediaDTO socialMediaDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("publicProfile");
	}

	public static SocialMediaController shareMsgInTwitter(TwitterDTO twitterDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("initSocialMedia");
	}
	public static SocialMediaController shareMsgInFaceBook(FaceBookDTO faceBookDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("initSocialMedia");
	}
	public static SocialMediaController postLinkedinComment(LinkedInDTO linkedInDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("initSocialMedia");
	}
	public static SocialMediaController checkAlreadyFriend(SocialMediaDTO socialMediaDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("checkAlreadyFriend");
	}
	public static SocialMediaController inviteFriend(SocialMediaDTO socialMediaDTO) throws Exception{
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("inviteFriendInSM");
	}
	
	public static SocialMediaController getSocialMediaImageURL() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("getSocialMediaImageURL");
	}
	
	public static SocialMediaController getSocialMediaProfileData() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SocialMediaController) factory.getBean("getSocialMediaProfileData");
	}
	
}
