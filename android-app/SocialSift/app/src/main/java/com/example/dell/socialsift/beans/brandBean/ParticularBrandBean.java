package com.example.dell.socialsift.beans.brandBean;

import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/11/2017.
 */

public class ParticularBrandBean {


    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("dataArr")
    @Expose
    private Object dataArr;
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
