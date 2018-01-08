package com.example.dell.socialsift.beans.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Replete Android on 10/5/2016.
 */
public class ResponseMessage {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion The apiVersion
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
