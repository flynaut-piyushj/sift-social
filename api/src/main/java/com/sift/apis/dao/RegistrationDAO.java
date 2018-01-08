package com.sift.apis.dao;

import com.sift.apis.beans.entity.UserMasterDtl;

public interface RegistrationDAO extends BaseDAO{

	int insertUserDetails(String userMasterDtlsId, UserMasterDtl userMasterDtl, String string);

}
