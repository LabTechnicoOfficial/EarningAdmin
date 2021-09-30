package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allUser_repositories {
    private API api;
    private MutableLiveData<allUser_response> data;
    private static allUser_repositories allUser_repositories;

    public allUser_repositories() {
        api = ApiUtilize.api_response();
        data = new MutableLiveData<>();
    }

    public synchronized static allUser_repositories getInstance() {
        if (allUser_repositories == null)
            return new allUser_repositories();
        return allUser_repositories;
    }

    public MutableLiveData<allUser_response> getData(String token) {
        Call<allUser_response> call = api.getuser(token);
        call.enqueue(new Callback<allUser_response>() {
            @Override
            public void onResponse(Call<allUser_response> call, Response<allUser_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<allUser_response> call, Throwable t) {

            }
        });
        return data;
    }
}
