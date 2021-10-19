package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.e.cryptocracy.OnBackButtonClickListener;
import com.e.cryptocracy.R;
import com.e.cryptocracy.addservices.AdMob;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.databinding.ItemAddViewBinding;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.utility.UpdateFavouriteCoinsListener;
import com.e.cryptocracy.views.activity.AppHomeScreen;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CoinAdapter extends PagedListAdapter<CoinModal, AppViewHolder> {

    public static final int ITEM_COIN_VIEW = 1;
    public static final int ITEM_ADD_VIEW = 2;
    NavController navController;
    private static final String TAG = "CoinAdapter";
    List<String> favCoins = new ArrayList<>();


    @Inject
    public CoinAdapter(NavController navController) {
        super(itemCallback);
        this.navController = navController;
        String fav = AppUtils.getString(AppConstant.FAVOURITE_COINS, App.context);
        if (!fav.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> contactList = gson.fromJson(AppUtils.getString(AppConstant.FAVOURITE_COINS, App.context), type);
            favCoins.addAll(contactList);
        } else Log.d(TAG, "CoinAdapter: " + fav);

    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == ITEM_COIN_VIEW) {
            CoinViewBinding coinViewBinding = CoinViewBinding.inflate(layoutInflater, parent, false);
            return new AppViewHolder(coinViewBinding);
        } else {
            ItemAddViewBinding itemAddViewBinding = ItemAddViewBinding.inflate(layoutInflater, parent, false);
            return new AppViewHolder(itemAddViewBinding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_COIN_VIEW) {
            CoinModal coinModal = getItem(position);
            if (null != coinModal) {
                double pos = coinModal.getMarket_cap_rank();
                holder.coinViewBinding.setPosition(pos);


                holder.coinViewBinding.setCoin(getItem(position));

                holder.coinViewBinding.checkBox.setChecked(favCoins.contains(coinModal.getId()));


                holder.coinViewBinding.getRoot().setOnClickListener(v -> {

                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstant.COIN_ID, coinModal.getId());
                    bundle.putString(AppConstant.NAME, coinModal.getName());
                    bundle.putString(AppConstant.SYMBOL, coinModal.getSymbol());
                    navController.navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundle);
                });

                holder.coinViewBinding.checkBox.setOnClickListener(v -> AppUtils.updateFavCoins(coinModal.getId(), holder.coinViewBinding.checkBox.isChecked(), favouriteCoinsListener, v));


            }
        } else {
            setupAdd(holder);
        }


    }

    private void setupAdd(AppViewHolder holder) {
        new AdMob(AppHomeScreen.getInstance(), holder.itemAddViewBinding.adViewContainer);
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
        public void onSuccess(Object obj, View view) {
            // AppUtils.showSnackbar((String) obj, view);
            Toast.makeText(App.context, (String) obj, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed(String msg, View view) {
            // AppUtils.showSnackbar(msg, view);
            Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show();


        }
    };

    @Override
    public int getItemViewType(int position) {
        if (position % 13 == 0)
            return ITEM_ADD_VIEW;
        else return ITEM_COIN_VIEW;

    }
}
