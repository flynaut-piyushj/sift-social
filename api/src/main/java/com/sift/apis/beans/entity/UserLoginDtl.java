package com.sift.apis.beans.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_login_dtls database table.
 * 
 */
@Entity
@Table(name="user_login_dtls")
@NamedQuery(name="UserLoginDtl.findAll", query="SELECT u FROM UserLoginDtl u")
public class UserLoginDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_LOGIN_DTLS_ID")
	private String userLoginDtlsId;

	@Column(name="ACCESS_CODE")
	private String accessCode;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_TS")
	private String createdTs;

	@Column(name="EMAIL_ID")
	private String emailId;

	@Column(name="FORGOT_PASS_CODE")
	private String forgotPassCode;

	@Column(name="IS_ACTIVE")
	private byte isActive;

	@Column(name="IS_EMAIL_VERIFIED")
	private byte isEmailVerified;

	@Column(name="IS_PASSWORD_CHANGED")
	private byte isPasswordChanged;

	@Column(name="IS_TNC_ACCPT")
	private byte isTncAccpt;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_TS")
	private String modifiedTs;

	private String password;

	@Column(name="TNC_ACCPT_TS")
	private String tncAccptTs;

	private String userMasterDtlsId;
public String getUserMasterDtlsId() {
		return userMasterDtlsId;
	}

	public void setUserMasterDtlsId(String userMasterDtlsId) {
		this.userMasterDtlsId = userMasterDtlsId;
	}

	/*
	//bi-directional many-to-one association to UserMasterDtl
	@OneToMany(mappedBy="userLoginDtl")
	private List<UserMasterDtl> userMasterDtls;

	//bi-directional many-to-one association to UserRoleDtl
	@OneToMany(mappedBy="userLoginDtl")
	private List<UserRoleDtl> userRoleDtls;
*/
	public UserLoginDtl() {
	}
	
	
	// TO DO : Take the value security content
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
	public String toString() {
		return "UserLoginDtl [userLoginDtlsId=" + userLoginDtlsId + ", accessCode=" + accessCode + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", emailId=" + emailId + ", forgotPassCode=" + forgotPassCode
				+ ", isActive=" + isActive + ", isEmailVerified=" + isEmailVerified + ", isPasswordChanged="
				+ isPasswordChanged + ", isTncAccpt=" + isTncAccpt + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", password=" + password + ", tncAccptTs=" + tncAccptTs + ", userMasterDtlsId="
				+ userMasterDtlsId + "]";
	}

/*	public UserMasterDtl getUserMasterDtl() {
		return this.userMasterDtl;
	}

	public void setUserMasterDtl(UserMasterDtl userMasterDtl) {
		this.userMasterDtl = userMasterDtl;
	}

	public List<UserMasterDtl> getUserMasterDtls() {
		return this.userMasterDtls;
	}

	public void setUserMasterDtls(List<UserMasterDtl> userMasterDtls) {
		this.userMasterDtls = userMasterDtls;
	}

	public UserMasterDtl addUserMasterDtl(UserMasterDtl userMasterDtl) {
		getUserMasterDtls().add(userMasterDtl);
		userMasterDtl.setUserLoginDtl(this);

		return userMasterDtl;
	}

	public UserMasterDtl removeUserMasterDtl(UserMasterDtl userMasterDtl) {
		getUserMasterDtls().remove(userMasterDtl);
		userMasterDtl.setUserLoginDtl(null);

		return userMasterDtl;
	}

	public List<UserRoleDtl> getUserRoleDtls() {
		return this.userRoleDtls;
	}

	public void setUserRoleDtls(List<UserRoleDtl> userRoleDtls) {
		this.userRoleDtls = userRoleDtls;
	}

	public UserRoleDtl addUserRoleDtl(UserRoleDtl userRoleDtl) {
		getUserRoleDtls().add(userRoleDtl);
		userRoleDtl.setUserLoginDtl(this);

		return userRoleDtl;
	}

	public UserRoleDtl removeUserRoleDtl(UserRoleDtl userRoleDtl) {
		getUserRoleDtls().remove(userRoleDtl);
		userRoleDtl.setUserLoginDtl(null);

		return userRoleDtl;
	}*/
/*
	@Override
	public String toString() {
		return "UserLoginDtl [userLoginDtlsId=" + userLoginDtlsId + ", accessCode=" + accessCode + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", emailId=" + emailId + ", forgotPassCode=" + forgotPassCode
				+ ", isActive=" + isActive + ", isEmailVerified=" + isEmailVerified + ", isPasswordChanged="
				+ isPasswordChanged + ", isTncAccpt=" + isTncAccpt + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", password=" + password + ", tncAccptTs=" + tncAccptTs + ", userMasterDtl="
				+ userMasterDtl + ", userMasterDtls=" + userMasterDtls + ", userRoleDtls=" + userRoleDtls + "]";
	}*/

	
}