package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ValueRequiredException;

public class CampaignDefaultForList
{
	@JsonProperty("from_name")
	private String fromName;

	@JsonProperty("from_email")
	private String fromEmail;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("language")
	private String language;
	
	public CampaignDefaultForList() {
		super();
	}
	public CampaignDefaultForList(String fromName, String fromEmail, String subject, String language) {
		super();
		if(fromName=="" || fromName==null)
			throw new ValueRequiredException("511");
		else if(fromEmail=="" || fromEmail==null)
			throw new ValueRequiredException("512");
		else if(subject=="" || subject==null)
			throw new ValueRequiredException("513");
		else if(language=="" || language==null)
			throw new ValueRequiredException("514");
		else
		{
			this.fromName = fromName;
			this.fromEmail = fromEmail;
			this.subject = subject;
			this.language = language;
		}
	}
	@Override
	public String toString() {
		return "CampaignDefaultForList [fromName=" + fromName + ", fromEmail=" + fromEmail + ", subject=" + subject
				+ ", language=" + language + "]";
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
