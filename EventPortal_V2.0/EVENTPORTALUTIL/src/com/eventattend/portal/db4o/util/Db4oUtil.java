/*
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Kyyba ventures("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into 
 * with Kyyba Ventures.
 * 
 * CHANGE HISTORY
 * ==================================================================================
 * Sep 22, 2010 - mmanimaran created the file.
 * 
 */
package com.eventattend.portal.db4o.util;

import java.io.File;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;

/**
 * Class Description
 *    
 * @version 1.0
 * @author mmanimaran
 * @Date Sep 22, 2010
 * 
 * Copyright 2010-2011 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */
public class Db4oUtil {	

	    // EDIT THESE SETTINGS
	    private static final String DB_FILE_SUFFIX = ".db4o.yap";
	    private static final int PORT = 0;

	    private static ObjectServer objectServer;
		public static String imageBasePath;

	    private static final ThreadLocal dbThreadLocal = new ThreadLocal();

	    public static ObjectContainer getObjectContainer() {
	        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
	        if (oc == null || oc.ext().isClosed()) {
	            oc = getObjectServer().openClient();
	            dbThreadLocal.set(oc);
	        }
	        return oc;
	    }

	    public static void closeDb() {
	        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
	        dbThreadLocal.set(null);
	        if (oc != null) oc.close();
	    }

	    public static ObjectServer getObjectServer() {
	        return getObjectServer(null);
	    }

	    public synchronized static ObjectServer getObjectServer(String name) {
	        return getObjectServer(name, PORT);
	    }

	    public static ObjectServer getObjectServer(String name, int port) {
	        if(name == null){
	            name = "default";
	        }
	        if (objectServer == null) {
	            objectServer = getObjectServerForFilename(name + DB_FILE_SUFFIX, port);
	        }
	        return objectServer;
	    }


	    public static void shutdown() {
	        if (objectServer != null) {
	            objectServer.close();
	        }
	    }

	    public static ObjectServer getObjectServerForFilename(String yapfilename, int port) {
	        File parentDir = getDbDirectory();
	        File dbfile = new File(parentDir, yapfilename);

	        // for replication //////////////////////////
	        Db4o.configure().generateUUIDs(Integer.MAX_VALUE);
	        Db4o.configure().generateVersionNumbers(Integer.MAX_VALUE);

	        // other options
	        Db4o.configure().exceptionsOnNotStorable(true);
	        Db4o.configure().objectClass("java.math.BigDecimal").translate(new com.db4o.config.TSerializable());

	        // now open server
	        ObjectServer objectServer = Db4o.openServer(dbfile.getPath(), port);

	        return objectServer;
	    }

	    private static File getDbDirectory() {
	        // will store database in {user.home}/db4o-data directory
	        String dbfile = System.getProperty("jboss.server.home.dir") + "/db4o";
	        File f = new File(dbfile);
	        if (!f.exists()) {
	            f.mkdirs();
	        }
	        return f;
	    }

	}

