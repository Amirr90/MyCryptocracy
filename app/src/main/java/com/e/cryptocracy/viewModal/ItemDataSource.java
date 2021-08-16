package com.e.cryptocracy.viewModal;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUrl;
import com.e.cryptocracy.utility.AppUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDataSource extends PageKeyedDataSource<Integer, CoinModal> {

    private static final String TAG = "ItemDataSource";
    public static final int PAGE_SIZE = 50;
    private static int FIRST_PAGE = 1;
    Api api;

    String currency = AppUtils.getString(AppConstant.CURRENCY, App.context);
    String category = AppUtils.getString(AppConstant.CATEGORY, App.context);
    String orderBy = AppUtils.getString(AppConstant.ORDER_BY, App.context);

    public ItemDataSource() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        httpClient.addInterceptor(logging);
        httpClient.dispatcher(dispatcher);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.coinGeckoUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        api = retrofit.create(Api.class);

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, CoinModal> callback) {


        Log.d(TAG, "loadInitial: " + params);
        api.getAllLatestCoins(String.valueOf(FIRST_PAGE), currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy)
                .enqueue(new Callback<List<CoinModal>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CoinModal>> call, @NotNull Response<List<CoinModal>> response) {
                        AppUtils.hideDialog();
                        if (response.code() == 200 && response.body() != null) {
                            callback.onResult(response.body(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CoinModal>> call, @NotNull Throwable t) {
                        AppUtils.hideDialog();
                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });


    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CoinModal> callback) {
        Log.d(TAG, "loadBefore: " + params.key);
        api.getAllLatestCoins(String.valueOf(params.key), currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy)
                .enqueue(new Callback<List<CoinModal>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CoinModal>> call, @NotNull Response<List<CoinModal>> response) {
                        AppUtils.hideDialog();
                        if (response.code() == 200 && response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CoinModal>> call, @NotNull Throwable t) {
                        AppUtils.hideDialog();

                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CoinModal> callback) {
        Log.d(TAG, "loadAfter: " + params.key);
        api.getAllLatestCoins(String.valueOf(params.key), currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy)
                .enqueue(new Callback<List<CoinModal>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CoinModal>> call, @NotNull Response<List<CoinModal>> response) {
                        AppUtils.hideDialog();
                        Integer key = response.body() != null ? params.key + 1 : null;
                        if (response.code() == 200 && response.body() != null) {
                            callback.onResult(response.body(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CoinModal>> call, @NotNull Throwable t) {
                        AppUtils.hideDialog();
                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });


    }

}

