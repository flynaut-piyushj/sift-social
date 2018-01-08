package com.example.dell.socialsift.beans.brandBean;

import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/6/2017.
 */

public class RandomBrandList {


    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("dataArr")
    @Expose
    private Object dataArr;
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Object getDataArr() {
        return dataArr;
    }

    public void setDataArr(Object dataArr) {
        this.dataArr = dataArr;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

}
