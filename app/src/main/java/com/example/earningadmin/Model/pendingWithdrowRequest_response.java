package com.example.earningadmin.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pendingWithdrowRequest_response {
    public class pendingRequest{
        @SerializedName("request_id")
        private String request_id;
        @SerializedName("user_id")
        private String user_id;
        @SerializedName("user_name")
        private String user_name;
        @SerializedName("amount")
        private String amount;
        @SerializedName("number")
        private String number;
        @SerializedName("request_date")
        private String request_date;
        @SerializedName("method")
        private String method;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getRequest_date() {
            return request_date;
        }

        public void setRequest_date(String request_date) {
            this.request_date = request_date;
        }
    }

    @SerializedName("total_amount")
    private String total_amount;
    @SerializedName("pending_list")
    private List<pendingRequest> pending_list;

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public List<pendingRequest> getPending_list() {
        return pending_list;
    }

    public void setPending_list(List<pendingRequest> pending_list) {
        this.pending_list = pending_list;
    }
}
