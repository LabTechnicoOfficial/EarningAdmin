package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.AddAmount.AddAmount_repositories;
import com.example.earningadmin.Model.AddAmount.AddAmount_response;
import com.example.earningadmin.Model.Method.Method_repositories;
import com.example.earningadmin.Model.Method.Method_response;

import java.util.List;

public class AddAmountViewModel extends AndroidViewModel {
    public AddAmountViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<AddAmount_response>> getAmounts() {
        return AddAmount_repositories.getInstance().getAddAmounts();
    }

    public LiveData<String> addAmounts(String amount) {
        return AddAmount_repositories.getInstance().addAmount(amount);
    }

    public LiveData<String> deleteAmount(String amountID) {
        return AddAmount_repositories.getInstance().deleteAmount(amountID);
    }
}
