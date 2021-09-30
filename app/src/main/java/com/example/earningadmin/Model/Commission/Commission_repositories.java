package com.example.earningadmin.Model.Commission;

import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.API;
import com.example.earningadmin.Model.ApiUtilize;
import com.example.earningadmin.Model.acceptPendingWithdrow_repositories;
import com.example.earningadmin.Model.response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Commission_repositories {

    private Commission_API commissionApi;
    private MutableLiveData<Commission_response> data;
    private MutableLiveData<String> updateData;
    private static Commission_repositories commission_repositories;

    public Commission_repositories() {
        commissionApi = ApiUtilize.commissionApi();
    }

    public synchronized static Commission_repositories getInstance() {
        if (commission_repositories == null)
            return new Commission_repositories();
        return commission_repositories;
    }

    public MutableLiveData<Commission_response> getCommission() {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<Commission_response> call = commissionApi.getCommission();
        call.enqueue(new Callback<Commission_response>() {
            @Override
            public void onResponse(Call<Commission_response> call, Response<Commission_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Commission_response> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<String> updateCommission(String rate) {
        if (updateData == null) {
            updateData = new MutableLiveData<>();
        }

        Call<String> call = commissionApi.updateCommission(rate);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    updateData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return updateData;
    }

    public MutableLiveData<Commission_response> getVideoCommission() {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<Commission_response> call = commissionApi.getVideoCommission();
        call.enqueue(new Callback<Commission_response>() {
            @Override
            public void onResponse(Call<Commission_response> call, Response<Commission_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Commission_response> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<String> updateVideoCommission(String rate) {
        if (updateData == null) {
            updateData = new MutableLiveData<>();
        }

        Call<String> call = commissionApi.updateVideoCommission(rate);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    updateData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return updateData;
    }

    public MutableLiveData<Commission_response> getVideoWatchCommission() {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<Commission_response> call = commissionApi.getVideoWatchCommission();
        call.enqueue(new Callback<Commission_response>() {
            @Override
            public void onResponse(Call<Commission_response> call, Response<Commission_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Commission_response> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<String> updateVideoWatchCommission(String rate) {
        if (updateData == null) {
            updateData = new MutableLiveData<>();
        }

        Call<String> call = commissionApi.updateVideoWatchCommission(rate);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    updateData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return updateData;
    }

}
