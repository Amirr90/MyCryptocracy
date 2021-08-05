package com.e.cryptocracy.apiInterface;

import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/api/v3/coins/markets?order=market_cap_desc&per_page=100&sparkline=false")
    Call<List<CoinModal>> getAllLatestCoins(
            @Query("page") String page,
            @Query("vs_currency") String convert
    );


    @GET("api/v3/coins/categories/list")
    Call<List<CoinCategoryModal>> getAllCoinCategory();
}
