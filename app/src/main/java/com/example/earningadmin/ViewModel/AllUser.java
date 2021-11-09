package com.example.earningadmin.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.earningadmin.Model.allUser_response;
import com.example.earningadmin.Model.allUser_repositories;
import com.example.earningadmin.Model.all_user_details_response;

import java.util.List;

public class AllUser extends ViewModel {
    public LiveData<List<allUser_response>> getUser(String token,int page,int limit)
    {
        return allUser_repositories.getInstance().getData(token,page,limit);
    }
    public LiveData<all_user_details_response> getdetails(String token)
    {
        return allUser_repositories.getInstance().getDetails(token);
    }
}
