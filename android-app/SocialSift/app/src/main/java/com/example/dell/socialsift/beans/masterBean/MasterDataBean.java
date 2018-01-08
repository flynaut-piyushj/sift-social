package com.example.dell.socialsift.beans.masterBean;

import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 9/29/2017.
 */

public class MasterDataBean {


    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("state")
    @Expose
    private List<State> state = null;
    @SerializedName("city")
    @Expose
    private List<City> city = null;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

}
