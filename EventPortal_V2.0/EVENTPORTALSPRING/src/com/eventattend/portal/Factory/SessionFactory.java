package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


import com.eventattend.portal.controller.EventController;
import com.eventattend.portal.controller.SessionController;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.SessionDTO;

public class SessionFactory {
	/**
	 * @method populateSessionInfoPage-To get the all session information 
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static SessionController populateSessionInfoPage(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionDetail");
	}
	/**
	 * @method sessionCommentsList - To list down session comments
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static SessionController sessionCommentsList() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionCommentsList");
	}
	
	/**
	 * @method updateSessionComment - To update session comment
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static SessionController updateSessionComment() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("updateSessionComment");
	}
	
	public static SessionController sessionAttendee() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionAttendees");
	}
	public static SessionController attendSession (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("attendSession");
	}
	public static SessionController leaveSession (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("leaveSession");
	}
	
	
	public static SessionController liveSessionAttendee() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("liveSessionAttendees");
	}
	
	public static SessionController userInLiveSession() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("userInLiveSession");
	}

	public static SessionController sessionInformation (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionInformation");
	}
	public static SessionController sessionSpeakerInformation (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionSpeakerInfo");
	}
	
	public static SessionController resetLiveSessionInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("resetLiveSessionInfo");
	}
	
	public static SessionController getSessionByEventId() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("getSessionByEventId");
	}
	
	public static SessionController sessionSearchTag() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("sessionSearchTag");
	}
	
	public static SessionController saveSessionIdForSpeaker() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("saveSessionIdForSpeaker");
	}
	
	public static SessionController removeSessionIdForSpeaker() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (SessionController) factory.getBean("removeSessionIdForSpeaker");
	}
}
