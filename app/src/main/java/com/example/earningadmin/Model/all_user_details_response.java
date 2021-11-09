package com.example.earningadmin.Model;

import com.google.gson.annotations.SerializedName;

public class all_user_details_response {
    @SerializedName("total_user")
    private String total_user;
    @SerializedName("total_user_balance")
    private String total_user_balance;

    public String getTotal_user() {
        return total_user;
    }

    public void setTotal_user(String total_user) {
        this.total_user = total_user;
    }

    public String getTotal_user_balance() {
        return total_user_balance;
    }

    public void setTotal_user_balance(String total_user_balance) {
        this.total_user_balance = total_user_balance;
    }
}
