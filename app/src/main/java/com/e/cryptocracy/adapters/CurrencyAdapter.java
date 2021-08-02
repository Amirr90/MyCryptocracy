package com.e.cryptocracy.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.CurrencyViewBinding;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.views.utility.AppUtils;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<AppViewHolder> {

    List<CurrencyModel> currencyModels;

    public CurrencyAdapter(List<CurrencyModel> currencyModels) {
        this.currencyModels = currencyModels;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyViewBinding currencyViewBinding = CurrencyViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(currencyViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.currencyViewBinding.setCurrency(currencyModels.get(position));
    }

    @Override
    public int getItemCount() {
        return null == currencyModels ? 0 : currencyModels.size();
    }
}
