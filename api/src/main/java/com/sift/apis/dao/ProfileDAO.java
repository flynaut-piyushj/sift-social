package com.sift.apis.dao;

import com.sift.apis.beans.entity.UserMasterDtl;

public interface ProfileDAO extends BaseDAO {

	UserMasterDtl getUserDetails(String username);

	int updateProfile(String username, UserMasterDtl dtls);
}
