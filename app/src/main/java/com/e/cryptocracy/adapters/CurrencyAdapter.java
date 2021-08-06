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
import com.e.cryptocracy.databinding.CurrencyViewBinding;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppUtils;

import java.util.Random;

public class CurrencyAdapter extends ListAdapter<CurrencyModel, AppViewHolder> {

    int[] colorsDif;
    onAdapterClick adapterClick;

    public CurrencyAdapter(onAdapterClick onAdapterClick) {
        super(itemCallback);
        colorsDif = App.context.getResources().getIntArray(R.array.ingr_color_arr);
        this.adapterClick = onAdapterClick;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyViewBinding currencyViewBinding = CurrencyViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(currencyViewBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        final int random = new Random().nextInt((colorsDif.length - 1) + 1);
        holder.currencyViewBinding.setCurrency(getItem(position));
        holder.currencyViewBinding.textView22.getBackground().setTint(colorsDif[random]);
        holder.currencyViewBinding.getRoot().setOnClickListener(v -> adapterClick.onClickItem(getItem(position).getSymbol()));
    }

    public static DiffUtil.ItemCallback<CurrencyModel> itemCallback = new DiffUtil.ItemCallback<CurrencyModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurrencyModel oldItem, @NonNull CurrencyModel newItem) {
            return oldItem.getId().equalsIgnoreCase(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CurrencyModel oldItem, @NonNull CurrencyModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    /*List<CurrencyModel> currencyModels;

    int[] colorsDif = App.context.getResources().getIntArray(R.array.ingr_color_arr);
    public CurrencyAdapter(List<CurrencyModel> currencyModels) {
        this.currencyModels = currencyModels;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyViewBinding currencyViewBinding = CurrencyViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(currencyViewBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        final int random = new Random().nextInt((colorsDif.length - 1) + 1);
        holder.currencyViewBinding.setCurrency(currencyModels.get(position));
        holder.currencyViewBinding.textView22.getBackground().setTint(colorsDif[random]);
    }

    @Override
    public int getItemCount() {
        return null == currencyModels ? 0 : currencyModels.size();
    }

    public void addItem(List<CurrencyModel> modelList) {
        if (null == currencyModels)
            currencyModels = new ArrayList<>();
        currencyModels.addAll(modelList);
        notifyDataSetChanged();
    }*/
}
