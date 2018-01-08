package com.sift.apis.beans.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_master_dtls database table.
 * 
 */
@Entity
@Table(name="user_master_dtls")
@NamedQuery(name="UserMasterDtl.findAll", query="SELECT u FROM UserMasterDtl u")
public class UserMasterDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_MASTER_DTLS_ID")
	private String userMasterDtlsId;

	@Column(name="ANDROID_REG_ID")
	private String androidRegId;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_TS")
	private String createdTs;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="IS_ACTIVE")
	private byte isActive;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="MODIFIED_TS")
	private String modifiedTs;

	@Column(name="USER_PROFILE_PHOTO")
	private String userProfilePhoto;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="CONFIRM_PASSWORD")
	private String confirmPassword;

	@Column(name="GENDER")
	private String gender;
	
	@Column(name="OCCUPATION")
	private String occupation;
	
	private String city;
	private String state;
	private String country;
	@Column(name="DATE_OF_BIRTH")
	private String dateOfBirth;
	
	private String userLoginDtlsId;


	public UserMasterDtl() {
	}
	
	public UserMasterDtl(String userMasterDtlsId, String androidRegId, String contactNo, String createdBy, String createdTs,
			String firstName, byte isActive, String lastName, String modifiedBy, String modifiedTs,
			String userProfilePhoto, String emailId, String password, String confirmPassword, String gender,
			String dateOfBirth, String userLoginDtlsId) {
		super();
		this.userMasterDtlsId = userMasterDtlsId;
		this.androidRegId = androidRegId;
		this.contactNo = contactNo;
		this.createdBy = createdBy;
		this.createdTs = createdTs;
		this.firstName = firstName;
		this.isActive = isActive;
		this.lastName = lastName;
		this.modifiedBy = modifiedBy;
		this.modifiedTs = modifiedTs;
		this.userProfilePhoto = userProfilePhoto;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.userLoginDtlsId = userLoginDtlsId;
	}



	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserMasterDtlsId() {
		return userMasterDtlsId;
	}


	public void setUserMasterDtlsId(String userMasterDtlsId) {
		this.userMasterDtlsId = userMasterDtlsId;
	}

	public String getAndroidRegId() {
		return androidRegId;
	}

	public void setAndroidRegId(String androidRegId) {
		this.androidRegId = androidRegId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedTs() {
		return createdTs;
	}


	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public byte getIsActive() {
		return isActive;
	}


	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public String getModifiedTs() {
		return modifiedTs;
	}


	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}


	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}


	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getUserLoginDtlsId() {
		return userLoginDtlsId;
	}


	public void setUserLoginDtlsId(String userLoginDtlsId) {
		this.userLoginDtlsId = userLoginDtlsId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserMasterDtl [userMasterDtlsId=" + userMasterDtlsId + ", androidRegId=" + androidRegId + ", contactNo="
				+ contactNo + ", createdBy=" + createdBy + ", createdTs=" + createdTs + ", firstName=" + firstName
				+ ", isActive=" + isActive + ", lastName=" + lastName + ", modifiedBy=" + modifiedBy + ", modifiedTs="
				+ modifiedTs + ", userProfilePhoto=" + userProfilePhoto + ", emailId=" + emailId + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", gender=" + gender + ", occupation="
				+ occupation + ", city=" + city + ", state=" + state + ", country=" + country + ", dateOfBirth="
				+ dateOfBirth + ", userLoginDtlsId=" + userLoginDtlsId + "]";
	}

	
	
}