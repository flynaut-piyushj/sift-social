package com.example.dell.socialsift.beans.profileBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/3/2017.
 */

public class UpdateProfileBean {


    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userProfilePhoto")
    @Expose
    private String userProfilePhoto;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public UpdateProfileBean(
            String firstName, String lastName, String userProfilePhoto,
                             String emailId, String gender, String occupation,
                             String city, String dateOfBirth) {
        this.contactNo = contactNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userProfilePhoto = userProfilePhoto;
        this.emailId = emailId;
        this.password = password;
        this.gender = gender;
        this.occupation = occupation;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
