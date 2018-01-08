package com.sift.apis.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.entity.UserMasterDtl;
import com.sift.apis.dao.BaseDAOImpl;
import com.sift.apis.dao.RegistrationDAO;
import com.sift.apis.utils.CommonUtility;

@Service
@Transactional(rollbackFor=Throwable.class)
public class RegistrationServiceImpl extends BaseDAOImpl implements RegistrationService {

	@Autowired
	private RegistrationDAO registrationDAO;
	
	@Autowired
	private CommonUtility commonUtility;
	
	@Override
	public BaseWrapper postUserDetails(UserMasterDtl userMasterDtl) {
		String userMasterDtlsId = getUUID();
		int isSuccess = registrationDAO.insertUserDetails(userMasterDtlsId,userMasterDtl,getUUID());
		if(isSuccess == 3)
		{
			return new BaseWrapper();
		}else if(isSuccess == 0)
			throw new ServiceException("616");
		else
			throw new ServiceException("612");
	}
}
