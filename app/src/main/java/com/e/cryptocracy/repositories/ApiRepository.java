package com.e.cryptocracy.repositories;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.appDao.AppDao;
import com.e.cryptocracy.appDatabase.AppDatabase;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ApiRepository {
    private static final String TAG = "ApiRepository";
    Api api;
    AppDao appDao;
    LiveData<List<CoinModal>> userList;


    @Inject
    public ApiRepository(Api api) {
        this.api = api;
        appDao = AppDatabase.getDatabase(App.context).getAppDao();
        userList = appDao.getAllCoins();
    }

    public LiveData<List<CoinModal>> getAllCoins(String page) {
        initGetUserApi(page);
        return userList;
    }

    private void initGetUserApi(String page) {
        new InsertDataInToUserTable(api, appDao, page).execute();
    }


    public static class InsertDataInToUserTable extends AsyncTask<Void, Void, Void> {

        Api api;
        AppDao appDao;
        String page;
        String currency = "INR";

        public InsertDataInToUserTable(Api api, AppDao appDao, String page) {
            this.api = api;
            this.appDao = appDao;
            this.page = page;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getAllLatestCoins(page, currency).enqueue(new Callback<List<CoinModal>>() {
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

}
