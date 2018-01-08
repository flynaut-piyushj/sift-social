package com.sift.apis.beans;

public class State 
{
	private String id;
	private String name;
	private String countryDtlId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryDtlId() {
		return countryDtlId;
	}
	public void setCountryDtlId(String countryDtlId) {
		this.countryDtlId = countryDtlId;
	}
	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", countryDtlId=" + countryDtlId + "]";
	}
	
}
