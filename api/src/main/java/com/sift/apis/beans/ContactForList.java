package com.sift.apis.beans;

import com.sift.apis.exception.ValueRequiredException;

public class ContactForList
{
	public String company;
	public String address1;
	public String address2;	
	public String city;
	public String state;
	public String zip;
	public String country;
	public String phone;
	
	public ContactForList(String company, String address1, String address2, String city, String state, String zip, String country,String phone) {
		this(company, address1, city, state, zip, country);
		if(address2==null )
			throw new ValueRequiredException("In ContactForList: address2");
		if(phone==null || phone=="")
			throw new ValueRequiredException("In ContactForList: phone");
		else
			this.phone=phone;
	}

	public ContactForList() {
		super();
	}

	public ContactForList(String company, String address1, String city, String state, String zip, String country) {
		super();
			if(company==null || company=="")
				throw new ValueRequiredException("In ContactForList: company");	
			else if(address1==null || address1=="")
				throw new ValueRequiredException("In ContactForList: address1");
			else if(city==null || city=="")
				throw new ValueRequiredException("In ContactForList: city");
			else if(state==null || state=="")
				throw new ValueRequiredException("In ContactForList: state");
			else if(zip==null || zip=="")
				throw new ValueRequiredException("In ContactForList: zip");
			else if(country==null || country=="")
				throw new ValueRequiredException("In ContactForList: country");
			else
			{
				this.company=company;
				this.address1=address1;
				this.city=city;
				this.state=state;
				this.zip=zip;
				this.country=country;
			}
	}
	
	@Override
	public String toString() {
		return "ContactForList [company=" + company + ", address1=" + address1 + ", address2=" + address2 + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", country=" + country + ", phone=" + phone + "]";
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
