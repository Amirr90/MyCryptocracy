package com.e.cryptocracy.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.CoinDetailViewBinding;
import com.e.cryptocracy.databinding.FilterViewBinding;

public class AppViewHolder extends RecyclerView.ViewHolder {
    FilterViewBinding filterViewBinding;
    public CoinDetailViewBinding coinDetailViewBinding;

    public AppViewHolder(@NonNull CoinDetailViewBinding coinDetailViewBinding) {
        super(coinDetailViewBinding.getRoot());
        this.coinDetailViewBinding = coinDetailViewBinding;
    }

    public AppViewHolder(@NonNull FilterViewBinding filterViewBinding) {
        super(filterViewBinding.getRoot());
        this.filterViewBinding = filterViewBinding;
    }
}
