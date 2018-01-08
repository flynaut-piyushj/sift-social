package com.sift.apis.service;

import org.springframework.security.core.Authentication;

import com.sift.apis.beans.ChangePassword;
import com.sift.apis.beans.MasterDataWrapper;

public interface UtilityService 
{
	public MasterDataWrapper getMasterData();

	public void forgotPassword(String email);

	public void changePassword(ChangePassword passsword, Authentication authentication);

	public String resetPassword(String forgotPassCode);
}
