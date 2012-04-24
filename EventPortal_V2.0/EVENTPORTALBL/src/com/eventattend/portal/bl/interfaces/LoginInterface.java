package com.eventattend.portal.bl.interfaces;

import java.util.Collection;

public interface LoginInterface {

	public Collection isUserExists(); 
	public Collection registerUser();
	public boolean sendUserMailConfirmation();
}
