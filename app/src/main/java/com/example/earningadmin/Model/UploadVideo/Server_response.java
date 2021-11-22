package com.example.earningadmin.Model.UploadVideo;

import com.google.gson.annotations.SerializedName;

public class Server_response {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
