package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.balance_response;
import com.example.earningadmin.Model.balance_repositories;

public class Balance {
    public LiveData<balance_response> getBalance(String phone, String password) {
        return balance_repositories.getInstance().getBalance(phone, password);

    }
}
