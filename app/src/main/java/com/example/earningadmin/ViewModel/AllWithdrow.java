package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.earningadmin.Model.allWithdrow_response;
import com.example.earningadmin.Model.allWithdrow_repositories;
import com.example.earningadmin.Model.pendingWithdrowRequest_response;

public class AllWithdrow extends ViewModel {
    public LiveData<allWithdrow_response> getWithdrow(String token) {
        return allWithdrow_repositories.getInstance().getData(token);
    }

    public LiveData<pendingWithdrowRequest_response> getpendingRequest(String token) {
        return allWithdrow_repositories.getInstance().getPending_data(token);
    }
}
