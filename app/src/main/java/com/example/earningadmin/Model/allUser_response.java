package com.example.earningadmin.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class allUser_response {

        @SerializedName("user_id")
        private String user_id;
        @SerializedName("user_name")
        private String user_name;
        @SerializedName("user_phone")
        private String user_phone;
        @SerializedName("user_mail")
        private String user_mail;
        @SerializedName("user_balance")
        private String user_balance;
        @SerializedName("user_status")
        private String user_status;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }



}
