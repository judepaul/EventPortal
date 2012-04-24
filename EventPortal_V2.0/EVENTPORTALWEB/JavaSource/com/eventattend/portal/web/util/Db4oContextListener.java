package com.eventattend.portal.web.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;
import com.eventattend.portal.db4o.util.Db4oServerInfo;
import com.eventattend.portal.db4o.util.Db4oUtil;
import com.eventattend.portal.db4o.util.Db4oXML;
import com.eventattend.portal.web.bean.SessionBean;

public class Db4oContextListener implements ServletContextListener,Db4oServerInfo {
	
	private static String fs = System.getProperty("file.separator");
	// To set Db4o configuration XML file name
	public static final String KEY_DB4O_CONFIG_FILE = "Db4o-ds.xml";
	// To set as context attribute key value for server
	public static final String KEY_DB4O_SERVER = "db4oServer";
	
	private ObjectServer server = null;	

	public void contextInitialized(ServletContextEvent event) {
		close();
		ServletContext context = event.getServletContext();
		String imagePath = context.getRealPath("/images/");
		Db4oUtil.imageBasePath = imagePath;


		/* Read the DB4O_CONFIG_FILE from the context */
		String filePathnew[] = context.getRealPath(fs).split("default");
		ApplicationConfig.setBaseUrlPath(filePathnew[0] + "default" + fs + "deploy" + fs + KEY_DB4O_CONFIG_FILE);
		context.log("Loading Database configuration settings from " + ApplicationConfig.getBaseUrlPath());
		System.setProperty("Db4oConfigXML", ApplicationConfig.getBaseUrlPath());
		Db4oXML.readXMLFile();
		/* End */
		
		File parentDir = getDbDirectory();
		File dbfile = new File(parentDir, FILE);
		String filePath = dbfile.getAbsolutePath();
		server = Db4oClientServer.openServer(filePath, PORT);
		System.out.println("server ==> " +server);
		server.grantAccess(USER, PASS);
		context.setAttribute(KEY_DB4O_SERVER, server);
		context.log("db4o startup on " + filePath);
		
		//SessionBean sessionBean = new SessionBean();
		
		//System.out.println("Resetting Live Session info...");
		
		//sessionBean.resetLiveSessionInfo();
	}

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.removeAttribute(KEY_DB4O_SERVER);
		close();
		context.log("db4o shutdown");
	}

	private void close() {
		if (server != null) {
			server.close();
		}
		server = null;
	}

	// Create a directory to hold db file if not exists
	public static File getDbDirectory() {
		String FILE_SEPERATOR = System.getProperty("file.separator");
		String dbfile = System.getProperty("jboss.server.home.dir")+ FILE_SEPERATOR + "db4o" + FILE_SEPERATOR;
		File f = new File(dbfile);
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}	
	
}
