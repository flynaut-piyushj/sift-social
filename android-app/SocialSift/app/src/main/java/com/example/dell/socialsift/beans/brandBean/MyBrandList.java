package com.example.dell.socialsift.beans.brandBean;

import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 10/11/2017.
 */

public class MyBrandList {

    @SerializedName("dataArr")
    @Expose
    private List<List<DataArr>> dataArr = null;
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public List<List<DataArr>> getDataArr() {
        return dataArr;
    }

    public void setDataArr(List<List<DataArr>> dataArr) {
        this.dataArr = dataArr;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }
}
