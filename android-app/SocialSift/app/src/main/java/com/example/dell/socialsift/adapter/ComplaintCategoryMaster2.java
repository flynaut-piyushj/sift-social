package com.example.dell.socialsift.adapter;

/**
 * Created by DELL on 8/23/2017.
 */

public class ComplaintCategoryMaster2 {

    private String categoryNm;
    private int iconUrl;

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public int getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(int iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ComplaintCategoryMaster2(String categoryNm, int iconUrl) {

        this.categoryNm = categoryNm;
        this.iconUrl = iconUrl;
    }
}
