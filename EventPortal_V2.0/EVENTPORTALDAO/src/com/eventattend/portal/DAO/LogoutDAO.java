package com.eventattend.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;
import com.eventattend.portal.bo.BusinessObject;
import com.eventattend.portal.bo.Login;
import com.eventattend.portal.bo.Logout;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.exceptions.DatabaseException;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.exceptions.UserNotExistException;
import com.eventattend.portal.model.db4o.UserPOJO;

public class LogoutDAO extends DataAccessObject {

	/**
	 * @ method read - To call fuction based on hidden action.
	 * 
	 * @param object
	 * @return Collection
	 * @throws EventPortalException 
	 */
	public Collection read(BusinessObject object) throws EventPortalException {
		Logout logout = null;
		Collection resultCollection = null;
		String hiddenAction = null;
		logout = (Logout) object;
		hiddenAction = logout.getHiddenAction();

		if (hiddenAction != null) {
			if (hiddenAction.equals("isUserLogout")) {
				resultCollection = isUserLogout(logout);
			}
		}
		return resultCollection;
	}
	
	ObjectContainer db = null;
	
	public Collection isUserLogout(Logout logout) throws EventPortalException {
		boolean isExist = false;
		List loginList = null;
		String userId = null;
		String password = null;
		String userLoginStatus = null;
		
		loginList = new ArrayList();
		if (logout != null) {
			userId = logout.getUserId();
		}
		try {
			db = getDbConnection();
			ObjectSet<UserPOJO> results = db.get(new UserPOJO());
			if (!results.isEmpty()) {
				while (results.hasNext()) {
					UserPOJO user = (UserPOJO) results.next();
					logout = new Logout();
					if(userId.equals(user.getUserId())){
						user.setUserStatus("N");
						db.store(user);
						db.commit();
						logout.setUserStatus(user.getUserStatus());
						System.out.println("Logout Details :" + "\n");
						System.out.println("UserId==>" + user.getUserId()
								+ " - Status ==> " + user.getUserStatus());
					}
				}
			}			
			loginList.add(logout);
		}catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (Db4oIOException e) {
			e.printStackTrace();
			throw new DatabaseException("Database Connection Error");
		} finally {
			db.close();
		}
		return loginList;
	}

}
