package com.e.cryptocracy.adapters;

import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.R;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppUtils;

import java.util.List;

public class GraphFilterKeysAdapter extends RecyclerView.Adapter<AppViewHolder> {

    List<String> keys;
    onAdapterClick adapterClick;
    int[] colorsDif = {App.context.getResources().getColor(R.color.purple_500),
            App.context.getResources().getColor(R.color.purple_200)};
    int selectedIndexPosition = 0;

    public GraphFilterKeysAdapter(List<String> keys, onAdapterClick onAdapterClick) {
        this.keys = keys;
        adapterClick = onAdapterClick;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilterViewBinding filterViewBinding = FilterViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(filterViewBinding);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.filterViewBinding.setFilterModel(new FilterModel("", keys.get(position)));
        holder.filterViewBinding.textView10.setOnClickListener(v -> {
            adapterClick.onClickItem(keys.get(position));
            selectedIndexPosition = position;
            notifyItemChanged(position);
        });
        holder.filterViewBinding.textView10.getBackground().setTint(selectedIndexPosition == position ? colorsDif[0] : colorsDif[1]);


    }

    @Override
    public int getItemCount() {
        return null == keys ? 0 : keys.size();
    }
}