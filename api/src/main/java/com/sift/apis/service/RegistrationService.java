package com.sift.apis.service;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.entity.UserMasterDtl;


public interface RegistrationService {

	BaseWrapper postUserDetails(UserMasterDtl userMasterDtl);



}
