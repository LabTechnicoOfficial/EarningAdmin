package com.example.earningadmin.Model;

import com.example.earningadmin.Model.ADDMOB.ADDMOB_API;
import com.example.earningadmin.Model.AddAmount.AddAmount_API;
import com.example.earningadmin.Model.Commission.Commission_API;
import com.example.earningadmin.Model.Method.Method_API;
import com.example.earningadmin.Model.RequestApprove.Request_approve_API;

public class ApiUtilize {
    public ApiUtilize() {
    }

    public static final String BASE_URL = "https://alifew.com/ALive/earning_admin/";

    public static API api_response() {

        return Retrofit_Client.getClient(BASE_URL).create(API.class);
    }

    public static Commission_API commissionApi(){
        return Retrofit_Client.getClient(BASE_URL).create(Commission_API.class);
    }

    public static ADDMOB_API addmobApi(){
        return Retrofit_Client.getClient(BASE_URL).create(ADDMOB_API.class);
    }

    public static Request_approve_API requestApproveApi(){
        return Retrofit_Client.getClient(BASE_URL).create(Request_approve_API.class);
    }

    public static Method_API methodApi(){
        return Retrofit_Client.getClient(BASE_URL).create(Method_API.class);
    }

    public static AddAmount_API addAmountApi(){
        return Retrofit_Client.getClient(BASE_URL).create(AddAmount_API.class);
    }
}
