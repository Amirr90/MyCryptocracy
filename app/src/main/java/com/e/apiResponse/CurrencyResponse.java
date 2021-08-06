package com.e.apiResponse;

import com.e.cryptocracy.modals.CurrencyModel;

import java.util.List;

public class CurrencyResponse {
    ApiStatus status;
    List<CurrencyModel> data;

    public ApiStatus getStatus() {
        return status;
    }

    public List<CurrencyModel> getData() {
        return data;
    }
}

