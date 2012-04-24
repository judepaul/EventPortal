package com.eventattend.portal.mail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;



public class MailUtility implements MailServerInfo{
	
	
	String mailSubject = null; 
	String mailContent = null; 
	String uid = null;
	String baseURL = null;
	
	private String mailProvider = "smtp.gmail.com";

	public Properties mailProps() {

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", mailProvider);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		return props;

	}
	
	
	public void mailAlert(String mailTo, String uid, String baseURL, String mailSubject, String mailContent)
	throws MessagingException {
		
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
		this.uid = uid;
		this.baseURL = baseURL;
		mailAlert("smartadmin@visiontss.com", "smartadmin", baseURL, mailTo, uid, mailSubject, mailContent);
	}
	

	public void mailAlert(String mailFrom, String mailFromPwd, String baseURL, String mailTo, String uid, String mailSubject, String mailContent)
			throws MessagingException {
/*		public void mailAlert(String mailFrom, String mailFromPwd, String mailTo, String mailSubject, String mailContent)
		throws MessagingException {
*/
		
		Properties props = new Properties();
		InternetAddress[] mailCCAddress = null;
		

		props = mailProps();

		Session mailSession = Session.getInstance(props);
		
		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject(mailSubject);
		
		String activation_URL = "<html><body>" + baseURL + "/views/account_activate.jsf?activation_id=" +uid +"</body></html>";
		
		//mailContent = mailContent +"\n" +"http://localhost:8080/EVENTPORTAL ";
		mailContent = activation_URL;
		message.setContent(mailContent, "text/html");		

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
		
		message.setFrom(new InternetAddress(mailFrom));
		

		Transport tr = mailSession.getTransport("smtp");
		try {
			tr.connect(mailProvider, mailFrom, mailFromPwd);
		} catch (AuthenticationFailedException ex) {			
			ex.printStackTrace();
			tr.close();
		}
		message.saveChanges();
		
		tr.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		
		tr.close();
	}

}