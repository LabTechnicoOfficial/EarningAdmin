package com.example.earningadmin.Model.Method;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Method_API {

    @GET("transaction_method_add.php")
    Call<String> addMethod(@Query("method") String method);

    //transaction_method_show.php
    @GET("transaction_method_show.php")
    Call<List<Method_response>> getMethod();

    //transaction_method_delete.php
    @GET("transaction_method_delete.php")
    Call<String> deleteMethod(@Query("id") String methodID);
}
