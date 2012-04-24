package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.eventattend.portal.controller.EventController;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ResultDTO;



public class EventFactory {
	
	/**
	 * @method eventDetails -To collect the data related to event
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static EventController eventDetails (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("eventDetail");
	}
	
	/**
	 * @method listEvents-To get all the existing events
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static EventController listEvents (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("listEvents");
	}
	/**
	 * @method eventAttendees-To get the all attendees of the event
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static EventController eventAttendees (EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("eventAttendees");
	}
	/**
	 * @method populateAgendaSessionPage-To get the all agenda
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static EventController populateAgendaSessionPage(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("listEvents");
	}
	
	/**
	 * @method joinEvent-User joins an event 
	 * @param eventDTO
	 * @return EventController
	 * @throws Exception
	 */
	public static EventController joinEvent(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("joinEvent");
	}
	

	/**
	 * @method unjoinEvent-To unjoin from an event 
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public static EventController unjoinEvent(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("unjoinEvent");
	}
	
	/**
	 * @method firstEventSearchTag-To get the tag for first event
	 * @param eventDTO
	 * @return
	 * @throws Exception
	 */
	public static EventController eventSearchTag(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("eventSearchTag");
	}
	

	
	
	public static EventController getEvents(EventDTO eventDTO) throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (EventController) factory.getBean("listEvents");
	}

	
	
}
