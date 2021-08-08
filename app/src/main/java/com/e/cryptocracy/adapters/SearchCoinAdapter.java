package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.R;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.SearchedCoinViewBinding;
import com.e.cryptocracy.modals.SearchedCoinModal;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppUtils;

import java.util.Random;

public class SearchCoinAdapter extends ListAdapter<SearchedCoinModal, AppViewHolder> {
    int[] colorsDif;
    onAdapterClick adapterClick;

    public SearchCoinAdapter(onAdapterClick onAdapterClick) {
        super(itemCallback);
        colorsDif = App.context.getResources().getIntArray(R.array.ingr_color_arr);

        adapterClick = onAdapterClick;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchedCoinViewBinding searchedCoinViewBinding = SearchedCoinViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(searchedCoinViewBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        final int random = new Random().nextInt((colorsDif.length - 1) + 1);
        holder.searchedCoinViewBinding.textView22.getBackground().setTint(colorsDif[random]);
        holder.searchedCoinViewBinding.setSearchCoin(getItem(position));
        try {

            holder.searchedCoinViewBinding.getRoot().setOnClickListener(v -> adapterClick.onClickItem(getItem(position)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DiffUtil.ItemCallback<SearchedCoinModal> itemCallback = new DiffUtil.ItemCallback<SearchedCoinModal>() {
        @Override
        public boolean areItemsTheSame(@NonNull SearchedCoinModal oldItem, @NonNull SearchedCoinModal newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull SearchedCoinModal oldItem, @NonNull SearchedCoinModal newItem) {
            return oldItem.equals(newItem);
        }
    };
}
