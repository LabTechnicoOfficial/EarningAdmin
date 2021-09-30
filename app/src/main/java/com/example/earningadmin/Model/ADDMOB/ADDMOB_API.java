package com.example.earningadmin.Model.ADDMOB;

import androidx.annotation.NonNull;

import com.example.earningadmin.Model.Commission.Commission_response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ADDMOB_API {
    @GET("google_add.php")
    Call<ADDMOB_response> getADDMOB();

    @GET("google_add_update.php")
    Call<String> updateADDMOB(@Query("app_id") String app_id,
                              @Query("unit_id") String unit_id);
}
