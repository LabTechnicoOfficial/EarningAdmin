package com.example.earningadmin.Model.AddAmount;

import com.google.gson.annotations.SerializedName;

public class AddAmount_response {

    @SerializedName("id")
    String id;
    @SerializedName("amount")
    String amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
