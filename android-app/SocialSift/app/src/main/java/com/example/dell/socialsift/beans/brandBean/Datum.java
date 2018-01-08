package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/6/2017.
 */

public class Datum {

    @SerializedName("brandDtlsId")
    @Expose
    private String brandDtlsId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categories")
    @Expose
    private Object categories;
    @SerializedName("brandLogo")
    @Expose
    private String brandLogo;
    @SerializedName("coverPic")
    @Expose
    private Object coverPic;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("isSubscribed")
    @Expose
    private Integer isSubscribed;
    @SerializedName("subscribedCount")
    @Expose
    private Integer subscribedCount;
    @SerializedName("ownerId")
    @Expose
    private String ownerId;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;

    public String getBrandDtlsId() {
        return brandDtlsId;
    }

    public void setBrandDtlsId(String brandDtlsId) {
        this.brandDtlsId = brandDtlsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCategories() {
        return categories;
    }

    public void setCategories(Object categories) {
        this.categories = categories;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public Object getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(Object coverPic) {
        this.coverPic = coverPic;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(Integer isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public Integer getSubscribedCount() {
        return subscribedCount;
    }

    public void setSubscribedCount(Integer subscribedCount) {
        this.subscribedCount = subscribedCount;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
