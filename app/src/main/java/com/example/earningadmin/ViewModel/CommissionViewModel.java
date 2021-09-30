package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.Commission.Commission_repositories;
import com.example.earningadmin.Model.Commission.Commission_response;
import com.example.earningadmin.Model.Commission.Commission_update_response;
import com.example.earningadmin.Model.login_repositories;
import com.example.earningadmin.Model.response;

public class CommissionViewModel extends AndroidViewModel {
    public CommissionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Commission_response> getCommission() {
        return Commission_repositories.getInstance().getCommission();
    }

    public LiveData<String> updateCommission(String rate) {
        return Commission_repositories.getInstance().updateCommission(rate);
    }

    public LiveData<Commission_response> getVideoCommission() {
        return Commission_repositories.getInstance().getVideoCommission();
    }

    public LiveData<String> updateVideoCommission(String rate) {
        return Commission_repositories.getInstance().updateVideoCommission(rate);
    }

    public LiveData<Commission_response> getVideoWatchCommission() {
        return Commission_repositories.getInstance().getVideoWatchCommission();
    }

    public LiveData<String> updateVideoWatchCommission(String rate) {
        return Commission_repositories.getInstance().updateVideoWatchCommission(rate);
    }
}
