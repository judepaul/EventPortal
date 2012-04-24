package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.eventattend.portal.controller.AttendeeController;
import com.eventattend.portal.dto.AttendeeDTO;

public class AttendeeFactory {

	public static AttendeeController sendMailToAttendees() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("sendAttendeeMail");
	}
	
	public static AttendeeController getAttendees() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("getAttendees");
	}

	public static AttendeeController getMailOptionsForAttendees() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("sendAttendeeMail");
	}
	
	public static AttendeeController updateSessionCommentLikeCount(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}

	public static AttendeeController updateSessionLikeCount(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}

	public static AttendeeController getSessionCommentLike(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}

	public static AttendeeController getSessionLike(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}
	
	public static AttendeeController updateEventLikeCount(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}

	public static AttendeeController getEventLike(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}

	public static AttendeeController getTotalEventLike(AttendeeDTO attendeeDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("updateSessionCommentsLike");
	}
	
	public static AttendeeController getToMailProfileInfo() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (AttendeeController) factory.getBean("sendAttendeeMail");
	}


}
