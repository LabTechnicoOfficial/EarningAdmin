package com.example.earningadmin.Model.UploadVideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video_response {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
