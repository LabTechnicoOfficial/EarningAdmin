package com.example.earningadmin.Model.ADDMOB;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ADDMOB_response {
    @SerializedName("app_id")
    @Expose
    public String appId;
    @SerializedName("unit_id")
    @Expose
    public String unitId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
