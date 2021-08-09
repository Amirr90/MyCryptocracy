package com.e.cryptocracy.adapters;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.e.cryptocracy.databinding.InvestorViewBinding;
import com.e.cryptocracy.modals.Companies;
import com.e.cryptocracy.utility.AppUtils;

public class CompanyAdapter extends ListAdapter<Companies, AppViewHolder> {
    public CompanyAdapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InvestorViewBinding investorViewBinding = InvestorViewBinding.inflate(AppUtils.getInflater(parent), parent, false);
        return new AppViewHolder(investorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.investorViewBinding.setCompany(getItem(position));
    }

    public static DiffUtil.ItemCallback<Companies> itemCallback = new DiffUtil.ItemCallback<Companies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Companies oldItem, @NonNull Companies newItem) {
            return oldItem.getName().equalsIgnoreCase(newItem.getName());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Companies oldItem, @NonNull Companies newItem) {
            return oldItem.equals(newItem);
        }
    };
}
