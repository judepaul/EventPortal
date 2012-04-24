package com.eventattend.portal.dto;

public class MailDTO {

	private String fromMailId = null;
	private String toMailId = null;
	private String mailContent = null;
	private boolean firstMail = false;
	
	private String toMailFirstName = null;
	private String toMailLastName = null;
	
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String getToMailId() {
		return toMailId;
	}
	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public boolean isFirstMail() {
		return firstMail;
	}
	public void setFirstMail(boolean firstMail) {
		this.firstMail = firstMail;
	}
	public String getToMailFirstName() {
		return toMailFirstName;
	}
	public void setToMailFirstName(String toMailFirstName) {
		this.toMailFirstName = toMailFirstName;
	}
	public String getToMailLastName() {
		return toMailLastName;
	}
	public void setToMailLastName(String toMailLastName) {
		this.toMailLastName = toMailLastName;
	}
	
	
}
