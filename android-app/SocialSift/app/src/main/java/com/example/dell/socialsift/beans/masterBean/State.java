package com.example.dell.socialsift.beans.masterBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 9/29/2017.
 */

public class State {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public State(String name, String id) {
        this.id = id;
        this.name = name;
    }

    @SerializedName("countryDtlId")
    @Expose
    private String countryDtlId;

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

    public String getCountryDtlId() {
        return countryDtlId;
    }

    public void setCountryDtlId(String countryDtlId) {
        this.countryDtlId = countryDtlId;
    }
}
