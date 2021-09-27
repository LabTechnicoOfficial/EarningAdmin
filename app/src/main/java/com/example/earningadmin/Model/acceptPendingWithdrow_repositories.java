package com.example.earningadmin.Model;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class acceptPendingWithdrow_repositories {
    private API api;
    private MutableLiveData<response> data;
    private static acceptPendingWithdrow_repositories acceptPendingWithdrow_repositories;

    public acceptPendingWithdrow_repositories() {
        api = ApiUtilize.api_response();
        data = new MutableLiveData<>();
    }

    public synchronized static acceptPendingWithdrow_repositories getInstance() {
        if (acceptPendingWithdrow_repositories == null)
            return new acceptPendingWithdrow_repositories();
        return acceptPendingWithdrow_repositories;
    }

    public MutableLiveData<response> acceptRequest(String request_id, String user_id, String user_name, String amount, String number, String request_date, String accept_date) {
        Call<response> call = api.acceptWithdrow_response(request_id, user_id, user_name, amount, number, request_date, accept_date);
        call.enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

            }
        });
        return data;
    }
}
