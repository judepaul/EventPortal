package com.eventattend.portal.web.util;

import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.eventattend.portal.common.util.AppConfigXML;
import com.eventattend.portal.common.util.AppConfigXMLInfo;
import com.eventattend.portal.db4o.util.Db4oXML;
import com.eventattend.portal.socialmedia.util.SocialMediaKeysGenerator;
import com.eventattend.portal.web.bean.SessionBean;

public class ApplicationContextListener implements ServletContextListener,AppConfigXMLInfo {
	private ServletContext context = null;
	
	private static String fileSeperator = System.getProperty("file.separator");
	
	public static final String APP_CONFIG_FILE = "App-Config.xml";
	
	/**
	 * Constructor for ApplicationContextListener class.
	 */
	public ApplicationContextListener() {}
	/**
	 * Method is invoked when the Web Application has been removed and is no longer able to accept requests.
	 * @param event ServletContextEvent
	 */
	public void contextDestroyed(ServletContextEvent event) {
		this.context = null;
	}
	/**
	 * Method is invoked when the Web Application is ready to service requests.
	 * @param event ServletContextEvent
	 */
	public void contextInitialized(ServletContextEvent event) {
		String applicationMode = null;
		
		this.context = event.getServletContext();    
		try {

			/* Read the APP_CONFIG_FILE from the context */
			
			String a[]=event.getServletContext().getRealPath(fileSeperator).split("server");
			StringTokenizer stt = new StringTokenizer(a[1],fileSeperator);			
			String profile = stt.nextToken();
			ApplicationConfig.setAppConfigFilePath(a[0]+"server"+ fileSeperator +profile+ fileSeperator + "deploy" + fileSeperator + APP_CONFIG_FILE);

			context.log("Loading Application configuration settings from " + ApplicationConfig.getAppConfigFilePath());
			System.setProperty("AppConfigXMLPath", ApplicationConfig.getAppConfigFilePath());
			
			AppConfigXML.readAppConfigXMLFile();
			
			applicationMode = APP_MODE;
			
			System.out.println("The Application Mode is set to "+applicationMode);
			
			if(applicationMode != null){
				
				System.out.println("Initialising Social Media Keys...");
				
				if(applicationMode.equalsIgnoreCase("development")){
					
					SocialMediaKeysGenerator.initialiseSocialMediaKeysForDevelopment();
					
				}else if(applicationMode.equalsIgnoreCase("staging")) {
					
					SocialMediaKeysGenerator.initialiseSocialMediaKeysForStaging(); 
					
				}else if(applicationMode.equalsIgnoreCase("production")) {
					
					SocialMediaKeysGenerator.initialiseSocialMediaKeysForProduction(); 
					
				}else{
					
					System.out.println("Unable to load the keys for social media");
					
				}
			}
			
			
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
