package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/14/2017.
 */

public class Medium {



    @SerializedName("mediaDtlsId")
    @Expose
    private String mediaDtlsId;
    @SerializedName("mediaType")
    @Expose
    private String mediaType;
    @SerializedName("mediaUrl")
    @Expose
    private String mediaUrl;

    public String getMediaDtlsId() {
        return mediaDtlsId;
    }

    public void setMediaDtlsId(String mediaDtlsId) {
        this.mediaDtlsId = mediaDtlsId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
