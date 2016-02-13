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
 * Oct 29, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.socialmedia.util;

import java.util.ResourceBundle;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Oct 29, 2010
 * 
 * Copyright 2015-2016 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */
public class SocialMediaKeysGenerator {

	public static void initialiseSocialMediaKeysForDevelopment(){
		
		try{
			
			//System.out.println("Initialising Social Media Keys");
			
			ResourceBundle keyBundle = ResourceBundle.getBundle("com.eventattend.portal.socialmedia.util.SocialMediaKeys");
			
			SocialMediaKeys.TWITTER_API_KEY = keyBundle.getString("twitter.api.key.dev");
			
			SocialMediaKeys.TWITTER_API_SECRET = keyBundle.getString("twitter.api.secret.dev");

			SocialMediaKeys.LINKEDIN_API_KEY = keyBundle.getString("linkedin.api.key.dev");
			
			SocialMediaKeys.LINKEDIN_API_SECRET = keyBundle.getString("linkedin.api.secret.dev");
			
			SocialMediaKeys.FACEBOOK_API_KEY = keyBundle.getString("facebook.api.key.dev");
			
			SocialMediaKeys.FACEBOOK_API_SECRET = keyBundle.getString("facebook.api.secret.dev");
			
			SocialMediaKeys.GOOGLE_MAP_KEY = keyBundle.getString("google.map.key.dev");
			
		}catch(Exception e){
			System.out.println("Unable to load API Keys");
			e.printStackTrace();
		}
		
	}
	
	
public static void initialiseSocialMediaKeysForStaging(){
		
		try{
			
			//System.out.println("Initialising Social Media Keys......For Staging");
			
			ResourceBundle keyBundle = ResourceBundle.getBundle("com.eventattend.portal.socialmedia.util.SocialMediaKeys");
			
			SocialMediaKeys.TWITTER_API_KEY = keyBundle.getString("twitter.api.key.staging");
			
			SocialMediaKeys.TWITTER_API_SECRET = keyBundle.getString("twitter.api.secret.staging");

			SocialMediaKeys.LINKEDIN_API_KEY = keyBundle.getString("linkedin.api.key.staging");
			
			SocialMediaKeys.LINKEDIN_API_SECRET = keyBundle.getString("linkedin.api.secret.staging");
			
			SocialMediaKeys.FACEBOOK_API_KEY = keyBundle.getString("facebook.api.key.staging");
			
			SocialMediaKeys.FACEBOOK_API_SECRET = keyBundle.getString("facebook.api.secret.staging");
			
			SocialMediaKeys.GOOGLE_MAP_KEY = keyBundle.getString("google.map.key.staging");
			
		}catch(Exception e){
			System.out.println("Unable to load API Keys");
			e.printStackTrace();
		}
		
		
	}
	
	public static void initialiseSocialMediaKeysForProduction(){
		
		try{
			
			//System.out.println("Initialising Social Media Keys......For Production");
			
			ResourceBundle keyBundle = ResourceBundle.getBundle("com.eventattend.portal.socialmedia.util.SocialMediaKeys");
			
			SocialMediaKeys.TWITTER_API_KEY = keyBundle.getString("twitter.api.key.prod");
			
			SocialMediaKeys.TWITTER_API_SECRET = keyBundle.getString("twitter.api.secret.prod");

			SocialMediaKeys.LINKEDIN_API_KEY = keyBundle.getString("linkedin.api.key.prod");
			
			SocialMediaKeys.LINKEDIN_API_SECRET = keyBundle.getString("linkedin.api.secret.prod");
			
			SocialMediaKeys.FACEBOOK_API_KEY = keyBundle.getString("facebook.api.key.prod");
			
			SocialMediaKeys.FACEBOOK_API_SECRET = keyBundle.getString("facebook.api.secret.prod");
			
			SocialMediaKeys.GOOGLE_MAP_KEY = keyBundle.getString("google.map.key.prod");
			
			
		}catch(Exception e){
			System.out.println("Unable to load API Keys");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}
