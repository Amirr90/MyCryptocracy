package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.modals.FilterModel;

public class FilterAdapter extends ListAdapter<FilterModel, AppViewHolder> {
    public static DiffUtil.ItemCallback<FilterModel> itemCallback = new DiffUtil.ItemCallback<FilterModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull FilterModel oldItem, @NonNull FilterModel newItem) {
            return oldItem.getSortText().equalsIgnoreCase(newItem.getSortText());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull FilterModel oldItem, @NonNull FilterModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    onAdapterClick onAdapterClick;

    public FilterAdapter(onAdapterClick onAdapterClick) {
        super(itemCallback);
        this.onAdapterClick = onAdapterClick;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FilterViewBinding coinViewBinding = FilterViewBinding.inflate(layoutInflater, parent, false);
        return new AppViewHolder(coinViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.filterViewBinding.setFilterModel(getItem(position));
        holder.filterViewBinding.textView10.setOnClickListener(v -> onAdapterClick.onClickItem(getItem(position)));

    }
}