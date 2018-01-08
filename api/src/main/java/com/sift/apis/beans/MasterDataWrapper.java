package com.sift.apis.beans;

import java.util.List;

public class MasterDataWrapper extends BaseWrapper
{
	private List<Country> country; 
	private List<State> state;
	private List<City> city;
	private List<Category> category;
	public MasterDataWrapper(List<Country> country,List<State> state,List<City> city,List<Category> category,ResponseMessage responseMessage) {
		this.country=country;
		this.state=state;
		this.city=city;
		this.category = category;
		this.setResponseMessage(responseMessage);
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	public List<State> getState() {
		return state;
	}
	public void setState(List<State> state) {
		this.state = state;
	}
	public List<City> getCity() {
		return city;
	}
	public void setCity(List<City> city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "MasterDataWrapper [country=" + country + ", state=" + state + ", city=" + city + ", category="
				+ category + "]";
	}
}
