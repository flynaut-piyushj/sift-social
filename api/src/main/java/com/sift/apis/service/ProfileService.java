package com.sift.apis.service;

import org.springframework.security.core.Authentication;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.entity.UserMasterDtl;

public interface ProfileService {

	Object getUserDetails(Authentication principle);

	BaseWrapper updateProfile(UserMasterDtl dtls, Authentication authentication);

}
