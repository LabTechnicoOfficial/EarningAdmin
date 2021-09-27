package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.response;
import com.example.earningadmin.Model.login_repositories;

public class Login extends AndroidViewModel {
    public Login(@NonNull Application application) {
        super(application);
    }

    public LiveData<response> login(String phone, String password) {
        return login_repositories.getInstance().login(phone, password);
    }
}
