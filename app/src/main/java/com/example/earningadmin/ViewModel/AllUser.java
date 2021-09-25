package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.earningadmin.Model.allUser_response;
import com.example.earningadmin.Model.allUser_repositories;
public class AllUser extends ViewModel {
    public LiveData<allUser_response> getUser(String token)
    {
        return allUser_repositories.getInstance().getData(token);
    }
}
