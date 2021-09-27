package com.example.earningadmin.Model;

import com.example.earningadmin.Model.Commission.Commission_API;

public class ApiUtilize {
    public ApiUtilize() {
    }

    public static final String BASE_URL = "https://tiger50.com/earning_admin/";

    public static API api_response() {
        return Retrofit_Client.getClient(BASE_URL).create(API.class);
    }

    public static Commission_API commissionApi(){
        return Retrofit_Client.getClient(BASE_URL).create(Commission_API.class);
    }
}
