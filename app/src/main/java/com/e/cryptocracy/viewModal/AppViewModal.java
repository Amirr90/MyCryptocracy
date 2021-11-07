package com.e.cryptocracy.viewModal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.e.apiResponse.TrendingCoinResponse;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.modals.GraphModel;
import com.e.cryptocracy.modals.SearchedCoinModal;
import com.e.cryptocracy.repositories.ApiRepository;

import java.util.List;

import javax.inject.Inject;

public class AppViewModal extends ViewModel {
    private static final String TAG = "AppViewModal";

    @Inject
    ApiRepository apiRepository;
    public LiveData<PagedList<CoinModal>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, CoinModal>> liveDataSource;
    MutableLiveData<Boolean> _loading = new MutableLiveData();

    LiveData<Boolean> loadAdd;

    public LiveData<Boolean> getLoadAdd() {
        return loadAdd;
    }

    @Inject
    public AppViewModal(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }


    public void setItemPagedList() {

        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }

    public LiveData<List<CoinCategoryModal>> getAllCategory() {

        return apiRepository.getAllCoinsCategory();
    }

    public LiveData<List<CurrencyModel>> getAllCurrency() {
        return apiRepository.getAllCurrencyList();
    }

    public LiveData<List<TrendingCoinResponse.TrendingCoins>> getTrendingCoins() {
        return apiRepository.getTrendingCoins();
    }

    public LiveData<Object> getExchangeRates() {
        return apiRepository.getExchangeRates();
    }


    public LiveData<List<SearchedCoinModal>> getAllCoins() {
        return apiRepository.getAllCoins();
    }

    public LiveData<Object> getCoinDetail(String coinId) {
        return apiRepository.getCoinDetail(coinId);
    }

    public LiveData<GraphModel> getCoinGraphData(String coinId, String days) {
        return apiRepository.getCoinGraphData(coinId, days);
    }

    public LiveData<Object> coinExchangeList(String coinId, String page) {
        return apiRepository.coinExchangeList(coinId, page);
    }

    public LiveData<Object> coinTweet(String coinSymbol) {
        return apiRepository.tweetData(coinSymbol);
    }

    public LiveData<Object> coinInvestorData(String coinId) {
        return apiRepository.coinInvestorData(coinId);
    }

    public LiveData<List<CoinModal>> fetchFavCoins() {
        return apiRepository.getFavCoinsList();
    }

    public void setFavIds(String coinIds) {
        apiRepository.fetchFavCoins(coinIds);
    }


    public void loadAdd(Boolean aBoolean) {
        _loading.setValue(aBoolean);
        loadAdd = _loading;
    }
}
