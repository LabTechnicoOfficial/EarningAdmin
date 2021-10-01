package com.example.earningadmin.Model.Method;

import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.ApiUtilize;
import com.example.earningadmin.Model.Commission.Commission_API;
import com.example.earningadmin.Model.Commission.Commission_repositories;
import com.example.earningadmin.Model.Commission.Commission_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Method_repositories {

    private Method_API methodApi;
    private MutableLiveData<String> data;
    private MutableLiveData<List<Method_response>> methods;
    private static Method_repositories method_repositories;

    public Method_repositories() {
        methodApi = ApiUtilize.methodApi();
    }

    public synchronized static Method_repositories getInstance() {
        if (method_repositories == null)
            return new Method_repositories();
        return method_repositories;
    }

    public MutableLiveData<String> addMethod(String method) {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<String> call = methodApi.addMethod(method);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<Method_response>> getMethod() {
        if (methods == null) {
            methods = new MutableLiveData<>();
        }

        Call<List<Method_response>> call = methodApi.getMethod();
        call.enqueue(new Callback<List<Method_response>>() {
            @Override
            public void onResponse(Call<List<Method_response>> call, Response<List<Method_response>> response) {
                if (response.isSuccessful()) {
                    methods.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Method_response>> call, Throwable t) {

            }
        });
        return methods;
    }

    public MutableLiveData<String> deleteMethod(String methodID) {
        if (data == null) {
            data = new MutableLiveData<>();
        }

        Call<String> call = methodApi.deleteMethod(methodID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return data;
    }
}
