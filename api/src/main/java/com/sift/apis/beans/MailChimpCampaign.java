package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ValueRequiredException;

public class MailChimpCampaign
{
	private String type;
	
	@JsonProperty("recipients")
	private Recipients recipients;
	
	public CampaignSetting getSettings() {
		return settings;
	}

	public void setSettings(CampaignSetting settings) {
		this.settings = settings;
	}

	@JsonProperty("settings")	
	private CampaignSetting settings;

	@JsonProperty("variate_settings")
	private CampaignVariateSetting variateSetting;

	public MailChimpCampaign() {
	}

	public MailChimpCampaign(String type, Recipients recipients, CampaignSetting settings) {
		super();
		if(type==null || type=="")
			throw new ValueRequiredException("In MailChimpCampaign: type ");
		else if(recipients==null)
			throw new ValueRequiredException("In MailChimpCampaign: recipients ");
		else if(settings==null)
			throw new ValueRequiredException("In MailChimpCampaign: setting ");
		else
		{
			this.type = type;
			this.recipients = recipients;
			this.settings = settings;
		}
	}

	public MailChimpCampaign(String type, Recipients recipients, CampaignSetting setting,
			CampaignVariateSetting variateSetting) {
		this(type, recipients, setting);
		this.variateSetting = variateSetting;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Recipients getRecipients() {
		return recipients;
	}

	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}


	public CampaignVariateSetting getVariateSetting() {
		return variateSetting;
	}

	public void setVariateSetting(CampaignVariateSetting variateSetting) {
		this.variateSetting = variateSetting;
	}

	@Override
	public String toString() {
		return "MailChimpCampaign [type=" + type + ", recipients=" + recipients + ", settings=" + settings
				+ ", variateSetting=" + variateSetting + "]";
	}

	
	
	
}