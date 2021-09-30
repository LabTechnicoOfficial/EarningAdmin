package com.example.earningadmin.Model.RequestApprove;

import com.example.earningadmin.Model.response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Request_approve_API {
    @FormUrlEncoded
    @POST("withdrow_accept.php")
    Call<Request_approve_response> requestApproveResponse(@Field("request_id") String request_id,
                                           @Field("user_id") String user_id,
                                           @Field("user_name") String user_name,
                                           @Field("amount") String amount,
                                           @Field("number") String number,
                                           @Field("accept_number") String accept_number,
                                           @Field("request_date") String request_date,
                                           @Field("accept_date") String accept_date);
}
