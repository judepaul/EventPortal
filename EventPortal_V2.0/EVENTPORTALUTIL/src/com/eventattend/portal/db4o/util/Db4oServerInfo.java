package com.eventattend.portal.db4o.util;

public interface Db4oServerInfo {
	  
	  /**
	   * Enter the computer name of the one that you want to use as server. 
	   */
	  public String   HOST = Db4oXML.value.get("serverHost");  
	  
	  /**
	   * the database file to be used by the server.
	   */
	  public String   FILE = Db4oXML.value.get("serverDBFile");
	   
	  /**
	   * the port to be used by the server.
	   */
	  public int   PORT = Integer.valueOf(Db4oXML.value.get("serverPort")).intValue();
	  
	  /**
	   * the user name for access control.
	   */
	  public String   USER = Db4oXML.value.get("serverUID");
	  
	  /**
	   * the pasword for access control.
	   */
	  public String   PASS = Db4oXML.value.get("serverPwd");

}
