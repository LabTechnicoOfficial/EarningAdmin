package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.response;
import com.example.earningadmin.Model.acceptPendingWithdrow_repositories;

public class AcceptWithdrow {
    public LiveData<response> accept(String request_id, String user_id, String user_name, String amount, String number, String request_date, String accept_date) {
        return acceptPendingWithdrow_repositories.getInstance().acceptRequest(request_id, user_id, user_name, amount, number, request_date, accept_date);
    }
}
