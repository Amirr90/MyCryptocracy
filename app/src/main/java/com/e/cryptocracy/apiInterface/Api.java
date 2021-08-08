package com.e.cryptocracy.apiInterface;

import com.e.apiResponse.CurrencyResponse;
import com.e.apiResponse.TrendingCoinResponse;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.SearchedCoinModal;
import com.e.cryptocracy.utility.ApiKey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/api/v3/coins/markets?per_page=50&sparkline=false")
    Call<List<CoinModal>> getAllLatestCoins(
            @Query("page") String page,
            @Query("vs_currency") String convert,
            @Query("category") String category,
            @Query("order") String order
    );


    @GET("api/v3/coins/categories/list")
    Call<List<CoinCategoryModal>> getAllCoinCategory();

    @Headers({"X-CMC_PRO_API_KEY:" + ApiKey.COIN_MARKET_KEY_1})
    @GET("/v1/fiat/map")
    Call<CurrencyResponse> getAllCurrencyList();

    @GET("api/v3/search/trending")
    Call<TrendingCoinResponse> loadTendingCoinData();

    @GET("api/v3/exchange_rates")
    Call<Object> getExchangeRates();

    @GET("api/v3/coins/list")
    Call<List<SearchedCoinModal>> getAllCoins();

    @GET("api/v3/coins/{coinId}?localization=false&sparkline=true'")
    Call<Object> coinDetail(@Path("coinId") String coinId);

    @GET("api/v3/coins/{id}/market_chart")
    Call<Object> graphData(@Path("id") String id,
                           @Query("vs_currency") String currency,
                           @Query("days") String days);

    @GET("api/v3/coins/{id}/tickers?include_exchange_logo=true")
    Call<Object> getExchangeByCoinId(@Path("id") String coinID,
                                     @Query("page") String page);

    @Headers({
            "x-rapidapi-key: 2d6de22e41msh529033f312cd392p10ec41jsnb1694424915d",
            "x-rapidapi-host: coinpaprika1.p.rapidapi.com"})
    @GET("coins/{coin_id_and_symbol}/twitter")
    Call<Object> getTweetData(@Path("coin_id_and_symbol") String coinIdAndSymbol);

    @GET("api/v3/companies/public_treasury/{id}}")
    Call<Object> public_treasury(@Path("id") String coinID);

}


