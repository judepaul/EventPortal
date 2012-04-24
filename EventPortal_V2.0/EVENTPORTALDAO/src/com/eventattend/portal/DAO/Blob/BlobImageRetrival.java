package com.eventattend.portal.DAO.Blob;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.dao.DataAccessObject;
import com.eventattend.portal.db4o.util.Db4oUtil;
import com.eventattend.portal.exceptions.EventPortalException;
import com.eventattend.portal.model.db4o.ProfilePOJO;

public class BlobImageRetrival extends DataAccessObject {

	static ObjectContainer db = null;
	
	public static String retrieveUserProfileImgFile(String profileId) {
		String getImageLoc = null;
		
		try {
			db = getDbConnection();
			ProfilePOJO query = new ProfilePOJO(profileId);
			ObjectSet result = db.get(query);
			getImageLoc = getUserProfileImages(result);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} catch (EventPortalException e){
			e.printStackTrace();
		} finally {
			db.close();
		}
	return getImageLoc;
	}	
	
	public static String getUserProfileImages(List<ProfilePOJO> result) {
		Profile profile = null;
		String destFile = null;
		for (ProfilePOJO profilePOJO : result) {
			profile = new Profile();
			destFile = profilePOJO.getProfileImage().getFileName();
			//destFile = basePath +System.getProperty("file.separator")+profilePOJO.getProfileLargePhoto().getFileName();
			// System.out.println(destFile);
			try {
				profilePOJO.writeUserImgFile(Db4oUtil.imageBasePath);
			} catch (Exception ex) {
				System.out.print(ex.getMessage());
			}
		}
	return destFile;
	}


}
