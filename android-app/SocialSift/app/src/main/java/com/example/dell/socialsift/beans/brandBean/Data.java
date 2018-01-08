package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/11/2017.
 */

public class Data {



    @SerializedName("brandDtlsId")
    @Expose
    private String brandDtlsId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("brandLogo")
    @Expose
    private String brandLogo;
    @SerializedName("coverPic")
    @Expose
    private String coverPic;
    @SerializedName("description")
    @Expose
    private String description;
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
    @SerializedName("posts")
    @Expose
    private List<Post> posts = null;

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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
