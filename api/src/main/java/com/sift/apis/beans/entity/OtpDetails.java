package com.sift.apis.beans.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the otp_details database table.
 * 
 */
@Entity
@Table(name="otp_details")
public class OtpDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3208436350740039385L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OTP_ID")
	private int otpId;

	@NotNull(message="626")
	@NotEmpty(message="626")
	@Column(name="CELLNUMBER")
	private String cellnumber;

	@NotNull(message="805")
	@NotEmpty(message="805")
	@Column(name="DEVICE_INFO")
	private String deviceInfo;

	@Column(name="NUM_OF_ATTEMPTS")
	private int numOfAttempts;

	@Column(name="OTP")
	private int otp;

	private String createdTs;
	
	private String modifiedTs;
	
	public OtpDetails() {
	}

	public String getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public String getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public int getOtpId() {
		return this.otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public String getCellnumber() {
		return this.cellnumber;
	}

	public void setCellnumber(String cellnumber) {
		this.cellnumber = cellnumber;
	}

	public String getDeviceInfo() {
		return this.deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public int getNumOfAttempts() {
		return this.numOfAttempts;
	}

	public void setNumOfAttempts(int numOfAttempts) {
		this.numOfAttempts = numOfAttempts;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	public Object[] convertToInsertparams() {
		
		Object[] paramsToInsert = new Object[] {this.getCellnumber(), this.getDeviceInfo(), this.getNumOfAttempts(), this.getOtp()};
		return paramsToInsert;
	}

	@Override
	public String toString() {
		return "OtpDetails [otpId=" + otpId + ", cellnumber=" + cellnumber + ", deviceInfo=" + deviceInfo
				+ ", numOfAttempts=" + numOfAttempts + ", otp=" + otp + "]";
	}
	
	

}