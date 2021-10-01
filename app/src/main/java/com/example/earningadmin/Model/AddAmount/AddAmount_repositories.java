package com.example.earningadmin.Model.AddAmount;

import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.ApiUtilize;
import com.example.earningadmin.Model.Method.Method_API;
import com.example.earningadmin.Model.Method.Method_repositories;
import com.example.earningadmin.Model.Method.Method_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAmount_repositories {
    private AddAmount_API addAmountApi;
    private MutableLiveData<String> data;
    private MutableLiveData<List<AddAmount_response>> addAmounts;
    private static AddAmount_repositories addAmountRepositories;

    public AddAmount_repositories() {
        addAmountApi = ApiUtilize.addAmountApi();
    }

    public synchronized static AddAmount_repositories getInstance() {
        if (addAmountRepositories == null)
            return new AddAmount_repositories();
        return addAmountRepositories;
    }

    public MutableLiveData<List<AddAmount_response>> getAddAmounts() {
        if (addAmounts == null) {
            addAmounts = new MutableLiveData<>();
        }

        Call<List<AddAmount_response>> call = addAmountApi.getAmount();
        call.enqueue(new Callback<List<AddAmount_response>>() {
            @Override
            public void onResponse(Call<List<AddAmount_response>> call, Response<List<AddAmount_response>> response) {
                if (response.isSuccessful()) {
                    addAmounts.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AddAmount_response>> call, Throwable t) {

            }
        });
        return addAmounts;
    }

    public MutableLiveData<String> addAmount(String amount) {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<String> call = addAmountApi.addAmount(amount);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<String> deleteAmount(String amountID) {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<String> call = addAmountApi.deleteAmount(amountID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return data;
    }
}
