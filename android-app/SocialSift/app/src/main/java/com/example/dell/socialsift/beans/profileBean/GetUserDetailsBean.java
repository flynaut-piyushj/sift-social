package com.example.dell.socialsift.beans.profileBean;

import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 10/3/2017.
 */

public class GetUserDetailsBean {

    @SerializedName("userMasterDtl")
    @Expose
    private UserMasterDtl userMasterDtl;
    @SerializedName("responseMessage")
    @Expose
    private ResponseMessage responseMessage;

    public UserMasterDtl getUserMasterDtl() {
        return userMasterDtl;
    }

    public void setUserMasterDtl(UserMasterDtl userMasterDtl) {
        this.userMasterDtl = userMasterDtl;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }
}
