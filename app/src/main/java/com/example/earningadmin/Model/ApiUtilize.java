package com.example.earningadmin.Model;

public class ApiUtilize {
    public ApiUtilize() {
    }

    public static final String BASE_URL = "https://tiger50.com/earning_admin/";
    public static API api_response()
    {
        return Retrofit_Client.getClient(BASE_URL).create(API.class);
    }
}
