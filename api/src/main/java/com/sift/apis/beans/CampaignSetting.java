package com.sift.apis.beans;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ValueRequiredException;

public class CampaignSetting 
{
	@JsonProperty("subject_line")
	private String subjectLine;
	@JsonProperty("title")
	private String title;
	@JsonProperty("from_name")
	private String fromName;
	@JsonProperty("reply_to")
	private String replyTo;
	@JsonProperty("use_conversation")
	private boolean useConversation;
	@JsonProperty("to_name")
	private String toName;
	@JsonProperty("folder_id")
	private String folderId;
	@JsonProperty("authenticate")
	private boolean authenticate=true;

	public CampaignSetting() {
	}

	@JsonProperty("auto_footer")
	private boolean autoFooter;
	@JsonProperty("inline_css")
	private boolean inlineCss;
	@JsonProperty("auto_tweet")
	private boolean autoTweet;
	@JsonProperty("auto_fb_post")
	private String[] autoFbPost;
	@JsonProperty("fb_comments")
	private boolean fbComments=true;
	@JsonProperty("template_id")
	private int templateId;
	
	
	public CampaignSetting(String subjectLine, String fromName, String replyTo) {
		super();
		if(subjectLine==null || subjectLine=="")
			throw new ValueRequiredException("In CampaignSetting: subjectLine");
		else if(fromName==null || fromName=="")
			throw new ValueRequiredException("In CampaignSetting: fromName");
		else if(replyTo==null || replyTo=="")
			throw new ValueRequiredException("In CampaignSetting: replyTo");
		else
		{
			this.subjectLine = subjectLine;
			this.fromName = fromName;
			this.replyTo = replyTo;
		}
	}

	public CampaignSetting(String subjectLine, String title, String fromName, String replyTo, boolean useConversation,
			String toName, String folderId, boolean authenticate, boolean autoFooter, boolean inlineCss,
			boolean autoTweet, String[] autoFbPost, boolean fbComments, int templateId) {
		this(subjectLine,fromName,replyTo);
		this.title = title;
		this.useConversation = useConversation;
		this.toName = toName;
		this.folderId = folderId;
		this.authenticate = authenticate;
		this.autoFooter = autoFooter;
		this.inlineCss = inlineCss;
		this.autoTweet = autoTweet;
		this.autoFbPost = autoFbPost;
		this.fbComments = fbComments;
		this.templateId = templateId;
	}

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public boolean isUseConversation() {
		return useConversation;
	}

	public void setUseConversation(boolean useConversation) {
		this.useConversation = useConversation;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public boolean isAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(boolean authenticate) {
		this.authenticate = authenticate;
	}

	public boolean isAutoFooter() {
		return autoFooter;
	}

	public void setAutoFooter(boolean autoFooter) {
		this.autoFooter = autoFooter;
	}

	public boolean isInlineCss() {
		return inlineCss;
	}

	public void setInlineCss(boolean inlineCss) {
		this.inlineCss = inlineCss;
	}

	public boolean isAutoTweet() {
		return autoTweet;
	}

	public void setAutoTweet(boolean autoTweet) {
		this.autoTweet = autoTweet;
	}

	public String[] getAutoFbPost() {
		return autoFbPost;
	}

	public void setAutoFbPost(String[] autoFbPost) {
		this.autoFbPost = autoFbPost;
	}

	public boolean isFbComments() {
		return fbComments;
	}

	public void setFbComments(boolean fbComments) {
		this.fbComments = fbComments;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	@Override
	public String toString() {
		return "CampaignSetting [subjectLine=" + subjectLine + ", title=" + title + ", fromName=" + fromName
				+ ", replyTo=" + replyTo + ", useConversation=" + useConversation + ", toName=" + toName + ", folderId="
				+ folderId + ", authenticate=" + authenticate + ", autoFooter=" + autoFooter + ", inlineCss="
				+ inlineCss + ", autoTweet=" + autoTweet + ", autoFbPost=" + Arrays.toString(autoFbPost)
				+ ", fbComments=" + fbComments + ", templateId=" + templateId + "]";
	}	
	
	
	
	
}