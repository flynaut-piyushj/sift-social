package com.sift.apis.beans;

import com.sift.apis.beans.entity.UserMasterDtl;

public class UserResponseDetails extends BaseWrapper {
	
	private UserMasterDtl userMasterDtl;

	
	public UserResponseDetails(UserMasterDtl userMasterDtl) {
		this.userMasterDtl = userMasterDtl;
	}

	public UserMasterDtl getUserMasterDtl() {
		return userMasterDtl;
	}

	public void setUserMasterDtl(UserMasterDtl userMasterDtl) {
		this.userMasterDtl = userMasterDtl;
	}
	
	

}
