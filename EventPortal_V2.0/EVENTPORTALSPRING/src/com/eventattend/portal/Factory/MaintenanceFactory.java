package com.eventattend.portal.Factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.eventattend.portal.controller.MaintenanceController;


public class MaintenanceFactory {
	public static MaintenanceController addEvent() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("addEvent");
	}
	public static MaintenanceController events() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("events");
	}
	public static MaintenanceController event() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("event");
	}
	public static MaintenanceController updateEvent() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("updateEvent");
	}
	public static MaintenanceController deleteEvent() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("deleteEvent");
	}
	public static MaintenanceController sessions() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("sessions");
	}
	public static MaintenanceController session() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("session");
	}
	public static MaintenanceController deleteSession() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("deleteSession");
	}
	public static MaintenanceController addSession() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("deleteSession");
	}
	public static MaintenanceController updateSession() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("updateSession");
	}
	public static MaintenanceController attendee() throws Exception {
		ClassPathResource resource = new ClassPathResource("BusinessObjectFactory.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		return (MaintenanceController) factory.getBean("attendee");
	}
}
