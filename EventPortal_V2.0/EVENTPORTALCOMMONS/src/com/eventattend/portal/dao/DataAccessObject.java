package com.eventattend.portal.dao;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.db4o.ObjectContainer;
import com.db4o.cs.Db4oClientServer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.db4o.uniquekeygenerator.UniqueKeyGenerator;
import com.eventattend.portal.db4o.util.Db4oServerInfo;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;

/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Seedcube LLC. All Rights Reserved.
 * This software is the proprietary information of Seedcube LLC.
 * Use is subject to license terms.
 */

public class DataAccessObject implements Db4oServerInfo {

	static HashMap daoMap = new HashMap();
	static ObjectContainer clientConn = null;
	final String INITIALROWIDVALUE = "100000";
	
	public synchronized static DataAccessObject getDAO(String boName) {

		/** To get the Corresponding DAO from Hashmap*/
		DataAccessObject currentDAO = (DataAccessObject) daoMap.get(boName);
		String daoClassName = null;
		if (currentDAO == null) {
			try {

				daoClassName = boName.replaceFirst(".bo.", ".DAO.")
						+ "DAO";

				System.out.println("In DAO Layer" + daoClassName);

				currentDAO = (DataAccessObject) (Class.forName(daoClassName))
						.newInstance();

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			daoMap.put(boName, currentDAO);
		}
		return currentDAO;
	}
	

	public static ObjectContainer getDbConnection()  throws EventPortalException {
		try {
			clientConn = Db4oClientServer.openClient(
							Db4oClientServer.newClientConfiguration(), HOST, PORT, USER, PASS);
		} catch (DatabaseFileLockedException dfle) {
			dfle.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} catch (DatabaseClosedException dce){
			dce.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} catch (Db4oIOException ioe){
			ioe.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} catch (Exception e){
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		}
		return clientConn;
	}

	
	/**
	 * @Method - getNewId
	 * @return String
	 */
	public String getNewId() {
		String newId = INITIALROWIDVALUE;
		newId = UniqueKeyGenerator.generateUniqueKey();
		return newId;
	}


	/**
	 * @Method - read
	 * @param object
	 * @return Collection
	 */
	public Collection read(BusinessObject object) throws EventPortalException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * @Method - save 
	 * @param object
	 * @return boolean
	 * @throws EventPortalException 
	 */
	public boolean save(BusinessObject object) throws EventPortalException {
		return true;
	}

	/**
	 * @Method - update 
	 * @param object
	 * @return boolean
	 * @throws EventPortalException 
	 */
	public boolean update(BusinessObject object) throws EventPortalException {
		return true;
	}

	/**
	 * @Method - save 
	 * @param object
	 * @return boolean
	 * @throws EventPortalException 
	 */
	public boolean delete(BusinessObject object) throws EventPortalException {
		return true;
	}
	
	/**
	 * @Method - save 
	 * @param object
	 * @return boolean
	 * @throws EventPortalException 
	 */
	public boolean isExist(BusinessObject object) throws EventPortalException {
		return true;
	}
	
}
