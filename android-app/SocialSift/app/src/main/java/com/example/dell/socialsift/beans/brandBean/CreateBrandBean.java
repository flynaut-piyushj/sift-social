package com.example.dell.socialsift.beans.brandBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/6/2017.
 */

public class CreateBrandBean {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = null;
    @SerializedName("brandLogo")
    @Expose
    private String brandLogo;

    public CreateBrandBean(String name, List<Integer> categories, String brandLogo, String coverPic, String description) {
        this.name = name;
        this.categories = categories;
        this.brandLogo = brandLogo;
        this.coverPic = coverPic;
        this.description = description;
    }

    @SerializedName("coverPic")
    @Expose

    private String coverPic;
    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
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


}
