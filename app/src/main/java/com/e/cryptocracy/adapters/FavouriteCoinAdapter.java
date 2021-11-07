package com.e.cryptocracy.adapters;

import static com.e.cryptocracy.adapters.CoinAdapter.ITEM_ADD_VIEW;
import static com.e.cryptocracy.adapters.CoinAdapter.ITEM_COIN_VIEW;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

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

            if (null != coinModal)
                holder.coinViewBinding.checkBox.setOnClickListener(v -> AppUtils.updateFavCoins(coinModal.getId(), holder.coinViewBinding.checkBox.isChecked(), favouriteCoinsListener, v));
        } else {
            setupAdd(holder);
        }


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
            AppUtils.showSnackbar((String) obj, view);
        }

        @Override
        public void onFailed(String msg, View view) {
            AppUtils.showSnackbar(msg, view);
        }
    };

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0)
            return ITEM_COIN_VIEW;
        else return ITEM_COIN_VIEW;

    }

    private void setupAdd(AppViewHolder holder) {
        new AdMob(AppHomeScreen.getInstance(), holder.itemAddViewBinding.adViewContainer);
    }
}
