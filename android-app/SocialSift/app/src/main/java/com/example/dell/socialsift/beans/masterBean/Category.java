package com.example.dell.socialsift.beans.masterBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/5/2017.
 */

public class Category {


    @SerializedName("id")
    @Expose
    private String id;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
