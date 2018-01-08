package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.InvalidValueException;
import com.sift.apis.exception.ServiceException;

public class MailChimpList
{
	private  String name;
	private ContactForList contact;

	
	// give user a permission-reminder like 'You signed up for updates on our website'
	@JsonProperty("permission_reminder")
	private String permissionReminder;
	
	@JsonProperty("use_archive_bar")
	private String useArchiveBar;
	
	@JsonProperty("campaign_defaults")
	private CampaignDefaultForList defaultCampaign;
	
	public MailChimpList() {
		super();
	}

	@JsonProperty("notify_on_subscribe")
	private String notifyOnSubscribe;
	
	@JsonProperty("notify_on_unsubscribe")
	private String notifyOnUnsubscribe;
	
	@JsonProperty("email_type_option")
	private boolean emailTypeOption;
	
	public boolean isEmailTypeOption() {
		return emailTypeOption;
	}
	public void setEmailTypeOption(boolean emailTypeOption) {
		this.emailTypeOption = emailTypeOption;
	}

	public static final String VISIBILITY_PUB="pub";
	public static final String VISIBILITY_PRV="prv";
	
	private String visibility;

	public MailChimpList(String name, ContactForList contact, String permissionReminder,
			CampaignDefaultForList defaultCampaign, boolean emailTypeOption) {
		super();
		
		if(name=="" || name==null)
			throw new ServiceException("In MailChimpList: name");
		else if(contact==null)
			throw new ServiceException("In MailChimpList: contact");
		else if(permissionReminder=="" || permissionReminder==null)
			throw new ServiceException("In MailChimpList: permissionReminder");
		else if(defaultCampaign==null)
			throw new ServiceException("In MailChimpList: defaultCampaign");
		else
		{
			this.name = name;
			this.contact = contact;
			this.permissionReminder = permissionReminder;
			this.defaultCampaign = defaultCampaign;
			this.emailTypeOption = emailTypeOption;
		}
	}
	public MailChimpList(String name, ContactForList contact, String permissionReminder, String useArchiveBar,
			CampaignDefaultForList defaultCampaign, String notifyOnSubscribe, String notifyOnUnsubscribe,
			boolean emailTypeOption, String visibility) {
		this(name, contact, permissionReminder, defaultCampaign, emailTypeOption);
		if(visibility!=MailChimpList.VISIBILITY_PRV || visibility!=MailChimpList.VISIBILITY_PUB)
			throw new InvalidValueException("In MailChimpList: 'visibility' Required values ['"+VISIBILITY_PRV+"','"+VISIBILITY_PUB+"']");
		else
		{
			this.visibility = visibility;
			this.useArchiveBar = useArchiveBar;
			this.notifyOnSubscribe = notifyOnSubscribe;
			this.notifyOnUnsubscribe = notifyOnUnsubscribe;
		}
	}
	
	@Override
	public String toString() {
		return "MailChimpList [name=" + name + ", contact=" + contact + ",  permissionReminder="
				+ permissionReminder + ", useArchiveBar=" + useArchiveBar + ", defaultCampaign=" + defaultCampaign
				+ ", notifyOnSubscribe=" + notifyOnSubscribe + ", notifyOnUnsubscribe=" + notifyOnUnsubscribe
				+ ", emailTypeOption=" + emailTypeOption + ", visibility=" + visibility + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ContactForList getContact() {
		return contact;
	}
	public void setContact(ContactForList contact) {
		this.contact = contact;
	}
	public String getPermissionReminder() {
		return permissionReminder;
	}
	public void setPermissionReminder(String permissionReminder) {
		this.permissionReminder = permissionReminder;
	}
	public String getUseArchiveBar() {
		return useArchiveBar;
	}
	public void setUseArchiveBar(String useArchiveBar) {
		this.useArchiveBar = useArchiveBar;
	}
	public CampaignDefaultForList getDefaultCampaign() {
		return defaultCampaign;
	}
	public void setDefaultCampaign(CampaignDefaultForList defaultCampaign) {
		this.defaultCampaign = defaultCampaign;
	}
	public String getNotifyOnSubscribe() {
		return notifyOnSubscribe;
	}
	public void setNotifyOnSubscribe(String notifyOnSubscribe) {
		this.notifyOnSubscribe = notifyOnSubscribe;
	}
	public String getNotifyOnUnsubscribe() {
		return notifyOnUnsubscribe;
	}
	public void setNotifyOnUnsubscribe(String notifyOnUnsubscribe) {
		this.notifyOnUnsubscribe = notifyOnUnsubscribe;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public static String getVisibilityPub() {
		return VISIBILITY_PUB;
	}
	public static String getVisibilityPrv() {
		return VISIBILITY_PRV;
	}	
}