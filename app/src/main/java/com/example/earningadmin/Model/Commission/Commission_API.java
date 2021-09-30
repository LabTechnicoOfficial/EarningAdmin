package com.example.earningadmin.Model.Commission;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Commission_API {
    @GET("comission_rate_withdraw.php")
    Call<Commission_response> getCommission();

    //comission_rate_withdraw
    @GET("comission_rate_withdraw_update.php")
    Call<String> updateCommission(@Query("comission") String rate);

    @GET("comission_video_rate.php")
    Call<Commission_response> getVideoCommission();

    //comission_video_update_rate.php
    @GET("comission_video_update_rate.php")
    Call<String> updateVideoCommission(@Query("comission") String rate);

    //watch rate
    @GET("video_watch_rate.php")
    Call<Commission_response> getVideoWatchCommission();

    @GET("video_watch_rate_update.php")
    Call<String> updateVideoWatchCommission(@Query("rate") String rate);
}
