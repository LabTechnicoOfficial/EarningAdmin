package com.example.earningadmin.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Session_Management {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "SESSION_ID";
    String SESSION_PHONE = "SESSION_PHONE";
    String SESSION_PASSWORD = "SESSION_PASSWORD";

    public Session_Management(Context con) {
        sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String phn, String pass) {
        String phone= phn;
        String password = pass;
        editor.putString(SESSION_PHONE, phone);
        editor.putString(SESSION_PASSWORD, password);
        editor.commit();
    }

    public String getSession() {
        return sharedPreferences.getString(SESSION_KEY, "-1");
    }

    public String getPassword() {
        return sharedPreferences.getString(SESSION_PASSWORD, "-1");
    }
    public String getPhone() {
        return sharedPreferences.getString(SESSION_PHONE, "-1");
    }

    public void removeSession() {
        editor.putString(SESSION_PHONE, "-1").commit();
    }
}
