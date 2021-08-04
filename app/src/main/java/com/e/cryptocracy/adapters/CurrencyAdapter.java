package com.e.cryptocracy.adapters;

import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.CurrencyViewBinding;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppUtils;

import java.util.List;
import java.util.Random;

public class CurrencyAdapter extends RecyclerView.Adapter<AppViewHolder> {

    List<CurrencyModel> currencyModels;

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
        final int random = new Random().nextInt((colorsDif.length-1) + 1);
        holder.currencyViewBinding.setCurrency(currencyModels.get(position));
        holder.currencyViewBinding.textView22.getBackground().setTint(colorsDif[random]);
    }

    @Override
    public int getItemCount() {
        return null == currencyModels ? 0 : currencyModels.size();
    }
}
