package com.e.cryptocracy.adapters;

import static com.e.cryptocracy.adapters.CoinAdapter.ITEM_ADD_VIEW;
import static com.e.cryptocracy.adapters.CoinAdapter.ITEM_COIN_VIEW;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.apiResponse.TrendingCoinResponse;
import com.e.cryptocracy.R;
import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.databinding.ItemAddViewBinding;
import com.e.cryptocracy.databinding.TrendingCoinViewBinding;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.views.activity.AppHomeScreen;

public class TrendingCoinsAdapter extends ListAdapter<TrendingCoinResponse.TrendingCoins, AppViewHolder> {
    double price;
    public onAdapterClick adapterClick;

    public TrendingCoinsAdapter(onAdapterClick onAdapterClick) {
        super(itemCallback);
        this.adapterClick = onAdapterClick;

    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_COIN_VIEW) {
            TrendingCoinViewBinding binding = TrendingCoinViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
            return new AppViewHolder(binding);
        } else {
            ItemAddViewBinding itemAddViewBinding = ItemAddViewBinding.inflate(layoutInflater, parent, false);
            return new AppViewHolder(itemAddViewBinding);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {


        if (holder.getItemViewType() == ITEM_COIN_VIEW) {
            String value = AppUtils.getString(AppConstant.CURRENCY_PRICE_IN_SELECTED, App.context);
            price = value.isEmpty() ? 0.0 : Double.parseDouble(value);
            holder.trendingCoinViewBinding.setCoin(getItem(position).getItem());
            holder.trendingCoinViewBinding.textView6.setText(AppUtils.getCurrencyFormat(getItem(position).getItem().getPrice_btc() * price));

            holder.trendingCoinViewBinding.getRoot().setOnClickListener(v -> adapterClick.onClickItem(getItem(position).getItem()));
        } else {
            setupAdd(holder);
        }
    }

    public static DiffUtil.ItemCallback<TrendingCoinResponse.TrendingCoins> itemCallback = new DiffUtil.ItemCallback<TrendingCoinResponse.TrendingCoins>() {
        @Override
        public boolean areItemsTheSame(@NonNull TrendingCoinResponse.TrendingCoins oldItem, @NonNull TrendingCoinResponse.TrendingCoins newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TrendingCoinResponse.TrendingCoins oldItem, @NonNull TrendingCoinResponse.TrendingCoins newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0)
            return ITEM_ADD_VIEW;
        else return ITEM_COIN_VIEW;

    }

    private void setupAdd(AppViewHolder holder) {
        new AdMob(AppHomeScreen.getInstance(), holder.itemAddViewBinding.adViewContainer);
    }
}
