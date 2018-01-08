package com.sift.apis.dao;

public interface UtilityDao 
{
	public Object[] getMasterData();

	public String forgotPassword(String email);

	public int changePassword(String oldPassword, String newPassword, String emailId);

	public int resetPassword(String forgotPassCode);
}
