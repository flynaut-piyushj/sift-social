package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ValueRequiredException;

public class Member
{
	@JsonProperty("email_address")
	private String emailAddress;
	@JsonProperty("email_type")
	private String emailType;
	@JsonProperty("status")
	private String status;
	@JsonProperty("interests")
	private String interests;
	@JsonProperty("language")
	private String language;
	@JsonProperty("vip")
	private String vip;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("ip_signup")
	private String ipSignUp;
	@JsonProperty("timestamp_signup")
	private String timestapSignUp;

	@JsonProperty("ip_opt")
	private String ipOpt;
	@JsonProperty("timestamp_opt")
	private String timestampOpt;
	@JsonProperty("merge_fields")
	private MergeField field;
	
	
	
	public Member() {
	}




	public Member(String emailAddress, String status, MergeField field) {
		super();
		if(emailAddress==null || emailAddress=="")
			throw new ValueRequiredException("In Member: emailAddress");
		else if(status==null || status=="")
			throw new ValueRequiredException("In Member: status");
		else if(field==null || field.getAtu()==null || field.getFname()==null)
			throw new ValueRequiredException("In Member: mergeField");
		else
		{
			this.emailAddress = emailAddress;
			this.status = status;
			this.field = field;
		}
	}




	public Member(String emailAddress, String emailType, String status, String interests,
			String language, String vip, Location location, String ipSignUp, String timestapSignUp, String ipOpt,
			String timestampOpt,MergeField field) {
		this(emailAddress,status,field);
		this.emailType = emailType;
		this.interests = interests;
		this.language = language;
		this.vip = vip;
		this.location = location;
		this.ipSignUp = ipSignUp;
		this.timestapSignUp = timestapSignUp;
		this.ipOpt = ipOpt;
		this.timestampOpt = timestampOpt;
	}




	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getIpSignUp() {
		return ipSignUp;
	}
	public void setIpSignUp(String ipSignUp) {
		this.ipSignUp = ipSignUp;
	}
	public String getTimestapSignUp() {
		return timestapSignUp;
	}
	public void setTimestapSignUp(String timestapSignUp) {
		this.timestapSignUp = timestapSignUp;
	}
	public String getIpOpt() {
		return ipOpt;
	}
	public void setIpOpt(String ipOpt) {
		this.ipOpt = ipOpt;
	}
	public String getTimestampOpt() {
		return timestampOpt;
	}

	public void setTimestampOpt(String timestampOpt) {
		this.timestampOpt = timestampOpt;
	}
	public MergeField getField() {
		return field;
	}

	public void setField(MergeField field) {
		this.field = field;
	}	
	@Override
	public String toString() {
		return "Member [emailAddress=" + emailAddress + ", emailType=" + emailType + ", status=" + status
				+ ", interests=" + interests + ", language=" + language + ", vip=" + vip + ", location=" + location
				+ ", ipSignUp=" + ipSignUp + ", timestapSignUp=" + timestapSignUp + ", ipOpt=" + ipOpt
				+ ", timestampOpt=" + timestampOpt + ", field=" + field + "]";
	}

	
}