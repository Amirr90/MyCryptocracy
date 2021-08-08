package com.e.apiResponse;

import androidx.annotation.NonNull;

public class ApiStatus {
    String timestamp;
    Integer error_code;
    String error_message;
    Integer elapsed;
    Integer credit_count;
    String notice;
    Integer total_count;

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getError_code() {
        return error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public Integer getCredit_count() {
        return credit_count;
    }

    public String getNotice() {
        return notice;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiStatus{" +
                "timestamp='" + timestamp + '\'' +
                ", error_code=" + error_code +
                ", error_message='" + error_message + '\'' +
                ", elapsed=" + elapsed +
                ", credit_count=" + credit_count +
                ", notice='" + notice + '\'' +
                ", total_count=" + total_count +
                '}';
    }
}
