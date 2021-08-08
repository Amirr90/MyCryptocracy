package com.e.pagingSource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, CoinModal> {

    Api api;
    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 100;

    public ItemDataSource(Api api) {
        this.api = api;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, CoinModal> callback) {


        String currency = AppUtils.getString(AppConstant.CURRENCY, App.context);
        String category = AppUtils.getString(AppConstant.CATEGORY, App.context);
        String orderBy = AppUtils.getString(AppConstant.ORDER_BY, App.context);

        api.getAllLatestCoins("1", currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy).enqueue(new Callback<List<CoinModal>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoinModal>> call, @NonNull Response<List<CoinModal>> response) {
                if (response.isSuccessful() && null != response.body()) {
                    callback.onResult(response.body(), null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CoinModal>> call, @NonNull Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CoinModal> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CoinModal> callback) {
        String currency = AppUtils.getString(AppConstant.CURRENCY, App.context);
        String category = AppUtils.getString(AppConstant.CATEGORY, App.context);
        String orderBy = AppUtils.getString(AppConstant.ORDER_BY, App.context);

        api.getAllLatestCoins("1", currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy).enqueue(new Callback<List<CoinModal>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoinModal>> call, @NonNull Response<List<CoinModal>> response) {
                if (response.isSuccessful() && null != response.body()) {
                    int nextKey = (params.key == response.body().size()) ? null : params.key + 1;
                    callback.onResult(response.body(), nextKey);
                }
            }

            @Override
            public void onFailure(Call<List<CoinModal>> call, Throwable t) {

            }
        });
    }
}