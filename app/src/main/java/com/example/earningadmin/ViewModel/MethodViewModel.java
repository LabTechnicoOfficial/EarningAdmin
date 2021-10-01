package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.Method.Method_repositories;
import com.example.earningadmin.Model.Method.Method_response;

import java.util.List;

public class MethodViewModel extends AndroidViewModel {
    public MethodViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> addMethod(String method) {
        return Method_repositories.getInstance().addMethod(method);
    }

    public LiveData<List<Method_response>> getMethod() {
        return Method_repositories.getInstance().getMethod();
    }

    public LiveData<String> deleteMethod(String methodID) {
        return Method_repositories.getInstance().deleteMethod(methodID);
    }
}
