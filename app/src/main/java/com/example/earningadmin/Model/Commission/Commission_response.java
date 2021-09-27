package com.example.earningadmin.Model.Commission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commission_response {
    @SerializedName("comission")
    @Expose
    public String comission;

    public String getComission() {
        return comission;
    }

    public void setComission(String comission) {
        this.comission = comission;
    }
}
