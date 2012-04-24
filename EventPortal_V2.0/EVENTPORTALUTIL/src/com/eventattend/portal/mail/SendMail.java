package com.eventattend.portal.mail;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public String username = null;
	public String password = null;
	public String validateMail(String profileFirstName, String profileLastName, String toProfileFirstName, String toProfileLastName, String profileFromMailId, String profileToMailId, String profileContent) throws MessagingException{
		username = "eventadmin@visiontss.com";
		password = "aslkdf";
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();

		Authenticator auth = new SMTPAuthenticator1();

		//Session mailSession = Session.getDefaultInstance(props, auth);
		Session mailSession = Session.getInstance(props);
		
		mailSession.setDebug(true);
		// Define message
		MimeMessage message = new MimeMessage(mailSession);

		// Set the Mail From_address
		//message.setFrom(new InternetAddress("smartadmin@visiontss.com"));
		//message.setFrom(new InternetAddress("Joe Smith <joe@acme.com>"));
		message.setFrom(new InternetAddress("On behalf of " +profileFirstName + "<" +profileFromMailId+">") );
		// Set the Mail To_address
		//message.addRecipient(Message.RecipientType.TO,new InternetAddress("smartadmin@visiontss.com"));
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(toProfileFirstName  +"<" +profileToMailId +">"));
		
		
		message.setSubject(profileFirstName +" " +profileLastName +" send you an message from EVENTATTEND");

		 // Set message content
		message.setText(profileContent);

		Transport tr = mailSession.getTransport("smtp");
		//Transport transport = mailSession.getTransport();

		try{			
			tr.connect("smtp.gmail.com", username, password);
			
	        //Send the message
	        //tr.send(message);
			tr.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	        tr.close();
			//transport.connect();
		}catch (AuthenticationFailedException ex){
			ex.printStackTrace();
		}catch (Exception ex){
			return "Failure";
		}
		return "Success";
	}
	private class SMTPAuthenticator1 extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}	
}
