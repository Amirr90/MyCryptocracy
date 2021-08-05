package com.e.cryptocracy.apiInterface;

import com.e.cryptocracy.modals.CoinModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
   /* @Headers({"X-CMC_PRO_API_KEY: " + ApiKey.KEY_1})
    @GET("/v1/cryptocurrency/listings/latest")
    Call<List<CoinModal>> getAllLatestCoins();*/

    @GET("/api/v3/coins/markets?order=market_cap_desc&per_page=100&sparkline=false")
    Call<List<CoinModal>> getAllLatestCoins(
            @Query("page") String page,
            @Query("vs_currency") String convert
    );
}
