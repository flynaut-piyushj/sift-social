package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/13/2017.
 */

public class SendPostBean {
    public SendPostBean(String text, List<String> images, List<String> videos) {
        this.text = text;
        this.images = images;
        this.videos = videos;
    }

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("videos")
    @Expose
    private List<String> videos = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

}
