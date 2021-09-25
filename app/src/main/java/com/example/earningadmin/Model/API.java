package com.example.earningadmin.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    //all user api
    @GET("all_user.php")
    Call<allUser_response> getuser(@Query("token") String token);

    //all withdrow api

    @GET("all_withdrow.php")
    Call<allWithdrow_response> getWithdrow(@Query("token") String token);

    //pending withdrow request
    @GET("pending_withdrow_request.php")
    Call<pendingWithdrowRequest_response> getPendingRequest(@Query("token") String token);


}
