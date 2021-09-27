package com.example.earningadmin.Model;

import com.google.gson.annotations.SerializedName;

public class balance_response {

    @SerializedName("balance")
    private String balance;



    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
