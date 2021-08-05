package com.e.apiResponse;

import com.e.cryptocracy.modals.CoinModal;

import java.util.List;

public class CoinResponse {
    ApiStatus status;
    List<CoinModal> data;

    public ApiStatus getStatus() {
        return status;
    }

    public List<CoinModal> getData() {
        return data;
    }
}

