package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class MergeField 
{
	@JsonProperty("FNAME")
	private String fname;
	@JsonProperty("ATU")
	private String atu;
	@JsonIgnore
	private String tag;
	@JsonIgnore
	private String name;
	@JsonIgnore
	private Type type;
	
	public MergeField() 
	{
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getAtu() {
		return atu;
	}
	public void setAtu(String atu) {
		this.atu = atu;
	}
	static public enum Type 
	{
		text,number,address,phone,date,url,image,radio,dropdown,birthday,zip;	
	}
	@JsonIgnore
	private boolean required;
	@JsonIgnore
	private String defaultValue;
	public MergeField(String fname, String atu) {
		super();
		this.fname = fname;
		this.atu = atu;
	}
	public MergeField(String tag, String name, Type type) {
		super();
		this.tag = tag;
		this.name = name;
		this.type = type;
	}
	public MergeField(String tag, String name, Type type, boolean required, String defaultValue) {
		super();
		this.tag = tag;
		this.name = name;
		this.type = type;
		this.required = required;
		this.defaultValue = defaultValue;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	@Override
	public String toString() {
		return "MergeField [fname=" + fname + ", atu=" + atu + ", tag=" + tag + ", name=" + name + ", type=" + type
				+ ", required=" + required + ", defaultValue=" + defaultValue + "]";
	}	
	
}
