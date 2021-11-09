package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allUser_repositories {
    private API api;
    private MutableLiveData<List<allUser_response>> data;
    private MutableLiveData<all_user_details_response> details;
    private static allUser_repositories allUser_repositories;

    public allUser_repositories() {
        api = ApiUtilize.api_response();
        data = new MutableLiveData<>();
        details = new MutableLiveData<>();
    }

    public synchronized static allUser_repositories getInstance() {
        if (allUser_repositories == null)
            return new allUser_repositories();
        return allUser_repositories;
    }

    public MutableLiveData<all_user_details_response> getDetails(String token) {
        Call<all_user_details_response> call = api.getuserDetails(token);
        call.enqueue(new Callback<all_user_details_response>() {
            @Override
            public void onResponse(Call<all_user_details_response> call, Response<all_user_details_response> response) {
                if (response.isSuccessful())
                    details.postValue(response.body());
            }

            @Override
            public void onFailure(Call<all_user_details_response> call, Throwable throwable) {

            }
        });
        return details;
    }

    public MutableLiveData<List<allUser_response>> getData(String token,int page,int limit) {
        Call<List<allUser_response>> call = api.getuser(token,page,limit);
        call.enqueue(new Callback<List<allUser_response>>() {
            @Override
            public void onResponse(Call<List<allUser_response>> call, Response<List<allUser_response>> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<allUser_response>> call, Throwable t) {

            }
        });
        return data;
    }
}
