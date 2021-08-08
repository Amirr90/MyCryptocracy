package com.e.cryptocracy.repositories;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.e.apiResponse.CurrencyResponse;
import com.e.apiResponse.TrendingCoinResponse;
import com.e.cryptocracy.apiInterface.Api;
import com.e.cryptocracy.appDao.AppDao;
import com.e.cryptocracy.appDatabase.AppDatabase;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.modals.GraphModel;
import com.e.cryptocracy.modals.SearchedCoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUrl;
import com.e.cryptocracy.utility.AppUtils;
import com.e.pagingSource.ItemDataFactory;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.e.pagingSource.ItemDataSource.PAGE_SIZE;

@Singleton
public class ApiRepository {
    private static final String TAG = "ApiRepository";
    Api api;
    AppDao appDao;
    OkHttpClient.Builder builder;
    LiveData<List<CoinModal>> userList;
    MutableLiveData<List<SearchedCoinModal>> allCoinList;
    LiveData<List<CoinCategoryModal>> categoryList;
    MutableLiveData<List<CurrencyModel>> currencyList;
    MutableLiveData<List<TrendingCoinResponse.TrendingCoins>> trendingList;
    MutableLiveData<Object> exchangeRates;
    MutableLiveData<Object> coinDetail;
    MutableLiveData<GraphModel> graphData;
    MutableLiveData<Object> coinExchangeByIdList;
    MutableLiveData<Object> tweetList;

    public LiveData<PagedList<CoinModal>> pagedCoinList;
    LiveData<PageKeyedDataSource<Integer, CoinModal>> pagedItemSource;

    @Inject
    public ApiRepository(Api api, OkHttpClient.Builder builder) {
        this.api = api;
        this.builder = builder;
        appDao = AppDatabase.getDatabase(App.context).getAppDao();

        userList = appDao.getCoins();
        categoryList = appDao.getAllCategory();

        ItemDataFactory itemDataSource = new ItemDataFactory(api);
        pagedItemSource = itemDataSource.getLiveData();


        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        Log.d(TAG, "ApiRepository: ");

        pagedCoinList = (new LivePagedListBuilder(itemDataSource, config).build());
    }


    public LiveData<List<CoinModal>> getCoins(String page) {
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

    public LiveData<List<TrendingCoinResponse.TrendingCoins>> getTrendingCoins() {
        if (null == trendingList) {
            trendingList = new MutableLiveData<>();
            api.loadTendingCoinData().enqueue(new Callback<TrendingCoinResponse>() {
                @Override
                public void onResponse(@NonNull Call<TrendingCoinResponse> call, @NonNull Response<TrendingCoinResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        appDao.deleteCoins();
                        List<TrendingCoinResponse.TrendingCoins> coins = response.body().getCoins();
                        if (null != coins && !coins.isEmpty())
                            trendingList.setValue(coins);
                        else {
                            Log.d(TAG, "onResponse: getAllLatestCoins " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TrendingCoinResponse> call, @NonNull Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        }
        return trendingList;
    }

    public LiveData<Object> getExchangeRates() {
        if (null == exchangeRates) {
            exchangeRates = new MutableLiveData<>();
        }
        api.getExchangeRates().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    exchangeRates.setValue(response.body());
                } else Log.d(TAG, "onResponse: error " + response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {

            }
        });

        return exchangeRates;
    }

    public LiveData<List<SearchedCoinModal>> getAllCoins() {
        //initGetAllCoinsApi();
        if (null == allCoinList) {
            allCoinList = new MutableLiveData<>();
            api.getAllCoins().enqueue(new Callback<List<SearchedCoinModal>>() {
                @Override
                public void onResponse(@NotNull Call<List<SearchedCoinModal>> call, @NotNull Response<List<SearchedCoinModal>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        allCoinList.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<SearchedCoinModal>> call, @NotNull Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        }
        return allCoinList;
    }


    public LiveData<Object> getCoinDetail(String coinId) {
        if (null == coinDetail)
            coinDetail = new MutableLiveData<>();

        api.coinDetail(coinId).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NotNull Call<Object> call, @NotNull Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    coinDetail.setValue(response.body());
                } else Log.d(TAG, "onResponse: error " + response.message());

            }

            @Override
            public void onFailure(@NotNull Call<Object> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return coinDetail;
    }

    public LiveData<GraphModel> getCoinGraphData(String coinId, String days) {
        if (null == graphData)
            graphData = new MutableLiveData<>();

        String currency = AppUtils.getString(AppConstant.CURRENCY, App.context);
        api.graphData(coinId, currency, days).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NotNull Call<Object> call, @NotNull Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();
                    GraphModel graphModel = gson.fromJson(gson.toJson(response.body()), GraphModel.class);
                    graphData.setValue(graphModel);
                } else Log.d(TAG, "onResponse: error " + response.message());

            }

            @Override
            public void onFailure(@NotNull Call<Object> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return graphData;
    }

    public LiveData<Object> coinExchangeList(String coinId, String page) {
        if (null == coinExchangeByIdList)
            coinExchangeByIdList = new MutableLiveData<>();

        api.getExchangeByCoinId(coinId, page).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NotNull Call<Object> call, @NotNull Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    coinExchangeByIdList.setValue(response.body());
                } else Log.d(TAG, "onResponse: error " + response.message());

            }

            @Override
            public void onFailure(@NotNull Call<Object> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return coinExchangeByIdList;
    }

    public LiveData<Object> tweetData(String coinSymbol) {
        if (null == tweetList)
            tweetList = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.coinpaprikaUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        Api api = retrofit.create(Api.class);
        api.getTweetData(coinSymbol).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NotNull Call<Object> call, @NotNull Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tweetList.setValue(response.body());
                } else {
                    tweetList.setValue("");
                    Log.d(TAG, "onResponse: error " + response.message());
                }

            }

            @Override
            public void onFailure(@NotNull Call<Object> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return tweetList;
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
                                appDao.deleteCoins();
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
