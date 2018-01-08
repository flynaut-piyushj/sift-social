package com.sift.apis.beans;

public class City 
{
	private String id;
	private String name;
	private String stateDtlId;
	public String getStateDtlId() {
		return stateDtlId;
	}
	public void setStateDtlId(String stateDtlId) {
		this.stateDtlId = stateDtlId;
	}
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
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", stateDtlId=" + stateDtlId + "]";
	}
	 
}
