package com.example.dell.socialsift.beans.profileBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/3/2017.
 */

public class UserMasterDtl {



    @SerializedName("userMasterDtlsId")
    @Expose
    private Object userMasterDtlsId;
    @SerializedName("androidRegId")
    @Expose
    private Object androidRegId;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("createdTs")
    @Expose
    private Object createdTs;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("modifiedBy")
    @Expose
    private Object modifiedBy;
    @SerializedName("modifiedTs")
    @Expose
    private Object modifiedTs;
    @SerializedName("userProfilePhoto")
    @Expose
    private Object userProfilePhoto;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("confirmPassword")
    @Expose
    private Object confirmPassword;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("userLoginDtlsId")
    @Expose
    private Object userLoginDtlsId;

    public Object getUserMasterDtlsId() {
        return userMasterDtlsId;
    }

    public void setUserMasterDtlsId(Object userMasterDtlsId) {
        this.userMasterDtlsId = userMasterDtlsId;
    }

    public Object getAndroidRegId() {
        return androidRegId;
    }

    public void setAndroidRegId(Object androidRegId) {
        this.androidRegId = androidRegId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Object createdTs) {
        this.createdTs = createdTs;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Object getModifiedTs() {
        return modifiedTs;
    }

    public void setModifiedTs(Object modifiedTs) {
        this.modifiedTs = modifiedTs;
    }

    public Object getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(Object userProfilePhoto) {
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

    public Object getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(Object confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getUserLoginDtlsId() {
        return userLoginDtlsId;
    }

    public void setUserLoginDtlsId(Object userLoginDtlsId) {
        this.userLoginDtlsId = userLoginDtlsId;
    }
}
