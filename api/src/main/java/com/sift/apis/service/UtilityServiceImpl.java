package com.sift.apis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sift.apis.beans.Category;
import com.sift.apis.beans.ChangePassword;
import com.sift.apis.beans.City;
import com.sift.apis.beans.Country;
import com.sift.apis.beans.MasterDataWrapper;
import com.sift.apis.beans.ResponseMessage;
import com.sift.apis.beans.State;
import com.sift.apis.beans.UserLoginDtlTo;
import com.sift.apis.constants.Constants;
import com.sift.apis.dao.UtilityDao;
import com.sift.apis.exception.ServicesException;

@Service
public class UtilityServiceImpl implements UtilityService
{
	@Autowired
	private UtilityDao dao;
	
	
	@Override
	public MasterDataWrapper getMasterData() {
		Object[] o = dao.getMasterData();
		@SuppressWarnings("unchecked")
		MasterDataWrapper mdw = new MasterDataWrapper((List<Country>)o[0],(List<State>)o[1],(List<City>)o[2],(List<Category>)o[3],new ResponseMessage(HttpStatus.OK.toString(), Constants.SUCCESS_OK, "2"));
		return mdw;
	}
	@Override
	public void forgotPassword(String email) {
		String isSuccess = dao.forgotPassword(email);
		if(isSuccess.equals("608") || isSuccess.equals("903"))
			throw new ServicesException(isSuccess);
	}
	
	@Override
	public void changePassword(ChangePassword password,Authentication authentication) 
	{
		int isChange = dao.changePassword(password.getOldPassword(),password.getNewPassword(),((UserLoginDtlTo)authentication.getPrincipal()).getEmailId());
		if(isChange == -1)
			throw new ServicesException("902");
		if(isChange == 0)
			throw new ServicesException("904");
	}
	
	@Override
	public String resetPassword(String forgotPassCode)
	{
		return dao.resetPassword(forgotPassCode)>0 ? forgotPassCode : null;
	}
}
