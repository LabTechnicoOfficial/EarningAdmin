package com.example.earningadmin.Model.RequestApprove;

import com.google.gson.annotations.SerializedName;

public class Request_approve_response {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
