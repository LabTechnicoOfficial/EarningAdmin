package com.example.earningadmin.Model.RequestApprove;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.API;
import com.example.earningadmin.Model.ApiUtilize;
import com.example.earningadmin.Model.allUser_repositories;
import com.example.earningadmin.Model.allUser_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Request_approve_repositories {
    private Request_approve_API requestApproveApi;
    private MutableLiveData<Request_approve_response> data;
    private static Request_approve_repositories request_approve_repositories;

    public Request_approve_repositories() {
        requestApproveApi = ApiUtilize.requestApproveApi();
        data = new MutableLiveData<>();
    }

    public synchronized static Request_approve_repositories getInstance() {
        if (request_approve_repositories == null)
            return new Request_approve_repositories();
        return request_approve_repositories;
    }

    public MutableLiveData<Request_approve_response> approveRequest(String requestID, String userID, String userName, String w_amount, String w_number, String accept_number,String method, String req_date, String accept_date) {
        
        Log.d("dataxxx", requestID+" "+userID+" "+userName+" "+ w_amount+" "+w_number+ " "+ accept_number+" "+method+" "+req_date+" "+accept_date);
        Call<Request_approve_response> call = requestApproveApi.requestApproveResponse(requestID, userID, userName, w_amount, w_number, accept_number,method, req_date, accept_date);
        call.enqueue(new Callback<Request_approve_response>() {
            @Override
            public void onResponse(Call<Request_approve_response> call, Response<Request_approve_response> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                } else {
                    Request_approve_response request_approve_response = new Request_approve_response();
                    request_approve_response.setMessage("invalid");
                    data.postValue(request_approve_response);
                }
            }

            @Override
            public void onFailure(Call<Request_approve_response> call, Throwable t) {

            }
        });
        return data;
    }
}
