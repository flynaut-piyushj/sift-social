package com.sift.apis.beans;

import org.codehaus.jackson.annotate.JsonProperty;

public class Location
{
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("longitude")
	private String longitude;
	
	public Location()
	{
		
	}
	public Location(String latitude, String longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}	
}
