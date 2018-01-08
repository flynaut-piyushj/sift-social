package com.example.dell.socialsift.beans.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Replete Android on 10/5/2016.
 */
public class ResponseMessageWrapper {

    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;


    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }


    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "ResponseMessageWrapper{" +
                "responseMessage=" + responseMessage.toString() +
                '}';
    }
}
