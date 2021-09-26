package com.example.earningadmin.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class allWithdrow_response {
    public class history {
        @SerializedName("message")
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @SerializedName("total_withdrow")
    private String total_withdrow;
    @SerializedName("history")
    private List<history> history;

    public String getTotal_withdrow() {
        return total_withdrow;
    }

    public void setTotal_withdrow(String total_withdrow) {
        this.total_withdrow = total_withdrow;
    }

    public List<allWithdrow_response.history> getHistory() {
        return history;
    }

    public void setHistory(List<allWithdrow_response.history> history) {
        this.history = history;
    }
}
