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
 * Sep 21, 2010 mmanimaran created the file.
 * 
 */
package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.eventattend.portal.controller.LoginController;
import com.eventattend.portal.dto.LoginDTO;

/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */

public class LoginFactory {
	
	
	public static LoginController checkLayer() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (LoginController) factory.getBean("checkdetails");
	}
	
	public static LoginController isUserExist() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (LoginController) factory.getBean("checkUser");
	}

	public static LoginController loginUserInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (LoginController) factory.getBean("checkUser");
	}
	
	
	
	

}
