package com.e.cryptocracy.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.CoinDetailViewBinding;
import com.e.cryptocracy.databinding.CoinTradingMarketViewBinding;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.databinding.CurrencyViewBinding;
import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.databinding.InvestorViewBinding;
import com.e.cryptocracy.databinding.SearchedCoinViewBinding;
import com.e.cryptocracy.databinding.TrendingCoinViewBinding;

public class AppViewHolder extends RecyclerView.ViewHolder {
    public FilterViewBinding filterViewBinding;
    public CoinDetailViewBinding coinDetailViewBinding;
    CurrencyViewBinding currencyViewBinding;
    CoinTradingMarketViewBinding coinTradingMarketViewBinding;
    public CoinViewBinding coinViewBinding;
    TrendingCoinViewBinding trendingCoinViewBinding;
    SearchedCoinViewBinding searchedCoinViewBinding;
    InvestorViewBinding investorViewBinding;

    public AppViewHolder(@NonNull InvestorViewBinding investorViewBinding) {
        super(investorViewBinding.getRoot());
        this.investorViewBinding = investorViewBinding;
    }

    public AppViewHolder(@NonNull SearchedCoinViewBinding searchedCoinViewBinding) {
        super(searchedCoinViewBinding.getRoot());
        this.searchedCoinViewBinding = searchedCoinViewBinding;
    }

    public AppViewHolder(@NonNull TrendingCoinViewBinding trendingCoinViewBinding) {
        super(trendingCoinViewBinding.getRoot());
        this.trendingCoinViewBinding = trendingCoinViewBinding;
    }

    public AppViewHolder(@NonNull CoinViewBinding coinViewBinding) {
        super(coinViewBinding.getRoot());
        this.coinViewBinding = coinViewBinding;
    }

    public AppViewHolder(@NonNull CoinTradingMarketViewBinding coinTradingMarketViewBinding) {
        super(coinTradingMarketViewBinding.getRoot());
        this.coinTradingMarketViewBinding = coinTradingMarketViewBinding;
    }

    public AppViewHolder(@NonNull CurrencyViewBinding currencyViewBinding) {
        super(currencyViewBinding.getRoot());
        this.currencyViewBinding = currencyViewBinding;
    }

    public AppViewHolder(@NonNull CoinDetailViewBinding coinDetailViewBinding) {
        super(coinDetailViewBinding.getRoot());
        this.coinDetailViewBinding = coinDetailViewBinding;
    }

    public AppViewHolder(@NonNull FilterViewBinding filterViewBinding) {
        super(filterViewBinding.getRoot());
        this.filterViewBinding = filterViewBinding;
    }
}
