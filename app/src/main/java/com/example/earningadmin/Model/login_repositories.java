package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_repositories {
    private API api;
    private MutableLiveData<response> data;
    private static login_repositories login_repositories;

    private login_repositories() {
        api = ApiUtilize.api_response();
        data = new MutableLiveData<>();
    }

    public synchronized static login_repositories getInstance() {
        if (login_repositories == null)
            return new login_repositories();
        return login_repositories;
    }

    public MutableLiveData<response> login(String phone, String password) {
        Call<response> call = api.login(phone, password);
        call.enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

            }
        });
        return data;
    }

}
