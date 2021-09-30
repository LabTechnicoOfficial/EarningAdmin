package com.example.earningadmin.Model.ADDMOB;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.ApiUtilize;
import com.example.earningadmin.Model.Commission.Commission_repositories;
import com.example.earningadmin.Model.Commission.Commission_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ADDMOB_repositories {
    private ADDMOB_API addmobApi;
    private MutableLiveData<ADDMOB_response> data;
    private MutableLiveData<String> updateData;
    private static ADDMOB_repositories addmob_repositories;

    public ADDMOB_repositories() {
        addmobApi = ApiUtilize.addmobApi();
    }

    public synchronized static ADDMOB_repositories getInstance() {
        if (addmob_repositories == null)
            return new ADDMOB_repositories();
        return addmob_repositories;
    }

    public MutableLiveData<ADDMOB_response> getAddmob() {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<ADDMOB_response> call = addmobApi.getADDMOB();
        call.enqueue(new Callback<ADDMOB_response>() {
            @Override
            public void onResponse(Call<ADDMOB_response> call, Response<ADDMOB_response> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ADDMOB_response> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<String> updateAddmob(@NonNull String app_id, @NonNull String unit_id) {
        if (updateData == null) {
            updateData = new MutableLiveData<>();
        }

        Call<String> call = addmobApi.updateADDMOB(app_id, unit_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    updateData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return updateData;
    }
}
