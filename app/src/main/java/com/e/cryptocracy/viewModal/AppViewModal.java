package com.e.cryptocracy.viewModal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
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

    @Inject
    public AppViewModal(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public LiveData<List<CoinModal>> getCoins(String page) {
        return apiRepository.getCoins(page);
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

    public LiveData<PagedList<CoinModal>> getFavCoins() {
        return apiRepository.pagedCoinList;
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
}
