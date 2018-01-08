package com.sift.apis.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.UploadFileResponse;
import com.sift.apis.beans.UserLoginDtlTo;
import com.sift.apis.beans.UserResponseDetails;
import com.sift.apis.beans.entity.UserMasterDtl;
import com.sift.apis.dao.BaseDAOImpl;
import com.sift.apis.dao.ProfileDAO;
import com.sift.apis.exception.ServicesException;
import com.sift.apis.utils.CommonUtility;
import com.sift.apis.utils.UploadUtility;

@Service
@Transactional(rollbackFor=Throwable.class)
public class ProfileServiceImpl extends BaseDAOImpl implements ProfileService{
	
	@Autowired
	private ProfileDAO profileDAO;
	
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private UploadUtility uploadUtility;
	@Override
	public Object getUserDetails(Authentication principle) {
		UserMasterDtl dtl = profileDAO.getUserDetails(((UserLoginDtlTo)principle.getPrincipal()).getUserMasterDtlsId());
		if(dtl != null)
		{
			return new UserResponseDetails(dtl);
		}else
			throw new ServiceException("616");
	} 
	
	@Override
	public BaseWrapper updateProfile(UserMasterDtl dtls, Authentication authentication) {
		if(dtls.getUserProfilePhoto() != null)
		{
		    byte eventPicByteArray[] = Base64.decodeBase64(dtls.getUserProfilePhoto());
		    InputStream fileInputStream = new ByteArrayInputStream(eventPicByteArray);    
			UploadFileResponse eventPicDropboxDetails = null;
		    try {
		    	eventPicDropboxDetails = uploadUtility.uploadFile(fileInputStream, ((UserLoginDtlTo)authentication.getPrincipal()).getUserMasterDtlsId()+".png");
		    	dtls.setUserProfilePhoto(eventPicDropboxDetails.getContentUrl());
			}catch(ServicesException se)
		    {
		    	throw new ServiceException("608");
		    }
		}
		if(profileDAO.updateProfile(((UserLoginDtlTo)authentication.getPrincipal()).getUserMasterDtlsId(),dtls)>0)
			return new BaseWrapper();
		else
			throw new ServiceException("608");
	}
}
