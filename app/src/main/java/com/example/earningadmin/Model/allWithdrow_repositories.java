package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allWithdrow_repositories {
    private API api;
    private MutableLiveData<allWithdrow_response> data;
    private MutableLiveData<pendingWithdrowRequest_response> pending_data;
    private static allWithdrow_repositories allWithdrow_repositories;

    public allWithdrow_repositories() {
        api=ApiUtilize.api_response();
        data=new MutableLiveData<>();
        pending_data=new MutableLiveData<>();
    }
    public synchronized static allWithdrow_repositories getInstance()
    {
        if(allWithdrow_repositories==null)
            return new allWithdrow_repositories();
        return allWithdrow_repositories;
    }

    public MutableLiveData<allWithdrow_response> getData(String token)
    {
        Call<allWithdrow_response> call=api.getWithdrow(token);
        call.enqueue(new Callback<allWithdrow_response>() {
            @Override
            public void onResponse(Call<allWithdrow_response> call, Response<allWithdrow_response> response) {
                if(response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<allWithdrow_response> call, Throwable t) {

            }
        });
        return data;
    }
    public MutableLiveData<pendingWithdrowRequest_response> getPending_data(String token)
    {
        Call<pendingWithdrowRequest_response> call=api.getPendingRequest(token);
        call.enqueue(new Callback<pendingWithdrowRequest_response>() {
            @Override
            public void onResponse(Call<pendingWithdrowRequest_response> call, Response<pendingWithdrowRequest_response> response) {
                if(response.isSuccessful())
                    pending_data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<pendingWithdrowRequest_response> call, Throwable t) {

            }
        });
        return pending_data;
    }
}
