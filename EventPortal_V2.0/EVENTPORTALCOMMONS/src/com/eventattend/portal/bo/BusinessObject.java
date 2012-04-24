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
 * Sep 21, 2010 mmanimaran created the file.
 * 
 */

package com.eventattend.portal.bo;

import java.util.Collection;

import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.EventPortalException;

/**
 * Class Description
 *    
 * @version 1.0
 * @author M.Mani Maran
 * @Date 21 Sep 2010
 * 
 * Copyright 2009-2010 Kyyba Ventures,Inc. All Rights Reserved.
 * This software is the proprietary information of Kyyba Ventures Inc.
 * Use is subject to license terms.
 */

public class BusinessObject {

	DataAccessObject dao;
	
	private String hiddenObject = null;
	
	private String hiddenAction = null;

	public BusinessObject() {

	}

	/**
	 * @Method - getDao
	 * @return DataAccessObject
	 */
	public DataAccessObject getDao() {
		if (dao == null) {

			dao = DataAccessObject.getDAO(this.getClass().getName());
		}

		return dao;
	}
	
	/**
	 * @Method read - To read data from DB
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection read() throws EventPortalException {
		return getDao().read(this);
	}
	
	public boolean isExist(BusinessObject object) throws EventPortalException {
		return getDao().isExist(this);
	}
	
	
	public boolean save() throws EventPortalException {
		return getDao().save(this);
	}
	
	public boolean update() throws EventPortalException {
		return getDao().update(this);
	}
	
	public boolean delete() throws EventPortalException {
		return getDao().delete(this);
	}

	public String getHiddenObject() {
		return hiddenObject;
	}

	public void setHiddenObject(String hiddenObject) {
		this.hiddenObject = hiddenObject;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public String getHiddenAction() {
		return hiddenAction;
	}

	public void setHiddenAction(String hiddenAction) {
		this.hiddenAction = hiddenAction;
	}
	
	
	
}
