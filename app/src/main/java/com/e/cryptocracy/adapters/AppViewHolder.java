package com.e.cryptocracy.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.CoinDetailViewBinding;
import com.e.cryptocracy.databinding.CoinTradingMarketViewBinding;
import com.e.cryptocracy.databinding.CurrencyViewBinding;
import com.e.cryptocracy.databinding.FilterViewBinding;

public class AppViewHolder extends RecyclerView.ViewHolder {
    FilterViewBinding filterViewBinding;
    public CoinDetailViewBinding coinDetailViewBinding;
    CurrencyViewBinding currencyViewBinding;
    CoinTradingMarketViewBinding coinTradingMarketViewBinding;

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
