package com.sift.apis.beans;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sift.apis.exception.ValueRequiredException;


public class CampaignVariateSetting
{
	public CampaignVariateSetting() {
	}

	@JsonProperty("winner_criteria")
	private String winnerCriteria;
	
	@JsonProperty("wait_time")
	private int waitTime;
	
	@JsonProperty("test_size")
	private int testSize;
	
	@JsonProperty("subject_lines")
	private String[] subjectLines;
	
	@JsonProperty("send_times")
	private String[] sendTimes;
	
	@JsonProperty("from_names")
	private String[] fromNames;
	
	@JsonProperty("reply_to_addresses")
	private String[] replyToAddress;
	
	public CampaignVariateSetting(String winnerCriteria)
	{
		if(winnerCriteria==null || winnerCriteria=="")
			throw new ValueRequiredException("In CampaignVariate: winnerCriteria");
		else
			this.winnerCriteria=winnerCriteria;
	}

	public CampaignVariateSetting(String winnerCriteria, int waitTime, int testSize, String[] subjectLines,
			String[] sendTimes, String[] fromNames, String[] replyToAddress) {
		this(winnerCriteria);
		this.waitTime = waitTime;
		this.testSize = testSize;
		this.subjectLines = subjectLines;
		this.sendTimes = sendTimes;
		this.fromNames = fromNames;
		this.replyToAddress = replyToAddress;
	}

	public String getWinnerCriteria() {
		return winnerCriteria;
	}

	public void setWinnerCriteria(String winnerCriteria) {
		this.winnerCriteria = winnerCriteria;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getTestSize() {
		return testSize;
	}

	public void setTestSize(int testSize) {
		this.testSize = testSize;
	}

	public String[] getSubjectLines() {
		return subjectLines;
	}

	public void setSubjectLines(String[] subjectLines) {
		this.subjectLines = subjectLines;
	}

	public String[] getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(String[] sendTimes) {
		this.sendTimes = sendTimes;
	}

	public String[] getFromNames() {
		return fromNames;
	}

	public void setFromNames(String[] fromNames) {
		this.fromNames = fromNames;
	}

	public String[] getReplyToAddress() {
		return replyToAddress;
	}

	public void setReplyToAddress(String[] replyToAddress) {
		this.replyToAddress = replyToAddress;
	}

	@Override
	public String toString() {
		return "CampaignVariateSetting [winnerCriteria=" + winnerCriteria + ", waitTime=" + waitTime + ", testSize="
				+ testSize + ", subjectLines=" + Arrays.toString(subjectLines) + ", sendTimes="
				+ Arrays.toString(sendTimes) + ", fromNames=" + Arrays.toString(fromNames) + ", replyToAddress="
				+ Arrays.toString(replyToAddress) + "]";
	}	
	
	
}
