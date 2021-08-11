package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.CoinTradingMarketViewBinding;
import com.e.cryptocracy.modals.CoinTradingModel;
import com.e.cryptocracy.utility.AppUtils;

public class CoinTradingListAdapter extends ListAdapter<CoinTradingModel, AppViewHolder> {
    onAdapterClick onAdapterClick;

    public CoinTradingListAdapter(onAdapterClick onAdapterClick) {
        super(itemCallback);
        this.onAdapterClick = onAdapterClick;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CoinTradingMarketViewBinding coinTradingMarketViewBinding = CoinTradingMarketViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(coinTradingMarketViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        CoinTradingModel coinTradingModel = getItem(position);
        holder.coinTradingMarketViewBinding.setTrading(getItem(position));
        holder.coinTradingMarketViewBinding.getRoot().setOnClickListener(v ->
                onAdapterClick.onClickItem(coinTradingModel.getTrade_url()));

    }

    public static DiffUtil.ItemCallback<CoinTradingModel> itemCallback = new DiffUtil.ItemCallback<CoinTradingModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CoinTradingModel oldItem, @NonNull CoinTradingModel newItem) {
            return oldItem.getCoin_id().equalsIgnoreCase(newItem.getCoin_id());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CoinTradingModel oldItem, @NonNull CoinTradingModel newItem) {
            return oldItem.equals(newItem);
        }
    };
}
