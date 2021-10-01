package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.RequestApprove.Request_approve_repositories;
import com.example.earningadmin.Model.RequestApprove.Request_approve_response;
import com.example.earningadmin.Model.allWithdrow_repositories;
import com.example.earningadmin.Model.pendingWithdrowRequest_response;

public class ApproveRequestViewModel extends AndroidViewModel {
    public ApproveRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Request_approve_response> approveRequest(String requestID, String userID, String userName, String w_amount, String w_number, String accept_number,String method, String req_date, String accept_date) {
        return Request_approve_repositories.getInstance().approveRequest(requestID, userID, userName, w_amount, w_number, accept_number,method, req_date, accept_date);
    }
}
