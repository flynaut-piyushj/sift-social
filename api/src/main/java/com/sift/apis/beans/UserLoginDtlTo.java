package com.sift.apis.beans;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserLoginDtlTo implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -590679795677963892L;
	private String userLoginDtlsId;

	private String accessCode;

	private String createdBy;

	private String createdTs;

	private String emailId;

	private String forgotPassCode;

	private byte isActive;

	private byte isEmailVerified;

	private byte isPasswordChanged;

	private byte isTncAccpt;

	private String modifiedBy;

	private String modifiedTs;

	private String password;

	private String tncAccptTs;

	private String userMasterDtlsId;

	private List<GrantedAuthority> grantedAuthorities;
	
	public UserLoginDtlTo() {}
	
	
	@Override
	public String toString() {
		return "UserLoginDtlTo [userLoginDtlsId=" + userLoginDtlsId + ", accessCode=" + accessCode + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", emailId=" + emailId + ", forgotPassCode=" + forgotPassCode
				+ ", isActive=" + isActive + ", isEmailVerified=" + isEmailVerified + ", isPasswordChanged="
				+ isPasswordChanged + ", isTncAccpt=" + isTncAccpt + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", password=" + password + ", tncAccptTs=" + tncAccptTs + ", userMasterDtlsId="
				+ userMasterDtlsId + ", grantedAuthorities=" + grantedAuthorities + "]";
	}


	public List<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public String getUserMasterDtlsId() {
		return userMasterDtlsId;
	}

	public void setUserMasterDtlsId(String userMasterDtlsId) {
		this.userMasterDtlsId = userMasterDtlsId;
	}

	public String getUserLoginDtlsId() {
		return this.userLoginDtlsId;
	}

	public void setUserLoginDtlsId(String userLoginDtlsId) {
		this.userLoginDtlsId = userLoginDtlsId;
	}

	public String getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getForgotPassCode() {
		return this.forgotPassCode;
	}

	public void setForgotPassCode(String forgotPassCode) {
		this.forgotPassCode = forgotPassCode;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsEmailVerified() {
		return this.isEmailVerified;
	}

	public void setIsEmailVerified(byte isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public byte getIsPasswordChanged() {
		return this.isPasswordChanged;
	}

	public void setIsPasswordChanged(byte isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}

	public byte getIsTncAccpt() {
		return this.isTncAccpt;
	}

	public void setIsTncAccpt(byte isTncAccpt) {
		this.isTncAccpt = isTncAccpt;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTncAccptTs() {
		return this.tncAccptTs;
	}

	public void setTncAccptTs(String tncAccptTs) {
		this.tncAccptTs = tncAccptTs;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return this.emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
