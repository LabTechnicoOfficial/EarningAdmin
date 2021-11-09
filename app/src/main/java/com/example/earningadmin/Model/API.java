package com.example.earningadmin.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    //all user api
    @GET("all_user_details.php")
    Call<all_user_details_response> getuserDetails(@Query("token") String token);

    @GET("all_user1.php")
    Call<List<allUser_response>> getuser(@Query("token") String token,@Query("page") int page,@Query("limit") int limit);

    //all withdrow api

    @GET("all_withdrow.php")
    Call<allWithdrow_response> getWithdrow(@Query("token") String token);

    //pending withdrow request
    @GET("pending_withdrow_request.php")
    Call<pendingWithdrowRequest_response> getPendingRequest(@Query("token") String token);

    //approve pending withdrow request api

    @FormUrlEncoded
    @POST("withdrow_accept.php")
    Call<response> acceptWithdrow_response(@Field("request_id") String request_id,
                                           @Field("user_id") String user_id,
                                           @Field("user_name") String user_name,
                                           @Field("amount") String amount,
                                           @Field("number") String number,
                                           @Field("request_date") String request_date,
                                           @Field("accept_dtae") String accept_date);

    //admin login
    @GET("login.php")
    Call<response> login(@Query("phone") String phone,@Query("password") String password);
    //admin balance
    @GET("get_balance.php")
    Call<balance_response> getBalance(@Query("phone") String phone,@Query("password") String password);

}
