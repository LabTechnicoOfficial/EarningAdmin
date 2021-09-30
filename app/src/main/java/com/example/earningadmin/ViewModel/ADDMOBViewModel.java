package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.ADDMOB.ADDMOB_repositories;
import com.example.earningadmin.Model.ADDMOB.ADDMOB_response;
import com.example.earningadmin.Model.Commission.Commission_repositories;

public class ADDMOBViewModel extends AndroidViewModel {
    public ADDMOBViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ADDMOB_response> getADDMOB() {
        return ADDMOB_repositories.getInstance().getAddmob();
    }

    public LiveData<String> updateADDMOB(String app_id, String unit_id) {
        return ADDMOB_repositories.getInstance().updateAddmob(app_id, unit_id);
    }
}
