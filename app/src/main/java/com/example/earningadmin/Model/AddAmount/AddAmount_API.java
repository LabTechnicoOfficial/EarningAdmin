package com.example.earningadmin.Model.AddAmount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddAmount_API {

    @GET("transaction_amount_show.php")
    Call<List<AddAmount_response>> getAmount();

    @GET("transaction_amount_add.php")
    Call<String> addAmount(@Query("amount") String amount);

    @GET("transaction_amount_delete.php")
    Call<String> deleteAmount(@Query("id") String amountID);
}
