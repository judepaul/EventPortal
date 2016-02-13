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
package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.ProfileDTO;

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


public class UserFactory {

	public static UserController userProfilePopulate() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController updateUserAccountSettings() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController updateUserProfilePic() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController updateUserContactInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController userProfileInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController adminProfileInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController updateProfileSettings() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("updateProfileSettings");
	}
	
	public static UserController updateProfileSMInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}

	public static UserController getSocialMediaImageURL() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("User");
	}
	
	public static UserController createAccount() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("createAccount");
	}
	
	public static UserController confirmNewUserMailId() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("confirmNewUserMailId");
	}
	
	public static UserController userTimeZone() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (UserController) factory.getBean("userTimeZone");
	}

	
}
