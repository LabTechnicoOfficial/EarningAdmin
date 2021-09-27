package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.response;
import com.example.earningadmin.Model.login_repositories;
public class Login {
    public LiveData<response> login(String phone,String password)
    {
        return login_repositories.getInstance().login(phone, password);
    }
}
