package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.balance_response;
import com.example.earningadmin.Model.balance_repositories;

public class Balance extends AndroidViewModel {
    public Balance(@NonNull Application application) {
        super(application);
    }

    public LiveData<balance_response> getBalance(String phone, String password) {
        return balance_repositories.getInstance().getBalance(phone, password);

    }
}
