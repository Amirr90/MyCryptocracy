package com.e.pagingSource;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.e.cryptocracy.adapters.AppViewHolder;
import com.e.cryptocracy.databinding.CoinViewBinding;
import com.e.cryptocracy.modals.CoinModal;

public class PagingAdapter extends PagedListAdapter<CoinModal, AppViewHolder> {
    public PagingAdapter() {
        super(itemCallback);
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
            // navController.navigate(R.id.action_coinListFragment_to_coinDetailFragment);
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
