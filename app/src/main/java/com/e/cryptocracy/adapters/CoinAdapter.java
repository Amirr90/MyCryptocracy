package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.AppConstant;

import javax.inject.Inject;

public class CoinAdapter extends ListAdapter<CoinModal, AppViewHolder> {

    NavController navController;

    @Inject
    public CoinAdapter(NavController navController) {
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


        holder.coinViewBinding.setCoin(getItem(position));
        holder.coinViewBinding.getRoot().setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.COIN_ID, getItem(position).getId());
            bundle.putString(AppConstant.NAME, getItem(position).getName());
            bundle.putString(AppConstant.SYMBOL, getItem(position).getSymbol());
            navController.navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundle);
        });
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
}
