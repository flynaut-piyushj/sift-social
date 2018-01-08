package com.sift.apis.beans;

import java.util.Arrays;

public class Brand 
{
	private String brandDtlsId;
	private String name;
	private String[] categories;
	private String brandLogo;
	private String coverPic;
	private String description;
	private int isSubscribed;
	private int subscribedCount;

	
	public int getSubscribedCount() {
		return subscribedCount;
	}
	public void setSubscribedCount(int subscribedCount) {
		this.subscribedCount = subscribedCount;
	}
	public int getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(int isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrandDtlsId() {
		return brandDtlsId;
	}
	public void setBrandDtlsId(String brandDtlsId) {
		this.brandDtlsId = brandDtlsId;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	@Override
	public String toString() {
		return "Brand [brandDtlsId=" + brandDtlsId + ", name=" + name + ", categories=" + Arrays.toString(categories)
				+ ", brandLogo=" + brandLogo + ", coverPic=" + coverPic + ", description=" + description
				+ ", isSubscribed=" + isSubscribed + ", subscribedCount=" + subscribedCount + "]";
	}
}