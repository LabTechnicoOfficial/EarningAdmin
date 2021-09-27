package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.MutableData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class balance_repositories {
    private API api;
    private MutableLiveData<balance_response> data;
    private static balance_repositories balance_repositories;

    private balance_repositories() {
        api = ApiUtilize.api_response();
        data = new MutableLiveData<>();
    }

    public synchronized static balance_repositories getInstance() {
        if (balance_repositories == null)
            return new balance_repositories();
        return balance_repositories;
    }

    public MutableLiveData<balance_response> getBalance(String phone, String password) {
        Call<balance_response> call = api.getBalance(phone, password);
        call.enqueue(new Callback<balance_response>() {
            @Override
            public void onResponse(Call<balance_response> call, Response<balance_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<balance_response> call, Throwable t) {

            }
        });
        return data;
    }
}
