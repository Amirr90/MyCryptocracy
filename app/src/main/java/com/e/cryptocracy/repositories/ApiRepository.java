package com.e.cryptocracy.repositories;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.apiResponse.CurrencyResponse;
import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.appDao.AppDao;
import com.e.cryptocracy.appDatabase.AppDatabase;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUrl;
import com.e.cryptocracy.utility.AppUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ApiRepository {
    private static final String TAG = "ApiRepository";
    Api api;
    AppDao appDao;
    LiveData<List<CoinModal>> userList;
    LiveData<List<CoinCategoryModal>> categoryList;
    MutableLiveData<List<CurrencyModel>> currencyList;


    @Inject
    public ApiRepository(Api api) {
        this.api = api;
        appDao = AppDatabase.getDatabase(App.context).getAppDao();
        userList = appDao.getAllCoins();
        categoryList = appDao.getAllCategory();
    }

    public LiveData<List<CoinModal>> getAllCoins(String page) {
        initGetUserApi(page);
        return userList;
    }

    private void initGetUserApi(String page) {
        new InsertDataInToUserTable(api, appDao, page).execute();
    }

    public LiveData<List<CurrencyModel>> getAllCurrencyList() {
        if (null == currencyList) {
            currencyList = new MutableLiveData<>();
            initGetCurrencyData();
        }
        return currencyList;
    }

    private void initGetCurrencyData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        api.getAllCurrencyList().enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyResponse> call, @NonNull Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CurrencyResponse currencyResponse = response.body();
                    currencyList.setValue(currencyResponse.getData());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrencyResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public static class InsertDataInToUserTable extends AsyncTask<Void, Void, Void> {

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
                                appDao.deleteAllCoins();
                                List<CoinModal> coinModals = response.body();
                                if (null != coinModals && !coinModals.isEmpty())
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


    //getting category data
    public LiveData<List<CoinCategoryModal>> getAllCoinsCategory() {
        initGetCategoryData();
        return categoryList;
    }

    private void initGetCategoryData() {
        new InsertDataInToCategoryTable(api, appDao).execute();
    }


    public static class InsertDataInToCategoryTable extends AsyncTask<Void, Void, Void> {
        Api api;
        AppDao appDao;

        public InsertDataInToCategoryTable(Api api, AppDao appDao) {
            this.api = api;
            this.appDao = appDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getAllCoinCategory().enqueue(new Callback<List<CoinCategoryModal>>() {
                @Override
                public void onResponse(@NonNull Call<List<CoinCategoryModal>> call, @NonNull Response<List<CoinCategoryModal>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        appDao.deleteAllCategory();
                        List<CoinCategoryModal> categoryModals = response.body();
                        if (null != categoryModals && !categoryModals.isEmpty())
                            for (int a = 0; a < categoryModals.size(); a++) {
                                appDao.addCategory(categoryModals.get(a));
                            }
                        else {
                            Log.d(TAG, "onResponse: getAllLatestCoins " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<CoinCategoryModal>> call, @NonNull Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
            return null;
        }
    }
}
