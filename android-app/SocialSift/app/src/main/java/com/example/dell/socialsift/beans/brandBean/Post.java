package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/14/2017.
 */

public class Post {


    @SerializedName("postDtlsId")
    @Expose
    private String postDtlsId;
    @SerializedName("postBy")
    @Expose
    private Object postBy;
    @SerializedName("textStatus")
    @Expose
    private String textStatus;
    @SerializedName("createdTs")
    @Expose
    private String createdTs;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public String getPostDtlsId() {
        return postDtlsId;
    }

    public void setPostDtlsId(String postDtlsId) {
        this.postDtlsId = postDtlsId;
    }

    public Object getPostBy() {
        return postBy;
    }

    public void setPostBy(Object postBy) {
        this.postBy = postBy;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}

