package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.utility.UpdateFavouriteCoinsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavouriteCoinAdapter extends ListAdapter<CoinModal, AppViewHolder> {

    NavController navController;
    @Inject
    public FavouriteCoinAdapter(NavController navController) {
        super(itemCallback);
        this.navController = navController;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CoinViewBinding coinViewBinding = CoinViewBinding.inflate(layoutInflater, parent, false);
        return new AppViewHolder(coinViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {


        CoinModal coinModal = getItem(position);
        holder.coinViewBinding.setCoin(getItem(position));

        holder.coinViewBinding.checkBox.setChecked(true);

        if (null != coinModal) {
            double pos = coinModal.getMarket_cap_rank();
            holder.coinViewBinding.setPosition(pos);
        }
        holder.coinViewBinding.getRoot().setOnClickListener(v -> {

            if (null != coinModal) {
                Bundle bundle = new Bundle();
                bundle.putString(AppConstant.COIN_ID, coinModal.getId());
                bundle.putString(AppConstant.NAME, coinModal.getName());
                bundle.putString(AppConstant.SYMBOL, coinModal.getSymbol());
                navController.navigate(R.id.action_favouriteFragment_to_coinDetailFragment, bundle);
            }
        });

        holder.coinViewBinding.checkBox.setOnClickListener(v -> AppUtils.updateFavCoins(coinModal.getId(), holder.coinViewBinding.checkBox.isChecked(), favouriteCoinsListener));

    }


    public static DiffUtil.ItemCallback<CoinModal> itemCallback = new DiffUtil.ItemCallback<CoinModal>() {
        @Override
        public boolean areItemsTheSame(@NonNull CoinModal oldItem, @NonNull CoinModal newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CoinModal oldItem, @NonNull CoinModal newItem) {
            return oldItem.equals(newItem);
        }
    };

    UpdateFavouriteCoinsListener favouriteCoinsListener = new UpdateFavouriteCoinsListener() {
        @Override
        public void onSuccess(Object obj) {
            Toast.makeText(App.context, (String) obj, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed(String msg) {
            Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show();

        }
    };
}
