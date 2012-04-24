package com.eventattend.portal.mail;

import com.eventattend.portal.db4o.util.Db4oXML;

public interface MailServerInfo {
	  
	  public String   ADMINUID = String.valueOf(MailXML.value.get("smartadminuid"));  
	  
	  public String   ADMINPWD = String.valueOf(MailXML.value.get("smartadminpwd"));  

}
