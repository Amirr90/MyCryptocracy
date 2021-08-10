package com.e.cryptocracy.utility;

import android.os.AsyncTask;
import android.util.Log;

import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.appDao.AppDao;
import com.e.cryptocracy.modals.CoinModal;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDataInToUserTable extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "InsertDataInToUserTable";

    Api api;
    AppDao appDao;
    String page;
    String currency = AppUtils.getString(AppConstant.CURRENCY, App.context);
    String category = AppUtils.getString(AppConstant.CATEGORY, App.context);
    String orderBy = AppUtils.getString(AppConstant.ORDER_BY, App.context);

    public InsertDataInToUserTable(Api api, AppDao appDao, String page) {
        this.api = api;
        this.appDao = appDao;
        this.page = page;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        api.getAllLatestCoins(page, currency,
                category.isEmpty() ? null : category,
                orderBy.isEmpty() ? "market_cap_desc" : orderBy)
                .enqueue(new Callback<List<CoinModal>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CoinModal>> call, @NotNull Response<List<CoinModal>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<CoinModal> coinModals = response.body();
                            appDao.deleteAllCoins();
                            if (!coinModals.isEmpty())
                                for (int a = 0; a < coinModals.size(); a++) {
                                    appDao.addCoins(coinModals.get(a));
                                }
                            else {
                                Log.d(TAG, "onResponse: getAllLatestCoins " + response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CoinModal>> call, @NotNull Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });

        return null;
    }
}