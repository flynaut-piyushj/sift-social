package com.sift.web.dao;

import org.springframework.stereotype.Repository;

import com.sift.web.beans.LoginDtlsTo;

@Repository
public class ChangePasswordDAOImpl extends BaseDAOImpl implements ChangePasswordDAO{

	
	@Override
	public int changePassword(String newPassword, LoginDtlsTo loginDtlsTo) {
		int flag=0;		
		//System.out.println("LoggedIn User Id:"+commonUtility.getLoggedUser().getUserId());
		flag=jdbcTemplate.update(sqlProperties.getProperty("update.user.password"),new Object[] {newPassword,loginDtlsTo.getUserId()});
		return flag;
	
	}
	
	
	
}
